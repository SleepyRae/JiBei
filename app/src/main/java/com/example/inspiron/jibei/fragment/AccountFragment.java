package com.example.inspiron.jibei.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;
import com.example.inspiron.jibei.Extra;
import com.example.inspiron.jibei.FontHelper;
import com.example.inspiron.jibei.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import com.example.inspiron.jibei.adapter.AccountAdapter;
import com.example.inspiron.jibei.adapter.BaseRecycleAdapter;
import com.example.inspiron.jibei.bean.AccountBean;
import com.example.inspiron.jibei.ui.AccountAddActivity;
import com.github.mikephil.charting.charts.PieChart;
import mehdi.sakout.fancybuttons.FancyButton;

public class AccountFragment extends Fragment implements View.OnClickListener{

    private View AccountPage;
    private LinearLayout add_account;

    @BindView(R.id.ll_add_deposit)
    LinearLayout mLlAddDeposit;

    @Nullable
    @Override
    public View onCreateView(@Nullable LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        AccountPage= inflater.inflate(R.layout.fragment_account,container,false);
        FontHelper.injectFont(AccountPage.findViewById(R.id.rootView));

        /*billItemList = DataSupport.where("user_id = ?", MainActivity.getUserLogining()).order("create_date").find(BillItem.class);
        if(billItemList.size()!=0){
            init();
            initListener();
        }*/

        init();

        return AccountPage;
    }

    public void init() {
        add_account = (LinearLayout) AccountPage.findViewById(R.id.ll_add_deposit);
        add_account.setOnClickListener(this);

    }

    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ll_add_deposit:
                startActivity(new Intent(getActivity(), AccountAddActivity.class));
                break;

            default:
                break;
        }
    }

}
