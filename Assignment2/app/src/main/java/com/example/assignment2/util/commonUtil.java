package com.example.assignment2.util;

import android.view.View;

import com.example.assignment2.R;
import com.google.android.material.snackbar.Snackbar;

public class commonUtil {

    public static void showSnackbar(View view,String message)
    {
        Snackbar.make( view, message,Snackbar.LENGTH_SHORT).show();
    }


}
