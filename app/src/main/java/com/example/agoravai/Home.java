package com.example.agoravai;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.icu.text.NumberFormat;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class Home extends AppCompatActivity implements View.OnClickListener {

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
        }
    }
}
