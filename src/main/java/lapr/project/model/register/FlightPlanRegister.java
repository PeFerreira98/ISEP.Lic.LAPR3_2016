/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model.register;

import java.util.HashMap;
import lapr.project.model.Flight;
import lapr.project.model.FlightPlan;
import lapr.project.model.network.Segment;

/**
 *
 * @author João
 */
public class FlightPlanRegister {

    private HashMap<Integer, FlightPlan> mapFlightPlans;
    private SegmentsRegister regSegment;

    public FlightPlanRegister() {
        this.mapFlightPlans = new HashMap<>();
    }

    public FlightPlan newFlightPlan() {
        return new FlightPlan();
    }

    public FlightPlan addFlightPlan(FlightPlan newFlight) {
        return mapFlightPlans.put(newFlight.getId(), newFlight);

    }

    public HashMap<Integer, FlightPlan> getFlightPlansList() {
        return this.mapFlightPlans;
    }
}
