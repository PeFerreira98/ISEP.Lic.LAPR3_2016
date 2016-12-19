/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model.register;

import java.util.HashMap;
import lapr.project.model.network.Segment;

/**
 *
 * @author João
 */
public class SegmentsRegister {
    
    private HashMap<String, Segment> SegmentsMap;
    
    public SegmentsRegister(){
        
    }
    
    public Segment addSegment(){
        return new Segment();
    }
    
    public HashMap<String, Segment> getSegmentsList(){
        return this.SegmentsMap;
    }
}
