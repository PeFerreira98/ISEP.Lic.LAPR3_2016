/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

/**
 *
 * @author João
 */
public class PhysicsConverters {

    public static double calculateSegmentDistanceInMiles(double distance) {
        return 0.62 * distance;
    }


    public static double speedAndMMOConverterMachToKmsHour(Double aircraftSpeedORMmoValue) {
        return aircraftSpeedORMmoValue * 1225.04;
    }

    public static double tsfcConverter(Aircraft aircraft) {
        return aircraft.getModel().getTSFC() / 3600 * 101972;
    }

//    public static double thrustConversor(Aircraft aircraft){
//        
//        return aircraft.getModel().getRegimeRegister().getRegime("cruise").getTSFC()
//    }
    public static double altitudeConverterFeetToMeters(Aircraft aircraft) {
        return aircraft.getModel().getCruiseAltitude() * 0.3048;
    }

    public static double aicraftWeightConverterPoundsToKg(double anyAircraftWeightValue) {
        return anyAircraftWeightValue * 0.45359237;
    }

    public static double aircraftVMOConverterKnotToKmsHour(Aircraft aircraft) {
        return aircraft.getModel().getVMO() * 1.852;
    }

    public static double aircraftFuelCapacityConverterGallonsToLiter(Aircraft aircraft) {
        return (aircraft.getModel().getFuelCapacity() * 0.82) / 0.2642;       //Fuel density = 0,82 fuel (1kg water = 0,82 fuel(petrol)
    }

    public static double temperatudeConverterKelvinToCelsius(double temperature) {

        return temperature - 273.15;
    }
}
