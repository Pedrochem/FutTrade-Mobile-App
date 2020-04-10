package com.example.agoravai;

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
}
