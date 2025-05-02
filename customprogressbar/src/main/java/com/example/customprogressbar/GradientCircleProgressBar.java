package com.example.customprogressbar;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.*;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;

public class GradientCircleProgressBar extends View {
    private Paint paintText, paintBackground, paintGradient;
    private int progress = 0;

    private int strokeWidth = 20;
    private int backgroundColor = Color.parseColor("#E0E0E0");

    public GradientCircleProgressBar(Context context) {
        super(context);
        init(null);
    }

    public GradientCircleProgressBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    private void init(AttributeSet attrs) {
        paintText = new Paint(Paint.ANTI_ALIAS_FLAG);
        paintText.setColor(Color.BLACK);
        paintText.setTextAlign(Paint.Align.CENTER);
        paintBackground = new Paint(Paint.ANTI_ALIAS_FLAG);
        paintBackground.setStyle(Paint.Style.STROKE);
        if (attrs != null) {
            TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.GradientCircleProgressBar);
            strokeWidth = a.getDimensionPixelSize(R.styleable.GradientCircleProgressBar_gradientProgressStrokeWidth, strokeWidth);
            backgroundColor = a.getColor(R.styleable.GradientCircleProgressBar_gradientProgressBackgroundColor, backgroundColor);
            a.recycle();
        }
        paintBackground.setColor(backgroundColor);
        paintBackground.setStrokeWidth(strokeWidth);
        paintGradient = new Paint(Paint.ANTI_ALIAS_FLAG);
        paintGradient.setStyle(Paint.Style.STROKE);
        paintGradient.setStrokeWidth(strokeWidth);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        int size = Math.min(getWidth(), getHeight());
        float padding = strokeWidth;
        RectF rect = new RectF(padding, padding, size - padding, size - padding);

        SweepGradient gradient = new SweepGradient(
                size / 2f, size / 2f,
                new int[]{Color.RED, Color.YELLOW, Color.GREEN, Color.BLUE, Color.RED}, null);
        paintGradient.setShader(gradient);
        canvas.drawArc(rect, -90, 360, false, paintBackground);
        canvas.drawArc(rect, -90, 360 * progress / 100f, false, paintGradient);
        paintText.setTextSize(size / 4f);
        float textY = size / 2f - ((paintText.descent() + paintText.ascent()) / 2);
        canvas.drawText(progress + "%", size / 2f, textY, paintText);
    }

    public void setProgress(int value) {
        this.progress = Math.max(0, Math.min(value, 100));
        invalidate();
    }

    public int getProgress() {
        return progress;
    }

    public void animateProgressTo(int target, int durationMs) {
        ValueAnimator animator = ValueAnimator.ofInt(progress, target);
        animator.setDuration(durationMs);
        animator.setInterpolator(new LinearInterpolator());
        animator.addUpdateListener(a -> setProgress((int) a.getAnimatedValue()));
        animator.start();
    }
}
