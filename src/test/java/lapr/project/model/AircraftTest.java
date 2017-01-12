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
public class AircraftTest {
    
    private Project project;
    
    private final AircraftModel aircraftModel;
    private final Aircraft aircraft;
   
    
    public AircraftTest() {
        defaultProject();
        
        aircraftModel = project.getAircraftModelRegister().getAircraftModel("A380");

        aircraft = new Aircraft(aircraftModel.getId(), aircraftModel.getDescription(), 10, 10, 10, 200, 10.000, aircraftModel);

       
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


    }

    /**
     * Test of getId method, of class Aircraft.
     */
    @Test
    public void testGetId() {
        System.out.println("getId");
        Aircraft instance = aircraft;
        String expResult = aircraft.getId();
        String result = instance.getId();
        assertEquals(expResult, result);
        
    }

    /**
     * Test of getDescription method, of class Aircraft.
     */
    @Test
    public void testGetDescription() {
        System.out.println("getDescription");
        Aircraft instance = aircraft;
        String expResult = aircraft.getDescription();
        String result = instance.getDescription();
        assertEquals(expResult, result);
       
    }

    /**
     * Test of getNumberFirstClass method, of class Aircraft.
     */
    @Test
    public void testGetNumberFirstClass() {
        System.out.println("getNumberFirstClass");
        Aircraft instance = aircraft;
        double expResult = 10;
        double result = instance.getNumberFirstClass();
        assertEquals(expResult, result, 10);
       
    }

    /**
     * Test of getNumberNormalClass method, of class Aircraft.
     */
    @Test
    public void testGetNumberNormalClass() {
        System.out.println("getNumberNormalClass");
        Aircraft instance = aircraft;
        double expResult = 10;
        double result = instance.getNumberNormalClass();
        assertEquals(expResult, result, 10);
        
    }

    /**
     * Test of getNumberElementsCrew method, of class Aircraft.
     */
    @Test
    public void testGetNumberElementsCrew() {
        System.out.println("getNumberElementsCrew");
        Aircraft instance = aircraft;
        double expResult = 10;
        double result = instance.getNumberElementsCrew();
        assertEquals(expResult, result, 10);
        
    }

    /**
     * Test of getCargo method, of class Aircraft.
     */
    @Test
    public void testGetCargo() {
        System.out.println("getCargo");
        Aircraft instance = aircraft;
        double expResult = 200;
        double result = instance.getCargo();
        assertEquals(expResult, result, 200);
       
    }

    /**
     * Test of getFuel method, of class Aircraft.
     */
    @Test
    public void testGetFuel() {
        System.out.println("getFuel");
        Aircraft instance = aircraft;
        double expResult = 10.000;
        double result = instance.getFuel();
        assertEquals(expResult, result, 10.000);
        
    }

    /**
     * Test of getModel method, of class Aircraft.
     */
    @Test
    public void testGetModel() {
        System.out.println("getModel");
        Aircraft instance = aircraft;
        AircraftModel expResult = aircraft.getModel();
        AircraftModel result = instance.getModel();
        assertEquals(expResult, result);
        
    }

    /**
     * Test of setDescription method, of class Aircraft.
     */
    @Test
    public void testSetDescription() {
        System.out.println("setDescription");
        String description = aircraft.getDescription();
        Aircraft instance = aircraft;
        instance.setDescription(description);
        
    }

    
}
