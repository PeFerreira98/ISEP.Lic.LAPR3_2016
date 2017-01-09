/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.controller;

import java.util.LinkedList;
import java.util.Map;
import lapr.project.model.Aircraft;
import lapr.project.model.FlightPlan;
import lapr.project.model.Project;
import lapr.project.model.network.Node;

/**
 *
 * @author zero_
 */
public class SimulationResultsController {

    private Project project;
    private FlightPlan flightPlan;
    private Aircraft aircraft;
    private String flightPath;

    public SimulationResultsController(Project project, FlightPlan flightPlan, Aircraft aircraft, String flightPath) {
        this.project = project;
        this.flightPlan = flightPlan;
        this.aircraft = aircraft;
        this.flightPath = flightPath;
    }

    //TODO: Change this
    public Map<Double, LinkedList<Node>> getPathByAlgorithm(FlightPlan fp, int algorithm) {
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

    public double getEnergyByPath(LinkedList<Node> path) {
        //TODO metodo calcular energia
        return 0.0;
    }
}
