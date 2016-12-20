/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model.network;

import java.util.LinkedHashMap;
import java.util.Map;
import lapr.project.model.graph.Graph;
import lapr.project.model.graph.Vertex;

/**
 *
 * @author João
 */
public class AirNetwork {

    private Graph<Node, Segment> airNetwork;
    private Map<String,Node> map_Nodes;
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
    public Map<String,Node> getMapNodes() {
        return map_Nodes;
    }
    
    public Map<String,Segment> getMapSegment(){
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
    public boolean createGraph(){
        map_Nodes.values().stream().forEach((node) -> {
            airNetwork.insertVertex(node);
        });
        
        map_Segment.values().stream().forEach((segment) -> {
            airNetwork.insertEdge(segment.getBeginningNode(), segment.getEndNode(), segment, 0);
        });
        
        for (Vertex v : airNetwork.vertices()) {
            if(airNetwork.outDegree(v) == 0 || airNetwork.inDegree(v) == 0){
                return false;
            }
        }
        return true;
    }

}
