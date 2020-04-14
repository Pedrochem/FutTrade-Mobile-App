package com.example.agoravai;

public class Match {
    private int matchId;
    private String time1;
    private String time2;
    private int score1;
    private int score2;
    Main main;
    Time[] times;

    public Match(int matchId, String time1, int score1,String time2, int score2) {
        this.matchId = matchId;
        this.time1 = time1;
        this.time2 = time2;
        this.score1 = score1;
        this.score2 = score2;
    }

    public void process(){
        int t1=-1;
        int t2=-1;
        main = Main.getInstance();
        times = main.times;

        for (int i=0;i<times.length;i++){
            if (times[i].getName().equals(time1))
                t1 = i;
            else if (times[i].getName().equals(time2))
                t2= i;
        }

        int res = score1-score2;
        if (res>=1){
            main.times[t1].uptadeVitoria(main.times[t1].getPos(),main.times[t2].getPos());
            main.times[t2].uptadeDerrota(main.times[t2].getPos(),main.times[t1].getPos());
        }
        else if (res==0) {
            main.times[t1].uptadeEmpate(main.times[t1].getPos(), main.times[t2].getPos());
            main.times[t2].uptadeEmpate(main.times[t2].getPos(), main.times[t1].getPos());
        }
        else{
            main.times[t1].uptadeDerrota(main.times[t1].getPos(),main.times[t2].getPos());
            main.times[t2].uptadeVitoria(main.times[t2].getPos(),main.times[t1].getPos());
        }

    }

    public int getMatchId() {
        return matchId;
    }

    public String getTime1() {
        return time1;
    }

    public String getTime2() {
        return time2;
    }

    public int getScore1() {
        return score1;
    }

    public int getScore2() {
        return score2;
    }
}
