package biz.devspot.management.game.framework.rest;

import biz.devspot.entity.framework.core.EntityManagerFactory;
import biz.devspot.entity.framework.core.query.QueryBuilder;
import biz.devspot.management.game.framework.builder.AbstractManagerBuilder;
import biz.devspot.management.game.framework.match.MatchEngine;
import biz.devspot.management.game.framework.model.AbstractClub;
import biz.devspot.management.game.framework.model.AbstractManager;
import biz.devspot.management.game.framework.model.AbstractPlayer;
import biz.devspot.management.game.framework.model.AbstractRound;
import biz.devspot.management.game.framework.model.AbstractWorld;
import biz.devspot.management.game.framework.model.state.RoundStates;
import biz.devspot.management.game.framework.persistence.DataPack;
import biz.devspot.management.game.framework.persistence.PersistenceManager;
import biz.devspot.management.game.framework.state.AbstractGameState;
import biz.devspot.management.game.framework.state.GameStateBuilder;
import biz.devspot.management.game.framework.state.GameStates;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.neuland.jade4j.JadeConfiguration;
import de.neuland.jade4j.exceptions.JadeException;
import de.neuland.jade4j.template.JadeTemplate;
import de.neuland.jade4j.template.TemplateLoader;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.OPTIONS;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;

@Path("/")
public abstract class AbstractRestService<GS extends AbstractGameState> {

    private final ObjectMapper objectMapper;
    private final AbstractManagerBuilder managerBuilder;
    private final GameStateBuilder<GS> gameStateBuilder;
    private final PersistenceManager persistenceManager;
    private final JadeConfiguration jadeConfig;
    private final MatchEngine matchEngine;
    protected GS gameState;

    public AbstractRestService(ObjectMapper objectMapper, PersistenceManager persistenceManager, GameStateBuilder gameStateBuilder, AbstractManagerBuilder managerBuilder, TemplateLoader templateLoader, MatchEngine matchEngine) {
        this.objectMapper = objectMapper;
        this.persistenceManager = persistenceManager;
        this.managerBuilder = managerBuilder;
        this.gameStateBuilder = gameStateBuilder;
        jadeConfig = new JadeConfiguration();
        jadeConfig.setTemplateLoader(templateLoader);
        jadeConfig.setCaching(false);
        jadeConfig.setPrettyPrint(false);
        this.matchEngine = matchEngine;
    }

    @GET
    @Path("/view/{view}")
    @Produces(MediaType.TEXT_HTML)
    public Response getView(@PathParam("view") String view, @Context UriInfo ui) {
        try {
            Map<String, Object> model = new HashMap<String, Object>();
            model.put("entityManager", EntityManagerFactory.getManager());
            model.put("persistenceManager", persistenceManager);
            model.put("state", gameState);
            Request request = new Request();
            for (Entry<String, List<String>> entry : ui.getQueryParameters().entrySet()) {
                request.getParameters().put(entry.getKey(), entry.getValue().get(0));
            }
            model.put("request", request);
            JadeTemplate template = jadeConfig.getTemplate("views/" + view + ".jade");
            String html = jadeConfig.renderTemplate(template, model);
            return buildResponse(html);
        } catch (IOException ex) {
            return buildErrorResponse(ex);
        } catch (JadeException ex) {
            return buildErrorResponse(ex);
        }
    }

    @POST
    @Path("/load/new")
    public Response loadDataPack(@FormParam("id") String id) {
        try {
            DataPack dataPack = persistenceManager.getDataPack(id);
            persistenceManager.loadDataPack(dataPack);
            gameState = gameStateBuilder.build();
            gameState.setStatus(GameStates.NORMAL);
            String worldId = dataPack.getWorld();
            AbstractWorld world = (AbstractWorld) EntityManagerFactory.getManager().findById(worldId);
            gameState.setWorld(world);
            return buildResponse();
        } catch (IOException ex) {
            return buildErrorResponse(ex);
        } catch (RuntimeException ex) {
            return buildErrorResponse(ex);
        }
    }

    @OPTIONS
    @Path("/load/new")
    public Response getLoadDataPackOptions() {
        return getOptions();
    }

    @POST
    @Path("/start")
    public Response startGame(@FormParam("firstName") String firstName, @FormParam("surname") String surname, @FormParam("club") String clubId) {
        try {
            EntityManagerFactory.getManager().openTransaction();
            AbstractClub club = (AbstractClub) EntityManagerFactory.getManager().findById(clubId);
            AbstractManager manager = (AbstractManager) managerBuilder.setFirstName(firstName).setSurname(surname).build();
            club.appointManager(manager);
            gameState.setManager(manager);
            EntityManagerFactory.getManager().commitTransaction();
            return buildResponse();
        } catch (RuntimeException ex) {
            return buildErrorResponse(ex);
        }
    }

    @OPTIONS
    @Path("/start")
    public Response getStartGameOptions() {
        return getOptions();
    }

