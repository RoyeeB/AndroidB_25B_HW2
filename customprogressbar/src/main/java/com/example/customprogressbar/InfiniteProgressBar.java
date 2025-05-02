package com.example.customprogressbar;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.*;
import android.util.AttributeSet;
import android.view.View;

public class InfiniteProgressBar extends View {
    private Paint paintProgress, paintBackground;
    private int progressColor = Color.parseColor("#FFA726");
    private int backgroundColor = Color.parseColor("#E0E0E0");
    private float offset = 0;
    private boolean isAnimating = false;

    public InfiniteProgressBar(Context context) {
        super(context);
        init(null);
    }

    public InfiniteProgressBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    private void init(AttributeSet attrs) {
        paintProgress = new Paint(Paint.ANTI_ALIAS_FLAG);
        paintBackground = new Paint(Paint.ANTI_ALIAS_FLAG);
        if (attrs != null) {
            TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.InfiniteProgressBar);
            progressColor = a.getColor(R.styleable.InfiniteProgressBar_infiniteProgressColor, progressColor);
            backgroundColor = a.getColor(R.styleable.InfiniteProgressBar_infiniteProgressBackgroundColor, backgroundColor);
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
        float barWidth = width / 4f;
        float startX = offset % (width + barWidth) - barWidth;
        float endX = startX + barWidth;
        RectF bgRect = new RectF(0, 0, width, height);
        canvas.drawRoundRect(bgRect, height / 2f, height / 2f, paintBackground);
        RectF progRect = new RectF(startX, 0, endX, height);
        canvas.drawRoundRect(progRect, height / 2f, height / 2f, paintProgress);

        if (isAnimating) {
            offset += 10;
            postInvalidateDelayed(16);
        }
    }

    public void start() {
        isAnimating = true;
        invalidate();
    }

    public void stop() {
        isAnimating = false;
        invalidate();
    }
}
