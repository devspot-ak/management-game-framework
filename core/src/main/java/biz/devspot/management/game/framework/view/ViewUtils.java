package biz.devspot.management.game.framework.view;

public class ViewUtils {
    
    public boolean instanceOf(Object object, String type) throws ClassNotFoundException{
        return getClass(type).isInstance(object);
    }
    
    public Class getClass(String name) throws ClassNotFoundException{
        return Class.forName(name);
    }
    
}
