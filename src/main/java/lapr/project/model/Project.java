/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

import java.util.HashMap;
import java.util.LinkedHashMap;
import lapr.project.model.network.AirNetwork;

/**
 *
 * @author João
 */
public class Project {

    private int id;
    private String name;
    private AirNetwork AirNetwork;
    private LinkedHashMap<String, Airport> airportHashMap;
    private LinkedHashMap<Integer, Aircraft> aircraftHashMap;
    private LinkedHashMap<Integer, AircraftModel> aircraftModelHashMap;

    public Project(int id, String nome) {
        this.AirNetwork = new AirNetwork();
        this.airportHashMap = new LinkedHashMap<>();
        this.aircraftModelHashMap = new LinkedHashMap<>();
    }

    public Project(int id, String name, AirNetwork AirNetwork, LinkedHashMap<String, Airport> airportHashMap, LinkedHashMap<Integer, Aircraft> aircraftHashMap, LinkedHashMap<Integer, AircraftModel> aircraftModelHashMap) {
        this.id = id;
        this.name = name;
        this.AirNetwork = AirNetwork;
        this.airportHashMap = airportHashMap;
        this.aircraftHashMap = aircraftHashMap;
        this.aircraftModelHashMap = aircraftModelHashMap;
    }
    
    public Airport addAirport(Airport newAirport) {

        for (Airport airport : this.airportHashMap.values()) {
            if (newAirport.equals(airport)) {
                return null;
            }
        }

        return this.airportHashMap.put(newAirport.getIATAcode(), newAirport);
    }

    public Aircraft addAircraft(Aircraft newAircraft) {

        for (Aircraft aircraft : this.aircraftHashMap.values()) {
            if (newAircraft.equals(aircraft)) {
                return null;
            }
        }

        return this.aircraftHashMap.put(newAircraft.getId(), newAircraft);
    }

    public AircraftModel addAircraftModel(AircraftModel newAircraftModel) {

        for (AircraftModel aircraftModel : this.aircraftModelHashMap.values()) {
            if (newAircraftModel.equals(aircraftModel)) {
                return null;
            }
        }

        return this.aircraftModelHashMap.put(newAircraftModel.getId(), newAircraftModel);
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public AirNetwork getAirNetwork() {
        return AirNetwork;
    }

    public LinkedHashMap<String, Airport> getAirportHashMap() {
        return airportHashMap;
    }

    public LinkedHashMap<Integer, Aircraft> getAircraftHashMap() {
        return aircraftHashMap;
    }

    public LinkedHashMap<Integer, AircraftModel> getAircraftModelHashMap() {
        return aircraftModelHashMap;
    }
    
}
