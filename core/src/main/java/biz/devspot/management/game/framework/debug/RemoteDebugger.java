package biz.devspot.management.game.framework.debug;

public interface RemoteDebugger {

    public Object execute(String command) throws Exception;
    
}
