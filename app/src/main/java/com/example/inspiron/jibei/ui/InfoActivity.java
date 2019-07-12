package com.example.inspiron.jibei.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.example.inspiron.jibei.FontHelper;
import com.example.inspiron.jibei.R;
import mehdi.sakout.fancybuttons.FancyButton;

public class InfoActivity extends AppCompatActivity {
    private TextView cancle;
    private EditText moneySelector;
    private FancyButton button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        cancle=(TextView)findViewById(R.id.cancle_button2);
        moneySelector=(EditText)findViewById(R.id.comment);
        button=findViewById(R.id.submit);
        FontHelper.injectFont(findViewById(R.id.rootView));

        cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });//返回键


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = moneySelector.getText().toString();
                if (!TextUtils.isEmpty(text)) {
                    Toast.makeText(getApplicationContext(), "意见反馈成功", Toast.LENGTH_SHORT).show();

                }
                else {
                    Toast.makeText(getApplicationContext(), "请输入内容！", Toast.LENGTH_SHORT).show();

                }
            }
        });

    }
}
