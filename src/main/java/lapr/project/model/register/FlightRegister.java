/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model.register;

import java.util.HashMap;
import java.util.LinkedHashMap;
import lapr.project.model.Flight;
import lapr.project.model.network.Segment;

/**
 *
 * @author João
 */
public class FlightRegister {

    private HashMap<Integer, Flight> mapFlights;

    public FlightRegister() {
        mapFlights = new LinkedHashMap<>();
    }

    public Flight addFlight(Flight newFlight) {
        if (validateFlight(newFlight)) {
            return mapFlights.put(newFlight.getId(), newFlight);
        }
        return null;
    }

    public boolean confirmFlight(Flight newFlight) {
        if (addFlight(newFlight).equals(newFlight)) {
            return true;
        }
        return false;
    }

    public boolean validateFlight(Flight flight) {
        if (flight.getFlight_plan().isEmpty()) {
            return false;
        }
        if (flight.getScheduled_arrival().after(flight.getDeparture_day())) {
            return false;
        }
        if (mapFlights.containsKey(flight.getId())) {
            return false;
        }

        if (flight.getAircraft().getNumberFirstClass() < 0) {
            return false;
        }
        if (flight.getAircraft().getNumberNormalClass() < 0) {
            return false;
        }
        if (flight.getAircraft().getNumberElementsCrew() < 0) {
            return false;
        }
        return true;
    }

    public HashMap<Integer, Flight> getFlightsList() {
        return this.mapFlights;
    }
}
