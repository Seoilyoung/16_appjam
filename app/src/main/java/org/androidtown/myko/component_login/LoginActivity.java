package org.androidtown.myko.component_login;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import org.androidtown.myko.R;
import org.androidtown.myko.component_main.MainActivity;
import org.androidtown.myko.component_server.ApplicationController;
import org.androidtown.myko.model.Info_User;
import org.androidtown.myko.network.ServerInterface;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by Administrator on 2015-06-30.
 */
public class LoginActivity extends Activity {
    private ServerInterface api;
    private EditText edit_id,edit_pwd ;
    private Button btn_login ;
    public Info_User save_user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_login);
/*

        final ImageView imageView = (ImageView) findViewById(R.id.backdrop);
        Glide.with(this).load(R.drawable.t1).centerCrop().into(imageView);
*/

        save_user = new Info_User() ;

        ApplicationController application = ApplicationController.getInstance();
        application.buildServerInterface();
        api = application.getServerInterface();
        edit_id = (EditText)findViewById(R.id.edit_id);
        edit_pwd = (EditText)findViewById(R.id.edit_pwd);
        btn_login = (Button)findViewById(R.id.btn_login);

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Info_User info_user = new Info_User() ;
                info_user.userid = edit_id.getText().toString() ;
                info_user.password = edit_pwd.getText().toString() ;
                api.login(info_user, new Callback<Info_User>() {
                    @Override
                    public void success(Info_User info_user, Response response) {
                        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                        intent.putExtra("user_school",info_user.schoolcode);
                        intent.putExtra("user_id",info_user.userid);
                        startActivity(intent);
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        error.printStackTrace();
                        Toast.makeText(getApplicationContext(),"로그인 정보가 일치하지 않습니다",Toast.LENGTH_SHORT).show();
                    }
                });

                /*Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);*/
            }
        });
        TextView txt_join ;
        txt_join = (TextView) findViewById(R.id.txt_join);
        txt_join.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),JoinActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }
}
