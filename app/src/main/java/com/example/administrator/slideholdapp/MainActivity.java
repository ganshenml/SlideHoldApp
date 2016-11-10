package com.example.administrator.slideholdapp;

import android.graphics.Color;
import android.graphics.Rect;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements ObservableScrollView.ScrollViewListener {
    private ObservableScrollView scrollView;
    private Button topBtn1, topBtn2, middleBtn1, middleBtn2;
    private View topPanel, middlePanel;
    private int topHeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        initListeners();

    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);

        Rect frame = new Rect();
        getWindow().getDecorView().getWindowVisibleDisplayFrame(frame);
        int statusBarHeight = frame.top;//状态栏高度

        int titleBarHeight = getWindow().findViewById(Window.ID_ANDROID_CONTENT).getTop();//标题栏高度
        topHeight = titleBarHeight + statusBarHeight;
    }


    private void initViews() {
        scrollView = (ObservableScrollView) findViewById(R.id.scrollView);
        topPanel = findViewById(R.id.topPanel);
        topBtn1 = (Button) topPanel.findViewById(R.id.button1);
        topBtn2 = (Button) topPanel.findViewById(R.id.button2);

        middlePanel = findViewById(R.id.middlePanel);
        middleBtn1 = (Button) middlePanel.findViewById(R.id.button1);
        middleBtn2 = (Button) middlePanel.findViewById(R.id.button2);

    }

    private void initListeners() {
        topBtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                middleBtn1.setBackgroundColor(Color.WHITE);
                topBtn1.setBackgroundColor(Color.WHITE);
            }
        });

        middleBtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                middleBtn1.setBackgroundColor(Color.BLUE);
                topBtn1.setBackgroundColor(Color.BLUE);
            }
        });

        scrollView.setScrollViewListener(this);


    }


    @Override
    public void onScrollChanged(ObservableScrollView scrollView, int x, int y, int oldx, int oldy) {
        int[] location = new int[2];
        middleBtn1.getLocationOnScreen(location);
        int locationY = location[1];
        Log.e("locationY", locationY + "   " + "topHeight的值是：" + topHeight);

        if (locationY <= topHeight && (topPanel.getVisibility() == View.GONE || topPanel.getVisibility() == View.INVISIBLE)) {
            topPanel.setVisibility(View.VISIBLE);
        }

        if (locationY > topHeight && topPanel.getVisibility() == View.VISIBLE) {
            topPanel.setVisibility(View.GONE);
        }

    }
}
