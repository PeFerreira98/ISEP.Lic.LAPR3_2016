/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.controller;

import java.util.logging.Level;
import lapr.project.database.DatabaseModel;
import lapr.project.model.Project;

/**
 *
 * @author zero_
 */
public class CopyProjectController {

    private Project oldProject;
    private Project newProject;

    public CopyProjectController(Project project) {
        this.oldProject = project;
    }

    public boolean validateProjectNameAndDescription(String name, String description) {
        DatabaseModel db = new DatabaseModel();
        return db.validateName(name);
    }
    
    public boolean createProject(String name, String description){
        try {
            this.newProject = new Project(name, description, 
                    oldProject.getAirNetwork(), oldProject.getAircraftModelRegister(),
                    oldProject.getAirportRegister(), oldProject.getAircraftRegister(), 
                    oldProject.getFlightPlanRegister());
            return true;
        } catch (NullPointerException e) {
            System.out.println("Error Creating New Project > " + e);
            return false;
        }
    }
    
    public boolean saveInDataBase(){
        DatabaseModel db = new DatabaseModel();
        try {
            db.addProject(newProject);
            return true;
        } catch (Exception e) {
            System.out.println("Error adding to database > " + e);
            return false;
        }
        
    }
}
