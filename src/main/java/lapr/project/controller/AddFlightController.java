/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.controller;

import lapr.project.model.register.FlightRegister;
import java.util.HashMap;
import lapr.project.model.Flight;
import lapr.project.model.Project;
import lapr.project.model.network.AirNetwork;

/**
 *
 * @author João
 */
public class AddFlightController {

    private AirNetwork AirNetwork;
    private Flight flight;
    private Project proj;
    private HashMap<Integer, Flight> flightsList;
    private FlightRegister regFlights;

    public AddFlightController(AirNetwork airnetwork, Flight flight, Project proj) {
        this.AirNetwork = airnetwork;
        this.flight = flight;
        this.proj = proj;
    }

//    public void setData(int id, FlightType type, Date departure_day, double minimum_stop,
//            Date scheduled_arrival, ArrayList<Segment> flight_plan, Aircraft aircraft) {
//
//        Flight flight = new Flight(id, type, departure_day, minimum_stop, departure_day, flight_plan, aircraft);
//
//        if (regFlights.validateFlight(flight) == true) {
//            this.regFlights.addFlight(flight);
//
//        }
//    }
}
