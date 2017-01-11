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

    public boolean addAircraft(Aircraft newAircraft) {
        aircraftCheckDuplicate(newAircraft);
        if (validateAircraft(newAircraft)) {
            this.aircraftRegister.put(newAircraft.getId(), newAircraft);
            return true;

        }
        return false;
    }

    public Aircraft getAircraftByID(String ID) {
        for (Aircraft aircraft : this.aircraftRegister.values()) {
            if (aircraft.getId().equals(ID)) {
                return this.aircraftRegister.get(aircraft.getId());
            }
        }
        return null;
    }

    public boolean aircraftCheckDuplicate(Aircraft aircraft) {
        for (Aircraft aircraft1 : this.aircraftRegister.values()) {
            if (aircraft.getDescription().equalsIgnoreCase(aircraft1.getDescription())) {
                System.out.println(aircraft.getDescription() + " = " + aircraft1.getDescription());
                aircraftDuplicationName(aircraft, 1);
                return true;
            }
            System.out.println(aircraft);
        }
        return false;
    }

    private void aircraftDuplicationName(Aircraft aircraft, int p) {
        aircraft.setDescription(String.format("%s(%d)", aircraft.getDescription(), p));
        int i = 0;
        p++;
        for (Aircraft aircraft1 : this.aircraftRegister.values()) {
            if (i >= p) {
                if (aircraft.getDescription().equalsIgnoreCase(aircraft1.getDescription())) {
                    aircraftDuplicationName(aircraft, p);
                }
            }

        }
    }

    public HashMap<String, Aircraft> getAircraftRegister() {
        return aircraftRegister;
    }

}
