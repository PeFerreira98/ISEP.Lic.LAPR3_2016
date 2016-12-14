/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.database;

import java.sql.*;
import java.util.*;

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
}
