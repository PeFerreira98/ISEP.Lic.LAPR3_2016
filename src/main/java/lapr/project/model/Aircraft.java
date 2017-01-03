/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

import java.util.Objects;

/**
 *
 * @author João
 */
public class Aircraft{
    
    private String id;
    private String description;
    
    private int numberFirstClass;
    private int numberNormalClass;
    private int numberElementsCrew;
    
    private AircraftModel model;

    public Aircraft(AircraftModel model, String id, String description, int numberFirstClass, int numberNormalClass, int numberElementsCrew) {
        this.model = model;
        
        this.id = id;
        this.description = description;
        
        this.numberFirstClass = numberFirstClass;
        this.numberNormalClass = numberNormalClass;
        this.numberElementsCrew = numberElementsCrew;
    }

    public AircraftModel getModel() {
        return model;
    }
    
    public String getId() {
        return id;
    }

    public String getDescription() {
        return description;
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
        return "Aircraft{" + "id=" + id + ", description=" + description + ", numberFirstClass=" + numberFirstClass + ", numberNormalClass=" + numberNormalClass + ", numberElementsCrew=" + numberElementsCrew + ",\n model=" + model + '}';
    }

    

}
