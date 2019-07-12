package com.example.inspiron.jibei.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;

import com.example.inspiron.jibei.bean.AccountBean;
import com.example.inspiron.jibei.R;

import org.json.JSONArray;

import java.util.List;

public class AccountAdapter extends RecyclerView.Adapter<AccountAdapter.ItemHoleder> {

    //    private final List<AccountBean> mList;
    private final JSONArray jsonArray;
    private View inflater;
    private Context context;


//   public AccountAdapter(Context context, List<AccountBean> list) {
//        this.context = context;
//        this.mList = list;
//    }

    public AccountAdapter(Context context, JSONArray list) {
        this.context = context;
        this.jsonArray = list;
    }


    @Override
    public ItemHoleder onCreateViewHolder(ViewGroup parent, int viewType) {
        //返回的是每一项的布局
//        inflater = LayoutInflater.from(context).inflate(R.layout.item_account,parent,false);
//        RecyclerView.ViewHolder myViewHolder = new RecyclerView.ViewHolder(inflater);
//        return myViewHolder;
        return new ItemHoleder(LayoutInflater.from(context).inflate(R.layout.item_account, parent, false));
    }

    @Override
    public void onBindViewHolder(final ItemHoleder holder, final int position) {
        //将数据和控件绑定
//        super.onBindViewHolder(holder, position);
        try{
            holder.mIvAccountName.setText(jsonArray.getJSONObject(position).get("accountName").toString());
            holder.lastModefined.setText(jsonArray.getJSONObject(position).get("lastModifiedTime").toString());
            holder.createdDate.setText(jsonArray.getJSONObject(position).get("createdTime").toString());
            holder.sum.setText(jsonArray.getJSONObject(position).get("sum").toString());
        }catch (Exception e){

        }
    }


    @Override
    //返回item的个数
    public int getItemCount() {
        return jsonArray.length();
    }


    class ItemHoleder extends RecyclerView.ViewHolder {
        TextView mIvAccountName;
        TextView createdDate;
        TextView lastModefined;
        TextView sum;
        public ItemHoleder(View itemView) {
            super(itemView);
            mIvAccountName = (TextView) itemView.findViewById(R.id.tv_account_name);
            createdDate = (TextView)itemView.findViewById(R.id.tv_bandcard_type);
            lastModefined = (TextView)itemView.findViewById(R.id.tv_bandcard_id);
            sum=(TextView)itemView.findViewById(R.id.tv_amount);
        }
    }

//    class EndHoleder extends RecyclerView.ViewHolder {
//        public EndHoleder(View itemView) {
//            super(itemView);
//        }
//    }

}
