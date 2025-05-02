package com.example.customprogressbar;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.*;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;

public class CircleProgressBar extends View {
    private Paint paintProgress, paintBackground, paintText;
    private int progress = 0;

    private int strokeWidth = 20;
    private int progressColor = Color.parseColor("#4285F4");
    private int backgroundColor = Color.parseColor("#E0E0E0");
    private int textColor = Color.parseColor("#212121");

    public CircleProgressBar(Context context) {
        super(context);
        init(null);
    }

    public CircleProgressBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    private void init(AttributeSet attrs) {
        paintProgress = new Paint(Paint.ANTI_ALIAS_FLAG);
        paintBackground = new Paint(Paint.ANTI_ALIAS_FLAG);
        paintText = new Paint(Paint.ANTI_ALIAS_FLAG);
        paintText.setTextAlign(Paint.Align.CENTER);
        if (attrs != null) {
            TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.CircleProgressBar);
            progressColor = a.getColor(R.styleable.CircleProgressBar_circleProgressColor, progressColor);
            backgroundColor = a.getColor(R.styleable.CircleProgressBar_circleProgressBackgroundColor, backgroundColor);
            textColor = a.getColor(R.styleable.CircleProgressBar_circleProgressTextColor, textColor);
            strokeWidth = a.getDimensionPixelSize(R.styleable.CircleProgressBar_circleProgressStrokeWidth, strokeWidth);
            a.recycle();
        }
        paintProgress.setStyle(Paint.Style.STROKE);
        paintProgress.setStrokeWidth(strokeWidth);
        paintProgress.setColor(progressColor);
        paintBackground.setStyle(Paint.Style.STROKE);
        paintBackground.setStrokeWidth(strokeWidth);
        paintBackground.setColor(backgroundColor);
        paintText.setColor(textColor);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        int size = Math.min(getWidth(), getHeight());
        float padding = strokeWidth;
        RectF rect = new RectF(padding, padding, size - padding, size - padding);
        canvas.drawArc(rect, -90, 360, false, paintBackground);
        canvas.drawArc(rect, -90, 360 * progress / 100f, false, paintProgress);
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
