package com.example.inspiron.jibei.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.RadioGroup;
import android.widget.TextView;
import com.example.inspiron.jibei.FontHelper;
import com.example.inspiron.jibei.R;

import java.util.ArrayList;
import java.util.List;

public class ChartFragment extends Fragment {

    private View ChartPage;
    private RadioGroup chart_page_payment;
    private RadioGroup chart_page_date_bar;
    private TextView total_spend_inst,total_spend_number,total_list_inst;
    private TextView total_count,total_max;
    private RecyclerView listBar;
    private RecyclerView listView;

    private LinearLayoutManager linearLayoutManager1;
    private LinearLayoutManager linearLayoutManager2;

    //初始化 当前选中的item， 默认支出 月份类别
    private int currentPosition=0;
    private Boolean currentPayment=false;
    //  2 月 3 总
    private int currentDateType=2;

   /* private List<BillItem> billItems=new ArrayList<>();
    private List<BillItemForChart> billItemForChartList=new ArrayList<>();
    private List<BillItem> billItemsTemp=new ArrayList<>();
    private List<CountAndMoney> countAndMoneyList=new ArrayList<>();
    private BillItemForChart billItemForChart;
    private ChartListAdapter chartListAdapter;
    private String MonAndYear;

    private BillItemForChartAdapter billItemForChartAdapter;*/


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ChartPage= inflater.inflate(R.layout.fragment_chart,container,false);

        /*billItems=DataSupport.select().where("user_id = ?", MainActivity.getUserLogining()).order("create_date").find(BillItem.class);

        if(billItems.size()!=0){
            init();

            linearLayoutManager1=new LinearLayoutManager(getActivity());
            linearLayoutManager1.setOrientation(LinearLayoutManager.HORIZONTAL);
            listBar.setLayoutManager(linearLayoutManager1);
            billItemForChartAdapter=new BillItemForChartAdapter(billItemForChartList);
            listBar.setAdapter(billItemForChartAdapter);

            initListener();
        }*/

        init();

        return ChartPage;
    }

    public void  init(){
        chart_page_payment=(RadioGroup)ChartPage.findViewById(R.id.chart_page_payment);
        chart_page_date_bar=(RadioGroup)ChartPage.findViewById(R.id.chart_page_date_bar);
        total_spend_inst=(TextView)ChartPage.findViewById(R.id.total_spend_inst);
        total_spend_number=(TextView)ChartPage.findViewById(R.id.total_spend_number);
        total_list_inst=(TextView)ChartPage.findViewById(R.id.total_list_inst);
        total_count=(TextView)ChartPage.findViewById(R.id.total_count);
        total_max=(TextView)ChartPage.findViewById(R.id.total_max);
        listBar=(RecyclerView)ChartPage.findViewById(R.id.listBar);
        listView=(RecyclerView)ChartPage.findViewById(R.id.recycleView);

        chart_page_payment.check(R.id.spending);
        chart_page_date_bar.check(R.id.chart_date_month);


        FontHelper.injectFont(ChartPage.findViewById(R.id.rootView));

        /*billItems=DataSupport.select().where("user_id = ?",MainActivity.getUserLogining()).order("create_date").find(BillItem.class);
        if(billItems.size()!=0){
            defaultPageChartList();
            changeListByPosition(currentPosition);
        }*/
    }

}
