package application.gui.view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class MainPanelView {
    private JPanel mainPanel;
    private JPanel tablePanel;
    private JPanel classifyPanel;
    private JTable examinedTable;
    private JButton refreshButton;
    private JButton saveButton;
    private JTextField genotypeTextField;
    private JButton classifyButton;
    private JButton classifyManyBacteriasButton;
    private DefaultTableModel model;

    public MainPanelView(){
        JFrame f = new JFrame("Bacteria classification");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setContentPane(mainPanel);

        f.pack();
        f.setLocationByPlatform(true);
        f.setMinimumSize(f.getSize());
        f.setVisible(true);
    }

    public DefaultTableModel getModel() {
        return model;
    }

    public void setModel(DefaultTableModel model) {
        this.model = model;
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public void setMainPanel(JPanel mainPanel) {
        this.mainPanel = mainPanel;
    }

    public JTable getExaminedTable() {
        return examinedTable;
    }

    public void setExaminedTable(JTable examinedTable) {
        this.examinedTable = examinedTable;
    }

    public JButton getRefreshButton() {
        return refreshButton;
    }

    public void setRefreshButton(JButton refreshButton) {
        this.refreshButton = refreshButton;
    }

    public JButton getSaveButton() {
        return saveButton;
    }

    public void setSaveButton(JButton saveButton) {
        this.saveButton = saveButton;
    }

    public JTextField getGenotypeTextField() {
        return genotypeTextField;
    }

    public void setGenotypeTextField(JTextField genotypeTextField) {
        this.genotypeTextField = genotypeTextField;
    }

    public JButton getClassifyButton() {
        return classifyButton;
    }

    public void setClassifyButton(JButton classifyButton) {
        this.classifyButton = classifyButton;
    }

    public JButton getClassifyManyBacteriasButton() {
        return classifyManyBacteriasButton;
    }

    public void setClassifyManyBacteriasButton(JButton classifyManyBacteriasButton) {
        this.classifyManyBacteriasButton = classifyManyBacteriasButton;
    }


    private void createUIComponents() {

        model = new DefaultTableModel();
        examinedTable = new JTable(model);

        model.addColumn("Genotype");
        model.addColumn("Class");

        examinedTable.setFillsViewportHeight(true);
    }
}


