/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.controller;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import lapr.project.database.DatabaseModel;
import lapr.project.model.Aircraft;
import lapr.project.model.Airport;
import lapr.project.model.Flight;
import lapr.project.model.FlightPlan;
import lapr.project.model.Physics;
import lapr.project.model.Project;
import lapr.project.model.network.AirNetwork;
import lapr.project.model.network.Node;
import lapr.project.model.network.Segment;
import lapr.project.utils.ExportCSV;
import lapr.project.utils.ExportHTML;

/**
 *
 * @author zero_
 */
public class SimulationResultsController {

    private static final Logger LOG = Logger.getLogger("SimulationResultsControllerLog");
    private Project project;
    private FlightPlan flightPlan;
    private Aircraft aircraft;
    private String flightPath;
    private double[][] flightPattern;
    private Flight flight;
    private ArrayList<Segment> arrayListSegments;

    public SimulationResultsController(Project project, FlightPlan flightPlan, Aircraft aircraft, String flightPath, double[][] flightPattern) {
        this.project = project;
        this.flightPlan = flightPlan;
        this.aircraft = aircraft;
        this.flightPath = flightPath;
        this.flightPattern = flightPattern;
        this.arrayListSegments = new ArrayList<>();
    }

    public double[] calculatePathByAlgorithm() {
        System.out.println(flightPath);
        try {
            switch (flightPath) {
                case "Fastest path":
                    return calculateFastestPath();
                case "Shortest path":
                    return calculateShortestPath();
                case "Less energy consuption path":
                    return calculateBestPath();
            }
            return null;
        } catch (Exception e) {
            LOG.log(Level.INFO, "Error in Calculations >> ", e);
            return null;
        }

    }

    public ArrayList<Segment> getArrayListSegments() {
        return this.arrayListSegments;
    }

    public int getArrayListSegmentsSize() {
        return this.arrayListSegments.size();
    }

    public boolean exportHTML(String filePath) {
        try {
            new ExportHTML(filePath, this.flight);
            return true;
        } catch (Exception e) {
            LOG.log(Level.INFO, "Error Creating HTML File! > " + filePath, e);
            return false;
        }
    }

    public boolean exportCSV(String filePath) {
        try {
            ExportCSV.writeCSVFile(filePath, this.flight);
            return true;
        } catch (Exception e) {
            LOG.log(Level.INFO, "Error Creating CSV File! > " + filePath, e);
            return false;
        }
    }

    public boolean isReachable() {
        return this.project.isReachable(this.flightPlan.getOrigin(), this.flightPlan.getDest());
    }

    public Flight getFlight() {
        return this.flight;
    }

    public boolean saveFlightToDatabase() {
        DatabaseModel db = new DatabaseModel();
        try {
            db.addFlight(flight);
            return true;
        } catch (Exception e) {
            System.out.println("Error adding to database > " + e);
            return false;
        }
    }

    public boolean saveFlight(double time, double energy) {
        try {
            this.flight = new Flight(this.flightPlan, this.aircraft, this.arrayListSegments, time, energy);

            if (this.flight.equals(this.project.addFlight(flight))) {
                return true;
            }

        } catch (NullPointerException e) {
            System.out.println("Error Creating New Flight > " + e);
        }

        return false;
    }

    private double[] calculateShortestPath() {
        Airport initialAirport = this.flightPlan.getOrigin();
        Airport endAirport = this.flightPlan.getDest();
        Node voInf = getAirportNode(initialAirport);
        Node vdInf = getAirportNode(endAirport);

        Deque<Node> shortPath = new ArrayDeque<>();

        Map<Double, LinkedList<Node>> mapShortest = project.getAirNetwork().getShortestPath(project.getAirNetwork().getNetwork(), voInf, vdInf, shortPath, aircraft);

        return calculateData(mapShortest, shortPath);
    }

