package com.example.inspiron.jibei.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.UriMatcher;
import android.os.Bundle;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.inspiron.jibei.R;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.net.URLConnection;
import java.net.URLEncoder;

import static android.content.ContentValues.TAG;

public class AccountAddActivity extends AppCompatActivity {

    EditText edt1,edt2;

    String accountName,amount;

    private SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_add);

        sp = getSharedPreferences("Datadefault",MODE_PRIVATE);

        edt1 = (EditText)findViewById(R.id.account_name);
        edt2 = (EditText)findViewById(R.id.account_amount);

        ImageView iv1 = (ImageView) findViewById(R.id.toolbar_back);
        iv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        ImageView iv2 = (ImageView) findViewById(R.id.toolbar_complete);
        iv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addAccount();
                finish();
            }
        });

    }


//    private void login() {
//        accountName = edt1.getText().toString();
//        amount = edt2.getText().toString();
//        addAccount();
//    }

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



    private void requestPOST(String a,String b) {
        try {
            String baseUrl = "http://192.168.43.251:8081/account?";
            String requestUrl = baseUrl + "accountName=" + a + "&sum=" + b;
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
            urlConn.setRequestMethod("POST");
            //urlConn设置请求头信息
            //设置请求中的媒体类型信息。
            urlConn.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
            //设置客户端与服务连接类型
            urlConn.addRequestProperty("Connection", "Keep-Alive");
            //cookie添加
            urlConn.addRequestProperty("Cookie", sp.getString("jsessionid", ""));
            // 开始连接
            urlConn.connect();
            // 判断请求是否成功

            if (urlConn.getResponseCode() == 200) {
                // 获取返回的数据
                String result = streamToString(urlConn.getInputStream());
                Log.e(TAG, "POST方式请求成功，result--->" + result);

                JSONObject jsonObject=new JSONObject(result);     //返回的数据形式是一个Object类型

                Boolean succeed=jsonObject.getBoolean("succeed");
                String description=jsonObject.getString("description");

//                if(succeed){
//                    finish();
//                }else{
//                    if(Looper.myLooper() == null)
//                    {
//                        Looper.prepare();
//                    }
//                    Toast.makeText(LoginActivity.this,"" + description,Toast.LENGTH_SHORT).show();
//                    Looper.loop();
//                }
//

            } else {
                Log.e(TAG, "POST方式请求失败");
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
            requestPOST(accountName,amount);

        }
    };

    public void addAccount() {
        accountName = edt1.getText().toString();
        amount = edt2.getText().toString();
        new Thread(getRun).start();
    }
}
