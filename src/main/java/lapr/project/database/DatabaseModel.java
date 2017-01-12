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
    public static final String DBPASS = "20ftw";
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

    // <editor-fold defaultstate="collapsed" desc=" LISTS ">
    /**
     * Método utilizado para receber todos os projectos da base de dados.(DAL)
     *
     * @return
     */
    public List<Project> getProjects() {
        List<Project> list_projects = new ArrayList<>();

        try {

            this.cs = this.con.prepareCall("{ ? = call GETALLPROJECTS() }");
            cs.registerOutParameter(1, OracleTypes.CURSOR);
            cs.execute();

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

    /**
     * returns the list of segments (DAL)
     *
     * @return
     */
    //FIXME falta meter o metodo getNode atraves de DAL
    public List<Segment> getSegments() {
        List<Segment> lst_seg = new ArrayList<>();

        try {
            this.cs = this.con.prepareCall("{ ? = call getProjectSegments(?) }");
            cs.setString(2, this.project.getName());
            cs.registerOutParameter(1, OracleTypes.CURSOR);
            cs.execute();

            ResultSet cs1 = (ResultSet) cs.getObject(1);

            while (cs1.next()) {
                Node n1 = getNode(cs1.getInt("Node_Start"));
                Node n2 = getNode(cs1.getInt("Node_End"));
                Segment s = new Segment(cs1.getString("name"),
                        n1,
                        n2,
                        null,
                        cs1.getString("direction"),
                        cs1.getDouble("wind_direction"),
                        cs1.getDouble("wind_intensity"));
                lst_seg.add(s);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        closeDBDAL();
        return lst_seg;
    }

    /**
     * return the list of aircraft models (DAL)
     *
     * @return
     */
    public List<AircraftModel> getListAircraftModels() {
        List<AircraftModel> lst_a = new ArrayList<>();

        try {
            cs = con.prepareCall("{ ? = call getProjectAircraftModels(?) }");
            cs.setString(2, this.project.getName());
            cs.registerOutParameter(1, OracleTypes.CURSOR);
            cs.execute();

            ResultSet cs1 = (ResultSet) cs.getObject(1);

            while (cs1.next()) {
                AircraftModel am = new AircraftModel(cs1.getString("name"), cs1.getString("description"), cs1.getString("maker"),
                        AircraftModel.Type.valueOf(cs1.getString("type")), cs1.getDouble("number_motors"), cs1.getString("motor"),
                        AircraftModel.MotorType.valueOf(cs1.getString("motor_type")), cs1.getDouble("cruise_altitude"),
                        cs1.getDouble("cruise_speed"), cs1.getDouble("TSFC"), cs1.getDouble("lapse_Rate_Factor"),
                        cs1.getDouble("thrust_0"), cs1.getDouble("thrust_Max_Speed"), cs1.getDouble("max_Speed"), cs1.getDouble("eWeight"),
                        cs1.getDouble("MTOW"), cs1.getDouble("max_payload"), cs1.getDouble("fuel_capacity"), cs1.getDouble("VMO"),
                        cs1.getDouble("MMO"), cs1.getDouble("wing_area"), cs1.getDouble("wing_span"), cs1.getDouble("aspect_ratio"),
                        cs1.getDouble("e"));
                lst_a.add(am);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        closeDBDAL();
        return lst_a;
    }

    /**
     * return the list of airports by project (DAL)
     *
     * @param p
     * @return
     */
    public List<Airport> getListAirports(Project p) {
        List<Airport> lst_airports = new ArrayList<>();

        try {

            this.cs = this.con.prepareCall("{ ? = call GETPROJECTAIRPORTS(?) }");
            cs.setString(2, p.getName());
            cs.registerOutParameter(1, OracleTypes.CURSOR);
            cs.execute();

            ResultSet cs1 = (ResultSet) cs.getObject(1);

            while (cs1.next()) {
                Location l = new Location(cs1.getDouble("latitude"), cs1.getDouble("longitude"), cs1.getDouble("altitude"));
                Airport a = new Airport(cs1.getString("name"),
                        cs1.getString("town"),
                        cs1.getString("country"),
                        cs1.getString("cod_IATA"), l);
                lst_airports.add(a);

            }
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseModel.class.getName()).log(Level.SEVERE, null, ex);
        }

        closeDBDAL();
        return lst_airports;
    }

    /**
     * return the list of nodes by project(DAL)
     *
     * @param p
     * @return
     */
    public List<Node> getListNodes(Project p) {
        List<Node> lst_nodes = new ArrayList<>();

        try {
            this.cs = this.con.prepareCall("{ ? = call GETPROJECTNODES(?) }");

            this.cs.setString(2, p.getName());
            cs.registerOutParameter(1, OracleTypes.CURSOR);
            cs.execute();

            ResultSet cs1 = (ResultSet) cs.getObject(1);

            while (cs1.next()) {
                Node n = new Node(cs1.getString("name"),
                        cs1.getDouble("latitude"),
                        cs1.getDouble("longitude"));
                lst_nodes.add(n);
            }

        } catch (Exception ex) {
            Logger.getLogger(DatabaseModel.class.getName()).log(Level.SEVERE, null, ex);
        }

        closeDBDAL();
        return lst_nodes;
    }

    //Falta DAL
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

// </editor-fold>
    // <editor-fold defaultstate="collapsed" desc=" INSERTS">
    /**
     * add the project and the lists to Database.(DAL)
     *
     * @param project
     */
    public void addProject(Project project) {

        int flag = 0;
        try {
            cs = con.prepareCall("{call insertProject(?,?)}");
            cs.setString(1, project.getName());
            cs.setString(2, project.getDescription());
            cs.executeUpdate();
            this.project = project;

            if (!project.getAirNetwork().getMapNodes().isEmpty()) {
                project.getAirNetwork().getMapNodes().values().stream().forEach((n) -> {
                    addNode(n);
                });
                flag = 1;
            }

            if (!project.getAirNetwork().getMapSegment().isEmpty()) {
                project.getAirNetwork().getMapSegment().values().stream().forEach((s) -> {
                    addSegment(s);
                });
                flag = 1;
            }

            if (!project.getAirportRegister().getAirportRegister().isEmpty()) {
                project.getAirportRegister().getAirportRegister().values().stream().forEach((airport) -> {
                    addAirport2(airport);
                });
                flag = 1;
            }

            if (!project.getAircraftModelRegister().getAircraftModelMap().isEmpty()) {
                project.getAircraftModelRegister().getAircraftModelMap().values().stream().forEach((airModel) -> {
                    addAircraftModel(airModel);
                });
                flag = 1;
            }
            if (!project.getFlightPlanRegister().getFlightPlansList().isEmpty()) {
                project.getFlightRegister().getFlightsList().values().stream().forEach((f) -> {
                    addFlight(f);
                });
                flag = 1;
            }

        } catch (SQLException ex) {
            Logger.getLogger(DatabaseModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (flag == 1) {
            closeDBDAL();
        }
    }

    /**
     * add segment to DB
     *
     * @param segment
     */
    public void addSegment(Segment segment) {
        try {

            cs = con.prepareCall("{call insertSegment(?,?,?,?,?,?,?)");
            cs.setString(1, segment.getId());
            cs.setString(2, segment.getDirection());
            cs.setDouble(3, segment.getWind_speed());
            cs.setDouble(4, segment.getWind_direction());
            cs.setInt(5, getNodeIdByName(segment.getBeginningNode().getName()));
            cs.setInt(6, getNodeIdByName(segment.getEndNode().getName()));
            cs.setString(7, this.project.getName());

            cs.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseModel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Método utilizado para guardar dados de um modelo de um avião na base de
     * dados(DAL)
     *
     * @param air
     */
    public void addAircraftModel(AircraftModel air) {
        try {
            cs = con.prepareCall("{call insertAircraftModel(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
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
            cs.setString(25, this.project.getName());
            cs.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseModel.class.getName()).log(Level.SEVERE, null, ex);
        }
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

    /**
     * Método utilizado para guardar dados de um aeroporto na base de dados.
     *
     * @param air
     */
    public void addAirport2(Airport air) {
        try {
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
    
    /**
     * metodo que adiciona flight plans à base de dados.(DAL)
     * @param fp 
     */
    public void addFlightPlan(FlightPlan fp) {
        try {
            cs = con.prepareCall("{ call insertFlightPlan(?,?,?,?,?,?,?) }");
            cs.setString(1, fp.getName());
            cs.setString(2, fp.getAircraftType().toString());
            cs.setDouble(3, fp.getnNormalClass());
            cs.setDouble(4, fp.getnFirstClass());
            cs.setDouble(5, fp.getnCrew());
            cs.setInt(6, getAirportId(fp.getOrigin().getIATAcode()));
            cs.setInt(7, getAirportId(fp.getDest().getIATAcode()));
            cs.setString(8, this.project.getName());
            cs.registerOutParameter(1, OracleTypes.CURSOR);

            ResultSet cs1 = (ResultSet) cs.getObject(1);
            while (cs1.next()) {

            }
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseModel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * add Node to Database (DAL)
     *
     * @param n
     */
    public void addNode(Node n) {
        try {
            CallableStatement cs = con.prepareCall("{call insertNode(?,?,?,?)}");
            cs.setString(1, n.getName());
            cs.setDouble(2, n.getLatitude());
            cs.setDouble(3, n.getLongitude());
            cs.setString(4, this.project.getName());
            cs.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(DatabaseModel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Metodo utilizado para guardar dados de um flight na base de dados.
     *
     * @param f
     */
    public void addFlight(Flight f) {
        if (f != null) {
            try {
                cs = con.prepareCall("{call insertFlight(?,?,?,?,?,?) }");
                cs.setString(1, f.getId());
                cs.setDouble(2, f.getTravelingTime());
                cs.setDouble(3, f.getEnergyConsumption());
                cs.setInt(4, getFlightPlanId(f.getFlightPlan().getName())); //flightplan ID
                cs.setInt(5, getAircraftId(f.getAircraft().getId())); //aircraft id
                cs.setString(6, this.project.getName());
            } catch (SQLException ex) {
                Logger.getLogger(DatabaseModel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

// </editor-fold>
    // <editor-fold defaultstate="collapsed" desc=" EDITS ">
    //FIXME falta DAL
    public void EditAirport(String iata, String name, String town, String country, double latitude, double longitude, double altitude) {
        try {
            this.rs = this.st.executeQuery("UPDATE AIRPORT set name = '" + name
                    + "' && town = '" + town
                    + "' && country = '" + country
                    + "' && latitude = " + latitude
                    + " && longitude = " + longitude
                    + " && altitude = " + altitude
                    + " WHERE project_name = '" + this.project.getName() + "' "
                    + "AND cod_iata = '" + iata);

            this.rs.close();

        } catch (Exception ex) {

        }
    }

    public void EditAircraftModel(String id, String description, String maker, String type, String motor, double numberMotors, String motorType, double cruiseAltitude, double cruiseSpeed, double TSFC, double lapseRateFactor,
            double thrust_0,
            double thrustMaxSpeed,
            double maxSpeed,
            double emptyWeight,
            double MTOW,
            double maxPayload,
            double fuelCapacity,
            double VMO,
            double MMO,
            double wingArea,
            double wingSpan,
            double aspectRatio,
            double e) {

    }

    // </editor-fold>
    
    
    
    
    /**
     * return Node by id
     *
     * @param id_node
     * @return
     */
    private Node getNode(int id_node) {
        Node n = new Node();
        Statement st2;
        ResultSet rs2;

        try {
            cs = con.prepareCall("{ ? = call getNodeById(?) }");
            cs.setInt(2, id_node);
            cs.registerOutParameter(1, OracleTypes.CURSOR);
            cs.execute();

            ResultSet cs1 = (ResultSet) cs.getObject(1);

            while (cs1.next()) {
                n = new Node(cs1.getString("name"),
                        cs1.getDouble("latitude"),
                        cs1.getDouble("longitude"));
            }

        } catch (SQLException ex) {
            Logger.getLogger(DatabaseModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
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
            CallableStatement cs1;
            cs1 = con.prepareCall("{ ? = call getNodeIndex(?,?) }");
            cs1.setString(2, name);
            cs1.setString(3, this.project.getName());
            cs1.registerOutParameter(1, java.sql.Types.INTEGER);
            cs1.execute();

            id = cs1.getInt(1);

        } catch (SQLException ex) {
            Logger.getLogger(DatabaseModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return id;
    }

    private int getAirportId(String iataCode) {
        int id = 0;
        try {
            cs = con.prepareCall("{ ? = call getAirportIndex(?,?) }");
            cs.setString(2, iataCode);
            cs.setString(3, this.project.getName());
            cs.registerOutParameter(1, OracleTypes.INTEGER);
            cs.execute();
            
            id = cs.getInt(1);
        } catch (Exception ex) {

        }
        return id;
    }

    private int getFlightPlanId(String fName){
        int id = 0;
        try{
            cs = con.prepareCall("{? = getFlightPlanIndex(?,?) }");
            cs.setString(2, fName);
            cs.setString(3, this.project.getName());
            cs.registerOutParameter(1, OracleTypes.INTEGER);
            cs.execute();
            
            id = cs.getInt(1);
        }catch(Exception ex){
            
        }
        return id;
    }
    
    private int getAircraftId(String aName){
        int id = 0;
        try{
            cs = con.prepareCall("{ ? = getAircraftIndex(?,?) }");
            cs.setString(2, aName);
            cs.setString(3, this.project.getName());
            cs.registerOutParameter(1, OracleTypes.INTEGER);
            cs.execute();
            
            id = cs.getInt(1);
        }catch(Exception ex){
            
        }
        return id;
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
                System.out.println("Nome de projecto já existe na base de dados.");
                return false;
            }
        }
        return true;
    }
}
