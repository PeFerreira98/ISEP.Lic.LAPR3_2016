/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

import java.util.ArrayList;
import lapr.project.model.network.Node;
import lapr.project.model.network.Segment;
import lapr.project.utils.AircraftStAXParser;
import lapr.project.utils.AirportStAXParser;
import lapr.project.utils.NetworkStAXParser;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Tiago
 */
public class FlightPlanTest {

    private Project project;
    private final Node bNode, eNode;
    private final Segment segment;
    private final ArrayList<Segment> listSegments;
    private final AircraftModel aircraftModel;
    private final Aircraft aircraft;
    private final FlightPlan flightplan1;
    private final Flight flight1;

    public FlightPlanTest() {
        defaultProject();

        bNode = new Node("ATC02", 32.3639984, -64.6787033);
        eNode = new Node("ATC03", 38.7411995, -45);

        segment = new Segment("LSFU01", bNode, eNode, new double[1], "bidirectional", 10, 135);

        listSegments = new ArrayList<>();
        listSegments.add(segment);

        aircraftModel = project.getAircraftModelRegister().getAircraftModel("A380");

        aircraft = new Aircraft(aircraftModel.getId(), aircraftModel.getDescription(), 10, 5, 4, 200, 10.000, aircraftModel);

        flightplan1 = project.getFlightPlanRegister().getFlightPlansList().get("FP1");

        Flight flight = new Flight("FLIGHT", flightplan1, aircraft, listSegments, 2000, 3000);
        project.addFlight(flight);

        flight1 = project.getFlightRegister().getFlightByID("FLIGHT");
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    private void defaultProject() {
        project = new Project("proj0", "proj");

        NetworkStAXParser network = new NetworkStAXParser(project);
        AircraftStAXParser instance = new AircraftStAXParser(project);
        AirportStAXParser airports = new AirportStAXParser(project);

        network.XMLReader("inOutFiles/TestSet02_Network.xml");
        instance.XMLReader("inOutFiles/TestSet02_Aircraft.xml");
        airports.XMLReader("inOutFiles/TestSet02_Airports.xml");

        FlightPlan flightplan = new FlightPlan("FP1", AircraftModel.Type.PASSENGER,
                project.getAirportRegister().getAirportByIATACode("OPO"),
                project.getAirportRegister().getAirportByIATACode("LIS"),
                10, 10, 10);

        project.addFlightPlan(flightplan);

    }

    /**
     * Test of getId method, of class FlightPlan.
     */
    @Test
    public void testGetId() {
        System.out.println("getId");
        FlightPlan instance = flightplan1;
        int expResult = 0;
        int result = instance.getId();
        assertEquals(expResult, result);

    }

    /**
     * Test of setId method, of class FlightPlan.
     */
    @Test
    public void testSetId() {
        System.out.println("setId");
        int id = 0;
        FlightPlan instance = flightplan1;
        instance.setId(id);

    }

    /**
     * Test of getName method, of class FlightPlan.
     */
    @Test
    public void testGetName() {
        System.out.println("getName");
        FlightPlan instance = flightplan1;
        String expResult = "FP1";
        String result = instance.getName();
        assertEquals(expResult, result);

    }

    /**
     * Test of getAircraftType method, of class FlightPlan.
     */
    @Test
    public void testGetAircraftType() {
        System.out.println("getAircraftType");
        FlightPlan instance = flightplan1;
        AircraftModel.Type expResult = aircraftModel.getType();
        AircraftModel.Type result = instance.getAircraftType();
        assertEquals(expResult, result);

    }

    /**
     * Test of getOrigin method, of class FlightPlan.
     */
    @Test
    public void testGetOrigin() {
        System.out.println("getOrigin");
        FlightPlan instance = flightplan1;
        Airport expResult = flightplan1.getOrigin();
        Airport result = instance.getOrigin();
        assertEquals(expResult, result);

    }

    /**
     * Test of getDest method, of class FlightPlan.
     */
    @Test
    public void testGetDest() {
        System.out.println("getDest");
        FlightPlan instance = flightplan1;
        Airport expResult = flightplan1.getDest();
        Airport result = instance.getDest();
        assertEquals(expResult, result);

    }

    /**
     * Test of getnNormalClass method, of class FlightPlan.
     */
    @Test
    public void testGetnNormalClass() {
        System.out.println("getnNormalClass");
        FlightPlan instance = flightplan1;
        double expResult = 10;
        double result = instance.getnNormalClass();
        assertEquals(expResult, result, 10);

    }

    /**
     * Test of getnFirstClass method, of class FlightPlan.
     */
    @Test
    public void testGetnFirstClass() {
        System.out.println("getnFirstClass");
        FlightPlan instance = flightplan1;
        double expResult = 10;
        double result = instance.getnFirstClass();
        assertEquals(expResult, result, 10);

    }

    /**
     * Test of getnCrew method, of class FlightPlan.
     */
    @Test
    public void testGetnCrew() {
        System.out.println("getnCrew");
        FlightPlan instance = flightplan1;
        double expResult = 10;
        double result = instance.getnCrew();
        assertEquals(expResult, result, 10);

    }

}
