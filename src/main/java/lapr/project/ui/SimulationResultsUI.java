/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.ui;

import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import lapr.project.controller.SimulationResultsController;
import lapr.project.model.Aircraft;
import lapr.project.model.FlightPlan;
import lapr.project.model.Project;
import lapr.project.model.network.Segment;

/**
 *
 * @author zero_
 */
public class SimulationResultsUI extends javax.swing.JFrame {

    private SimulationResultsController simulationResultsController;
    private double time = 0.0;
    private double energy = 0.0;
    private double distance = 0.0;
    private String aircraft;
    private String flightPlan;
    List<Segment> arrayListSegments;

    /**
     * Creates new form SimulationResultsUI
     *
     * @param project
     * @param flightPlan
     * @param aircraft
     * @param pathChoosed
     * @param flightPattern
     */
    public SimulationResultsUI(Project project, FlightPlan flightPlan, Aircraft aircraft, String pathChoosed, double[][] flightPattern) {
        this.simulationResultsController = new SimulationResultsController(project, flightPlan, aircraft, pathChoosed, flightPattern);
        this.aircraft = aircraft.getDescription();
        this.flightPlan = flightPlan.getName();
        this.arrayListSegments = new ArrayList<>();

        if (this.simulationResultsController.isReachable()) {
            initCalculations();
            if (!this.simulationResultsController.checkReserve(time, energy)) {
                JOptionPane.showMessageDialog(this, "Not enough fuel to travel");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Error > No path found between Airports!");
        }

        initComponents();
        initSegmentsList();
        super.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    private void initCalculations() {
        double[] c = this.simulationResultsController.calculatePathByAlgorithm();

        if (c == null) {
            JOptionPane.showMessageDialog(this, "Error Doing Calculations! (Not Yet Implemented?)");
        } else {
            distance = Math.round(c[2] * 10.0) / 10.0;
            energy = Math.round(c[3] * 10.0) / 10.0;
            time = Math.round(c[4] * 10.0) / 10.0;
        }
    }

    private void initSegmentsList() {

        System.out.println(this.simulationResultsController.getArrayListSegments());
        System.out.println(this.simulationResultsController.getArrayListSegments().size());
        
        if (this.simulationResultsController.getArrayListSegments().isEmpty()) {
        this.jListSegments.setModel(new DefaultListModel());
            return;
        }

        for (Segment s : this.simulationResultsController.getArrayListSegments()) {
            arrayListSegments.add(s);
        }

        int tam = this.simulationResultsController.getArrayListSegmentsSize();
        final Segment[] a = new Segment[tam];
        for (int i = 0; i < tam; i++) {
            a[i] = arrayListSegments.get(i);
        }

        DefaultListModel lm = new DefaultListModel() {
            public int getSize() {
                return a.length;
            }

            public Object getElementAt(int i) {
                return a[i];
            }
        };

        this.jListSegments.setModel(lm);

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jListSegments = new javax.swing.JList<>();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        btnSave = new javax.swing.JButton();
        btnCancel = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        ExportHTML = new javax.swing.JButton();
        ExportCSV = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        jLabel1.setText("Segments list:");

        jScrollPane1.setViewportView(jListSegments);

        jLabel2.setText("Time:");

        jLabel3.setText("Energy consuption:");

        btnSave.setText("Save");
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });

        btnCancel.setText("Cancel");
        btnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelActionPerformed(evt);
            }
        });

        jLabel4.setText("Simulation results");

        jLabel5.setText("Aircraft:");

        jLabel6.setText("FlightPlan:");

        jLabel7.setText("Distance:");

        jLabel8.setText(String.valueOf(this.time));

        jLabel9.setText("Seconds");

        jLabel10.setText("N/N/S");

        ExportHTML.setText("Export HTML");
        ExportHTML.setEnabled(false);
        ExportHTML.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ExportHTMLActionPerformed(evt);
            }
        });

        ExportCSV.setText("Export CSV");
        ExportCSV.setEnabled(false);
        ExportCSV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ExportCSVActionPerformed(evt);
            }
        });

        jLabel11.setText("Meters");

        jLabel12.setText(String.valueOf(this.energy));

        jLabel13.setText(String.valueOf(this.distance));

        jLabel14.setText(this.aircraft);

        jLabel15.setText(this.flightPlan);

        jTextField1.setText("CSV_File_Name.csv");

        jTextField2.setText("HTML_File_Name.html");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(53, 53, 53)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel7))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addComponent(jTextField1)
                            .addComponent(jTextField2))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(44, 44, 44)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel10)
                                    .addComponent(jLabel9)
                                    .addComponent(jLabel11)))
                            .addComponent(ExportHTML)
                            .addComponent(ExportCSV, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnCancel)))
                .addGap(54, 54, 54))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel4))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(jLabel1)))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jLabel4)
                .addGap(26, 26, 26)
                .addComponent(jLabel1)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(jLabel8)
                            .addComponent(jLabel9))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(jLabel12)
                            .addComponent(jLabel10))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel13)
                            .addComponent(jLabel11)
                            .addComponent(jLabel7))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(jLabel14))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(jLabel15))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(ExportCSV)
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(ExportHTML)
                            .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnSave)
                            .addComponent(btnCancel)))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 373, Short.MAX_VALUE)))
                .addGap(25, 25, 25))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        if (simulationResultsController.saveFlight(time, energy)) {
            ExportCSV.setEnabled(true);
            ExportHTML.setEnabled(true);

            int option = JOptionPane.showConfirmDialog(this, "Want add to database?", "Confirm", 0);
            if (option == 0) {
                if (simulationResultsController.saveFlightToDatabase()) {
                    JOptionPane.showMessageDialog(this, "Flight saved");
                } else {
                    JOptionPane.showMessageDialog(this, "Error Adding Flight to database!");
                }
            }
            if (option == 1) {
                JOptionPane.showMessageDialog(this, "Flight saved");
            }

        } else {
            JOptionPane.showMessageDialog(this, "Error Creating Flight!");
        }

    }//GEN-LAST:event_btnSaveActionPerformed

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        int option = JOptionPane.showConfirmDialog(this, "Want to exit?", "Confirm", 0);

        if (option == 0) {
            dispose();
        }
    }//GEN-LAST:event_btnCancelActionPerformed

    private void ExportHTMLActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ExportHTMLActionPerformed
        if (this.simulationResultsController.exportHTML(jTextField2.getText())) {
            JOptionPane.showMessageDialog(this, "HTML File Created!");
        } else {
            JOptionPane.showMessageDialog(this, "Error Creating HTML File!");
        }
    }//GEN-LAST:event_ExportHTMLActionPerformed

    private void ExportCSVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ExportCSVActionPerformed
        if (this.simulationResultsController.exportCSV(jTextField1.getText())) {
            JOptionPane.showMessageDialog(this, "CSV File Created!");
        } else {
            JOptionPane.showMessageDialog(this, "Error Creating CSV File!");
        }
    }//GEN-LAST:event_ExportCSVActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton ExportCSV;
    private javax.swing.JButton ExportHTML;
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnSave;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JList<String> jListSegments;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    // End of variables declaration//GEN-END:variables
}
