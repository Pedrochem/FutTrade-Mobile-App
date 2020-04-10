package com.example.agoravai;

public class Match {
    private Time time1;
    private Time time2;
    private Time vencedor;


    public Match(Time time1, Time time2, Time vencedor) {
        this.time1 = time1;
        this.time2 = time2;
        this.vencedor = vencedor;
    }

    public Time getTime1() {
        return time1;
    }

    public void setTime1(Time time1) {
        this.time1 = time1;
    }

    public Time getTime2() {
        return time2;
    }

    public void setTime2(Time time2) {
        this.time2 = time2;
    }

    public Time getVencedor() {
        return vencedor;
    }

    public void setVencedor(Time vencedor) {
        this.vencedor = vencedor;
    }


}
