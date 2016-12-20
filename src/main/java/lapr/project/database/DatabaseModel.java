/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.database;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import lapr.project.model.Aircraft;
import lapr.project.model.AircraftModel;
import lapr.project.model.Airport;
import lapr.project.model.Project;
//import oracle.jdbc.OracleTypes;

/**
 *
 * @author zero_
 */
public class DatabaseModel {
    
    public static final String DBURL = "jdbc:oracle:thin://@gandalf.dei.isep.ipp.pt:1521/pdborcl";
    public static final String DBUSER = "LAPR3_33";
    public static final String DBPASS = "qwerty";
    Connection con;
    Statement st;
    ResultSet rs;

    public DatabaseModel() {

    }
    
    /**
     * Método utilizado para ligar a base de dados.
     *
     */
      public void openDB(){
        // LER O DRIVER Oracle JDBC E CONECTAR À BASE DE DADOS ORACLE
        
        //DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
        try {
            this.con = DriverManager.getConnection(DBURL, DBUSER, DBPASS);
            this.st = con.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseModel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
      /**
     * Método utilizado para desligar a base de dados.
     *
     */
      public void closeDB(){
        try {
            this.con.close();
            this.rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseModel.class.getName()).log(Level.SEVERE, null, ex);
        }
      }
      
      /**
     * Método utilizado para receber todos os projectos da base de dados.
     *
     * @return
     */
    public List<Project> getProjects(){
        List<Project> list_projects = new ArrayList<>();
        
        try {
            this.rs = this.st.executeQuery("SELECT * FROM PROJECT");
            while (rs.next()) {
                Project p = new Project(rs.getInt("id"),rs.getString("NAME"),rs.getString("DESCRIPTION"));
                list_projects.add(p);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        closeDB();
        return list_projects;
    }
          
      /**
     * Método utilizado para guardar dados de um modelo de um avião na base de dados.
     *
     * @param air
     */
    public void addAircraftModel(AircraftModel air){
        try {
            this.st.execute("insert into AircraftModel(id, type, numberMotors, motorType, emptyWeight, MTOW, MZFW, maxPayload, fuelCapacity, VMO, MMO, wingArea, wingSpan, dragCoeficient, e) "
                    + "values ('" 
                    + air.getMotor() + "', '"
                    + air.getType().toString() + "', '"
                    + air.getNumberMotors() + "', '"
                    + air.getMotorType().toString() + "', '"
                    + air.getEmptyWeight() + "', '"
                    + air.getMTOW() + "', '"
                    + air.getMZFW() + "', '"
                    + air.getMaxPayload() + "', '"
                    + air.getFuelCapacity() + "', '"
                    + air.getVMO() + "', '"
                    + air.getMMO() + "', '"
                    + air.getWingArea() + "', '"
                    + air.getWingSpan() + "', '"
                    + air.getDragCoeficient() + "', '"
                    + air.getE() + "')");
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        closeDB();
        //return getLastInsertedProjectCod();
    }

    /**
     * Método utilizado para guardar dados de um avião na base de dados.
     *
     * @param air
     */
    public void addAircraft(Aircraft air){
        try {
            this.st.execute("insert into Aircraft(model, id, company, numberFirstClass, numberNormalClass, numberElementsCrew) "
                    + "values ('" + air.getModel()+ "', '"
                    + air.getId()+ "', '"
                    + air.getMaker()+ "', '"
                    + air.getNumberFirstClass()+ "', '"
                    + air.getNumberNormalClass()+ "', '"
                    + air.getNumberElementsCrew()+ "')");
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        closeDB();
        //return getLastInsertedProjectCod();
    }
    
    /**
     * Método utilizado para guardar dados de um aeroporto na base de dados.
     *
     * @param ap
     */
    public void addAirport(Airport ap){
        try {
            this.st.execute("insert into Airport(name, town, country, IATAcode, location) "
                    + "values ('" + ap.getName()+ "', '"
                    + ap.getTown()+ "', '"
                    + ap.getCountry()+ "', '"
                    + ap.getIATAcode()+ "', '"
                    + ap.getLocation()+ "')");
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        closeDB();
        //return getLastInsertedProjectCod();
    }
      
    public void editAircraft(Aircraft  air, String company, int nrFirstClass, int nrNormalClass, int nrElementsCrew){
        String idAircraft = air.getId();
        try {
            this.st.execute("UPDATE Aircraft set comany = " + company
                    + " && numberFirstClass = " + nrFirstClass
                    + " && numberNormalClass = " + nrNormalClass
                    + " && numberElements = " + nrElementsCrew
                    + " where id = " + idAircraft);
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseModel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
