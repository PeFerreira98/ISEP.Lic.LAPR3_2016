/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model.network;

import java.util.Deque;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;
import lapr.project.model.Aircraft;
import lapr.project.model.Physics;
import lapr.project.model.graph.Edge;
import lapr.project.model.graph.Graph;
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

    public static <Node, Segment> double fastestPathLength(Graph<Node, Segment> g, Node voInf, Node vdInf, Deque<Node> shortPath, Aircraft aircraft) {

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

        fastestPathLength(g, vOrig, visited, pathKeys, time, aircraft);

        double lengthPath = time[vDest.getKey()];

        if (lengthPath != Double.MAX_VALUE) {
            getPathFastest(g, voInf, vdInf, pathKeys, shortPath);
            return lengthPath;
        }
        return 0;

    }

    private static <V, E> void fastestPathLength(Graph<V, E> g, Vertex<V, E> vOrig,
            boolean[] visited, int[] pathKeys, double[] time, Aircraft aircraft) {

        int vkeyOrig = vOrig.getKey();
        time[vkeyOrig] = 0;
        while (vkeyOrig != -1) {
            visited[vkeyOrig] = true;
            vOrig = g.getVertex(vkeyOrig);
            for (Edge<V, E> edge : g.outgoingEdges(vOrig)) {
                Vertex<V, E> vAdj = g.opposite(vOrig, edge);
                int vkeyAdj = vAdj.getKey();
                if (!visited[vkeyAdj] && time[vkeyAdj] > time[vkeyOrig] + Physics.calculateTravelTimeInASegment(aircraft, (Segment) edge.getElement())) {
                    time[vkeyAdj] = time[vkeyOrig] + Physics.calculateTravelTimeInASegment(aircraft, (Segment) edge.getElement());
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
    
    public Map<Double, LinkedList<Node>> getFastestPath(Graph<Node, Segment> g, Node voInf, Node vdInf, Deque<Node> shortPath, Aircraft aircraft){
        LinkedList<Node> path = new LinkedList<>();
        Map<Double, LinkedList<Node>> map = new LinkedHashMap<>();
        
        double time = fastestPathLength(g, voInf, vdInf, shortPath, aircraft);
        for (Node a : shortPath) {
            path.add(a);
        }
        map.put(time, path);
        
        System.out.println(map);
        return map;
    }

}
