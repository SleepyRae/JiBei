package com.example.inspiron.jibei.ui;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import com.example.inspiron.jibei.R;
import com.example.inspiron.jibei.fragment.ChartFragment;
import com.example.inspiron.jibei.fragment.PieFragment;

public class PieChartActivity extends AppCompatActivity {

    private FrameLayout fra;
    private PieFragment piefra;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pie_chart);

        piefra= new PieFragment();
        //调用查找控件方法
        initView();
        //调用布局替换方法进入默认加载第一个Fragment布局页面
        frag(piefra);
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

    }

}
