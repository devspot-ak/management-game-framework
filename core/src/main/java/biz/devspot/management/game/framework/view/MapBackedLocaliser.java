package biz.devspot.management.game.framework.view;

import java.util.Map;

public class MapBackedLocaliser implements Localiser{

    private Map<String, String> map;

    public MapBackedLocaliser(Map<String, String> map) {
        this.map = map;
    }

    @Override
    public String get(String identifier) {
        return get(identifier, "{{" + identifier + "}}");
    }
    
    @Override
    public String get(String identifier, String defaultValue) {
        if(map.containsKey(identifier)){
            return map.get(identifier);
        }else{
            return defaultValue;
        }
    }

}
