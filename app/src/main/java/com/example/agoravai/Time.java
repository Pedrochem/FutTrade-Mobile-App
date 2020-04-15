package com.example.agoravai;

import android.util.Log;

public class Time {
    private String name;
    private int value;
    private int pos;
    private int points;

    public Time(String name, int value, int pos, int points) {
        this.name = name;
        this.value = value;
        this.pos = pos;
        this.points = points;

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

    public void setValue(int value) {
        this.value = value;
    }

    public int getPos() {
        return pos;
    }

    public void setPos(int pos) {
        this.pos = pos;
    }

    public void uptadeVitoria(int posT1, int posT2) {
        Log.d("Matches", "PosT1: "+posT1+" | PosT2: "+posT2);
        Log.d("Matches", "uptadeVitoria: "+name+ "| Value: "+value);
        if (posT1>posT2)
            value+=(((posT1-posT2)+3)/10.0)*value;
        else
            value+=(((posT1-posT2)+8)/20.0)*value;

        Log.d("Matches", "uptadeVitoria: "+name+"| Value: "+value);
    }

    public void uptadeDerrota(int pos, int pos1) {
        //TODO
        Log.d("Matches", "uptadeDerrota: "+name);
    }

    public void uptadeEmpate(int pos, int pos1) {
        //TODO
        Log.d("Matches", "uptadeDerrota: "+name);
    }
}
