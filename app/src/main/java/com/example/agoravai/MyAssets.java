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

    private Button b_Market;
    private Button b_Home;
    private TextView txtMoney;
    private Main main;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_assets);

        main = main.getInstance(this);

        txtMoney = findViewById(R.id.txt_money_my_assets);
        txtMoney.setText(NumberFormat.getCurrencyInstance().format(main.money));


        b_Market = findViewById(R.id.b_market);
        b_Home = findViewById(R.id.b_home);

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
}