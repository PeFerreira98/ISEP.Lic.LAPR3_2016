/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.controller;

import lapr.project.model.AircraftModel;

/**
 *
 * @author zero_
 */
public class AddAircraftModelController {
    
    public AircraftModel addAircraftModel (int id, String type, String motorization, 
            double emptyWeight, double MTOW, double MZFW, double maximumFuelCapacity,
            double serviceCeiling, double cruiseSpeed, double wingArea, double dragCoeficient, double liftCoeficient){
        
        final AircraftModel newAircraftModel = new AircraftModel(id, AircraftModel.Type.valueOf(type), AircraftModel.Motorization.valueOf(motorization), emptyWeight, MTOW, MZFW, maximumFuelCapacity, serviceCeiling, cruiseSpeed, wingArea, dragCoeficient, liftCoeficient);
        
//        final DatabaseModel databaseModel = new DatabaseModel();
//        databaseModel.addAircraftModel(newAircraftModel);

        return newAircraftModel;
    }
}