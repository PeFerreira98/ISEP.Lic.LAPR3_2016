/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

import java.util.LinkedHashMap;
import lapr.project.model.network.AirNetwork;
import lapr.project.model.register.AircraftRegister;

/**
 *
 * @author João
 */
public class Project {

    private int id;
    private String name;
    private String description;
    private AirNetwork AirNetwork;
    private AircraftRegister aircraftRegister;

    private LinkedHashMap<String, Airport> airportHashMap; //TODO: change to airportregister;
    private LinkedHashMap<Integer, Flight> flightsList; //TODO: change to flightregister

    public Project(int id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.AirNetwork = new AirNetwork();
        this.aircraftRegister = new AircraftRegister();

        this.airportHashMap = new LinkedHashMap<>();
        this.flightsList = new LinkedHashMap<>();
    }

    public Project(int id, String name, String description, AirNetwork AirNetwork, AircraftRegister aircraftRegister, LinkedHashMap<String, Airport> airportHashMap, LinkedHashMap<Integer, Flight> flightsList) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.AirNetwork = AirNetwork;
        this.aircraftRegister = aircraftRegister;
        this.airportHashMap = airportHashMap;
        this.flightsList = flightsList;
    }

    public Aircraft addAircraft(Aircraft newAircraft) {
        return this.aircraftRegister.addAircraft(newAircraft);
    }

    public Airport addAirport(Airport newAirport) {

        for (Airport airport : this.airportHashMap.values()) {
            if (newAirport.equals(airport)) {
                return null;
            }
        }

        return this.airportHashMap.put(newAirport.getIATAcode(), newAirport);
    }

    public Flight addFlight(Flight newFlight) {

        for (Flight flight : this.flightsList.values()) {
            if (newFlight.equals(flight)) {
                return null;
            }
        }
        return this.flightsList.put(newFlight.getId(), newFlight);
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public AirNetwork getAirNetwork() {
        return AirNetwork;
    }

    public AircraftRegister getAircraftRegister() {
        return aircraftRegister;
    }

    public LinkedHashMap<String, Airport> getAirportHashMap() {
        return airportHashMap;
    }

    public LinkedHashMap<Integer, Flight> getFlightsList() {
        return flightsList;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setAirNetwork(AirNetwork AirNetwork) {
        this.AirNetwork = AirNetwork;
    }

    public void setAircraftRegister(AircraftRegister aircraftRegister) {
        this.aircraftRegister = aircraftRegister;
    }

    public void setAirportHashMap(LinkedHashMap<String, Airport> airportHashMap) {
        this.airportHashMap = airportHashMap;
    }

    public void setFlightsList(LinkedHashMap<Integer, Flight> flightsList) {
        this.flightsList = flightsList;
    }

    public Project duplicate() {
        Project p = new Project(getId(), getName(), getDescription());
        p.setId(getId());
        p.setName(getName());
        p.setDescription(getDescription());
        p.setAirNetwork(getAirNetwork());
        p.setAircraftRegister(getAircraftRegister());
        p.setAirportHashMap(getAirportHashMap());
        p.setFlightsList(getFlightsList());
        return p;

    }

    public boolean valida() {
        return name != null && description != null;
    }

    @Override
    public String toString() {
        return this.getName();
    }

}
