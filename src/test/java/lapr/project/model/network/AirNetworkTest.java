/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model.network;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import lapr.project.model.Aircraft;
import lapr.project.model.AircraftModel;
import lapr.project.model.Airport;
import lapr.project.model.FlightPlan;
import lapr.project.model.Project;
import lapr.project.model.graph.Graph;
import lapr.project.utils.AircraftStAXParser;
import lapr.project.utils.AirportStAXParser;
import lapr.project.utils.ImportFlightPatternCSV;
import lapr.project.utils.NetworkStAXParser;
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
public class AirNetworkTest {


    private List<Project> listProjects;

    public AirNetworkTest() {
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
    }

    /**
     * Test of isReachable method, of class AirNetwork.
     */
    @Test
    public void testIsReachable() {
        System.out.println("isReachable");
        
        Project project = listProjects.get(0);
        Node nOrigin = project.getAirNetwork().getNode("PT01");
        Node nEnd = project.getAirNetwork().getNode("USE01");
        AirNetwork instance = project.getAirNetwork();

        boolean expResult = true;
        boolean result = instance.isReachable(nOrigin, nEnd);

        assertEquals(expResult, result);
    }

    /**
     * Test of isReachable method, of class AirNetwork.
     */
    @Test
    public void testIsReachable2() {
        System.out.println("isReachable2");
        
        Project project = listProjects.get(0);
        Node nOrigin = project.getAirNetwork().getNode("PT01");
        Node nEnd = project.getAirNetwork().getNode("USE03");
        AirNetwork instance = project.getAirNetwork();

        boolean expResult = false;
        boolean result = instance.isReachable(nOrigin, nEnd);

        assertEquals(expResult, result);
    }


    /**
     * Test of getShortestPath method, of class AirNetwork.
     */
    @Test
    public void testGetShortestPath() {
        System.out.println("getShortestPath");

        Project project = listProjects.get(0);
        System.out.println(project.getDescription());
        Aircraft aircraft = new Aircraft("aaa", 0, 0, 0, 0, 320000, project.getAircraftModelRegister().getAircraftModelMap().get("A380"));
        project.getAircraftRegister().addAircraft(aircraft);
        aircraft.setDescription("aaa");

        project.getAircraftRegister().addAircraft(aircraft);
        System.out.print(project.getAircraftRegister().getAircraftRegister().values().toString());
        Airport initialAirport = project.getAirportRegister().getAirportByIATACode("LIS");
        Airport endAirport = project.getAirportRegister().getAirportByIATACode("MAD");

        Node voInf = null;
        Node vdInf = null;

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
        double[][] matrix = this.matrix;

        Map<Double, LinkedList<Node>> expResult = new HashMap<>();
        LinkedList<Node> a=new LinkedList<Node>();
        a.add(project.getAirNetwork().getNode("PT03"));
        a.add(project.getAirNetwork().getNode("PT02"));
        a.add(project.getAirNetwork().getNode("ES01"));
        expResult.put(658448.52154988, a);
        Map<Double, LinkedList<Node>> result = project.getAirNetwork().getShortestPath(project.getAirNetwork().getNetwork(), voInf, vdInf, shortPath, aircraft);
        assertEquals(expResult, result);
    }


    /**
     * Test of getFastestPath0 method, of class AirNetwork.
     */
    @Test
    public void testGetFastestPath0() {
        System.out.println("getFastestPath0");
        
        Project project = listProjects.get(0);
        System.out.println(project.getDescription());
        Aircraft aircraft = new Aircraft("aaa", 0, 0, 0, 0, 320000, project.getAircraftModelRegister().getAircraftModelMap().get("A380"));
        project.getAircraftRegister().addAircraft(aircraft);
        aircraft.setDescription("aaa");

        project.getAircraftRegister().addAircraft(aircraft);
        System.out.print(project.getAircraftRegister().getAircraftRegister().values().toString());
        Airport initialAirport = project.getAirportRegister().getAirportByIATACode("LIS");
        Airport endAirport = project.getAirportRegister().getAirportByIATACode("MAD");

        Node voInf = null;
        Node vdInf = null;

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
        double[][] matrix = this.matrix;

        Map<Double, LinkedList<Node>> expResult = new HashMap<>();
        LinkedList<Node> a=new LinkedList<Node>();
        a.add(project.getAirNetwork().getNode("PT03"));
        a.add(project.getAirNetwork().getNode("PT02"));
        a.add(project.getAirNetwork().getNode("ES01"));
        expResult.put(4448.1629573837145, a);
        Map<Double, LinkedList<Node>> result = AirNetwork.getFastestPath0(project.getAirNetwork().getNetwork(), voInf, vdInf, shortPath, aircraft, initialAirport, endAirport, matrix);
        assertEquals(expResult, result);

    }

    /**
     * Test of getBestPath0 method, of class AirNetwork.
     */
    @Test
    public void testGetBestPath0() {
        System.out.println("getBestPath0");
        Project project = listProjects.get(0);
        System.out.println(project.getDescription());
        Aircraft aircraft = new Aircraft("aaa", 0, 0, 0, 0, 320000, project.getAircraftModelRegister().getAircraftModelMap().get("A380"));
        project.getAircraftRegister().addAircraft(aircraft);
        aircraft.setDescription("aaa");

        project.getAircraftRegister().addAircraft(aircraft);
        System.out.print(project.getAircraftRegister().getAircraftRegister().values().toString());
        Airport initialAirport = project.getAirportRegister().getAirportByIATACode("LIS");
        Airport endAirport = project.getAirportRegister().getAirportByIATACode("MAD");

        Node voInf = null;
        Node vdInf = null;

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
        double[][] matrix = this.matrix;

        Map<Double, LinkedList<Node>> expResult = new HashMap<>();
        LinkedList<Node> a=new LinkedList<Node>();
        a.add(project.getAirNetwork().getNode("PT03"));
        a.add(project.getAirNetwork().getNode("PT02"));
        a.add(project.getAirNetwork().getNode("ES01"));
        expResult.put(33190.24188148072, a);
        Map<Double, LinkedList<Node>> result = AirNetwork.getBestPath0(project.getAirNetwork().getNetwork(), voInf, vdInf, shortPath, aircraft, initialAirport, endAirport, matrix);
        assertEquals(expResult, result);
    }

}
