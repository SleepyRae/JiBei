package com.example.inspiron.jibei.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.inspiron.jibei.FontHelper;
import com.example.inspiron.jibei.R;

public class ChartFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View chartF= inflater.inflate(R.layout.fragment_bill,container,false);

        FontHelper.injectFont(chartF.findViewById(R.id.rootView));

        return chartF;
    }
}