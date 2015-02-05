package biz.devspot.management.game.framework.rest;

import java.util.HashMap;
import java.util.Map;

public class Request {

    private Map<String, String> parameters = new HashMap<String, String>();

    public Map<String, String> getParameters() {
        return parameters;
    }

    public void setParameters(Map<String, String> parameters) {
        this.parameters = parameters;
    }
    
    public String getParameter(String key){
        return parameters.get(key);
    }
    
    public String getParameter(String key, String defaultValue){
        String result = getParameter(key);
        if(result == null){
            result = defaultValue;
        }
        return result;
    }
    
}
