package biz.devspot.management.game.framework.controller;

import biz.devspot.entity.framework.core.EntityManagerFactory;
import biz.devspot.management.game.framework.persistence.PersistenceManager;
import biz.devspot.management.game.framework.state.AbstractGameState;
import biz.devspot.management.game.framework.state.GameStateManager;
import org.json.JSONObject;
import uk.co.codeecho.mandrake.core.request.Request;

public class LoadGameController extends ActionController {

    private PersistenceManager persistenceManager;

    public LoadGameController(PersistenceManager persistenceManager) {
        this.persistenceManager = persistenceManager;
    }

    @Override
    protected void handle(Request request, JSONObject data) throws Exception {
        AbstractGameState gameState = persistenceManager.loadGame(data.getString("name"));
        GameStateManager.setGameState(gameState);
        EntityManagerFactory.getManager().clearCache();
    }

}
