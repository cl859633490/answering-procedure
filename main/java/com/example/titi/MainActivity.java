package com.example.titi;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.example.titi.ui.notifications.setting;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.titi.databinding.ActivityMainBinding;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;


public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;

    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(false);
       /* ActionBar actionBar =getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        actionBar.setDisplayOptions(0,ActionBar.DISPLAY_SHOW_TITLE);*/
        /*FrameLayout frameLayout=findViewById(R.id.navigation_home);
        frameLayout.addView(new d(this));*/


            binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);
        initFile();
setStatusBar(true);
       /* android.widget.SearchView search_button= findViewById(R.id.search_button);
        search_button.setQueryHint("  \t ?????????????????? ");*/

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode==KeyEvent.KEYCODE_BACK){
            exit();
            return true;
        }

        return super.onKeyDown(keyCode, event);
    }


    public void exit(){

        long etime=0;
        if((System.currentTimeMillis()-etime)>20000){
            Toast.makeText(this,"????????????????????????",Toast.LENGTH_LONG).show();
            etime=System.currentTimeMillis();
        }else {
            finish();
            System.exit(0);
        }
    }
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {

        super.onCreateContextMenu(menu, v, menuInfo);
        menu.add("??????"); menu.add("??????");

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater=getMenuInflater();
        menuInflater.inflate(R.menu.meun,menu);
        /*MenuItem menuItem=menu.findItem(R.id. searchView );
SearchView searchView = (SearchView) MenuItemCompat.getActionView(menuItem);
        MenuItemCompat. setOnActionExpandListener (menuItem, new MenuItemCompat.OnActionExpandListener() {
            @Override
            public boolean onMenuItemActionExpand(MenuItem item) {
                Toast.makeText(MainActivity.this, "?????????Expand!", Toast.LENGTH_SHORT).show();
                return true;
            }

            @Override
            public boolean onMenuItemActionCollapse(MenuItem item) {
                return false;
            }
        });*/
            //??????menu??????
           /* getMenuInflater().inflate(R.menu.meun, menu);*/





            return super.onCreateOptionsMenu(menu);

    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
           case  R.id.regarding:
               Intent intent=new Intent(MainActivity.this, setting.class);
                startActivity(intent);
              break;
            case  R.id.message:
              Intent intent1=new Intent(MainActivity.this,setting.class);
                startActivity(intent1);
                break;


        }
        return super.onOptionsItemSelected(item);}

    public void setStatusBar(boolean navi) {

//api>21,??????????????????????????????;api>19,??????????????????????????????


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

            Window window = getWindow();

            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);

            window.setStatusBarColor(Color.TRANSPARENT);

            if (navi) {

                window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN//???????????????????????????activity???????????????????????????????????????

                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION//???????????????????????????activity???????????????????????????????????????

                        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);

                window.setNavigationBarColor(Color.TRANSPARENT);

            } else {

                window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN

                        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);

            }

        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {

            if (navi) {

//??????????????????

                getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);

            }

//??????????????????

            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

        }

    }

    private void initFile() {
        //????????????????????????????????????????????????
        if (!new File("/data/data/shui.db").exists()) {
            File dir = new File("/data/data/shui.db");
            if (!dir.exists()) {
                Log.i(TAG, TAG + "Checking if DB exists...");
                dir.mkdir();
            }

            //????????????
            try {
                InputStream is = getBaseContext().getAssets().open("shui");
                OutputStream os = new FileOutputStream("shui.db");

                //??????????????????
                byte[] buffer = new byte[1024];
                //???????????????????????????
                int length = 0;

                //????????????
                while ((length = is.read(buffer)) > 0) {
                    os.write(buffer, 0, length);
                }

                //??????
                os.flush();
                //??????
                os.close();
                is.close();

            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }

}