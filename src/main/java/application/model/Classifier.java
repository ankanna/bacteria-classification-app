package application.model;

import database_support.DBManager;
import database_support.data_model.Examined;
import database_support.data_model.Flagella;
import database_support.data_model.Toughness;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Integer.parseInt;

public class Classifier {

    private List<Examined> examinedList;
    private List<Flagella> flagellaList;
    private List<Toughness> toughnessList;

    private static DBManager dbManager;

    int alpha;
    int beta;
    int gamma;

    public Classifier() {
        dbManager = new DBManager();
    }

    public static DBManager getDbManager() {
        return dbManager;
    }

    public List<Flagella> getFlagellaList() {
        return flagellaList;
    }

    public List<Toughness> getToughnessList() {
        return toughnessList;
    }

    public List<Examined> getExaminedList() {
        return examinedList;
    }

    public void setExaminedList(List<Examined> examinedList) {
        this.examinedList = examinedList;
    }

    public void addToList() {
        flagellaList = dbManager.getAllFlagella();
        toughnessList = dbManager.getAllToughness();
        examinedList = dbManager.getAllExamined();
    }

    public void classifyMany(List<String> genotypes){
        try {
            dbManager.getConnection().setAutoCommit(false);
            for (String genotype : genotypes) {
                classifyBacteria(genotype, false);
            }
            dbManager.getConnection().commit();
            dbManager.getConnection().setAutoCommit(true);
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }


    public void classifyBacteria(String genotype, boolean singleClassification) {

        String classId = classifyFlagella(genotype) + classifyToughness(genotype);

        Examined examined = new Examined(genotype, classId);

        if (getIndexByGenotype(genotype) < 0) {
            dbManager.addExamined(examined);
            examinedList.add(examined);
        } else {
            dbManager.updateExamined(examined);
            examinedList.set(getIndexByGenotype(genotype), examined);
        }
        if (singleClassification){
            JOptionPane.showMessageDialog(new Frame(),
                    "Bacteria with genotype: " + genotype + " is classified as " + classId );
        }
    }


    private int classifyFlagella(String genotype) {

        List<Double> distance = new ArrayList<>();

        flagellaList = dbManager.getAllFlagella();

        try{
        alpha = parseInt(genotype.substring(0, 1) + genotype.substring(5, 6));
        beta = parseInt(genotype.substring(1, 2) + genotype.substring(4, 5));


        distance = flagellaList.stream().map(flagella -> {
            double dist = Math.sqrt(Math.pow(alpha - flagella.getAlpha(), 2)
                    + Math.pow(beta - flagella.getBeta(), 2));
            return dist;
        }).collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
            return flagellaList.get(getMinValue(distance)).getNumber();}
        catch (Exception e){
            JOptionPane.showMessageDialog(null, "Genotype should contain 6 numbers", "Classify error", JOptionPane.ERROR_MESSAGE);
        }

        return flagellaList.get(getMinValue(distance)).getNumber();
    }



    private String classifyToughness(String genotype) {

        List<Double> distance;

        beta = parseInt(genotype.substring(1, 2) + genotype.substring(4, 5));
        gamma = parseInt(genotype.substring(2, 4));

        toughnessList = dbManager.getAllToughness();

        distance = toughnessList.stream().map(flagella -> {
            double dist = Math.sqrt(Math.pow(beta - flagella.getBeta(), 2)
                    + Math.pow(gamma - flagella.getGamma(), 2));
            return dist;
        }).collect(ArrayList::new, ArrayList::add, ArrayList::addAll);

        return toughnessList.get(getMinValue(distance)).getRank();
    }



    private int getMinValue(List<Double> distance) {

        int minAt = 0;

        for (int i = 0; i < distance.size(); i++) {
            minAt = distance.get(i) < distance.get(minAt) ? i : minAt;
        }

        return minAt;
    }



    private int getIndexByGenotype(String genotype) {
        for (int i = 0; i < examinedList.size(); i++) {
            if (examinedList.get(i) !=null &&
                    String.valueOf(examinedList.get(i)
                            .getGenotype()).equals(genotype)) {
                return i;
            }
        }
        return -1;
    }

}
