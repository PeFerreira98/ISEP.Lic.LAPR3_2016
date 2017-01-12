/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import lapr.project.model.network.AirNetwork;
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
public class FlightTest {
    private Project project;
    private final Node bNode,eNode;
    private final Segment segment;
    private final ArrayList<Segment> listSegments;
    private final AircraftModel aircraftModel;
    private final Aircraft aircraft;
    private final FlightPlan flightplan1;
    private final Flight flight1;
    
    
    public FlightTest() {
        defaultProject();
        
        bNode = new Node("ATC02",32.3639984,-64.6787033);
        eNode = new Node("ATC03",38.7411995,-45);
       
        segment = new Segment("LSFU01",bNode,eNode,new double[1],"bidirectional",10,135);
        
        listSegments = new ArrayList<>();
        listSegments.add(segment);
        
        aircraftModel = project.getAircraftModelRegister().getAircraftModel("A380");

        aircraft = new Aircraft(aircraftModel.getId(), aircraftModel.getDescription(), 10, 5, 4, 200, 10.000, aircraftModel);
        
        flightplan1 = project.getFlightPlanRegister().getFlightPlansList().get("FP1");
        
        Flight flight = new Flight("FLIGHT",flightplan1,aircraft,listSegments,2000,3000);
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
     * Test of getId method, of class Flight.
     */
    @Test
    public void testGetId() {
        System.out.println("getId");
        Flight instance = flight1;
        String expResult = "FLIGHT";
        String result = instance.getId();
        assertEquals(expResult, result);
        
    }

    /**
     * Test of getFlightPlan method, of class Flight.
     */
    @Test
    public void testGetFlightPlan() {
        System.out.println("getFlightPlan");
        Flight instance = flight1;
        FlightPlan expResult = flightplan1;
        FlightPlan result = instance.getFlightPlan();
        assertEquals(expResult, result);
       
    }

    /**
     * Test of getAircraft method, of class Flight.
     */
    @Test
    public void testGetAircraft() {
        System.out.println("getAircraft");
        Flight instance = flight1;
        Aircraft expResult = aircraft;
        Aircraft result = instance.getAircraft();
        assertEquals(expResult, result);
        
    }

    /**
     * Test of getPathTaken method, of class Flight.
     */
    @Test
    public void testGetPathTaken() {
        System.out.println("getPathTaken");
        Flight instance = flight1;
        ArrayList<Segment> expResult = listSegments;
        ArrayList<Segment> result = instance.getPathTaken();
        assertEquals(expResult, result);
        
    }

    /**
     * Test of getTravelingTime method, of class Flight.
     */
    @Test
    public void testGetTravelingTime() {
        System.out.println("getTravelingTime");
        Flight instance = flight1;
        double expResult = 0.0;
        double result = instance.getTravelingTime();
        assertEquals(expResult, result, 2000);
       
    }

    /**
     * Test of getEnergyConsumption method, of class Flight.
     */
    @Test
    public void testGetEnergyConsumption() {
        System.out.println("getEnergyConsumption");
        Flight instance = flight1;
        double expResult = 3000;
        double result = instance.getEnergyConsumption();
        assertEquals(expResult, result, 3000);
        
    }

    
}
