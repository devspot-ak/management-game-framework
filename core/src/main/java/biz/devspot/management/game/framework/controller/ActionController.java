package biz.devspot.management.game.framework.controller;

import java.util.Map;
import org.apache.commons.io.IOUtils;
import org.json.JSONObject;
import uk.co.codeecho.mandrake.core.controller.AbstractController;
import uk.co.codeecho.mandrake.core.request.Request;
import uk.co.codeecho.mandrake.core.request.Response;

public abstract class ActionController extends AbstractController{

    @Override
    protected void handle(Request request, Response response, Map<String, Object> model) throws Exception {
        JSONObject data = new JSONObject(IOUtils.toString(request.getBody()));
        handle(request, data);
    }
    
    protected abstract void handle(Request request, JSONObject data) throws Exception;

}
