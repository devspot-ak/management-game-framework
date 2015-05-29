package biz.devspot.management.game.framework.controller;

import biz.devspot.entity.framework.core.EntityManagerFactory;
import biz.devspot.entity.framework.core.query.QueryBuilder;
import biz.devspot.management.game.framework.model.AbstractRound;
import biz.devspot.management.game.framework.model.state.RoundStates;
import biz.devspot.management.game.framework.state.AbstractGameState;
import biz.devspot.management.game.framework.state.GameStateManager;
import biz.devspot.management.game.framework.state.GameStates;
import org.json.JSONObject;
import uk.co.codeecho.mandrake.core.request.Request;

public class AdvanceController extends ActionController {

    @Override
    protected void handle(Request request, JSONObject data) throws Exception {
        AbstractGameState gameState = GameStateManager.getGameState();
        if (gameState.getStatus().equals(GameStates.POST_MATCH)) {
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
            if (week == gameState.getWorld().getSeasonLength() + 1) {
                gameState.setStatus(GameStates.END_OF_SEASON);
            }
            gameState.setDay(day);
            gameState.setWeek(week);
        }
    }

}
