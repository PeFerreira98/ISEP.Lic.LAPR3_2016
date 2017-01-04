/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

import lapr.project.model.network.Segment;
import lapr.project.model.register.AircraftModelRegister;
import java.lang.Math;

/**
 *
 * @author João
 */
public class Physics {

    public static double calculateLiftForceInASegment(Aircraft aircraft, Segment segment) {
        double AirDensity;
        //if (segment.getAltitudes_slots()<1000.0)
        AirDensity = 0.5;
        double AreaAircraft = 30;
        return calculateLiftCoeficient(aircraft, segment) * (AirDensity * Math.pow(aircraft.getModel().getCruiseSpeed(), 2) / 2) * AreaAircraft;
    }

    public static double calculateDragForceInASegment(Aircraft aircraft, Segment segment) {
        double AirDensity = 0.5;  //segment.getAltitudeSlots().getAirDensity();
        double AreaAircraft = 30;

        return aircraft.getModel().getCdragRegister().getCDrag(0).getcDrag0() * (AirDensity * aircraft.getModel().getCruiseSpeed())
                / 2 * AreaAircraft;
    }

    public static double calculateLiftCoeficient(Aircraft aircraft, Segment segment) {
        double AirDensity = 0.5;  //segment.getAltitudeSlots().getAirDensity();
        double AreaAircraft = 30;
        return (2 * aircraft.getModel().getEmptyWeight() * 9.8) / (AirDensity * AreaAircraft * Math.pow(aircraft.getModel().getCruiseSpeed(), 2));
    }

    public static double calculateDragCoeficient(Aircraft aircraft, Segment segment) {
        double AirDensity = 0.5;  //segment.getAltitudeSlots().getAirDensity();
        double AreaAircraft = 30;
        return aircraft.getModel().getCdragRegister().getCDrag(0).getcDrag0() + (Math.pow(calculateLiftCoeficient(aircraft, segment), 2)) / (Math.PI * (Math.pow(aircraft.getModel().getWingSpan(), 2) / aircraft.getModel().getWingArea() * Math.exp(1)));
    }

    public static double calculateRangeEachSegment(Aircraft aircraft, Segment segment) {

        double distance = calculateSegmentDistance(aircraft, segment);

        double time = calculateTravelTimeInASegment(aircraft, segment);

        double fuelComsuption = time * aircraft.getModel().getTSFC(); //(Consumo no segment)

        //Falta calcular o consumo nesta distancia à velocidade cruseiro
        return (aircraft.getModel().getCruiseSpeed() / aircraft.getModel().getTSFC())
                * (calculateLiftCoeficient(aircraft, segment) / calculateDragCoeficient(aircraft, segment)
                * Math.log(aircraft.getModel().getEmptyWeight() / calculateAircraftFinalWeight(aircraft)));
    }
    

    public static double calculateTravelTimeInASegment(Aircraft aircraft, Segment segment) {
        double distance = calculateSegmentDistance(aircraft, segment);
        //calculateSegmentDistanceInMiles(distance) / speedAndMMOConverterMachToKmsHour(aircraft.getModel().getRegimeRegister().getRegime("Cruise").getSpeed());
        return distance / speedAndMMOConverterMachToKmsHour(aircraft.getModel().getCruiseSpeed()); //tempo(s)=distance(m)/speed(miles/sec?)
    }

    public static double calculateSegmentDistance(Aircraft aircraft, Segment segment) {

        double latitude1 = segment.getBeginningNode().getLatitude();
        double longitude1 = segment.getBeginningNode().getLongitude();
        double latitude2 = segment.getEndNode().getLatitude();
        double longitude2 = segment.getEndNode().getLongitude();

        double a = Math.toRadians(latitude2 - latitude1);
        double b = Math.toRadians(longitude2 - longitude1);

        double c = Math.sin(a / 2) * Math.sin(b / 2) + Math.cos(latitude1) * Math.cos(latitude2) * Math.sin(b / 2) * Math.sin(b / 2);

        double d = 2 * Math.atan2(Math.sqrt(c), Math.sqrt(1 - c));
        double r = 6371;

        return r * d;
    }
    
