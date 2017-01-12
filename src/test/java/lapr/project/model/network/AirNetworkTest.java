/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model.network;

import lapr.project.model.AircraftModel;
import lapr.project.model.FlightPlan;
import lapr.project.model.Project;
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
public class AirNetworkTest {

    Project project;
    AirNetwork airNetwork;

    public AirNetworkTest() {
        defaultProject();

        airNetwork = project.getAirNetwork();
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
     * Test of isReachable method, of class AirNetwork.
     */
    @Test
    public void testIsReachable() {
        System.out.println("isReachable");

        Node nOrigin = project.getAirNetwork().getNode("PT01");
        Node nEnd = project.getAirNetwork().getNode("USE01");
        AirNetwork instance = airNetwork;

        boolean expResult = true;
        boolean result = instance.isReachable(nOrigin, nEnd);

        assertEquals(expResult, result);
    }

    /**
     * Test of isReachable method, of class AirNetwork.
     */
    @Test
    public void testIsReachable2() {
        System.out.println("isReachable2");

        Node nOrigin = project.getAirNetwork().getNode("PT01");
        Node nEnd = project.getAirNetwork().getNode("USE03");
        AirNetwork instance = airNetwork;

        boolean expResult = false;
        boolean result = instance.isReachable(nOrigin, nEnd);

        assertEquals(expResult, result);
    }

}
