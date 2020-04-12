package com.example.agoravai;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class Main {

    private static final String MESSAGE_ID = "messages_pref";
    private static Main instance;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    Context context;

    Time liverpool;
    Time manchesterCity;
    Time leicester;
    Time chelsea;
    Time manchesterUntd;
    Time tottenham;
    Time arsenal;
    Time newcastle;

    Tabela tabela;
    Match match;
    Time[] times;

    int money;
    boolean keeper = true;

    Set<String> wallet;

    public static Main getInstance(Context c){
        if (instance == null)
            instance = new Main(c);
        return instance;
    }

    public Main(Context c){
        context = c;
        sharedPreferences = context.getSharedPreferences(MESSAGE_ID,context.MODE_PRIVATE);
        editor = sharedPreferences.edit();

        liverpool = new Time("Liverpool", 80, 1, 82);
        manchesterCity = new Time("Manchester City", 70, 2,57);
        leicester = new Time("Leicester City", 60, 3,53);
        chelsea = new Time("Chelsea", 50, 4, 48);
        manchesterUntd = new Time("Manchester United", 40, 5, 45);
        tottenham = new Time("Tottenham Hotspur", 30, 6, 41);
        arsenal = new Time("Arsenal", 20, 7, 40);
        newcastle = new Time("Newcastle United", 10, 8,35 );

        times = new Time[]{liverpool,manchesterCity,leicester,chelsea,manchesterUntd,tottenham,arsenal,newcastle};

//        if (keeper){
//            editor.clear();
//            editor.commit();
//            keeper = false;
//        }

        wallet = sharedPreferences.getStringSet("Wallet",new HashSet<String>());
        money = sharedPreferences.getInt("Money",100);




        tabela = new Tabela(times);






    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void buy(Time t, Market m){

        if(money-t.getValue()<0 || wallet.contains(t)) // revisar esse wallet ai
            return; //ERRO

        wallet.add(t.getName());
        money-=t.getValue();

        m.updateMoney(money);
        uptadeSharedPreferences();
        m.configButtons();
    }



    @RequiresApi(api = Build.VERSION_CODES.N)
    public void sell(Time t, MyAssets my){
        for (String time : wallet){
             if (time.equals(t.getName())){
                 wallet.remove(t.getName());
                 money+=t.getValue();
                 my.updateMoney(money);
                 uptadeSharedPreferences();
                 my.configButtons();
                 return;
             }
        }

    }




    public void uptadeSharedPreferences(){
        editor.putInt("Money",money);
        editor.putStringSet("Wallet",wallet);
        Log.d("Wallet", "Wallet: "+wallet.toString());

        editor.commit();

    }


    public Time getLiverpool() {
        return liverpool;
    }

    public Time getManchesterCity() {
        return manchesterCity;
    }

    public Time getLeicester() {
        return leicester;
    }

    public Time getChelsea() {
        return chelsea;
    }

    public Time getManchesterUntd() {
        return manchesterUntd;
    }

    public Time getTottenham() {
        return tottenham;
    }

    public Time getArsenal() {
        return arsenal;
    }

    public Time getNewcastle() {
        return newcastle;
    }

    public Tabela getTabela() {
        return tabela;
    }


}
