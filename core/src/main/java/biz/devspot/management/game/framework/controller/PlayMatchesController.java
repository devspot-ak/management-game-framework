package biz.devspot.management.game.framework.controller;

import biz.devspot.entity.framework.core.EntityManagerFactory;
import biz.devspot.entity.framework.core.query.QueryBuilder;
import biz.devspot.management.game.framework.match.AbstractMatchEngine;
import biz.devspot.management.game.framework.model.AbstractRound;
import biz.devspot.management.game.framework.model.state.RoundStates;
import biz.devspot.management.game.framework.state.AbstractGameState;
import biz.devspot.management.game.framework.state.GameStateManager;
import biz.devspot.management.game.framework.state.GameStates;
import java.util.List;
import org.json.JSONObject;
import uk.co.codeecho.mandrake.core.request.Request;

public class PlayMatchesController extends TransactionalActionController {

    private AbstractMatchEngine matchEngine;

    public PlayMatchesController(AbstractMatchEngine matchEngine) {
        this.matchEngine = matchEngine;
    }
    
    @Override
    protected void doHandle(Request request, JSONObject data) throws Exception {
        AbstractGameState gameState = GameStateManager.getGameState();
        if (!gameState.getStatus().equals(GameStates.PRE_MATCH)) {
            throw new IllegalStateException(gameState.getStatus());
        }
        int week = gameState.getWeek();
        int day = gameState.getDay();
        List<AbstractRound> rounds = EntityManagerFactory.getManager().find(AbstractRound.class, new QueryBuilder().where("status").isEqualTo(RoundStates.PENDING).and("week").isEqualTo(week).and("day").isEqualTo(day).build());
        for (AbstractRound round : rounds) {
            round.getCompetition().playRound(matchEngine);
        }
        gameState.setStatus(GameStates.POST_MATCH);
    }

}
