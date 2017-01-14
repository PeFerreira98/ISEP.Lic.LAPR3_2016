/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model.network;

import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import lapr.project.model.Aircraft;
import lapr.project.model.Airport;
import lapr.project.model.Physics;
import lapr.project.model.graph.Edge;
import lapr.project.model.graph.Graph;
import lapr.project.model.graph.GraphAlgorithms;
import lapr.project.model.graph.Vertex;

/**
 *
 * @author João
 */
public class AirNetwork {

    private Graph<Node, Segment> airNetwork;
    private Map<String, Node> map_Nodes;
    private Map<String, Segment> map_Segment;

    public AirNetwork() {
        this.airNetwork = new Graph<>(false);
        this.map_Nodes = new LinkedHashMap<>();
        this.map_Segment = new LinkedHashMap<>();

    }
    
    public AirNetwork(AirNetwork an){
        this();
        System.out.println(an.getMapNodes().values());
        for (Node nod : an.getMapNodes().values()) {
            this.addNode(new Node(nod));
        }
        for (Segment segment : an.map_Segment.values()) {
            this.addSegment(new Segment(segment));
        }
    }

    /**
     * Devolve o objecto airNetwork
     *
     * @return objecto airNetwork
     */
    public Graph<Node, Segment> getNetwork() {
        return airNetwork;
    }

    /**
     * Metodo utilizado para devolver a lista dos nodes.
     *
     * @return lst_Nodes
     */
    public Map<String, Node> getMapNodes() {
        return map_Nodes;
    }

    public Map<String, Segment> getMapSegment() {
        return map_Segment;
    }

    public Node getNode(String nodeId) {
        return this.map_Nodes.get(nodeId);
    }
    
    public Segment getSegment(String segmentId){
        return this.map_Segment.get(segmentId);
    }

    public Node getNodeByLocation(double latitude, double longitude) {
        for (Node node : this.map_Nodes.values()) {
            if (node.getLatitude() == latitude && node.getLongitude() == longitude) {
                return node;
            }
        }
        return null;
    }

    /**
     * Adiciona Node n a Network
     *
     * @param n Node adicionado
     */
    public void addNode(Node n) {
        map_Nodes.put(n.getName(), n);
        this.airNetwork.insertVertex(n);
    }

    /**
     * Adiciona Segment s a Network
     *
     * @param s Segment adicionado
     */
    public void addSegment(Segment s) {
        map_Segment.put(s.getId(), s);
        this.airNetwork.insertEdge(s.getBeginningNode(), s.getEndNode(), s, s.getDistance());
    }

    /**
     * Metodo utilizado para criar um grafo em que nenhum dos vertices (nodes)
     * sao becos sem saida
     *
     * @return true se o grafo foi criado correctamente, false caso contrario.
     */
    public boolean createGraph() {
        map_Nodes.values().stream().forEach((node) -> {
            airNetwork.insertVertex(node);
        });

        map_Segment.values().stream().forEach((segment) -> {
            airNetwork.insertEdge(segment.getBeginningNode(), segment.getEndNode(), segment, 0);
        });

        for (Vertex v : airNetwork.vertices()) {
            if (airNetwork.outDegree(v) == 0 || airNetwork.inDegree(v) == 0) {
                return false;
            }
        }
        return true;
    }

    public boolean isReachable(Node nOrigin, Node nEnd) {
        Deque<Node> path = GraphAlgorithms.BreadthFirstSearch(this.airNetwork, nOrigin);

        for (Node node : path) {
            if (node.equals(nEnd)) {
                return true;
            }
        }
        return false;
    }

    private static <V, E> void getPath(Graph<V, E> g, V voInf, V vdInf, int[] pathKeys, Deque<V> path) {

        if (voInf != vdInf) {
            path.push(vdInf);

            Vertex<V, E> vert = g.getVertex(vdInf);
            int vKey = vert.getKey();
            int prevVKey = pathKeys[vKey];
            vert = g.getVertex(prevVKey);
            vdInf = vert.getElement();

            getPath(g, voInf, vdInf, pathKeys, path);
        } else {
            path.push(voInf);
        }
    }

