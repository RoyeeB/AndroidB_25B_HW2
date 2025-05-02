package com.example.customprogressbar;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.*;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;

public class HorizontalProgressBar extends View {
    private Paint paintProgress, paintBackground, paintText;
    private int progress = 0;
    private int progressColor = Color.parseColor("#4CAF50");
    private int backgroundColor = Color.parseColor("#E0E0E0");
    private int textColor = Color.WHITE;

    public HorizontalProgressBar(Context context) {
        super(context);
        init(null);
    }

    public HorizontalProgressBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    private void init(AttributeSet attrs) {
        paintProgress = new Paint(Paint.ANTI_ALIAS_FLAG);
        paintBackground = new Paint(Paint.ANTI_ALIAS_FLAG);
        paintText = new Paint(Paint.ANTI_ALIAS_FLAG);
        paintText.setTextAlign(Paint.Align.CENTER);

        if (attrs != null) {
            TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.HorizontalProgressBar);
            progressColor = a.getColor(R.styleable.HorizontalProgressBar_horizontalProgressColor, progressColor);
            backgroundColor = a.getColor(R.styleable.HorizontalProgressBar_horizontalProgressBackgroundColor, backgroundColor);
            textColor = a.getColor(R.styleable.HorizontalProgressBar_horizontalProgressTextColor, textColor);
            a.recycle();
        }
        paintProgress.setStyle(Paint.Style.FILL);
        paintProgress.setColor(progressColor);
        paintBackground.setStyle(Paint.Style.FILL);
        paintBackground.setColor(backgroundColor);
        paintText.setColor(textColor);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        int width = getWidth(), height = getHeight();
        float progressWidth = width * progress / 100f;

        RectF bgRect = new RectF(0, 0, width, height);
        canvas.drawRoundRect(bgRect, height / 2f, height / 2f, paintBackground);

        RectF progRect = new RectF(0, 0, progressWidth, height);
        canvas.drawRoundRect(progRect, height / 2f, height / 2f, paintProgress);

        paintText.setTextSize(height / 2f);
        float textY = height / 2 - ((paintText.descent() + paintText.ascent()) / 2);
        canvas.drawText(progress + "%", width / 2f, textY, paintText);
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
