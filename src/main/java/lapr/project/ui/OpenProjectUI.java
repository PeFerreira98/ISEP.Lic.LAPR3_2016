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
import lapr.project.controller.OpenProjectController;
import lapr.project.model.Airport;
import lapr.project.model.Project;
import lapr.project.model.network.Node;
import lapr.project.model.network.Segment;

/**
 *
 * @author Marcos
 */
public class OpenProjectUI extends javax.swing.JFrame {

    private Project project;
    private OpenProjectController ctr_OpenP;

    /**
     * Creates new form ProjectMenuUI
     */
    public OpenProjectUI(Project proj) {
        this.project = proj;
        this.ctr_OpenP = new OpenProjectController(proj);
        this.ctr_OpenP.LoadInformation();

        initComponents();
        inicializar();
        super.setLocationRelativeTo(null);
        super.setVisible(true);
    }

    private void initAirportsList() {
        try {
            List<Airport> lst_a = new ArrayList<>();
            for (Airport a : this.project.getAirportRegister().getAirportRegister().values()) {
                lst_a.add(a);
            }

            int tam = lst_a.size();
            final String[] a = new String[tam];
            for (int i = 0; i < tam; i++) {
                a[i] = lst_a.get(i).getIATAcode() + " (" + lst_a.get(i).getCountry() +")";
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

        } catch (NullPointerException ex) {
            this.lst_airports.setModel(new DefaultListModel());

            JOptionPane.showMessageDialog(this, "There are no existing airports.");
        }

    }

    private void initAircraftsList() {
//        try {
//            List lst_a = this.ctr_OpenP.getAircraftModelDB();
//
//            int tam = lst_a.size();
//            final String[] a = new String[tam];
//            for (int i = 0; i < tam; i++) {
//                a[i] = lst_a.get(i).toString();
//            }
//
//            DefaultListModel lm = new DefaultListModel() {
//                public int getSize() {
//                    return a.length;
//                }
//
//                public Object getElementAt(int i) {
//                    return a[i];
//                }
//            };
//
//            this.lst_aircraftModels.setModel(lm);
//
//        } catch (NullPointerException ex) {
//            this.lst_aircraftModels.setModel(new DefaultListModel());
//
//            //JOptionPane.showMessageDialog(this, "There are no existing aircraft Models.");
//        }

    }

    private void initNodeList() {

        if (this.project.getAirNetwork().getMapNodes().values().isEmpty()) {
            this.lst_Nodes.setModel(new DefaultListModel());
            return;
        }

        List<Node> lst_a = new ArrayList<>();
        for (Node n : this.project.getAirNetwork().getMapNodes().values()) {
            lst_a.add(n);
        }

        int tam = lst_a.size();
        final String[] a = new String[tam];
        for (int i = 0; i < tam; i++) {
            a[i] = lst_a.get(i).getName();
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

    private void initSegmentsList() {

        if (this.project.getAirNetwork().getMapSegment().values().isEmpty()) {
            this.lst_Segments.setModel(new DefaultListModel());
            return;
        }

        List<Segment> lst_a = new ArrayList<>();

        for (Segment s : this.project.getAirNetwork().getMapSegment().values()) {
            lst_a.add(s);
        }

        int tam = lst_a.size();
        final String[] a = new String[tam];
        for (int i = 0; i < tam; i++) {
            a[i] = lst_a.get(i).getId();
        }

        DefaultListModel lm = new DefaultListModel() {
            public int getSize() {
                return a.length;
            }

            public Object getElementAt(int i) {
                return a[i];
            }
        };

        this.lst_Segments.setModel(lm);

    }

    private void initFlightPlansList() {
//        List lst_a = this.ctr_OpenP.getFlightPlansDB();
//
//        if (lst_a == null) {
//            this.lst_flightplans.setModel(new DefaultListModel());
//
//            JOptionPane.showMessageDialog(this, "There are no existing fli6ts.");
//
//            return;
//        }
//
//        int tam = lst_a.size();
//        final String[] a = new String[tam];
//        for (int i = 0; i < tam; i++) {
//            a[i] = lst_a.get(i).toString();
//        }
//
//        DefaultListModel lm = new DefaultListModel() {
//            public int getSize() {
//                return a.length;
//            }
//
//            public Object getElementAt(int i) {
//                return a[i];
//            }
//        };
//
//        this.lst_flightplans.setModel(lm);
    }

    private void inicializar() {
        initAirportsList();
        initAircraftsList();
        //initFlightPlansList();
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
        lst_flightplans = new javax.swing.JList<>();
        jScrollPane3 = new javax.swing.JScrollPane();
        lst_airports = new javax.swing.JList<>();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        lst_Nodes = new javax.swing.JList<>();
        jScrollPane5 = new javax.swing.JScrollPane();
        lst_Segments = new javax.swing.JList<>();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        lbl_proj_name1 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        btnEditAirports = new javax.swing.JToggleButton();
        btnEditAircraftModels = new javax.swing.JToggleButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        jLabel1.setText("Project: ");

        lbl_proj_name.setText(this.project.getName());

        lst_aircraftModels.setRequestFocusEnabled(false);
        jScrollPane1.setViewportView(lst_aircraftModels);

        jScrollPane2.setViewportView(lst_flightplans);

        lst_airports.setRequestFocusEnabled(false);
        jScrollPane3.setViewportView(lst_airports);

        jLabel2.setText("Airports");

        jLabel3.setText("Aircraft Models");

        jLabel4.setText("Flight Plans");

        jScrollPane4.setViewportView(lst_Nodes);

        jScrollPane5.setViewportView(lst_Segments);

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

        jButton3.setText("Update");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        btnEditAirports.setText("...");
        btnEditAirports.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditAirportsActionPerformed(evt);
            }
        });

        btnEditAircraftModels.setText("...");

        jMenu1.setText("File");

        jMenuItem1.setText("Edit project");
        jMenu1.add(jMenuItem1);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnEditAirports, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel2)
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel3))
                            .addComponent(btnEditAircraftModels, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel4))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel5))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel6)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jButton3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addGap(18, 18, 18)
                                .addComponent(lbl_proj_name1, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(lbl_proj_name)))
                .addGap(0, 30, Short.MAX_VALUE))
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
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel4)
                                .addComponent(jLabel3))
                            .addComponent(jLabel2))
                        .addGap(14, 14, 14)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnEditAirports)
                            .addComponent(btnEditAircraftModels))
                        .addGap(0, 61, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6))
                        .addGap(14, 14, 14)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jButton1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton3)))))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        new CreateFlightPlanUI(project);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        new SimulateFlightUI(project);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        inicializar();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void btnEditAirportsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditAirportsActionPerformed
        if(lst_airports.isSelectionEmpty()){
            JOptionPane.showMessageDialog(this, "Choose an airport");
        }else{
            String airportName = lst_airports.getSelectedValue();
            Airport a = this.project.getAirportRegister().getAirportByIATACode(airportName);
            
            new EditAirportUI(this.project, a);
        }
    }//GEN-LAST:event_btnEditAirportsActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JToggleButton btnEditAircraftModels;
    private javax.swing.JToggleButton btnEditAirports;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JMenu jMenu1;
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
    private javax.swing.JList<String> lst_Segments;
    private javax.swing.JList<String> lst_aircraftModels;
    private javax.swing.JList<String> lst_airports;
    private javax.swing.JList<String> lst_flightplans;
    // End of variables declaration//GEN-END:variables
}
