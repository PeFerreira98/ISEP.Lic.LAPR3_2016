/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model.register;

import java.util.HashMap;
import lapr.project.model.network.Node;

/**
 *
 * @author João
 */
public class NodesRegister {
    
    
    private HashMap<String, Node> mapNodes;

    public NodesRegister() {
        mapNodes = new HashMap<>();
    }
    
    public NodesRegister(NodesRegister nr){
        this();
        for (Node nod : nr.getNodesList().values()) {
            this.getNodesList().put(nod.getName(), new Node(nod));
        }
    }

    public Node newNode() {
        return new Node();
    }

    public boolean validateNode(Node node) {
    
        return true;
    }
    
    public HashMap<String, Node> getNodesList(){
        return this.mapNodes;
    }
}
