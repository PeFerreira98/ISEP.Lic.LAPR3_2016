/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model.network;

import lapr.project.model.graph.Graph;

/**
 *
 * @author João
 */
public class AirNetwork {

    private Graph<Node, Segment> airNetwork;

    public AirNetwork() {
        this.airNetwork = new Graph<>(false);
    }
    
}
