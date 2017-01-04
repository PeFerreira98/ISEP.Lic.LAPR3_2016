/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model.register;

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
     * Test of validateAircraft method, of class AircraftRegister.
     */
    @Test
    public void testValidateAircraft() {
        System.out.println("validateAircraft");
        AircraftRegister ar1 = new AircraftRegister();
        boolean expResult = false;
        AircraftModel newAircraftModel = new AircraftModel("1A", "AircraftDescription", "Maker", AircraftModel.Type.MIXED, 5, "Motor", AircraftModel.MotorType.TURBOJET, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25);
        Aircraft a1 = new Aircraft(newAircraftModel, "1A", "description", 15, 25, 7);

        ar1.addAircraft(a1);

        Aircraft a2 = new Aircraft(newAircraftModel, "1A", "description", 15, 25, 7);

        boolean result = ar1.validateAircraft(a2);
        assertEquals(expResult, result);

    }

    /**
     * Test of addAircraft method, of class AircraftRegister.
     */
    @Test
    public void testAddAircraft() {
        System.out.println("addAircraft");
        AircraftModel newAircraftModel = new AircraftModel("1A", "AircraftDescription", "Maker", AircraftModel.Type.MIXED, 5, "Motor", AircraftModel.MotorType.TURBOJET, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25);
        Aircraft a1 = new Aircraft(newAircraftModel, "1A", "description", 15, 25, 7);

        AircraftRegister instance = new AircraftRegister();
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
        AircraftModel newAircraftModel = new AircraftModel("1A", "AircraftDescription", "Maker", AircraftModel.Type.MIXED, 5, "Motor", AircraftModel.MotorType.TURBOJET, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25);
        Aircraft a1 = new Aircraft(newAircraftModel, "1A", "description", 15, 25, 7);
        AircraftRegister instance = new AircraftRegister();
        boolean expResult = instance.getAircraftByID("1A");
        boolean result = true;
        assertEquals(expResult, result);

    }

    /**
     * Test of aircraftDuplicationName method, of class AircraftRegister.
     */
    @Test
    public void testAircraftDuplicationName() {
        System.out.println("aircraftDuplicationName");
        AircraftModel newAircraftModel = new AircraftModel("1A", "AircraftDescription", "Maker", AircraftModel.Type.MIXED, 5, "Motor", AircraftModel.MotorType.TURBOJET, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25);
        Aircraft a1 = new Aircraft(newAircraftModel, "1A", "description", 15, 25, 7);
        Aircraft a2 = new Aircraft(newAircraftModel, "1A", "description", 15, 25, 7);
        AircraftRegister instance = new AircraftRegister();
        instance.addAircraft(a1);
        instance.aircraftDuplicationName(a2, 1);
        assertEquals(a2.getDescription(),"description(1)");
        
        
    }

    /**
     * Test of aircraftCheckDuplicate method, of class AircraftRegister.
     */
    @Test
    public void testAircraftCheckDuplicate() {
        System.out.println("aircraftCheckDuplicate");
        AircraftModel newAircraftModel = new AircraftModel("1A", "AircraftDescription", "Maker", AircraftModel.Type.MIXED, 5, "Motor", AircraftModel.MotorType.TURBOJET, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25);
        Aircraft a1 = new Aircraft(newAircraftModel, "1A", "description", 15, 25, 7);
        Aircraft a2 = new Aircraft(newAircraftModel, "1A", "description", 15, 25, 7);
        AircraftRegister instance = new AircraftRegister();
        instance.addAircraft(a1);
        instance.aircraftDuplicationName(a2, 1);
    
        
    }

}
