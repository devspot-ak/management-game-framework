package biz.devspot.management.game.framework.state;

public class GameStateManager {

    private static AbstractGameState gameState;

    public static AbstractGameState getGameState() {
        return gameState;
    }

    public static void setGameState(AbstractGameState gameState) {
        GameStateManager.gameState = gameState;
    }

}
