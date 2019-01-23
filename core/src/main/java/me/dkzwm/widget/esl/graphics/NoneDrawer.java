/*
 * MIT License
 *
 * Copyright (c) 2018 dkzwm
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package me.dkzwm.widget.esl.graphics;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.PointF;
import android.util.TypedValue;

public class NoneDrawer extends Drawer {
    private int mMaxSize;

    public NoneDrawer(Context context) {
        super(context);
        mMaxSize =
                (int)
                        TypedValue.applyDimension(
                                TypedValue.COMPLEX_UNIT_DIP,
                                26,
                                mContext.getResources().getDisplayMetrics());
    }

    @Override
    public int getMaxSize() {
        return mMaxSize;
    }

    @Override
    public void drawLeft(Canvas canvas, PointF downPoint, PointF movedPoint) {}

    @Override
    public void drawTop(Canvas canvas, PointF downPoint, PointF movedPoint) {}

    @Override
    public void drawRight(Canvas canvas, PointF downPoint, PointF movedPoint) {}

    @Override
    public void drawBottom(Canvas canvas, PointF downPoint, PointF movedPoint) {}

    @Override
    public boolean canTrigger(int edge, float pos) {
        return pos >= mMaxSize / 3f * 2;
    }
}