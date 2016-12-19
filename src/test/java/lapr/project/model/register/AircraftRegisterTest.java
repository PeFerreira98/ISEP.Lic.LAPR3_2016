/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model.register;

import com.sun.org.apache.bcel.internal.generic.AALOAD;
import java.util.LinkedHashMap;
import java.util.Map;
import lapr.project.model.Aircraft;
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
public class AircraftRegisterTest {
    
    public AircraftRegisterTest() {
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
     * Test of addAircraft method, of class AircraftRegister.
     */
    @Test
    public void testAddAircraft() {
        System.out.println("addAircraft");
        
        AircraftModel model = new AircraftModel(AircraftModel.Type.MIXED, 0, "exp", AircraftModel.MotorType.TURBOJET, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11);
        RegimeRegister regimeRegister = new RegimeRegister();
        Aircraft newAircraft = new Aircraft(model, regimeRegister, "id0", "desc", "boeing", 1, 2, 3);
        AircraftRegister instance = new AircraftRegister();
        
        Aircraft expResult = newAircraft;
        Aircraft result = instance.addAircraft(newAircraft);
        
        assertEquals(expResult, result);
    }

    /**
     * Test of getAircraftMap method, of class AircraftRegister.
     */
    @Test
    public void testGetAircraftMap() {
        System.out.println("getAircraftMap");
        
        AircraftModel model = new AircraftModel(AircraftModel.Type.MIXED, 0, "exp", AircraftModel.MotorType.TURBOJET, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11);
        RegimeRegister regimeRegister = new RegimeRegister();
        Aircraft newAircraft = new Aircraft(model, regimeRegister, "id0", "desc", "boeing", 1, 2, 3);
        
        AircraftRegister instance = new AircraftRegister();
        instance.addAircraft(newAircraft);
        Map<String, Aircraft> expResult = new LinkedHashMap<>();
        expResult.put(newAircraft.getId(), newAircraft);
        Map<String, Aircraft> result = instance.getAircraftMap();
        
        assertEquals(expResult, result);
    }

    /**
     * Test of equals method, of class AircraftRegister.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        
        AircraftModel model = new AircraftModel(AircraftModel.Type.MIXED, 0, "exp", AircraftModel.MotorType.TURBOJET, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11);
        RegimeRegister regimeRegister = new RegimeRegister();
        Aircraft newAircraft = new Aircraft(model, regimeRegister, "id0", "desc", "boeing", 1, 2, 3);
        
        Object obj = new AircraftRegister();
        AircraftRegister instance = new AircraftRegister();
        
        instance.addAircraft(newAircraft);
        
        boolean expResult = false;
        boolean result = instance.equals(obj);
        
        assertEquals(expResult, result);
    }
    
}
