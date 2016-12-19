/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

import java.util.Objects;
import lapr.project.model.register.RegimeRegister;

/**
 *
 * @author João
 */
public class Aircraft{
    
    private AircraftModel model;
    private RegimeRegister regimeRegister;
    private String id;
    private String description;
    private String maker;
    private int numberFirstClass;
    private int numberNormalClass;
    private int numberElementsCrew;

    public Aircraft(AircraftModel model, RegimeRegister regimeRegister, String id, String description, String maker, int numberFirstClass, int numberNormalClass, int numberElementsCrew) {
        this.model = model;
        this.regimeRegister = regimeRegister;
        
        this.id = id;
        this.description = description;
        this.maker = maker;
        this.numberFirstClass = numberFirstClass;
        this.numberNormalClass = numberNormalClass;
        this.numberElementsCrew = numberElementsCrew;
    }

    public AircraftModel getModel() {
        return model;
    }

    public RegimeRegister getRegimeRegister() {
        return regimeRegister;
    }

    public String getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public String getMaker() {
        return maker;
    }

    public int getNumberFirstClass() {
        return numberFirstClass;
    }

    public int getNumberNormalClass() {
        return numberNormalClass;
    }

    public int getNumberElementsCrew() {
        return numberElementsCrew;
    }

    public boolean equals(Object otherObj) {
            if (otherObj == null || this.getClass() != otherObj.getClass()) {
                return false;
            }
            
            Aircraft otherAircraft = (Aircraft) otherObj;
            return Objects.equals(this.id, otherAircraft.getId());
        }

    @Override
    public String toString() {
        return "Aircraft{" + "id=" + id + ", description=" + description + ", maker=" + maker + ",\n model=" + model + ",\n regimeRegister=" + regimeRegister + '}';
    }

}
