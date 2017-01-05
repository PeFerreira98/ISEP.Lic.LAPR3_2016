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
import lapr.project.controller.ProjectMenuController;
import lapr.project.model.Airport;
import lapr.project.model.Project;

/**
 *
 * @author Marcos
 */
public class ProjectMenuUI extends javax.swing.JFrame {
    
    private Project project;
    private ProjectMenuController ctr_pMenu;
    
    /**
     * Creates new form ProjectMenuUI
     */
    public ProjectMenuUI(Project proj) {
        this.project = proj;
        this.ctr_pMenu = new ProjectMenuController(proj);
        initComponents();
        inicializar();
        super.setVisible(true);
    }
    
    private void initAirportsList(){
        List lst_a = this.ctr_pMenu.getAirportsNames();
        
        
        if (lst_a == null) {
            this.lst_airports.setModel(new DefaultListModel());

            JOptionPane.showMessageDialog(this, "There are no existing airports.");

            return;
        }

        int tam = lst_a.size();
        final String[] a = new String[tam];
        for (int i = 0; i < tam; i++) {
            a[i] = lst_a.get(i).toString();
        }

        DefaultListModel lm = new DefaultListModel() {
            public int getSize() {
                return a.length;
            }

            public Object getElementAt(int i) {
                return a[i];
            }
        };

        this.lst_airports.setModel(lm);
    }
    
    private void initAircraftsList(){
        List lst_a = this.ctr_pMenu.getAircraftModelNames();
        
        
        if (lst_a == null) {
            this.lst_aircraftModels.setModel(new DefaultListModel());

            JOptionPane.showMessageDialog(this, "There are no existing aircraft Models.");

            return;
        }

        int tam = lst_a.size();
        final String[] a = new String[tam];
        for (int i = 0; i < tam; i++) {
            a[i] = lst_a.get(i).toString();
        }

        DefaultListModel lm = new DefaultListModel() {
            public int getSize() {
                return a.length;
            }

            public Object getElementAt(int i) {
                return a[i];
            }
        };

        this.lst_aircraftModels.setModel(lm);
    }
    
    private void initFlightsList(){
        List lst_a = this.ctr_pMenu.getFlightsNames();
        
        if (lst_a == null) {
            this.lst_flights.setModel(new DefaultListModel());

            JOptionPane.showMessageDialog(this, "There are no existing fli6ts.");

            return;
        }

        int tam = lst_a.size();
        final String[] a = new String[tam];
        for (int i = 0; i < tam; i++) {
            a[i] = lst_a.get(i).toString();
        }

        DefaultListModel lm = new DefaultListModel() {
            public int getSize() {
                return a.length;
            }

            public Object getElementAt(int i) {
                return a[i];
            }
        };

        this.lst_flights.setModel(lm);
    }
    
    private void initNodeList(){
        List lst_a = this.ctr_pMenu.getNodesNames();
        
        if (lst_a == null) {
            this.lst_Nodes.setModel(new DefaultListModel());

            JOptionPane.showMessageDialog(this, "There are no existing nodes.");

            return;
        }

        int tam = lst_a.size();
        final String[] a = new String[tam];
        for (int i = 0; i < tam; i++) {
            a[i] = lst_a.get(i).toString();
        }

        DefaultListModel lm = new DefaultListModel() {
            public int getSize() {
                return a.length;
            }

            public Object getElementAt(int i) {
                return a[i];
            }
        };

        this.lst_Nodes.setModel(lm);
    }
    
    private void initSegmentsList(){
        List lst_a = this.ctr_pMenu.getSegmentNames();
        
        if (lst_a == null) {
            this.lst_Se6ments.setModel(new DefaultListModel());

            JOptionPane.showMessageDialog(this, "There are no existing segments.");

            return;
        }

        int tam = lst_a.size();
        final String[] a = new String[tam];
        for (int i = 0; i < tam; i++) {
            a[i] = lst_a.get(i).toString();
        }

        DefaultListModel lm = new DefaultListModel() {
            public int getSize() {
                return a.length;
            }

            public Object getElementAt(int i) {
                return a[i];
            }
        };

        this.lst_Se6ments.setModel(lm);
    }
    
    private void inicializar() {
       initAirportsList();
       initAircraftsList();
       initFlightsList();
       initNodeList();
       initSegmentsList();
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
        lbl_proj_name = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        lst_aircraftModels = new javax.swing.JList<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        lst_flights = new javax.swing.JList<>();
        jScrollPane3 = new javax.swing.JScrollPane();
        lst_airports = new javax.swing.JList<>();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        lst_Nodes = new javax.swing.JList<>();
        jScrollPane5 = new javax.swing.JScrollPane();
        lst_Se6ments = new javax.swing.JList<>();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        lbl_proj_name1 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jLabel1.setText("Project: ");

        lbl_proj_name.setText(this.project.getName());

        lst_aircraftModels.setRequestFocusEnabled(false);
        jScrollPane1.setViewportView(lst_aircraftModels);

        jScrollPane2.setViewportView(lst_flights);

        lst_airports.setRequestFocusEnabled(false);
        jScrollPane3.setViewportView(lst_airports);

        jLabel2.setText("Airports");

        jLabel3.setText("Aircraft Models");

        jLabel4.setText("Flights");

        jScrollPane4.setViewportView(lst_Nodes);

        jScrollPane5.setViewportView(lst_Se6ments);

        jLabel5.setText("Nodes");

        jLabel6.setText("Segments");

        jButton1.setText("Create Flight");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Simulate Flight");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel7.setText("Description:");

        lbl_proj_name1.setText(this.project.getDescription());

        jMenu1.setText("File");

        jMenuItem1.setText("jMenuItem1");
        jMenu1.add(jMenuItem1);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Edit");
        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel3))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel7)))
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel5))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel6)))
                            .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(lbl_proj_name)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lbl_proj_name1, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(lbl_proj_name)
                    .addComponent(jLabel7)
                    .addComponent(lbl_proj_name1))
                .addGap(35, 35, 35)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel4)
                        .addComponent(jLabel3)
                        .addComponent(jLabel5)
                        .addComponent(jLabel6))
                    .addComponent(jLabel2))
                .addGap(14, 14, 14)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
       new CreateFlightUI(project);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        new SimulateFlightUI(project);
    }//GEN-LAST:event_jButton2ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JLabel lbl_proj_name;
    private javax.swing.JLabel lbl_proj_name1;
    private javax.swing.JList<String> lst_Nodes;
    private javax.swing.JList<String> lst_Se6ments;
    private javax.swing.JList<String> lst_aircraftModels;
    private javax.swing.JList<String> lst_airports;
    private javax.swing.JList<String> lst_flights;
    // End of variables declaration//GEN-END:variables
}
