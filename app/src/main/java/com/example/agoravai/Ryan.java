package com.example.agoravai;

import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.listener.OnChartGestureListener;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;

public class Ryan extends AppCompatActivity {
    private LineChart lineChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ryan);

//        lineChart = findViewById(R.id.linechart);
//        lineChart.setOnChartGestureListener(Ryan.this);

    }
}
