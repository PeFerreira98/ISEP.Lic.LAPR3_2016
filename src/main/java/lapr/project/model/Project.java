/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

import lapr.project.model.network.AirNetwork;
import lapr.project.model.network.Node;
import lapr.project.model.register.*;

/**
 *
 * @author João
 */
public class Project {

    private String name;
    private String description;
    private AirNetwork airNetwork;

    private AircraftModelRegister aircraftModelRegister;
    private AirportRegister airportRegister;
    private AircraftRegister aircraftRegister;

    private FlightPlanRegister flightPlanRegister;
    private FlightRegister flightRegister;

    public Project() {
        this.airNetwork = new AirNetwork();

        this.aircraftModelRegister = new AircraftModelRegister();
        this.airportRegister = new AirportRegister();
        this.aircraftRegister = new AircraftRegister();

        this.flightPlanRegister = new FlightPlanRegister();
        this.flightRegister = new FlightRegister();
    }

    public Project(String name, String description) {
        this();
        this.name = name;
        this.description = description;
    }

    public Project(String name, String description, AirNetwork airNetwork, AircraftModelRegister aircraftModelRegister, AirportRegister airportRegister, AircraftRegister aircraftRegister, FlightPlanRegister flightPlanRegister) {
        this.name = name;
        this.description = description;

        this.airNetwork = airNetwork;
        this.aircraftModelRegister = aircraftModelRegister;
        this.airportRegister = airportRegister;
        this.aircraftRegister = aircraftRegister;

        this.flightPlanRegister = flightPlanRegister;
        this.flightRegister = new FlightRegister();
    }

    public boolean addAircraft(Aircraft newAircraft) {
        return this.aircraftRegister.addAircraft(newAircraft);
    }

    public FlightPlan addFlightPlan(FlightPlan newFlightPlan) {
        return this.flightPlanRegister.addFlightPlan(newFlightPlan);
    }

    public AircraftModel addAircraftModel(AircraftModel newAircraftModel) {
        return this.aircraftModelRegister.addAircraftModel(newAircraftModel);
    }

    public Airport addAirport(Airport newAirport) {
        return this.airportRegister.addAirport(newAirport);
    }

    public Flight addFlight(Flight newFlight) {
        return this.flightRegister.addFlight(newFlight);
    }

    public boolean isReachable(Node nOrigin, Node nEnd) {
        return this.airNetwork.isReachable(nOrigin, nEnd);
    }

    public boolean isReachable(Airport aOrigin, Airport aEnd) {
        Node nOrigin = getAirportNode(aOrigin);
        Node nEnd = getAirportNode(aEnd);

        if (nOrigin != null && nEnd != null) {
            return this.airNetwork.isReachable(nOrigin, nEnd);
        }
        return false;
    }

    public Node getAirportNode(Airport airport) {
        return this.airNetwork.getNodeByLocation(airport.getLocation().getLatitude(), airport.getLocation().getLongitude());
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public AirNetwork getAirNetwork() {
        return airNetwork;
    }

    public AircraftModelRegister getAircraftModelRegister() {
        return aircraftModelRegister;
    }

    public AirportRegister getAirportRegister() {
        return airportRegister;
    }

    public AircraftRegister getAircraftRegister() {
        return aircraftRegister;
    }

    public FlightPlanRegister getFlightPlanRegister() {
        return flightPlanRegister;
    }

    public FlightRegister getFlightRegister() {
        return flightRegister;
    }

}
