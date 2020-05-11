package com.example.agoravai;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

public class Time implements Comparable<Time> {
    private String name;
    private int value;
    private int lastValue;
    private int pos;
    private int points;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    public Time(String name, int value, int pos, int points,Context c) {
        sharedPreferences = c.getSharedPreferences("messages_pref", c.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        this.name = name;
        this.value = sharedPreferences.getInt(name+"value",value);
        lastValue = sharedPreferences.getInt(name+"lastValue",-1);
        this.pos = pos;
        this.points = sharedPreferences.getInt(name+"points",points);


    }

    public void save(){
        editor.putInt(name+"points",points);
        editor.putInt(name+"value",value);
        editor.putInt(name+"lastValue",lastValue);
        editor.commit();
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getValue() {
        return value;
    }

    public int getLastValue(){ return lastValue; }

    public void setValue(int value) {
        this.value = value;
    }

    public int getPos() {
        return pos;
    }

    public int getPoints() {return points;}

    public void setPos(int pos) {
        this.pos = pos;
    }




    public void uptadeVitoria(int posT1, int posT2) {
        Log.d("Matches", "PosT1: "+posT1+" | PosT2: "+posT2);
        Log.d("Matches", "uptadeVitoria: "+name+ "| Antigo value: "+value);
        lastValue = value;

        if (posT1>posT2)
            value+=(((posT1-posT2)+3)/10.0)*value;
        else
            value+=(((posT1-posT2)+8)/20.0)*value;
        points+=3;

        Log.d("Matches", "uptadeVitoria: "+name+"| Value: "+value);
        Log.d("Last value", name+"Last Value: "+lastValue);
    }



    public void uptadeDerrota(int posT1, int posT2) {
        Log.d("Matches", "PosT1: "+posT1+" | PosT2: "+posT2);
        Log.d("Matches", "updateDerrota: "+name+ "| Antigo value: "+value);
        lastValue = value;

        if (posT2>posT1)
            value-=(((posT2-posT1)+3)/10.0)*value;
        else
            value-=(((posT2-posT1)+8)/20.0)*value;
        points-=3;

        Log.d("Matches", "uptadeDerrota: "+name+"| Value: "+value);
        Log.d("Last value", name+"Last Value: "+lastValue);
    }

    public void uptadeEmpate(int posT1, int posT2) {
        Log.d("Matches", "uptadeEmpate: "+name);
        Log.d("Matches", "updateEmpate: "+name+ "| Antigo value: "+value);
        lastValue = value;

        if(posT1>posT2)
            value+=0.1*value;
        else
            value-=0.1*value;
        points+=1;
        Log.d("Matches", "uptadeEmpate: "+name+"| Value: "+value);
        Log.d("Last value", name+"Last Value: "+lastValue);
    }

    @Override
    public int compareTo(Time o) {
        return o.value - this.value;
    }
}
