/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Set;
import lapr.project.model.network.AirNetwork;
import lapr.project.model.network.Node;
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

//    /**
//     * Test of calculateAirDensityTemperatureDueAltitude method, of class
//     * Physics.
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
//        double altitude = 0.0;
//        double expResult = 0.0;
//        double result = Physics.calculateLiftForceInASegment(aircraft, altitude);
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
//        double altitude = 0.0;
//        double expResult = 0.0;
//        double result = Physics.calculateDragForceInASegment(aircraft, altitude);
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
//        double altitude = 0.0;
//        double expResult = 0.0;
//        double result = Physics.calculateLiftCoeficient(aircraft, altitude);
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
//        Double altitude = null;
//        double expResult = 0.0;
//        double result = Physics.calculateDragCoeficient(aircraft, altitude);
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
//
//        Project project = listProjects.get(0);
//        System.out.println(project.getDescription());
//        Aircraft aircraft = new Aircraft("aaa", 0, 0, 0, 120000, 320000, project.getAircraftModelRegister().getAircraftModelMap().get("A380"));
//        project.getAircraftRegister().addAircraft(aircraft);
//        aircraft.setDescription("aaa");
//        System.out.print(project.getAircraftRegister().getAircraftRegister().values().toString());
//
//        Airport initialAirport = project.getAirportRegister().getAirportByIATACode("OPO");
//        Airport endAirport = project.getAirportRegister().getAirportByIATACode("MAD");
//        
//        Node node1= project.getAirNetwork().getNode("PT01");
//        Node node2= project.getAirNetwork().getNode("PT02");
//        Node node3= project.getAirNetwork().getNode("ES01");
//        
//        
//        Segment segment1= new Segment();
//        Segment segment2= new Segment();
//        
//        for (Segment segment : project.getAirNetwork().getMapSegment().values()) {
//            if(segment.getBeginningNode().equals(node1) && segment.getEndNode().equals(node2)){
//                segment1=segment;
//            }
//            if(segment.getBeginningNode().equals(node2) && segment.getEndNode().equals(node3)){
//                segment2=segment;
//            }
//        }
//        
//        double expResult = 0;
//
//        System.out.println("\na:" + expResult);
//
//        double result = Physics.calculateSegmentDistance(aircraft, segment1) + Physics.calculateSegmentDistance(aircraft, segment2);
//
//        System.out.println("\naa:" + result);
//
//        assertEquals(expResult, result);
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
//        double altitude = 0.0;
//        double trueMachNumber = 0.0;
//        double expResult = 0.0;
//        double result = Physics.calculateThrustAltitude(aircraft, altitude, trueMachNumber);
//        assertEquals(expResult, result, 0.0);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of calculateTotalThrust method, of class Physics.
//     */
//    @Test
//    public void testCalculateTotalThrust() {
//        System.out.println("calculateTotalThrust");
//        Aircraft aircraft = null;
//        double thrust = 0.0;
//        double expResult = 0.0;
//        double result = Physics.calculateTotalThrust(aircraft, thrust);
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
//        double thrustTotal = 0.0;
//        double dragForce = 0.0;
//        double maxWeight = 0.0;
//        double trueAirSpeed = 0.0;
//        double expResult = 0.0;
//        double result = Physics.calculateAircraftClimbRate(aircraft, thrustTotal, dragForce, maxWeight, trueAirSpeed);
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
//        double trueAirSpeed = 0.0;
//        double totalThrust = 0.0;
//        double dragForce = 0.0;
//        double maxWeight = 0.0;
//        double expResult = 0.0;
//        double result = Physics.calculateAltitudeVariation(trueAirSpeed, totalThrust, dragForce, maxWeight);
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
//
//    /**
//     * Test of calculateDistanceEach60SecAtCruiseAltitude method, of class
//     * Physics.
//     */
//    @Test
//    public void testCalculateDistanceEach60SecAtCruiseAltitude() {
//        System.out.println("calculateDistanceEach60SecAtCruiseAltitude");
//        Aircraft aircraft = null;
//        double speed = 0.0;
//        double expResult = 0.0;
//        double result = Physics.calculateDistanceEach60SecAtCruiseAltitude(aircraft, speed);
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

        Airport initialAirport = project.getAirportRegister().getAirportByIATACode("LIS");
        Airport endAirport = project.getAirportRegister().getAirportByIATACode("PMI");

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

//        for (Node node : project.getAirNetwork().getMapNodes().values()) {
//            shortPath.add(node);
//        }
        LinkedList<Node> a = new LinkedList<>();
        Map<Double, LinkedList<Node>> aaaaaaaaa = project.getAirNetwork().getShortestPath(project.getAirNetwork().getNetwork(), voInf, vdInf, shortPath, aircraft);

        double dist = 0;
        Set<Double> b = aaaaaaaaa.keySet();
        
        Iterable<Double> b2 = b;
        Iterator<Double> it= b.iterator();
        
        while(it.hasNext()){
            dist=it.next();
        }
        
        //dist*1000;

        a = aaaaaaaaa.get(dist);

       
        Iterator<Node> nodess = a.iterator();
      
        
        List <Node> listNodes = new ArrayList<>();
        
        while (nodess.hasNext()) {
            listNodes.add(nodess.next());
        }
        
        Segment[] segments1 = new Segment[shortPath.size()];
        int i = 0;
        int i1 = 0;
        
        System.out.println("\nMap Segments total:"+project.getAirNetwork().getMapSegment());
        
        System.out.println("\nNodesList: "+listNodes);
        
        while (i < listNodes.size()-1) {
            for (Segment segmentaa : project.getAirNetwork().getMapSegment().values()) {
                if (listNodes.get(i).equals(segmentaa.getBeginningNode()) && listNodes.get(i + 1).equals(segmentaa.getEndNode())
                        ||listNodes.get(i).equals(segmentaa.getEndNode()) && listNodes.get(i + 1).equals(segmentaa.getBeginningNode())) {
                    segments1[i1] = segmentaa;
                    i1++;
                    i++;
                    if(i==listNodes.size()-1){
                        break;
                    }
                }
            }
        }

            System.out.println("\nShortPath" + shortPath);

            for (i = 0; i < segments1.length; i++) {
                System.out.println("\nArraySegments" + segments1[i]);
            }

            //List<Segment> segments = new ArrayList<>();

            double[] c = new double[8];
            c[0]=7;
            c[1]=605000;
            c[2]=dist;
            c[3]=1100000;
            c[4]=30000;
            c[5]=4000;
            c[6]=2;
            c[7]=300000;
            
            double[] expResult = c;
            for (i = 0; i < expResult.length; i++) {
                System.out.println("\na:" + expResult[i]);
            }
            double[] result = Physics.allFlightCalculations(aircraft, initialAirport, endAirport, dist, segments1);

            for (i = 0; i < result.length; i++) {
                System.out.println("\naa:" + result[i]);
            }

            assertEquals(expResult[0], result[0], 10);
            assertEquals(expResult[1], result[1], 10000);
            assertEquals(expResult[2], result[2], 10000);
            assertEquals(expResult[3], result[3], 2000);
            assertEquals(expResult[4], result[4], 300);
            assertEquals(expResult[5], result[5], 0);

    
    }
    
}