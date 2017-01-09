/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

import java.util.ArrayList;
import lapr.project.model.network.Segment;

/**
 *
 * @author Tiago
 */
public class Flight {

    private String id;
    private FlightPlan flightPlan;
    private Aircraft aircraft;
    private ArrayList<Segment> pathTaken;
    private double travelingTime;
    private double energyConsumption;

    public Flight(String id, FlightPlan flightPlan, Aircraft aircraft, ArrayList<Segment> pathTaken, double travelingTime, double energyConsumption) {
        this.id = id;
        this.flightPlan = flightPlan;
        this.aircraft = aircraft;
        this.pathTaken = pathTaken;
        this.travelingTime = travelingTime;
        this.energyConsumption = energyConsumption;
    }

    public Flight(FlightPlan flightPlan, Aircraft aircraft, ArrayList<Segment> pathTaken, double travelingTime, double energyConsumption) {
        this.id = flightPlan.getId() + aircraft.getId();
        this.flightPlan = flightPlan;
        this.aircraft = aircraft;
        this.pathTaken = pathTaken;
        this.travelingTime = travelingTime;
        this.energyConsumption = energyConsumption;
    }

    public String getId() {
        return id;
    }

    public FlightPlan getFlightPlan() {
        return flightPlan;
    }

    public Aircraft getAircraft() {
        return aircraft;
    }

    public ArrayList<Segment> getPathTaken() {
        return pathTaken;
    }

    public double getTravelingTime() {
        return travelingTime;
    }

    public double getEnergyConsumption() {
        return energyConsumption;
    }

    @Override
    public String toString() {
        return "Flight{" + "id=" + id + ", flightPlan=" + flightPlan + ", aircraft=" + aircraft + ", pathTaken=" + pathTaken + ", travelingTime=" + travelingTime + ", energyConsumption=" + energyConsumption + '}';
    }
}
