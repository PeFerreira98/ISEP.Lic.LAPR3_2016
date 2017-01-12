/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

import lapr.project.model.register.CDragRegister;
import lapr.project.utils.AircraftStAXParser;
import lapr.project.utils.AirportStAXParser;
import lapr.project.utils.NetworkStAXParser;
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
public class AircraftModelTest {
    private Project project;
    
    private final AircraftModel aircraftModel;
    private final Aircraft aircraft;
    
    public AircraftModelTest() {
         defaultProject();
        
        aircraftModel = project.getAircraftModelRegister().getAircraftModel("A380");

        aircraft = new Aircraft(aircraftModel.getId(), aircraftModel.getDescription(), 10, 10, 10, 200, 10.000, aircraftModel);
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
    private void defaultProject() {
        project = new Project("proj0", "proj");

        NetworkStAXParser network = new NetworkStAXParser(project);
        AircraftStAXParser instance = new AircraftStAXParser(project);
        AirportStAXParser airports = new AirportStAXParser(project);

        network.XMLReader("inOutFiles/TestSet02_Network.xml");
        instance.XMLReader("inOutFiles/TestSet02_Aircraft.xml");
        airports.XMLReader("inOutFiles/TestSet02_Airports.xml");


    }

    /**
     * Test of getId method, of class AircraftModel.
     */
    @Test
    public void testGetId() {
        System.out.println("getId");
        AircraftModel instance = aircraftModel;
        String expResult = aircraftModel.getId();
        String result = instance.getId();
        assertEquals(expResult, result);
        
    }

    /**
     * Test of getDescription method, of class AircraftModel.
     */
    @Test
    public void testGetDescription() {
        System.out.println("getDescription");
        AircraftModel instance = aircraftModel;
        String expResult = aircraftModel.getDescription();
        String result = instance.getDescription();
        assertEquals(expResult, result);
        
    }

    /**
     * Test of getMaker method, of class AircraftModel.
     */
    @Test
    public void testGetMaker() {
        System.out.println("getMaker");
        AircraftModel instance = aircraftModel;
        String expResult = aircraftModel.getMaker();
        String result = instance.getMaker();
        assertEquals(expResult, result);
        
    }

    /**
     * Test of getType method, of class AircraftModel.
     */
    @Test
    public void testGetType() {
        System.out.println("getType");
        AircraftModel instance = aircraftModel;
        AircraftModel.Type expResult = aircraftModel.getType();
        AircraftModel.Type result = instance.getType();
        assertEquals(expResult, result);
        
    }

    /**
     * Test of getNumberMotors method, of class AircraftModel.
     */
    @Test
    public void testGetNumberMotors() {
        System.out.println("getNumberMotors");
        AircraftModel instance = aircraftModel;
        double expResult = aircraftModel.getNumberMotors();
        double result = instance.getNumberMotors();
        assertEquals(expResult, result, aircraftModel.getNumberMotors());
        
    }

    /**
     * Test of getMotor method, of class AircraftModel.
     */
    @Test
    public void testGetMotor() {
        System.out.println("getMotor");
        AircraftModel instance = aircraftModel;
        String expResult = aircraftModel.getMotor();
        String result = instance.getMotor();
        assertEquals(expResult, result);
        
    }

    /**
     * Test of getMotorType method, of class AircraftModel.
     */
    @Test
    public void testGetMotorType() {
        System.out.println("getMotorType");
        AircraftModel instance = aircraftModel;
        AircraftModel.MotorType expResult = aircraftModel.getMotorType();
        AircraftModel.MotorType result = instance.getMotorType();
        assertEquals(expResult, result);
        
    }

    /**
     * Test of getCruiseAltitude method, of class AircraftModel.
     */
    @Test
    public void testGetCruiseAltitude() {
        System.out.println("getCruiseAltitude");
        AircraftModel instance = aircraftModel;
        double expResult = aircraftModel.getCruiseAltitude();
        double result = instance.getCruiseAltitude();
        assertEquals(expResult, result, aircraftModel.getCruiseAltitude());
        
    }

    /**
     * Test of getCruiseSpeed method, of class AircraftModel.
     */
    @Test
    public void testGetCruiseSpeed() {
        System.out.println("getCruiseSpeed");
        AircraftModel instance = aircraftModel;
        double expResult = aircraftModel.getCruiseSpeed();
        double result = instance.getCruiseSpeed();
        assertEquals(expResult, result, aircraftModel.getCruiseSpeed());
        
    }

    /**
     * Test of getTSFC method, of class AircraftModel.
     */
    @Test
    public void testGetTSFC() {
        System.out.println("getTSFC");
        AircraftModel instance = aircraftModel;
        double expResult = aircraftModel.getTSFC();
        double result = instance.getTSFC();
        assertEquals(expResult, result, aircraftModel.getTSFC());
        
    }

    /**
     * Test of getLapseRateFactor method, of class AircraftModel.
     */
    @Test
    public void testGetLapseRateFactor() {
        System.out.println("getLapseRateFactor");
        AircraftModel instance = aircraftModel;
        double expResult = aircraftModel.getLapseRateFactor();
        double result = instance.getLapseRateFactor();
        assertEquals(expResult, result, aircraftModel.getLapseRateFactor());
        
    }

    /**
     * Test of getThrust_0 method, of class AircraftModel.
     */
    @Test
    public void testGetThrust_0() {
        System.out.println("getThrust_0");
        AircraftModel instance = aircraftModel;
        double expResult = aircraftModel.getThrust_0();
        double result = instance.getThrust_0();
        assertEquals(expResult, result, aircraftModel.getThrust_0());
        
    }

    /**
     * Test of getThrustMaxSpeed method, of class AircraftModel.
     */
    @Test
    public void testGetThrustMaxSpeed() {
        System.out.println("getThrustMaxSpeed");
        AircraftModel instance = aircraftModel;
        double expResult = aircraftModel.getThrustMaxSpeed();
        double result = instance.getThrustMaxSpeed();
        assertEquals(expResult, result, aircraftModel.getThrustMaxSpeed());
        
    }

    /**
     * Test of getMaxSpeed method, of class AircraftModel.
     */
    @Test
    public void testGetMaxSpeed() {
        System.out.println("getMaxSpeed");
        AircraftModel instance = aircraftModel;
        double expResult = aircraftModel.getMaxSpeed();
        double result = instance.getMaxSpeed();
        assertEquals(expResult, result, aircraftModel.getMaxSpeed());
        
    }

    /**
     * Test of getEmptyWeight method, of class AircraftModel.
     */
    @Test
    public void testGetEmptyWeight() {
        System.out.println("getEmptyWeight");
        AircraftModel instance = aircraftModel;
        double expResult = aircraftModel.getEmptyWeight();
        double result = instance.getEmptyWeight();
        assertEquals(expResult, result, aircraftModel.getEmptyWeight());
       
    }

    /**
     * Test of getMTOW method, of class AircraftModel.
     */
    @Test
    public void testGetMTOW() {
        System.out.println("getMTOW");
        AircraftModel instance = aircraftModel;
        double expResult = aircraftModel.getMTOW();
        double result = instance.getMTOW();
        assertEquals(expResult, result, aircraftModel.getMTOW());
        
    }

    /**
     * Test of getMaxPayload method, of class AircraftModel.
     */
    @Test
    public void testGetMaxPayload() {
        System.out.println("getMaxPayload");
        AircraftModel instance = aircraftModel;
        double expResult = aircraftModel.getMaxPayload();
        double result = instance.getMaxPayload();
        assertEquals(expResult, result, aircraftModel.getMaxPayload());
        
    }

    /**
     * Test of getFuelCapacity method, of class AircraftModel.
     */
    @Test
    public void testGetFuelCapacity() {
        System.out.println("getFuelCapacity");
        AircraftModel instance = aircraftModel;
        double expResult = aircraftModel.getFuelCapacity();
        double result = instance.getFuelCapacity();
        assertEquals(expResult, result, aircraftModel.getFuelCapacity());
        
    }

    /**
     * Test of getVMO method, of class AircraftModel.
     */
    @Test
    public void testGetVMO() {
        System.out.println("getVMO");
        AircraftModel instance = aircraftModel;
        double expResult = aircraftModel.getVMO();
        double result = instance.getVMO();
        assertEquals(expResult, result, aircraftModel.getVMO());
        
    }

    /**
     * Test of getMMO method, of class AircraftModel.
     */
    @Test
    public void testGetMMO() {
        System.out.println("getMMO");
        AircraftModel instance = aircraftModel;
        double expResult = aircraftModel.getMMO();
        double result = instance.getMMO();
        assertEquals(expResult, result, aircraftModel.getMMO());
        
    }

    /**
     * Test of getWingArea method, of class AircraftModel.
     */
    @Test
    public void testGetWingArea() {
        System.out.println("getWingArea");
        AircraftModel instance = aircraftModel;
        double expResult = aircraftModel.getWingArea();
        double result = instance.getWingArea();
        assertEquals(expResult, result, aircraftModel.getWingArea());
        
    }

    /**
     * Test of getWingSpan method, of class AircraftModel.
     */
    @Test
    public void testGetWingSpan() {
        System.out.println("getWingSpan");
        AircraftModel instance = aircraftModel;
        double expResult = aircraftModel.getWingSpan();
        double result = instance.getWingSpan();
        assertEquals(expResult, result, aircraftModel.getWingSpan());
        
    }

    /**
     * Test of getAspectRatio method, of class AircraftModel.
     */
    @Test
    public void testGetAspectRatio() {
        System.out.println("getAspectRatio");
        AircraftModel instance = aircraftModel;
        double expResult = aircraftModel.getAspectRatio();
        double result = instance.getAspectRatio();
        assertEquals(expResult, result, aircraftModel.getAspectRatio());
        
    }

    /**
     * Test of getE method, of class AircraftModel.
     */
    @Test
    public void testGetE() {
        System.out.println("getE");
        AircraftModel instance = aircraftModel;
        double expResult = aircraftModel.getE();
        double result = instance.getE();
        assertEquals(expResult, result, aircraftModel.getE());
       
    }

    /**
     * Test of getCdragRegister method, of class AircraftModel.
     */
    @Test
    public void testGetCdragRegister() {
        System.out.println("getCdragRegister");
        CDrag cdrag = new CDrag(100,100);
        AircraftModel instance = aircraftModel;
        CDragRegister expResult = aircraftModel.getCdragRegister();
        CDragRegister result = instance.getCdragRegister();
        assertEquals(expResult, result);
        
    }

    /**
     * Test of setId method, of class AircraftModel.
     */
    @Test
    public void testSetId() {
        System.out.println("setId");
        String id = aircraftModel.getId();
        AircraftModel instance = aircraftModel;
        instance.setId(id);
        
    }

    /**
     * Test of setDescription method, of class AircraftModel.
     */
    @Test
    public void testSetDescription() {
        System.out.println("setDescription");
        String description = aircraftModel.getDescription();
        AircraftModel instance = aircraftModel;
        instance.setDescription(description);
        
    }

    /**
     * Test of setMaker method, of class AircraftModel.
     */
    @Test
    public void testSetMaker() {
        System.out.println("setMaker");
        String maker = aircraftModel.getMaker();
        AircraftModel instance = aircraftModel;
        instance.setMaker(maker);
        
    }

    /**
     * Test of setType method, of class AircraftModel.
     */
    @Test
    public void testSetType() {
        System.out.println("setType");
        AircraftModel.Type type = aircraftModel.getType();
        AircraftModel instance = aircraftModel;
        instance.setType(type);
      
    }

    /**
     * Test of setNumberMotors method, of class AircraftModel.
     */
    @Test
    public void testSetNumberMotors() {
        System.out.println("setNumberMotors");
        double numberMotors = aircraftModel.getNumberMotors();
        AircraftModel instance = aircraftModel;
        instance.setNumberMotors(numberMotors);
        
    }

    /**
     * Test of setMotor method, of class AircraftModel.
     */
    @Test
    public void testSetMotor() {
        System.out.println("setMotor");
        String motor = aircraftModel.getMotor();
        AircraftModel instance = aircraftModel;
        instance.setMotor(motor);
        
    }

    /**
     * Test of setMotorType method, of class AircraftModel.
     */
    @Test
    public void testSetMotorType() {
        System.out.println("setMotorType");
        AircraftModel.MotorType motorType = aircraftModel.getMotorType();
        AircraftModel instance = aircraftModel;
        instance.setMotorType(motorType);
        
    }

    /**
     * Test of setCruiseAltitude method, of class AircraftModel.
     */
    @Test
    public void testSetCruiseAltitude() {
        System.out.println("setCruiseAltitude");
        double cruiseAltitude = aircraftModel.getCruiseAltitude();
        AircraftModel instance = aircraftModel;
        instance.setCruiseAltitude(cruiseAltitude);
        
    }

    /**
     * Test of setCruiseSpeed method, of class AircraftModel.
     */
    @Test
    public void testSetCruiseSpeed() {
        System.out.println("setCruiseSpeed");
        double cruiseSpeed = aircraftModel.getCruiseSpeed();
        AircraftModel instance = aircraftModel;
        instance.setCruiseSpeed(cruiseSpeed);
        
    }

    /**
     * Test of setTSFC method, of class AircraftModel.
     */
    @Test
    public void testSetTSFC() {
        System.out.println("setTSFC");
        double TSFC = aircraftModel.getTSFC();
        AircraftModel instance = aircraftModel;
        instance.setTSFC(TSFC);
        
    }

    /**
     * Test of setLapseRateFactor method, of class AircraftModel.
     */
    @Test
    public void testSetLapseRateFactor() {
        System.out.println("setLapseRateFactor");
        double lapseRateFactor = aircraftModel.getLapseRateFactor();
        AircraftModel instance = aircraftModel;
        instance.setLapseRateFactor(lapseRateFactor);
        
    }

    /**
     * Test of setThrust_0 method, of class AircraftModel.
     */
    @Test
    public void testSetThrust_0() {
        System.out.println("setThrust_0");
        double thrust_0 = aircraftModel.getThrust_0();
        AircraftModel instance = aircraftModel;
        instance.setThrust_0(thrust_0);
        
    }

    /**
     * Test of setThrustMaxSpeed method, of class AircraftModel.
     */
    @Test
    public void testSetThrustMaxSpeed() {
        System.out.println("setThrustMaxSpeed");
        double thrustMaxSpeed = aircraftModel.getThrustMaxSpeed();
        AircraftModel instance = aircraftModel;
        instance.setThrustMaxSpeed(thrustMaxSpeed);
       
    }

    /**
     * Test of setMaxSpeed method, of class AircraftModel.
     */
    @Test
    public void testSetMaxSpeed() {
        System.out.println("setMaxSpeed");
        double maxSpeed = aircraftModel.getMaxSpeed();
        AircraftModel instance = aircraftModel;
        instance.setMaxSpeed(maxSpeed);
       
    }

    /**
     * Test of setEmptyWeight method, of class AircraftModel.
     */
    @Test
    public void testSetEmptyWeight() {
        System.out.println("setEmptyWeight");
        double emptyWeight = aircraftModel.getEmptyWeight();
        AircraftModel instance = aircraftModel;
        instance.setEmptyWeight(emptyWeight);
       
    }

    /**
     * Test of setMTOW method, of class AircraftModel.
     */
    @Test
    public void testSetMTOW() {
        System.out.println("setMTOW");
        double MTOW = aircraftModel.getMTOW();
        AircraftModel instance = aircraftModel;
        instance.setMTOW(MTOW);
      
    }

    /**
     * Test of setMaxPayload method, of class AircraftModel.
     */
    @Test
    public void testSetMaxPayload() {
        System.out.println("setMaxPayload");
        double maxPayload = aircraftModel.getMaxPayload();
        AircraftModel instance = aircraftModel;
        instance.setMaxPayload(maxPayload);
       
    }

    /**
     * Test of setFuelCapacity method, of class AircraftModel.
     */
    @Test
    public void testSetFuelCapacity() {
        System.out.println("setFuelCapacity");
        double fuelCapacity = aircraftModel.getFuelCapacity();
        AircraftModel instance = aircraftModel;
        instance.setFuelCapacity(fuelCapacity);
        
    }

    /**
     * Test of setVMO method, of class AircraftModel.
     */
    @Test
    public void testSetVMO() {
        System.out.println("setVMO");
        double VMO = aircraftModel.getVMO();
        AircraftModel instance = aircraftModel;
        instance.setVMO(VMO);
        
    }

    /**
     * Test of setMMO method, of class AircraftModel.
     */
    @Test
    public void testSetMMO() {
        System.out.println("setMMO");
        double MMO = aircraftModel.getMMO();
        AircraftModel instance = aircraftModel;
        instance.setMMO(MMO);
        
    }

    /**
     * Test of setWingArea method, of class AircraftModel.
     */
    @Test
    public void testSetWingArea() {
        System.out.println("setWingArea");
        double wingArea = aircraftModel.getWingArea();
        AircraftModel instance = aircraftModel;
        instance.setWingArea(wingArea);
       
    }

    /**
     * Test of setWingSpan method, of class AircraftModel.
     */
    @Test
    public void testSetWingSpan() {
        System.out.println("setWingSpan");
        double wingSpan = aircraftModel.getWingSpan();
        AircraftModel instance = aircraftModel;
        instance.setWingSpan(wingSpan);
        
    }

    /**
     * Test of setAspectRatio method, of class AircraftModel.
     */
    @Test
    public void testSetAspectRatio() {
        System.out.println("setAspectRatio");
        double aspectRatio = aircraftModel.getAspectRatio();
        AircraftModel instance = aircraftModel;
        instance.setAspectRatio(aspectRatio);
       
    }

    /**
     * Test of setE method, of class AircraftModel.
     */
    @Test
    public void testSetE() {
        System.out.println("setE");
        double e = aircraftModel.getE();
        AircraftModel instance = aircraftModel;
        instance.setE(e);
        
    }

    /**
     * Test of setCdragRegister method, of class AircraftModel.
     */
    @Test
    public void testSetCdragRegister() {
        System.out.println("setCdragRegister");
        CDrag cdrag = new CDrag(100,100);
        CDragRegister cdragRegister = aircraftModel.getCdragRegister();
        AircraftModel instance = aircraftModel;
        instance.setCdragRegister(cdragRegister);
        
    }

   
    
}
