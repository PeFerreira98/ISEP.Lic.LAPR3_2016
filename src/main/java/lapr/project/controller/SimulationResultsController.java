/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.controller;

import com.sun.org.apache.bcel.internal.generic.AALOAD;
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
                    break;
                case "Shortest path":
                    return calculateShortestPath();
                case "Less energy consuption path":
                    break;
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

        Map<Double, LinkedList<Node>> mapShortest = project.getAirNetwork().getShortestPath(project.getAirNetwork().getNetwork(), voInf, vdInf, shortPath, aircraft);

        System.out.println(mapShortest);

        double dist = 0;
        Set<Double> b = mapShortest.keySet();

        Iterable<Double> b2 = b;
        Iterator<Double> it = b.iterator();

        while (it.hasNext()) {
            dist = it.next();
        }

        a = mapShortest.get(dist);

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
            this.arrayListSegments.add(segments1[i]);
        }
        this.arrayListSegments.remove(segments1.length - 1);

        System.out.println(Physics.calculateAircraftFinalWeight(aircraft));

        double[] result = Physics.allFlightCalculations(aircraft, initialAirport, endAirport, dist, segments1, this.flightPattern);

        for (i = 0; i < result.length; i++) {
            System.out.println("\naa:" + result[i]);
        }

        return result;
    }
}
