/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.controller;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import lapr.project.model.Project;
import lapr.project.model.network.Node;
import lapr.project.model.network.Segment;

/**
 *
 * @author zero_
 */
public class SimulateFlightController {
    
    private Project project; 
        
    public SimulateFlightController(Project p){
        this.project = p;
    }
    
    public Map<Double, LinkedList<Node>> getPathByAlgorithm(String algorithm){
        if(algorithm.equalsIgnoreCase("fastest")){
            //TODO metodo de pesquisa
        }else if(algorithm.equalsIgnoreCase("shortest")){
            //TODO metodo de pesquisa
        }else if(algorithm.equalsIgnoreCase("lessEnergy")){
            //TODO metodo de pesquisa
        }     
        return null;
    }
    
    public double getEnergyByPath(LinkedList<Node> path){
        //TODO metodo calcular energia
        return 0.0;
    }
}
