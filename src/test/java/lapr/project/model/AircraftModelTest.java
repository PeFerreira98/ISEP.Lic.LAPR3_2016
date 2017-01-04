/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

import lapr.project.model.register.CDragRegister;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Sofia
 */
public class AircraftModelTest {
    
    public AircraftModelTest() {
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
     * Test of getId method, of class AircraftModel.
     */
    @Test
    public void testGetId() {
        System.out.println("getId");
        AircraftModel instance = null;
        String expResult = "";
        String result = instance.getId();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getDescription method, of class AircraftModel.
     */
    @Test
    public void testGetDescription() {
        System.out.println("getDescription");
        AircraftModel instance = null;
        String expResult = "";
        String result = instance.getDescription();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getMaker method, of class AircraftModel.
     */
    @Test
    public void testGetMaker() {
        System.out.println("getMaker");
        AircraftModel instance = null;
        String expResult = "";
        String result = instance.getMaker();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getType method, of class AircraftModel.
     */
    @Test
    public void testGetType() {
        System.out.println("getType");
        AircraftModel instance = null;
        AircraftModel.Type expResult = null;
        AircraftModel.Type result = instance.getType();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getNumberMotors method, of class AircraftModel.
     */
    @Test
    public void testGetNumberMotors() {
        System.out.println("getNumberMotors");
        AircraftModel instance = null;
        double expResult = 0.0;
        double result = instance.getNumberMotors();
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getMotor method, of class AircraftModel.
     */
    @Test
    public void testGetMotor() {
        System.out.println("getMotor");
        AircraftModel instance = null;
        String expResult = "";
        String result = instance.getMotor();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getMotorType method, of class AircraftModel.
     */
    @Test
    public void testGetMotorType() {
        System.out.println("getMotorType");
        AircraftModel instance = null;
        AircraftModel.MotorType expResult = null;
        AircraftModel.MotorType result = instance.getMotorType();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getCruiseAltitude method, of class AircraftModel.
     */
    @Test
    public void testGetCruiseAltitude() {
        System.out.println("getCruiseAltitude");
        AircraftModel instance = null;
        double expResult = 0.0;
        double result = instance.getCruiseAltitude();
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getCruiseSpeed method, of class AircraftModel.
     */
    @Test
    public void testGetCruiseSpeed() {
        System.out.println("getCruiseSpeed");
        AircraftModel instance = null;
        double expResult = 0.0;
        double result = instance.getCruiseSpeed();
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getTSFC method, of class AircraftModel.
     */
    @Test
    public void testGetTSFC() {
        System.out.println("getTSFC");
        AircraftModel instance = null;
        double expResult = 0.0;
        double result = instance.getTSFC();
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getLapseRateFactor method, of class AircraftModel.
     */
    @Test
    public void testGetLapseRateFactor() {
        System.out.println("getLapseRateFactor");
        AircraftModel instance = null;
        double expResult = 0.0;
        double result = instance.getLapseRateFactor();
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getThrust_0 method, of class AircraftModel.
     */
    @Test
    public void testGetThrust_0() {
        System.out.println("getThrust_0");
        AircraftModel instance = null;
        double expResult = 0.0;
        double result = instance.getThrust_0();
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getThrustMaxSpeed method, of class AircraftModel.
     */
    @Test
    public void testGetThrustMaxSpeed() {
        System.out.println("getThrustMaxSpeed");
        AircraftModel instance = null;
        double expResult = 0.0;
        double result = instance.getThrustMaxSpeed();
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getMaxSpeed method, of class AircraftModel.
     */
    @Test
    public void testGetMaxSpeed() {
        System.out.println("getMaxSpeed");
        AircraftModel instance = null;
        double expResult = 0.0;
        double result = instance.getMaxSpeed();
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getEmptyWeight method, of class AircraftModel.
     */
    @Test
    public void testGetEmptyWeight() {
        System.out.println("getEmptyWeight");
        AircraftModel instance = null;
        double expResult = 0.0;
        double result = instance.getEmptyWeight();
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getMTOW method, of class AircraftModel.
     */
    @Test
    public void testGetMTOW() {
        System.out.println("getMTOW");
        AircraftModel instance = null;
        double expResult = 0.0;
        double result = instance.getMTOW();
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getMaxPayload method, of class AircraftModel.
     */
    @Test
    public void testGetMaxPayload() {
        System.out.println("getMaxPayload");
        AircraftModel instance = null;
        double expResult = 0.0;
        double result = instance.getMaxPayload();
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getFuelCapacity method, of class AircraftModel.
     */
    @Test
    public void testGetFuelCapacity() {
        System.out.println("getFuelCapacity");
        AircraftModel instance = null;
        double expResult = 0.0;
        double result = instance.getFuelCapacity();
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getVMO method, of class AircraftModel.
     */
    @Test
    public void testGetVMO() {
        System.out.println("getVMO");
        AircraftModel instance = null;
        double expResult = 0.0;
        double result = instance.getVMO();
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getMMO method, of class AircraftModel.
     */
    @Test
    public void testGetMMO() {
        System.out.println("getMMO");
        AircraftModel instance = null;
        double expResult = 0.0;
        double result = instance.getMMO();
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getWingArea method, of class AircraftModel.
     */
    @Test
    public void testGetWingArea() {
        System.out.println("getWingArea");
        AircraftModel instance = null;
        double expResult = 0.0;
        double result = instance.getWingArea();
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getWingSpan method, of class AircraftModel.
     */
    @Test
    public void testGetWingSpan() {
        System.out.println("getWingSpan");
        AircraftModel instance = null;
        double expResult = 0.0;
        double result = instance.getWingSpan();
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAspectRatio method, of class AircraftModel.
     */
    @Test
    public void testGetAspectRatio() {
        System.out.println("getAspectRatio");
        AircraftModel instance = null;
        double expResult = 0.0;
        double result = instance.getAspectRatio();
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getE method, of class AircraftModel.
     */
    @Test
    public void testGetE() {
        System.out.println("getE");
        AircraftModel instance = null;
        double expResult = 0.0;
        double result = instance.getE();
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getCdragRegister method, of class AircraftModel.
     */
    @Test
    public void testGetCdragRegister() {
        System.out.println("getCdragRegister");
        AircraftModel instance = null;
        CDragRegister expResult = null;
        CDragRegister result = instance.getCdragRegister();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of hashCode method, of class AircraftModel.
     */
    @Test
    public void testHashCode() {
        System.out.println("hashCode");
        AircraftModel instance = null;
        int expResult = 0;
        int result = instance.hashCode();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of equals method, of class AircraftModel.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        Object obj = null;
        AircraftModel instance = null;
        boolean expResult = false;
        boolean result = instance.equals(obj);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of toString method, of class AircraftModel.
     * 
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        AircraftModel instance = new AircraftModel("1", "aircraft5", "caknge", AircraftModel.Type.PASSENGER, 0, "lasdfr", AircraftModel.MotorType.TURBOPROP, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
        String expResult = "AircraftModel{id=1, description=aircraft5, maker=caknge, type=PASSENGER, numberMotors=0.0, motor=lasdfr, motorType=TURBOPROP, cruiseAltitude=0.0, cruiseSpeed=0.0, TSFC=0.0, lapseRateFactor=0.0, thrust_0=0.0, thrustMaxSpeed=0.0, maxSpeed=0.0, emptyWeight=0.0, MTOW=0.0, maxPayload=0.0, fuelCapacity=0.0, VMO=0.0, MMO=0.0, wingArea=0.0, wingSpan=0.0, aspectRatio=0.0, e=0.0, \n" +
"cdragRegister=CDragRegister{cDragList=[]}}";
        String result = instance.toString();
        assertEquals(expResult, result);
    }
    
}
