/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.controller;

import java.util.ArrayList;
import java.util.List;
import lapr.project.database.DatabaseModel;
import lapr.project.model.Aircraft;
import lapr.project.model.AircraftModel;
import lapr.project.model.Airport;
import lapr.project.model.Flight;
import lapr.project.model.FlightPlan;
import lapr.project.model.Project;
import lapr.project.model.network.Node;
import lapr.project.model.network.Segment;

/**
 *
 * @author Marcos
 */
public class OpenProjectController {

    Project p;

    public OpenProjectController(Project p) {
        this.p = p;
    }

    public List<String> getAirportsNames() {
        List<String> lst = new ArrayList<>();
        for (Airport airport : this.p.getAirportRegister().getAirportRegister().values()) {
            lst.add(airport.getIATAcode());
        }
        return lst;
    }

    public List<String> getAircraftModelNames() {
        List<String> lst = new ArrayList<>();
        for (AircraftModel aircraftModel : this.p.getAircraftModelRegister().getAircraftModelMap().values()) {
            lst.add(aircraftModel.getId());
        }
        return lst;
    }

    public List<String> getFlightPlansNames() {
        List<String> lst = new ArrayList<>();
        for (FlightPlan flightplan : this.p.getFlightPlanRegister().getFlightPlansList().values()) {
            lst.add(String.valueOf(flightplan.getName()));
        }
        return lst;
    }

    public List<String> getNodesNames() {
        List<String> lst = new ArrayList<>();
        for (Node node : this.p.getAirNetwork().getMapNodes().values()) {
            lst.add(node.getName());
        }
        return lst;
    }

    public List<String> getSegmentNames() {
        List<String> lst = new ArrayList<>();
        for (Segment segment : this.p.getAirNetwork().getMapSegment().values()) {
            lst.add(segment.getId());
        }
        return lst;
    }
    
    
    
    public List<Airport> getAirportsDB(){
        DatabaseModel db = new DatabaseModel(this.p);
        List<Airport> lst = db.getListAirports(this.p);
        
        for(Airport a : lst){
            this.p.getAirportRegister().addAirport(a);
        }
        return lst;
    }
    
    public List<String> getAircraftModelDB(){
        List<String> lst = new ArrayList<>();
        DatabaseModel db = new DatabaseModel(this.p);
        
        return lst;
    }
    
    public List<FlightPlan> getFlightPlansDB() {
        List<FlightPlan> lst = new ArrayList<>();
        
        DatabaseModel db = new DatabaseModel(this.p);
        //lst = db.getListFlightPlans(this.p);
        return lst;
    }
    
    public List<Node> getNodesDB(){
        List<Node> lst = new ArrayList<>();
        
        DatabaseModel db = new DatabaseModel(this.p);
        lst = db.getListNodes(this.p);
        return lst;
    }
    
    public List<Segment> getSegmentDB(){
        List<Segment> lst = new ArrayList<>();
        
        DatabaseModel db = new DatabaseModel(this.p);
        lst = db.getSegments();
        return lst;
    }
    
    public void LoadInformation(){
        getAircraftModelDB();
        getAirportsDB();
        getFlightPlansDB();
        getNodesDB();
        getSegmentDB();
    }
}
