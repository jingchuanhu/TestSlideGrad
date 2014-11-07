package test.jch.com.testslidegrad;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.LinearLayout;

/**
 * Created by jch on 2014/11/6.
 */
public class SlideGridView extends LinearLayout {
    //每一行的列数
    private int columeNum = 3;

    private SlideGrideViewAdapter mAdapter = null;

    private SlideGridController controller;

    // @formatter:off
    private static final int[] ATTRS = new int[]{android.R.attr.textSize, android.R.attr.textColor};
    // @formatter:on

    public SlideGridView(Context context, AttributeSet attrs) {
        super(context, attrs);

        TypedArray a = context.obtainStyledAttributes(attrs, ATTRS);

        columeNum = a.getInt(R.styleable.SlideGridView_colum_num, columeNum);

        a.recycle();

        init(context);

    }

    public SlideGridView(Context context) {
        super(context);
    }

    public void init(Context context) {

        controller = new SlideGridController(context, this, columeNum);
        setOrientation(LinearLayout.VERTICAL);      //上下排布

    }

    public SlideGrideViewAdapter getAdapter() {
        return mAdapter;
    }

    public void setAdapter(SlideGrideViewAdapter mAdapter) {
        this.mAdapter = mAdapter;
        if (mAdapter != null) {
            mAdapter.registerDataSetObserver(controller);

        }

    }


}
