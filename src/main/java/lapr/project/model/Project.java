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
    private LinkedHashMap<Integer, Airport> airportHashMap;
    private LinkedHashMap<Integer, Aircraft> aircraftHashMap;
    private LinkedHashMap<Integer, AircraftModel> aircraftModelHashMap;

    public Project(int id, String nome) {
        this.AirNetwork = new AirNetwork();
        this.airportHashMap = new LinkedHashMap<>();
        this.aircraftModelHashMap = new LinkedHashMap<>();
    }

    public Project(int id, String name, AirNetwork AirNetwork, LinkedHashMap<Integer, Airport> airportHashMap, LinkedHashMap<Integer, Aircraft> aircraftHashMap, LinkedHashMap<Integer, AircraftModel> aircraftModelHashMap) {
        this.id = id;
        this.name = name;
        this.AirNetwork = AirNetwork;
        this.airportHashMap = airportHashMap;
        this.aircraftHashMap = aircraftHashMap;
        this.aircraftModelHashMap = aircraftModelHashMap;
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

}
