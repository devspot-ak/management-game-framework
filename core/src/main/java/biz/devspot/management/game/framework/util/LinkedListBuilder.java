package biz.devspot.management.game.framework.util;

import java.util.LinkedList;

public class LinkedListBuilder<I> extends ListBuilder<I>{

    public LinkedListBuilder() {
        super(new LinkedList<I>());
    }

}
