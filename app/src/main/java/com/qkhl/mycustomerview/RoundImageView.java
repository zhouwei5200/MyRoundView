package com.qkhl.mycustomerview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.widget.ImageView;
import android.graphics.Shader.TileMode;
public class RoundImageView extends ImageView {


    /**
     * 圆角大小的默认值12345
     */
    private static final int BODER_RADIUS_DEFAULT = 10;
    /**
     * 图片的类型，圆形or圆角
     */
    private int type;
    public static final int TYPE_CIRCLE = 0;
    public static final int TYPE_ROUND = 1;
    /**
     * 矩阵  主要用于 平面的 缩放 平移  旋转
     */
    private Matrix mMatrix;
    private Paint mBitmapPaint;
    /**
     * 圆角的半径 
     */
    private int mRadius;
    private int mBorderRadius;
    private int mWinth;
    private RectF rectF;


    public RoundImageView(Context context) {
        this(context, null);
    }

    public RoundImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
      /*  mMatrix = new Matrix();
        mBitmapPaint = new Paint();
        mBitmapPaint.setAntiAlias(true);

        TypedArray a = context.obtainStyledAttributes(attrs,
                R.styleable.RoundImageView);
        *//**
         //获取圆角的角度
        
        // 默认为10dp
        mBorderRadius = a.getDimensionPixelSize(
                R.styleable.RoundImageView_bordaoRadius, (int) TypedValue
                        .applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                                BODER_RADIUS_DEFAULT, getResources()
                                        .getDisplayMetrics()));
        *//**
         * 获取类型
         *//*
        type = a.getInt(R.styleable.RoundImageView_type, TYPE_CIRCLE);// 默认为Circle
       //回收
        a.recycle();*/
        mMatrix = new Matrix();
        mBitmapPaint = new Paint();
        mBitmapPaint.setAntiAlias(true);

        TypedArray a = context.obtainStyledAttributes(attrs,
                R.styleable.RoundImageView);

