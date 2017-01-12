/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.controller;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
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
public class CreateFlightPlanControllerTest {
    private Project project;
    private final List<Project> listProjects;
    private final List<Airport> listAirports;
    private final Airport airport;
    private final FlightPlan flightplan;
    private final Airport airportOrigin, airportDest;
    private final AircraftModel aircraftModel1;
  
    

    public CreateFlightPlanControllerTest() {
        
        defaultProject();
        project = new Project("proj0","proj");
        
        listProjects = new LinkedList<>();
        
        listAirports = new LinkedList<>();
        
        airport = project.getAirportRegister().getAirportByIATACode("OPO");
        
        flightplan = project.getFlightPlanRegister().getFlightPlansList().get("FP1");
        
        airportOrigin = project.getAirportRegister().getAirportByIATACode("OPO");
        airportDest = project.getAirportRegister().getAirportByIATACode("LIS");
        
        aircraftModel1 = project.getAircraftModelRegister().getAircraftModel("A380");
        
        
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
       
        Airport airport1 = new Airport("Francisco de Sá Carneiro Airport","Porto","Portugal","OPO");
        
         FlightPlan flightplan1 = new FlightPlan("FP1", AircraftModel.Type.PASSENGER,
                project.getAirportRegister().getAirportByIATACode("OPO"),
                project.getAirportRegister().getAirportByIATACode("LIS"),
                10, 10, 10);
         
        project.addAirport(airport1);
        project.addFlightPlan(flightplan1);
    }

    /**
     * Test of getAirports method, of class CreateFlightPlanController.
     */
    @Test
    public void testGetAirports() {
        System.out.println("getAirports");
        CreateFlightPlanController instance = new CreateFlightPlanController(project);
        List<Airport> expResult = listAirports;
        List<Airport> result = instance.getAirports();
        assertEquals(expResult, result);
        
    }

    /**
     * Test of saveFlightPlan method, of class CreateFlightPlanController.
     */
    @Test
    public void testSaveFlightPlan() {
        System.out.println("saveFlightPlan");
        String name = "FP1";
        String aircraftModel = "PASSENGER";
        Airport originAeroport = airportOrigin;
        Airport destinationAeroport = airportDest;
        double normalClass = 10;
        double firstClass = 10;
        double crew = 10;
        CreateFlightPlanController instance = new CreateFlightPlanController(project);
        boolean expResult = true;
        boolean result = instance.saveFlightPlan(name, aircraftModel, originAeroport, destinationAeroport, normalClass, firstClass, crew);
        assertEquals(expResult, result);
        
    }

}
