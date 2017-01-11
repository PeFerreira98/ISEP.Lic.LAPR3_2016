/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.controller;

import java.util.ArrayList;
import lapr.project.model.Aircraft;
import lapr.project.model.AircraftModel;
import lapr.project.model.FlightPlan;
import lapr.project.model.Project;
import lapr.project.utils.ImportFlightPatternCSV;

/**
 *
 * @author zero_
 */
public class SimulateFlightController {

    private Project project;
    private ArrayList<FlightPlan> flightPlansList;
    private ArrayList<AircraftModel> aircraftModelsList;
    private Aircraft newAircraft;
    private double[][] csvList = null;

    public SimulateFlightController(Project p) {
        this.project = p;
        this.flightPlansList = new ArrayList<>();
        this.aircraftModelsList = new ArrayList<>();
    }

    public void initializeFlightPlansList() {
        for (FlightPlan fp : this.project.getFlightPlanRegister().getFlightPlansList().values()) {
            this.flightPlansList.add(fp);
        }
    }

    public int getFlightPlansListSize() {
        return this.flightPlansList.size();
    }

    public String getFlightPlanNameByIndex(int index) {
        return this.flightPlansList.get(index).getName();
    }

    public FlightPlan getFlightPlanByIndex(int index) {
        return this.flightPlansList.get(index);
    }

    public void initializeaircraftModelsList(AircraftModel.Type aircraftType) {
        for (AircraftModel aircraftModel : this.project.getAircraftModelRegister().getAircraftsByType(aircraftType).values()) {
            this.aircraftModelsList.add(aircraftModel);
        }
    }

    public int getaircraftModelsListSize() {
        return this.aircraftModelsList.size();
    }

    public String getaircraftModelIdByIndex(int index) {
        return this.aircraftModelsList.get(index).getId();
    }

    public AircraftModel getaircraftModelsByIndex(int index) {
        return this.aircraftModelsList.get(index);
    }

    public Aircraft getAircraft() {
        return this.newAircraft;
    }
    
    public double[][] getCSVList(){
        return this.csvList;
    }
    
    public boolean importCSV(String filepath){
        this.csvList = ImportFlightPatternCSV.CSVImport(filepath);
        return this.csvList != null;
    }

    public boolean checkMax(FlightPlan fp, double nNormal, double nFirst, double nCrew) {

        return !(fp.getnNormalClass() < nNormal
                || fp.getnFirstClass() < nFirst
                || fp.getnCrew() < nCrew);
    }

    public boolean generateAircraft(double nNormal, double nFirst, double nCrew,
            double cargo, double fuel, AircraftModel aircraftModel) {

        this.newAircraft = new Aircraft(aircraftModel.getId(), aircraftModel.getDescription(),
                nFirst, nNormal, nCrew, cargo, fuel, aircraftModel);

        System.out.println(newAircraft);

        return this.project.addAircraft(this.newAircraft);
    }
}
