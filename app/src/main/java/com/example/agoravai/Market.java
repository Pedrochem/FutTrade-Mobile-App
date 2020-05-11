package com.example.agoravai;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.icu.text.NumberFormat;
import android.media.Image;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


public class Market extends AppCompatActivity implements View.OnClickListener {
    private Main main;
    private NumberFormat nf;

    private Intent intentMyAssets;
    private Intent intentHome;

    private ImageButton b_Home;
    private ImageButton b_My_Assets;

    private TextView txtMoney;
    private boolean canBuySell;
    private Toast toast;
    private ImageView img0;
    private ImageView img1;
    private ImageView img2;
    private ImageView img3;
    private ImageView img4;
    private ImageView img5;
    private ImageView img6;
    private ImageView img7;

    private TextView txt0;
    private TextView txt1;
    private TextView txt2;
    private TextView txt3;
    private TextView txt4;
    private TextView txt5;
    private TextView txt6;
    private TextView txt7;

    private TextView price0;
    private TextView price1;
    private TextView price2;
    private TextView price3;
    private TextView price4;
    private TextView price5;
    private TextView price6;
    private TextView price7;

    private Button buy0;
    private Button buy1;
    private Button buy2;
    private Button buy3;
    private Button buy4;
    private Button buy5;
    private Button buy6;
    private Button buy7;

    private Button[] buttons;
    private TextView[] txts;
    private TextView[] prices;
    private ImageView[] imgs;




    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_market);
        main = Main.getInstance(this);
        nf = NumberFormat.getCurrencyInstance();

        canBuySell = getIntent().getExtras().getBoolean("canBuySell");

        intentHome = new Intent(this,Home.class);
        intentMyAssets = new Intent(this,MyAssets.class);
        intentMyAssets.putExtra("canBuySell",canBuySell);


        findIds();
        configButtons();

        b_Home.setOnClickListener(this);
        b_My_Assets.setOnClickListener(this);
    }



    @RequiresApi(api = Build.VERSION_CODES.N)
    public void updateMoney(int x){
        txtMoney.setText(nf.format(x));
    }

    public void configButtons() {

        for (int i = 0; i < buttons.length; i++) {
            if (main.wallet.contains(buttons[i].getTag())||main.times[i].getValue()>main.money){
                buttons[i].setBackgroundColor(Color.parseColor("#8F8F8F"));
                buttons[i].setEnabled(false);
                buttons[i].setTextColor(Color.parseColor("#3C3939"));
            }
        }
    }


    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.b_home:
                startActivity(intentHome);
                overridePendingTransition(0,0);
                break;
            case R.id.b_my_assets:
                startActivity(intentMyAssets);
                overridePendingTransition(0,0);
                break;

            default:
                if (canBuySell)
                    main.buy(v.getTag().toString(),this);
                else{
                    toast = Toast.makeText(this,"You can't buy, market is closed!", Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.CENTER,0,0);
                    toast.show();
                }
                break;

        }
    }


    @RequiresApi(api = Build.VERSION_CODES.N)
    public void findIds(){
        txtMoney = findViewById(R.id.txt_money_market);

        b_Home = findViewById(R.id.b_home);
        b_My_Assets = findViewById(R.id.b_my_assets);

        img0 = findViewById(R.id.ma_img0);
        img1 = findViewById(R.id.ma_img1);
        img2 = findViewById(R.id.ma_img2);
        img3 = findViewById(R.id.ma_img3);
        img4 = findViewById(R.id.ma_img4);
        img5 = findViewById(R.id.ma_img5);
        img6 = findViewById(R.id.ma_img6);
        img7 = findViewById(R.id.ma_img7);

        txt0 = findViewById(R.id.txt_0);
        txt1 = findViewById(R.id.txt_1);
        txt2 = findViewById(R.id.txt_2);
        txt3 = findViewById(R.id.txt_3);
        txt4 = findViewById(R.id.txt_4);
        txt5 = findViewById(R.id.txt_5);
        txt6 = findViewById(R.id.txt_6);
        txt7 = findViewById(R.id.txt_7);

        price0 = findViewById(R.id.txt_price_0);
        price1 = findViewById(R.id.txt_price_1);
        price2 = findViewById(R.id.txt_price_2);
        price3 = findViewById(R.id.txt_price_3);
        price4 = findViewById(R.id.txt_price_4);
        price5 = findViewById(R.id.txt_price_5);
        price6 = findViewById(R.id.txt_price_6);
        price7 = findViewById(R.id.txt_price_7);

        buy0 = findViewById(R.id.buy_0);
        buy1 = findViewById(R.id.buy_1);
        buy2 = findViewById(R.id.buy_2);
        buy3 = findViewById(R.id.buy_3);
        buy4 = findViewById(R.id.buy_4);
        buy5 = findViewById(R.id.buy_5);
        buy6 = findViewById(R.id.buy_6);
        buy7 = findViewById(R.id.buy_7);

        buttons = new Button[]{buy0, buy1, buy2, buy3, buy4, buy5, buy6, buy7};
        txts = new TextView[]{txt0,txt1,txt2,txt3,txt4,txt5,txt6,txt7};
        prices = new TextView[]{price0,price1,price2,price3,price4,price5,price6,price7};
        imgs = new ImageView[]{img0,img1,img2,img3,img4,img5,img6,img7};
        int dimensionInDp = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 100, getResources().getDisplayMetrics());


        for(int i=0;i<buttons.length;i++) {
            txts[i].setText(main.times[i].getName());
            buttons[i].setTag(main.times[i].getName());
            prices[i].setText(nf.format(main.times[i].getValue()));
            imgs[i].setImageDrawable(main.getImg(main.times[i]));
            imgs[i].getLayoutParams().height = dimensionInDp;
            imgs[i].getLayoutParams().width = dimensionInDp;
            imgs[i].requestLayout();
        }

        txtMoney.setText(nf.format(main.money));
    }

}