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
    
    private AirNetwork AirNetwork;
    private LinkedHashMap<Integer, Airport> mapAirports;
    private LinkedHashMap<Integer, AircraftModel> mapAircrafts;
    
    public Project(){
    this.AirNetwork= new AirNetwork();
    this.mapAirports = new LinkedHashMap<>();
    this.mapAircrafts = new LinkedHashMap<>();
    }

    public AirNetwork getAirNetwork() {
        return AirNetwork;
    }

    public void setAirNetwork(AirNetwork AirNetwork) {
        this.AirNetwork = AirNetwork;
    }

    public LinkedHashMap<Integer, Airport> getMapAirports() {
        return mapAirports;
    }

    public void setMapAirports(LinkedHashMap<Integer, Airport> mapAirports) {
        this.mapAirports = mapAirports;
    }

    public LinkedHashMap<Integer, AircraftModel> getMapAircrafts() {
        return mapAircrafts;
    }

    public void setMapAircrafts(LinkedHashMap<Integer, AircraftModel> mapAircrafts) {
        this.mapAircrafts = mapAircrafts;
    }
    
    
    
}
