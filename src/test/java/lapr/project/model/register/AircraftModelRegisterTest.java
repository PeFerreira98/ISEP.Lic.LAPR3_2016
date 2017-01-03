/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model.register;

import java.util.LinkedHashMap;
import java.util.Map;
import lapr.project.model.AircraftModel;
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
public class AircraftModelRegisterTest {
    
    public AircraftModelRegisterTest() {
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
     * Test of addAircraftModel method, of class AircraftModelRegister.
     */
    @Test
    public void testAddAircraftModel() {
        System.out.println("addAircraftModel");
        
        AircraftModel newAircraftModel = new AircraftModel("1A", "AircraftDescription", "Maker", AircraftModel.Type.MIXED, 5, "Motor", AircraftModel.MotorType.TURBOJET, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25);
        AircraftModelRegister instance = new AircraftModelRegister();
        
        AircraftModel expResult = new AircraftModel("1A", "AircraftDescription", "Maker", AircraftModel.Type.MIXED, 5, "Motor", AircraftModel.MotorType.TURBOJET, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25);
        AircraftModel result = instance.addAircraftModel(newAircraftModel);
        
        assertEquals(expResult, result);
    }

    /**
     * Test of getAircraftModelMap method, of class AircraftModelRegister.
     */
    @Test
    public void testGetAircraftModelMap() {
        System.out.println("getAircraftModelMap");
        
        AircraftModel newAircraftModel = new AircraftModel("1A", "AircraftDescription", "Maker", AircraftModel.Type.MIXED, 5, "Motor", AircraftModel.MotorType.TURBOJET, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25);
        AircraftModelRegister instance = new AircraftModelRegister();
        
        instance.addAircraftModel(newAircraftModel);
        Map<String, AircraftModel> expResult = new LinkedHashMap<>();
        expResult.put(newAircraftModel.getId(), newAircraftModel);
        Map<String, AircraftModel> result = instance.getAircraftModelMap();
        
        assertEquals(expResult, result);
    }

    /**
     * Test of equals method, of class AircraftModelRegister.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        
        AircraftModel newAircraftModel = new AircraftModel("1A", "AircraftDescription", "Maker", AircraftModel.Type.MIXED, 5, "Motor", AircraftModel.MotorType.TURBOJET, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25);
        
        Object obj = new AircraftModelRegister();
        AircraftModelRegister instance = new AircraftModelRegister();
        
        instance.addAircraftModel(newAircraftModel);
        
        boolean expResult = false;
        boolean result = instance.equals(obj);
        
        assertEquals(expResult, result);
    }
    
}
