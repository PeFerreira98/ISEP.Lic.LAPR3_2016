/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.controller;

import lapr.project.database.DatabaseModel;
import lapr.project.model.*;
import lapr.project.model.register.AircraftModelRegister;
import lapr.project.utils.AircraftStAXParser;

/**
 *
 * @author zero_
 */
public class AddAircraftModelController {

    private final Project project;

    public AddAircraftModelController(Project project) {
        this.project = project;
    }

    public void addAircraftModel(String filePath) {

        final AircraftStAXParser aircraftStAXParser = new AircraftStAXParser();
        final AircraftModelRegister aircraftModelRegister = aircraftStAXParser.XMLReader(filePath);

        for (AircraftModel aircraftModel : aircraftModelRegister.getAircraftModelMap().values()) {
            if (this.project.addAircraftModel(aircraftModel) != null) {
                final DatabaseModel databaseModel = new DatabaseModel();
                databaseModel.addAircraftModel(aircraftModel);
            }
        }
        
    }

}
