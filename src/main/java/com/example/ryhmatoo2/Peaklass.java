package com.example.ryhmatoo2;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.control.TextField;

import java.io.*;

import java.nio.charset.StandardCharsets;
import java.util.*;

public class Peaklass extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    /**
     *
     * Start meetodis toimib programmi põhitöö.
     * Tekitatakse aken, kus on olemas marsruutide info ning lastakse kasutajal valida soovitud marsruut.
     *
     * Ebasobivate sisendite korral visatakse erind ning kasutajale antakse teada vastavalt
     * ebasobiva(te)st sisendi(te)st.
     *
     * Õige marsruuti sisendi korral tekitatakse uus aken, kus küsitakse soovitud piletite kogust.
     *
     * Sobiva piletite koguse sisestamisel salvestatakse tehingu informatsioon faili
     * ja antakse kasutajale valik osta veel marsruutidele pileteid või lõpetada piletite ostmine (programmi sulgemine).
     *
     * @param primaryStage pealava, kus programm oma tööd teeb.
     * @throws FileNotFoundException Kui faili, kust saadakse kätte info piletite kohta, pole olemas, visatakse erind.
     */
    @Override
    public void start(Stage primaryStage) throws FileNotFoundException {


        List<Pilet> piletid = failist();
        ArrayList<String> marsruutList = new ArrayList<>();
        ArrayList<String> lähtekohadList = new ArrayList<>();
        ArrayList<String> sihtkohadList = new ArrayList<>();
        Map<Pilet, Integer> ostetudpiletid = new HashMap<>();

        for(Pilet pilet : piletid) {
            pilet.setBuss(new Buss());
        }

        for (Pilet pilet:
             piletid) {

            marsruutList.add(pilet.getLahtepeatus() + " --> " + pilet.getSihtpeatus());

            if (!lähtekohadList.contains(pilet.getLahtepeatus())) {
                lähtekohadList.add(pilet.getLahtepeatus());
            }
            if (!sihtkohadList.contains(pilet.getSihtpeatus())) {
                sihtkohadList.add(pilet.getSihtpeatus());
            }
        }

        // Algse ekraani tegemine
        primaryStage.setTitle("Piletite müük");

        VBox vbox = new VBox();
        vbox.setSpacing(20);

        Text bussid = new Text("Bussid sõidavad marsruutidel:" );
        vbox.getChildren().add(bussid);

        for(String marsruut : marsruutList) {
            Text marsruutTekst = new Text(marsruut);
            vbox.getChildren().add(marsruutTekst);
        }

        Text lähtekoht = new Text("Sisestage lähtekoht:" );
        TextField lähtekohtSisend = new TextField();

        Text sihtkoht = new Text("Sisestage sihtkoht:" );
        TextField sihtkohtSisend = new TextField();

        Button sisesta = new Button("Sisesta andmed");

        lähtekohtSisend.setMaxWidth(200);
        sihtkohtSisend.setMaxWidth(200);

        vbox.getChildren().add(lähtekoht);
        vbox.getChildren().add(lähtekohtSisend);
        vbox.getChildren().add(sihtkoht);
        vbox.getChildren().add(sihtkohtSisend);
        vbox.getChildren().add(sisesta);

        vbox.setAlignment(Pos.CENTER);

        Scene stseen = new Scene(vbox, 500, 500);

        primaryStage.setScene(stseen);
        primaryStage.show();

        Text vabukohti = new Text("Vabu kohti pole bussile!");
        Text lähtekohteivälju = new Text("Antud lähtekohast ei välju ühtegi bussi!");
        Text sihtkohteijõua = new Text("Antud sihtkohta ei jõua ühtegi bussi!");
        Text marsruutieiole = new Text("Antud asukohate vahel ei eksisteeri marsruuti!");

        // Nupule vajutus
        sisesta.setOnMouseClicked(event -> {

            boolean kasMarsruutEksisteerib = false;

            // Marsruudikontroll
            for (int i = 0; i < marsruutList.size(); i++) {
                if (marsruutList.contains(lähtekohtSisend.getText() + " --> " + sihtkohtSisend.getText())) {
                    kasMarsruutEksisteerib = true;
                    break;
                }
            }
            // Kui lähtekohta pole või sihtkohta pole
            if (!lähtekohadList.contains(lähtekohtSisend.getText()) || !sihtkohadList.contains(sihtkohtSisend.getText())) {
                try {
                    throw new AsukohtPuudubErind();
                } catch (AsukohtPuudubErind e) {
                    vbox.getChildren().remove(marsruutieiole);
                    vbox.getChildren().remove(vabukohti);

                    if (!lähtekohadList.contains(lähtekohtSisend.getText())) {

                        if(sihtkohadList.contains(sihtkohtSisend.getText()))
                            vbox.getChildren().remove(sihtkohteijõua);

                        if(!vbox.getChildren().contains(lähtekohteivälju))
                            vbox.getChildren().add(lähtekohteivälju);

                    }  if (!sihtkohadList.contains(sihtkohtSisend.getText())) {

                        if(lähtekohadList.contains(lähtekohtSisend.getText()))
                            vbox.getChildren().remove(lähtekohteivälju);

                        if (!vbox.getChildren().contains(sihtkohteijõua))
                            vbox.getChildren().add(sihtkohteijõua);
                    }
                }
                // Kui marsruuti pole
            } else if (!kasMarsruutEksisteerib) {
                try {
                    throw new MarsruutPuudubErind();
                } catch (MarsruutPuudubErind e) {
                    vbox.getChildren().remove(vabukohti);
                    vbox.getChildren().remove(lähtekohteivälju);
                    vbox.getChildren().remove(sihtkohteijõua);
                    if (!vbox.getChildren().contains(marsruutieiole))
                        vbox.getChildren().add(marsruutieiole);
                }
            }
            // Kui kõik õige, siis tekitatakse uus ekraan.
            if (kasMarsruutEksisteerib) {
                vbox.getChildren().remove(lähtekohteivälju);
                vbox.getChildren().remove(sihtkohteijõua);
                vbox.getChildren().remove(marsruutieiole);
                String lähteTekst = lähtekohtSisend.getText();
                String sihtTekst = sihtkohtSisend.getText();

                VBox piletidbox = new VBox();

                Pilet müüdavpilet = null;
                for (Pilet pilet : piletid) {
                    if (pilet.getLahtepeatus().equals(lähteTekst) && pilet.getSihtpeatus().equals(sihtTekst)) {
                        piletidbox.getChildren().add(new Text(pilet.getLahtepeatus() + " --> " + pilet.getSihtpeatus()));
                        piletidbox.getChildren().add(new Text("Vabu kohti antud marsruudile: " + pilet.getBuss().getKohti()));
                        müüdavpilet = pilet;
                    }
                }

                // Piletite ostmine
                piletidbox.getChildren().add(new Text("Sisesta soovitud piletite kogus: "));
                TextField piletiteKogus = new TextField();
                piletiteKogus.setMaxWidth(200);
                piletidbox.getChildren().add(piletiteKogus);
                Button sisestaPiletiteArv = new Button("Kinnita ost");
                piletidbox.getChildren().add(sisestaPiletiteArv);

                piletidbox.setAlignment(Pos.CENTER);
                piletidbox.setSpacing(20);
                Scene piletitestseen = new Scene(piletidbox, 500, 500);

                // Kui bussis pole kohti enam
                if(müüdavpilet.getBuss().getKohti() == 0) {
                    try {
                        throw new KohtiPoleErind();
                    } catch (KohtiPoleErind e) {
                        if (!vbox.getChildren().contains(vabukohti))
                            vbox.getChildren().add(vabukohti);
                    }
                }
                else {
                    vbox.getChildren().remove(vabukohti);
                    primaryStage.setScene(piletitestseen);
                }

                Pilet finalMüüdavpilet = müüdavpilet;
                Text vähe = new Text("Pileteid liiga vähe!");

                //Nupuvajutus
                sisestaPiletiteArv.setOnMouseClicked(event1 -> {
                    //Pileteid vähem kui tahetakse
                    if (finalMüüdavpilet.getBuss().getKohti() < Integer.parseInt(piletiteKogus.getText()))
                        try {
                            throw new PileteidVäheErind();
                        } catch (PileteidVäheErind e) {
                            if (!piletidbox.getChildren().contains(vähe))
                                piletidbox.getChildren().add(vähe);
                        }
                    else{
                        //Ostetud piletite salvestamine

                        if(!ostetudpiletid.containsKey(finalMüüdavpilet))
                            ostetudpiletid.put(finalMüüdavpilet, 0);
                        int uus = ostetudpiletid.get(finalMüüdavpilet) + Integer.parseInt(piletiteKogus.getText());
                        ostetudpiletid.put(finalMüüdavpilet, uus);
                        if(!ostetudpiletid.isEmpty()) {
                            try (BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("OstetudPiletid.txt"),
                                    StandardCharsets.UTF_8))) {
                                for (Pilet pilet : ostetudpiletid.keySet()) {
                                    bw.append("Piletit:" + pilet + " osteti kogus: " + ostetudpiletid.get(pilet));
                                    bw.newLine();
                                }
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                        }
                        finalMüüdavpilet.getBuss().müüKohti(Integer.parseInt(piletiteKogus.getText()));
                        }

                        //Valik, kas lõpetada programm või osta veel pileteid.
                        HBox nupud = new HBox();
                        Button lõpeta = new Button("Lõpeta");
                        Button ostaveel = new Button("Osta veel pileteid");
                        nupud.getChildren().add(ostaveel);
                        nupud.getChildren().add(lõpeta);
                        nupud.setAlignment(Pos.CENTER);
                        nupud.setSpacing(20);
                        Scene lõpetaVõiOstaVeel = new Scene(nupud, 500, 500);

                        primaryStage.setScene(lõpetaVõiOstaVeel);

                        lõpeta.setOnMouseClicked(event2 -> System.exit(0));
                        ostaveel.setOnMouseClicked(event2 -> primaryStage.setScene(stseen));

                });

            }
        });

    }

    /**
     *
     * Meetod loeb antud failist sisse informatsiooni piletitest ning tagastab selle informatsiooni List kujul.
     *
     * @return List<Pilet> List, kus on olemas piletite objektid.
     * @throws FileNotFoundException Kui faili, kust saadakse kätte info piletite kohta, pole olemas, visatakse erind.
     */
    public static List<Pilet> failist() throws FileNotFoundException {
        // Loeb failist piletite info ja tagastab listi, kus sees Pilet-objektid.
//
        File file = new File("piletid.txt");
        List<Pilet> piletid = new ArrayList<>();
        Scanner sc = new Scanner(file, "UTF-8");

        while(sc.hasNextLine()) {
            String rida = sc.nextLine();
            String[] info = rida.split(",");
            Pilet pilet = new Pilet(info[0], info[1], Double.parseDouble(info[2]), Double.parseDouble(info[3]), Double.parseDouble(info[4]));
            piletid.add(pilet);
        }

        return piletid;
    }
}
