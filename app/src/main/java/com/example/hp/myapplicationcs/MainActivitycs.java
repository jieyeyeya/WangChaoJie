package com.example.hp.myapplicationcs;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

import java.net.PasswordAuthentication;

import static java.lang.Thread.sleep;

public class MainActivitycs extends AppCompatActivity {
    
    private Button button;
    private Button button1;


    private EditText username, password;
    private String userNameValue, passwordValue;
    private CheckBox rememberPassword, autoLogin;
    private Button btn_login, btn_register;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my2018shiti);
        init_UI();
        is_login();
        ClickListener();
    }

    private void init_UI() {
        //获得实例对象
        username = findViewById(R.id.signup_phone_num_Edta);
        password = findViewById(R.id.signup_pswd_Edta);
        rememberPassword = findViewById(R.id.cb_passworda);
        autoLogin = findViewById(R.id.cb_logina);
        btn_login = findViewById(R.id.signup_Btna);
        btn_register = findViewById(R.id.signup_Btna1);
        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("登陆");
        progressDialog.setMessage("登陆中");
        progressDialog.setCancelable(false);
    }

    public void is_login() {
        //判断记住密码多选框的状态
        if (App.isRememberPassword()) {
            //设置默认是记录密码状态
            rememberPassword.setChecked(true);
            username.setText(App.getUserName());
            password.setText(App.getPassword());
            //判断自动登陆多选框状态
            if (App.isAutoLogin() && isPassword(App.getUserName(), App.getPassword())) {
                //设置默认是自动登录状态
                autoLogin.setChecked(true);
                startIntent();
            }
        } else {
            if (!App.getUserName().equals(""))
                username.setText(App.getUserName());
        }
    }

    private void ClickListener() {
        //监听记住密码多选框按钮事件
        rememberPassword.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (rememberPassword.isChecked())
                    //记住密码已选中
                    App.setRememberPassword(true);
                else
                    ///记住密码没有选中
                    App.setRememberPassword(false);
            }
        });
        //监听自动登录多选框事件
        autoLogin.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (autoLogin.isChecked()) {
                    //自动登录已选中
                    rememberPassword.setChecked(true);
                    App.setAutoLogin(true);

                } else
                    //自动登录没有选中
                    App.setAutoLogin(false);
            }
        });
        btn_login.setOnClickListener((View.OnClickListener) this);
        btn_register.setOnClickListener((View.OnClickListener) this);
    }
    
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.signup_Btna:
                if (isPassword(App.getUserName(), App.getPassword()))
                    startIntent();
                else {
                    App.showAlertDialog(this, "提醒", "用户名或密码错误，请重新登录",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    password.setText("");
                                    autoLogin.setChecked(false);
                                    rememberPassword.setChecked(false);
                                }
                            });
                }
                break;
            case R.id.signup_Btna1:
                new RegisterDialog().show(getSupportFragmentManager(), "Register");
                break;
        }
    }

    //登陆成功跳转
    private void startIntent() {
        progressDialog.show();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                //跳转界面
                progressDialog.dismiss();
                startActivity(new Intent(MainActivitycs.this, MainActivityzhgl.class));
                MainActivitycs.this.finish();
            }
        }).start();
    }

    //判断账号密码是否正确
    private boolean isPassword(String _userName, String _password) {
        userNameValue = username.getText().toString();
        passwordValue = password.getText().toString();
        return (userNameValue.equals(_userName) && passwordValue.equals(_password) && !_userName.equals("") && !_password.equals(""));
    }

}
