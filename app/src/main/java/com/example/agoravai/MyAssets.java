package com.example.agoravai;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.icu.text.NumberFormat;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MyAssets extends AppCompatActivity implements View.OnClickListener {
    private Main main;
    private NumberFormat nf;

    private Button b_Market;
    private Button b_Home;
    private TextView txtMoney;

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

    private Button sell0;
    private Button sell1;
    private Button sell2;
    private Button sell3;
    private Button sell4;
    private Button sell5;
    private Button sell6;
    private Button sell7;

    private Button[] buttons;
    private TextView[] txts;
    private TextView[] prices;
    private ImageView[] imgs;



    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_assets);
        main = main.getInstance(this);
        nf = NumberFormat.getCurrencyInstance();

        findIds();
        configButtons();


        b_Market.setOnClickListener(this);
        b_Home.setOnClickListener(this);
    }




    @RequiresApi(api = Build.VERSION_CODES.N)
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
            default:
                main.sell(v.getTag().toString(),this);
                break;
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void findIds(){
        txtMoney = findViewById(R.id.txt_money_my_assets);

        b_Home = findViewById(R.id.b_home);
        b_Market = findViewById(R.id.b_market);


        img0 = findViewById(R.id.img0);
        img1 = findViewById(R.id.img1);
        img2 = findViewById(R.id.img2);
        img3 = findViewById(R.id.img3);
        img4 = findViewById(R.id.img4);
        img5 = findViewById(R.id.img5);
        img6 = findViewById(R.id.img6);
        img7 = findViewById(R.id.img7);

        txt0 = findViewById(R.id.my_txt_0);
        txt1 = findViewById(R.id.my_txt_1);
        txt2 = findViewById(R.id.my_txt_2);
        txt3 = findViewById(R.id.my_txt_3);
        txt4 = findViewById(R.id.my_txt_4);
        txt5 = findViewById(R.id.my_txt_5);
        txt6 = findViewById(R.id.my_txt_6);
        txt7 = findViewById(R.id.my_txt_7);

        price0 = findViewById(R.id.my_txt_price_0);
        price1 = findViewById(R.id.my_txt_price_1);
        price2 = findViewById(R.id.my_txt_price_2);
        price3 = findViewById(R.id.my_txt_price_3);
        price4 = findViewById(R.id.my_txt_price_4);
        price5 = findViewById(R.id.my_txt_price_5);
        price6 = findViewById(R.id.my_txt_price_6);
        price7 = findViewById(R.id.my_txt_price_7);

        sell0 = findViewById(R.id.sell_0);
        sell1 = findViewById(R.id.sell_1);
        sell2 = findViewById(R.id.sell_2);
        sell3 = findViewById(R.id.sell_3);
        sell4 = findViewById(R.id.sell_4);
        sell5 = findViewById(R.id.sell_5);
        sell6 = findViewById(R.id.sell_6);
        sell7 = findViewById(R.id.sell_7);

        buttons = new Button[]{sell0, sell1, sell2, sell3, sell4, sell5, sell6, sell7};
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



    @RequiresApi(api = Build.VERSION_CODES.N)
    public void updateMoney(int x){
        txtMoney.setText(nf.format(x));
    }

    public void configButtons() {


        for (int i = 0; i < buttons.length; i++) {

            if (!main.wallet.contains(buttons[i].getTag())){
                buttons[i].setBackgroundColor(Color.DKGRAY);
                buttons[i].setEnabled(false);
            }
        }
    }

}