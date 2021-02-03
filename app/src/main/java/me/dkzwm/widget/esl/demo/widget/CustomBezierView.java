package me.dkzwm.widget.esl.demo.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * author : Bill
 * date : 2021/2/2
 * description :
 */
public class CustomBezierView extends View {

    private Paint mBgPaint;

    private Paint mPaint;
    private Path mPath = new Path();

    public CustomBezierView(Context context) {
        super(context);
        init();
    }

    public CustomBezierView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CustomBezierView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        this.setClickable(true);
        this.setFocusable(true);
        this.setFocusableInTouchMode(true);

        this.setBackgroundColor(Color.BLACK);

        mBgPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mBgPaint.setColor(Color.parseColor("#F0E68C"));
        mBgPaint.setStyle(Paint.Style.FILL);

        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(Color.GREEN);
        mPaint.setStrokeWidth(10);
        mPaint.setStyle(Paint.Style.FILL);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        mPath.reset();

        int width = getWidth();
        int height = getHeight();

        canvas.drawRect(0, 0, width, height, mBgPaint);

        if (actionType < 2)
            return;

        int canvasWidth = width;
        int canvasHeight = height / 5;

        int size = (int) Math.min(x, canvasWidth / 2f);

        mPath.reset();
        mPath.moveTo(0, 0);
        mPath.cubicTo(0, canvasHeight / 5f, size, canvasHeight / 3f, size, canvasHeight / 2f);
        mPath.cubicTo(size, canvasHeight / 3f * 2, 0, canvasHeight / 5f * 4, 0, canvasHeight);
        if (y >= 0)
            mPath.offset(0, y - canvasHeight / 2f);
        canvas.drawPath(mPath, mPaint);

    }

    float x = -1, y = -1;
    private int actionType;

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        final int action = event.getActionMasked();
        switch (action) {
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                actionType = 0;
                getParent().requestDisallowInterceptTouchEvent(false); // 交给
                break;
            case MotionEvent.ACTION_DOWN:
                actionType = 1;
                x = event.getX();
                y = event.getY();
                getParent().requestDisallowInterceptTouchEvent(true); // 拦截
                break;
            case MotionEvent.ACTION_MOVE:
                actionType = 2;
                x = event.getX();
                y = event.getY();
                invalidate();
                break;
        }
        return super.dispatchTouchEvent(event);
    }
}
