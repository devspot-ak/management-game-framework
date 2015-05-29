package biz.devspot.management.game.framework.controller;

import biz.devspot.management.game.framework.state.AbstractGameState;
import biz.devspot.management.game.framework.state.GameStateManager;
import biz.devspot.management.game.framework.state.GameStates;
import org.json.JSONObject;
import uk.co.codeecho.mandrake.core.request.Request;

public class EndSeasonController extends TransactionalActionController {

    @Override
    protected void doHandle(Request request, JSONObject data) throws Exception {
        AbstractGameState gameState = GameStateManager.getGameState();
        if (!gameState.getStatus().equals(GameStates.END_OF_SEASON)) {
            throw new IllegalStateException(gameState.getStatus());
        }
        gameState.getWorld().rollover();
        gameState.setSeason(gameState.getSeason() + 1);
        gameState.setWeek(1);
        gameState.setDay(1);
        gameState.setStatus(GameStates.NORMAL);
    }

}
