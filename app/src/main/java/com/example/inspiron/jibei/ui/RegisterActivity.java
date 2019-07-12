package com.example.inspiron.jibei.ui;

import android.content.Intent;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.example.inspiron.jibei.R;

import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import static android.content.ContentValues.TAG;

public class RegisterActivity extends AppCompatActivity {

    private EditText username,passw,compassw;
    private Button register;
    private TextView gotoLogin;

    //用户名 密码 确认密码 字符串
    private String userns,pws,cpws;

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
                        comparePassword(username,passw,compassw);
                    }
                }.start();

            }
        });


    }

    public void comparePassword(EditText userna,EditText pw, EditText cpw){
        userns = userna.getText().toString();
        pws=pw.getText().toString();
        cpws=cpw.getText().toString();

         if(userns.equals("")){
            if(Looper.myLooper()==null)
            {
                Looper.prepare();
            }
            Toast.makeText(RegisterActivity.this,"用户名不能为空",Toast.LENGTH_LONG).show();
            Looper.loop();
        }
        else if (pws.equals("")||cpws.equals("")){	//判断两次密码是否为空
            if(Looper.myLooper() == null)
            {
                Looper.prepare();
            }
            Toast.makeText(RegisterActivity.this,"密码不能为空",Toast.LENGTH_SHORT).show();
            Looper.loop();
        }else if(!pws.equals(cpws)){
            if(Looper.myLooper()==null)
            {
                Looper.prepare();
            }
            Toast.makeText(RegisterActivity.this,"两次密码不一致",Toast.LENGTH_LONG).show();
            Looper.loop();
        }
        else if(pws.equals(cpws)){
            if(Looper.myLooper() == null)
            {
                Looper.prepare();
            }
            register();
            Toast.makeText(RegisterActivity.this,"注册成功！",Toast.LENGTH_SHORT).show();
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

    private void register() {
        //TODO:登录逻辑
        registerGET();

    }

    /**
     * 将输入流转换成字符串
     *
     * @param is 从网络获取的输入流
     * @return
     */
    public String streamToString(InputStream is) {
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int len = 0;
            while ((len = is.read(buffer)) != -1) {
                baos.write(buffer, 0, len);
            }
            baos.close();
            is.close();
            byte[] byteArray = baos.toByteArray();
            return new String(byteArray);
        } catch (Exception e) {
            Log.e(TAG, e.toString());
            return null;
        }
    }



    private void requestGet(String a,String b) {
        try {
            String baseUrl = "http://192.168.43.251:8081/register?";
            String requestUrl = baseUrl + "userName=" + a + "&password=" + b;
            // 新建一个URL对象
            URL url = new URL(requestUrl);
            // 打开一个HttpURLConnection连接
            HttpURLConnection urlConn = (HttpURLConnection) url.openConnection();
            // 设置连接主机超时时间
            urlConn.setConnectTimeout(5 * 1000);
            //设置从主机读取数据超时
            urlConn.setReadTimeout(5 * 1000);
            // 设置是否使用缓存  默认是true
            urlConn.setUseCaches(true);
            // 设置为Post请求
            urlConn.setRequestMethod("GET");
            //urlConn设置请求头信息
            //设置请求中的媒体类型信息。
            urlConn.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
            //设置客户端与服务连接类型
            urlConn.addRequestProperty("Connection", "Keep-Alive");
            // 开始连接
            urlConn.connect();
            // 判断请求是否成功

            if (urlConn.getResponseCode() == 200) {
                // 获取返回的数据
                String result = streamToString(urlConn.getInputStream());
                Log.e(TAG, "Get方式请求成功，result--->" + result);

                JSONObject jsonObject=new JSONObject(result);     //返回的数据形式是一个Object类型

                Boolean succeed=jsonObject.getBoolean("succeed");
                String description=jsonObject.getString("description");

                if(succeed){
                    //登录成功进入主界面
                    Intent intent=new Intent(RegisterActivity.this,LoginActivity.class);
                    //intent.putExtra(,);//可以填入用户信息，如ID等
                    startActivity(intent);
                    finish();
                }else{
//                    if(Looper.myLooper() == null)
//                    {
//                        Looper.prepare();
//                    }
//                    Toast.makeText(LoginActivity.this,"" + description,Toast.LENGTH_SHORT).show();
//                    Looper.loop();
                }


            } else {
                Log.e(TAG, "Get方式请求失败");
            }
            // 关闭连接
            urlConn.disconnect();
        } catch (Exception e) {
            Log.e(TAG, e.toString());
        }
    }


    Runnable getRun = new Runnable() {

        @Override
        public void run() {
            // TODO Auto-generated method stub
            requestGet(userns,pws);

        }
    };

    public void registerGET() {
        new Thread(getRun).start();
    }

}
