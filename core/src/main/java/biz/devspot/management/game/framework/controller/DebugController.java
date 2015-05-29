package biz.devspot.management.game.framework.controller;

import biz.devspot.management.game.framework.debug.RemoteDebugger;
import java.util.Map;
import uk.co.codeecho.mandrake.core.controller.AbstractController;
import uk.co.codeecho.mandrake.core.request.Request;
import uk.co.codeecho.mandrake.core.request.Response;

public class DebugController extends AbstractController{

    private RemoteDebugger debugger;

    public DebugController(RemoteDebugger debugger) {
        this.debugger = debugger;
    }
    
    @Override
    protected void handle(Request request, Response response, Map<String, Object> model) throws Exception {
        String cmd = request.getParameter("cmd");
        Object result = debugger.execute(cmd);
        String output = "null";
        if(result!=null){
            output = result.toString();
        }
        response.setHeader("Content-Type", "text/plain");
        response.setBody(output);
    }

}
