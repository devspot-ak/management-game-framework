package biz.devspot.management.game.framework.persistence;

import biz.devspot.management.game.framework.state.AbstractGameState;
import biz.devspot.management.game.framework.util.DBDump;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import uk.co.revsys.resource.repository.ResourceRepository;
import uk.co.revsys.resource.repository.model.Resource;

public class PersistenceManagerImpl implements PersistenceManager {

    private final ResourceRepository resourceRepository;
    private final ObjectMapper objectMapper;
    private final DBDump dbDump;
    private final Class<? extends AbstractGameState> gameStateType;

    public PersistenceManagerImpl(ResourceRepository resourceRepository, ObjectMapper objectMapper, DBDump dbDump, Class<? extends AbstractGameState> gameStateType) {
        this.resourceRepository = resourceRepository;
        this.objectMapper = objectMapper;
        this.dbDump = dbDump;
        this.gameStateType = gameStateType;
    }

    @Override
    public List<DataPack> getDataPacks() throws IOException {
        List<DataPack> dataPacks = new ArrayList<DataPack>();
        List<Resource> resources = resourceRepository.listResources(".");
        for (Resource resource : resources) {
            if (resource.getName().endsWith(".dpmd")) {
                InputStream stream = resourceRepository.read(resource);
                DataPack dataPack = objectMapper.readValue(stream, DataPack.class);
                dataPacks.add(dataPack);
                stream.close();
            }
        }
        return dataPacks;
    }

    @Override
    public DataPack getDataPack(String id) throws IOException {
        Resource resource = new Resource(id + ".dpmd");
        InputStream stream = resourceRepository.read(resource);
        DataPack dataPack = objectMapper.readValue(stream, DataPack.class);
        return dataPack;
    }

    @Override
    public void loadDataPack(DataPack dataPack) throws IOException {
        Resource resource = new Resource(dataPack.getId() + ".dp");
        InputStream stream = resourceRepository.read(resource);
        dbDump.importDump(stream);
    }

    @Override
    public void saveDataPack(DataPack dataPack) throws IOException {
        Resource metaData = new Resource(dataPack.getId() + ".dpmd");
        resourceRepository.write(metaData, new ByteArrayInputStream(objectMapper.writeValueAsBytes(dataPack)));
        Resource data = new Resource(dataPack.getId() + ".dp");
        OutputStream outputStream = resourceRepository.getOutputStream(data);
        dbDump.exportDump(outputStream);
        outputStream.close();
    }

    @Override
    public void saveGame(AbstractGameState gameState) throws IOException {
        Resource metaData = new Resource(gameState.getName() + ".sgmd");
        resourceRepository.write(metaData, new ByteArrayInputStream(objectMapper.writeValueAsBytes(gameState)));
        Resource data = new Resource(gameState.getName() + ".sg");
        OutputStream outputStream = resourceRepository.getOutputStream(data);
        dbDump.exportDump(outputStream);
        outputStream.close();
    }

    @Override
    public List<AbstractGameState> getSavedGames() throws IOException {
        System.out.println("getSavedGames");
        List<AbstractGameState> savedGames = new ArrayList<AbstractGameState>();
        List<Resource> resources = resourceRepository.listResources(".");
        for (Resource resource : resources) {
            if (resource.getName().endsWith(".sgmd")) {
                InputStream stream = resourceRepository.read(resource);
                AbstractGameState savedGame = objectMapper.readValue(stream, gameStateType);
                savedGames.add(savedGame);
                stream.close();
            }
        }
        return savedGames;
    }

    @Override
    public AbstractGameState loadGame(String gameName) throws IOException {
        Resource resource = new Resource(gameName + ".sgmd");
        InputStream stream = resourceRepository.read(resource);
        AbstractGameState gameState = objectMapper.readValue(stream, gameStateType);
        stream.close();
        resource = new Resource(gameName + ".sg");
        stream = resourceRepository.read(resource);
        dbDump.importDump(stream);
        stream.close();
        return gameState;
    }

}
