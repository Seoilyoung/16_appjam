package org.androidtown.myko.component_login;


import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.androidtown.myko.R;
import org.androidtown.myko.component_server.ApplicationController;
import org.androidtown.myko.mail.GMailSender;
import org.androidtown.myko.model.Info_User;
import org.androidtown.myko.network.ServerInterface;

import java.util.Random;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class JoinActivity extends Activity {
    Spinner spinner_join ;
    ProgressDialog dialog;
    GMailSender sender;
    Boolean Certification = false;
    private ServerInterface api;
    int randomnumber, temp=0 ;
    String R_email ;
    String content ;
    String str_email;
    String str_chk1 ;
    EditText edit_chk;
    EditText edit_id ;
    EditText edit_pwd ;
    EditText edit_mailid;
    CheckBox chk_agree ;
    TextView txt_clause ;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_join);
        api = ApplicationController.getInstance().getServerInterface();
        initView();
        edit_chk = (EditText)findViewById(R.id.edit_chk);
        edit_id = (EditText)findViewById(R.id.edit_id);
        edit_mailid = (EditText)findViewById(R.id.edit_mailid);
        edit_pwd = (EditText)findViewById(R.id.edit_pwd);
        txt_clause = (TextView)findViewById(R.id.txt_clause);
        chk_agree = (CheckBox)findViewById(R.id.chk_agree);

        ArrayAdapter adapter = ArrayAdapter.createFromResource(
        getApplicationContext(), R.array.E_mail, android.R.layout.simple_spinner_item);

        SpinnerListener spinnerListener1 = new SpinnerListener();

        Spinner spin = (Spinner)findViewById(R.id.spinner_join);
        spin.setAdapter(adapter);
        spin.setOnItemSelectedListener(spinnerListener1);

        Button btn_give = (Button)findViewById(R.id.btn_give);
        btn_give.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                R_email = edit_mailid.getText().toString() + str_email;
                str_chk1 = String.format("%d",makeRandomnumber());
                sender = new GMailSender("16appjam@gmail.com", "appjam16"); // SUBSTITUTE ID PASSWORD
                content = "교수님을 부탁해!\n"+"앱 개발 중입니다.\n\n\n" + "인증 번호\n" + str_chk1;
                timeThread();
            }
        });

        Button btn_take = (Button)findViewById(R.id.btn_take);
        btn_take.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String str_chk2 = edit_chk.getText().toString();
                if(TextUtils.equals(str_chk1,str_chk2)) {
                    Certification = true;
                    Toast.makeText(getApplicationContext(), "인증 성공하였습니다", Toast.LENGTH_SHORT).show();
                }
                else {
                    Certification = false;
                    Toast.makeText(getApplicationContext(),"인증 실패하였습니다",Toast.LENGTH_SHORT).show();
                }
            }
        });

        txt_clause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"약관",Toast.LENGTH_SHORT).show();
            }
        });

        Button btn_join = (Button)findViewById(R.id.btn_join) ;
        btn_join.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Certification) {
                    if (chk_agree.isChecked()) {
                        Info_User info_user = new Info_User();
                        info_user.userid = edit_id.getText().toString();
                        info_user.password = edit_pwd.getText().toString();
                        String str_temp = String.format("%d",temp);
                        info_user.schoolcode = str_temp ;
                        if(temp==10) info_user.schoolcode = "1";
                        api.join(info_user, new Callback<Info_User>() {
                            @Override
                            public void success(Info_User info_user, Response response) {
                                Toast.makeText(getApplicationContext(), "등록 성공", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
                                startActivity(intent);
                            }

                            @Override
                            public void failure(RetrofitError error) {
                                Toast.makeText(getApplicationContext(), "등록 실패", Toast.LENGTH_SHORT).show();

                            }
                        });
                    } else
                        Toast.makeText(getApplicationContext(), "약관을 동의하지 않았습니다.", Toast.LENGTH_SHORT).show();
                }
                else
                    Toast.makeText(getApplicationContext(),"학교 인증 절차를 진행하여 주세요",Toast.LENGTH_SHORT).show();
            }
        });
    }


    public class SpinnerListener implements AdapterView.OnItemSelectedListener {
        public SpinnerListener() {}
        public void onItemSelected(AdapterView<?> arg0, View arg1,final int position, long arg2) {
            switch (position+1) {
                case 1:
                    str_email = "@swu.ac.kr" ;
                    temp = position + 1 ;
                    break ;
                case 2:
                    str_email = "@ssu.ac.kr" ;
                    temp = position + 1 ;
                    break ;
                case 3:
                    str_email ="@sogang.ac.kr" ;
                    temp = position + 1 ;
                    break ;
                case 4:
                    str_email = "@kookmin.ac.kr" ;
                    temp = position + 1 ;
                    break ;
                case 5:
                    str_email ="@hongik.ac.kr" ;
                    temp = position + 1 ;
                    break ;
                case 6:
                    str_email = "@kw.ac.krt" ;
                    temp = position + 1 ;
                    break ;
                case 7:
                    str_email ="@sejong.ac.kr" ;
                    temp = position + 1 ;
                    break ;
                case 8:
                    str_email = "@yonsei.ac.kr" ;
                    temp = position + 1 ;
                    break ;
                case 9:
                    str_email = "@korea.ac.kr" ;
                    temp = position + 1 ;
                    break ;
                case 10:
                    str_email = "@naver.com" ;
                    temp = position + 1 ;;
                    break ;
                default:
                    break ;
            }
        }public void onNothingSelected(AdapterView<?> arg0) {
        }
    }
    public void timeThread() {

        dialog = new ProgressDialog(this);
        dialog = new ProgressDialog(this);
        dialog.setTitle("Wait...");
        dialog.setMessage("학교 이메일로" + "\n" + "인증 코드 전송 중입니다");
        dialog.setIndeterminate(true);
        dialog.setCancelable(true);
        dialog.show();
        new Thread(new Runnable() {

            public void run() {
                // TODO Auto-generated method stub
                try {
                    sender.sendMail("교수님을 부탁해! 인증 번호입니다.", // subject.getText().toString(),
                            content, // body.getText().toString(),
                            "16appjam@gmail.com", // from.getText().toString(),
                            R_email // to.getText().toString()
                    );
                    sleep(3000);
                } catch (Exception e) {
                    Log.e("SendMail", e.getMessage(), e);
                    Toast.makeText(JoinActivity.this, "신청 실패", Toast.LENGTH_SHORT)
                            .show();


                }
                dialog.dismiss();
            }

            private void sleep(int i) {
                // TODO Auto-generated method stub

            }

        }).start();
    }
    private int makeRandomnumber() {
        Random rnd = new Random() ;
        randomnumber = rnd.nextInt(9000000)+1000000;
        return randomnumber ;
    }
    private void initView() {
        spinner_join = (Spinner) findViewById(R.id.spinner_join);
    }
}