/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

import java.util.ArrayList;
import java.util.Date;
import lapr.project.model.Flight.FlightType;
import lapr.project.model.network.Segment;
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
    
    public FlightTest() {
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
     * Test of getAircraft method, of class Flight.
     */
//    @Test
//    public void testGetAircraft() {
//        System.out.println("getAircraft");
//        FlightType t = FlightType.regular; 
//        
//        AircraftModel newAircraftModel = new AircraftModel("1A", "AircraftDescription", "Maker", AircraftModel.Type.CARGO, 5, "Motor", AircraftModel.MotorType.TURBOPROP, null, 25, 2, 3, 3, 300, 5, 4, 40, 20, 20, 2);
//        Aircraft a1 = new Aircraft(newAircraftModel, "1A", "description", 15, 25, 7);
////        Flight instance = new Flight(a1);
//        
//        Aircraft result = instance.getAircraft();
//        Aircraft expResult = a1;
//        assertEquals(expResult, result);
//       
//    }

    /**
     * Test of getId method, of class Flight.
     */
    @Test
    public void testGetId() {
        System.out.println("getId");
        Flight instance = new Flight();
        int expResult = 0;
        int result = instance.getId();
        assertEquals(expResult, result);
        
    }

    /**
     * Test of setId method, of class Flight.
     */
    @Test
    public void testSetId() {
        System.out.println("setId");
        int id = 0;
        Flight instance = new Flight();
        instance.setId(id);
        
    }

    /**
     * Test of getType method, of class Flight.
     */
    @Test
    public void testGetType() {
        System.out.println("getType");
        Flight instance = new Flight();
        Flight.FlightType expResult = null;
        Flight.FlightType result = instance.getType();
        assertEquals(expResult, result);
        
    }

    /**
     * Test of setType method, of class Flight.
     */
    @Test
    public void testSetType() {
        System.out.println("setType");
        Flight.FlightType type = null;
        Flight instance = new Flight();
        instance.setType(type);
        
    }

    /**
     * Test of getDeparture_day method, of class Flight.
     */
    @Test
    public void testGetDeparture_day() {
        System.out.println("getDeparture_day");
        Flight instance = new Flight();
        Date expResult = null;
        Date result = instance.getDeparture_day();
        assertEquals(expResult, result);
        
    }

    /**
     * Test of setDeparture_day method, of class Flight.
     */
    @Test
    public void testSetDeparture_day() {
        System.out.println("setDeparture_day");
        Date departure_day = null;
        Flight instance = new Flight();
        instance.setDeparture_day(departure_day);
       
    }

    /**
     * Test of getMinimun_stop method, of class Flight.
     */
    @Test
    public void testGetMinimun_stop() {
        System.out.println("getMinimun_stop");
        Flight instance = new Flight();
        double expResult = 0.0;
        double result = instance.getMinimun_stop();
        assertEquals(expResult, result, 0.0);
       
    }

    /**
     * Test of setMinimun_stop method, of class Flight.
     */
    @Test
    public void testSetMinimun_stop() {
        System.out.println("setMinimun_stop");
        double minimun_stop = 0.0;
        Flight instance = new Flight();
        instance.setMinimun_stop(minimun_stop);
        
    }

    /**
     * Test of getScheduled_arrival method, of class Flight.
     */
    @Test
    public void testGetScheduled_arrival() {
        System.out.println("getScheduled_arrival");
        Flight instance = new Flight();
        Date expResult = null;
        Date result = instance.getScheduled_arrival();
        assertEquals(expResult, result);
       
    }

    /**
     * Test of setScheduled_arrival method, of class Flight.
     */
    @Test
    public void testSetScheduled_arrival() {
        System.out.println("setScheduled_arrival");
        Date scheduled_arrival = null;
        Flight instance = new Flight();
        instance.setScheduled_arrival(scheduled_arrival);
       
    }

    /**
     * Test of getFlight_plan method, of class Flight.
     */
    @Test
    public void testGetFlight_plan() {
        System.out.println("getFlight_plan");
        Flight instance = new Flight();
        ArrayList<Segment> expResult = null;
        ArrayList<Segment> result = instance.getFlight_plan();
        assertEquals(expResult, result);
       
    }

    /**
     * Test of setFlight_plan method, of class Flight.
     */
    @Test
    public void testSetFlight_plan() {
        System.out.println("setFlight_plan");
        ArrayList<Segment> flight_plan = null;
        Flight instance = new Flight();
        instance.setFlight_plan(flight_plan);
        
    }

  
}
