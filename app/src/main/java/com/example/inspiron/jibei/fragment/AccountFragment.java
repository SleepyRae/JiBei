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

 public class AccountFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View accountF= inflater.inflate(R.layout.fragment_account,container,false);

        FontHelper.injectFont(accountF.findViewById(R.id.rootView));

        return accountF;
    }
}