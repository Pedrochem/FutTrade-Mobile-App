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
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MyAssets extends AppCompatActivity implements View.OnClickListener {
    private Main main;
    private NumberFormat nf;

    private ImageButton b_Market;
    private ImageButton b_Home;
    private TextView txtMoney;

    private ImageView img0;
    private ImageView img1;
    private ImageView img2;
    private ImageView img3;
    private ImageView img4;
    private ImageView img5;
    private ImageView img6;
    private ImageView img7;

    private TextView up0;
    private TextView up1;
    private TextView up2;
    private TextView up3;
    private TextView up4;
    private TextView up5;
    private TextView up6;
    private TextView up7;

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

    private boolean canBuySell;
    private Toast toast;

    private Intent intentMarket;
    private Intent intentHome;

    private Button[] buttons;
    private TextView[] txts;
    private TextView[] prices;
    private ImageView[] imgs;
    private TextView[] ups;



    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_assets);
        main = main.getInstance(this);
        nf = NumberFormat.getCurrencyInstance();

        findIds();
        configButtons();

        canBuySell = getIntent().getExtras().getBoolean("canBuySell");

        intentHome = new Intent(this,Home.class);
        intentMarket = new Intent(this,Market.class);
        intentMarket.putExtra("canBuySell",canBuySell);

        b_Market.setOnClickListener(this);
        b_Home.setOnClickListener(this);
    }




    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.b_market:
                startActivity(intentMarket);
                overridePendingTransition(0,0);
                break;
            case R.id.b_home:
                startActivity(intentHome);
                overridePendingTransition(0,0);
                break;
            default:
                if (canBuySell)
                    main.sell(v.getTag().toString(),this);
                else{
                    toast = Toast.makeText(this,"You can't sell, market is closed!", Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.CENTER,0,0);
                    toast.show();
                }
                break;
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void findIds(){
        txtMoney = findViewById(R.id.txt_money_my_assets);

        b_Home = findViewById(R.id.b_home);
        b_Market = findViewById(R.id.b_market);

        up0 = findViewById(R.id.up_0);
        up1 = findViewById(R.id.up_1);
        up2 = findViewById(R.id.up_2);
        up3 = findViewById(R.id.up_3);
        up4 = findViewById(R.id.up_4);
        up5 = findViewById(R.id.up_5);
        up6 = findViewById(R.id.up_6);
        up7 = findViewById(R.id.up_7);

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
        ups = new TextView[]{up0,up1,up2,up3,up4,up5,up6,up7};

        int dimensionInDp = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 100, getResources().getDisplayMetrics());

        for(int i=0;i<buttons.length;i++) {
            txts[i].setText(main.times[i].getName());
            buttons[i].setTag(main.times[i].getName());
            prices[i].setText(nf.format(main.times[i].getValue()));
            imgs[i].setImageDrawable(main.getImg(main.times[i]));
            imgs[i].getLayoutParams().height = dimensionInDp;
            imgs[i].getLayoutParams().width = dimensionInDp;
            imgs[i].requestLayout();
            setUp(ups[i],main.times[i].getValue(),main.times[i].getLastValue());
        }
        txtMoney.setText(nf.format(main.money));
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void setUp(TextView up, int value, int lastValue) {
        if (value-lastValue>0){
            up.setText("Up "+NumberFormat.getCurrencyInstance().format(value-lastValue));
            up.setTextColor(Color.GREEN);
        }

        else if(value-lastValue<0){
            up.setText("Down "+NumberFormat.getCurrencyInstance().format(value-lastValue));
            up.setTextColor(Color.RED);
        }

        else{
            up.setText("Up/Down"+NumberFormat.getCurrencyInstance().format(value-lastValue));
            up.setTextColor(Color.GRAY);
        }

        if (lastValue==-1){
            up.setText("");
        }

    }


    @RequiresApi(api = Build.VERSION_CODES.N)
    public void updateMoney(int x){
        txtMoney.setText(nf.format(x));
    }

    public void configButtons() {


        for (int i = 0; i < buttons.length; i++) {

            if (!main.wallet.contains(buttons[i].getTag())){
                buttons[i].setBackgroundColor(Color.parseColor("#8F8F8F"));
                buttons[i].setEnabled(false);
                buttons[i].setTextColor(Color.parseColor("#3C3939"));
            }
            else
                buttons[i].setBackgroundColor(Color.parseColor("#4CAF50"));

        }
    }

}