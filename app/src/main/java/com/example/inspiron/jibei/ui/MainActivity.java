package com.example.inspiron.jibei.ui;


import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import com.example.inspiron.jibei.R;
import com.example.inspiron.jibei.fragment.ChartFragment;
import com.example.inspiron.jibei.fragment.BillFragment;
import com.example.inspiron.jibei.fragment.AccountFragment;
import com.example.inspiron.jibei.fragment.MyFragment;


public class MainActivity extends FragmentActivity implements View.OnClickListener{


    private FrameLayout fra;
    private RadioButton rb1;
    private RadioButton rb2;
    private RadioButton rb3;
    private RadioButton rb4;
    private RadioGroup rg;
    private BillFragment fr1;
    private AccountFragment fr2;
    private ChartFragment fr3;
    private MyFragment fr4;
    private ImageView add_img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fr1 = new BillFragment();
        fr2 = new AccountFragment();
        fr3 = new ChartFragment();
        fr4 = new MyFragment();
        //调用查找控件方法
        initView();
        //调用布局替换方法进入默认加载第一个Fragment布局页面
        frag(fr1);

    }
    //替换页面方法
    private void frag(Fragment f) {
        //这是继承v4包的方法
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,f).commit();
        //以下是fragment继承app下的方法
//    FragmentManager().beginTransaction().replace(R.id.fra,f).commit();
    }
    //查找控件方法
    private void initView() {
        fra = (FrameLayout) findViewById(R.id.fragment_container);
        rb1 = (RadioButton) findViewById(R.id.bill_tab);
        rb1.setOnClickListener(this);
        rb2 = (RadioButton) findViewById(R.id.account_tab);
        rb2.setOnClickListener(this);
        rb3 = (RadioButton) findViewById(R.id.chart_tab);
        rb3.setOnClickListener(this);
        rb4 = (RadioButton) findViewById(R.id.my_tab);
        rb4.setOnClickListener(this);
        add_img=(ImageView) findViewById(R.id.add_img);
        add_img.setOnClickListener(this);
        rg = (RadioGroup) findViewById(R.id.tabs_rg);
    }
    //自动生成的点击事件
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.bill_tab:
                frag(fr1);
                break;
            case R.id.account_tab:
                frag(fr2);
                break;
            case R.id.chart_tab:
                frag(fr3);
                break;
            case R.id.my_tab:
                frag(fr4);
                break;
            case R.id.add_img:
                startActivity(new Intent(MainActivity.this, AddBillActivity.class));
        }
    }

}
