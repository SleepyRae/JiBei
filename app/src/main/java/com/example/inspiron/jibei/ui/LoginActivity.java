package com.example.inspiron.jibei.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.example.inspiron.jibei.R;

public class LoginActivity extends AppCompatActivity {

    private EditText username,password;
    private Button login;
    private TextView gotoRegister;

    private String userns,passws;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username=findViewById(R.id.username_login);
        password=findViewById(R.id.password_login);

        gotoRegister = (TextView) findViewById(R.id.turn_to_register);
        gotoRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread() {
                    public void run() {
                        Intent intent = new Intent(LoginActivity.this,RegisterActivity.class);
                        startActivity(intent);
                        finish();
                    }
                }.start();
            }
        });

        login = (Button)findViewById(R.id.btn_login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new Thread() {
                    public void run() {
                        login();
                    }
                }.start();

            }
        });


    }

    private void login() {
        userns=username.getText().toString();
        passws=password.getText().toString();

        //TODO:登录逻辑

        //登录成功进入主界面
        Intent intent=new Intent(LoginActivity.this,MainActivity.class);
        //intent.putExtra(,);//可以填入用户信息，如ID等
        startActivity(intent);
        finish();


    }
}
