/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.controller;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import lapr.project.model.FlightPlan;
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
    
    public Map<Double, LinkedList<Node>> getPathByAlgorithm(FlightPlan fp, int algorithm){
        switch (algorithm) {
        //TODO metodo de pesquisa
            case 1:
                break;
        //TODO metodo de pesquisa
            case 2:
                break;     
        //TODO metodo de pesquisa
            case 3:
                break;
        }
        return null;
    }
    
    public double getEnergyByPath(LinkedList<Node> path){
        //TODO metodo calcular energia
        return 0.0;
    }
    
    public boolean checkMax(FlightPlan fp, double nNormal, double nFirst, double nCrew){
        
        return !(fp.getnNormalClass() < nNormal ||
                fp.getnFirstClass() < nFirst ||
                fp.getnCrew() < nCrew);
    }
}
