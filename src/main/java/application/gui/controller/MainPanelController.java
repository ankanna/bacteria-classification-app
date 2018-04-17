package application.gui.controller;

import application.gui.view.MainPanelView;
import application.model.Classifier;
import application.serialization.JAXBserializer;
import database_support.data_model.Examined;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.xml.bind.JAXBException;
import java.util.Arrays;
import java.util.List;

public class MainPanelController {

    private JPanel mainPanel;
    private JTable examinedTable;
    private JButton refreshButton;
    private JButton saveButton;
    private JTextField genotypeTextField;
    private JTextField manyGenotypesTextField;
    private JButton classifyButton;
    private JButton classifyManyButton;
    private DefaultTableModel model;

    private Classifier classifier;

    public MainPanelController() {

        MainPanelView mainPanelView = new MainPanelView();
        startup();
        initComponents(mainPanelView);
        initListeners();

        refreshExamined();
    }

    void initComponents(MainPanelView mainPanelView){
        this.mainPanel = mainPanelView.getMainPanel();
        this.examinedTable = mainPanelView.getExaminedTable();
        this.refreshButton = mainPanelView.getRefreshButton();
        this.saveButton = mainPanelView.getSaveButton();
        this.genotypeTextField = mainPanelView.getGenotypeTextField();
        this.manyGenotypesTextField = mainPanelView.getManyGenotypesTextField();
        this.classifyButton = mainPanelView.getClassifyButton();
        this.classifyManyButton = mainPanelView.getClassifyManyButton();
        this.model = mainPanelView.getModel();
    }

    void initListeners(){
        this.classifyButton.addActionListener(e -> quickClassify());
        this.refreshButton.addActionListener(e -> refreshExamined());
        this.saveButton.addActionListener(e -> saveToFile());
        this.classifyManyButton.addActionListener(e -> classifyMany());

    }

    void startup(){
        classifier = new Classifier();
        classifier.getDbManager().removeAllExamined();
        classifier.addToList();
        classifier.classifyBacteria("222222", false);
        classifier.classifyBacteria("333333", false);
        classifier.classifyBacteria("999999", false);

    }

    void refreshExamined(){
        model.setRowCount(0);
        for (Examined examined : classifier.getExaminedList()) {
            model.addRow(new Object[]{examined.getGenotype(), examined.getClassId()});
        }
    }

    void quickClassify(){
        classifier.classifyBacteria(genotypeTextField.getText(), true);
        refreshExamined();
    }

    void saveToFile(){
        try {
            JAXBserializer.marshal();
        } catch (JAXBException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, e.getMessage(), "Saving went wrong :(", JOptionPane.ERROR_MESSAGE);
        }
    }

    void classifyMany(){

        String genotypes = manyGenotypesTextField.getText();
        List<String> genotypeList = Arrays.asList(genotypes.split(",[ ]*"));
        classifier.classifyMany(genotypeList);
    }



}

