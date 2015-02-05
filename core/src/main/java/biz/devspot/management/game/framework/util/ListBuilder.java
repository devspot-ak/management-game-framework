package biz.devspot.management.game.framework.util;

import java.util.ArrayList;
import java.util.List;

public class ListBuilder<I> {

    private List<I> list;

    public ListBuilder() {
        this.list = new ArrayList<I>();
    }

    public ListBuilder(List<I> list) {
        this.list = list;
    }
    
    public ListBuilder add(I item){
        list.add(item);
        return this;
    }
    
    public List<I> build(){
        return list;
    }
    
}
