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

    AircraftModel newAircraftModel, newAircraftModel2, newAircraftModel3;
    AircraftModelRegister instance;

    public AircraftModelRegisterTest() {
        newAircraftModel = new AircraftModel("1A", "AircraftDescription", "Maker", AircraftModel.Type.MIXED, 5, "Motor", AircraftModel.MotorType.TURBOJET, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25);
        newAircraftModel2 = new AircraftModel("1B", "AircraftDescription", "Maker", AircraftModel.Type.CARGO, 5, "Motor", AircraftModel.MotorType.ELECTRICPROPELER, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25);
        newAircraftModel3 = new AircraftModel("1C", "AircraftDescription", "Maker", AircraftModel.Type.PASSENGER, 5, "Motor", AircraftModel.MotorType.TURBOPROP, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25);
        
        instance = new AircraftModelRegister();
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

        AircraftModel expResult = new AircraftModel("1A", "AircraftDescription", "Maker", AircraftModel.Type.MIXED, 5, "Motor", AircraftModel.MotorType.TURBOJET, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25);
        AircraftModel result = this.instance.addAircraftModel(this.newAircraftModel);

        assertEquals(expResult, result);
    }

    /**
     * Test of getAircraftModelMap method, of class AircraftModelRegister.
     */
    @Test
    public void testGetAircraftModelMap() {
        System.out.println("getAircraftModelMap");

        this.instance.addAircraftModel(this.newAircraftModel);
        this.instance.addAircraftModel(newAircraftModel2);
        this.instance.addAircraftModel(newAircraftModel3);
        
        Map<String, AircraftModel> expResult = new LinkedHashMap<>();
        expResult.put(this.newAircraftModel.getId(), this.newAircraftModel);
        expResult.put(this.newAircraftModel2.getId(), this.newAircraftModel2);
        expResult.put(this.newAircraftModel3.getId(), this.newAircraftModel3);
        
        Map<String, AircraftModel> result = this.instance.getAircraftModelMap();

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
        instance.addAircraftModel(newAircraftModel);

        boolean expResult = false;
        boolean result = instance.equals(obj);

        assertEquals(expResult, result);
    }

    /**
     * Test of getAircraftModel method, of class AircraftModelRegister.
     */
    @Test
    public void testGetAircraftModel() {
        System.out.println("getAircraftModel");

        String aircraftModelId = "1A";
        this.instance.addAircraftModel(newAircraftModel);
        this.instance.addAircraftModel(newAircraftModel2);
        this.instance.addAircraftModel(newAircraftModel3);

        AircraftModel expResult = this.newAircraftModel;
        AircraftModel result = instance.getAircraftModel(aircraftModelId);

        assertEquals(expResult, result);
    }

    /**
     * Test of getAircraftsByType method, of class AircraftModelRegister.
     */
    @Test
    public void testGetAircraftsByType() {
        System.out.println("getAircraftsByType");

        AircraftModel.Type aircraftType = AircraftModel.Type.MIXED;
        this.instance.addAircraftModel(newAircraftModel);
        this.instance.addAircraftModel(newAircraftModel2);
        this.instance.addAircraftModel(newAircraftModel3);
        
        Map<String, AircraftModel> expResult = new LinkedHashMap<>();
        expResult.put(this.newAircraftModel.getId(), this.newAircraftModel);
        
        Map<String, AircraftModel> result = instance.getAircraftsByType(aircraftType);

        assertEquals(expResult, result);
    }
}
