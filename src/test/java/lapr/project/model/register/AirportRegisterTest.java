/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model.register;

import java.util.HashMap;
import java.util.LinkedHashMap;
import lapr.project.model.Airport;
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
public class AirportRegisterTest {

    Project project;
    AirportRegister airportRegister;
    Airport airport1, airport2;

    public AirportRegisterTest() {
        defaultProject();
        airportRegister = new AirportRegister();
        airport1 = project.getAirportRegister().getAirportByIATACode("OPO");
        airport2 = project.getAirportRegister().getAirportByIATACode("LIS");
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
     * Test of validateAirport method, of class AirportRegister.
     */
    @Test
    public void testValidateAirport() {
        System.out.println("validateAirport");

        Airport newAirport = airport1;
        AirportRegister instance = airportRegister;
        instance.addAirport(newAirport);

        boolean expResult = false;
        boolean result = instance.validateAirport(newAirport);

        assertEquals(expResult, result);
    }
    
        /**
     * Test of validateAirport method, of class AirportRegister.
     */
    @Test
    public void testValidateAirport2() {
        System.out.println("validateAirport2");

        Airport newAirport = airport1;
        AirportRegister instance = airportRegister;

        boolean expResult = true;
        boolean result = instance.validateAirport(newAirport);

        assertEquals(expResult, result);
    }

    /**
     * Test of getAirportByIATACode method, of class AirportRegister.
     */
    @Test
    public void testGetAirportByIATACode() {
        System.out.println("getAirportByIATACode");
        
        String IATAcode = "OPO";
        AirportRegister instance = airportRegister;
        instance.addAirport(airport1);
        
        Airport expResult = airport1;
        Airport result = instance.getAirportByIATACode(IATAcode);
        
        assertEquals(expResult, result);
    }

    /**
     * Test of addAirport method, of class AirportRegister.
     */
    @Test
    public void testAddAirport() {
        System.out.println("addAirport");
        
        Airport newAirport = airport1;
        AirportRegister instance = airportRegister;
        
        Airport expResult = airport1;
        Airport result = instance.addAirport(newAirport);
        
        assertEquals(expResult, result);
    }

    /**
     * Test of getAirportRegister method, of class AirportRegister.
     */
    @Test
    public void testGetAirportRegister() {
        System.out.println("getAirportRegister");
        
        AirportRegister instance = airportRegister;
        instance.addAirport(airport1);
        instance.addAirport(airport2);
        
        HashMap<String, Airport> expResult = new LinkedHashMap<>();
        expResult.put(airport1.getIATAcode(), airport1);
        expResult.put(airport2.getIATAcode(), airport2);
        
        HashMap<String, Airport> result = instance.getAirportRegister();
        
        assertEquals(expResult, result);
    }

}
