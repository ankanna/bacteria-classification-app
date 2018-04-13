package database_support;

import model.Examined;
import model.Flagella;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;

class DBManager {

    public static Connection connection;
    public static String url = "jdbc:sqlite:";

    public DBManager() {
        connect("database/bacteriasDB.db");

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

        try {
            PreparedStatement pStatement = connection.prepareStatement(sql);
            pStatement.setInt(1, examined.getGenotype());
            pStatement.setString(2, examined.getClassId());

            ret = pStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, e.getMessage(), "Examined add error", JOptionPane.ERROR_MESSAGE);
        }
        return ret;
    }

    public ArrayList<Examined> getAllExamined() {

        ArrayList<Examined> userList = new ArrayList<Examined>();

        String sql = "SELECT genotype, class FROM EXAMINED";

        try {
            PreparedStatement pStatement = connection.prepareStatement(sql);
            ResultSet resultSet = pStatement.executeQuery();


            while (resultSet.next()) {
                userList.add(new Examined(resultSet.getInt("genotype"), resultSet.getString("class")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, e.getMessage(), "Examined selection error", JOptionPane.ERROR_MESSAGE);
        }
        return userList;
    }

    public ArrayList<Flagella> getAllFlagella() {

        ArrayList<Flagella> flagellaList = new ArrayList<Flagella>();

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


    public void updateUser(Examined examined) {
        String sql = "UPDATE users SET genotype = ? , classId = ?";
        try {
            PreparedStatement pStatement = connection.prepareStatement(sql);
            pStatement.setInt(1, examined.getGenotype());
            pStatement.setString(2, examined.getClassId());
            pStatement.execute();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Examined update error", JOptionPane.ERROR_MESSAGE);

        }
    }

    public void testMethod() {
        DBManager manager = new DBManager();

//        Adding new records
//        manager.addExamined(new Examined(123456, "1a"));
//        manager.addExamined(new Examined(432567, "3b"));
//        manager.addOrder(new Order(1,"01012018","02012018","Puzon",7,1,"03012018"));
//        manager.addStorageItem(new StorageItem(1,"Puzon",5));

////        Displaying records
//        ArrayList<Examined> examined = manager.getAllExamined();
//        for (int i = 0; i < examined.size(); i++) {
//            System.out.println(examined.get(i).getGenotype());
//        }

        ArrayList<Flagella> flagellas = manager.getAllFlagella();
        for(Flagella flagella : flagellas){
            System.out.println(flagella.getAlpha());
        }

//        ArrayList<StorageItem> item = manager.getAllStorageItems();
//        for(int i =0; i< item.size(); i++){
//            System.out.println(item.get(i).getItem());
//        }
//          Removing records
//        manager.removeUser(1);
//        manager.removeOrder(1);
//        manager.removeStorageItem(1);
//            Displaying records
//        ArrayList<Examined> examined2 = manager.getAllExamined();
//        for (int i = 0; i < examined2.size(); i++) {
//            System.out.println(examined2.get(i).getGenotype());
//        }
//
//        ArrayList<Order> orders2 = manager.getAllOrders();
//        for(int i =0; i< orders2.size(); i++){
//            System.out.println(orders2.get(i).getCreationDate());
//        }
//
//        ArrayList<StorageItem> item2 = manager.getAllStorageItems();
//        for(int i =0; i< item2.size(); i++){
//            System.out.println(item2.get(i).getItem());
//        }
    }

    public static void main(String[] args) {
        DBManager manager = new DBManager();
        manager.testMethod();
    }
}