    //___________________________________________________fastestPath_______________________________________________
    public static <Node, Segment> double fastestPathLength(Graph<Node, Segment> g, Node voInf, Node vdInf, Deque<Node> shortPath, Aircraft aircraft, Airport initialAirport,
            Airport endAirport, double[][] matrix, double altitude) {

        Vertex<Node, Segment> vOrig = g.getVertex(voInf);
        Vertex<Node, Segment> vDest = g.getVertex(vdInf);

        if (vOrig == null || vDest == null) {
            return 0;
        }

        int nverts = g.numVertices();
        boolean[] visited = new boolean[nverts]; //default value: false
        int[] pathKeys = new int[nverts];
        double[] time = new double[nverts];

        for (int i = 0; i < nverts; i++) {
            time[i] = Double.MAX_VALUE;
            pathKeys[i] = -1;
        }

        fastestPathLength(g, vOrig, vDest, visited, pathKeys, time, aircraft, initialAirport, endAirport, matrix, altitude);

        double lengthPath = time[vDest.getKey()];

        if (lengthPath != Double.MAX_VALUE) {
            getPath(g, voInf, vdInf, pathKeys, shortPath);
            return lengthPath;
        }
        return 0;

    }

//    private static <V, E> void fastestPathLength(Graph<V, E> g, Vertex<V, E> vOrig, Vertex<V, E> vDest,
//            boolean[] visited, int[] pathKeys, double[] time, Aircraft aircraft, Airport initialAirport, Airport endAirport, double[][] matrix) {
//
//        Vertex<V, E> vAux;
//        //vAux=(Vertex<V,E>)endAirport;
//        double[] values = new double[10];
//        double[] valuesAcumulated = new double[10];
//        double[] vecAux = new double[10];
//        double[] valuesKeys = new double[100];
//        Map<Integer, double[]> mapValues = new HashMap<>();
//        Segment[] segments = new Segment[30];
//        int aux = 0;
//        for (int j = 0; j < values.length; j++) {
//            values[j] = 0;
//        }
//        int a = -1;
//        int vkeyOrig = vOrig.getKey();
//        Edge edgeAux = null;
//        Segment segment = null;
//        Segment segment2 = null;
//        time[vkeyOrig] = -1;
//        while (vkeyOrig != -1) {
//            visited[vkeyOrig] = true;
//            vOrig = g.getVertex(vkeyOrig);
//            if (a != -1) {
//                if (segments[a] == null && a + 1 <= segments.length) {
//                    //Segment segment = (Segment) edgeAux.getElement();
//                    segments[a] = segment;
//                }
//            }
//            for (Edge<V, E> edge : g.outgoingEdges(vOrig)) {
//                Vertex<V, E> vAdj = g.opposite(vOrig, edge);
//                int vkeyAdj = vAdj.getKey();
//                if (a <= segments.length) {
//                    segment2 = (Segment) edge.getElement();
//                    segments[a + 1] = segment2;
//                }
//                for (int k = 0; k < values.length; k++) {
//                    vecAux[k] = values[k];
//                }
//                if (vAdj.getElement().equals(vDest) && !visited[vkeyAdj] && time[vkeyAdj] > time[vkeyOrig] + Physics.calculateTravelTimeInASegmentDescending(aircraft, initialAirport, segments,
//                        values, matrix, valuesAcumulated, endAirport)) {
//                    for (int k = 0; k < values.length; k++) {
//                        values[k] = vecAux[k];
//                    }
//
//                    if (mapValues.get(vkeyAdj) == null) {
//                        mapValues.put(vkeyAdj, values);
//                    }
//                    time[vkeyAdj] = time[vkeyOrig] + Physics.calculateTravelTimeInASegmentDescending(aircraft, initialAirport, segments,
//                            values, matrix, mapValues.get(vkeyAdj), endAirport);
//                    pathKeys[vkeyAdj] = vkeyOrig;
//
//                    int v = 0;
//                    for (double value : mapValues.get(vkeyAdj)) {
//                        value = value + valuesAcumulated[v];
//                        v++;
//                    }
//                    aux = 1;
//                }
//                for (int k = 0; k < values.length; k++) {
//                    vecAux[k] = values[k];
//                }
//                if (aux == 0 && !visited[vkeyAdj] && time[vkeyAdj] > time[vkeyOrig] + Physics.calculateTravelTimeInASegment2(aircraft, initialAirport, segments,
//                        values, matrix, valuesAcumulated)) {
//                    for (int k = 0; k < values.length; k++) {
//                        values[k] = vecAux[k];
//                    }
//                    if (mapValues.get(vkeyAdj) == null) {
//                        mapValues.put(vkeyAdj, values);
//                    }
//
//                    time[vkeyAdj] = time[vkeyOrig] + Physics.calculateTravelTimeInASegment2(aircraft, initialAirport, segments,
//                            values, matrix, mapValues.get(vkeyAdj));
//                    pathKeys[vkeyAdj] = vkeyOrig;
//                    int v = 0;
////                    if (mapValues.get(vkeyAdj) == null) {
////                        mapValues.put(vkeyAdj, values);
////                    }
//
//                    for (double value : mapValues.get(vkeyAdj)) {
//                        value = value + valuesAcumulated[v];
//                        v++;
//                    }
//                }
//            }
//            double minTime = Double.MAX_VALUE;
//            vkeyOrig = -1;
//            for (int i = 0; i < g.numVertices(); i++) {
//                if (!visited[i] && time[i] < minTime) {
//                    minTime = time[i];
//                    vkeyOrig = i;
//                }
//            }
//            if (minTime == Double.MAX_VALUE) {
//                vkeyOrig = -1;
//            }
//            a++;
//            segment = segment2;
//            values[5] = a;
//            values[9] = 1;
//
//        }
//
//    }
    private static <V, E> void fastestPathLength(Graph<V, E> g, Vertex<V, E> vOrig, Vertex<V, E> vDest,
            boolean[] visited, int[] pathKeys, double[] time, Aircraft aircraft, Airport initialAirport, Airport endAirport, double[][] matrix, double altitude) {

        int vkeyOrig = vOrig.getKey();
        time[vkeyOrig] = 0;
        while (vkeyOrig != -1) {
            visited[vkeyOrig] = true;
            vOrig = g.getVertex(vkeyOrig);
            for (Edge<V, E> edge : g.outgoingEdges(vOrig)) {
                Vertex<V, E> vAdj = g.opposite(vOrig, edge);
                int vkeyAdj = vAdj.getKey();
                if (!visited[vkeyAdj] && time[vkeyAdj] > time[vkeyOrig] + Physics.calculateTravelTimeInASegment2(aircraft, (Segment) edge.getElement(), altitude, matrix)) {
                    time[vkeyAdj] = time[vkeyOrig] + Physics.calculateTravelTimeInASegment2(aircraft, (Segment) edge.getElement(), altitude, matrix);
                    pathKeys[vkeyAdj] = vkeyOrig;
                }
            }
            double minTime = Double.MAX_VALUE;
            vkeyOrig = -1;
            for (int i = 0; i < g.numVertices(); i++) {
                if (!visited[i] && time[i] < minTime) {
                    minTime = time[i];
                    vkeyOrig = i;
                }
            }
            if (minTime == Double.MAX_VALUE) {
                vkeyOrig = -1;
            }
        }

    }

