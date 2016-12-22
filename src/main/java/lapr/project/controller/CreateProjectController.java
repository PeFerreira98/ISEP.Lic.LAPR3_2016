/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.controller;

import lapr.project.database.DatabaseModel;
import lapr.project.model.Project;
import lapr.project.ui.LoadProjectUI;
import lapr.project.utils.*;

/**
 *
 * @author Sofia
 */
public class CreateProjectController {
    
    private DatabaseModel db;
    private NetworkStAXParser networkParser;
    private AirportStAXParser airportParser;
    private AircraftStAXParser aircraftParser;
    private Project proj;
    
    public CreateProjectController(){
        this.proj = new Project();
        db = new DatabaseModel();
    }
    
    public boolean validateProjectNameAndDescription(String name, String description){
        if(db.validateName(name)){
            proj = new Project(name, description);
            return true;
        }
        return false;
    }
    
    public void LoadProject(){
        new LoadProjectUI();
    }
    
    public void addNetwork(String path){
        networkParser = new NetworkStAXParser(proj);
        networkParser.XMLReader(path);
    }
    
    public void addAirports(String path){
        airportParser = new AirportStAXParser(proj);
        airportParser.XMLReader(path);
    }
    
    public void addAircraftModels(String path){
        aircraftParser = new AircraftStAXParser(proj);
        aircraftParser.XMLReader(path);
    }
        
    public void saveInDataBase(){
        db.addProject(proj);
    }
}
