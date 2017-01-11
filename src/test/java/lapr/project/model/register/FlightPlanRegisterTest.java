/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model.register;

import java.util.HashMap;
import java.util.LinkedHashMap;
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
public class FlightPlanRegisterTest {
    
    Project project;
    FlightPlan flightPlan1, flightPlan2;

    public FlightPlanRegisterTest() {
        defaultProject();
        
        flightPlan1 = project.getFlightPlanRegister().getFlightPlansList().get("FP1");
        flightPlan2 = project.getFlightPlanRegister().getFlightPlansList().get("FP2");
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
     * Test of addFlightPlan method, of class FlightPlanRegister.
     */
    @Test
    public void testAddFlightPlan() {
        System.out.println("addFlightPlan");
        
        FlightPlan newFlightPlan = flightPlan1;
        FlightPlanRegister instance = new FlightPlanRegister();
        
        FlightPlan expResult = flightPlan1;
        FlightPlan result = instance.addFlightPlan(newFlightPlan);
        
        assertEquals(expResult, result);
    }

    /**
     * Test of getFlightPlansList method, of class FlightPlanRegister.
     */
    @Test
    public void testGetFlightPlansList() {
        System.out.println("getFlightPlansList");
        
        FlightPlanRegister instance = new FlightPlanRegister();
        instance.addFlightPlan(flightPlan1);
        instance.addFlightPlan(flightPlan2);
        
        HashMap<String, FlightPlan> expResult =  new LinkedHashMap<>();
        expResult.put(flightPlan1.getName(), flightPlan1);
        expResult.put(flightPlan2.getName(), flightPlan2);
        HashMap<String, FlightPlan> result = instance.getFlightPlansList();
        
        assertEquals(expResult, result);
    }

}
