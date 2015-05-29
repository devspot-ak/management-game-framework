package biz.devspot.management.game.framework.view;

public interface Localiser {

    public String get(String identifier);
    
    public String get(String identifier, String defaultValue);
    
}
