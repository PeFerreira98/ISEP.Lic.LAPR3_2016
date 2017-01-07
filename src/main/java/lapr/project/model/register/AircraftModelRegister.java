/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model.register;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import lapr.project.model.AircraftModel;

/**
 *
 * @author zero_
 */
public class AircraftModelRegister {

    private Map<String, AircraftModel> aircraftModelMap;

    public AircraftModelRegister() {
        this.aircraftModelMap = new LinkedHashMap<>();
    }

    public AircraftModel addAircraftModel(AircraftModel newAircraftModel) {
        if (validateAircraftModel(newAircraftModel)) {
            this.aircraftModelMap.put(newAircraftModel.getId(), newAircraftModel);
            return this.aircraftModelMap.get(newAircraftModel.getId());
        }

        return null;
    }

    private boolean validateAircraftModel(AircraftModel newAircraftModel) {
        for (AircraftModel aircraftModel : this.aircraftModelMap.values()) {
            if (newAircraftModel.equals(aircraftModel)) {
                return false;
            }
        }

        return true;
    }

    public AircraftModel getAircraftModel(String aircraftModelId) {
        for (AircraftModel aircraftModel : this.aircraftModelMap.values()) {
            if (aircraftModelId.equals(aircraftModel.getId())) {
                return aircraftModel;
            }
        }

        return null;
    }

    public Map<String, AircraftModel> getAircraftModelMap() {
        return aircraftModelMap;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }

        final AircraftModelRegister other = (AircraftModelRegister) obj;
        if (!Objects.equals(this.aircraftModelMap.size(), other.aircraftModelMap.size())) {
            return false;
        }

        return Objects.equals(this.aircraftModelMap, other.aircraftModelMap);
    }

    @Override
    public String toString() {
        return "AircraftModelMap{" + "aircraftModelMap=" + aircraftModelMap + '}';
    }

}
