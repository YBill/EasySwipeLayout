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
public class ThirdBezierView extends View {

    private Paint mBgPaint;

    private Paint mPaint;
    private Path mPath = new Path();

    public ThirdBezierView(Context context) {
        super(context);
        init();
    }

    public ThirdBezierView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ThirdBezierView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        this.setBackgroundColor(Color.BLACK);

        mBgPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mBgPaint.setColor(Color.parseColor("#F0E68C"));
        mBgPaint.setStyle(Paint.Style.FILL);

        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(Color.GREEN);
        mPaint.setStyle(Paint.Style.FILL);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int width = getWidth();
        int height = getHeight();

        canvas.drawRect(0, 0, width, height, mBgPaint);

        // 初始化 路径对象
        mPath.reset();
        // 移动至第一个控制点 A(ax,ay)
        mPath.moveTo(0, height / 2f);
        // 填充三阶贝塞尔曲线的另外三个控制点：B(bx,by) C(cx,cy) D(dx,dy) 切记顺序不能变
        mPath.cubicTo(0, 0, width / 2f, 0, width / 2f, height / 2f);
        // 第二个三阶贝塞尔曲线，起点即为上一个贝塞尔曲线的终点，即A2为D，另外两个控制点 B2、C2和D2按顺序
        mPath.cubicTo(width / 2f, height, width, height, width, height / 2f);
        // 将 贝塞尔曲线 绘制至画布
        canvas.drawPath(mPath, mPaint);

    }

}
