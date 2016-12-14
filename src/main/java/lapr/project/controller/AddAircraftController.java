/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.controller;

import lapr.project.model.*;

/**
 *
 * @author zero_
 */
public class AddAircraftController {

    private final Project project;

    public AddAircraftController(Project project) {
        this.project = project;
    }

    public Aircraft addAircraft(AircraftModel model, int id, String company, 
            int numberFirstClass, int numberNormalClass, int numberElementsCrew) {

        final Aircraft newAircraft = new Aircraft(model, id, company, numberFirstClass, numberNormalClass, numberElementsCrew);

        if (this.project.addAircraft(newAircraft) != null) {
//        final DatabaseModel databaseModel = new DatabaseModel();
//        databaseModel.addAircraft(newAircraft);
        }

        return newAircraft;
    }

}
