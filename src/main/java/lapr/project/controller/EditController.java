/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.controller;

import lapr.project.database.DatabaseModel;
import lapr.project.model.Project;

/**
 *
 * @author Marcos
 */
public class EditController {
    
    Project project;
    
    public EditController(Project p){
        this.project = p;
    }
    
    public void editAirport(String iata, String name, String town, String country, double latitude, double longitude, double altitude){
        DatabaseModel db = new DatabaseModel(this.project);
    }
    
    public void editAircraftModel(String id, String description, String maker, String type, String motor, double numberMotors, String motorType, double cruiseAltitude, double cruiseSpeed, double TSFC, double lapseRateFactor,
        double thrust_0, double thrustMaxSpeed, double maxSpeed, double emptyWeight, double MTOW, double maxPayload, double fuelCapacity, double VMO,double MMO, double wingArea, double wingSpan, double aspectRatio,double e){
        
        DatabaseModel db = new DatabaseModel(this.project);
        
    }
    
}