    public static Map<Double, LinkedList<Node>> getFastestPath0(Graph<Node, Segment> g, Node voInf, Node vdInf, Deque<Node> shortPath, Aircraft aircraft, Airport initialAirport,
            Airport endAirport, double[][] matrix) {

        //new HashMap<>();
        Map<Double, LinkedList<Node>> finalTime = new HashMap<>();
        double[] valuesVec = new double[10];
        double time2 = 0;
        valuesVec = Physics.aircraftClimb3(aircraft, valuesVec, initialAirport, matrix);
        time2 = valuesVec[4];
        double altitude = valuesVec[0];
        valuesVec = Physics.aircraftDescent3(aircraft, valuesVec, endAirport, matrix);
        time2 = time2 + valuesVec[4];
        Map<Double, LinkedList<Node>> finalTimeAux= getFastestPath(g, voInf, vdInf, shortPath, aircraft, initialAirport, endAirport, matrix, altitude);
        
        Set<Double> a = finalTimeAux.keySet();
        
        LinkedList<Node> b = new LinkedList<>();
        Iterator<Double> it = a.iterator();

        LinkedList<Node> lNodes = new LinkedList<>();
        double time = 0;
        while (it.hasNext()) {
            time = it.next();
        }
        
        lNodes = finalTimeAux.get(time);
        

        Iterator<Node> nodess = lNodes.iterator();

        List<Node> listNodes2 = new ArrayList<>();

        while (nodess.hasNext()) {
            listNodes2.add(nodess.next());
        }
        int i = 0;
        Node node1 = new Node();
        Node node2 = new Node();

        for (Node node : listNodes2) {
            if (node.getLatitude() == initialAirport.getLocation().getLatitude() && node.getLongitude() == initialAirport.getLocation().getLongitude()) {
                node1 = node;
            } else if (node.getLatitude() == endAirport.getLocation().getLatitude() && node.getLongitude() == endAirport.getLocation().getLongitude()) {
                node2 = node;
            }
        }

        Segment seg1 = new Segment();
        Segment seg2 = new Segment();

        for (Node node : listNodes2) {
            if (listNodes2.get(i).equals(node1)) {
                Edge edge1 = g.getEdge(g.getVertex(node), g.getVertex(listNodes2.get(i + 1)));
                seg1 = (Segment) edge1.getElement();
            } else if (listNodes2.get(i).equals(node2)) {
                Edge edge2 = g.getEdge(g.getVertex(listNodes2.get(i-1)), g.getVertex(node2));
                seg2 = (Segment) edge2.getElement();
            }
            i++;
        }

        time2 = time2 - Physics.calculateTravelTimeInASegment2(aircraft, seg1, altitude/2, matrix) - Physics.calculateTravelTimeInASegment2(aircraft, seg2, altitude/2, matrix);

        finalTime.put(time + time2, lNodes);
        return finalTime;
    }

