package com.dvidal.beastmainproject.views.RushViews;

import com.dvidal.beastmainproject.enties.RushEvent;

import java.util.List;

/**
 * Created by diegovidal on 08/09/17.
 */

public class Item {

    public int type;
    public String header;
    public RushEvent rushEvent;
    public List<Item> invisibleChildren;

    public Item(int type, String header) {
        this.type = type;
        this.header = header;
    }

    public Item(int type, RushEvent rushEvent) {
        this.type = type;
        this.rushEvent = rushEvent;
    }
}
