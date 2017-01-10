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
import lapr.project.model.*;
import lapr.project.model.Flight.*;
import lapr.project.model.network.*;
import oracle.jdbc.OracleTypes;

/**
 *
 * @author zero_
 */
public class DatabaseModel {

    public static final String DBURL = "jdbc:oracle:thin://@gandalf.dei.isep.ipp.pt:1521/pdborcl";
    public static final String DBUSER = "LAPR3_33";
    public static final String DBPASS = "qwerty";
    Connection con;
    CallableStatement cs;
    Statement st;
    ResultSet rs;
    Project project;

    public DatabaseModel() {
        openDB();
    }

    public DatabaseModel(Project p) {
        this.project = p;
        openDB();
    }

    /**
     * Método utilizado para ligar a base de dados.
     *
     */
    public void openDB() {
        // LER O DRIVER Oracle JDBC E CONECTAR À BASE DE DADOS ORACLE
        try {
            DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
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
    public void closeDB() {
        try {
            this.con.close();
            this.rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseModel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void closeDBDAL() {
        try {
            this.con.close();
            this.cs.close();
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseModel.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * Método utilizado para receber todos os projectos da base de dados.
     *
     * @return
     */
    public List<Project> getProjects() {
        List<Project> list_projects = new ArrayList<>();

        try {

            this.cs = this.con.prepareCall("{? = call getAllProjects}");
            cs.registerOutParameter(1, OracleTypes.CURSOR);
            cs.executeUpdate();

            ResultSet cs1 = (ResultSet) cs.getObject(1);

            while (cs1.next()) {
                String id = cs1.getString("NAME");
                String des = cs1.getString("DESCRIPTION");
                
                Project p = new Project(id, des);
                list_projects.add(p);
            }

        } catch (SQLException ex) {
            Logger.getLogger(DatabaseModel.class.getName()).log(Level.SEVERE, null, ex);
        }

        closeDBDAL();
        return list_projects;
    }

    public void addProject(Project project) {
        try {
//            this.rs = this.st.executeQuery("insert into Project(name, description) "
//                    + "values ('"
//                    + project.getName() + "', '"
//                    + project.getDescription() + "')");

            CallableStatement cs = con.prepareCall("{call insertProject(?,?)}");
            cs.setString(1, project.getName());
            cs.setString(2, project.getDescription());
            cs.executeUpdate();
            this.project = project;

        } catch (SQLException ex) {
            Logger.getLogger(DatabaseModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (!project.getAirNetwork().getMapNodes().isEmpty()) {
            project.getAirNetwork().getMapNodes().values().stream().forEach((n) -> {
                addNode(n);
            });
        }
        if (!project.getAirNetwork().getMapSegment().isEmpty()) {
            project.getAirNetwork().getMapSegment().values().stream().forEach((s) -> {
                addSegment(s);
            });
        }

        project.getAirportRegister().getAirportRegister().values().stream().forEach((airport) -> {
            addAirport2(airport);
        });
//        project.getAircraftRegister().getAircraftRegister().values().stream().forEach((aircraft) -> {
//            //openDB();
//            addAircraft(aircraft);
//        });
//        project.getAircraftModelRegister().getAircraftModelMap().values().stream().forEach((airModel) -> {
//            //openDB();
//            addAircraftModel(airModel);
//        });
//        project.getFlightRegister().getFlightsList().values().stream().forEach((f) -> {
//            //openDB();
//            addFlight(f);
//        });
        //closeDB();
        closeDBDAL();
    }

    /**
     * add segment to DB
     *
     * @param segment
     */
    public void addSegment(Segment segment) {
        try {
            this.st.execute("insert into Segment(name, direction, wind_intensity, wind_Direction, node_start, node_end, project_name) "
                    + "values ('"
                    + segment.getId() + "','"
                    + segment.getDirection() + "',"
                    + segment.getWind_speed() + ","
                    + segment.getWind_direction() + ","
                    + getNodeIdByName(segment.getBeginningNode().getName()) + ","
                    + getNodeIdByName(segment.getEndNode().getName()) + ", '"
                    + this.project.getName() + "')");
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseModel.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * search the id that only DB knows
     *
     * @param name
     * @return id
     */
    public int getNodeIdByName(String name) {
        int id = 0;
        try {
            this.rs = this.st.executeQuery("SELECT * FROM NODE "
                    + "WHERE name = '" + name + "' AND "
                    + " project_name = '" + this.project.getName() + "'");
            while (rs.next()) {
                id = this.rs.getInt("node_id");
            }
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return id;
    }

    public List<Segment> getSegments(/*Project p*/) {
        List<Segment> lst_seg = new ArrayList<>();

        try {
            this.rs = this.st.executeQuery("SELECT * FROM segment WHERE project_name= '" + this.project.getName() + "'");

            while (rs.next()) {
                Node n1 = getNode(rs.getInt("Node_Start"));
                Node n2 = getNode(rs.getInt("Node_End"));
                Segment s = new Segment(rs.getString("name"),
                        n1,
                        n2,
                        null,
                        rs.getString("direction"),
                        rs.getDouble("wind_direction"),
                        rs.getDouble("wind_intensity"));
                lst_seg.add(s);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        closeDB();
        return lst_seg;
    }

    /**
     * Método utilizado para guardar dados de um modelo de um avião na base de
     * dados.
     *
     * @param air
     */
    public void addAircraftModel(AircraftModel air) {
        try {
//            this.st.execute("insert into AircraftModel(id, description, maker, type, numberMotors, motor, motorType, cruiseAltitude, cruiseSpeed, TSFC, lapseRateFactor, thrust0, thrustMaxSpeed, maxSpeed, emptyWeight, MTOW, maxPayload, fuelCapacity, VMO, MMO, wingArea, wingSpan, aspectRatio, e, project_name) "
//                    + "values ('"
//                    + air.getId() + "', '"
//                    + air.getDescription() + "', '"
//                    + air.getMaker() + "', '"
//                    + air.getType().toString() + "', "
//                    + air.getNumberMotors() + ", '"
//                    + air.getMotor() + "', '"
//                    + air.getMotorType().toString() + "', "
//                    + air.getCruiseAltitude() + ","
//                    + air.getCruiseSpeed() + ","
//                    + air.getTSFC() + ","
//                    + air.getLapseRateFactor() + ","
//                    + air.getThrust_0() + ","
//                    + air.getThrustMaxSpeed() + ","
//                    + air.getMaxSpeed() + ","
//                    + air.getEmptyWeight() + ","
//                    + air.getMTOW() + ","
//                    + air.getMaxPayload() + ","
//                    + air.getFuelCapacity() + ","
//                    + air.getVMO() + ","
//                    + air.getMMO() + ","
//                    + air.getWingArea() + ","
//                    + air.getWingSpan() + ","
//                    + air.getAspectRatio() + ","
//                    + air.getE() + ", '"
//                    + this.project.getName() + "')");

            CallableStatement cs = con.prepareCall("{call insertAircraftModel(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
            cs.setString(1, air.getId());
            cs.setString(2, air.getDescription());
            cs.setString(3, air.getMaker());
            cs.setString(4, air.getType().toString());
            cs.setDouble(5, air.getNumberMotors());
            cs.setString(6, air.getMotor());
            cs.setString(7, air.getMotorType().toString());
            cs.setDouble(8, air.getCruiseAltitude());
            cs.setDouble(9, air.getCruiseSpeed());
            cs.setDouble(10, air.getTSFC());
            cs.setDouble(11, air.getLapseRateFactor());
            cs.setDouble(12, air.getThrust_0());
            cs.setDouble(13, air.getThrustMaxSpeed());
            cs.setDouble(14, air.getMaxSpeed());
            cs.setDouble(15, air.getEmptyWeight());
            cs.setDouble(16, air.getMTOW());
            cs.setDouble(17, air.getMaxPayload());
            cs.setDouble(18, air.getFuelCapacity());
            cs.setDouble(19, air.getVMO());
            cs.setDouble(20, air.getMMO());
            cs.setDouble(21, air.getWingArea());
            cs.setDouble(22, air.getWingSpan());
            cs.setDouble(23, air.getAspectRatio());
            cs.setDouble(24, air.getE());
            cs.setString(25, this.project.getName() + "')");
            cs.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        //closeDB();
        //return getLastInsertedProjectCod();
    }

    /**
     * Método utilizado para guardar dados de um avião na base de dados.
     *
     * @param air
     */
    public void addAircraft(Aircraft air) {
        try {
            this.st.execute("insert into Aircraft(id, model, company, numberFirstClass, numberNormalClass, numberElementsCrew, project_id) "
                    + "values ('"
                    + air.getId() + "', '"
                    + air.getModel().getId() + "', "
                    + air.getNumberFirstClass() + ", "
                    + air.getNumberNormalClass() + ", "
                    + air.getNumberElementsCrew() + ", '"
                    + this.project.getName() + "')");
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        //closeDB();
        //return getLastInsertedProjectCod();
    }

    public void editAircraft(Aircraft air, String company, int nrFirstClass, int nrNormalClass, int nrElementsCrew) {
        String idAircraft = air.getId();
        try {
            this.st.execute("UPDATE Aircraft set company = '" + company
                    + "' && numberFirstClass = " + nrFirstClass
                    + " && numberNormalClass = " + nrNormalClass
                    + " && numberElements = " + nrElementsCrew
                    + " where id = '" + idAircraft + "'");
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        closeDB();
    }

    /**
     * Método utilizado para guardar dados de um aeroporto na base de dados.
     *
     * @param air
     */
    public void addAirport2(Airport air) {
        try {
//            this.st.executeQuery("INSERT INTO Airport(cod_IATA, name, town, country, latitude, longitude, altitude, project_name) "
//                    + "values('"
//                    + air.getIATAcode() + "', '"
//                    + air.getName() + "', '"
//                    + air.getTown() + "', '"
//                    + air.getCountry() + "', "
//                    + air.getLocation().getLatitude() + ","
//                    + air.getLocation().getLongitude() + ","
//                    + air.getLocation().getAltitude() + ",'"
//                    + this.project.getName() + "')");

            this.cs = con.prepareCall("{call insertAirport(?,?,?,?,?,?,?,?)}");
            this.cs.setString(1, air.getIATAcode());
            this.cs.setString(2, air.getName());
            this.cs.setString(3, air.getTown());
            this.cs.setString(4, air.getCountry());
            this.cs.setDouble(5, air.getLocation().getLatitude());
            this.cs.setDouble(6, air.getLocation().getLongitude());
            this.cs.setDouble(7, air.getLocation().getAltitude());
            this.cs.setString(8, this.project.getName());
            this.cs.executeUpdate();

        } catch (Exception ex) {
            Logger.getLogger(DatabaseModel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //FIX_ME falta adicionar esta tabela à base de dados (falta add FK project)
    public void addFlightPlan(FlightPlan fp) {
        try {
            this.st.execute("insert into FlightPlan(name, flightType, id_origion, id_dest, numberFirstClass, numberNormalClass, numberCrew)"
                    + "values ('" + fp.getName() + "', '"
                    + fp.getAircraftType().toString() + "', '"
                    + fp.getOrigin().getIATAcode() + "', '"
                    + fp.getDest().getIATAcode() + "', '"
                    + fp.getnNormalClass() + "', '"
                    + fp.getnFirstClass() + "', '"
                    + fp.getnCrew() + "', '"
            );
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseModel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<Airport> getListAirports(Project p) {
        List<Airport> lst_airports = new ArrayList<>();

        try {

            this.rs = this.st.executeQuery("SELECT * FROM AIRPORT WHERE project_name = '" + p.getName() + "'");
            while (rs.next()) {
                //FIX_ME falta saber o que fazer com Location
//                Airport a = new Airport ();
//                lst_airports.add(a);

                Airport a = new Airport(rs.getString("name"),
                        rs.getString("town"),
                        rs.getString("country"),
                        rs.getString("cod_IATA"));
                lst_airports.add(a);
                System.out.println(lst_airports);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseModel.class.getName()).log(Level.SEVERE, null, ex);
        }

        closeDB();
        return lst_airports;
    }

    public List<Node> getListNodes(Project p) {
        List<Node> lst_nodes = new ArrayList<>();

        try {
            this.rs = this.st.executeQuery("SELECT * FROM NODE WHERE project_name = '" + p.getName() + "'");
            while (rs.next()) {
                Node n = new Node(rs.getString("name"),
                        rs.getDouble("latitude"),
                        rs.getDouble("longitude"));
                lst_nodes.add(n);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseModel.class.getName()).log(Level.SEVERE, null, ex);
        }

        closeDB();
        return lst_nodes;
    }

    private Node getNode(int id_node) {
        Node n = new Node();
        Statement st2;
        ResultSet rs2;

        try {
            st2 = con.createStatement();
            rs2 = st2.executeQuery("SELECT * FROM NODE "
                    + "WHERE node_id = " + id_node
                    + " AND project_name = '" + this.project.getName() + "'");
            while (rs2.next()) {
                n = new Node(rs2.getString("name"),
                        rs2.getDouble("latitude"),
                        rs2.getDouble("longitude"));
            }

            rs2.close();
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public void addNode(Node n) {
        try {
//            this.st.execute("insert into Node(name, latitude, longitude, project_name)"
//                    + "values ('"
//                    + n.getName() + "', "
//                    + n.getLatitude() + ","
//                    + n.getLongitude() + ", '"
//                    + this.project.getName() + "')");

            CallableStatement cs = con.prepareCall("{call insertNode(?,?,?,?)}");
            cs.setString(1, n.getName());
            cs.setDouble(2, n.getLatitude());
            cs.setDouble(3, n.getLongitude());
            cs.setString(4, this.project.getName());
            cs.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(DatabaseModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        //closeDB();
    }

    public void addFlight(Flight f) {
        if (f != null) {
            try {
                this.st.execute("insert into Flight(Id, FlightPlan, Aircraft, PathTaken, TravelingTime, EnergyConsumption, Project_name)"
                        + "values ('"
                        + f.getId() + "', "
                        + f.getFlightPlan().getName() + ", "
                        + f.getAircraft().getId() + ", "
                        + f.getPathTaken() + ", "
                        + f.getTravelingTime() + ", "
                        + f.getEnergyConsumption() + ", "
                        + this.project.getName()
                        + "')");
            } catch (SQLException ex) {
                Logger.getLogger(DatabaseModel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public List<Flight> getListFlights() {
        List<Flight> lst_Flight = new ArrayList<>();
        try {
            this.rs = this.st.executeQuery("SELECT F.*, FROM FLIGHT F"
                    + "WHERE Flight.project_name = '" + this.project.getName()
                    + "'");

            while (rs.next()) {

                String id_flight = rs.getString("id_flight");
                int id_flightPlan = rs.getInt("FlightPlan");
                String aircraft_name = rs.getString("Aircraft");

                double travelingTime = rs.getInt("TravelingTime");
                double energyConsumption = rs.getInt("EnergyConsumption");

//                ArrayList<Segment> lst_s = getListSegmentByFlight(id_flight);
                ArrayList<Segment> lst_s = new ArrayList<>();

                Aircraft aircraft = this.project.getAircraftRegister().getAircraftByID(aircraft_name);
                FlightPlan flightPlan = this.project.getFlightPlanRegister().getFlightPlansList().get(id_flightPlan);

                Flight f = new Flight(id_flight, flightPlan, aircraft, lst_s, travelingTime, energyConsumption);
                lst_Flight.add(f);
            }

            addSegmentsToFlights(lst_Flight);

        } catch (SQLException ex) {
            Logger.getLogger(DatabaseModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        closeDB();
        return lst_Flight;
    }

    private void addSegmentsToFlights(List<Flight> lst_Flight) {
        try {
            for (int i = 0; i < lst_Flight.size(); i++) {
                this.rs = this.st.executeQuery("SELECT S.* FROM SEgMENT S, FLIgHTPLAN FP, FLIgHT F WHERE S.project_name = " + this.project.getName()
                        + " AND S.segment_id = FP.segment_id"
                        + "AND FP.flight_id = F.flight_id"
                        + "AND F.flight_id = " + lst_Flight.get(i).getId());

                while (rs.next()) {
                    Node n1 = getNode(rs.getInt("Node_Id_Start"));
                    Node n2 = getNode(rs.getInt("Node_Id_End"));
                    Segment s = new Segment(rs.getString("Segment_name"),
                            n1,
                            n2,
                            null,
                            rs.getString("direction"),
                            rs.getDouble("wind_direction"),
                            rs.getDouble("wind_instensity"));
                    lst_Flight.get(i).getPathTaken().add(s);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseModel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean validateName(String name) {
        for (Project p : getProjects()) {
            if (p.getName().equals(name)) {
                System.out.println("Nome de project já existe na base de dados.");
                return false;
            }
        }
        return true;
    }

    public void deleteNodesByProject(String projectID) {
        try {
            this.st.executeQuery("DELETE FROM NODE"
                    + "WHERE project_name = '" + projectID + "'");
        } catch (Exception ex) {
            System.out.println("Error: deleting nodes");
        }
    }

    public void deleteSegmentsByProject(String projectID) {
        try {
            this.st.executeQuery("DELETE FROM SEgMENT"
                    + "WHERE project_name = '" + projectID + "'");
        } catch (Exception ex) {
            System.out.println("Error: deleting segments");
        }
    }

    public void deleteAirportsByProject(String projectID) {
        try {
            this.st.executeQuery("DELETE FROM AIRPORT"
                    + "WHERE project_name = '" + projectID + "'");
        } catch (Exception ex) {
            System.out.println("Error: deleting Airports");
        }
    }

    public void deleteProject(String projectID) {
        deleteNodesByProject(projectID);
        deleteSegmentsByProject(projectID);
        deleteAirportsByProject(projectID);
        try {
            this.st.executeQuery("DELETE FROM POJECT"
                    + "WHERE project_name = '" + projectID + "'");
        } catch (Exception ex) {
            System.out.println("Error: deleting Project");
        }

    }
}
