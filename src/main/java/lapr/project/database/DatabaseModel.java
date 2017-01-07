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

    /**
     * Método utilizado para receber todos os projectos da base de dados.
     *
     * @return
     */
    public List<Project> getProjects() {
        List<Project> list_projects = new ArrayList<>();

        try {
            this.rs = this.st.executeQuery("Select * From PROJECT");
            while (rs.next()) {
                Project p = new Project(rs.getString("NAME"), rs.getString("DESCRIPTION"));
                list_projects.add(p);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseModel.class.getName()).log(Level.SEVERE, null, ex);
        }

        closeDB();
        return list_projects;
    }

    public void addProject(Project project) {
        try {
            this.rs = this.st.executeQuery("insert into Project(name, description) "
                    + "values ('"
                    + project.getName() + "', '"
                    + project.getDescription() + "')");
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        project.getAirNetwork().getMapSegment().values().stream().forEach((s) -> {
            addSegment(s);
        });
        project.getAirNetwork().getMapNodes().values().stream().forEach((n) -> {
            //openDB();
            addNode(n);
        });
        project.getAirportRegister().getAirportRegister().values().stream().forEach((airport) -> {
            //openDB();
            addAirport(airport);
        });
        project.getAircraftRegister().getAircraftRegister().values().stream().forEach((aircraft) -> {
            //openDB();
            addAircraft(aircraft);
        });
        project.getAircraftModelRegister().getAircraftModelMap().values().stream().forEach((airModel) -> {
            //openDB();
            addAircraftModel(airModel);
        });
        project.getFlightRegister().getFlightsList().values().stream().forEach((f) -> {
            //openDB();
            addFlight(f);
        });
        closeDB();
        //return getLastInsertedProjectCod();
    }

    public void addSegment(Segment segment) {
        try {
            this.st.execute("insert into Segment(id, bNode, eNode, direction, windDirection, windSpeed, distance, project_name) "
                    + "values ('"
                    + segment.getId() + "', '"
                    + segment.getBeginningNode() + "', '"
                    + segment.getEndNode() + "', '"
                    + segment.getDirection() + "', '"
                    + segment.getWind_speed() + "', '"
                    + segment.getDistance() + "', '"
                    + this.project.getName() + "')'");
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        //closeDB();
        //return getLastInsertedProjectCod();
    }

    public List<Segment> getSegments(/*Project p*/) {
        List<Segment> lst_seg = new ArrayList<>();

        try {
            this.rs = this.st.executeQuery("SELECT * FROM segment WHERE project_name= " + this.project.getName());

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
            this.st.execute("insert into AircraftModel(id, description, maker, type, numberMotors, motor, motorType, cruiseAltitude, cruiseSpeed, TSFC, lapseRateFactor, thrust0, thrustMaxSpeed, maxSpeed, emptyWeight, MTOW, maxPayload, fuelCapacity, VMO, MMO, wingArea, wingSpan, aspectRatio, e, project_id) "
                    + "values ('"
                    + air.getId() + "', '"
                    + air.getDescription() + "', '"
                    + air.getMaker() + "', '"
                    + air.getType().toString() + "', '"
                    + air.getNumberMotors() + "', '"
                    + air.getMotor() + "', '"
                    + air.getMotorType().toString() + "', '"
                    + air.getCruiseAltitude() + "', '"
                    + air.getCruiseSpeed() + "', '"
                    + air.getTSFC() + "', '"
                    + air.getLapseRateFactor() + "', '"
                    + air.getThrust_0() + "', '"
                    + air.getThrustMaxSpeed() + "', '"
                    + air.getMaxSpeed() + "', '"
                    + air.getEmptyWeight() + "', '"
                    + air.getMTOW() + "', '"
                    + air.getMaxPayload() + "', '"
                    + air.getFuelCapacity() + "', '"
                    + air.getVMO() + "', '"
                    + air.getMMO() + "', '"
                    + air.getWingArea() + "', '"
                    + air.getWingSpan() + "', '"
                    + air.getAspectRatio() + "', '"
                    + air.getE() + "', '"
                    + this.project.getName() + "')");
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
                    + air.getModel().getId() + "', '"
                    + air.getNumberFirstClass() + "', '"
                    + air.getNumberNormalClass() + "', '"
                    + air.getNumberElementsCrew() + "','"
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
            this.st.execute("UPDATE Aircraft set company = " + company
                    + " && numberFirstClass = " + nrFirstClass
                    + " && numberNormalClass = " + nrNormalClass
                    + " && numberElements = " + nrElementsCrew
                    + " where id = " + idAircraft);
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        closeDB();
    }

    /**
     * Método utilizado para guardar dados de um aeroporto na base de dados.
     *
     * @param ap
     */
    public void addAirport(Airport ap) {
        try {
            this.st.execute("insert into Airport(IATAcode, name, town, country, latitude, longitude, altitude, project_name) "
                    + "values ('" + ap.getIATAcode() + "', '"
                    + ap.getName() + "', '"
                    + ap.getTown() + "', '"
                    + ap.getCountry() + "', '"
                    + ap.getLocation().getLatitude() + "', '"
                    + ap.getLocation().getLongitude() + "', '"
                    + ap.getLocation().getAltitude() + "', '"
                    + this.project.getName() + "')");
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        //closeDB();
        //return getLastInsertedProjectCod();
    }

    
    //FIX_ME falta adicionar esta tabela à base de dados (falta add FK project)
    public void addFlightPlan(FlightPlan fp) {
        try {
            this.st.execute("insert into FlightPlan(name, flightType, id_origion, id_dest, numberFirstClass, numberNormalClass, numberCrew)"
                    + "values ('" + fp.getName() + "', '"
                    + fp.getAircraftType().toString() + "', '"
                    + fp.getOrigin().getIATAcode() + "', '"
                    + fp.getDest().getIATAcode()+ "', '"
                    + fp.getnNormalClass() + "', '"
                    + fp.getnFirstClass() + "', '"
                    + fp.getnCrew() + "', '"
            );
        }catch (SQLException ex) {
            Logger.getLogger(DatabaseModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        //closeDB();
    }

    public ArrayList<Airport> getListAirports() {
        List<Airport> lst_airports = new ArrayList<>();

        try {
            this.rs = this.st.executeQuery("SELECT * FROM AIRPORT WHERE project_name =" + this.project.getName());
            while (rs.next()) {
                //FIX_ME falta saber o que fazer com Location
//                Airport a = new Airport ();
//                lst_airports.add(a);

                Airport a = new Airport(rs.getString("name"),
                        rs.getString("town"),
                        rs.getString("country"),
                        rs.getString("IATAcode"));
                lst_airports.add(a);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseModel.class.getName()).log(Level.SEVERE, null, ex);
        }

        closeDB();
        return (ArrayList<Airport>) lst_airports;
    }

    public List<Node> getListNodes(Project p) {
        List<Node> lst_nodes = new ArrayList<>();

        try {
            this.rs = this.st.executeQuery("SELECT * FROM NODE WHERE project_name =" + p.getName());
            while (rs.next()) {
                Node n = new Node(rs.getString("node_name"),
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
                    + "WHERE name = " + id_node
                    + " AND project_name = " + this.project.getName());

            n = new Node(rs2.getString("name"),
                    rs2.getDouble("latitude"),
                    rs2.getDouble("longitude"));

            rs2.close();
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public void addNode(Node n) {
        try {
            this.st.execute("inser into Node(id, latitude, longitude, project_name)"
                    + "values ('"
                    + n.getName() + "', '"
                    + n.getLatitude() + "', '"
                    + n.getLongitude() + "', '"
                    + this.project.getName() + "')'");
//            "')'"
//            + "WHERE project_id = " + p.getId());
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        //closeDB();
    }

    public void addFlight(Flight f) {
        if (f != null) {
            try {
                this.st.execute("inser into Flight(Type, Departure_day,Minimun_stop,Scheduled_arrival, Flight_plan,Project_name,Airport_id_Start,Airport_id_End, Aircraft_id) "
                        + "values (" + f.getType() + "', '"
                        + f.getDeparture_day() + "', '"
                        + f.getMinimun_stop() + "', '"
                        + f.getScheduled_arrival() + "', '"
                        + f.getFlight_plan() + "', '"
                        + this.project.getName() + "', '"
                        + f.getFlight_plan().get(0).getBeginningNode() + "', '"
                        + f.getFlight_plan().get(f.getFlight_plan().size()).getEndNode() + "', '"
                        + f.getAircraft().getId() + "')'");
            } catch (SQLException ex) {
                Logger.getLogger(DatabaseModel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

//    private ArrayList<Segment> getListSegmentByFlight(int id_flight){
//        ArrayList<Segment> lst_segment = new ArrayList<>();
//        Statement st2;
//        ResultSet rs2;
//        try {
//            
//            st2 = con.createStatement();
//            
//            rs2 = st2.executeQuery("SELECT Segment.* FROM FlightPlan, Segment WHERE FlightPlan.id_fligt = " + id_flight
//            + " AND FlightPlan.id_segment = Segment.id_segment");
//            
//            while(rs.next()){
//                Node n1 = getNode(rs2.getInt("Node_Id_Start"));
//                Node n2 = getNode(rs2.getInt("Node_Id_End"));
//                Segment s = new Segment(rs2.getString("Segment_name"),
//                                n1,
//                                n2,
//                                null,
//                                rs2.getString("direction"),
//                                rs2.getDouble("wind_direction"),
//                                rs2.getDouble("wind_instensity"));
//                lst_segment.add(s);
//            }
//            
//            rs2.close();
//        } catch (SQLException ex) {
//            Logger.getLogger(DatabaseModel.class.getName()).log(Level.SEVERE, null, ex);
//        }
//               
//        return lst_segment;
//    }
    public List<Flight> getListFlights() {
        List<Flight> lst_Flight = new ArrayList<>();
        try {
            this.rs = this.st.executeQuery("SELECT F.*, A.description FROM FLIgHT F, Aircraft A "
                    + "WHERE Fliht.project_name = " + this.project.getName()
                    + " And F.aircraft_id = A.aircraft_id");

            while (rs.next()) {
                int id_flight = rs.getInt("id_flight");
                FlightType type = FlightType.valueOf(rs.getString("Type"));
                Date departure_day = rs.getDate("departure_day");
                double minimun_stop = rs.getDouble("mininum_stop");
                Date shedule_arrival = rs.getDate("shedule_day");
                String aircraft_name = rs.getString("description");

                //ArrayList<Segment> lst_s = getListSegmentByFlight(id_flight);
                Aircraft aircraft = this.project.getAircraftRegister().getAircraftByID(aircraft_name);
                Flight f = new Flight(id_flight, type, departure_day, minimun_stop, shedule_arrival, aircraft);
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
                    lst_Flight.get(i).getFlight_plan().add(s);
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
}
