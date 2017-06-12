package com.example.huy.drumpads;

import android.location.Location;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private List<ImageView> listButtons;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        listButtons = new ArrayList<>();
        listButtons.add((ImageView) findViewById(R.id.bt_1));
        listButtons.add((ImageView) findViewById(R.id.bt_2));
        listButtons.add((ImageView) findViewById(R.id.bt_3));
        listButtons.add((ImageView) findViewById(R.id.bt_4));
        listButtons.add((ImageView) findViewById(R.id.bt_5));
        listButtons.add((ImageView) findViewById(R.id.bt_6));
        listButtons.add((ImageView) findViewById(R.id.bt_7));
        listButtons.add((ImageView) findViewById(R.id.bt_8));
        listButtons.add((ImageView) findViewById(R.id.bt_9));
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        for (ImageView imageView : listButtons) {
            if (isInside(event.getX(), event.getY(), imageView)) {
                    imageView.setBackgroundResource(R.drawable.custom_bt_1);
                 if (imageView.getTag().equals("2")) {
                    imageView.setBackgroundResource(R.drawable.custom_bt_2);
                } else if (imageView.getTag().equals("3")) {
                    imageView.setBackgroundResource(R.drawable.custom_bt_3);
                } else if (imageView.getTag().equals("4")) {
                    imageView.setBackgroundResource(R.drawable.custom_bt_4);
                } else if (imageView.getTag().equals("5")) {
                    imageView.setBackgroundResource(R.drawable.custom_bt_5);
                } else if (imageView.getTag().equals("6")) {
                    imageView.setBackgroundResource(R.drawable.custom_bt_6);
                } else if (imageView.getTag().equals("7")) {
                    imageView.setBackgroundResource(R.drawable.custom_bt_7);
                } else if (imageView.getTag().equals("8")) {
                    imageView.setBackgroundResource(R.drawable.custom_bt_8);
                } else if (imageView.getTag().equals("9")) {
                    imageView.setBackgroundResource(R.drawable.custom_bt_9);
                }
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    imageView.setBackgroundResource(R.drawable.custom_default);
                }
            }
        }
        for (ImageView imageView : listButtons) {
            if (!isInside(event.getX(), event.getY(), imageView)) {
                imageView.setBackgroundResource(R.drawable.custom_default);
            }
        }
        return super.onTouchEvent(event);
    }

    public boolean isInside(float x, float y, View v) {
        int[] location = new int[2];
        v.getLocationOnScreen(location);

        int left = location[0];
        int top = location[1];
        int right = left + v.getWidth();
        int bot = top + v.getHeight();
        return x > left && x < right && y > top && y < bot;
    }
}
