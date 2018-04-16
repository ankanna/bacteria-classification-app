package application.gui.controller;

import application.gui.view.MainPanelView;
import database_support.DBManager;
import application.model.Classifier;
import database_support.data_model.Examined;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class MainPanelController {

    private JPanel mainPanel;
    private JTable examinedTable;
    private JButton refreshButton;
    private JButton saveButton;
    private JTextField genotypeTextField;
    private JButton classifyButton;
    private JButton classifyManyBacteriasButton;
    private DefaultTableModel model;


    private static DBManager dbManager;
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
        this.classifyButton = mainPanelView.getClassifyButton();
        this.classifyManyBacteriasButton = mainPanelView.getClassifyManyBacteriasButton();
        this.model = mainPanelView.getModel();
    }

    void initListeners(){
        this.classifyButton.addActionListener(e -> quickClassify());
        this.refreshButton.addActionListener(e -> refreshExamined());

    }

    void startup(){
        classifier = new Classifier();
        classifier.getDbManager().removeAllExamined();
        classifier.addToList();
        classifier.classifyBacteria("222222");
        classifier.classifyBacteria("333333");
        classifier.classifyBacteria("999999");

    }

    void refreshExamined(){
        model.setRowCount(0);
        for (Examined examined : classifier.getExaminedList()) {
            model.addRow(new Object[]{examined.getGenotype(), examined.getClassId()});
        }
    }

    void quickClassify(){
        classifier.classifyBacteria(genotypeTextField.getText());
        refreshExamined();
    }

}

