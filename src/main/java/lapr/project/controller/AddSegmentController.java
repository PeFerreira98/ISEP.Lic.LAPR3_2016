/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.controller;

import lapr.project.model.Location;
import lapr.project.model.Project;
import lapr.project.model.network.AirNetwork;
import lapr.project.model.network.Node;
import lapr.project.model.network.Segment;

/**
 *
 * @author João
 */
public class AddSegmentController {
    private AirNetwork AirNetwork;
    private Project proj;
    private Segment segment;
    private Node node;
    
    
    public AddSegmentController(AirNetwork airnetwork, Segment segment, Project proj){
        this.AirNetwork= airnetwork;
        this.segment= segment;
        this.proj = proj;
    }
    
    public boolean insertSegment(Location locNodeOrig, Location locNodeDest){
       
        
        return false;
    }
    
    
}
