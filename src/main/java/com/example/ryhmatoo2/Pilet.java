package com.example.ryhmatoo2;

public class Pilet {

    private String lahtepeatus;
    private String sihtpeatus;
    private double soiduAlgus;
    private double soiduLopp;
    private double hind;
    private Buss buss;

    public String getLahtepeatus() {
        return lahtepeatus;
    }

    public String getSihtpeatus() {
        return sihtpeatus;
    }

    public Pilet(String lahtepeatus, String sihtpeatus, double soiduAlgus, double soiduLopp, double hind) {
        this.lahtepeatus = lahtepeatus;
        this.sihtpeatus = sihtpeatus;
        this.soiduAlgus = soiduAlgus;
        this.soiduLopp = soiduLopp;
        this.hind = hind;
    }

    public void setBuss(Buss buss) {
        this.buss = buss;
    }

    @Override
    public String toString() {
        return "Pilet{" +
                "lahtepeatus='" + lahtepeatus + '\'' +
                ", sihtpeatus='" + sihtpeatus + '\'' +
                ", soiduAlgus=" + soiduAlgus +
                ", soiduLopp=" + soiduLopp +
                ", hind=" + hind +
                '}';
    }

    public Buss getBuss() {
        return buss;
    }
}

