/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.controller;

import java.util.HashMap;
import lapr.project.database.DatabaseModel;
import lapr.project.model.Location;
import lapr.project.model.Project;
import lapr.project.model.network.AirNetwork;
import lapr.project.model.network.Node;
import lapr.project.model.network.Segment;
import lapr.project.model.register.NodesRegister;

/**
 *
 * @author João
 */
public class AddSegmentController {
    private AirNetwork AirNetwork;
    private Project proj;
    private Segment segment;
    private Node node;
    private HashMap<String, Segment> fileSegments;
    private NodesRegister NodesReg;
    
    
    public AddSegmentController(AirNetwork airnetwork, Segment segment, Project proj, HashMap<String, Segment> fileSegments){
        this.AirNetwork= airnetwork;
        this.segment= segment;
        this.proj = proj;
        this.fileSegments= fileSegments;
    }    
    
//    public boolean insertSegment(Location locNodeOrig, Location locNodeDest){
//       
//        if(NodesReg.getNodesList().containsKey(locNodeOrig.)) É preciso adicionar Location ID
//        
//        return false;
//    }
    
    public AddSegmentController(Project p){
        this.proj = p;
    }
    
    public void insertSegment(Segment s){
        if(s != null){
            this.proj.getAirNetwork().addSegment(s);
            DatabaseModel dbm = new DatabaseModel(this.proj);
            dbm.addSegment(segment);
        }
    }
    
}
