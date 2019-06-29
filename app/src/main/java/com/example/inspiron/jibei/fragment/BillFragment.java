package com.example.inspiron.jibei.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.TextView;
import com.example.inspiron.jibei.FontHelper;
import com.example.inspiron.jibei.R;
import se.emilsjolander.stickylistheaders.StickyListHeadersListView;

import java.util.ArrayList;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;


public class BillFragment extends Fragment {
    private static final String TAG = "BillFragment";
    private View billF;

    private TextView balance, balance_month, spend, spend_month, income, income_month;
    private StickyListHeadersListView stickyList;

    private String lastCurrentDateEndMonth = "19900701";
    private String currentMonth;
    private String moneyType;
    private String moneyCount;

    /*private BillItemAdapter adapter;
    private List<BillItem> billItems = new ArrayList<>();
    private List<BillItemShow> BillList = new ArrayList<>();
    private List<BillItem> billItemForCalMonthBalance = new ArrayList<>();*/


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        billF= inflater.inflate(R.layout.fragment_bill,container,false);
        FontHelper.injectFont(billF.findViewById(R.id.rootView));
        stickyList = (StickyListHeadersListView) billF.findViewById(R.id.stickyListView);

        /*billItems = DataSupport.select().where("user_id = ?", MainActivity.getUserLogining()).order("create_date").find(BillItem.class);
*/
        init();

        /*adapter = new BillItemAdapter(getActivity(), BillList);
        stickyList.setAdapter(adapter);
        initListener();*/

         return billF;
    }

    public void init() {
        //查询item
        queryAll();

        balance = (TextView) billF.findViewById(R.id.balance);
        balance_month = (TextView) billF.findViewById(R.id.balance_month);
        spend = (TextView) billF.findViewById(R.id.spend);
        spend_month = (TextView) billF.findViewById(R.id.spend_month);
        income = (TextView) billF.findViewById(R.id.income);
        income_month = (TextView) billF.findViewById(R.id.income_month);

    }

    public void queryAll() {
       /* BillList.clear();
        BillItemShow billItemShow;
//        billItems=DataSupport.findAll(BillItem.class);
        billItems = DataSupport.select().where("user_id = ?", MainActivity.getUserLogining()).order("create_date").find(BillItem.class);
        for (int i = billItems.size() - 1; i >= 0; i--) {
            BillItem b = billItems.get(i);
            billItemShow = new BillItemShow(b.getId(), b.getHead_id(), b.getCreate_date(), b.getMoney(), b.isPayment_type(), b.getMoney_type(), b.getNote());
            BillList.add(billItemShow);
        }*/
    }

    @Override
    public void onResume() {
        super.onResume();

        queryAll();
       /* adapter.notifyDataSetChanged();*/
        SharedPreferences.Editor editor = getActivity().getSharedPreferences("User", MODE_PRIVATE).edit();
        editor.putBoolean("isDelete", false);
        editor.apply();


    }

}