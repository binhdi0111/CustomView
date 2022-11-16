package com.binhdi0111.bka.customview;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;

import java.util.ArrayList;
import java.util.Calendar;

public class Fragment_Dashboard extends Fragment implements OnChartValueSelectedListener {
    private Calendar calendar;
    TextView txtTime,txtDuocGiao,txtDaGiao;
    public static PieChart pieChart,pieChart2;
    ArrayList<String> arrayList1,arrayList,arrayList2,arrayList3,arrayList4;
    Spinner spinner,spinner1,spinner2,spinner3,spinner4;
    ArrayAdapter<String> adapter,adapter1,adapter2,adapter3,adapter4;
    View view1,view2;
    ConstraintLayout layout1,layout2;
    ImageView imgDashboard,imgJob,imgNotify,imgUser,imgTaoCongViec;
    RecyclerView recyclerView;
    ArrayList<Room> arrayListRoom;
    AdapterRoom adapterRoom;


    @SuppressLint("MissingInflatedId")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dashboard,container,false);
        txtTime = (TextView)view.findViewById(R.id.textViewTime);
        imgTaoCongViec =(ImageView)view.findViewById(R.id.imageView8) ;
        txtDuocGiao = (TextView) view.findViewById(R.id.textView18);
        view1 = (View)view.findViewById(R.id.view1);
        txtDaGiao = (TextView) view.findViewById(R.id.textView26);
        view2 = (View) view.findViewById(R.id.view2);
        layout1 = (ConstraintLayout)view.findViewById(R.id.layout1);
        layout2 = (ConstraintLayout)view.findViewById(R.id.layou2);
        pieChart = (PieChart) view.findViewById(R.id.piechart);
        setUpPieChart(pieChart);
