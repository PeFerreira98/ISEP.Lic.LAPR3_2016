/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

import lapr.project.model.network.Segment;
import lapr.project.model.register.AircraftModelRegister;
import java.lang.Math;

/**
 *
 * @author João
 */
public class Physics {
    
    private AircraftModelRegister AircraftReg;
    
    
    public double calculateLiftForceInASegment(Aircraft aircraft, Segment segment){
    double AirDensity;
        //if (segment.getAltitudes_slots()<1000.0)
            AirDensity = 0.5;
    double AreaAircraft = 30;
        return calculateLiftCoeficient(aircraft, segment)*(AirDensity*Math.pow(aircraft.getModel().getRegimeRegister().getRegime("cruise").getSpeed(),2)/2)*AreaAircraft;
    }
    
    
    public double calculateDragForceInASegment(Aircraft aircraft, Segment segment){
        double AirDensity = 0.5;  //segment.getAltitudeSlots().getAirDensity();
        double AreaAircraft = 30;
        
        return aircraft.getModel().getDragCoeficient()*(AirDensity*aircraft.getModel().getRegimeRegister().getRegime("cruise").getSpeed())
                /2*AreaAircraft;
    }

}
