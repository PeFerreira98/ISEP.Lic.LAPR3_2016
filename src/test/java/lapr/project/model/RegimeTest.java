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
public class RegimeTest {
    
    public RegimeTest() {
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
     * Test of getId method, of class Regime.
     */
    @Test
    public void testGetId() {
        System.out.println("getId");
        Regime instance = new Regime("1RA", 2, 345, 100, 95);
        String expResult = "1RA";
        String result = instance.getId();
        assertEquals(expResult, result);
        
    }

    /**
     * Test of setId method, of class Regime.
     */
    @Test
    public void testSetId() {
        System.out.println("setId");
        String id = "1RA";
        Regime instance = new Regime("1RA", 2, 345, 100, 95);
        instance.setId(id);
        
    }

    /**
     * Test of getTSFC method, of class Regime.
     */
    @Test
    public void testGetTSFC() {
        System.out.println("getTSFC");
        Regime instance = new Regime("1RA", 2, 345, 100, 95);
        double expResult = 2;
        double result = instance.getTSFC();
        assertEquals(expResult, result, 2);
        
    }

    /**
     * Test of setTSFC method, of class Regime.
     */
    @Test
    public void testSetTSFC() {
        System.out.println("setTSFC");
        double TSFC = 2;
        Regime instance = new Regime("1RA", 2, 345, 100, 95);
        instance.setTSFC(TSFC);
        
    }

    /**
     * Test of getSpeed method, of class Regime.
     */
    @Test
    public void testGetSpeed() {
        System.out.println("getSpeed");
        Regime instance = new Regime("1RA", 2, 345, 100, 95);
        double expResult = 345;
        double result = instance.getSpeed();
        assertEquals(expResult, result, 345);
       
    }

    /**
     * Test of setSpeed method, of class Regime.
     */
    @Test
    public void testSetSpeed() {
        System.out.println("setSpeed");
        double speed = 345;
        Regime instance = new Regime("1RA", 2, 345, 100, 95);
        instance.setSpeed(speed);
        
    }

    /**
     * Test of getThrust method, of class Regime.
     */
    @Test
    public void testGetThrust() {
        System.out.println("getThrust");
        Regime instance = new Regime("1RA", 2, 345, 100, 95);
        double expResult = 100;
        double result = instance.getThrust();
        assertEquals(expResult, result, 100);
        
    }

    /**
     * Test of setThrust method, of class Regime.
     */
    @Test
    public void testSetThrust() {
        System.out.println("setThrust");
        double thrust = 100;
        Regime instance = new Regime("1RA", 2, 345, 100, 95);
        instance.setThrust(thrust);
        
    }

    /**
     * Test of getAltitude method, of class Regime.
     */
    @Test
    public void testGetAltitude() {
        System.out.println("getAltitude");
        Regime instance = new Regime("1RA", 2, 345, 100, 95);
        double expResult = 95;
        double result = instance.getAltitude();
        assertEquals(expResult, result, 95);
        
    }

    /**
     * Test of setAltitude method, of class Regime.
     */
    @Test
    public void testSetAltitude() {
        System.out.println("setAltitude");
        double altitude = 95;
        Regime instance = new Regime("1RA", 2, 345, 100, 95);
        instance.setAltitude(altitude);
        
    }

}
