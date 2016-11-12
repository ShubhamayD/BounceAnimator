package com.example.shubhamb.androidexampleapp1;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
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

        private void setupShape() {
            mBitmap = BitmapFactory.decodeResource(getResources(),
                    R.drawable.ic_launcher);
            mShapeW = mBitmap.getWidth();
            mShapeH = mBitmap.getHeight();
            setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startAnimation();
                }
            });
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
            ValueAnimator anim = getValueAnimator();
            anim.start();
        }

        ValueAnimator getValueAnimator() {
            ValueAnimator anim = ValueAnimator.ofFloat(0, 1);
            anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    setShapeY((int) (animation.getAnimatedFraction() *
                            (getHeight() - mShapeH)));
                }
            });
            return anim;
        }

    }
}
