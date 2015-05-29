package biz.devspot.management.game.framework.controller;

import biz.devspot.entity.framework.core.EntityManagerFactory;
import biz.devspot.management.game.framework.model.AbstractWorld;
import biz.devspot.management.game.framework.persistence.DataPack;
import biz.devspot.management.game.framework.persistence.PersistenceManager;
import biz.devspot.management.game.framework.state.AbstractGameState;
import biz.devspot.management.game.framework.state.GameStateBuilder;
import biz.devspot.management.game.framework.state.GameStateManager;
import biz.devspot.management.game.framework.state.GameStates;
import org.json.JSONObject;
import uk.co.codeecho.mandrake.core.request.Request;

public class NewGameController extends ActionController {

    private PersistenceManager persistenceManager;
    private GameStateBuilder gameStateBuilder;

    public NewGameController(PersistenceManager persistenceManager, GameStateBuilder gameStateBuilder) {
        this.persistenceManager = persistenceManager;
        this.gameStateBuilder = gameStateBuilder;
    }

    @Override
    protected void handle(Request request, JSONObject data) throws Exception {
        DataPack dataPack = persistenceManager.getDataPack(data.getString("id"));
        persistenceManager.loadDataPack(dataPack);
        EntityManagerFactory.getManager().clearCache();
        AbstractGameState gameState = gameStateBuilder.build();
        gameState.setStatus(GameStates.NORMAL);
        String worldId = dataPack.getWorld();
        AbstractWorld world = (AbstractWorld) EntityManagerFactory.getManager().findById(worldId);
        gameState.setWorld(world);
        GameStateManager.setGameState(gameState);
    }

}