    private static <V, E> void getPathFastest(Graph<V, E> g, V voInf, V vdInf, int[] pathKeys, Deque<V> path) {

        if (voInf != vdInf) {
            path.push(vdInf);

            Vertex<V, E> vert = g.getVertex(vdInf);
            int vKey = vert.getKey();
            int prevVKey = pathKeys[vKey];
            vert = g.getVertex(prevVKey);
            vdInf = vert.getElement();

            getPath(g, voInf, vdInf, pathKeys, path);
        } else {
            path.push(voInf);

        }
    }
    // para apagar

    public static Map<Double, LinkedList<Node>> getFastestPath(Graph<Node, Segment> g, Node voInf, Node vdInf, Deque<Node> shortPath, Aircraft aircraft, Airport initialAirport,
            Airport endAirport, double[][] matrix, double altitude) {
        LinkedList<Node> path = new LinkedList<>();
        Map<Double, LinkedList<Node>> map = new LinkedHashMap<>();

        double time = fastestPathLength(g, voInf, vdInf, shortPath, aircraft, initialAirport, endAirport, matrix, altitude);
        for (Node a : shortPath) {
            path.add(a);
        }
        map.put(time, path);

        System.out.println(map);
        return map;
    }

    //__________________________________________Shortest Path_____________________________________________________
    public Map<Double, LinkedList<Node>> getShortestPath(Graph<Node, Segment> g, Node voInf, Node vdInf, Deque<Node> shortPath, Aircraft aircraft) {
        LinkedList<Node> path = new LinkedList<>();
        Map<Double, LinkedList<Node>> map = new LinkedHashMap<>();

        double distance = shortestPathLength(g, voInf, vdInf, shortPath, aircraft);
        for (Node a : shortPath) {
            path.add(a);
        }
        map.put(distance, path);

        return map;
    }

    private static <V, E> void shortestPathLength(Graph<V, E> g, Vertex<V, E> vOrig,
            boolean[] visited, int[] pathKeys, double[] distance, Aircraft aircraft) {

        int vkeyOrig = vOrig.getKey();
        distance[vkeyOrig] = 0;
        while (vkeyOrig != -1) {
            visited[vkeyOrig] = true;
            vOrig = g.getVertex(vkeyOrig);
            for (Edge<V, E> edge : g.outgoingEdges(vOrig)) {
                Vertex<V, E> vAdj = g.opposite(vOrig, edge);
                int vkeyAdj = vAdj.getKey();
                if (!visited[vkeyAdj] && distance[vkeyAdj] > distance[vkeyOrig] + Physics.calculateSegmentDistance(aircraft, (Segment) edge.getElement())) {
                    distance[vkeyAdj] = distance[vkeyOrig] + Physics.calculateSegmentDistance(aircraft, (Segment) edge.getElement());
                    pathKeys[vkeyAdj] = vkeyOrig;
                }
            }
            double minDistance = Double.MAX_VALUE;
            vkeyOrig = -1;
            for (int i = 0; i < g.numVertices(); i++) {
                if (!visited[i] && distance[i] < minDistance) {
                    minDistance = distance[i];
                    vkeyOrig = i;
                }
            }
            if (minDistance == Double.MAX_VALUE) {
                vkeyOrig = -1;
            }
        }
    }

