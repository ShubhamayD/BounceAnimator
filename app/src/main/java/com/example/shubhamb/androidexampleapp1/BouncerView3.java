package com.example.shubhamb.androidexampleapp1;


import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AccelerateInterpolator;


public class BouncerView3 extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    static class MyView extends View {

        Bitmap mBitmap;
        Paint paint = new Paint();
        int mShapeX, mShapeY;
        int mShapeW, mShapeH;

        public MyView(Context context, AttributeSet attrs, int defStyle) {
            super(context, attrs, defStyle);
            setupShape();
        }

        public MyView(Context context, AttributeSet attrs) {
            super(context, attrs);
            setupShape();
        }

        public MyView(Context context) {
            super(context);
            setupShape();
        }

        public void setShapeX(int shapeX) {
            int minX = mShapeX;
            int maxX = mShapeX + mShapeW;
            mShapeX = shapeX;
            minX = Math.min(mShapeX, minX);
            maxX = Math.max(mShapeX + mShapeW, maxX);
            invalidate(minX, mShapeY, maxX, mShapeY + mShapeH);
        }

        public void setShapeY(int shapeY) {
            int minY = mShapeY;
            int maxY = mShapeY + mShapeH;
            mShapeY = shapeY;
            minY = Math.min(mShapeY, minY);
            maxY = Math.max(mShapeY + mShapeH, maxY);
            invalidate(mShapeX, minY, mShapeX + mShapeW, maxY);
        }

        private void setupShape() {
            mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher);
            mShapeW = mBitmap.getWidth();
            mShapeH = mBitmap.getHeight();
            setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startAnimation();
                }
            });
        }

        @Override
        protected void onSizeChanged(int w, int h, int oldw, int oldh) {
            mShapeX = (w - mBitmap.getWidth()) / 2;
            mShapeY = 0;
        }

        @Override
        protected void onDraw(Canvas canvas) {
            canvas.drawBitmap(mBitmap, mShapeX, mShapeY, paint);
        }

        void startAnimation() {
            ObjectAnimator anim = getObjectAnimator();
            anim.setRepeatCount(ValueAnimator.INFINITE);
            anim.setRepeatMode(ValueAnimator.REVERSE);
            anim.setInterpolator(new AccelerateInterpolator());
            anim.start();
        }

        ObjectAnimator getObjectAnimator() {
            return ObjectAnimator.ofInt(this, "shapeY", 0, (getHeight() - mShapeH));
        }

    }
}
