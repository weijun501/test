package com.test.app_two;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;

import androidx.core.app.ActivityCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;



import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;


import java.io.File;

import top.gate.boxcore.BBoxCore;

public class MainActivity extends AppCompatActivity {
// https://www.cnblogs.com/renhui/p/14214996.html
    // https://www.xda-developers.com/bypass-hidden-apis/
    // https://github.com/LSPosed/AndroidHiddenApiBypass
    // implementation 'com.github.ChickenHook:RestrictionBypass:2.2'
    private AppBarConfiguration appBarConfiguration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("zwj","MainActivity onCreate");
        setContentView(R.layout.activity_main);

        findViewById(R.id.fab).setOnClickListener((view) -> {
            String isInstall= BBoxCore.get().installPackageAsUser(new File(this.getFilesDir().getAbsolutePath()+"/share.pdf"),1).msg;
            boolean isLaunch= BBoxCore.get().launchApk("com.supergame.superslotsgame",1);
            Log.d("zwj","安装成功："+isInstall+" 启动成功:"+isLaunch);
            if(isLaunch){
                Toast.makeText(this,"启动成功...1",Toast.LENGTH_LONG).show();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp();
    }
}