# 📦 CustomProgressBar Library for Android

A fully customizable and reusable ProgressBar library for Android, written in Java.  
Supports multiple styles with smooth animations, color configuration, and percentage text — all in one place.

---

## 🚀 Features

✅ Easy to integrate  
✅ Written entirely in Java  
✅ Customizable colors, stroke width, text and more  
✅ Works with any Android View-based app  
✅ Includes a full working demo layout

---

## 🎨 Supported ProgressBar Styles

| Style                     | Description                          |
|--------------------------|--------------------------------------|
| `CircleProgressBar`      | Circular progress with centered %     |
| `HorizontalProgressBar`  | Horizontal bar with text              |
| `InfiniteProgressBar`    | Endless loading animation             |
| `SegmentedProgressBar`   | Progress split into segments          |
| `GradientCircleProgressBar` | Circle with animated gradient ring |

---

## 🛠 Installation

1. Copy the library files (`java` classes and `attrs.xml`) into your project  
2. Add views to your layout using XML (see below)  
3. Control progress via Java as usual

---

## 🧩 Example Usage (XML)

<com.example.customprogressbar.CircleProgressBar
    android:id="@+id/circle_progress"
    android:layout_width="200dp"
    android:layout_height="200dp"
    app:circleProgressColor="@android:color/holo_blue_dark"
    app:circleProgressTextColor="@android:color/white"
    app:circleProgressBackgroundColor="@android:color/darker_gray"
    app:circleProgressStrokeWidth="16dp" />

---

## 💻 Example Usage (Java)

CircleProgressBar circle = findViewById(R.id.circle_progress);
circle.animateProgressTo(75, 2000);

HorizontalProgressBar horizontal = findViewById(R.id.horizontal_progress);
horizontal.animateProgressTo(60, 1500);

InfiniteProgressBar infinite = findViewById(R.id.infinite_progress);
infinite.start();

---

| View Class                  | XML Attributes                                                                                                 |
| --------------------------- | -------------------------------------------------------------------------------------------------------------- |
| `CircleProgressBar`         | `circleProgressColor`, `circleProgressTextColor`, `circleProgressBackgroundColor`, `circleProgressStrokeWidth` |
| `HorizontalProgressBar`     | `horizontalProgressColor`, `horizontalProgressTextColor`, `horizontalProgressBackgroundColor`                  |
| `InfiniteProgressBar`       | `infiniteProgressColor`, `infiniteProgressBackgroundColor`                                                     |
| `SegmentedProgressBar`      | `segmentedProgressColor`, `segmentedProgressBackgroundColor`, `segmentCount`                                   |
| `GradientCircleProgressBar` | `gradientProgressStrokeWidth`, `gradientProgressBackgroundColor`                                               |

---

## 📱 Demo
https://drive.google.com/file/d/1HXq8d431fTMdRC-5Y7iTo1lRsK0LkNYk/view?usp=drive_link
![image](https://github.com/user-attachments/assets/356e1f5d-2896-491d-8cb7-d6703a5a7a62)

