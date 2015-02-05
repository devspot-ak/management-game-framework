package biz.devspot.management.game.framework.util;

import java.util.ArrayList;
import java.util.Collection;

public class RandomAccessList<E> extends ArrayList<E>{

    public RandomAccessList(int initialCapacity) {
        super(initialCapacity);
    }

    public RandomAccessList() {
    }

    public RandomAccessList(Collection<? extends E> c) {
        super(c);
    }

    public E get(){
        int index = (int) Math.round(Math.random() * (size()-1));
        return get(index);
    }
}
