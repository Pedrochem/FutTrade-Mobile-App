package com.example.agoravai;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.icu.text.NumberFormat;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Market extends AppCompatActivity implements View.OnClickListener {

    private Button b_Home;
    private Button b_My_Assets;
    private TextView txtMoney;

    private Main main;



    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_market);

        main = main.getInstance();

        txtMoney = findViewById(R.id.txt_money_market);
        txtMoney.setText(NumberFormat.getCurrencyInstance().format(main.money));

        b_Home = findViewById(R.id.b_home);
        b_My_Assets = findViewById(R.id.b_my_assets);



        b_Home.setOnClickListener(this);
        b_My_Assets.setOnClickListener(this);
    }



    public void buy(Time t){
        main.buy(t);
    }



    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.b_home:
                startActivity(new Intent(Market.this,Home.class));
                overridePendingTransition(0,0);
                break;
            case R.id.b_my_assets:
                startActivity(new Intent(Market.this,MyAssets.class));
                overridePendingTransition(0,0);
                break;
            case R.id.buy_liverpoool:
                main.buy(main.liverpool);
                break;
        }
    }
}