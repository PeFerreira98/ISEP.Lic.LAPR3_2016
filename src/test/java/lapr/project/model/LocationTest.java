/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

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
public class LocationTest {
    
    public LocationTest() {
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
     * Test of getLatitude method, of class Location.
     */
    @Test
    public void testGetLatitude() {
        System.out.println("getLatitude");
        Location instance = new Location(41.13363, -8.61742, 150);
        double expResult = 41.13363;
        double result = instance.getLatitude();
        assertEquals(expResult, result, 41.13363);
        
    }

    /**
     * Test of setLatitude method, of class Location.
     */
    @Test
    public void testSetLatitude() {
        System.out.println("setLatitude");
        double latitude = 41.13363;
        Location instance = new Location(41.13363, -8.61742, 150);
        instance.setLatitude(latitude);
        
    }

    /**
     * Test of getLongitude method, of class Location.
     */
    @Test
    public void testGetLongitude() {
        System.out.println("getLongitude");
        Location instance = new Location(41.13363, -8.61742, 150);
        double expResult = -8.61742;
        double result = instance.getLongitude();
        assertEquals(expResult, result, -8.61742);
        
    }

    /**
     * Test of setLongitude method, of class Location.
     */
    @Test
    public void testSetLongitude() {
        System.out.println("setLongitude");
        double longitude = -8.61742;
        Location instance = new Location(41.13363, -8.61742, 150);
        instance.setLongitude(longitude);
        
    }

    /**
     * Test of getAltitude method, of class Location.
     */
    @Test
    public void testGetAltitude() {
        System.out.println("getAltitude");
        Location instance = new Location(41.13363, -8.61742, 150);
        double expResult = 150;
        double result = instance.getAltitude();
        assertEquals(expResult, result, 150);
        
    }

    /**
     * Test of setAltitude method, of class Location.
     */
    @Test
    public void testSetAltitude() {
        System.out.println("setAltitude");
        double altitude = 150;
        Location instance = new Location(41.13363, -8.61742, 150);
        instance.setAltitude(altitude);
        
    }
    
}
