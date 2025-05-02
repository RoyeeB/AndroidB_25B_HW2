package com.example.customprogressbar;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.*;
import android.util.AttributeSet;
import android.view.View;

public class SegmentedProgressBar extends View {
    private Paint paintProgress, paintBackground;
    private int progress = 0;

    private int progressColor = Color.parseColor("#9C27B0");
    private int backgroundColor = Color.parseColor("#E0E0E0");
    private int segmentCount = 5;

    public SegmentedProgressBar(Context context) {
        super(context);
        init(null);
    }

    public SegmentedProgressBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    private void init(AttributeSet attrs) {
        paintProgress = new Paint(Paint.ANTI_ALIAS_FLAG);
        paintBackground = new Paint(Paint.ANTI_ALIAS_FLAG);
        if (attrs != null) {
            TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.SegmentedProgressBar);
            progressColor = a.getColor(R.styleable.SegmentedProgressBar_segmentedProgressColor, progressColor);
            backgroundColor = a.getColor(R.styleable.SegmentedProgressBar_segmentedProgressBackgroundColor, backgroundColor);
            segmentCount = a.getInt(R.styleable.SegmentedProgressBar_segmentCount, segmentCount);
            a.recycle();
        }
        paintProgress.setStyle(Paint.Style.FILL);
        paintProgress.setColor(progressColor);
        paintBackground.setStyle(Paint.Style.FILL);
        paintBackground.setColor(backgroundColor);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        int width = getWidth(), height = getHeight();
        int space = 8;
        int totalWidth = width - (segmentCount - 1) * space;
        int segmentWidth = totalWidth / segmentCount;

        for (int i = 0; i < segmentCount; i++) {
            int left = i * (segmentWidth + space);
            int right = left + segmentWidth;
            RectF rect = new RectF(left, 0, right, height);
            if (i < (progress * segmentCount / 100f)) {
                canvas.drawRoundRect(rect, height / 2f, height / 2f, paintProgress);
            } else {
                canvas.drawRoundRect(rect, height / 2f, height / 2f, paintBackground);
            }
        }
    }

    public void setProgress(int value) {
        this.progress = Math.max(0, Math.min(value, 100));
        invalidate();
    }

    public int getProgress() {
        return progress;
    }

    public void setSegmentCount(int count) {
        this.segmentCount = Math.max(1, count);
        invalidate();
    }

    public int getSegmentCount() {
        return segmentCount;
    }
}
