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
        
        db.EditAirport(iata, name, town, country, latitude, longitude, altitude);
    }
    
}
