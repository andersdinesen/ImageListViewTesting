package com.andersdinesen.imagelistview;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ad on 27-05-2015.
 */
public class ListGroup {
    public String string;
    public final List<String> children = new ArrayList<String>();

    public ListGroup(String string) {
        this.string = string;
    }
}
