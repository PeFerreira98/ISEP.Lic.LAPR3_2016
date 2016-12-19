/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.controller;

import lapr.project.database.DatabaseModel;
import lapr.project.model.*;
import lapr.project.model.register.AircraftRegister;
import lapr.project.utils.AircraftStAXParser;

/**
 *
 * @author zero_
 */
public class AddAircraftController {

    private final Project project;

    public AddAircraftController(Project project) {
        this.project = project;
    }

    public void addAircraft(String filePath) {

        final AircraftStAXParser aircraftStAXParser = new AircraftStAXParser();
        final AircraftRegister aircraftRegister = aircraftStAXParser.XMLReader(filePath);

        for (Aircraft aircraft : aircraftRegister.getAircraftMap().values()) {
            if (this.project.addAircraft(aircraft) != null) {
//                final DatabaseModel databaseModel = new DatabaseModel();
//                databaseModel.addAircraft(aircraft);
            }
        }
        
    }

}
