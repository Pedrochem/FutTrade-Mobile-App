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

public class MyAssets extends AppCompatActivity implements View.OnClickListener {
    private Main main;

    private Button b_Market;
    private Button b_Home;
    private TextView txtMoney;

    private TextView priceLiverpool;
    private TextView priceManchesterCity;
    private TextView priceLeicesterCity;
    private TextView priceChelsea;
    private TextView priceManchesterUnited;
    private TextView priceTottenhamHotspur;
    private TextView priceArsenal;
    private TextView priceNewcastleUnited;

    private Button sell_Liverpool;
    private Button sell_ManchesterCity;
    private Button sell_LeicesterCity;
    private Button sell_Chelsea;
    private Button sell_ManchesterUnited;
    private Button sell_TottenhamHotspur;
    private Button sell_Arsenal;
    private Button sell_NewcastleUnited;

    private TextView up_liverpool;
    private TextView up_manchesterCity;
    private TextView up_leicesterCity;
    private TextView up_chelsea;
    private TextView up_manchesterUnited;
    private TextView up_tottenhamHotspur;
    private TextView up_arsenal;
    private TextView up_newcastleUnited;



    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_assets);

        main = main.getInstance(this);


        findIds();
        txtMoney.setText(NumberFormat.getCurrencyInstance().format(main.money));


        b_Market.setOnClickListener(this);
        b_Home.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.b_market:
                startActivity(new Intent(MyAssets.this,Market.class));
                overridePendingTransition(0,0);
                break;
            case R.id.b_home:
                startActivity(new Intent(MyAssets.this,Home.class));
                overridePendingTransition(0,0);
                break;
        }
    }

    public void findIds(){
        b_Market = findViewById(R.id.b_market);
        b_Home = findViewById(R.id.b_home);
        txtMoney = findViewById(R.id.txt_money_my_assets);

        priceLiverpool = findViewById(R.id.txt_price_liverpool);
        priceManchesterCity = findViewById(R.id.txt_price_manchesterCity);
        priceLeicesterCity = findViewById(R.id.txt_price_leicesterCity);
        priceChelsea = findViewById(R.id.txt_price_chelsea);
        priceManchesterUnited = findViewById(R.id.txt_price_manchesterUnited);
        priceTottenhamHotspur = findViewById(R.id.txt_price_tottenhamHotspur);
        priceArsenal = findViewById(R.id.txt_price_arsenal);
        priceNewcastleUnited = findViewById(R.id.txt_price_newcastleUnited);

        sell_Liverpool = findViewById(R.id.sell_liverpoool);
        sell_ManchesterCity = findViewById(R.id.sell_manchesterCity);
        sell_LeicesterCity = findViewById(R.id.sell_LeicesterCity);
        sell_Chelsea = findViewById(R.id.sell_chelsea);
        sell_ManchesterUnited = findViewById(R.id.sell_manchesterUnited);
        sell_TottenhamHotspur = findViewById(R.id.sell_tottenhamHotspur);
        sell_Arsenal = findViewById(R.id.sell_arsenal);
        sell_NewcastleUnited = findViewById(R.id.sell_newcastleUnited);

        up_liverpool = findViewById(R.id.up_liverpool);
        up_manchesterCity = findViewById(R.id.up_manchesterCity);
        up_leicesterCity = findViewById(R.id.up_leicesterCity);
        up_chelsea = findViewById(R.id.up_chelsea);
        up_manchesterUnited = findViewById(R.id.up_manchesterUnited);
        up_tottenhamHotspur = findViewById(R.id.up_tottenhamHotspur);
        up_arsenal = findViewById(R.id.up_arsenal);
        up_newcastleUnited = findViewById(R.id.up_newcastleUnited);
    }
}