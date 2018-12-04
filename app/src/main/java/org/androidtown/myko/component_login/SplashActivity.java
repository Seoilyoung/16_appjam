package org.androidtown.myko.component_login;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import org.androidtown.myko.R;
import org.androidtown.myko.component_main.MainActivity;

/**
 * Created by Administrator on 2015-07-08.
 */
public class SplashActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        try {
            Thread.sleep(2000);
        }catch (InterruptedException e) {
            e.printStackTrace();
        }

        startActivity(new Intent(this,MainActivity.class));
        finish() ;
    }
}