package com.binhdi0111.bka.customview;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;

import java.util.ArrayList;

public class AdapterRoom extends RecyclerView.Adapter<AdapterRoom.ViewHolder>{
    MainActivity context;
    ArrayList<Room> arrayList;
    public AdapterRoom(Context context, ArrayList<Room> arrayList) {
        this.context = (MainActivity) context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Room room = arrayList.get(position);
        holder.txtRoom.setText(room.getName());
        boolean isVisibility= room.visibility;
        holder.constraintLayout.setVisibility(isVisibility ?View.VISIBLE :View.GONE );
        setUpPieChart(holder.pieChart);
        holder.txtDaGiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.view2.setVisibility(View.VISIBLE);
                holder.view1.setVisibility(View.INVISIBLE);
                holder.txtDaGiao.setTypeface(null, Typeface.BOLD);
                holder.txtDuocGiao.setTypeface(null,Typeface.NORMAL);
                holder.layout1.getContext().getResources().getDrawable(R.drawable.custom4);
                holder.layout2.getContext().getResources().getDrawable(R.drawable.custom3);
            }
        });
        holder.txtDuocGiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.view1.setVisibility(View.VISIBLE);
                holder.view2.setVisibility(View.INVISIBLE);
                holder.txtDuocGiao.setTypeface(null, Typeface.BOLD);
                holder.txtDaGiao.setTypeface(null,Typeface.NORMAL);
                holder.layout1.getContext().getResources().getDrawable(R.drawable.custom1);
                holder.layout2.getContext().getResources().getDrawable(R.drawable.custom2);
            }
        });


        if(isVisibility){
            holder.constraintLayout1.getContext().getResources().getDrawable(R.drawable.hihia);
        }else {
            holder.constraintLayout1.getContext().getResources().getDrawable(R.drawable.ahhihi);
        }
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtRoom;
        ConstraintLayout constraintLayout,constraintLayout1;
        ImageView imgDrop;
        PieChart pieChart;
        TextView txtDuocGiao,txtDaGiao;
        View view1,view2;
        ConstraintLayout layout1,layout2;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtRoom = (TextView)itemView.findViewById(R.id.textViewUser);
            constraintLayout  = (ConstraintLayout) itemView.findViewById(R.id.constraintLayoutRecy);
            imgDrop = (ImageView) itemView.findViewById(R.id.imageViewDrop);
            constraintLayout1 = (ConstraintLayout) itemView.findViewById(R.id.constraintLayout10);
            pieChart = (PieChart)itemView.findViewById(R.id.piechart2);
            view1 = (View)itemView.findViewById(R.id.viewA);
            view2 = (View)itemView.findViewById(R.id.viewB);
            layout1 = (ConstraintLayout)itemView.findViewById(R.id.constraintA);
            layout2 = (ConstraintLayout)itemView.findViewById(R.id.constraintB);
            txtDuocGiao = (TextView)itemView.findViewById(R.id.textViewA);
            txtDaGiao = (TextView)itemView.findViewById(R.id.textViewB);

            imgDrop.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Room room = arrayList.get(getAdapterPosition());
                    room.setVisibility(!room.isVisibility());
                    notifyItemChanged(getAdapterPosition());
                }
            });
        }

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
    }
}
