package test.jch.com.testslidegrad;

import android.animation.LayoutTransition;
import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.ScaleAnimation;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ToggleButton;


public class TestActivity extends ActionBarActivity {


    private ToggleButton showbtn;
    private TextView tv1;
    private LinearLayout contentll;

    private LayoutTransition mTransitioner;
    private int duration = 200;
    private boolean animIsStart = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        initialize();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_test, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    private void initialize() {

        showbtn = (ToggleButton) findViewById(R.id.show_btn);
        tv1 = (TextView) findViewById(R.id.tv1);
        contentll = (LinearLayout) findViewById(R.id.content_ll);
        addContentAnim();

        showbtn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked)

                    tv1.startAnimation(getTransIn());
                else
                    tv1.startAnimation(getTransOut());
            }
        });
    }


    private Animation getTransIn() {
        ScaleAnimation translateAnimation = new ScaleAnimation(1, 1, 0, 1, 0.5f, 0.5f);
        translateAnimation.setInterpolator(new AccelerateInterpolator());

        translateAnimation.setFillAfter(true);
        translateAnimation.setDuration(duration);
        translateAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        return translateAnimation;
    }

    private Animation getTransOut() {

        ScaleAnimation translateAnimation = new ScaleAnimation(1, 1, 1, 0, 0.5f, 0.5f);
        translateAnimation.setInterpolator(new DecelerateInterpolator());
        translateAnimation.setFillAfter(true);
        translateAnimation.setDuration(duration);
        translateAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        return translateAnimation;

    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public void addContentAnim() {
        mTransitioner = new LayoutTransition();
        mTransitioner.setDuration(duration);
        mTransitioner.addTransitionListener(new LayoutTransition.TransitionListener() {

            @Override
            public void startTransition(LayoutTransition transition,
                                        ViewGroup container, View view, int transitionType) {
                animIsStart = true;
            }

            @Override
            public void endTransition(LayoutTransition transition,
                                      ViewGroup container, View view, int transitionType) {
                animIsStart = false;
            }
        });

        contentll.setLayoutTransition(mTransitioner);
    }
}
