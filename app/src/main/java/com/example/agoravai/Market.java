package com.example.agoravai;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.icu.text.NumberFormat;
import android.os.Build;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.LinkedList;

public class Market extends AppCompatActivity implements View.OnClickListener {
    private Main main;
    private NumberFormat nf;

    private Button b_Home;
    private Button b_My_Assets;
    private TextView txtMoney;

    private TextView priceLiverpool;
    private TextView priceManchesterCity;
    private TextView priceLeicesterCity;
    private TextView priceChelsea;
    private TextView priceManchesterUnited;
    private TextView priceTottenhamHotspur;
    private TextView priceArsenal;
    private TextView priceNewcastleUnited;

    private Button buyLiverpool;
    private Button buyManchesterCity;
    private Button buyLeicesterCity;
    private Button buyChelsea;
    private Button buyManchesterUnited;
    private Button buyTottenhamHotspur;
    private Button buyArsenal;
    private Button buyNewcastleUnited;

    private Button[] buttons;



    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_market);

        main = main.getInstance(this);
        nf = NumberFormat.getCurrencyInstance();


        txtMoney = findViewById(R.id.txt_money_market);
       //-------------------------------------------------------------------------------------------
        b_Home = findViewById(R.id.b_home);
        b_My_Assets = findViewById(R.id.b_my_assets);
        //------------------------------------------------------------------------------------------
        priceLiverpool = findViewById(R.id.txt_price_liverpool);
        priceManchesterCity = findViewById(R.id.txt_price_manchesterCity);
        priceLeicesterCity = findViewById(R.id.txt_price_leicesterCity);
        priceChelsea = findViewById(R.id.txt_price_chelsea);
        priceManchesterUnited = findViewById(R.id.txt_price_manchesterUnited);
        priceTottenhamHotspur = findViewById(R.id.txt_price_tottenhamHotspur);
        priceArsenal = findViewById(R.id.txt_price_arsenal);
        priceNewcastleUnited = findViewById(R.id.txt_price_newcastleUnited);

        buyLiverpool = findViewById(R.id.buy_liverpoool);
        buyManchesterCity = findViewById(R.id.buy_manchesterCity);
        buyLeicesterCity = findViewById(R.id.buy_leicester_city);
        buyChelsea = findViewById(R.id.buy_chelsea);
        buyManchesterUnited = findViewById(R.id.buy_manchesterUntd);
        buyTottenhamHotspur = findViewById(R.id.buy_tottenhamHotspur);
        buyArsenal = findViewById(R.id.buy_arsenal);
        buyNewcastleUnited = findViewById(R.id.buy_newcastleUnited);
        buttons = new Button[]{buyLiverpool,buyManchesterCity,buyLeicesterCity,buyChelsea,buyManchesterUnited,buyTottenhamHotspur,buyArsenal,buyNewcastleUnited};


        txtMoney.setText(nf.format(main.money));
        //------------------------------------------------------------------------------------------
        priceLiverpool.setText(nf.format(main.liverpool.getValue()));
        priceManchesterCity.setText(nf.format(main.manchesterCity.getValue()));
        priceLeicesterCity.setText(nf.format(main.leicester.getValue()));
        priceChelsea.setText(nf.format(main.chelsea.getValue()));
        priceManchesterUnited.setText(nf.format(main.manchesterUntd.getValue()));
        priceTottenhamHotspur.setText(nf.format(main.tottenham.getValue()));
        priceArsenal.setText(nf.format(main.arsenal.getValue()));
        priceNewcastleUnited.setText(nf.format(main.newcastle.getValue()));

        this.configButtons();
        //------------------------------------------------------------------------------------------
        b_Home.setOnClickListener(this);
        b_My_Assets.setOnClickListener(this);
    }



    @RequiresApi(api = Build.VERSION_CODES.N)
    public void uptadeMoney(int x){
        txtMoney.setText(nf.format(x));
    }

    public void configButtons(){
        int i=0;
        for (Time t : main.wallet){
            if (main.wallet.contains(t)){
                buttons[i].setBackgroundColor(Color.RED);
                buttons[i].setEnabled(false);
                buttons[i].setTextColor(Color.parseColor("#3C3939"));
            }
            i++;
        }
    }


    @RequiresApi(api = Build.VERSION_CODES.N)
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
                main.buy(main.liverpool,this);
                break;
            case R.id.buy_manchesterCity:
                main.buy(main.manchesterCity,this);
                break;
            case R.id.buy_leicester_city:
                main.buy(main.leicester,this);
                break;
            case R.id.buy_chelsea:
                main.buy(main.chelsea,this);
                break;
            case R.id.buy_manchesterUntd:
                main.buy(main.manchesterUntd,this);
                break;
            case R.id.buy_tottenhamHotspur:
                main.buy(main.tottenham,this);
                break;
            case R.id.buy_arsenal:
                main.buy(main.arsenal,this);
                break;
            case R.id.buy_newcastleUnited:
                main.buy(main.newcastle,this);
                break;

        }
    }
}