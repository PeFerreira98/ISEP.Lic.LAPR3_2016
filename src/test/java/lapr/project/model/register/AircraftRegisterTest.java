/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model.register;

import java.util.HashMap;
import java.util.LinkedHashMap;
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
 * @author Tiago
 */
public class AircraftRegisterTest {

    AircraftRegister ar1;
    AircraftModel newAircraftModel, newAircraftModel2, newAircraftModel3;
    Aircraft a1, a2, a3, a4, a5, a6;

    public AircraftRegisterTest() {
        ar1 = new AircraftRegister();

        newAircraftModel = new AircraftModel("1A", "AircraftDescription", "Maker", AircraftModel.Type.MIXED, 5, "Motor", AircraftModel.MotorType.TURBOJET, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25);
        newAircraftModel2 = new AircraftModel("1B", "AircraftDescription", "Maker", AircraftModel.Type.CARGO, 5, "Motor", AircraftModel.MotorType.ELECTRICPROPELER, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25);
        newAircraftModel3 = new AircraftModel("1C", "AircraftDescription", "Maker", AircraftModel.Type.PASSENGER, 5, "Motor", AircraftModel.MotorType.TURBOPROP, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25);

        a1 = new Aircraft("1A", "description", 15, 25, 7, 20, 20, newAircraftModel);
        a2 = new Aircraft("1B", "description", 15, 25, 7, 20, 20, newAircraftModel);
        a3 = new Aircraft("2A", "description", 15, 25, 7, 20, 20, newAircraftModel2);
        a4 = new Aircraft("2B", "description", 15, 25, 7, 20, 20, newAircraftModel2);
        a5 = new Aircraft("3A", "description", 15, 25, 7, 20, 20, newAircraftModel3);
        a6 = new Aircraft("3B", "description", 15, 25, 7, 20, 20, newAircraftModel3);
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
     * Test of validateAircraft method, of class AircraftRegister.
     */
    @Test
    public void testValidateAircraft() {
        System.out.println("validateAircraft");

        ar1.addAircraft(a1);
        ar1.addAircraft(a2);

        boolean expResult = false;
        boolean result = ar1.validateAircraft(a2);
        
        assertEquals(expResult, result);

    }

    /**
     * Test of addAircraft method, of class AircraftRegister.
     */
    @Test
    public void testAddAircraft() {
        System.out.println("addAircraft");

        AircraftRegister instance = ar1;
        
        boolean expResult = true;
        boolean result = instance.addAircraft(a1);
        
        assertEquals(expResult, result);
    }
    
    /**
     * Test of addAircraft method, of class AircraftRegister.
     */
    @Test
    public void testAddAircraft2() {
        System.out.println("addAircraft2");

        AircraftRegister instance = ar1;
        instance.addAircraft(a1);
        
        boolean expResult = false;
        boolean result = instance.addAircraft(a1);
        
        assertEquals(expResult, result);
    }
    
    /**
     * Test of getAircraftByID method, of class AircraftRegister.
     */
    @Test
    public void testGetAircraftByID() {
        System.out.println("getAircraftByID");
        
        String ID = "1A";
        AircraftRegister instance = ar1;
        instance.addAircraft(a1);
        
        Aircraft expResult = a1;
        Aircraft result = instance.getAircraftByID(ID);
        
        assertEquals(expResult, result);
    }

    /**
     * Test of getAircraftRegister method, of class AircraftRegister.
     */
    @Test
    public void testGetAircraftRegister() {
        System.out.println("getAircraftRegister");
        
        AircraftRegister instance = ar1;
        ar1.addAircraft(a1);
        ar1.addAircraft(a2);
        ar1.addAircraft(a4);
        
        HashMap<String, Aircraft> expResult = new LinkedHashMap<>();
        expResult.put(a1.getId(), a1);
        expResult.put(a4.getId(), a4);
        expResult.put(a2.getId(), a2);

        HashMap<String, Aircraft> result = instance.getAircraftRegister();
        
        assertEquals(expResult, result);
    }

    /**
     * Test of aircraftCheckDuplicate method, of class AircraftRegister.
     */
    @Test
    public void testAircraftCheckDuplicate() {
        System.out.println("aircraftCheckDuplicate");
       
        AircraftRegister instance = ar1;
        instance.addAircraft(a1);
        
        boolean expResult = true;
        boolean result = instance.aircraftCheckDuplicate(a1);
        
        assertEquals(expResult, result);
    }

}
