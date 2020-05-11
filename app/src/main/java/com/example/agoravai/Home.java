package com.example.agoravai;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.icu.text.NumberFormat;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.Map;


public class Home extends AppCompatActivity implements View.OnClickListener {

    private LinkedList<Integer> moneys;
    private int moneyAtual;
    private int moneyTotal;
    private LineChart lineChart;

    private ImageButton b_Market;
    private ImageButton b_My_Assets;
    private ImageButton b_Home;

    private TextView txtMoney;
    private TextView txtMoneyTotal;
    private TextView marketClose;
    private boolean canBuySell;


    private Main main;
    private Intent intentMarket;
    private Intent intentMyAssets;



    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        marketClose = findViewById(R.id.txt_Market_Closes);


        main = main.getInstance(this);

        intentMarket = new Intent(Home.this,Market.class);
        intentMyAssets = new Intent(Home.this,MyAssets.class);
        getDates();

        txtMoney = findViewById(R.id.txt_money_home);
        txtMoney.setText(NumberFormat.getCurrencyInstance().format(main.money));

        b_Market = findViewById(R.id.b_market);
        b_My_Assets = findViewById(R.id.b_my_assets);
        b_Home = findViewById(R.id.b_home);

        b_Market.setOnClickListener(this);
        b_My_Assets.setOnClickListener(this);
        b_Home.setOnClickListener(this);

        moneyAtual = main.getMoneyAtual();

        txtMoneyTotal = findViewById(R.id.txt_MoneyTotal);
        txtMoneyTotal.setText(NumberFormat.getCurrencyInstance().format(moneyAtual));

        moneys = main.getMoneys();

        lineChart = findViewById(R.id.linechartHome);
        initializeGraph();

        Log.d("Money", "Money Atual: "+moneyAtual);
        Log.d("Money", "Moneys: "+moneys.toString());

        main.connect();
//        try {
//            Thread.sleep(2000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

    }

    private void getDates() {

        Date now = new Date();
        Calendar calendar = Calendar.getInstance();

        calendar.setTime(now);

        if (calendar.get(Calendar.DAY_OF_WEEK)==Calendar.FRIDAY||calendar.get(Calendar.DAY_OF_WEEK)==Calendar.SUNDAY){
            marketClose.setText("Market Closed");
            marketClose.setTextColor(Color.RED);
            canBuySell=false;

        }
        else{
            marketClose.setText("Market Open");
            marketClose.setTextColor(Color.GREEN);
            canBuySell=true;
        }
        intentMarket.putExtra("canBuySell",canBuySell);
        intentMyAssets.putExtra("canBuySell",canBuySell);
    }






    private void initializeGraph() {
        ArrayList<Entry> yvalues = new ArrayList<>();

        for (int i=0;i<moneys.size();i++)
            yvalues.add(new Entry(i, moneys.get(i)));
        yvalues.add(new Entry(moneys.size(),moneyAtual));




        LineDataSet set1 = new LineDataSet(yvalues, "Money Progress");
        set1.setCircleColor(Color.RED);
        set1.setColor(Color.GREEN);
        set1.setValueTextSize(10);


        ArrayList<ILineDataSet> dataSets = new ArrayList<>();
        dataSets.add(set1);
        LineData data = new LineData(dataSets);


        lineChart.getAxisRight().setEnabled(false);
        lineChart.getXAxis().setEnabled(true);

        lineChart.getAxisLeft().setAxisMinimum(100);
        lineChart.getXAxis().setAxisMinimum(0f);

        lineChart.getDescription().setEnabled(false);
        lineChart.setDrawBorders(true);



        lineChart.setData(data);



    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.b_market:
                startActivity(intentMarket);
                overridePendingTransition(0,0);
                break;
            case R.id.b_my_assets:
                startActivity(intentMyAssets);
                overridePendingTransition(0,0);
                break;
            case R.id.b_home:
                startActivity(new Intent(Home.this,Ryan.class));
                overridePendingTransition(0,0);
                break;
//            case R.id.b_update:
//                main.connect();
//                try {
//                    Thread.sleep(2000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                startActivity(new Intent(Home.this,Home.class));
//                overridePendingTransition(0,0);

        }
    }
}
