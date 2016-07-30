package com.example.q.quickindextest;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by YQ on 2016/7/30.
 */
public class MyToast {
    private static Toast toast;
    public static void showToast(Context context,String content){
        if (toast==null){
            toast=Toast.makeText(context,content,Toast.LENGTH_SHORT);
        }
        toast.setText(content);
        toast.show();
    }
}
