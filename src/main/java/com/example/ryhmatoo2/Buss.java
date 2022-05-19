package com.example.ryhmatoo2;

import java.util.Arrays;
import java.util.Random;

public class Buss {

    // Bussi kohtade arv, juhi nimi ning bussi mark valitakse bussile suvaliselt antud parameetritest.
    private Random r = new Random();
    private String[] juhid = {"Toomas", "Markus", "Madis", "Lutsar", "Karmo"};
    private String[] margid = {"Mercedes", "Opel", "MAN"};

    private int kohti;
    private String mark;

    @Override
    public String toString() {
        return "Buss{" +
                "r=" + r +
                ", juhid=" + Arrays.toString(juhid) +
                ", margid=" + Arrays.toString(margid) +
                ", vabu kohti=" + kohti +
                ", mark='" + mark + '\'' +
                ", juht='" + juht + '\'' +
                '}';
    }

    private String juht;

    public Buss() {
        this.kohti = r.nextInt(10,51);
        this.mark = margid[r.nextInt(0,3)];
        this.juht = juhid[r.nextInt(0,5)];
    }

    public int getKohti() {
        return kohti;
    }

    // Meetod eemaldab vabadest kohtadest ära müüdud kohad.
    public void müüKohti(int arv) {
        this.kohti -= arv;
    }
}
