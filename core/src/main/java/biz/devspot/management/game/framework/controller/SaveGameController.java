package biz.devspot.management.game.framework.controller;

import biz.devspot.management.game.framework.persistence.PersistenceManager;
import biz.devspot.management.game.framework.state.AbstractGameState;
import biz.devspot.management.game.framework.state.GameStateManager;
import org.json.JSONObject;
import uk.co.codeecho.mandrake.core.request.Request;

public class SaveGameController extends ActionController {

    private PersistenceManager persistenceManager;

    public SaveGameController(PersistenceManager persistenceManager) {
        this.persistenceManager = persistenceManager;
    }
    
    @Override
    protected void handle(Request request, JSONObject data) throws Exception {
        AbstractGameState gameState = GameStateManager.getGameState();
        gameState.setName(data.getString("name"));
        persistenceManager.saveGame(gameState);
    }

}
