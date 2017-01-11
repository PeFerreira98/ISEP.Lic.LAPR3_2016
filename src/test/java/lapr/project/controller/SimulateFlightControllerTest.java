/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.controller;

import lapr.project.model.Aircraft;
import lapr.project.model.AircraftModel;
import lapr.project.model.Airport;
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
 * @author Tiago
 */
public class SimulateFlightControllerTest {

    Project project;

    Aircraft aircraft;
    AircraftModel aircraftModel;

    FlightPlan flightPlan1, flightPlan2;
    Airport airportOrigin, airportDest;

    public SimulateFlightControllerTest() {
        defaultProject();

        aircraftModel = project.getAircraftModelRegister().getAircraftModel("A380");

        aircraft = new Aircraft(aircraftModel.getId(), aircraftModel.getDescription(), 10, 5, 4, 200, 10.000, aircraftModel);

        flightPlan1 = project.getFlightPlanRegister().getFlightPlansList().get("FP1");
        flightPlan2 = project.getFlightPlanRegister().getFlightPlansList().get("FP2");

        airportOrigin = project.getAirportRegister().getAirportByIATACode("OPO");
        airportDest = project.getAirportRegister().getAirportByIATACode("LIS");
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
     * Test of initializeFlightPlansList method, of class
     * SimulateFlightController.
     */
    @Test
    public void testInitializeFlightPlansList() {
        System.out.println("initializeFlightPlansList");

        SimulateFlightController instance = new SimulateFlightController(project);
        instance.initializeFlightPlansList();
    }

    /**
     * Test of getFlightPlansListSize method, of class SimulateFlightController.
     */
    @Test
    public void testGetFlightPlansListSize() {
        System.out.println("getFlightPlansListSize");

        SimulateFlightController instance = new SimulateFlightController(project);
        instance.initializeFlightPlansList();

        int expResult = 2;
        int result = instance.getFlightPlansListSize();

        assertEquals(expResult, result);
    }

    /**
     * Test of getFlightPlanNameByIndex method, of class
     * SimulateFlightController.
     */
    @Test
    public void testGetFlightPlanNameByIndex() {
        System.out.println("getFlightPlanNameByIndex");

        int index = 0;
        SimulateFlightController instance = new SimulateFlightController(project);
        instance.initializeFlightPlansList();

        String expResult = "FP1";
        String result = instance.getFlightPlanNameByIndex(index);

        assertEquals(expResult, result);

    }

    /**
     * Test of getFlightPlanByIndex method, of class SimulateFlightController.
     */
    @Test
    public void testGetFlightPlanByIndex() {
        System.out.println("getFlightPlanByIndex");

        int index = 0;
        SimulateFlightController instance = new SimulateFlightController(project);
        instance.initializeFlightPlansList();

        FlightPlan expResult = flightPlan1;
        FlightPlan result = instance.getFlightPlanByIndex(index);

        assertEquals(expResult, result);
    }

    /**
     * Test of initializeaircraftModelsList method, of class
     * SimulateFlightController.
     */
    @Test
    public void testInitializeaircraftModelsList() {
        System.out.println("initializeaircraftModelsList");

        SimulateFlightController instance = new SimulateFlightController(project);

        instance.initializeaircraftModelsList(AircraftModel.Type.PASSENGER);
    }

    /**
     * Test of getaircraftModelsListSize method, of class
     * SimulateFlightController.
     */
    @Test
    public void testGetaircraftModelsListSize() {
        System.out.println("getaircraftModelsListSize");
        FlightPlan flightPlan = new FlightPlan("Porto-Lisboa", AircraftModel.Type.PASSENGER, airportOrigin, airportDest, 10, 5, 4);
        SimulateFlightController instance = new SimulateFlightController(project);
        int expResult = 0;
        int result = instance.getaircraftModelsListSize();
        assertEquals(expResult, result);

    }

    /**
     * Test of getaircraftModelIdByIndex method, of class
     * SimulateFlightController.
     */
    @Test
    public void testGetaircraftModelIdByIndex() {
        System.out.println("getaircraftModelIdByIndex");

        int index = 1;
        SimulateFlightController instance = new SimulateFlightController(project);
        instance.initializeaircraftModelsList(AircraftModel.Type.PASSENGER);

        String expResult = "A380";
        String result = instance.getaircraftModelIdByIndex(index);

        assertEquals(expResult, result);
    }

    /**
     * Test of getaircraftModelsByIndex method, of class
     * SimulateFlightController.
     */
    @Test
    public void testGetaircraftModelsByIndex() {
        System.out.println("getaircraftModelsByIndex");
        
        int index = 1;
        SimulateFlightController instance = new SimulateFlightController(project);
        instance.initializeaircraftModelsList(AircraftModel.Type.PASSENGER);
        
        AircraftModel expResult = aircraftModel;
        AircraftModel result = instance.getaircraftModelsByIndex(index);
        
        assertEquals(expResult, result);
    }

    /**
     * Test of getAircraft method, of class SimulateFlightController.
     */
    @Test
    public void testGetAircraft() {
        System.out.println("getAircraft");

        double nNormal = 10;
        double nFirst = 5;
        double nCrew = 4;
        double cargo = 200;
        double fuel = 10.000;

        SimulateFlightController instance = new SimulateFlightController(project);
        instance.generateAircraft(nNormal, nFirst, nCrew, cargo, fuel, aircraftModel);
        
        Aircraft expResult = aircraft;
        Aircraft result = instance.getAircraft();
        
        assertEquals(expResult, result);
    }

    /**
     * Test of importCSV method, of class SimulateFlightController.
     */
    @Test
    public void testImportCSV() {
        System.out.println("importCSV");
        
        String filepath = "inOutFiles/Flight_pattern_A380_v1a.csv";
        SimulateFlightController instance = new SimulateFlightController(project);
        
        boolean expResult = true;
        boolean result = instance.importCSV(filepath);
        
        assertEquals(expResult, result);
    }

    /**
     * Test of checkMax method, of class SimulateFlightController.
     */
    @Test
    public void testCheckMax() {
        System.out.println("checkMax");
        
        FlightPlan fp = new FlightPlan("Porto-Lisboa", AircraftModel.Type.PASSENGER, airportOrigin, airportDest, 10, 5, 4);
        double nNormal = 10;
        double nFirst = 5;
        double nCrew = 4;
        SimulateFlightController instance = new SimulateFlightController(project);
        
        boolean expResult = true;
        boolean result = instance.checkMax(fp, nNormal, nFirst, nCrew);
        
        assertEquals(expResult, result);
    }

    /**
     * Test of generateAircraft method, of class SimulateFlightController.
     */
    @Test
    public void testGenerateAircraft() {
        System.out.println("generateAircraft");
        
        double nNormal = 10;
        double nFirst = 5;
        double nCrew = 4;
        double cargo = 200;
        double fuel = 10.000;
        AircraftModel aModel = project.getAircraftModelRegister().getAircraftModel("A380");
        SimulateFlightController instance = new SimulateFlightController(project);
        
        boolean expResult = true;
        boolean result = instance.generateAircraft(nNormal, nFirst, nCrew, cargo, fuel, aModel);
        
        assertEquals(expResult, result);
    }

}
