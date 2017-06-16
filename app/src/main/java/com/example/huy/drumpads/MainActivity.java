package com.example.huy.drumpads;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.example.huy.drumpads.touches.Touch;
import com.example.huy.drumpads.touches.TouchAction;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.toString();
    private List<ImageView> listKeys;
    private List<TouchInfo> touchInfoList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        listKeys = new ArrayList<>();
        listKeys.add((ImageView) findViewById(R.id.bt_1));
        listKeys.add((ImageView) findViewById(R.id.bt_2));
        listKeys.add((ImageView) findViewById(R.id.bt_3));
        listKeys.add((ImageView) findViewById(R.id.bt_4));
        listKeys.add((ImageView) findViewById(R.id.bt_5));
        listKeys.add((ImageView) findViewById(R.id.bt_6));
        listKeys.add((ImageView) findViewById(R.id.bt_7));
        listKeys.add((ImageView) findViewById(R.id.bt_8));
        listKeys.add((ImageView) findViewById(R.id.bt_9));
        listKeys.add((ImageView) findViewById(R.id.bt_10));
        listKeys.add((ImageView) findViewById(R.id.bt_11));
        listKeys.add((ImageView) findViewById(R.id.bt_12));
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        List<Touch> touches = Touch.processEvent(event);

        if (touches.size() == 0) return false;

        Touch firstTouch = touches.get(0);

        if (firstTouch.getAction() == TouchAction.DOWN) {
            ImageView pressedKey = findKeyByTouch(firstTouch);
            if (pressedKey != null && !checkPressedKey(pressedKey)) {
                touchInfoList.add(new TouchInfo(pressedKey, firstTouch));
            }
        }

        if (firstTouch.getAction() == TouchAction.UP) {
            ImageView releasedKey = findKeyByTouch(firstTouch);
            if (releasedKey != null) {
                Iterator<TouchInfo> touchInfoIterator = touchInfoList.iterator();
                while (touchInfoIterator.hasNext()) {
                    TouchInfo touchInfo = touchInfoIterator.next();
                    if (touchInfo.touch.getTouchId() == firstTouch.getTouchId()) {
                        touchInfoIterator.remove();
                    }
                }
            }
        } else if (firstTouch.getAction() == TouchAction.MOVE) {
            for (Touch touch : touches) {
                ImageView pressedKey = findKeyByTouch(touch);
                if (pressedKey != null) {

                    Iterator<TouchInfo> touchInfoIterator = touchInfoList.iterator();
                    while (touchInfoIterator.hasNext()) {
                        TouchInfo touchInfo = touchInfoIterator.next();
                        if (touchInfo.touch.equals(touch) && touchInfo.pressedKey != pressedKey) {
                            touchInfoIterator.remove();
                        }
                    }

                    if (!checkPressedKey(pressedKey)) {
                        touchInfoList.add(new TouchInfo(pressedKey, touch));
                    }
                }
            }
        }

        updateKeyImage();
        return super.onTouchEvent(event);
    }

    private void updateKeyImage() {
        for (ImageView key : listKeys) {
            if (checkPressedKey(key)) {
                //TODO:
                if (key.getTag().equals("1")) {
                    key.setImageResource(R.drawable.custom_bt_1);
                } else if (key.getTag().equals("2")) {
                    key.setImageResource(R.drawable.custom_bt_2);
                } else if (key.getTag().equals("3")) {
                    key.setImageResource(R.drawable.custom_bt_3);
                } else if (key.getTag().equals("4")) {
                    key.setImageResource(R.drawable.custom_bt_4);
                } else if (key.getTag().equals("5")) {
                    key.setImageResource(R.drawable.custom_bt_5);
                } else if (key.getTag().equals("6")) {
                    key.setImageResource(R.drawable.custom_bt_6);
                } else if (key.getTag().equals("7")) {
                    key.setImageResource(R.drawable.custom_bt_7);
                } else if (key.getTag().equals("8")) {
                    key.setImageResource(R.drawable.custom_bt_8);
                } else if (key.getTag().equals("9")) {
                    key.setImageResource(R.drawable.custom_bt_9);
                } else if (key.getTag().equals("10")) {
                    key.setImageResource(R.drawable.custom_bt_9);
                } else if (key.getTag().equals("11")) {
                    key.setImageResource(R.drawable.custom_bt_9);
                } else if (key.getTag().equals("12")) {
                    key.setImageResource(R.drawable.custom_bt_9);
                }
            } else key.setImageResource(R.drawable.custom_default);
        }
    }

    private boolean checkPressedKey(ImageView pressedKey) {
        for (TouchInfo touchInfo : touchInfoList) {
            if (touchInfo.getPressedKey() == pressedKey) {
                return true;
            }
        }
        return false;
    }

    private ImageView findKeyByTouch(Touch touch) {
        for (ImageView key : listKeys)
            if (touch.checkHit(key)) {
                return key;
            }
        return null;
    }

    class TouchInfo {
        public ImageView pressedKey;
        public Touch touch;

        public TouchInfo(ImageView pressedKey, Touch touch) {
            this.pressedKey = pressedKey;
            this.touch = touch;
        }

        public ImageView getPressedKey() {
            return pressedKey;
        }
    }
}