//        pieChart2 = (PieChart)view.findViewById(R.id.piechart2);
//        setUpPieChart(pieChart2);
        ChonNgay();
        imgTaoCongViec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),SecondActivity.class);
                startActivity(intent);
            }
        });

        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        InitData();
        InitRecyclerView();


        arrayList1 = new ArrayList<String>();
        arrayList1.add("Tháng 1/2022");
        arrayList1.add("Tháng 2/2022");
        arrayList1.add("Tháng 3/2022");
        arrayList1.add("Tháng 4/2022");
        this.spinner = (Spinner) view.findViewById(R.id.spinnerA);
        adapter = new ArrayAdapter(this.getActivity(),R.layout.spin,arrayList1);
        this.spinner.setAdapter(adapter);

        arrayList = new ArrayList<String>();
        arrayList.add("Từ 19-26/09/2022");
        arrayList.add("Từ 11-18/09/2022");
        arrayList.add("Từ 03-10/09/2022");
        arrayList.add("Từ 27-02/10/2022");
        this.spinner1 = (Spinner) view.findViewById(R.id.spinnerA1);
        adapter1 = new ArrayAdapter<String>(this.getActivity(),R.layout.spin,arrayList);
        this.spinner1.setAdapter(adapter1);


        this.spinner2 = (Spinner) view.findViewById(R.id.spinnerB);
        adapter2 = new ArrayAdapter(this.getActivity(),R.layout.spin,arrayList1);
        this.spinner2.setAdapter(adapter2);


        this.spinner3 = (Spinner) view.findViewById(R.id.spinnerB1);
        adapter3 = new ArrayAdapter<String>(this.getActivity(),R.layout.spin,arrayList);
        this.spinner3.setAdapter(adapter3);

        arrayList4 = new ArrayList<String>();
        arrayList4.add("Tất cả");
        arrayList4.add("hihi");
        arrayList4.add("haha");
        arrayList4.add("hoho");
        this.spinner4 = (Spinner) view.findViewById(R.id.spinnerC);
        adapter4 = new ArrayAdapter<String>(this.getActivity(),R.layout.spin,arrayList4);
        this.spinner4.setAdapter(adapter4);

        txtDuocGiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SelectTabIndex(0);
            }
        });

        txtDaGiao.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View view) {
                SelectTabIndex(1);
            }
        });
        return view;
    }
    public void InitData(){
        arrayListRoom = new ArrayList<Room>();
        arrayListRoom.add(new Room("Phòng Văn hóa-thông tin"));
        arrayListRoom.add(new Room("Văn phòng HĐNH-UBND huyện"));
        arrayListRoom.add(new Room("Phòng Tài chính kế hoạch"));
        arrayListRoom.add(new Room("Phòng Tư pháp"));
    }
    public void InitRecyclerView(){
//        recyclerView.setHasFixedSize(true);
        space itemDecorato = new space(20);
        recyclerView.addItemDecoration(itemDecorato);
        adapterRoom = new AdapterRoom(getActivity(),arrayListRoom);
        recyclerView.setAdapter(adapterRoom);
    }


    private void setUpPieChart(PieChart pieChart) {
        ArrayList<PieEntry> yEntrys = new ArrayList<>();
        ArrayList<String> xEntrys = new ArrayList<>();
        float[] yData = { 10, 20, 30,30 ,10};
        String[] xData = { "", "", "","","" };

        for (int i = 0; i < yData.length;i++){
            yEntrys.add(new PieEntry(yData[i],i));
        }
        for (int i = 0; i < xData.length;i++){
            xEntrys.add(xData[i]);
        }

        PieDataSet pieDataSet=new PieDataSet(yEntrys,"");
        pieDataSet.setSliceSpace(2);
        pieDataSet.setValueTextSize(12);

        ArrayList<Integer> colors=new ArrayList<>();
        colors.add(Color.rgb(238,235,235));
        colors.add(Color.rgb(76,166,65));
        colors.add(Color.rgb(236,156,82));
        colors.add(Color.rgb(58,119,252));
        colors.add(Color.rgb(226,93,91));

        pieDataSet.setColors(colors);
        Legend legend=pieChart.getLegend();
        legend.setForm(Legend.LegendForm.CIRCLE);
        legend.setPosition(Legend.LegendPosition.LEFT_OF_CHART);

        PieData pieData=new PieData(pieDataSet);
        pieData.setDrawValues(false);
        pieChart.setData(pieData);
        pieChart.invalidate();
        pieChart.setRotationEnabled(true);
        pieChart.setDescription(new Description());
        pieChart.setHoleRadius(35f);
        pieChart.setTransparentCircleAlpha(0);
        pieChart.setCenterText("Tổng\n"+10);
        pieChart.setCenterTextSize(10);
        pieChart.setDrawEntryLabels(true);
        pieChart.getLegend().setEnabled(false);
        pieChart.getDescription().setEnabled(false);
        pieChart.setDrawSliceText(false);
        float x = 70;
        pieChart.setDrawHoleEnabled(true);
        pieChart.setHoleRadius(x);
        pieChart.setOnChartValueSelectedListener(this);

    }

    private void ChonNgay(){
        calendar = Calendar.getInstance();
        int ngay = calendar.get(Calendar.DATE);
        int thang = calendar.get(Calendar.MONTH);
        int nam = calendar.get(Calendar.YEAR);
        int phut = calendar.get(Calendar.MINUTE);
        int gio = calendar.get(Calendar.HOUR);
        txtTime.setText(gio +":"+phut+"/"+ngay+"/"+thang+"/"+nam);
    }

    @Override
    public void onValueSelected(Entry e, Highlight h) {
    }

    @Override
    public void onNothingSelected() {

    }
    public void SelectTabIndex(int index){

        switch (index){
            case 0:
                view1.setVisibility(View.VISIBLE);
                view2.setVisibility(View.INVISIBLE);
                txtDuocGiao.setTypeface(null, Typeface.BOLD);
                txtDaGiao.setTypeface(null,Typeface.NORMAL);
                layout1.setBackground(getResources().getDrawable(R.drawable.custom1));
                layout2.setBackground(getResources().getDrawable(R.drawable.custom2));
                break;
            case 1:
                view2.setVisibility(View.VISIBLE);
                view1.setVisibility(View.INVISIBLE);
                txtDaGiao.setTypeface(null, Typeface.BOLD);
                txtDuocGiao.setTypeface(null,Typeface.NORMAL);
                layout1.setBackground(getResources().getDrawable(R.drawable.custom4));
                layout2.setBackground(getResources().getDrawable(R.drawable.custom3));
                break;
            default:
                view1.setVisibility(View.VISIBLE);
                view2.setVisibility(View.INVISIBLE);
                txtDuocGiao.setTypeface(null, Typeface.BOLD);
                txtDaGiao.setTypeface(null,Typeface.NORMAL);
                layout1.setBackground(getResources().getDrawable(R.drawable.custom1));
                layout2.setBackground(getResources().getDrawable(R.drawable.custom2));
                break;

        }
    }
}
