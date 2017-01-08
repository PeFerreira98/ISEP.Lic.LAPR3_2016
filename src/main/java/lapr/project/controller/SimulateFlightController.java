/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.controller;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import lapr.project.model.AircraftModel;
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
    private ArrayList<FlightPlan> flightPlansList;
    private ArrayList<AircraftModel> aircraftModelsList;
        
    public SimulateFlightController(Project p){
        this.project = p;
        this.flightPlansList = new ArrayList<>();
        this.aircraftModelsList = new ArrayList<>();
    }
    
    public void initializeFlightPlansList(){
        for (FlightPlan fp : this.project.getFlightPlanRegister().getFlightPlansList().values()) {
            this.flightPlansList.add(fp);
        }
    }
    
    public int getFlightPlansListSize(){
        return this.flightPlansList.size();
    }
    
    public String getFlightPlanNameByIndex(int index){
        return this.flightPlansList.get(index).getName();
    }
    
    public FlightPlan getFlightPlanByIndex(int index){
        return this.flightPlansList.get(index);
    }
    
    public void initializeaircraftModelsList(AircraftModel.Type aircraftType){
        for (AircraftModel aircraftModel : this.project.getAircraftModelRegister().getAircraftsByType(aircraftType).values()) {
            this.aircraftModelsList.add(aircraftModel);
        }
    }
    
    public int getaircraftModelsListSize(){
        return this.aircraftModelsList.size();
    }
    
    public String getaircraftModelIdByIndex(int index){
        return this.aircraftModelsList.get(index).getId();
    }
    
    public AircraftModel getaircraftModelsByIndex(int index){
        return this.aircraftModelsList.get(index);
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
