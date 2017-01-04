/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.controller;

import java.util.ArrayList;
import java.util.List;
import javafx.scene.chart.PieChart;
import lapr.project.database.DatabaseModel;
import lapr.project.model.Aircraft;
import lapr.project.model.AircraftModel;
import lapr.project.model.Airport;
import lapr.project.model.Flight;
import lapr.project.model.FlightPlan;
import lapr.project.model.Location;
import lapr.project.model.Project;

/**
 *
 * @author Tiago
 */
public class CreateFlightController {

    private Project proj;
    private DatabaseModel db;
    private AircraftModel aircraftType;

    public CreateFlightController(Project proj) {
        this.proj = proj;
        //this.db = new DatabaseModel(proj);
    }
    
    //Falta o flightPath

    public void showAircraftType(AircraftModel aircraftType) {
        this.aircraftType.getType();

    }
    public ArrayList<Airport> getAirports(){
        //FIX_ME getListAirports incompleto, falta a location
        ArrayList<Airport> lstAirports = db.getListAirports();
        return lstAirports;
    }

    public void saveFlightPlan(FlightPlan flightP) {
            db.addFlightPlan(flightP);
    }

}
