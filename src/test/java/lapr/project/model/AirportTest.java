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
public class AirportTest {
    
    public AirportTest() {
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
     * Test of getName method, of class Airport.
     */
    @Test
    public void testGetName() {
        System.out.println("getName");
        Location location = new Location(41.2421,-8.6786,69);
        Airport instance = new Airport("Francisco Sá Carneiro","Porto","Portugal","OPO", location);
        String expResult = "Francisco Sá Carneiro";
        String result = instance.getName();
        assertEquals(expResult, result);
        
    }

    /**
     * Test of getTown method, of class Airport.
     */
    @Test
    public void testGetTown() {
        System.out.println("getTown");
        Location location = new Location(41.2421,-8.6786,69);
        Airport instance = new Airport("Francisco Sá Carneiro","Porto","Portugal","OPO", location);
        String expResult = "Porto";
        String result = instance.getTown();
        assertEquals(expResult, result);
        
    }

    /**
     * Test of getCountry method, of class Airport.
     */
    @Test
    public void testGetCountry() {
        System.out.println("getCountry");
        Location location = new Location(41.2421,-8.6786,69);
        Airport instance = new Airport("Francisco Sá Carneiro","Porto","Portugal","OPO", location);
        String expResult = "Portugal";
        String result = instance.getCountry();
        assertEquals(expResult, result);
        
    }

    /**
     * Test of getIATAcode method, of class Airport.
     */
    @Test
    public void testGetIATAcode() {
        System.out.println("getIATAcode");
        Location location = new Location(41.2421,-8.6786,69);
        Airport instance = new Airport("Francisco Sá Carneiro","Porto","Portugal","OPO", location);
        String expResult = "OPO";
        String result = instance.getIATAcode();
        assertEquals(expResult, result);
       
    }

    /**
     * Test of getLocation method, of class Airport.
     */
    @Test
    public void testGetLocation() {
        System.out.println("getLocation");
        Location location = new Location(41.2421,-8.6786,69);
        Airport instance = new Airport("Francisco Sá Carneiro","Porto","Portugal","OPO", location);
        Location expResult = location;
        Location result = instance.getLocation();
        assertEquals(expResult, result);
       
    }

    
    
}
