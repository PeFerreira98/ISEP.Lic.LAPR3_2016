/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model.register;

import java.util.HashMap;
import java.util.LinkedHashMap;
import lapr.project.model.AircraftModel;
import lapr.project.model.Flight;
import lapr.project.model.FlightPlan;
import lapr.project.model.network.Segment;

/**
 *
 * @author João
 */
public class FlightPlanRegister {

    private HashMap<String, FlightPlan> mapFlightPlans;

    public FlightPlanRegister() {
        this.mapFlightPlans = new LinkedHashMap<>();
    }
    
    public FlightPlanRegister(FlightPlanRegister fpr){
        this();
        for (FlightPlan fp : fpr.getFlightPlansList().values()) {
            this.addFlightPlan(new FlightPlan(fp));
        }
    }
    
    public FlightPlan addFlightPlan(FlightPlan newFlightPlan) {
        if (validateFlightPlan(newFlightPlan)) {
            this.mapFlightPlans.put(newFlightPlan.getName(), newFlightPlan);
            return this.mapFlightPlans.get(newFlightPlan.getName());
        }
        return null;
    }

    private boolean validateFlightPlan(FlightPlan newFlightPlan) {
        for (FlightPlan flightPlan : this.mapFlightPlans.values()) {
            if (newFlightPlan.equals(flightPlan)) {
                return false;
            }
        }
        return true;
    }

    public HashMap<String, FlightPlan> getFlightPlansList() {
        return this.mapFlightPlans;
    }
}
