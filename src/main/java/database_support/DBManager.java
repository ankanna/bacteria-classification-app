package database_support;

import database_support.data_model.Examined;
import database_support.data_model.Flagella;
import database_support.data_model.Toughness;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Integer.parseInt;

public class DBManager {

    public static Connection connection;
    public static String url = "jdbc:sqlite:";

    public DBManager() {
        connect("database/bacteriasDB.db");
    }

    public static Connection getConnection() {
        return connection;
    }

    public void connect(String databaseName) {

        if (connection == null) {
            try {
                url += databaseName;
                connection = DriverManager.getConnection(url);
                System.out.println("Connection established");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    public boolean addExamined(Examined examined) {
        String sql = "INSERT INTO examined (genotype,class) values (?,?)";
        Boolean ret = false;

        if (parseInt(examined.getGenotype()) < 1000000 && examined.getClassId().length() < 3) {
            try {
                PreparedStatement pStatement = connection.prepareStatement(sql);
                pStatement.setString(1, examined.getGenotype());
                pStatement.setString(2, examined.getClassId());

                ret = pStatement.execute();
            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, e.getMessage(), "Examined add error", JOptionPane.ERROR_MESSAGE);
            }
        }
        return ret;
    }

    public List<Examined> getAllExamined() {

        List<Examined> examinedList = new ArrayList<>();

        String sql = "SELECT genotype, class FROM EXAMINED";

        try {
            PreparedStatement pStatement = connection.prepareStatement(sql);
            ResultSet resultSet = pStatement.executeQuery();

            while (resultSet.next()) {
                examinedList.add(new Examined(resultSet.getString("genotype"), resultSet.getString("class")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, e.getMessage(), "Examined selection error", JOptionPane.ERROR_MESSAGE);
        }
        return examinedList;
    }


    public boolean removeExamined(int genotype) {
        String sql = "DELETE FROM examined WHERE genotype = ?";
        Boolean ret = false;
        try {
            PreparedStatement pStatement = connection.prepareStatement(sql);
            pStatement.setInt(1, genotype);
            ret = pStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ret;
    }

    public boolean removeAllExamined() {
        String sql = "DELETE FROM examined";
        Boolean ret = false;
        try {
            PreparedStatement pStatement = connection.prepareStatement(sql);
            ret = pStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ret;
    }


    public List<Flagella> getAllFlagella() {

        List<Flagella> flagellaList = new ArrayList<>();

        String sql = "SELECT alpha, beta, number FROM FLAGELLA";

        try {
            PreparedStatement pStatement = connection.prepareStatement(sql);
            ResultSet resultSet = pStatement.executeQuery();


            while (resultSet.next()) {
                flagellaList.add(new Flagella(resultSet.getInt("alpha"),
                        resultSet.getInt("beta"),
                        resultSet.getInt("number")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, e.getMessage(), "Examined selection error", JOptionPane.ERROR_MESSAGE);
        }
        return flagellaList;
    }

    public List<Toughness> getAllToughness() {

        List<Toughness> toughnessList = new ArrayList<>();

        String sql = "SELECT beta, gamma, rank FROM TOUGHNESS";

        try {
            PreparedStatement pStatement = connection.prepareStatement(sql);
            ResultSet resultSet = pStatement.executeQuery();


            while (resultSet.next()) {
                toughnessList.add(new Toughness(resultSet.getInt("beta"),
                        resultSet.getInt("gamma"),
                        resultSet.getString("rank")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, e.getMessage(), "Examined selection error", JOptionPane.ERROR_MESSAGE);
        }
        return toughnessList;
    }


    public void updateExamined(Examined examined) {
        String sql = "UPDATE examined SET genotype = ? , class = ?";
        try {
            PreparedStatement pStatement = connection.prepareStatement(sql);
            pStatement.setString(1, examined.getGenotype());
            pStatement.setString(2, examined.getClassId());
            pStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, e.getMessage(), "Examined update error", JOptionPane.ERROR_MESSAGE);

        }
    }
}