    public static double calculateFuelComsuptionEachSegment(Aircraft aircraft,Segment segment){
        
        return 0;
    }

    public static double calculateSegmentDistanceInMiles(double distance) {
        return 0.62 * distance;
    }

    public static double calculateAircraftFinalWeight(Aircraft aircraft) {
        return (aircraft.getNumberElementsCrew() + aircraft.getNumberFirstClass() + aircraft.getNumberNormalClass()) * 195 + aircraft.getModel().getFuelCapacity();
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

    public static void setsToAircraftValues(Aircraft aircraft) {

    }

    public static double calculateAircraftTrueAirspeed(Aircraft aircraft) {
        double p;
        double t = 15; //(ºC) falta converter na formula

        if (aircraft.getModel().getCruiseAltitude() > 0 && aircraft.getModel().getCruiseAltitude() <= 1000) {
            p = 12.25;
            t = 15;
        } else if (aircraft.getModel().getCruiseAltitude() > 1000 && aircraft.getModel().getCruiseAltitude() <= 2000) {
            p = 11.12;
            t = 8.5;
        } else if (aircraft.getModel().getCruiseAltitude() > 2000 && aircraft.getModel().getCruiseAltitude() <= 3000) {
            p = 10.07;
            t = 2.00;
        } else if (aircraft.getModel().getCruiseAltitude() > 3000 && aircraft.getModel().getCruiseAltitude() <= 4000) {
            p = 9.093;
            t = -4.49;
        } else if (aircraft.getModel().getCruiseAltitude() > 4000 && aircraft.getModel().getCruiseAltitude() <= 5000) {
            p = 8.194;
            t = -10.98;
        } else if (aircraft.getModel().getCruiseAltitude() > 5000 && aircraft.getModel().getCruiseAltitude() <= 6000) {
            p = 7.634;
            t = -17.47;
        } else if (aircraft.getModel().getCruiseAltitude() > 6000 && aircraft.getModel().getCruiseAltitude() <= 7000) {
            p = 6.601;
            t = -23.96;
        }

        return 661.47 * aircraft.getModel().getMaxSpeed() * Math.sqrt(t / 288.15);
        
        //TAS=a0*M*Sqrt(T/T0)
        //a0=speed of sound(knot)
        //M=Mach number
        //T= temperature in kelvin
        //T0=temperature at standard sea level in kelvin
    }

    public static double calculateThrust(Aircraft aircraft, Segment segment) {
        double p;
        if (aircraft.getModel().getCruiseAltitude() > 0 && aircraft.getModel().getCruiseAltitude() <= 1000) {
            p = 12.25;
        } else if (aircraft.getModel().getCruiseAltitude() > 1000 && aircraft.getModel().getCruiseAltitude() <= 2000) {
            p = 11.12;
        } else if (aircraft.getModel().getCruiseAltitude() > 2000 && aircraft.getModel().getCruiseAltitude() <= 3000) {
            p = 10.07;
        } else if (aircraft.getModel().getCruiseAltitude() > 3000 && aircraft.getModel().getCruiseAltitude() <= 4000) {
            p = 9.093;
        } else if (aircraft.getModel().getCruiseAltitude() > 4000 && aircraft.getModel().getCruiseAltitude() <= 5000) {
            p = 8.194;
        } else if (aircraft.getModel().getCruiseAltitude() > 5000 && aircraft.getModel().getCruiseAltitude() <= 6000) {
            p = 7.634;
        } else if (aircraft.getModel().getCruiseAltitude() > 6000 && aircraft.getModel().getCruiseAltitude() <= 7000) {
            p = 6.601;
        }

        return 0;

    }

    public static double calculateThrustAltitude(Aircraft aircraft, Segment segment) {

        return 0;

    }

    public static double aircraftClimb(Aircraft aircraft, Segment segment) {

        double liftForce = calculateLiftForceInASegment(aircraft, segment);
        double dragCoeficient = calculateDragForceInASegment(aircraft, segment); //???????????

        return 0;
    }
}
