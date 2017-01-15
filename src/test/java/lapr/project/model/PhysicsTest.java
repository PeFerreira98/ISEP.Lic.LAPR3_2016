/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import static lapr.project.model.Physics.calculateSegmentDistance;
import lapr.project.model.network.Node;
import lapr.project.model.network.Segment;
import lapr.project.utils.AircraftStAXParser;
import lapr.project.utils.AirportStAXParser;
import lapr.project.utils.ImportFlightPatternCSV;
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
    double[][] matrix = ImportFlightPatternCSV.CSVImport("inOutFiles/Flight_pattern_A380_v1a.csv");

    private void defaultProject() {
        Project project = new Project("proj0", "proj");

        NetworkStAXParser network = new NetworkStAXParser(project);
        AircraftStAXParser instance = new AircraftStAXParser(project);
        AirportStAXParser airports = new AirportStAXParser(project);

        network.XMLReader("inOutFiles/TestSet02_Network.xml");
        instance.XMLReader("inOutFiles/TestSet02_Aircraft.xml");
        airports.XMLReader("inOutFiles/TestSet02_Airports.xml");

        this.listProjects.add(project);
        //System.out.println(listProjects.get(0).getAircraftHashMap().values().toString());
    }
//
//    /**
//     * Test of calculateAirDensityTemperatureDueAltitude method, of class
//     * Physics.
//     */
//    @Test
//    public void testCalculateAirDensityTemperatureDueAltitude() {
//        System.out.println("calculateAirDensityTemperatureDueAltitude");
//        double altitude = 0;
//        double p = 101325;
//        double t = 288.2;
//        
//        double expectedResult=1.12;
//        //double result =Physics.calculateAirDensityTemperatureDueAltitude(altitude, p, t);
//        assertEquals(t, t, 0.01);
//       
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
//        double expResult = 0.0;
//        double result = Physics.calculateSpeedDueAltitudeClimbing(aircraft, altitude, speed);
//        assertEquals(expResult, result, 0.0);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of calculateSpeedDueAltitudeDescending method, of class Physics.
//     */
//    @Test
//    public void testCalculateSpeedDueAltitudeDescending() {
//        System.out.println("calculateSpeedDueAltitudeDescending");
//        Aircraft aircraft = null;
//        double altitude = 0.0;
//        double speed = 0.0;
//        double expResult = 0.0;
//        double result = Physics.calculateSpeedDueAltitudeDescending(aircraft, altitude, speed);
//        assertEquals(expResult, result, 0.0);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//

    /**
     * Test of calculateTemperatureDueAltitude method, of class Physics.
     */
    @Test
    public void testCalculateTemperatureDueAltitude() {
        System.out.println("calculateTemperatureDueAltitude");
        double altitude = 0.0;

        double expectedResult = 288.2;
        double result = Physics.calculateTemperatureDueAltitude(altitude);
        System.out.println("Temperature:" + expectedResult);
        assertEquals(expectedResult, result, 0.01);
    }

    /**
     * Test of calculateDensityDueAltitude method, of class Physics.
     */
    @Test
    public void testCalculateDensityDueAltitude() {
        System.out.println("calculateDensityDueAltitude");
        double altitude = 0.0;
        double pressure = 101325;
        double temperature = 288.2;
        double expResult = 1.22;
        double result = Physics.calculateDensityDueAltitude(altitude, pressure, temperature);
        System.out.println(result);
        assertEquals(expResult, result, 0.01);

    }

    /**
     * Test of calculateSpeedOfSoundDueAltitude method, of class Physics.
     */
    @Test
    public void testCalculateSpeedOfSoundDueAltitude() {
        System.out.println("calculateSpeedOfSoundDueAltitude");
        double altitude = 0.0;
        double expResult = 340.29;
        double result = Physics.calculateSpeedOfSoundDueAltitude(altitude);
        System.out.println(result);
        assertEquals(expResult, result, 0.01);

    }

    /**
     * Test of calculatePressureDueAltitude method, of class Physics.
     */
    @Test
    public void testCalculatePressureDueAltitude() {
        System.out.println("calculatePressureDueAltitude");
        double altitude = 0.0;
        double expResult = 101325;
        double result = Physics.calculatePressureDueAltitude(altitude);
        System.out.println(result);
        assertEquals(expResult, result, 0.01);

    }

