/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.controller;

import lapr.project.model.register.FlightRegister;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import lapr.project.model.Flight;
import lapr.project.model.Flight.FlightType;
import lapr.project.model.Project;
import lapr.project.model.network.AirNetwork;
import lapr.project.model.network.Segment;

/**
 *
 * @author João
 */
public class AddFlightController {
    
    private AirNetwork AirNetwork;
    private Flight flight;
    private Project proj;
    private LinkedHashMap<Integer, Flight> flightsList;
    private FlightRegister regFlights;
    
    
    
    public AddFlightController(AirNetwork airnetwork, Flight flight, Project proj) {
        this.AirNetwork = airnetwork;
        this.flight = flight;
        this.proj = proj;
    }
    
    public LinkedHashMap<Integer, Flight> getFlightsList(){
        return this.flightsList=proj.getFlightsList();
    }
    
    public void setData(int id, FlightType type, Date departure_day, double minimum_stop,
            Date scheduled_arrival, ArrayList<Segment> flight_plan){
        
        Flight flight = new Flight(id, type, departure_day, minimum_stop, departure_day, flight_plan);
        
        if(regFlights.validateFlight(flight)==true){
        this.regFlights.addFlight(flight);
        
        }
    }
}
