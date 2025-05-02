package com.example.androidb_25b_hw2;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.example.customprogressbar.*;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        CircleProgressBar circleProgress = findViewById(R.id.circle_progress);
        circleProgress.animateProgressTo(100, 7000);

        HorizontalProgressBar horizontalProgress = findViewById(R.id.horizontal_progress);
        horizontalProgress.animateProgressTo(100, 12500);

        InfiniteProgressBar infiniteProgress = findViewById(R.id.infinite_progress);
        infiniteProgress.start();

        SegmentedProgressBar segmentedProgress = findViewById(R.id.segmented_progress);
        segmentedProgress.setProgress(100);

        GradientCircleProgressBar gradientProgress = findViewById(R.id.gradient_circle);
        gradientProgress.animateProgressTo(100, 8500);
    }
}