//    /**
//     * Test of calculateLiftForceInASegment method, of class Physics.
//     */
//    @Test
//    public void testCalculateLiftForceInASegment() {
//        System.out.println("calculateLiftForceInASegment");
//        Aircraft aircraft = null;
//        double altitude = 0.0;
//        double expResult = 0.0;
//        double result = Physics.calculateLiftForceInASegment(aircraft, altitude);
//        assertEquals(expResult, result, 0.0);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
    /**
     * Test of calculateDragForceInASegment method, of class Physics.
     */
    @Test
    public void testCalculateDragForceInASegment() {
        System.out.println("calculateDragForceInASegment");
        Project project = listProjects.get(0);
        System.out.println(project.getDescription());
        Aircraft aircraft = new Aircraft("aaa", 0, 0, 0, 0, 320000, project.getAircraftModelRegister().getAircraftModelMap().get("A380"));
        project.getAircraftRegister().addAircraft(aircraft);
        aircraft.setDescription("aaa");
        double altitude = 0.0;
        double expResult = 426922.82;
        double result = Physics.calculateDragForceInASegment(aircraft, altitude, matrix);
        assertEquals(expResult, result, 0.01);

    }

    /**
     * Test of calculateLiftCoeficient method, of class Physics.
     */
    @Test
    public void testCalculateLiftCoeficient() {
        System.out.println("calculateLiftCoeficient");
        Project project = listProjects.get(0);
        System.out.println(project.getDescription());
        Aircraft aircraft = new Aircraft("aaa", 0, 0, 0, 0, 320000, project.getAircraftModelRegister().getAircraftModelMap().get("A380"));
        project.getAircraftRegister().addAircraft(aircraft);
        aircraft.setDescription("aaa");

        double altitude = 0.0;
        double expResult = 1.0;
        double result = Physics.calculateLiftCoeficient(aircraft, altitude, matrix);
        assertEquals(expResult, result, 0.01);

    }

    /**
     * Test of calculateDragCoeficient method, of class Physics.
     */
    @Test
    public void testCalculateDragCoeficient() {
        System.out.println("calculateDragCoeficient");
        Project project = listProjects.get(0);
        System.out.println(project.getDescription());
        Aircraft aircraft = new Aircraft("aaa", 0, 0, 0, 0, 320000, project.getAircraftModelRegister().getAircraftModelMap().get("A380"));
        project.getAircraftRegister().addAircraft(aircraft);
        aircraft.setDescription("aaa");

        double altitude = 0;
        double expResult = 0.07;
        double result = Physics.calculateDragCoeficient(aircraft, altitude, matrix);
        assertEquals(expResult, result, 0.01);

    }

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
    /**
     * Test of calculateSegmentDistance method, of class Physics.
     */
    @Test
    public void testCalculateSegmentDistance() {
        System.out.println("calculateSegmentDistance");

        Project project = listProjects.get(0);
        System.out.println(project.getDescription());
        Aircraft aircraft = new Aircraft("aaa", 0, 0, 0, 120000, 320000, project.getAircraftModelRegister().getAircraftModelMap().get("A380"));
        project.getAircraftRegister().addAircraft(aircraft);
        aircraft.setDescription("aaa");

        Segment segment = new Segment();
        Segment segment2 = new Segment();
        segment = project.getAirNetwork().getMapSegment().get("PTLS02");
        segment2 = project.getAirNetwork().getMapSegment().get("LSMD01");

        double expResult = 225000;
        System.out.println("\n!!!!!!" + calculateSegmentDistance(aircraft, segment));
        System.out.println("\n!!!!!!" + calculateSegmentDistance(aircraft, segment2));
        double result = Physics.calculateSegmentDistance(aircraft, segment);

        System.out.println("\naa:" + result);

        assertEquals(expResult, result, expResult * 0.01);
    }
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

    /**
     * Test of calculateTrueMachNumber method, of class Physics.
     */
    @Test
    public void testCalculateTrueMachNumber() {
        System.out.println("calculateTrueMachNumber");
        Project project = listProjects.get(0);
        System.out.println(project.getDescription());
        Aircraft aircraft = new Aircraft("aaa", 0, 0, 0, 120000, 320000, project.getAircraftModelRegister().getAircraftModelMap().get("A380"));
        project.getAircraftRegister().addAircraft(aircraft);
        aircraft.setDescription("aaa");

        double altitude = 0.0;
        double speedVIAS = 210;
        double expResult = 0.31;
        double result = Physics.calculateTrueMachNumber(aircraft, altitude, speedVIAS);
        assertEquals(expResult, result, 0.01);
    }

    /**
     * Test of calculateThrust method, of class Physics.
     */
    @Test
    public void testCalculateThrust() {
        System.out.println("calculateThrust");
        Project project = listProjects.get(0);
        System.out.println(project.getDescription());
        Aircraft aircraft = new Aircraft("aaa", 0, 0, 0, 120000, 320000, project.getAircraftModelRegister().getAircraftModelMap().get("A380"));
        project.getAircraftRegister().addAircraft(aircraft);
        aircraft.setDescription("aaa");

        double altitude = 0.0;
        double trueMachNumber = 0.317;
        double expResult = 289000;
        double result = Physics.calculateThrust(aircraft, altitude, trueMachNumber);
        assertEquals(expResult, result, 100.0);
    }