        mBorderRadius = a.getDimensionPixelSize(
                R.styleable.RoundImageView_bordaoRadius, (int) TypedValue
                        .applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                                BODER_RADIUS_DEFAULT, getResources()
                                        .getDisplayMetrics()));// 默认为10dp
        type = a.getInt(R.styleable.RoundImageView_type, TYPE_CIRCLE);// 默认为Circle

        a.recycle();
    }


    /** view的生命周期方法1
     * ************************
     */



    /**
     *当View中所有的子控件均被映射成xml后触发
     */
    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        Log.e("flag","onFinishInflate");
    }

    /**
     * 当view被附着到一个窗口时触发
     */
    @Override
    protected void onAttachedToWindow() {
        Log.e("flag","onAttachedToWindow");
        super.onAttachedToWindow();
    }
    /**
     * 当窗口中包含的可见的view发生变化时触发
     * @param visibility
     */
    @Override
    protected void onWindowVisibilityChanged(int visibility) {
        Log.e("flag","onWindowVisibilityChanged");
        super.onWindowVisibilityChanged(visibility);
    }

    /**
     * 测量控件的宽高    生命周期
     * @param widthMeasureSpec
     * @param heightMeasureSpec
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        Log.e("flag","onMeasure");
        /**
         * 如果我们想要的type是圆形，则我们需要让宽高一致，以较小的值为准、
         */
        if(type == TYPE_CIRCLE){
            mWinth = Math.min(getMeasuredWidth(),getMeasuredHeight());
            mRadius = mWinth /2;
            setMeasuredDimension(mWinth, mWinth);
        }
    }

    /**
     * 当大小改变的时候调用这个方法，最少调用一次
     * @param w
     * @param h
     * @param oldw
     * @param oldh
     */
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        Log.e("flag","onSizeChanged");
        super.onSizeChanged(w, h, oldw, oldh);
        if(type == TYPE_ROUND){
            rectF = new RectF(0,0,w,h);

        }
    }

    /**
     * 当view分配所有子元素的大小和位置时触发
     * @param changed
     * @param left
     * @param top
     * @param right
     * @param bottom
     */
    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        Log.e("flag","onLayout");
    }


    @Override
    protected void onDraw(Canvas canvas) {
        //  super.onDraw(canvas);  一定要注意，把这个方法去点
        //  Log.e("flag","onSizeChanged");
        Log.e("flag", "onDraw");
        if (getDrawable() == null) {
            return;
        }
        //  canvas.drawRect();
        setUpShader();

        if (type == TYPE_ROUND) {
            canvas.drawRoundRect(rectF, mBorderRadius, mBorderRadius,
                    mBitmapPaint);
        } else {
            canvas.drawCircle(mRadius, mRadius, mRadius, mBitmapPaint);
            // drawSomeThing(canvas);
        }
    }

    /**
     * 当view离开附着的窗口时触发
     */
    @Override
    protected void onDetachedFromWindow() {
        Log.e("flag","onDetachedFromWindow");
        super.onDetachedFromWindow();
    }


    /**
     * 当有按键按下时触发
     * @param keyCode
     * @param event
     * @return
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        Log.e("flag","onKeyDown");
        return super.onKeyDown(keyCode, event);
    }

    /**
     * 当有按键弹起时触发
     * @param keyCode
     * @param event
     * @return
     */
    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        Log.e("flag","onKeyUp");
        return super.onKeyUp(keyCode, event);
    }

    /**
     * 轨迹球事件
     * @param event
     * @return
     */
    @Override
    public boolean onTrackballEvent(MotionEvent event) {
        Log.e("flag","onTrackballEvent");
        return super.onTrackballEvent(event);
    }

    /**
     * 触屏事件
     * @param event
     * @return
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.e("flag","onTouchEvent");
        return super.onTouchEvent(event);
    }

    /**
     * 当View获取或失去焦点时触发
     * @chu'faparam gainFocus
     * @param direction
     * @param previouslyFocusedRect
     */
    @Override
    protected void onFocusChanged(boolean gainFocus, int direction, Rect previouslyFocusedRect) {
        Log.e("flag","onFocusChanged");
        super.onFocusChanged(gainFocus, direction, previouslyFocusedRect);
    }

    /**
     * 当窗口包含的View获取或者失去焦点时触发
     * @param hasWindowFocus
     */
    @Override
    public void onWindowFocusChanged(boolean hasWindowFocus) {
        Log.e("flag","onWindowFocusChanged");
        super.onWindowFocusChanged(hasWindowFocus);
    }













    /**和
     * 初始化BitmapShader
     */

    /**
     *
     /**
     * 初始化BitmapShader,利用BitmapShader进行渲染（这里只用到了缩放边缘部分），利用矩阵进行缩放
     */
    /**
     * BitmapShaser作用：
     *
     * BitmapShader（图像渲染）

     　　BitmapShader的作用是使用一张位图作为纹理来对某一区域进行填充。可以想象成在一块区域内铺瓷砖，只是这里的瓷砖是一张张位图而已。

     　　BitmapShader函数原型为：

     　　public BitmapShader (Bitmap bitmap, Shader.TileMode tileX, Shader.TileMode tileY);

     　　其中，参数bitmap表示用来作为纹理填充的位图；参数tileX表示在位图X方向上位图衔接形式；参数tileY表示在位图Y方向上位图衔接形式。

     　　Shader.TileMode有3种参数可供选择，分别为CLAMP、REPEAT和MIRROR。

     　　CLAMP的作用是如果渲染器超出原始边界范围，则会复制边缘颜色对超出范围的区域进行着色。REPEAT的作用是在横向和纵向上以平铺的形式重复渲染位图。MIRROR的作用是在横向和纵向上以镜像的方式重复渲染位图。

     *
     */

    private void setUpShader() {
        /**
         * 获取图片
         */
        Drawable drawable = getDrawable();
        if (drawable == null) {
            return;
        }
        /**
         * 将drawable转化为bitmap
         */
        Bitmap bmp = drawableToBitamp(drawable);
        // 将bmp作为着色器，就是在指定区域内绘制bmp
        /*
        渲染图像，使用图像为绘制图形着色
        */
        BitmapShader mBitmapShader = new BitmapShader(bmp, TileMode.CLAMP, TileMode.CLAMP);
        float scale = 1.0f;
        if (type == TYPE_CIRCLE) {
            // 拿到bitmap宽或高的小值
            int bSize = Math.min(bmp.getWidth(), bmp.getHeight());
            scale = mWinth * 1.0f / bSize;

        } else if (type == TYPE_ROUND) {
            Log.e("TAG",
                    "b'w = " + bmp.getWidth() + " , " + "b'h = "
                            + bmp.getHeight());
            if (!(bmp.getWidth() == getWidth() && bmp.getHeight() == getHeight())) {
                // 如果图片的宽或者高与view的宽高不匹配，计算出需要缩放的比例；缩放后的图片的宽高，一定要大于我们view的宽高；所以我们这里取大值；
                scale = Math.max(getWidth() * 1.0f / bmp.getWidth(),
                        getHeight() * 1.0f / bmp.getHeight());
            }

        }
        // shader的变换矩阵，我们这里主要用于放大或者缩小
        mMatrix.setScale(scale, scale);
        // 设置变换矩阵
        mBitmapShader.setLocalMatrix(mMatrix);
        // 设置shader
        mBitmapPaint.setShader(mBitmapShader);
    }



    /**
     * drawable转bitmap
     *
     * @param drawable 要转换的图片
     * @return 结果图片
     */
    private Bitmap drawableToBitamp(Drawable drawable) {
        if (drawable instanceof BitmapDrawable) {
            BitmapDrawable bd = (BitmapDrawable) drawable;
            return bd.getBitmap();
        }
        int w = drawable.getIntrinsicWidth();
        int h = drawable.getIntrinsicHeight();
        Bitmap bitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, w, h);
        drawable.draw(canvas);
        return bitmap;
    }

    private static final String STATE_INSTANCE = "state_instance";
    private static final String STATE_TYPE = "state_type";
    private static final String STATE_BORDER_RADIUS = "state_border_radius";

    /**
     * 异常退出的时候保存
     * @return
     */
    @Override
    protected Parcelable onSaveInstanceState() {
        Bundle bundle = new Bundle();
        bundle.putParcelable(STATE_INSTANCE, super.onSaveInstanceState());
        bundle.putInt(STATE_TYPE, type);
        bundle.putInt(STATE_BORDER_RADIUS, mBorderRadius);
        return bundle;
    }

    /**
     * 恢复的时候从这里面获取
     * @param state
     */
    @Override
    protected void onRestoreInstanceState(Parcelable state) {
        if (state instanceof Bundle) {
            Bundle bundle = (Bundle) state;
            super.onRestoreInstanceState(((Bundle) state)
                    .getParcelable(STATE_INSTANCE));
            this.type = bundle.getInt(STATE_TYPE);
            this.mBorderRadius = bundle.getInt(STATE_BORDER_RADIUS);
        } else {
            super.onRestoreInstanceState(state);
        }

    }

    /**
     * 用于代码中调用
     * @param borderRadius
     */
    public void setBorderRadius(int borderRadius) {
        int pxVal = dp2px(borderRadius);
        if (this.mBorderRadius != pxVal) {
            this.mBorderRadius = pxVal;
            invalidate();
        }
    }

    /**
     * 用于代码中调用
     * @param type
     */
    public void setType(int type) {
        if (this.type != type) {
            this.type = type;
            if (this.type != TYPE_ROUND && this.type != TYPE_CIRCLE) {
                this.type = TYPE_CIRCLE;
            }
            requestLayout();
        }

    }

    public int dp2px(int dpVal) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                dpVal, getResources().getDisplayMetrics());
    }


}
