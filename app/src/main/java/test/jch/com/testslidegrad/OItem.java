package test.jch.com.testslidegrad;

import java.util.ArrayList;

/**
 * Created by ACER on 2014/11/7.
 */
public class OItem {

    /* 每一个item的title */
    private String itemText;
    private ArrayList<String> itemLists = new ArrayList<String>();

    public String getItemText() {
        return itemText;
    }

    public void setItemText(String itemText) {
        this.itemText = itemText;
    }

    public ArrayList<String> getItemLists() {
        return itemLists;
    }

    public void setItemLists(ArrayList<String> itemLists) {
        this.itemLists = itemLists;
    }
}
