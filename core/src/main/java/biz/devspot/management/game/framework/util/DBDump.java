package biz.devspot.management.game.framework.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public interface DBDump {

    public void exportDump(OutputStream destination) throws IOException;
    
    public void importDump(InputStream source) throws IOException;
    
}
