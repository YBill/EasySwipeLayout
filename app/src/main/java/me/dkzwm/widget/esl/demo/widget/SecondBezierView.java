package me.dkzwm.widget.esl.demo.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * author : Bill
 * date : 2021/2/2
 * description :
 */
public class SecondBezierView extends View {

    private Paint mBgPaint;

    private Paint mPaint;
    private Path mPath = new Path();

    public SecondBezierView(Context context) {
        super(context);
        init();
    }

    public SecondBezierView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public SecondBezierView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        this.setBackgroundColor(Color.BLACK);

        mBgPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mBgPaint.setColor(Color.parseColor("#FFC0CB"));
        mBgPaint.setStyle(Paint.Style.FILL);

        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(Color.GREEN);
        mPaint.setStrokeWidth(10);
        mPaint.setStyle(Paint.Style.STROKE);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int width = getWidth();
        int height = getHeight();

        canvas.drawRect(0, 0, width, height, mBgPaint);

        int offset = 0;

        // 初始化 路径对象
        mPath.reset();
        // 移动至第一个控制点 A(ax,ay)
        mPath.moveTo(offset, height - offset);
        // 填充二阶贝塞尔曲线的另外两个控制点 B(bx,by) 和 C(cx,cy)，切记顺序不能变
        mPath.quadTo(width / 2.0f, offset, width - offset, height - offset);
        // 第二个二阶贝塞尔曲线，起点即为上一个贝塞尔曲线的终点，即A2为C，另外两个控制点 B2和C2按顺序
        mPath.quadTo(0, offset, offset, height - offset);
        // 将 贝塞尔曲线 绘制至画布
        canvas.drawPath(mPath, mPaint);

    }

}
