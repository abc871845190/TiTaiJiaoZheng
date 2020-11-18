package com.example.titaijiaozheng.Ui.ViewDIY;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;

import com.example.titaijiaozheng.R;

public class GradeProgressBar extends View {

    private String TAG = "GradeProgressBar";

    private int height;//长
    private int width;//宽
    private Paint mPaint;//画笔
    private RectF mRectF;//扇形绘制的矩形范围
    private int strokeWidth = 5;//环形线条宽度
    private int normalColor = Color.parseColor("#778899");//未走颜色
    private int progressColor = Color.parseColor("#dc143c");//已走颜色
    private Boolean textIsDisplayable = false;//文字是否显示
    private int textColor = Color.parseColor("#000000");//文字颜色
    private float textSize = 20;//文字大小
    private int progress = 0;//进度
    private String centerText = "0%";//中间填充的文字
    private Paint fontPaint = null;
    private Paint.Style progressStyle = Paint.Style.STROKE;//进度条形式  圆形

    public GradeProgressBar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.GradeProgressBar);
        textSize = typedArray.getDimension(R.styleable.GradeProgressBar_textSize, textSize);
        textColor = typedArray.getColor(R.styleable.GradeProgressBar_textColor, textColor);
        textIsDisplayable = typedArray.getBoolean(R.styleable.GradeProgressBar_textIsDisplayable, textIsDisplayable);
        centerText = typedArray.getString(R.styleable.GradeProgressBar_text) == null ? centerText : typedArray.getString(R.styleable.GradeProgressBar_text);
        strokeWidth = typedArray.getInteger(R.styleable.GradeProgressBar_stroke_width, strokeWidth);
        normalColor = typedArray.getColor(R.styleable.GradeProgressBar_normalColor, normalColor);
        progressColor = typedArray.getColor(R.styleable.GradeProgressBar_progressColor, progressColor);
        progress = typedArray.getInteger(R.styleable.GradeProgressBar_process, progress);
        progressStyle = typedArray.getInt(R.styleable.GradeProgressBar_processStyle, 0) == 0 ? Paint.Style.STROKE : Paint.Style.FILL;
        typedArray.recycle();
        initPaint();
    }

    /**
     * 初始化画笔
     */
    private void initPaint() {
        mPaint = new Paint();
        //设置画笔颜色
        mPaint.setColor(normalColor);
        //反锯齿
        mPaint.setAntiAlias(true);
        //设置画笔模式为描边
        mPaint.setStyle(progressStyle);
        //设置画笔宽度
        mPaint.setStrokeWidth(strokeWidth);

        //字体画笔
        fontPaint = new Paint();
        //设置字体大小
        fontPaint.setTextSize(textSize);
        fontPaint.setAntiAlias(true);
        //字体颜色
        fontPaint.setColor(textColor);
    }

    /**
     * 测量View大小
     *
     * @param widthMeasureSpec
     * @param heightMeasureSpec
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        height = MeasureSpec.getSize(heightMeasureSpec);
        width = MeasureSpec.getSize(widthMeasureSpec);

        if (height > width) {
            //高大于宽 圆处于矩形中心
            mRectF = new RectF(
                    strokeWidth,
                    (height / 2 - width / 2) + strokeWidth,
                    width - strokeWidth,
                    (height / 2 + width / 2) - strokeWidth);
        } else if (height < width) {
            //宽大于高
            mRectF = new RectF(
                    (width / 2 - height / 2) + strokeWidth,
                    strokeWidth,
                    (width / 2 + height / 2) - strokeWidth,
                    height - strokeWidth);
        } else {
            //宽等于高
            mRectF = new RectF(
                    strokeWidth,
                    strokeWidth,
                    width - strokeWidth,
                    height - strokeWidth);
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    Paint.FontMetrics fontMetrics = null;

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPaint.setColor(normalColor);
        if (progress < 360) {
            //270+progress为未走起点,360 - progress为扫过的角度
            //画未走圆环
            canvas.drawArc(
                    mRectF,
                    270 + progress,
                    360 - progress,
                    progressStyle == Paint.Style.FILL,
                    mPaint);
        }

        //画已走圆环
        mPaint.setColor(progressColor);
        canvas.drawArc(
                mRectF,
                270,
                progress,
                progressStyle == Paint.Style.FILL,
                mPaint);

        //画中间文字
        if (textIsDisplayable) {
            fontMetrics = fontPaint.getFontMetrics();
            float textWidth = fontPaint.measureText(centerText);
            float textHeight = fontPaint.ascent() + fontPaint.descent();
            canvas.drawText(
                    centerText,
                    width / 2 - textWidth / 2,
                    height / 2 - textHeight / 2,
                    fontPaint);
        }
    }

    public void update(int progress, String text) {
        this.progress = progress;
        this.centerText = text;
        postInvalidate();
    }
}
