package com.example.inspiron.jibei.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.inspiron.jibei.FontHelper;
import com.example.inspiron.jibei.R;
import com.example.inspiron.jibei.adapter.AccountAdapter;
import com.example.inspiron.jibei.ui.AccountAddActivity;

import org.json.JSONArray;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import butterknife.BindView;

import static android.content.ContentValues.TAG;
import static android.content.Context.MODE_PRIVATE;


@SuppressLint("ValidFragment")
public class AccountFragment extends Fragment implements View.OnClickListener {
    @BindView(R.id.ll_add_deposit)
    LinearLayout mLlAddDeposit;
    private Handler handler;
    private RecyclerView recyclerView;//声明RecyclerView
    private AccountAdapter adapterDome;//声明适配器
    private View AccountPage;
    private LinearLayout add_account;
    private Context context;
    private SharedPreferences sp;
    private JSONArray jsonArray;
    Runnable getRun = new Runnable() {

        @Override
        public void run() {
            // TODO Auto-generated method stub
            requestGet();

        }
    };

    public AccountFragment(Context context) {
        this.context = context;
    }

    @SuppressLint("HandlerLeak")
    @Nullable
    @Override
    public View onCreateView(@Nullable LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        AccountPage = inflater.inflate(R.layout.fragment_account, container, false);
        FontHelper.injectFont(AccountPage.findViewById(R.id.rootView));

        //个人理解是由于Activity没有活动这个概念所以不存在oncreate生命周期函数  hhhhh
        sp = context.getSharedPreferences("Datadefault", MODE_PRIVATE);
        getAccounts();


        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                switch (msg.what){
                    case 100:
                        recyclerView = (RecyclerView) AccountPage.findViewById(R.id.rv_account_lists);
                        adapterDome = new AccountAdapter(getActivity(), jsonArray);
                        LinearLayoutManager manager = new LinearLayoutManager(context);
                        manager.setOrientation(LinearLayoutManager.VERTICAL);
                        recyclerView.setLayoutManager(manager);
                        recyclerView.setAdapter(adapterDome);
                        Log.e(TAG,jsonArray.toString() );
                        break;
                        default:break;
                }
            }
        };



//        recyclerView = (RecyclerView)AccountPage.findViewById(R.id.rv_account_lists);
//
//        adapterDome = new AccountAdapter(getActivity(),jsonArray);
////        Log.e(TAG, jsonArray.toString() );
//        /*
//        与ListView效果对应的可以通过LinearLayoutManager来设置
//        与GridView效果对应的可以通过GridLayoutManager来设置
//        与瀑布流对应的可以通过StaggeredGridLayoutManager来设置
//        */
//        //LinearLayoutManager manager = new LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false);
//        LinearLayoutManager manager = new LinearLayoutManager(context);
//        manager.setOrientation(LinearLayoutManager.VERTICAL);
//        //RecyclerView.LayoutManager manager = new LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false);
//        //GridLayoutManager manager1 = new GridLayoutManager(context,2);
//        //manager1.setOrientation(GridLayoutManager.VERTICAL);
//        //StaggeredGridLayoutManager manager2 = new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL);
//        recyclerView.setLayoutManager(manager);
//        recyclerView.setAdapter(adapterDome);


        init();

        return AccountPage;
    }

    public void init() {
        add_account = (LinearLayout) AccountPage.findViewById(R.id.ll_add_deposit);
        add_account.setOnClickListener(this);

    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_add_deposit:
                startActivity(new Intent(getActivity(), AccountAddActivity.class));
                break;
            default:
                break;
        }
    }

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

    private void requestGet() {
        try {
            String baseUrl = "http://192.168.43.251:8081/accounts";
            String requestUrl = baseUrl;
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
            urlConn.addRequestProperty("Cookie", sp.getString("jsessionid", ""));
            // 开始连接
            urlConn.connect();
            // 判断请求是否成功

            if (urlConn.getResponseCode() == 200) {
                // 获取返回的数据
                String result = streamToString(urlConn.getInputStream());
                Log.e(TAG, "Get方式请求成功，result--->" + result);

//                ArrayList<JSONObject> jsonObject=new JSONObject(result);     //返回的数据形式是一个Object类型
                jsonArray = new JSONArray(result);
                Log.e(TAG, jsonArray.toString());

                Message message = new Message();

                message.what = 100;

                handler.sendMessage(message);

//
//                Object a = jsonArray.getJSONObject(4).get("accountName");
//                Log.e(TAG,a.toString() );
//                Boolean succeed=jsonObject.getBoolean("succeed");
//                String description=jsonObject.getString("description");

                //注意这里获取服务器返回的头部信息,获取JSESSIONID=XXXXXX的信息
//                final String cookieString=urlConn.getHeaderField("Set-Cookie");
                //然后保存在本地


//                if(succeed){
//                    //登录成功进入主界面
//                    Intent intent=new Intent(LoginActivity.this,MainActivity.class);
//                    //intent.putExtra(,);//可以填入用户信息，如ID等
//                    startActivity(intent);
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
                Log.e(TAG, "Get方式请求失败");
            }
            // 关闭连接
            urlConn.disconnect();
        } catch (Exception e) {
            Log.e(TAG, e.toString());
        }
    }

    public void getAccounts() {
        try {
            new Thread(getRun).start();
        } catch (Exception e) {

        }
    }

}
