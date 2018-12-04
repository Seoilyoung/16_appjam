/*
 * Copyright (C) 2015 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.androidtown.myko.component_main;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.androidtown.myko.R;
import org.androidtown.myko.component_login.LoginActivity;
import org.androidtown.myko.component_side.MyFragment;
import org.androidtown.myko.component_side.AboutFragment;
import org.androidtown.myko.model.Info_Professor;

/**
 * TODO
 */
public class MainActivity extends AppCompatActivity {
    int c;
    private DrawerLayout mDrawerLayout;
    ActionBar ab;
    Info_Professor save_info;
    TextView txt_userid ;
    ImageView img_view ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_main);
        c = 0;
        save_info = new Info_Professor();
        Intent intent = getIntent() ;
        String userschool = intent.getStringExtra("user_school");
        String userid = intent.getStringExtra("user_id") ;
        txt_userid = (TextView)findViewById(R.id.txt_userid);
        txt_userid.setText("eadfasdf");
        img_view = (ImageView)findViewById(R.id.view);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ab = getSupportActionBar();
        ab.setHomeAsUpIndicator(R.drawable.ic_menu);
        ab.setDisplayHomeAsUpEnabled(true);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        if (navigationView != null) {
            setupDrawerContent(navigationView);
            userschool="1";
            switch (userschool) {
                case "1" :
                    img_view.setImageResource(R.drawable.view_seoulwomen);
                    break;
                case "2" :
                    img_view.setImageResource(R.drawable.view_soongsil);
                    break;
                case "3" :
                    img_view.setImageResource(R.drawable.view_sogang);
                    break;
                case "4" :
                    img_view.setImageResource(R.drawable.view_kookmin);
                    break;
                case "5" :
                    img_view.setImageResource(R.drawable.view_hongik);
                    break ;
                case "6" :
                    img_view.setImageResource(R.drawable.view_kwangwoon);
                    break;
                case "7" :
                    img_view.setImageResource(R.drawable.view_sejong);
                    break;
                case "8" :
                    img_view.setImageResource(R.drawable.view_yonsei);
                    break;
                case "9" :
                    img_view.setImageResource(R.drawable.view_korea);
                    break;
                default:
                    break ;
            }
        }
        getSupportFragmentManager().beginTransaction().add(R.id.frameLayout_Main, new SearchFragment()).commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.menu_main, menu);
        menu.findItem(R.id.action_insert).setVisible(false);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        if (c == 3) {
            menu.findItem(R.id.action_insert).setVisible(true);
        } else {
            menu.findItem(R.id.action_insert).setVisible(false);
        }
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                if (c == 0)
                    mDrawerLayout.openDrawer(GravityCompat.START);
                else {
                    getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout_Main, new SearchFragment()).addToBackStack(null).commit();
                    getSupportActionBar().setTitle("교수님을 부탁해!");
                    ab.setHomeAsUpIndicator(R.drawable.ic_menu);
                }
                invalidateOptionsMenu();
                return true;
            case R.id.action_insert:
                getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout_Main, new InsertFragment()).addToBackStack(null).commit();

                c = 4;
                getSupportActionBar().setTitle("리뷰 등록");
                ab.setHomeAsUpIndicator(R.drawable.ic_back);
                invalidateOptionsMenu();
                return true;
        }
        return false;
    }

    private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        switch (menuItem.getItemId()) {
                            case R.id.nav_home:
                                getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout_Main, new MyFragment()).addToBackStack(null).commit();
                                getSupportActionBar().setTitle("내가 쓴 리뷰들");
                                c = 1;
                                break;

                            case R.id.nav_messages:
                                getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout_Main, new AboutFragment()).addToBackStack(null).commit();
                                getSupportActionBar().setTitle("이 앱에 대하여");
                                c = 2;
                                break;

                        }
                        ab.setHomeAsUpIndicator(R.drawable.ic_back);
                        menuItem.setChecked(true);
                        mDrawerLayout.closeDrawers();
                        return true;
                    }
                });
    }
}
