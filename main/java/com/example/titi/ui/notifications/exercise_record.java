package com.example.titi.ui.notifications;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NavUtils;

import com.example.titi.R;

public class exercise_record extends AppCompatActivity {

    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.exercise_record);
        if (NavUtils.getParentActivityName(this) != null) {
            getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(false);
        }

    }
}