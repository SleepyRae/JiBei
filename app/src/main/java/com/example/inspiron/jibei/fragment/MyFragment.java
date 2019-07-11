package com.example.inspiron.jibei.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.example.inspiron.jibei.R;
import com.example.inspiron.jibei.ui.*;
import jp.wasabeef.glide.transformations.BlurTransformation;
import mehdi.sakout.fancybuttons.FancyButton;

import java.util.Date;

import static android.content.Context.MODE_PRIVATE;

public class MyFragment extends Fragment implements View.OnClickListener{

    private static final String TAG = "MyFragment";

    private View MyF;
    private ImageView h_head,h_black;

    private FancyButton bill_noting;
    private FancyButton bill_budget;
    private FancyButton sign_out;
    private LinearLayout connectus;
    private LinearLayout helpfeedback;

    @Nullable
    @Override
    public View onCreateView( @Nullable LayoutInflater inflater,  @Nullable ViewGroup container, Bundle savedInstanceState) {
        MyF= inflater.inflate(R.layout.fragment_my,container,false);

        init();

        return MyF;
    }

    public void init(){

        h_head=(ImageView)MyF.findViewById(R.id.h_head);
        h_black=(ImageView)MyF.findViewById(R.id.h_back);
        connectus=(LinearLayout)MyF.findViewById(R.id.connect_us);
        helpfeedback = (LinearLayout)MyF.findViewById(R.id.help_feedback);


        bill_budget=(FancyButton) MyF.findViewById(R.id.bill_budget);
        bill_noting=(FancyButton) MyF.findViewById(R.id.bill_noting);
        sign_out=(FancyButton) MyF.findViewById(R.id.sign_out);


        bill_budget.setOnClickListener(this);
        bill_noting.setOnClickListener(this);
        sign_out.setOnClickListener(this);
        connectus.setOnClickListener(this);
        helpfeedback.setOnClickListener(this);

        setUserPic();

    }

    public void setUserPic(){
        Glide.with(getContext())
                .load(R.drawable.user)
                .asBitmap()
                .centerCrop()
                .dontAnimate()
                .override(100, 100)
                .into(new BitmapImageViewTarget(h_head) {
                    @Override
                    protected void setResource(Bitmap resource) {
                        RoundedBitmapDrawable circularBitmapDrawable =
                                RoundedBitmapDrawableFactory.create(getContext().getResources(), resource);
                        circularBitmapDrawable.setCircular(true);
                        h_head.setImageDrawable(circularBitmapDrawable);
                    }
                });


        Glide.with(getContext())
                .load(R.drawable.background_pic)
                .bitmapTransform(new BlurTransformation(getContext(),25),new CenterCrop(getContext()))
                .dontAnimate()
                .into(h_black);

    }

    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bill_noting:
                startActivity(new Intent(getActivity(), BillNoting.class));
                break;
            case R.id.bill_budget:
                startActivity(new Intent(getActivity(), BillBudgetSetting.class));
                break;
            case R.id.sign_out:
                SharedPreferences.Editor editor=getActivity().getSharedPreferences("User",MODE_PRIVATE).edit();
                editor.putBoolean("isLogin",false);
                editor.apply();
                startActivity(new Intent(getActivity(), LoginActivity.class));
                getActivity().finish();
                break;
            case R.id.connect_us:
                startActivity(new Intent(getActivity(), ConnectUsActivity.class));
                break;
            case R.id.help_feedback:
                startActivity(new Intent(getActivity(), InfoActivity.class));
                break;
            default:
                break;
        }
    }

}
