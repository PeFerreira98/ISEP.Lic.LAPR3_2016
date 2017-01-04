/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.controller;

import lapr.project.model.Aircraft;
import lapr.project.model.AircraftModel;
import lapr.project.model.Project;
import lapr.project.model.register.AircraftRegister;

/**
 *
 * @author Tiago
 */
public class AddAircraftController {
    
    private AircraftRegister aircraftRegister;
    private Aircraft aircraft;
    
    
    public AddAircraftController(AircraftRegister aircraftRegister, Aircraft aircraft){
        this.aircraftRegister = aircraftRegister;
        this.aircraft = aircraft;
    }
    

    public void setDados(String id, String description, int numberFirstClass, int numberNormalClass,int numberElementsCrew ){
        aircraft.setId(id);
        aircraft.setDescription(description);
        aircraft.setNumberFirstClass(numberFirstClass);
        aircraft.setNumberNormalClass(numberNormalClass);
        aircraft.setNumberElementsCrew(numberElementsCrew);
    }
        
    public boolean validateAircraft(Aircraft aircraft){
        return aircraftRegister.validateAircraft(aircraft);
    }
    
    public Aircraft newAircraft(){
        return new Aircraft();
    }
}
