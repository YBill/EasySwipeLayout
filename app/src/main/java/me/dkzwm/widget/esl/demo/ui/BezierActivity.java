package me.dkzwm.widget.esl.demo.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import me.dkzwm.widget.esl.EasySwipeLayout;
import me.dkzwm.widget.esl.EasySwipeManager;
import me.dkzwm.widget.esl.IgnoreMakeEasy;
import me.dkzwm.widget.esl.OnSwipeListener;
import me.dkzwm.widget.esl.config.Constants;
import me.dkzwm.widget.esl.demo.R;
import me.dkzwm.widget.esl.demo.graphics.CustomDrawer2;

public class BezierActivity extends AppCompatActivity implements IgnoreMakeEasy {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bezier);
        EasySwipeLayout layout = EasySwipeManager.attach(this);
        if (layout != null) {
            layout.setDirection(Constants.DIRECTION_HORIZONTAL);
            layout.setDrawer(new CustomDrawer2(this));
            layout.setSwipeListener(
                    new OnSwipeListener() {
                        @Override
                        public void onSwipe(int side) {
                            Toast.makeText(BezierActivity.this, "back", Toast.LENGTH_SHORT).show();
                        }
                    });
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EasySwipeManager.detach(this);
    }
}
