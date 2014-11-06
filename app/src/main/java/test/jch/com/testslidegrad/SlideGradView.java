package test.jch.com.testslidegrad;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;

/**
 * Created by jch on 2014/11/6.
 */
public class SlideGradView extends LinearLayout {
    //每一行的列数
    private int columeNum = 3;

    private BaseAdapter mAdapter = null;

    private SlideGridController controller;

    // @formatter:off
    private static final int[] ATTRS = new int[]{android.R.attr.textSize, android.R.attr.textColor};
    // @formatter:on

    public SlideGradView(Context context, AttributeSet attrs) {
        super(context, attrs);

        TypedArray a = context.obtainStyledAttributes(attrs, ATTRS);

        columeNum = a.getInt(R.styleable.SlideGradView_colum_num, columeNum);

        a.recycle();

        init(context);

    }

    public SlideGradView(Context context) {
        super(context);
    }

    public void init(Context context) {


        controller = new SlideGridController(context, this, columeNum);
        setOrientation(LinearLayout.VERTICAL);      //上下排布

    }

    public BaseAdapter getAdapter() {
        return mAdapter;
    }

    public void setAdapter(BaseAdapter mAdapter) {
        this.mAdapter = mAdapter;
        if (mAdapter != null) {
            mAdapter.registerDataSetObserver(controller);

        }

    }


}
