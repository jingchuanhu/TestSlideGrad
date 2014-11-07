package test.jch.com.testslidegrad;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

import java.util.ArrayList;


public class MainActivity extends ActionBarActivity {

    private SlideGridView slidgrid;
    private SlideGrideViewAdapter mAdapter;
    private ArrayList<OItem> oItems = new ArrayList<OItem>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initialize();
    }

    /**
     *
     */
    private void initialize() {

        slidgrid = (SlideGridView) findViewById(R.id.slidgrid);
        mAdapter = new SlideGrideViewAdapter(oItems, getApplicationContext());
        slidgrid.setAdapter(mAdapter);

        initData();
        mAdapter.notifyDataSetChanged();
    }

    private ArrayList<OItem> initData() {

        for (int i = 0; i < 9; i++) {
            OItem oItem = new OItem();
            oItem.setItemText("item" + i);
            ArrayList<String> subItems = new ArrayList<String>();
            for (int j = 0; j < 18; j++) {
                subItems.add(i + "subItem" + j);
            }
            oItem.setItemLists(subItems);
            oItems.add(oItem);
        }
        return oItems;

    }
}
