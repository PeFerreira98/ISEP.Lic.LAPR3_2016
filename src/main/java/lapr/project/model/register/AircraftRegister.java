/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model.register;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import lapr.project.model.Aircraft;

/**
 *
 * @author zero_
 */
public class AircraftRegister {

    private Map<String, Aircraft> aircraftMap;

    public AircraftRegister() {
        this.aircraftMap = new LinkedHashMap<>();
    }

    public Aircraft addAircraft(Aircraft newAircraft) {
        if (validateAircraft(newAircraft)) {
            this.aircraftMap.put(newAircraft.getId(), newAircraft);
            return this.aircraftMap.get(newAircraft.getId());
        }

        return null;
    }

    private boolean validateAircraft(Aircraft newAircraft) {
        for (Aircraft aircraft : this.aircraftMap.values()) {
            if (newAircraft.equals(aircraft)) {
                return false;
            }
        }

        return true;
    }

    public Map<String, Aircraft> getAircraftMap() {
        return aircraftMap;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }

        final AircraftRegister other = (AircraftRegister) obj;
        if (!Objects.equals(this.aircraftMap.size(), other.aircraftMap.size())) {
            return false;
        }

        return Objects.equals(this.aircraftMap, other.aircraftMap);
    }

    @Override
    public String toString() {
        return "AircraftList{" + "aircraftList=" + aircraftMap + '}';
    }

}
