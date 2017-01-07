/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model.register;

import java.util.HashMap;
import java.util.LinkedHashMap;
import lapr.project.model.Flight;
import lapr.project.model.FlightPlan;
import lapr.project.model.network.Segment;

/**
 *
 * @author João
 */
public class FlightPlanRegister {

    private HashMap<Integer, FlightPlan> mapFlightPlans;

    public FlightPlanRegister() {
        this.mapFlightPlans = new LinkedHashMap<>();
    }

    public FlightPlan addFlightPlan(FlightPlan newFlightPlan) {
        return mapFlightPlans.put(newFlightPlan.getId(), newFlightPlan);

    }

    public HashMap<Integer, FlightPlan> getFlightPlansList() {
        return this.mapFlightPlans;
    }
}
