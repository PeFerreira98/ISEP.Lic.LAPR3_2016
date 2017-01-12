/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model.register;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import lapr.project.model.Aircraft;
import lapr.project.model.AircraftModel;
import lapr.project.model.Flight;
import lapr.project.model.FlightPlan;
import lapr.project.model.Project;
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
 * @author zero_
 */
public class FlightRegisterTest {

    Project project;
    AircraftModel newAircraftModel1, newAircraftModel2;
    Aircraft a1, a2;
    FlightPlan flightPlan1, flightPlan2;
    Flight flight1, flight2;

    public FlightRegisterTest() {
        defaultProject();

        newAircraftModel1 = project.getAircraftModelRegister().getAircraftModel("777-200ER");
        newAircraftModel2 = project.getAircraftModelRegister().getAircraftModel("A380");

        a1 = new Aircraft("1A", "description", 15, 25, 7, 20, 20, newAircraftModel1);
        a2 = new Aircraft("2A", "description", 15, 25, 7, 20, 20, newAircraftModel2);

        flightPlan1 = project.getFlightPlanRegister().getFlightPlansList().get("FP1");
        flightPlan2 = project.getFlightPlanRegister().getFlightPlansList().get("FP2");

        ArrayList<Segment> listSegments = new ArrayList<Segment>();
        listSegments.add(project.getAirNetwork().getMapSegment().get("PTLS01"));

        flight1 = new Flight(flightPlan1, a1, listSegments, 0.25, 0.30);
        flight2 = new Flight(flightPlan2, a2, listSegments, 0.66, 0.55);

        System.out.println(flight1.getId() + " - " + flight2.getId());
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

        FlightPlan flightplan2 = new FlightPlan("FP2", AircraftModel.Type.PASSENGER,
                project.getAirportRegister().getAirportByIATACode("PDL"),
                project.getAirportRegister().getAirportByIATACode("LIS"),
                7, 7, 7);

        project.addFlightPlan(flightplan);
        project.addFlightPlan(flightplan2);
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

    /**
     * Test of addFlight method, of class FlightRegister.
     */
    @Test
    public void testAddFlight() {
        System.out.println("addFlight");

        Flight newFlight = flight1;
        FlightRegister instance = new FlightRegister();

        Flight expResult = flight1;
        Flight result = instance.addFlight(newFlight);

        System.out.println(result);
        System.out.println("\n" + expResult);

        assertEquals(expResult, result);
    }

    /**
     * Test of validateFlight method, of class FlightRegister.
     */
    @Test
    public void testValidateFlight() {
        System.out.println("validateFlight");

        Flight flight = flight1;
        FlightRegister instance = new FlightRegister();

        boolean expResult = true;
        boolean result = instance.validateFlight(flight);

        assertEquals(expResult, result);
    }

    /**
     * Test of getFlightsList method, of class FlightRegister.
     */
    @Test
    public void testGetFlightsList() {
        System.out.println("getFlightsList");

        FlightRegister instance = new FlightRegister();

        HashMap<String, Flight> expResult = new LinkedHashMap<>();
        HashMap<String, Flight> result = instance.getFlightsList();

        assertEquals(expResult, result);
    }

}
