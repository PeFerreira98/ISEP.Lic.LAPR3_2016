/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model.register;

import java.util.HashMap;
import java.util.LinkedHashMap;
import lapr.project.model.Aircraft;

/**
 *
 * @author Tiago
 */
public class AircraftRegister {

    private HashMap<String, Aircraft> aircraftRegister;

    public AircraftRegister() {
        this.aircraftRegister = new LinkedHashMap<>();
    }

    public boolean validateAircraft(Aircraft newAircraft) {
        for (Aircraft aircraft : this.aircraftRegister.values()) {
            if (newAircraft.equals(aircraft)) {
                return false;
            }

        }
        return true;
    }

    public Aircraft addAircraft(Aircraft newAircraft) {
        if (validateAircraft(newAircraft)) {
            this.aircraftRegister.put(newAircraft.getId(), newAircraft);
            return this.aircraftRegister.get(newAircraft.getId());
        }
        return null;
    }
    
    public Aircraft getAircraftByID(String ID){
        for (Aircraft aircraft : this.aircraftRegister.values()) {
            if(aircraft.getId().equals(ID))
                return this.aircraftRegister.get(aircraft.getId());
            
        }
        return null;
    }
}
