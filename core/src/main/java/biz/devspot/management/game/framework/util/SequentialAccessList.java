package biz.devspot.management.game.framework.util;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;

public class SequentialAccessList<E> extends LinkedList<E>{

    private int index = -1;
    
    public SequentialAccessList() {
    }

    public SequentialAccessList(Collection<? extends E> c) {
        super(c);
    }

    public E get(){
        index = index + 1;
        if(index >= size()){
            index = 0;
        }
        return get(index);
    }
    
    public void shuffle(){
        Collections.shuffle(this);
    }
}
