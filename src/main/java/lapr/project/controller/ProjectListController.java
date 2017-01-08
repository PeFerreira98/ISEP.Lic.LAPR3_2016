/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.controller;

import java.util.LinkedList;
import java.util.List;
import lapr.project.database.DatabaseModel;
import lapr.project.model.AircraftModel;
import lapr.project.model.FlightPlan;
import lapr.project.model.Project;
import lapr.project.utils.AircraftStAXParser;
import lapr.project.utils.AirportStAXParser;
import lapr.project.utils.NetworkStAXParser;

/**
 *
 * @author Marcos
 */
public class ProjectListController {

    private List<Project> listProjects;

    public ProjectListController() {
        listProjects = new LinkedList<>();
        defaultProject();
    }

    public List<Project> getListProjects() {
        return listProjects;
    }

    public void setListProjects(List<Project> listProjects) {
        this.listProjects = listProjects;
    }

    /**
     * Carrega os Projects guardados.
     */
    public void loadAllProjects() {
        DatabaseModel db = new DatabaseModel();
        this.setListProjects(db.getProjects());
    }

    private void defaultProject() {
        Project project = new Project("proj0", "proj");

        NetworkStAXParser network = new NetworkStAXParser(project);
        AircraftStAXParser instance = new AircraftStAXParser(project);
        AirportStAXParser airports = new AirportStAXParser(project);

        network.XMLReader("inOutFiles/TestSet02_Network.xml");
        instance.XMLReader("inOutFiles/TestSet02_Aircraft.xml");
        airports.XMLReader("inOutFiles/TestSet02_Airports.xml");
        
        FlightPlan flightplan = new FlightPlan("FP1", AircraftModel.Type.PASSENGER, 
                project.getAirportRegister().getAirportByIATACode("OPO"),
                project.getAirportRegister().getAirportByIATACode("LIS"), 
                10, 10, 10);
        
        project.addFlightPlan(flightplan);

        this.listProjects.add(project);
    }
}
