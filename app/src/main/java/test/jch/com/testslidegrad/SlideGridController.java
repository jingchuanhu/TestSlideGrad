package test.jch.com.testslidegrad;

import android.animation.LayoutTransition;
import android.annotation.TargetApi;
import android.content.Context;
import android.database.DataSetObserver;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import java.util.ArrayList;

/**
 * Created by ACER on 2014/11/6.
 */
public class SlideGridController extends DataSetObserver implements View.OnClickListener {

    private Context context;
    private SlideGridView slideGridView;
    private SlideGrideViewAdapter mAdapter;
    private int columeNum;
    /**
     * myInflater. *
     */
    private LayoutInflater mInflater;
    /**
     * 当前被点击的Item。
     */
    private int currentItem = -1;

    private int oldClickItem = currentItem;

    private ArrayList<FrameLayout> tagViews = new ArrayList<FrameLayout>();
    /* 存储所有的可点击item*/
    private ArrayList<View> itemViews = new ArrayList<View>();

    public SlideGridController(Context context, SlideGridView slideGridView, int columeNum) {
        this.context = context;
        this.columeNum = columeNum;
        this.slideGridView = slideGridView;
        addContentAnim();
    }


    @Override
    public void onChanged() {
        super.onChanged();
        onInvalidated();
        layoutItem();
    }


    @Override
    public void onInvalidated() {
        super.onInvalidated();

        if (mAdapter == null) {
            mAdapter = slideGridView.getAdapter();
        }

    }

    /**
     * 将要显示的项添加到SlideGridView中。
     */
    private void layoutItem() {

        int itemNum = mAdapter.getCount();
        int lineNum = (int) Math.ceil((double) itemNum / (double) columeNum);

        for (int i = 0; i < lineNum; i++) {
            LinearLayout row = getRow();
            FrameLayout rowTagView = getRowTagView();
            for (int t = 0; t < columeNum; t++) {

                View view = mAdapter.getView(i * columeNum + t, null, null);
                FrameLayout itemView = getItemView();
                itemView.addView(view);
                itemViews.add(view);        //存储每一项，用于找点击View
                view.setTag(rowTagView);
                view.setOnClickListener(this);


                row.addView(itemView);
            }


            slideGridView.addView(row);
            slideGridView.addView(rowTagView);
            tagViews.add(rowTagView);
        }
    }

    /**
     * 每一行。
     *
     * @return
     */
    private LinearLayout getRow() {

        LinearLayout linearLayout = new LinearLayout(context);
        linearLayout.setOrientation(LinearLayout.HORIZONTAL);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        linearLayout.setWeightSum(columeNum);
        linearLayout.setLayoutParams(params);
        return linearLayout;
    }

    /**
     * 每一
     *
     * @return
     */
    private FrameLayout getItemView() {

        FrameLayout itemView = new FrameLayout(context);
        itemView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, 1));
        return itemView;
    }

    /**
     *
     *
     */
    private FrameLayout getRowTagView() {

        FrameLayout frameLayout = new FrameLayout(context);
        frameLayout.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        frameLayout.setVisibility(View.GONE);

        return frameLayout;
    }

    @Override
    public void onClick(View v) {

        if (itemViews.contains(v)) {
            int clickPosition = itemViews.indexOf(v);
            controlTagView(clickPosition, (FrameLayout) v.getTag());
        }
    }

    /**
     * 通过tagView控制subView的隐藏和显示。
     *
     * @param index
     * @param tagView
     */
    private void controlTagView(int index, FrameLayout tagView) {

        ArrayList<String> subItems = mAdapter.getDatas().get(index).getItemLists();
        for (FrameLayout view : tagViews) {
            view.removeAllViews();
            view.setVisibility(View.GONE);
        }
        if (oldClickItem != index) {        //当当前的item已经被点击。隐藏当前的item。
//            LinearLayout tagLine = new LinearLayout(context);
//            tagLine.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 200));
//            tagLine.setBackgroundColor(context.getResources().getColor(android.R.color.holo_blue_bright));

            tagView.addView(mAdapter.getSubview(index));
//            tagView.addView(tagLine);
            tagView.setVisibility(View.VISIBLE);
            oldClickItem = index;

        } else {
            oldClickItem = -1;
        }

    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public void addContentAnim() {
        LayoutTransition transitioner = new LayoutTransition();
        transitioner.setDuration(100);
        transitioner.addTransitionListener(new LayoutTransition.TransitionListener() {

            @Override
            public void startTransition(LayoutTransition transition,
                                        ViewGroup container, View view, int transitionType) {
//                animIsStart = true;
            }

            @Override
            public void endTransition(LayoutTransition transition,
                                      ViewGroup container, View view, int transitionType) {
//                animIsStart = false;
            }
        });

        slideGridView.setLayoutTransition(transitioner);
    }


}
