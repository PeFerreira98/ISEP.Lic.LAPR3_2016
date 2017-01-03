/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model.register;

import java.util.HashMap;
import lapr.project.model.Flight;
import lapr.project.model.network.Segment;

/**
 *
 * @author João
 */
public class FlightRegister {

    private HashMap<Integer, Flight> mapFlights;
    private SegmentsRegister regSegment;

    public FlightRegister() {
        mapFlights = new HashMap<>();
    }

    public Flight newFlight() {
        return new Flight();
    }

    public Flight addFlight(Flight newFlight) {
        return mapFlights.put(newFlight.getId(), newFlight);

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

        for (Segment seg : flight.getFlight_plan()) {
            if (!regSegment.getSegmentsList().containsKey(seg.getId())) {
                return false;
            }

        }
        return true;
    }

    public HashMap<Integer, Flight> getFlightsList() {
        return this.mapFlights;
    }
}
