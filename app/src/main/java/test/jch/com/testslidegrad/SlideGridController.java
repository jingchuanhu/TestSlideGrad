package test.jch.com.testslidegrad;

import android.content.Context;
import android.database.DataSetObserver;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

/**
 * Created by ACER on 2014/11/6.
 */
public class SlideGridController extends DataSetObserver {

    private Context context;
    private SlideGradView slideGradView;
    private BaseAdapter mAdapter;
    private int columeNum;

    public SlideGridController(Context context, SlideGradView slideGradView, int columeNum) {
        this.context = context;
        this.columeNum = columeNum;
        this.slideGradView = slideGradView;
    }


    @Override
    public void onChanged() {
        super.onChanged();

    }


    @Override
    public void onInvalidated() {
        super.onInvalidated();

        if (mAdapter == null) {
            mAdapter = slideGradView.getAdapter();
        }

    }

    private void layoutItem() {

        int itemNum = mAdapter.getCount();
        for (int i = 0; i < itemNum; i++) {

            for (int t = 0; t < columeNum; t++) {

            }
        }
    }

    /**
     * @return
     */
    private LinearLayout getRow() {

        LinearLayout linearLayout = new LinearLayout(context);
        linearLayout.setOrientation(LinearLayout.HORIZONTAL);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        linearLayout.setLayoutParams(params);
        return linearLayout;
    }

    /**
     *
     *
     */
    private FrameLayout getRowTag() {

        FrameLayout frameLayout = new FrameLayout(context);
        frameLayout.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        return frameLayout;
    }

}
