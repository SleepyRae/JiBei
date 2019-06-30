package com.example.inspiron.jibei.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.example.inspiron.jibei.FontHelper;
import com.example.inspiron.jibei.R;
import com.example.inspiron.jibei.model.ProvinceBean;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import mehdi.sakout.fancybuttons.FancyButton;

import java.util.ArrayList;

public class ChartFragment extends Fragment {

    private View CardPage;
    private TextView page_card_current_payment,page_card_listName;
    private TextView page_card_current_time;
    private PieChart mPieChart;
    private RecyclerView page_card_listView;
    private FancyButton page_card_selector, page_card_change_payment;

    private OptionsPickerView pvOptions;
    private ArrayList<ProvinceBean> options1Items = new ArrayList<>();
    private ArrayList<ArrayList<String>> options2Items = new ArrayList<>();

/*    private List<BillItem> billItemList = new ArrayList<>();*/

    private String currentSelectorTime;

    //构建一个当月的类实例，包含各类信息
/*    private BillItemForCard billItemForCard;*/
    private String currentYear, currentMonth;
    private Boolean currentePayment;
    private String currentMoneyType;

    private PieDataSet pieDataSet;
    private PieData pieData;

    private LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getActivity());
/*    private BillItemForCardAdapter billItemForCardAdapter;
    private List<BillItem> recycleViewList=new ArrayList<>();*/


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        CardPage = inflater.inflate(R.layout.fragment_chart, container, false);
        /*billItemList = DataSupport.where("user_id = ?", MainActivity.getUserLogining()).order("create_date").find(BillItem.class);
        if(billItemList.size()!=0){
            init();
            initListener();
        }*/

        init();

        return CardPage;
    }

    public void init() {
        page_card_selector = (FancyButton) CardPage.findViewById(R.id.page_card_selector);
        page_card_change_payment = (FancyButton) CardPage.findViewById(R.id.page_card_change_payment);
        mPieChart = (PieChart) CardPage.findViewById(R.id.mPieChart);
        page_card_listView = (RecyclerView) CardPage.findViewById(R.id.page_card_listView);
        page_card_current_time = (TextView) CardPage.findViewById(R.id.page_card_current_time);

        page_card_current_payment = (TextView) CardPage.findViewById(R.id.page_card_current_payment);
        page_card_listName=(TextView)CardPage.findViewById(R.id.page_card_listName);

        //字体图标渲染
        FontHelper.injectFont(CardPage.findViewById(R.id.rootView));

        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

       /* initSetCurrentDate();

        initSelectorDate();
*/
    }

}