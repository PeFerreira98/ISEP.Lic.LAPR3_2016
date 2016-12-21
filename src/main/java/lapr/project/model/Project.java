/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

import java.util.LinkedHashMap;
import lapr.project.model.network.AirNetwork;
import lapr.project.model.register.AircraftModelRegister;

/**
 *
 * @author João
 */
public class Project {

    private String name;
    private String description;
    private AirNetwork AirNetwork;
    private AircraftModelRegister aircraftModelRegister;

    private LinkedHashMap<String, Aircraft> aircraftHashMap; //TODO: change to aircraftRegister
    private LinkedHashMap<String, Airport> airportHashMap; //TODO: change to airportregister
    private LinkedHashMap<Integer, Flight> flightsList; //TODO: change to flightregister

    public Project(String name, String description) {
        this.name = name;
        this.description = description;
        
        this.AirNetwork = new AirNetwork();
        this.aircraftModelRegister = new AircraftModelRegister();
        
        this.aircraftHashMap = new LinkedHashMap<>();
        this.airportHashMap = new LinkedHashMap<>();
        this.flightsList = new LinkedHashMap<>();
    }    

    public Project(String name, String description, AirNetwork AirNetwork, AircraftModelRegister aircraftModelRegister, LinkedHashMap<String, Airport> airportHashMap, LinkedHashMap<Integer, Flight> flightsList) {
        this.name = name;
        this.description = description;
        this.AirNetwork = AirNetwork;
        this.aircraftModelRegister = aircraftModelRegister;
        this.aircraftHashMap = aircraftHashMap;
        this.airportHashMap = airportHashMap;
        this.flightsList = flightsList;
    }

    public AircraftModel addAircraftModel(AircraftModel newAircraftModel) {
        return this.aircraftModelRegister.addAircraftModel(newAircraftModel);
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

    public Project duplicate() {
        Project p = new Project(getName() + " - Copia", getDescription());
        return p;
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

    public AircraftModelRegister getAircraftModelRegister() {
        return aircraftModelRegister;
    }

    public LinkedHashMap<String, Aircraft> getAircraftHashMap() {
        return aircraftHashMap;
    }

    public LinkedHashMap<String, Airport> getAirportHashMap() {
        return airportHashMap;
    }

    public LinkedHashMap<Integer, Flight> getFlightsList() {
        return flightsList;
    }

}