    public static <Node, Segment> double shortestPathLength(Graph<Node, Segment> g, Node voInf, Node vdInf, Deque<Node> shortPath, Aircraft aircraft) {

        Vertex<Node, Segment> vOrig = g.getVertex(voInf);
        Vertex<Node, Segment> vDest = g.getVertex(vdInf);

        if (vOrig == null || vDest == null) {
            return 0;
        }

        int nverts = g.numVertices();
        boolean[] visited = new boolean[nverts]; //default value: false
        int[] pathKeys = new int[nverts];
        double[] distance = new double[nverts];

        for (int i = 0; i < nverts; i++) {
            distance[i] = Double.MAX_VALUE;
            pathKeys[i] = -1;
        }

        shortestPathLength(g, vOrig, visited, pathKeys, distance, aircraft);

        double lengthPath = distance[vDest.getKey()];

        if (lengthPath != Double.MAX_VALUE) {
            getPath(g, voInf, vdInf, pathKeys, shortPath);
            return lengthPath;
        }
        return 0;

    }

    //__________________________________________Best Path_____________________________________________________
    public static Map<Double, LinkedList<Node>> getBestPath(Graph<Node, Segment> g, Node voInf, Node vdInf, Deque<Node> shortPath, Aircraft aircraft, double[][] matrix, double altitude) {
        LinkedList<Node> path = new LinkedList<>();
        Map<Double, LinkedList<Node>> map = new LinkedHashMap<>();

        double consumption = bestPathLength(g, voInf, vdInf, shortPath, aircraft, matrix, altitude);
        for (Node a : shortPath) {
            path.add(a);
        }
        map.put(consumption, path);

        System.out.println(map);
        return map;
    }

    private static <V, E> void bestPathLength(Graph<V, E> g, Vertex<V, E> vOrig,
            boolean[] visited, int[] pathKeys, double[] fuelComsuption, Aircraft aircraft, double[][] matrix, double altitude) {

        int vkeyOrig = vOrig.getKey();
        fuelComsuption[vkeyOrig] = 0;
        while (vkeyOrig != -1) {
            visited[vkeyOrig] = true;
            vOrig = g.getVertex(vkeyOrig);
            for (Edge<V, E> edge : g.outgoingEdges(vOrig)) {
                Vertex<V, E> vAdj = g.opposite(vOrig, edge);
                int vkeyAdj = vAdj.getKey();
                if (!visited[vkeyAdj] && fuelComsuption[vkeyAdj] > fuelComsuption[vkeyOrig] + Physics.calculateFuelComsuptionEachSegment(aircraft, (Segment) edge.getElement(), matrix, altitude)) {
                    fuelComsuption[vkeyAdj] = fuelComsuption[vkeyOrig] + Physics.calculateFuelComsuptionEachSegment(aircraft, (Segment) edge.getElement(), matrix, altitude);
                    pathKeys[vkeyAdj] = vkeyOrig;
                }
            }
            double minFuelC = Double.MAX_VALUE;
            vkeyOrig = -1;
            for (int i = 0; i < g.numVertices(); i++) {
                if (!visited[i] && fuelComsuption[i] < minFuelC) {
                    minFuelC = fuelComsuption[i];
                    vkeyOrig = i;
                }
            }
            if (minFuelC == Double.MAX_VALUE) {
                vkeyOrig = -1;
            }
        }
    }

