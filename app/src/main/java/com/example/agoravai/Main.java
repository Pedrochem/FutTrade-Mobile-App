package com.example.agoravai;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Jsoup;

import org.jsoup.nodes.Document;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;


public class Main {

    private static final String MESSAGE_ID = "messages_pref";
    private static Main instance;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    int lastMatchId;

    Context context;

    Time liverpool;
    Time manchesterCity;
    Time leicester;
    Time chelsea;
    Time manchesterUntd;
    Time tottenham;
    Time arsenal;
    Time newcastle;


    List<Match> matches;
    Time[] times;

    int money;
    boolean keeper = true;

    Set<String> wallet;
    LinkedList<Integer> moneys;
    int moneyAtual;


    public static Main getInstance(Context c){
        if (instance == null)
            instance = new Main(c);
        return instance;
    }
    public static Main getInstance(){
        return instance;
    }

    public Main(Context c){

        context = c;
        sharedPreferences = context.getSharedPreferences(MESSAGE_ID,context.MODE_PRIVATE);
        editor = sharedPreferences.edit();


        if (keeper){
            editor.clear();
            editor.commit();
            keeper = false;
        }


        liverpool = new Time("Liverpool", 80, 1, 82,context);
        manchesterCity = new Time("Manchester City", 70, 2,57,context);
        leicester = new Time("Leicester City", 60, 3,53,context);
        chelsea = new Time("Chelsea", 50, 4, 48,context);
        manchesterUntd = new Time("Manchester United", 40, 5, 45,context);
        tottenham = new Time("Tottenham Hotspur", 30, 6, 41,context);
        arsenal = new Time("Arsenal", 20, 7, 40,context);
        newcastle = new Time("Newcastle United", 10, 8,35,context );

        times = new Time[]{liverpool,manchesterCity,leicester,chelsea,manchesterUntd,tottenham,arsenal,newcastle};

        wallet = sharedPreferences.getStringSet("Wallet",new HashSet<String>());

        money = sharedPreferences.getInt("Money",100);
        lastMatchId = sharedPreferences.getInt("LastMatchId",0);
        moneys = getMoneys();
        moneyAtual = getMoneyAtual();

    }
    public int getMoneyAtual(){
        int m= money;
        for (String t : wallet){
            for (int i=0;i<times.length;i++){
                if (times[i].getName().equals(t))
                    m+=times[i].getValue();
            }
        }
        return m;
    }

    public void connect() {
        Doit doit = new Doit();
        doit.execute();

    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void buy(String name, Market m) {
        Time t=null;
        for (int i = 0; i < times.length; i++) {
            if (times[i].getName().equals(name))
                t = times[i];
        }

        if(money-t.getValue()<0 || wallet.contains(t.getName())) // revisar esse wallet ai
            return; //ERRO

        wallet.add(t.getName());
        money-=t.getValue();

        m.updateMoney(money);
        uptadeSharedPreferences();
        m.configButtons();
    }



    @RequiresApi(api = Build.VERSION_CODES.N)
    public void sell(String name, MyAssets my){
        Time t=null;
        for (int i = 0; i < times.length; i++) {
            if (times[i].getName().equals(name))
                t = times[i];
        }
         if (wallet.contains(name)){
             wallet.remove(name);
             money+=t.getValue();
             my.updateMoney(money);
             uptadeSharedPreferences();
             my.configButtons();
             return;
         }
    }



    public void uptadeSharedPreferences(){
        editor.putInt("Money",money);
        editor.putStringSet("Wallet",wallet);
        editor.putInt("LastMatchId",lastMatchId);
        editor.putString("Moneys",convertMoneysString());


        Log.d("Wallet", "Wallet: "+wallet.toString());

        editor.commit();
    }

    public String convertMoneysString() {
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < moneys.size(); i++) {
            str.append(moneys.get(i)).append(",");
        }
        return str.toString();
    }
    public LinkedList<Integer> getMoneys(){
        String savedString = sharedPreferences.getString("Moneys", "");
        StringTokenizer st = new StringTokenizer(savedString, ",");
        moneys = new LinkedList<>();
        while (st.hasMoreTokens()){
            moneys.add(Integer.parseInt(st.nextToken()));
        }
        return moneys;
    }



    public void updatePos(){
        Arrays.sort(times);
        for (int i=0;i<times.length;i++){
            Log.d("Times", i+": "+times[i].getName()+" | Pts: "+times[i].getPoints());
            times[i].save();
        }
    }


    public class Doit extends AsyncTask<Void,Void,Void>{
        Gson gson = new Gson();
        String json1;
        Type type = new TypeToken<List<Match>>() {}.getType();


        @Override
        protected Void doInBackground(Void... voids) {
            try {
                Document doc1 = Jsoup.connect("http://dontpad.com/ordepncl/mobile").get();
                json1 = doc1.body().text() ;
                json1 = json1.trim().substring(41);

                Log.d("Jsoup", "DeuBom:"+json1);

                matches = gson.fromJson(json1,type);

                if (matches.get(0).getMatchId() != lastMatchId){
                    Log.d("Matches", "We need to update!");
                    Log.d("Matches", "Antigo: "+lastMatchId+ " | Atual: "+matches.get(0).getMatchId() );

                    moneys.add(getMoneyAtual());
                    lastMatchId = matches.get(0).getMatchId();
                    uptadeSharedPreferences();
                    for (Match m:matches)
                        m.process();
                    updatePos();


                }
                else {
                    Log.d("Matches", "We didn't need to update!");
                }

            } catch (IOException e) {
                Log.d("Jsoup", "Deu ruim");
                e.printStackTrace();
            }
            return null;
        }
    }
}
