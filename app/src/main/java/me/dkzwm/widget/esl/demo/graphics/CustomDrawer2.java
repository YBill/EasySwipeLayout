package me.dkzwm.widget.esl.demo.graphics;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.support.annotation.ColorInt;
import android.util.TypedValue;

import me.dkzwm.widget.esl.graphics.Drawer;

public class CustomDrawer2 extends Drawer {
    private Paint mBackgroundPaint;
    private Path mBackgroundPath = new Path();
    private float mBackgroundFixedSize;
    private int mBackgroundMaxDynamicSize;

    private Paint mArrowPaint;
    private Path mArrowPath = new Path();
    private int mArrowHeight;
    private int mArrowWidth;
    @ColorInt
    private final int mBackgroundColor = Color.BLACK;
    @ColorInt
    private final int mArrowColor = Color.WHITE;

    public CustomDrawer2(Context context) {
        super(context);

        mBackgroundPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mBackgroundPaint.setColor(mBackgroundColor);
        mBackgroundPaint.setStrokeWidth(1);
        mBackgroundPaint.setStyle(Paint.Style.FILL);
        mBackgroundPaint.setAlpha((int) (0.74f * 255));

        float arrowStrokeWidth =
                (int)
                        TypedValue.applyDimension(
                                TypedValue.COMPLEX_UNIT_DIP,
                                2,
                                mContext.getResources().getDisplayMetrics());
        mArrowPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mArrowPaint.setStyle(Paint.Style.STROKE);
        mArrowPaint.setColor(mArrowColor);
        mArrowPaint.setStrokeWidth(arrowStrokeWidth);
        mArrowPaint.setStrokeJoin(Paint.Join.ROUND);
        mArrowHeight =
                (int)
                        TypedValue.applyDimension(
                                TypedValue.COMPLEX_UNIT_DIP,
                                12,
                                mContext.getResources().getDisplayMetrics());
        mArrowWidth =
                (int)
                        TypedValue.applyDimension(
                                TypedValue.COMPLEX_UNIT_DIP,
                                5f,
                                mContext.getResources().getDisplayMetrics());

        mBackgroundFixedSize = context.getResources().getDisplayMetrics().heightPixels / 3.5f;
        mBackgroundMaxDynamicSize =
                (int)
                        TypedValue.applyDimension(
                                TypedValue.COMPLEX_UNIT_DIP,
                                26,
                                mContext.getResources().getDisplayMetrics());
    }

    @Override
    public int getMaxSize() {
        return mBackgroundMaxDynamicSize;
    }

    @Override
    public void drawLeft(Canvas canvas, PointF downPoint, PointF movedPoint) {
        mBackgroundPath.reset();
        int size = (int) Math.min(movedPoint.x, mBackgroundMaxDynamicSize);
        mBackgroundPath.moveTo(0, 0);
        mBackgroundPath.cubicTo(
                0,
                mBackgroundFixedSize / 5,
                size,
                mBackgroundFixedSize / 3,
                size,
                mBackgroundFixedSize / 2);
        mBackgroundPath.cubicTo(
                size,
                mBackgroundFixedSize / 3 * 2,
                0,
                mBackgroundFixedSize / 5 * 4,
                0,
                mBackgroundFixedSize);
        mBackgroundPath.offset(0, downPoint.y - mBackgroundFixedSize / 2f);
        canvas.drawPath(mBackgroundPath, mBackgroundPaint);
        if (size >= mBackgroundMaxDynamicSize / 2.5f) {
            float arrowScale = (float) size / mBackgroundMaxDynamicSize;
            float arrowAngle = arrowScale < 0.8f ? 0 : (arrowScale - 0.8f) * 2;
            mArrowPath.reset();
            mArrowPath.moveTo(
                    size / 2f + (mArrowWidth * arrowAngle),
                    downPoint.y - (arrowScale * mArrowHeight / 2));
            mArrowPath.lineTo(size / 2f - (mArrowWidth * arrowAngle), downPoint.y);
            mArrowPath.lineTo(
                    size / 2f + (mArrowWidth * arrowAngle),
                    downPoint.y + (arrowScale * mArrowHeight / 2));
            canvas.drawPath(mArrowPath, mArrowPaint);
        }
    }

    @Override
    public void drawTop(Canvas canvas, PointF downPoint, PointF movedPoint) {

    }

    @Override
    public void drawRight(Canvas canvas, PointF downPoint, PointF movedPoint) {
        mBackgroundPath.reset();
        int size = (int) Math.min(mWidth - movedPoint.x, mBackgroundMaxDynamicSize);
        mBackgroundPath.moveTo(0, 0);
        mBackgroundPath.cubicTo(
                0,
                mBackgroundFixedSize / 5,
                -size,
                mBackgroundFixedSize / 3,
                -size,
                mBackgroundFixedSize / 2);
        mBackgroundPath.cubicTo(
                -size,
                mBackgroundFixedSize / 3 * 2,
                0,
                mBackgroundFixedSize / 5 * 4,
                0,
                mBackgroundFixedSize);
        mBackgroundPath.offset(mWidth, movedPoint.y - mBackgroundFixedSize / 2f);
        canvas.drawPath(mBackgroundPath, mBackgroundPaint);
        if (size >= mBackgroundMaxDynamicSize / 2.5f) {
            final int x = mWidth - size;
            float arrowScale = (float) size / mBackgroundMaxDynamicSize;
            float arrowAngle = arrowScale < 0.8f ? 0 : (arrowScale - 0.8f) * 2;
            mArrowPath.reset();
            mArrowPath.moveTo(
                    x + size / 2f - (mArrowWidth * arrowAngle),
                    movedPoint.y - (arrowScale * mArrowHeight / 2));
            mArrowPath.lineTo(x + size / 2f + (mArrowWidth * arrowAngle), movedPoint.y);
            mArrowPath.lineTo(
                    x + size / 2f - (mArrowWidth * arrowAngle),
                    movedPoint.y + (arrowScale * mArrowHeight / 2));
            canvas.drawPath(mArrowPath, mArrowPaint);
        }
    }

    @Override
    public void drawBottom(Canvas canvas, PointF downPoint, PointF movedPoint) {

    }

    @Override
    public boolean canTrigger(int edge, float x) {
        return x >= getMaxSize() / 3f * 2;
    }
}
