package com.example.inspiron.jibei.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

public class LoginActivity extends AppCompatActivity {

    private EditText username,password;
    private Button login;
    private TextView gotoRegister;

    private String userns,passws;

    //保存会话session到本地
    private SharedPreferences sp;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);



        sp = getSharedPreferences("Datadefault",MODE_PRIVATE);//创建对象，Datadefault是储存数据的对象名
        editor = sp.edit();//获取编辑对象


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
//                Intent intent = new Intent(LoginActivity.this,MainActivity.class);
//                startActivity(intent);
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
        loginGET();

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
            String baseUrl = "http://192.168.43.251:8081/login?";
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

                //注意这里获取服务器返回的头部信息,获取JSESSIONID=XXXXXX的信息
                final String cookieString=urlConn.getHeaderField("Set-Cookie");
                //然后保存在本地
                editor.putString("jsessionid", cookieString);
                editor.commit();


                if(succeed){
                    //登录成功进入主界面
                    Intent intent=new Intent(LoginActivity.this,MainActivity.class);
                    //intent.putExtra(,);//可以填入用户信息，如ID等
                    startActivity(intent);
                    finish();
                }else{
                    if(Looper.myLooper() == null)
                    {
                        Looper.prepare();
                    }
                    Toast.makeText(LoginActivity.this,"" + description,Toast.LENGTH_SHORT).show();
                    Looper.loop();
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
            requestGet(userns,passws);

        }
    };

    public void loginGET() {
        new Thread(getRun).start();
    }
}
