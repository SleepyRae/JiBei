package com.example.inspiron.jibei.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;

import com.example.inspiron.jibei.bean.AccountBean;
import com.example.inspiron.jibei.R;

import java.util.List;

public class AccountAdapter extends BaseRecycleAdapter {

    private final List<AccountBean> mList;

    private Context context;


    public AccountAdapter(Context context, List<AccountBean> list) {
        this.context = context;
        this.mList = list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ItemHoleder(LayoutInflater.from(context).inflate(R.layout.item_account, parent, false));
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        super.onBindViewHolder(holder, position);


        //TODO:账本


    }


    @Override
    public int getItemCount() {
        return mList.size();
    }


    class ItemHoleder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_bandcard)
        ImageView mIvBandcard;
        @BindView(R.id.tv_bandcard_name)
        TextView mTvBandcardName;
        @BindView(R.id.tv_bandcard_type)
        TextView mTvBandcardType;
        @BindView(R.id.tv_bandcard_id)
        TextView mTvBandcardId;
        @BindView(R.id.tv_transfer)
        TextView mTvTransfer;

        public ItemHoleder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            //tv = (TextView) itemView.findViewById(R.id.tv_bandcard_name);
        }
    }

    class EndHoleder extends RecyclerView.ViewHolder {
        public EndHoleder(View itemView) {
            super(itemView);
        }
    }

}
