package biz.devspot.management.game.framework.persistence;

import biz.devspot.management.game.framework.state.AbstractGameState;
import java.io.IOException;
import java.util.List;

public interface PersistenceManager {

    public List<DataPack> getDataPacks() throws IOException;
    
    public DataPack getDataPack(String id) throws IOException;
    
    public void loadDataPack(DataPack dataPack) throws IOException;
    
    public void saveDataPack(DataPack dataPack) throws IOException;
    
    public void saveGame(AbstractGameState gameState) throws IOException;
    
    public List<AbstractGameState> getSavedGames() throws IOException;
    
    public AbstractGameState loadGame(String gameName) throws IOException;
    
}
