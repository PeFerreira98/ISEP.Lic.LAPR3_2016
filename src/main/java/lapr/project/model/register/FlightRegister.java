/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model.register;

import java.util.HashMap;
import java.util.LinkedHashMap;
import lapr.project.model.Airport;
import lapr.project.model.Flight;

/**
 *
 * @author João
 */
public class FlightRegister {

    private final HashMap<String, Flight> mapFlights;

    public FlightRegister() {
        mapFlights = new LinkedHashMap<>();
    }

    public Flight addFlight(Flight newFlight) {
        if (validateFlight(newFlight)) {
            this.mapFlights.put(newFlight.getId(), newFlight);
            return this.mapFlights.get(newFlight.getId());
        }
        return null;
    }
    
    public Flight getFlightByID(String id) {
        for (Flight flight : this.mapFlights.values()) {
            if (flight.getId().equalsIgnoreCase(id)) {
                return flight;

            }
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
        if (flight.getPathTaken().isEmpty()) {
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

    public HashMap<String, Flight> getFlightsList() {
        return this.mapFlights;
    }
}
