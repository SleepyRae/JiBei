package com.example.inspiron.jibei.ui;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import com.example.inspiron.jibei.FontHelper;
import com.example.inspiron.jibei.R;

public class ConnectUsActivity extends AppCompatActivity {
    private TextView cancle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connect_us);
        FontHelper.injectFont(findViewById(R.id.rootView));

        cancle=(TextView)findViewById(R.id.cancle_button1);
        cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });//返回键

        TextView question1 = findViewById(R.id.question1);
        question1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showNormalDialog1();
            }
        });//问题1

        TextView question2 = findViewById(R.id.question2);
        question2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showNormalDialog2();
            }
        });//问题2

        TextView question3 = findViewById(R.id.question3);
        question3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showNormalDialog3();
            }
        });//问题3

        TextView question4 = findViewById(R.id.question4);
        question4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showNormalDialog4();
            }
        });//问题4

        TextView question5 = findViewById(R.id.question5);
        question5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showNormalDialog5();
            }
        });//问题5


    }


    private void showNormalDialog1(){
        final AlertDialog.Builder normalDialog = new AlertDialog.Builder(ConnectUsActivity.this);
        normalDialog.setTitle("如何创建账本？");
        normalDialog.setMessage(" 账本>>新建账本 ");
        normalDialog.setNegativeButton("关闭",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //...To-do
                    }
                });
        // 显示
        normalDialog.show();
    }//问题1

    private void showNormalDialog2(){
        final AlertDialog.Builder normalDialog = new AlertDialog.Builder(ConnectUsActivity.this);
        normalDialog.setTitle("如何创建账单？");
        normalDialog.setMessage(" 账本>>记一笔 ");
        normalDialog.setNegativeButton("关闭",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //...To-do
                    }
                });
        // 显示
        normalDialog.show();
    }//问题1

    private void showNormalDialog3(){
        final AlertDialog.Builder normalDialog = new AlertDialog.Builder(ConnectUsActivity.this);
        normalDialog.setTitle("如何生成报表？");
        normalDialog.setMessage(" 选择账本>>报表 ");
        normalDialog.setNegativeButton("关闭",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //...To-do
                    }
                });
        // 显示
        normalDialog.show();
    }//问题1

    private void showNormalDialog4(){
        final AlertDialog.Builder normalDialog = new AlertDialog.Builder(ConnectUsActivity.this);
        normalDialog.setTitle("如何联系客服？");
        normalDialog.setMessage(" 个人中心>>联系我们>>QQ群 ");
        normalDialog.setNegativeButton("关闭",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //...To-do
                    }
                });
        // 显示
        normalDialog.show();
    }//问题1

    private void showNormalDialog5(){
        final AlertDialog.Builder normalDialog = new AlertDialog.Builder(ConnectUsActivity.this);
        normalDialog.setTitle("如何反馈意见？");
        normalDialog.setMessage(" 个人中心>>意见反馈 ");
        normalDialog.setNegativeButton("关闭",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //...To-do
                    }
                });
        // 显示
        normalDialog.show();
    }//问题1



}
