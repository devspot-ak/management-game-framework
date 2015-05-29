package biz.devspot.management.game.framework.controller;

import biz.devspot.entity.framework.core.EntityManagerFactory;
import biz.devspot.management.game.framework.builder.AbstractManagerBuilder;
import biz.devspot.management.game.framework.builder.MessageBuilder;
import biz.devspot.management.game.framework.model.AbstractClub;
import biz.devspot.management.game.framework.model.AbstractManager;
import biz.devspot.management.game.framework.state.GameStateManager;
import org.json.JSONObject;
import uk.co.codeecho.mandrake.core.request.Request;

public class StartGameController extends TransactionalActionController {

    private AbstractManagerBuilder managerBuilder;

    public StartGameController(AbstractManagerBuilder managerBuilder) {
        this.managerBuilder = managerBuilder;
    }
    
    @Override
    protected void doHandle(Request request, JSONObject data) throws Exception {
        AbstractClub club = (AbstractClub) EntityManagerFactory.getManager().findById(data.getString("club"));
        AbstractManager manager = (AbstractManager) managerBuilder.setFirstName(data.getString("firstName")).setSurname(data.getString("surname")).build();
        club.appointManager(manager);
        GameStateManager.getGameState().setManager(manager);
        new MessageBuilder(club.getName() + " appoint " + manager.getName() + " as new manager.", club.getName() + " have appointed " + manager.getName() + " as their new manager for the new season.").build();
    }

}