    public static <Node, Segment> double bestPathLength(Graph<Node, Segment> g, Node voInf, Node vdInf, Deque<Node> shortPath, Aircraft aircraft, double matrix[][], double altitude) {

        Vertex<Node, Segment> vOrig = g.getVertex(voInf);
        Vertex<Node, Segment> vDest = g.getVertex(vdInf);

        if (vOrig == null || vDest == null) {
            return 0;
        }

        int nverts = g.numVertices();
        boolean[] visited = new boolean[nverts]; //default value: false
        int[] pathKeys = new int[nverts];
        double[] fuelConsumption = new double[nverts];

        for (int i = 0; i < nverts; i++) {
            fuelConsumption[i] = Double.MAX_VALUE;
            pathKeys[i] = -1;
        }

        bestPathLength(g, vOrig, visited, pathKeys, fuelConsumption, aircraft, matrix, altitude);

        double lengthPath = fuelConsumption[vDest.getKey()];

        if (lengthPath != Double.MAX_VALUE) {
            getPath(g, voInf, vdInf, pathKeys, shortPath);
            return lengthPath;
        }
        return 0;

    }
    
    public static Map<Double, LinkedList<Node>> getBestPath0(Graph<Node, Segment> g, Node voInf, Node vdInf, Deque<Node> shortPath, Aircraft aircraft, Airport initialAirport,
            Airport endAirport, double[][] matrix) {

        //new HashMap<>();
        Map<Double, LinkedList<Node>> finalConsumption = new HashMap<>();
        double[] valuesVec = new double[10];
        double consumption2 = 0;
        valuesVec = Physics.aircraftClimb3(aircraft, valuesVec, initialAirport, matrix);
        consumption2 = valuesVec[3];
        double altitude = valuesVec[0];
        valuesVec = Physics.aircraftDescent3(aircraft, valuesVec, endAirport, matrix);
        consumption2 = consumption2 + valuesVec[3];
        Map<Double, LinkedList<Node>> finalConsumptionAux= getBestPath(g, voInf, vdInf, shortPath, aircraft, matrix, altitude);
        
        Set<Double> a = finalConsumptionAux.keySet();
        
        LinkedList<Node> b = new LinkedList<>();
        Iterator<Double> it = a.iterator();

        LinkedList<Node> lNodes = new LinkedList<>();
        double consumption = 0;
        while (it.hasNext()) {
            consumption = it.next();
        }
        
        lNodes = finalConsumptionAux.get(consumption);
        

        Iterator<Node> nodess = lNodes.iterator();

        List<Node> listNodes2 = new ArrayList<>();

        while (nodess.hasNext()) {
            listNodes2.add(nodess.next());
        }
        int i = 0;
        Node node1 = new Node();
        Node node2 = new Node();

        for (Node node : listNodes2) {
            if (node.getLatitude() == initialAirport.getLocation().getLatitude() && node.getLongitude() == initialAirport.getLocation().getLongitude()) {
                node1 = node;
            } else if (node.getLatitude() == endAirport.getLocation().getLatitude() && node.getLongitude() == endAirport.getLocation().getLongitude()) {
                node2 = node;
            }
        }

        Segment seg1 = new Segment();
        Segment seg2 = new Segment();

        for (Node node : listNodes2) {
            if (listNodes2.get(i).equals(node1)) {
                Edge edge1 = g.getEdge(g.getVertex(node), g.getVertex(listNodes2.get(i + 1)));
                seg1 = (Segment) edge1.getElement();
            } else if (listNodes2.get(i).equals(node2)) {
                Edge edge2 = g.getEdge(g.getVertex(listNodes2.get(i-1)), g.getVertex(node2));
                seg2 = (Segment) edge2.getElement();
            }
            i++;
        }

        consumption2 = consumption2 - Physics.calculateFuelComsuptionEachSegment(aircraft, seg1, matrix, altitude/2) - Physics.calculateFuelComsuptionEachSegment(aircraft, seg2, matrix, altitude/0.95);
        // a subir o aviao gasta muito mais, daí considerar valores diferentes na altitude, porque a altitude muda a velocidade
        finalConsumption.put(consumption + consumption2, lNodes);
        return finalConsumption;
    }
    

    @Override
    public String toString() {
        return "AirNetwork{" + "airNetwork=" + airNetwork + ", map_Nodes=" + map_Nodes + ", map_Segment=" + map_Segment + '}';
    }

}