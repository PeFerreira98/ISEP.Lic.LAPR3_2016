/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

/**
 *
 * @author João
 */
public class Aircraft{
    
    private AircraftModel model;
    private int id;
    private String company;
    private int numberFirstClass;
    private int numberNormalClass;
    private int numberElementsCrew;

    public Aircraft(AircraftModel model, int id, String company, int numberFirstClass, int numberNormalClass, int numberElementsCrew) {
        this.model = model;
        this.id = id;
        this.company = company;
        this.numberFirstClass = numberFirstClass;
        this.numberNormalClass = numberNormalClass;
        this.numberElementsCrew = numberElementsCrew;
    }

    public AircraftModel getModel() {
        return model;
    }

    public void setModel(AircraftModel model) {
        this.model = model;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public int getNumberFirstClass() {
        return numberFirstClass;
    }

    public void setNumberFirstClass(int numberFirstClass) {
        this.numberFirstClass = numberFirstClass;
    }

    public int getNumberNormalClass() {
        return numberNormalClass;
    }

    public void setNumberNormalClass(int numberNormalClass) {
        this.numberNormalClass = numberNormalClass;
    }

    public int getNumberElementsCrew() {
        return numberElementsCrew;
    }

    public void setNumberElementsCrew(int numberElementsCrew) {
        this.numberElementsCrew = numberElementsCrew;
    }
    
    public boolean equals(Object otherObj) {
            if (this == otherObj) {
                return true;
            }
            if (otherObj == null || this.getClass() != otherObj.getClass()) {
                return false;
            }
            Aircraft otherAircraft = (Aircraft) otherObj;

            return this.id == otherAircraft.id;
        }

    
    @Override
    public String toString() {
        return "Aircraft{" + "model=" + model + ", id=" + id + ", company=" + company + ", numberFirstClass=" + numberFirstClass + ", numberNormalClass=" + numberNormalClass + ", numberElementsCrew=" + numberElementsCrew + '}';
    }
    
    
}
