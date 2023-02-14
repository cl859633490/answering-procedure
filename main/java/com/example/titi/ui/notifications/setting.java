package com.example.titi.ui.notifications;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.app.NavUtils;

import com.example.titi.MainActivity;
import com.example.titi.R;
import com.google.android.material.snackbar.Snackbar;

public class setting extends AppCompatActivity {
    private  SharedPreferences sprfMain;
    SharedPreferences.Editor editorMain;
    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting);
        if (NavUtils.getParentActivityName(this)!=null){
            getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(false);}



      /*  ActionBar actionBar =getSupportActionBar();
          actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
          actionBar.setDisplayOptions(0,ActionBar.DISPLAY_SHOW_TITLE);
          actionBar.addTab(actionBar.newTab()
                 .setText("cid").setTabListener(new mytab(this,fragment1.class)));*/


      /*  Snackbar.make(c,"这是massage", Snackbar.LENGTH_LONG).setAction(
                "这是action", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(setting.this,"你点击了action",Toast.LENGTH_SHORT).show();
                    }
                }).show();*/
        sprfMain = getSharedPreferences("counter", Context.MODE_PRIVATE);
        editorMain = sprfMain.edit();
        Switch nigth=findViewById(R.id.switch1);
        nigth.setClickable(true);


        //取出保存的值（取数据）
        boolean isChecked = sprfMain.getBoolean("isChecked", false);
        nigth.setChecked(false);//获取状态并设置当前状态

        //开关
        nigth.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                    //保存数据
                    editorMain.putBoolean("isChecked", true);
                    editorMain.commit();


                } else {
                    getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                    //保存数据
                    editorMain.putBoolean("isChecked", false);
                    editorMain.commit();

                } //recreate();//
            }
        });



    }


    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.setting,container,false);

        return view;
    }

}
