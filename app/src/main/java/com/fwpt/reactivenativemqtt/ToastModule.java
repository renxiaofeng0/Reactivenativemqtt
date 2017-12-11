package com.fwpt.reactivenativemqtt;

import android.widget.Toast;

import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.uimanager.IllegalViewOperationException;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by xuym on 2017-11-24.
 */

public class ToastModule extends ReactContextBaseJavaModule {

    private static final String DURATION_SHORT_KEY = "SHORT";
    private static final String DURATION_LONG_KEY = "LONG";

    public ToastModule(ReactApplicationContext reactContext) {
        super(reactContext);
    }

    @Override
    public String getName() {
        return "ToastExample";
    }

    @Override
    public Map<String, Object> getConstants() {
        final Map<String, Object> constants = new HashMap<>();
        constants.put(DURATION_SHORT_KEY, Toast.LENGTH_SHORT);
        constants.put(DURATION_LONG_KEY, Toast.LENGTH_LONG);
        return constants;
    }

    @ReactMethod
    public void show(String message, int duration) {
        Toast.makeText(getReactApplicationContext(), "FWPT:"+message, duration).show();
    }

    /**
     * 执行吐司(含回调函数)
     * @param tag
     * @param errorCallback
     * @param successCallback
     */
    @ReactMethod
    public void measureLayout(
            int tag,
            Callback errorCallback,
            Callback successCallback) {
        try {
            if(tag==1) {
                Toast.makeText(getReactApplicationContext(), "回调函数测试", Toast.LENGTH_SHORT).show();
//              float relativeX = PixelUtil.toDIPFromPixel(mMeasureBuffer[0]);
//              float relativeY = PixelUtil.toDIPFromPixel(mMeasureBuffer[1]);
//              float width = PixelUtil.toDIPFromPixel(mMeasureBuffer[2]);
//              float height = PixelUtil.toDIPFromPixel(mMeasureBuffer[3]);
                successCallback.invoke("你传递了一个数字1");
            }else {
                errorCallback.invoke("你传递了一个非1的数字");
            }
        } catch (IllegalViewOperationException e) {
            errorCallback.invoke(e.getMessage());
        }
    }
}
