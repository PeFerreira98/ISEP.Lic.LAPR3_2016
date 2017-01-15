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

    public static double converterDistanceKmToMiles(double distance) {
        return 0.62 * distance;
    }

    public static double converterMachToKmsHour(double aircraftSpeedORMmoValue) {
        return aircraftSpeedORMmoValue * 1225.04;
    }
    
    public static double altitudeConverterFeetToMeters(double feet) {
        return feet * 0.3048;
    }

    public static double converterPoundsToKg(double anyAircraftWeightValue) {
        return anyAircraftWeightValue * 0.45359237;
    }
    
    public static double converterKnotToKms(double knot){
        return knot*1.852;
    }

    public static double converterGallonsToLiter(double gallons) {
        return (gallons* 0.82) / 0.2642;       //Fuel density = 0,82 fuel (1kg water = 0,82 fuel(petrol)
    }

    public static double converterKelvinToCelsius(double temperature) {
        return temperature - 273.15;
    }

    public static double litersToKgConverter(double liters) {

        return liters * 0.804;
    }
    
    public static double metersToKm(double meters){
        return meters * 1000;
    }
    
    public static double secondsToHours(double seconds){
        return seconds * 0.000277777778;
    }
    
}
