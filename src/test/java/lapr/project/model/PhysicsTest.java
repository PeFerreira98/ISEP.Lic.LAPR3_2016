/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

import java.util.LinkedList;
import java.util.List;
import lapr.project.model.network.Segment;
import lapr.project.utils.AircraftStAXParser;
import lapr.project.utils.AirportStAXParser;
import lapr.project.utils.NetworkStAXParser;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author João
 */
public class PhysicsTest {

    private List<Project> listProjects;

    public PhysicsTest() {
        listProjects = new LinkedList<>();
        defaultProject();
    }

    private void defaultProject() {
        Project project = new Project(0, "proj0", "proj");

        NetworkStAXParser network = new NetworkStAXParser(project);
        AircraftStAXParser instance = new AircraftStAXParser(project);
        AirportStAXParser airports = new AirportStAXParser(project);

        network.XMLReader("inOutFiles/TestSet02_Network.xml");
        instance.XMLReader("inOutFiles/TestSet02_Aircraft.xml");
        airports.XMLReader("inOutFiles/TestSet02_Airports.xml");
        
        this.listProjects.add(project);
        //System.out.println(listProjects.get(0).getAircraftHashMap().values().toString());
    }
    
    
//    /**
//     * Test of calculateAirDensityTemperatureDueAltitude method, of class Physics.
//     */
//    @Test
//    public void testCalculateAirDensityTemperatureDueAltitude() {
//        System.out.println("calculateAirDensityTemperatureDueAltitude");
//        double altitude = 0.0;
//        double p = 0.0;
//        double t = 0.0;
//        Physics.calculateAirDensityTemperatureDueAltitude(altitude, p, t);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of calculateSpeedDueAltitudeClimbing method, of class Physics.
//     */
//    @Test
//    public void testCalculateSpeedDueAltitudeClimbing() {
//        System.out.println("calculateSpeedDueAltitudeClimbing");
//        Aircraft aircraft = null;
//        double altitude = 0.0;
//        double speed = 0.0;
//        Physics.calculateSpeedDueAltitudeClimbing(aircraft, altitude, speed);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of calculateTemperatureDueAltitude method, of class Physics.
//     */
//    @Test
//    public void testCalculateTemperatureDueAltitude() {
//        System.out.println("calculateTemperatureDueAltitude");
//        double altitude = 0.0;
//        double expResult = 0.0;
//        double result = Physics.calculateTemperatureDueAltitude(altitude);
//        assertEquals(expResult, result, 0.0);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of calculateDensityDueAltitude method, of class Physics.
//     */
//    @Test
//    public void testCalculateDensityDueAltitude() {
//        System.out.println("calculateDensityDueAltitude");
//        double altitude = 0.0;
//        double pressure = 0.0;
//        double temperature = 0.0;
//        double expResult = 0.0;
//        double result = Physics.calculateDensityDueAltitude(altitude, pressure, temperature);
//        assertEquals(expResult, result, 0.0);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of calculateSpeedOfSoundDueAltitude method, of class Physics.
//     */
//    @Test
//    public void testCalculateSpeedOfSoundDueAltitude() {
//        System.out.println("calculateSpeedOfSoundDueAltitude");
//        double altitude = 0.0;
//        double expResult = 0.0;
//        double result = Physics.calculateSpeedOfSoundDueAltitude(altitude);
//        assertEquals(expResult, result, 0.0);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of calculatePressureDueAltitude method, of class Physics.
//     */
//    @Test
//    public void testCalculatePressureDueAltitude() {
//        System.out.println("calculatePressureDueAltitude");
//        double altitude = 0.0;
//        double expResult = 0.0;
//        double result = Physics.calculatePressureDueAltitude(altitude);
//        assertEquals(expResult, result, 0.0);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of calculateLiftForceInASegment method, of class Physics.
//     */
//    @Test
//    public void testCalculateLiftForceInASegment() {
//        System.out.println("calculateLiftForceInASegment");
//        Aircraft aircraft = null;
//        Segment segment = null;
//        double altitude = 0.0;
//        double expResult = 0.0;
//        double result = Physics.calculateLiftForceInASegment(aircraft, segment, altitude);
//        assertEquals(expResult, result, 0.0);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of calculateDragForceInASegment method, of class Physics.
//     */
//    @Test
//    public void testCalculateDragForceInASegment() {
//        System.out.println("calculateDragForceInASegment");
//        Aircraft aircraft = null;
//        Segment segment = null;
//        double altitude = 0.0;
//        double expResult = 0.0;
//        double result = Physics.calculateDragForceInASegment(aircraft, segment, altitude);
//        assertEquals(expResult, result, 0.0);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of calculateLiftCoeficient method, of class Physics.
//     */
//    @Test
//    public void testCalculateLiftCoeficient() {
//        System.out.println("calculateLiftCoeficient");
//        Aircraft aircraft = null;
//        Segment segment = null;
//        double altitude = 0.0;
//        double expResult = 0.0;
//        double result = Physics.calculateLiftCoeficient(aircraft, segment, altitude);
//        assertEquals(expResult, result, 0.0);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of calculateDragCoeficient method, of class Physics.
//     */
//    @Test
//    public void testCalculateDragCoeficient() {
//        System.out.println("calculateDragCoeficient");
//        Aircraft aircraft = null;
//        Segment segment = null;
//        Double altitude = null;
//        double expResult = 0.0;
//        double result = Physics.calculateDragCoeficient(aircraft, segment, altitude);
//        assertEquals(expResult, result, 0.0);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of calculateRangeEachSegment method, of class Physics.
//     */
//    @Test
//    public void testCalculateRangeEachSegment() {
//        System.out.println("calculateRangeEachSegment");
//        Aircraft aircraft = null;
//        Segment segment = null;
//        double altitude = 0.0;
//        double expResult = 0.0;
//        double result = Physics.calculateRangeEachSegment(aircraft, segment, altitude);
//        assertEquals(expResult, result, 0.0);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of calculateTravelTimeInASegment method, of class Physics.
//     */
//    @Test
//    public void testCalculateTravelTimeInASegment() {
//        System.out.println("calculateTravelTimeInASegment");
//        Aircraft aircraft = null;
//        Segment segment = null;
//        double expResult = 0.0;
//        double result = Physics.calculateTravelTimeInASegment(aircraft, segment);
//        assertEquals(expResult, result, 0.0);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of calculateSegmentDistance method, of class Physics.
//     */
//    @Test
//    public void testCalculateSegmentDistance() {
//        System.out.println("calculateSegmentDistance");
//        Aircraft aircraft = null;
//        Segment segment = null;
//        double expResult = 0.0;
//        double result = Physics.calculateSegmentDistance(aircraft, segment);
//        assertEquals(expResult, result, 0.0);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of calculateFuelComsuptionEachSegment method, of class Physics.
//     */
//    @Test
//    public void testCalculateFuelComsuptionEachSegment() {
//        System.out.println("calculateFuelComsuptionEachSegment");
//        Aircraft aircraft = null;
//        Segment segment = null;
//        double expResult = 0.0;
//        double result = Physics.calculateFuelComsuptionEachSegment(aircraft, segment);
//        assertEquals(expResult, result, 0.0);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of setsToAircraftValues method, of class Physics.
//     */
//    @Test
//    public void testSetsToAircraftValues() {
//        System.out.println("setsToAircraftValues");
//        Aircraft aircraft = null;
//        Physics.setsToAircraftValues(aircraft);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of calculateAircraftTrueAirspeed method, of class Physics.
//     */
//    @Test
//    public void testCalculateAircraftTrueAirspeed() {
//        System.out.println("calculateAircraftTrueAirspeed");
//        Aircraft aircraft = null;
//        Double altitude = null;
//        double expResult = 0.0;
//        double result = Physics.calculateAircraftTrueAirspeed(aircraft, altitude);
//        assertEquals(expResult, result, 0.0);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of calculateTrueMachNumber method, of class Physics.
//     */
//    @Test
//    public void testCalculateTrueMachNumber() {
//        System.out.println("calculateTrueMachNumber");
//        Aircraft aircraft = null;
//        double altitude = 0.0;
//        double speedVIAS = 0.0;
//        double expResult = 0.0;
//        double result = Physics.calculateTrueMachNumber(aircraft, altitude, speedVIAS);
//        assertEquals(expResult, result, 0.0);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of calculateThrust method, of class Physics.
//     */
//    @Test
//    public void testCalculateThrust() {
//        System.out.println("calculateThrust");
//        Aircraft aircraft = null;
//        double altitude = 0.0;
//        double trueMachNumber = 0.0;
//        double expResult = 0.0;
//        double result = Physics.calculateThrust(aircraft, altitude, trueMachNumber);
//        assertEquals(expResult, result, 0.0);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of calculateThrustAltitude method, of class Physics.
//     */
//    @Test
//    public void testCalculateThrustAltitude() {
//        System.out.println("calculateThrustAltitude");
//        Aircraft aircraft = null;
//        Segment segment = null;
//        double altitude = 0.0;
//        double trueMachNumber = 0.0;
//        double expResult = 0.0;
//        double result = Physics.calculateThrustAltitude(aircraft, segment, altitude, trueMachNumber);
//        assertEquals(expResult, result, 0.0);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of calculateTrueAirSpeed method, of class Physics.
//     */
//    @Test
//    public void testCalculateTrueAirSpeed() {
//        System.out.println("calculateTrueAirSpeed");
//        double trueMachNumber = 0.0;
//        double speedOfSound = 0.0;
//        double expResult = 0.0;
//        double result = Physics.calculateTrueAirSpeed(trueMachNumber, speedOfSound);
//        assertEquals(expResult, result, 0.0);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of calculateAircraftClimbRate method, of class Physics.
//     */
//    @Test
//    public void testCalculateAircraftClimbRate() {
//        System.out.println("calculateAircraftClimbRate");
//        Aircraft aircraft = null;
//        Segment segment = null;
//        double thrustTotal = 0.0;
//        double dragForce = 0.0;
//        double maxWeight = 0.0;
//        double trueAirSpeed = 0.0;
//        double expResult = 0.0;
//        double result = Physics.calculateAircraftClimbRate(aircraft, segment, thrustTotal, dragForce, maxWeight, trueAirSpeed);
//        assertEquals(expResult, result, 0.0);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of calculateFuelBurned method, of class Physics.
//     */
//    @Test
//    public void testCalculateFuelBurned() {
//        System.out.println("calculateFuelBurned");
//        Aircraft aircraft = null;
//        double dragForce = 0.0;
//        double expResult = 0.0;
//        double result = Physics.calculateFuelBurned(aircraft, dragForce);
//        assertEquals(expResult, result, 0.0);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of calculateAltitudeVariation method, of class Physics.
//     */
//    @Test
//    public void testCalculateAltitudeVariation() {
//        System.out.println("calculateAltitudeVariation");
//        double speed = 0.0;
//        double thrustAltitude = 0.0;
//        double dragForce = 0.0;
//        double SpeedVariationWithTime = 0.0;
//        double maxWeight = 0.0;
//        double expResult = 0.0;
//        double result = Physics.calculateAltitudeVariation(speed, thrustAltitude, dragForce, SpeedVariationWithTime, maxWeight);
//        assertEquals(expResult, result, 0.0);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of calculateClimbingAngle method, of class Physics.
//     */
//    @Test
//    public void testCalculateClimbingAngle() {
//        System.out.println("calculateClimbingAngle");
//        double trueAirSpeed = 0.0;
//        double climbRate = 0.0;
//        double expResult = 0.0;
//        double result = Physics.calculateClimbingAngle(trueAirSpeed, climbRate);
//        assertEquals(expResult, result, 0.0);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of calculatedWdT method, of class Physics.
//     */
//    @Test
//    public void testCalculatedWdT() {
//        System.out.println("calculatedWdT");
//        Aircraft aircraft = null;
//        double time = 0.0;
//        double totalThrust = 0.0;
//        double expResult = 0.0;
//        double result = Physics.calculatedWdT(aircraft, time, totalThrust);
//        assertEquals(expResult, result, 0.0);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of calculateDistanceTraveledWhileClimbing method, of class Physics.
//     */
//    @Test
//    public void testCalculateDistanceTraveledWhileClimbing() {
//        System.out.println("calculateDistanceTraveledWhileClimbing");
//        double trueAirspeed = 0.0;
//        double climbAngle = 0.0;
//        double time = 0.0;
//        double expResult = 0.0;
//        double result = Physics.calculateDistanceTraveledWhileClimbing(trueAirspeed, climbAngle, time);
//        assertEquals(expResult, result, 0.0);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
    /**
     * Test of aircraftClimb method, of class Physics.
     */
    @Test
    public void testAircraftClimb() {
        System.out.println("aircraftClimb");
        
        Project project =listProjects.get(0);
        System.out.println(project.getDescription());
        Aircraft aircraft = new Aircraft();
        System.out.print(project.getAircraftHashMap().values().toString());
        aircraft =project.getAircraftHashMap().get("Aibus A380");
        
        Segment segment = null;
        
        double[] expResult = new double[1];
        expResult[0]=9972.28;
        double[] result = Physics.aircraftClimb(aircraft, segment);
        assertEquals(expResult, result);

    }

//    /**
//     * Test of calculateSegmentDistanceInMiles method, of class Physics.
//     */
//    @Test
//    public void testCalculateSegmentDistanceInMiles() {
//        System.out.println("calculateSegmentDistanceInMiles");
//        double distance = 0.0;
//        double expResult = 0.0;
//        double result = Physics.calculateSegmentDistanceInMiles(distance);
//        assertEquals(expResult, result, 0.0);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of calculateAircraftFinalWeight method, of class Physics.
//     */
//    @Test
//    public void testCalculateAircraftFinalWeight() {
//        System.out.println("calculateAircraftFinalWeight");
//        Aircraft aircraft = null;
//        double expResult = 0.0;
//        double result = Physics.calculateAircraftFinalWeight(aircraft);
//        assertEquals(expResult, result, 0.0);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of speedAndMMOConverterMachToKmsHour method, of class Physics.
//     */
//    @Test
//    public void testSpeedAndMMOConverterMachToKmsHour() {
//        System.out.println("speedAndMMOConverterMachToKmsHour");
//        Double aircraftSpeedORMmoValue = null;
//        double expResult = 0.0;
//        double result = Physics.speedAndMMOConverterMachToKmsHour(aircraftSpeedORMmoValue);
//        assertEquals(expResult, result, 0.0);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of tsfcConverter method, of class Physics.
//     */
//    @Test
//    public void testTsfcConverter() {
//        System.out.println("tsfcConverter");
//        Aircraft aircraft = null;
//        double expResult = 0.0;
//        double result = Physics.tsfcConverter(aircraft);
//        assertEquals(expResult, result, 0.0);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of altitudeConverterFeetToMeters method, of class Physics.
//     */
//    @Test
//    public void testAltitudeConverterFeetToMeters() {
//        System.out.println("altitudeConverterFeetToMeters");
//        Aircraft aircraft = null;
//        double expResult = 0.0;
//        double result = Physics.altitudeConverterFeetToMeters(aircraft);
//        assertEquals(expResult, result, 0.0);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of aicraftWeightConverterPoundsToKg method, of class Physics.
//     */
//    @Test
//    public void testAicraftWeightConverterPoundsToKg() {
//        System.out.println("aicraftWeightConverterPoundsToKg");
//        double anyAircraftWeightValue = 0.0;
//        double expResult = 0.0;
//        double result = Physics.aicraftWeightConverterPoundsToKg(anyAircraftWeightValue);
//        assertEquals(expResult, result, 0.0);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of aircraftVMOConverterKnotToKmsHour method, of class Physics.
//     */
//    @Test
//    public void testAircraftVMOConverterKnotToKmsHour() {
//        System.out.println("aircraftVMOConverterKnotToKmsHour");
//        Aircraft aircraft = null;
//        double expResult = 0.0;
//        double result = Physics.aircraftVMOConverterKnotToKmsHour(aircraft);
//        assertEquals(expResult, result, 0.0);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of aircraftFuelCapacityConverterGallonsToLiter method, of class Physics.
//     */
//    @Test
//    public void testAircraftFuelCapacityConverterGallonsToLiter() {
//        System.out.println("aircraftFuelCapacityConverterGallonsToLiter");
//        Aircraft aircraft = null;
//        double expResult = 0.0;
//        double result = Physics.aircraftFuelCapacityConverterGallonsToLiter(aircraft);
//        assertEquals(expResult, result, 0.0);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of temperatudeConverterKelvinToCelsius method, of class Physics.
//     */
//    @Test
//    public void testTemperatudeConverterKelvinToCelsius() {
//        System.out.println("temperatudeConverterKelvinToCelsius");
//        double temperature = 0.0;
//        double expResult = 0.0;
//        double result = Physics.temperatudeConverterKelvinToCelsius(temperature);
//        assertEquals(expResult, result, 0.0);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }

}