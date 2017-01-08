/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.controller;

import java.util.ArrayList;
import java.util.List;
import lapr.project.database.DatabaseModel;
import lapr.project.model.AircraftModel;
import lapr.project.model.Airport;
import lapr.project.model.FlightPlan;
import lapr.project.model.Project;

/**
 *
 * @author Tiago
 */
public class CreateFlightPlanController {

    private Project project;
    private FlightPlan flightPlan;
    private AircraftModel aircraftType;

    public CreateFlightPlanController(Project proj) {
        this.project = proj;
    }

    //Falta o flightPath
    public void showAircraftType(AircraftModel aircraftType) {
        this.aircraftType.getType();
    }

    public List<Airport> getAirports() {
        List<Airport> lst = new ArrayList<>();
        for (Airport airport : this.project.getAirportRegister().getAirportRegister().values()) {
            lst.add(airport);
        }
        return lst;
    }

    public boolean saveFlightPlan(String name, String aircraftModel, 
            Airport originAeroport, Airport destinationAeroport, 
            double normalClass, double firstClass, double crew) {
        
        try {
            this.flightPlan = new FlightPlan(name, AircraftModel.Type.valueOf(aircraftModel), 
                originAeroport, destinationAeroport, normalClass, firstClass, crew);
            
            this.project.addFlightPlan(flightPlan);
            return true;
        } catch (NullPointerException e) {
            System.out.println("Error Creating New FlightPlan");
        }
        
        
        return true;
    }

    public void saveFlightPlanToDatabase() {
        DatabaseModel db = new DatabaseModel();
        db.addFlightPlan(flightPlan);
    }

}
