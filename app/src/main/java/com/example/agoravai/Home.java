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
import android.widget.TextView;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Map;


public class Home extends AppCompatActivity implements View.OnClickListener {

    private LinkedList<Integer> moneys;
    private int moneyAtual;
    private LineChart lineChart;

    private Button b_Market;
    private Button b_My_Assets;
    private Button b_Home;

    private TextView txtMoney;


    private Main main;
    private Intent intentMarket;



    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Log.d("Home", "onCreate: CREATED HOME");

        main = main.getInstance(this);

        intentMarket = new Intent(Home.this,Market.class);

        txtMoney = findViewById(R.id.txt_money_home);
        txtMoney.setText(NumberFormat.getCurrencyInstance().format(main.money));

        b_Market = findViewById(R.id.b_market);
        b_My_Assets = findViewById(R.id.b_my_assets);
        b_Home = findViewById(R.id.b_home);

        b_Market.setOnClickListener(this);
        b_My_Assets.setOnClickListener(this);
        b_Home.setOnClickListener(this);

        moneyAtual = main.getMoneyAtual();
        moneys = main.getMoneys();
        lineChart = findViewById(R.id.linechartHome);
        initializeGraph();

        Log.d("Money", "Money Atual: "+moneyAtual);
        Log.d("Money", "Moneys: "+moneys.toString());

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


        lineChart.getAxisLeft().setEnabled(true);
        lineChart.getXAxis().setEnabled(false);
        lineChart.getAxisRight().setEnabled(false);
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
                startActivity(new Intent(Home.this,MyAssets.class));
                overridePendingTransition(0,0);
                break;
            case R.id.b_home:
                startActivity(new Intent(Home.this,Ryan.class));
                overridePendingTransition(0,0);
                break;
            case R.id.b_update:
                main.connect();
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                startActivity(new Intent(Home.this,Home.class));
                overridePendingTransition(0,0);

                break;

        }
    }
}
