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
public class CDragTest {
    
    public CDragTest() {
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
     * Test of getSpeed method, of class CDrag.
     */
    @Test
    public void testGetSpeed() {
        System.out.println("getSpeed");
        CDrag instance = new CDrag(100,0.25);
        double expResult = 100;
        double result = instance.getSpeed();
        assertEquals(expResult, result, 100);
        
    }

    /**
     * Test of getcDrag0 method, of class CDrag.
     */
    @Test
    public void testGetcDrag0() {
        System.out.println("getcDrag0");
        CDrag instance = new CDrag(100,0.25);
        double expResult = 0.25;
        double result = instance.getcDrag0();
        assertEquals(expResult, result, 0.25);
        
    }

    
    
}
