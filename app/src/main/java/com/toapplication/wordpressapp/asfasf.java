package com.toapplication.wordpressapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatTextView;

import com.toapplication.wordpressapp.webservice.ServiceManager;

import org.jetbrains.annotations.NotNull;

public class asfasf extends AppCompatActivity{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ServiceManager.Companion.getInstance().getRecentPosts(1, (f, p)->{

        });

        ServiceManager.Companion.getInstance().getRecentPosts(0, new ServiceManager.ISuccessCallback() {
            @Override
            public void onSuccess(@NotNull String status, @NotNull Object data) {

            }
        });
    }
}
