/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model.register;

import lapr.project.model.Regime;
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
public class RegimeRegisterTest {
    
    public RegimeRegisterTest() {
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
     * Test of addRegime method, of class RegimeRegister.
     */
    @Test
    public void testAddRegime() {
        System.out.println("addRegime");
        
        Regime newRegime = new Regime("Cruise", 1, 2, 3, 4);
        RegimeRegister instance = new RegimeRegister();
        
        Regime expResult = newRegime;
        Regime result = instance.addRegime(newRegime);
        
        System.out.println(expResult);
        System.out.println(result);
        
        assertEquals(expResult, result);
    }

    /**
     * Test of equals method, of class RegimeRegister.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        
        Regime newRegime = new Regime("Cruise", 1, 2, 3, 4);
        Object obj = new RegimeRegister();
        RegimeRegister instance = new RegimeRegister();
        
        instance.addRegime(newRegime);
        
        boolean expResult = false;
        boolean result = instance.equals(obj);
        
        assertEquals(expResult, result);
    }
    
}
