package com.example.agoravai;

import android.util.Log;

import java.util.LinkedList;

public class Main {

    private static Main instance;

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

    int money;
    LinkedList<Time> wallet;

    public static Main getInstance(){
        if (instance == null)
            instance = new Main();
        return instance;
    }

    public Main(){
        liverpool = new Time("Liverpool", 80, 1, 82);
        manchesterCity = new Time("Manchester City", 70, 2,57);
        leicester = new Time("Leicester City", 60, 3,53);
        chelsea = new Time("Chelsea", 50, 4, 48);
        manchesterUntd = new Time("Manchester United", 40, 5, 45);
        tottenham = new Time("Tottenham Hotspur", 30, 6, 41);
        arsenal = new Time("Arsenal", 20, 7, 40);
        newcastle = new Time("Newcastle United", 10, 8,35 );

        Time[] times = new Time[]{liverpool,manchesterCity,leicester,chelsea,manchesterUntd,tottenham,arsenal,newcastle};
        wallet = new LinkedList<>();
        tabela = new Tabela(times);

        money = 100;

    }

    public void buy(Time t){

        if(money-t.getValue()<0)
            return; //ERRO

        money-=t.getValue();
        wallet.add(t);

        //UPTADATE MONEY GLOBAL
        //UPTDATE BUTTON MARKET
        //UPDATE TXT MARKET

        return;
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