//    /**
//     * Test of calculateThrustAltitude method, of class Physics.
//     */
//    @Test
//    public void testCalculateThrustAltitude() {
//        System.out.println("calculateThrustAltitude");
//        Project project = listProjects.get(0);
//        Aircraft aircraft = new Aircraft("aaa", 0, 0, 0, 120000, 320000, project.getAircraftModelRegister().getAircraftModelMap().get("A380"));
//        project.getAircraftRegister().addAircraft(aircraft);
//        aircraft.setDescription("aaa");
//
//        double altitude = 0.0;
//        double trueMachNumber = 0.318;
//        double expResult = 1150000;
//        double result = Physics.calculateThrustAltitude(aircraft, altitude, trueMachNumber);
//        assertEquals(expResult, result, 0.0);
//    }
    /**
     * Test of calculateTotalThrust method, of class Physics.
     */
    @Test
    public void testCalculateTotalThrust() {
        System.out.println("calculateTotalThrust");
        Project project = listProjects.get(0);
        Aircraft aircraft = new Aircraft("aaa", 0, 0, 0, 120000, 320000, project.getAircraftModelRegister().getAircraftModelMap().get("A380"));
        project.getAircraftRegister().addAircraft(aircraft);
        aircraft.setDescription("aaa");

        double thrust = 2.89E+05;
        double expResult = 4 * thrust;
        double result = Physics.calculateTotalThrust(aircraft, thrust);
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of calculateTrueAirSpeed method, of class Physics.
     */
    @Test
    public void testCalculateTrueAirSpeed() {
        System.out.println("calculateTrueAirSpeed");
        double trueMachNumber = 0.32;
        double speedOfSound = 340.29;
        double expResult = 108.08;
        double result = Physics.calculateTrueAirSpeed(trueMachNumber, speedOfSound);
        assertEquals(expResult, result, 1);
    }

    /**
     * Test of calculateAircraftClimbRate method, of class Physics.
     */
    @Test
    public void testCalculateAircraftClimbRate() {
        System.out.println("calculateAircraftClimbRate");
        Project project = listProjects.get(0);
        Aircraft aircraft = new Aircraft("aaa", 0, 0, 0, 120000, 320000, project.getAircraftModelRegister().getAircraftModelMap().get("A380"));
        project.getAircraftRegister().addAircraft(aircraft);
        aircraft.setDescription("aaa");
        double thrustTotal = 1.15E+06;
        double dragForce = 426922.8;
        double maxWeight = 6.18E+05;
        double trueAirSpeed = 108.08;
        double expResult = 13.0;
        double result = Physics.calculateAircraftClimbRate(aircraft, thrustTotal, dragForce, maxWeight, trueAirSpeed);
        assertEquals(expResult, result, 0.13);

    }

    /**
     * Test of calculateFuelBurned method, of class Physics.
     */
    @Test
    public void testCalculateFuelBurned() {
        System.out.println("calculateFuelBurned");
        Project project = listProjects.get(0);
        Aircraft aircraft = new Aircraft("aaa", 0, 0, 0, 120000, 320000, project.getAircraftModelRegister().getAircraftModelMap().get("A380"));
        project.getAircraftRegister().addAircraft(aircraft);
        aircraft.setDescription("aaa");
        double totalThrust = 1.15E+06;
        double expResult = -2.26E+03;
        double time = 120;
        double result = Physics.calculateFuelBurned(aircraft, totalThrust, time);
        assertEquals(expResult, result, 20);
    }

    /**
     * Test of calculateAltitudeVariation method, of class Physics.
     */
    @Test
    public void testCalculateAltitudeVariation() {
        System.out.println("calculateAltitudeVariation");
        double trueAirSpeed = 108;
        double totalThrust = 1.15E+06;
        double dragForce = 426922.8;
        double maxWeight = 6.18E+05;
        double expResult = 13.0;
        double result = Physics.calculateAltitudeVariation(trueAirSpeed, totalThrust, dragForce, maxWeight);
        assertEquals(expResult, result, 13 * 0.01);

    }

    /**
     * Test of calculateClimbingAngle method, of class Physics.
     */
    @Test
    public void testCalculateClimbingAngle() {
        System.out.println("calculateClimbingAngle");
        double trueAirSpeed = 108.0;
        double climbRate = 13.0;
        double expResult = 0.12;
        double result = Physics.calculateClimbingAngle(trueAirSpeed, climbRate);
        assertEquals(expResult, result, 0.01 * 0.12);
    }

    /**
     * Test of calculatedWdT method, of class Physics.
     */
    @Test
    public void testCalculatedWdT() {
        System.out.println("calculatedWdT");
        Project project = listProjects.get(0);
        Aircraft aircraft = new Aircraft("aaa", 0, 0, 0, 120000, 320000, project.getAircraftModelRegister().getAircraftModelMap().get("A380"));
        project.getAircraftRegister().addAircraft(aircraft);
        aircraft.setDescription("aaa");
        double time = 120;
        double totalThrust = 1.15E+06;
        double expResult = 2.26E+03;
        double result = Physics.calculatedWdT(aircraft, time, totalThrust);
        assertEquals(expResult, result, 0.01 * expResult);
    }

    /**
     * Test of calculateDistanceTraveledWhileClimbing method, of class Physics.
     */
    @Test
    public void testCalculateDistanceTraveledWhileClimbing() {
        System.out.println("calculateDistanceTraveledWhileClimbing");
        double trueAirspeed = 108;
        double climbAngle = 0.12;
        double time = 120;
        double expResult = 12900;
        double result = Physics.calculateDistanceTraveledWhileClimbing(trueAirspeed, climbAngle, time);
        assertEquals(expResult, result, 0.01 * expResult);
    }

    /**
     * Test of calculateDistanceEach60SecAtCruiseAltitude method, of class
     * Physics.
     */
    @Test
    public void testCalculateDistanceEach60SecAtCruiseAltitude() {
        System.out.println("calculateDistanceEach60SecAtCruiseAltitude");
        Aircraft aircraft = null;
        double speed = 120;
        double expResult = 60 * 120;
        double result = Physics.calculateDistanceEach60SecAtCruiseAltitude(aircraft, speed);
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of calculateAircraftFinalWeight method, of class Physics.
     */
    @Test
    public void testCalculateAircraftFinalWeight() {
        System.out.println("calculateAircraftFinalWeight");
        Project project = listProjects.get(0);
        Aircraft aircraft = new Aircraft("aaa", 0, 0, 0, 0, 32000, project.getAircraftModelRegister().getAircraftModelMap().get("A380"));
        project.getAircraftRegister().addAircraft(aircraft);
        aircraft.setDescription("aaa");
        double expResult = aircraft.getCargo() + PhysicsConverters.litersToKgConverter(aircraft.getFuel())
                + (aircraft.getNumberElementsCrew() + aircraft.getNumberFirstClass() + aircraft.getNumberNormalClass()) * 195
                + aircraft.getModel().getEmptyWeight()
                + aircraft.getModel().getMaxPayload();
        double result = Physics.calculateAircraftFinalWeight(aircraft);
        assertEquals(expResult, result, expResult * 0.01);
    }

    /**
     * Test of aircraftClimb method, of class Physics.
     */
//    @Test
//    public void testAircraftClimb() {
//        System.out.println("aircraftClimb");
//
//        Project project = listProjects.get(0);
//        System.out.println(project.getDescription());
//        Aircraft aircraft = new Aircraft();
//        aircraft.setDescription("aaa");
//        aircraft.setNumberElementsCrew(0);
//        aircraft.setNumberFirstClass(0);
//        aircraft.setNumberNormalClass(0);
//        aircraft.setModel(project.getAircraftModelRegister().getAircraftModelMap().get("A380"));
//        
//        project.getAircraftRegister().addAircraft(aircraft);
//        System.out.print(project.getAircraftRegister().getAircraftRegister().values().toString());
//
//        Segment segment = null;
//
//        double expResult = 0;
//        expResult = 8499;
//        System.out.println("\n"+expResult);
//        double result = Physics.aircraftClimb(aircraft);
//        System.out.println("\naaaa:" +result);
//        
//        assertEquals(expResult, result, 500);
//    }
//    /**
//     * Test of aircraftDescent method, of class Physics.
//     */
//    @Test
//    public void testAircraftDescent() {
//        System.out.println("aircraftDescent");
//        Aircraft aircraft = null;
//        double[] valuesVec = null;
//        Airport airport = null;
//        double[] expResult = null;
//        double[] result = Physics.aircraftDescent(aircraft, valuesVec, airport);
//        assertArrayEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of aircraftDistanceToDescent method, of class Physics.
//     */
//    @Test
//    public void testAircraftDistanceToDescent() {
//        System.out.println("aircraftDistanceToDescent");
//        Aircraft aircraft = null;
//        double[] valuesVec = null;
//        double totalDist = 0.0;
//        double[] expResult = null;
//        double[] result = Physics.aircraftDistanceToDescent(aircraft, valuesVec, totalDist);
//        assertArrayEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of aircraftCruiseAltitudeCalculations method, of class Physics.
//     */
//    @Test
//    public void testAircraftCruiseAltitudeCalculations() {
//        System.out.println("aircraftCruiseAltitudeCalculations");
//        Aircraft aircraft = null;
//        double[] valuesVec = null;
//        double distanceToDescend = 0.0;
//        double[] expResult = null;
//        double[] result = Physics.aircraftCruiseAltitudeCalculations(aircraft, valuesVec, distanceToDescend);
//        assertArrayEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
    /**
     * Test of allFlightCalculations method, of class Physics.
     */
    @Test
    public void testAllFlightCalculations() {
        System.out.println("allFlightCalculations");

        Project project = listProjects.get(0);
        System.out.println(project.getDescription());
        Aircraft aircraft = new Aircraft("aaa", 0, 0, 0, 120000, 320000, project.getAircraftModelRegister().getAircraftModelMap().get("A380"));
        project.getAircraftRegister().addAircraft(aircraft);
        aircraft.setDescription("aaa");
        System.out.print(project.getAircraftRegister().getAircraftRegister().values().toString());
        double[][] matrix = ImportFlightPatternCSV.CSVImport("inOutFiles/Flight_pattern_A380_v1a.csv");

        Airport initialAirport = project.getAirportRegister().getAirportByIATACode("LIS");
        Airport endAirport = project.getAirportRegister().getAirportByIATACode("OPO");

        Node voInf = null;
        Node vdInf = null;

        System.out.println("\naaaaaaaaaa" + project.getAirNetwork().getMapNodes().values());

        for (Node node : project.getAirNetwork().getMapNodes().values()) {
            if (node.getLatitude() == initialAirport.getLocation().getLatitude()
                    && node.getLongitude() == initialAirport.getLocation().getLongitude()) {
                voInf = node;
            }
        }

        for (Node node : project.getAirNetwork().getMapNodes().values()) {
            if (node.getLatitude() == endAirport.getLocation().getLatitude()
                    && node.getLongitude() == endAirport.getLocation().getLongitude()) {
                vdInf = node;
            }
        }

        Deque<Node> shortPath = new ArrayDeque<>();

        LinkedList<Node> a = new LinkedList<>();
        Map<Double, LinkedList<Node>> aaaaaaaaa = project.getAirNetwork().getShortestPath(project.getAirNetwork().getNetwork(), voInf, vdInf, shortPath, aircraft);

        double dist = 0;
        Set<Double> b = aaaaaaaaa.keySet();

        Iterable<Double> b2 = b;
        Iterator<Double> it = b.iterator();

        while (it.hasNext()) {
            dist = it.next();
        }

        a = aaaaaaaaa.get(dist);

        Iterator<Node> nodess = a.iterator();

        List<Node> listNodes = new ArrayList<>();

        while (nodess.hasNext()) {
            listNodes.add(nodess.next());
        }

        Segment[] segments1 = new Segment[shortPath.size()];

        int i = 0;
        int i1 = 0;

        System.out.println("\nMap Segments total:" + project.getAirNetwork().getMapSegment());

        System.out.println("\nNodesList: " + listNodes);

        while (i < listNodes.size() - 1) {
            for (Segment segmentaa : project.getAirNetwork().getMapSegment().values()) {
                if (listNodes.get(i).equals(segmentaa.getBeginningNode()) && listNodes.get(i + 1).equals(segmentaa.getEndNode())
                        || listNodes.get(i).equals(segmentaa.getEndNode()) && listNodes.get(i + 1).equals(segmentaa.getBeginningNode())) {
                    segments1[i1] = segmentaa;
                    i1++;
                    i++;
                    if (i == listNodes.size() - 1) {
                        break;
                    }
                }
            }
        }

        System.out.println("\nShortPath" + shortPath);

        for (i = 0; i < segments1.length; i++) {
            System.out.println("\nArraySegments" + segments1[i]);
        }
        double totalDistance = 0;

        for (i = 0; i <= segments1.length; i++) {
            if (segments1[i] != null) {
                totalDistance = totalDistance + calculateSegmentDistance(aircraft, segments1[i]);
            } else {
                break;
            }
        }

        System.out.println(Physics.calculateAircraftFinalWeight(aircraft));
        double[] c = new double[8];
//        c[0] = 7;
//        c[1] = 1039777;
//        c[2] = totalDistance;
//        c[3] = 179420;
//        c[4] = 12300;
//        c[5] = 2;

        c[0] = 69;
//        c[1] = 591773;
//        c[2] = totalDistance;
//        c[3] = 179420;
//        c[4] = 20730;
//        c[5] = 3;

        HashMap<Integer, double[]> ap = new HashMap<>();
        double[] expResult = c;
        for (i = 0; i < expResult.length; i++) {
            System.out.println("\na:" + expResult[i]);
        }
        double[] result = Physics.allFlightCalculations(aircraft, initialAirport, endAirport, dist, segments1, matrix, ap);

        for (i = 0; i < result.length; i++) {
            System.out.println("\naa:" + result[i]);
        }

        System.out.println("\ndistance:" + totalDistance);
        System.out.println("\ndistance2:" + result[2]);
        
        double[] a1 = ap.get(1);
        double[] a2 = ap.get(2);
        double[] a3 = ap.get(3);

        for (i = 0; i < a1.length; i++) {
            System.out.println(a1[i]);
        }
        for (i = 0; i < a2.length; i++) {
            System.out.println(a2[i]);
        }
        for (i = 0; i < a3.length; i++) {
            System.out.println(a3[i]);
        }

        System.out.println("\n::::::::" + dist);
        assertEquals(expResult[0], result[0], expResult[0] * 0.01);
//        assertEquals(expResult[1], result[1], expResult[1] * 0.01);
//        assertEquals(expResult[2], result[2], expResult[2] * 0.01);
//        assertEquals(expResult[3], result[3], expResult[2] * 0.01);
//        assertEquals(expResult[4], result[4], expResult[4] * 0.01);
//        assertEquals(expResult[5], result[5], expResult[5] * 0.01);

    }
}
