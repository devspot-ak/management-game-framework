package biz.devspot.management.game.framework.state;

public interface GameStateBuilder<GS extends AbstractGameState> {

    public GS build();
    
}