    private double[] calculateFastestPath() {
        Airport initialAirport = this.flightPlan.getOrigin();
        Airport endAirport = this.flightPlan.getDest();
        Node voInf = getAirportNode(initialAirport);
        Node vdInf = getAirportNode(endAirport);

        Deque<Node> fastPath = new ArrayDeque<>();

        Map<Double, LinkedList<Node>> mapFastest = AirNetwork.getFastestPath0(project.getAirNetwork().getNetwork(), voInf, vdInf, fastPath, aircraft, flightPlan.getOrigin(), flightPlan.getDest(), this.flightPattern);

        return calculateData(mapFastest, fastPath);
    }
    
    private double[] calculateBestPath(){
        Airport initialAirport = this.flightPlan.getOrigin();
        Airport endAirport = this.flightPlan.getDest();
        Node voInf = getAirportNode(initialAirport);
        Node vdInf = getAirportNode(endAirport);

        Deque<Node> bestPath = new ArrayDeque<>();
        
        Map<Double, LinkedList<Node>> mapBest = AirNetwork.getBestPath0(project.getAirNetwork().getNetwork(), voInf, vdInf, bestPath, aircraft, initialAirport, endAirport, this.flightPattern);
        
        return calculateData(mapBest, bestPath);
    }

    private double[] calculateData(Map<Double, LinkedList<Node>> mapPath, Deque<Node> path) {
        double distance = 0;
        LinkedList<Node> linkedListNode = new LinkedList<>();
        List<Node> listNodes = new ArrayList<>();

        System.out.println(mapPath);

        Set<Double> mapKeySet = mapPath.keySet();
        Iterator<Double> iterator = mapKeySet.iterator();

        while (iterator.hasNext()) {
            distance = iterator.next();
        }

        linkedListNode = mapPath.get(distance);
        Iterator<Node> iteratorNodes = linkedListNode.iterator();

        while (iteratorNodes.hasNext()) {
            listNodes.add(iteratorNodes.next());
        }

        Segment[] segments = getListSegmentsByNodes(listNodes, path.size());

        System.out.println("\nMap Segments total:" + project.getAirNetwork().getMapSegment());
        System.out.println("\nNodesList: " + listNodes);

        System.out.println("\nShortPath" + path);

        initializeArrayListSegments(segments);

        System.out.println(Physics.calculateAircraftFinalWeight(aircraft));

        double[] result = Physics.allFlightCalculations(aircraft, this.flightPlan.getOrigin(), this.flightPlan.getDest(), distance, segments, this.flightPattern);

        for (int i = 0; i < result.length; i++) {
            System.out.println("\naa:" + result[i]);
        }

        return result;
    }

    private Node getAirportNode(Airport airport) {
        for (Node node : project.getAirNetwork().getMapNodes().values()) {
            if (node.getLatitude() == airport.getLocation().getLatitude()
                    && node.getLongitude() == airport.getLocation().getLongitude()) {
                return node;
            }
        }
        return null;
    }

    private Segment[] getListSegmentsByNodes(List<Node> listNodes, int size) {
        int i = 0;
        int i1 = 0;
        Segment[] segments = new Segment[size];

        System.out.println("size:" + size);

        while (i < listNodes.size() - 1) {
            for (Segment segmentaa : project.getAirNetwork().getMapSegment().values()) {
                if (listNodes.get(i).equals(segmentaa.getBeginningNode()) && listNodes.get(i + 1).equals(segmentaa.getEndNode())
                        || listNodes.get(i).equals(segmentaa.getEndNode()) && listNodes.get(i + 1).equals(segmentaa.getBeginningNode())) {
                    segments[i1] = segmentaa;
                    i1++;
                    i++;
                    if (i == listNodes.size() - 1) {
                        break;
                    }
                }
            }
        }

        System.out.println(segments);

        return segments;
    }

    private void initializeArrayListSegments(Segment[] segments) {
        for (int i = 0; i < segments.length; i++) {
            System.out.println("\nArraySegments" + segments[i]);
            this.arrayListSegments.add(segments[i]);
        }
        this.arrayListSegments.remove(segments.length - 1);
    }
}
