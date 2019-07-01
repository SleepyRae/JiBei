package com.example.inspiron.jibei.ui;

import android.content.Intent;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.example.inspiron.jibei.R;

public class RegisterActivity extends AppCompatActivity {

    private EditText username,passw,compassw;
    private Button register;
    private TextView gotoLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        username=findViewById(R.id.username_register);
        passw=findViewById(R.id.password_register);
        compassw=findViewById(R.id.compass_register);

        register=findViewById(R.id.register);
        gotoLogin=findViewById( R.id.turn_to_login);
        gotoLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                        Intent intent = new Intent(RegisterActivity.this,LoginActivity.class);
                        startActivity(intent);
                        finish();

            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                new Thread() {
                    public void run() {
                        comparePassword(passw,compassw);
                    }
                }.start();

            }
        });


    }

    public void comparePassword(EditText pw, EditText cpw){
        String pws=pw.getText().toString();
        String cpws=cpw.getText().toString();

        if (pws.equals("")||cpws.equals("")){	//判断两次密码是否为空
            if(Looper.myLooper() == null)
            {
                Looper.prepare();
            }
            Toast.makeText(RegisterActivity.this,"密码不能为空",Toast.LENGTH_SHORT).show();
            Looper.loop();
        }else if(pws.equals(cpws)){
            if(Looper.myLooper() == null)
            {
                Looper.prepare();
            }
            Toast.makeText(RegisterActivity.this,"注册成功",Toast.LENGTH_SHORT).show();
            //TODO:把Editext里面的密码上传到数据库

            //注册成功后进入提前写好的登录页面
            Intent intent=new Intent(RegisterActivity.this,LoginActivity.class);
            //intent.putExtra(,);//可以填入用户信息，如ID等
            startActivity(intent);
            finish();

            Looper.loop();


        }else{
            if(Looper.myLooper() == null)
            {
                Looper.prepare();
            }
            Toast.makeText(RegisterActivity.this,"密码不一致，请重新输入",Toast.LENGTH_SHORT).show();
            Looper.loop();
        }
    }
}
