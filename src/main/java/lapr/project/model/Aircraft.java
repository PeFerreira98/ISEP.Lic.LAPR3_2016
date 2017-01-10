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
public class Aircraft {

    private String id;
    private String description;

    private double numberFirstClass;
    private double numberNormalClass;
    private double numberElementsCrew;
    private double cargo;
    private double fuel;

    private AircraftModel model;

    public Aircraft(String description, double numberFirstClass, double numberNormalClass, double numberElementsCrew, double cargo, double fuel, AircraftModel model) {
        this.description = description;
        this.numberFirstClass = numberFirstClass;
        this.numberNormalClass = numberNormalClass;
        this.numberElementsCrew = numberElementsCrew;
        this.cargo = cargo;
        this.fuel = fuel;
        this.model = model;
    }

    public Aircraft(String id, String description, double numberFirstClass, double numberNormalClass, double numberElementsCrew, double cargo, double fuel, AircraftModel model) {
        this.id = id;
        this.description = description;
        this.numberFirstClass = numberFirstClass;
        this.numberNormalClass = numberNormalClass;
        this.numberElementsCrew = numberElementsCrew;
        this.cargo = cargo;
        this.fuel = fuel;
        this.model = model;
    }

    public String getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public double getNumberFirstClass() {
        return numberFirstClass;
    }

    public double getNumberNormalClass() {
        return numberNormalClass;
    }

    public double getNumberElementsCrew() {
        return numberElementsCrew;
    }

    public double getCargo() {
        return cargo;
    }

    public double getFuel() {
        return fuel;
    }

    public AircraftModel getModel() {
        return model;
    }

    public void setDescription(String description) {
        this.description = description;
    }

   @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + Objects.hashCode(this.id);
        hash = 97 * hash + Objects.hashCode(this.description);
        return hash;
    }

    /**
     *
     * @param otherObj
     * @return
     */
    public boolean equals(Object otherObj) {
        if (otherObj == null || this.getClass() != otherObj.getClass()) {
            return false;
        }

        Aircraft otherAircraft = (Aircraft) otherObj;
        return Objects.equals(this.id, otherAircraft.getId())
                && this.description.equalsIgnoreCase(otherAircraft.description);
    }

    
    @Override
    public String toString() {
        return "Aircraft{" + "id=" + id + ", description=" + description + ", numberFirstClass=" + numberFirstClass + ", numberNormalClass=" + numberNormalClass + ", numberElementsCrew=" + numberElementsCrew + ", cargo=" + cargo + ", fuel=" + fuel + ", model=" + model + '}';
    }

}
