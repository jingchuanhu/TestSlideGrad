package test.jch.com.testslidegrad;

import android.content.Context;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by ACER on 2014/11/7.
 */
public class SlideGrideViewAdapter extends BaseAdapter {


    private ArrayList<OItem> datas;
    private Context context;
    private int subColumeNum = 3;

    public SlideGrideViewAdapter(ArrayList<OItem> items, Context context) {
        this.datas = items;
        this.context = context;
    }

    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public Object getItem(int position) {
        return datas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = LayoutInflater.from(context).inflate(R.layout.slidegrid_item_layout, null);
        TextView textView = (TextView) view.findViewById(R.id.slidegrid_item_tv);
        textView.setText(datas.get(position).getItemText());

        return view;
    }


    public ArrayList<OItem> getDatas() {
        return datas;
    }

    public View getSubview(int position) {

        ArrayList<String> subItems = datas.get(position).getItemLists();
        TableLayout tbLayout = new TableLayout(context);
        TableLayout.LayoutParams params = new TableLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        tbLayout.setOrientation(LinearLayout.VERTICAL);
        tbLayout.setBackgroundColor(context.getResources().getColor(android.R.color.holo_blue_bright));
        tbLayout.setLayoutParams(params);

        TableRow row = new TableRow(context);
        TableRow.LayoutParams tbParam = new TableRow.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        tbLayout.setWeightSum(subColumeNum);
        row.setLayoutParams(tbParam);
        for (int j = 0; j < subColumeNum; j++) {
            TextView subItemTv = new TextView(context);
            subItemTv.setLayoutParams(new LinearLayout.LayoutParams(60, 60, 1));
            subItemTv.setText(subItems.get(1 * subColumeNum + j));
            subItemTv.setTextSize(TypedValue.COMPLEX_UNIT_SP, 15);
            subItemTv.setTextColor(context.getResources().getColor(android.R.color.holo_orange_dark));
            tbLayout.addView(subItemTv, j);

            //TODO 添加监听事件。
        }
//        tbLayout.addView(row);
//        int size = subItems.size();
//        int lineNum = (int) Math.ceil((double) size / (double) subColumeNum);
//        for (int i = 0; i < lineNum; i++) {
//            TableRow row = new TableRow(context);
//            TableRow.LayoutParams tbParam = new TableRow.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 100);
//            tbLayout.setWeightSum(subColumeNum);
//            row.setLayoutParams(tbParam);
//            for (int j = 0; j < subColumeNum; j++) {
//                TextView subItemTv = new TextView(context);
//                subItemTv.setLayoutParams(new LinearLayout.LayoutParams(60, 60, 1));
//                subItemTv.setText(subItems.get(i * subColumeNum + j));
//                subItemTv.setTextSize(TypedValue.COMPLEX_UNIT_SP, 15);
//                subItemTv.setTextColor(context.getResources().getColor(android.R.color.holo_orange_dark));
//                row.addView(subItemTv, j);
//
//                //TODO 添加监听事件。
//            }
//            tbLayout.addView(row, i);
//        }
        return tbLayout;
    }

}