    @POST
    @Path("/advance")
    public Response advance() {
        if(gameState.getStatus().equals(GameStates.POST_MATCH)){
            gameState.setStatus(GameStates.NORMAL);
        }
        if (!gameState.getStatus().equals(GameStates.NORMAL)) {
            throw new IllegalStateException(gameState.getStatus());
        }
        int week = gameState.getWeek();
        int day = gameState.getDay();
        AbstractRound round = EntityManagerFactory.getManager().findOne(AbstractRound.class, new QueryBuilder().where("status").isEqualTo(RoundStates.PENDING).and("week").isEqualTo(week).and("day").isEqualTo(day).build());
        if (round != null) {
            gameState.setStatus(GameStates.PRE_MATCH);
        } else {
            day = day + 1;
            if (day > 7) {
                day = 1;
                week = week + 1;
            }
            if(week == gameState.getWorld().getSeasonLength()+1){
                gameState.setStatus(GameStates.END_OF_SEASON);
            }
            gameState.setDay(day);
            gameState.setWeek(week);
        }
        return buildResponse();
    }
    
    @POST
    @Path("/endSeason")
    public Response endSeason(){
        EntityManagerFactory.getManager().openTransaction();
        if (!gameState.getStatus().equals(GameStates.END_OF_SEASON)) {
            throw new IllegalStateException(gameState.getStatus());
        }
        gameState.getWorld().rollover();
        gameState.setSeason(gameState.getSeason()+1);
        gameState.setWeek(1);
        gameState.setDay(1);
        gameState.setStatus(GameStates.NORMAL);
        EntityManagerFactory.getManager().commitTransaction();
        return buildResponse();
    }
    
    @POST
    @Path("/playMatches")
    public Response playMatches() {
        if (!gameState.getStatus().equals(GameStates.PRE_MATCH)) {
            throw new IllegalStateException(gameState.getStatus());
        }
        int week = gameState.getWeek();
        int day = gameState.getDay();
        EntityManagerFactory.getManager().openTransaction();
        List<AbstractRound> rounds = EntityManagerFactory.getManager().find(AbstractRound.class, new QueryBuilder().where("status").isEqualTo(RoundStates.PENDING).and("week").isEqualTo(week).and("day").isEqualTo(day).build());
        for(AbstractRound round: rounds){
            round.getCompetition().playRound(matchEngine);
        }
        EntityManagerFactory.getManager().commitTransaction();
        gameState.setStatus(GameStates.POST_MATCH);
        return buildResponse();
    }

    @POST
    @Path("/save")
    public Response save(@FormParam("name") String name) {
        try {
            gameState.setName(name);
            persistenceManager.saveGame(gameState);
            return buildResponse();
        } catch (IOException ex) {
            return buildErrorResponse(ex);
        }
    }

    @POST
    @Path("/load")
    public Response load(@FormParam("name") String name) {
        try {
            gameState = (GS) persistenceManager.loadGame(name);
            return buildResponse();
        } catch (IOException ex) {
            return buildErrorResponse(ex);
        }
    }
    
    @POST
    @Path("/{id}/release")
    public Response releasePlayer(@PathParam("id") String id){
        System.out.println("Release Player");
        EntityManagerFactory.getManager().openTransaction();
        AbstractPlayer player = (AbstractPlayer) EntityManagerFactory.getManager().findById(id);
        System.out.println("Before " + player.getClub());
        player.release();
        System.out.println("After " + player.getClub());
        EntityManagerFactory.getManager().commitTransaction();
        System.out.println("Committed " + player.getClub());
        System.out.println("Release Player Done");
        return buildResponse();
    }

    protected Response getOptions() {
        return Response.status(Status.NO_CONTENT).header("Access-Control-Allow-Origin", "*").header("Access-Control-Allow-Methods", "GET,PUT,POST,DELETE").header("Access-Control-Allow-Headers", "accept, content-type").build();
    }

    protected Response buildJSONResponse(Object content) {
        try {
            return buildResponse(objectMapper.writeValueAsString(content));
        } catch (JsonProcessingException ex) {
            return buildErrorResponse(ex);
        } catch (RuntimeException ex) {
            return buildErrorResponse(ex);
        }
    }

    protected Response buildResponse() {
        return buildResponse(Status.NO_CONTENT, "");
    }

    protected Response buildResponse(String content) {
        return buildResponse(Status.OK, content);
    }

    protected Response buildErrorResponse(Exception ex) {
        return buildErrorResponse(Status.INTERNAL_SERVER_ERROR, ex);
    }

    protected Response buildErrorResponse(Status status, Exception ex) {
        ex.printStackTrace();
        return buildResponse(status, ex.getMessage());
    }

    protected Response buildResponse(Status status, String content) {
        Response response = Response.status(status).entity(content).header("Access-Control-Allow-Origin", "*").build();
        System.out.println(response.getStatus());
        System.out.println(response.getEntity());
        System.out.println(response.getMetadata());
        return response;
    }

}
