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

    public static void calculateAirDensityTemperatureDueAltitude(double altitude, double p, double t) {
        //Valores em unidades SI

        if (altitude > 0 && altitude <= 1000) {
            p = 12.25;
            t = 15;
        } else if (altitude > 1000 && altitude <= 2000) {
            p = 11.12;
            t = 8.5;
        } else if (altitude > 2000 && altitude <= 3000) {
            p = 10.07;
            t = 2.00;
        } else if (altitude > 3000 && altitude <= 4000) {
            p = 9.093;
            t = -4.49;
        } else if (altitude > 4000 && altitude <= 5000) {
            p = 8.194;
            t = -10.98;
        } else if (altitude > 5000 && altitude <= 6000) {
            p = 7.634;
            t = -17.47;
        } else if (altitude > 6000 && altitude <= 7000) {
            p = 6.601;
            t = -23.96;
        }
    }

    public static double calculateLiftForceInASegment(Aircraft aircraft, Segment segment, double altitude) {
        double AirDensity = 0;
        if (altitude == -1) {
            altitude = aircraft.getModel().getCruiseAltitude();
        }
        calculateAirDensityTemperatureDueAltitude(altitude, AirDensity, 0);

        return calculateLiftCoeficient(aircraft, segment, altitude) * (AirDensity * Math.pow(aircraft.getModel().getCruiseSpeed(), 2) / 2) * aircraft.getModel().getWingArea();
    }

    public static double calculateDragForceInASegment(Aircraft aircraft, Segment segment, double altitude) {
        double AirDensity = 0;
        if (altitude == -1) {
            altitude = aircraft.getModel().getCruiseAltitude();
        }
        calculateAirDensityTemperatureDueAltitude(altitude, AirDensity, 0);

        return aircraft.getModel().getCdragRegister().getCDrag(0).getcDrag0() * (AirDensity * Math.pow(aircraft.getModel().getCruiseSpeed(), 2) / 2)
                * aircraft.getModel().getWingArea();
    }

    public static double calculateLiftCoeficient(Aircraft aircraft, Segment segment, double altitude) {
        double AirDensity = 0;
        if (altitude == -1) {
            altitude = aircraft.getModel().getCruiseAltitude();
        }
        calculateAirDensityTemperatureDueAltitude(altitude, AirDensity, 0);

        return (2 * calculateAircraftFinalWeight(aircraft) * 9.8) / (AirDensity * aircraft.getModel().getWingArea() * Math.pow(aircraft.getModel().getCruiseSpeed(), 2));
    }

    public static double calculateDragCoeficient(Aircraft aircraft, Segment segment, Double altitude) {
        double AirDensity = 0;
        if (altitude == -1) {
            altitude = aircraft.getModel().getCruiseAltitude();
        }
        calculateAirDensityTemperatureDueAltitude(altitude, AirDensity, 0);
        return aircraft.getModel().getCdragRegister().getCDrag(0).getcDrag0() + (Math.pow(calculateLiftCoeficient(aircraft, segment, altitude), 2)) / (Math.PI * (Math.pow(aircraft.getModel().getWingSpan(), 2) / aircraft.getModel().getWingArea()) * Math.exp(1));
    }

    public static double calculateRangeEachSegment(Aircraft aircraft, Segment segment, double altitude) {
        if (altitude == -1) {
            altitude = aircraft.getModel().getCruiseAltitude();
        }

        double distance = calculateSegmentDistance(aircraft, segment);

        double time = calculateTravelTimeInASegment(aircraft, segment);

        double fuelComsuption = time * aircraft.getModel().getTSFC(); //(Consumo no segment)

        //Falta calcular o consumo nesta distancia à velocidade cruseiro
        return (aircraft.getModel().getCruiseSpeed() / aircraft.getModel().getTSFC())
                * (calculateLiftCoeficient(aircraft, segment, altitude) / calculateDragCoeficient(aircraft, segment, altitude)
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

    public static double calculateFuelComsuptionEachSegment(Aircraft aircraft, Segment segment) {

        return 0;
    }

    public static double calculateSegmentDistanceInMiles(double distance) {
        return 0.62 * distance;
    }

    public static double calculateAircraftFinalWeight(Aircraft aircraft) {
        return (aircraft.getNumberElementsCrew() + aircraft.getNumberFirstClass() + aircraft.getNumberNormalClass()) * 195 + aircraft.getModel().getFuelCapacity() + aircraft.getModel().getEmptyWeight();
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

    public static double calculateAircraftTrueAirspeed(Aircraft aircraft, Double altitude) {
        double p = 0;
        double t = 0;
        calculateAirDensityTemperatureDueAltitude(altitude, p, t);

        return 661.47 * aircraft.getModel().getMaxSpeed() * Math.sqrt(t / 288.15);

        //TAS=a0*M*Sqrt(T/T0)
        //a0=speed of sound(knot)
        //M=Mach number
        //T= temperature in kelvin
        //T0=temperature at standard sea level in kelvin
    }

    public static double calculateThrust(Aircraft aircraft, Segment segment, double altitude) {
        double p = 0;
        double t = 0;

        calculateAirDensityTemperatureDueAltitude(altitude, p, t);

        return 0;

    }

    public static double calculateThrustAltitude(Aircraft aircraft, Segment segment, double altitude) {
        double AirDensity = 0;
        calculateAirDensityTemperatureDueAltitude(altitude, AirDensity, 0);

        return aircraft.getModel().getThrust_0() * Math.pow((AirDensity / 12.25), 0.96); //0.96, valor dado pelo prof

    }

    public static double aircraftClimb(Aircraft aircraft, Segment segment) {
        double liftForce = 0;
        double dragForce = 0;
        double thrustAltitude = 0;
        double maxWeight = 0;
        double climbRate = 0;
        double altitude = 0;
        double time = 0;
        double fuelBurned = 0;
        double distanceTraveled = 0;
        double altitudeVariation = 0;
        double speed = 0;
        double speedVariationWithTime = 0;

        while (altitude <= aircraft.getModel().getCruiseAltitude()) {

            if (altitude >= 0 && altitude < 914.0) {

                liftForce = calculateLiftForceInASegment(aircraft, segment, altitude);
                dragForce = calculateDragForceInASegment(aircraft, segment, altitude);
                thrustAltitude = calculateThrustAltitude(aircraft, segment, altitude);
                maxWeight = calculateAircraftFinalWeight(aircraft);
                thrustAltitude = aircraft.getModel().getNumberMotors() * calculateThrustAltitude(aircraft, segment, altitude);
                climbRate = calculateAircraftClimbRate(aircraft, segment, thrustAltitude, dragForce, maxWeight);
                fuelBurned = calculateFuelBurned(aircraft, dragForce);
                altitudeVariation = calculateAltitudeVariation(speed, thrustAltitude, dragForce, speedVariationWithTime, maxWeight);

                maxWeight = maxWeight + fuelBurned; //fuelBurned já está negativo
                altitude = altitude + altitudeVariation;
                //falta calcular a distancia e fazer o update
                //duvida em como calcular a distancia, e a parte do TrueAirSpeed e descolagem(inicio dos slides)
                //duvida em relação à velocidade do aviao, consoante a altitude(preciso de alterá-la consoante a altitude)
            }
            if (altitude >= 914.0 && altitude < 914.0 * 2) {
                liftForce = calculateLiftForceInASegment(aircraft, segment, altitude);
                dragForce = calculateDragForceInASegment(aircraft, segment, altitude);
                thrustAltitude = calculateThrustAltitude(aircraft, segment, altitude);
                maxWeight = calculateAircraftFinalWeight(aircraft);
                thrustAltitude = aircraft.getModel().getNumberMotors() * calculateThrustAltitude(aircraft, segment, altitude);
                climbRate = calculateAircraftClimbRate(aircraft, segment, thrustAltitude, dragForce, maxWeight);
                fuelBurned = calculateFuelBurned(aircraft, dragForce);
                altitudeVariation = calculateAltitudeVariation(speed, thrustAltitude, dragForce, speedVariationWithTime, maxWeight);

                maxWeight = maxWeight + fuelBurned; //fuelBurned já está negativo
                altitude = altitude + altitudeVariation;
            }
            if (altitude >= 914.0 * 2 && altitude < 914.0 * 3) {
                liftForce = calculateLiftForceInASegment(aircraft, segment, altitude);
                dragForce = calculateDragForceInASegment(aircraft, segment, altitude);
                thrustAltitude = calculateThrustAltitude(aircraft, segment, altitude);
                maxWeight = calculateAircraftFinalWeight(aircraft);
                thrustAltitude = aircraft.getModel().getNumberMotors() * calculateThrustAltitude(aircraft, segment, altitude);
                climbRate = calculateAircraftClimbRate(aircraft, segment, thrustAltitude, dragForce, maxWeight);
                fuelBurned = calculateFuelBurned(aircraft, dragForce);
                altitudeVariation = calculateAltitudeVariation(speed, thrustAltitude, dragForce, speedVariationWithTime, maxWeight);

                maxWeight = maxWeight + fuelBurned; //fuelBurned já está negativo
                altitude = altitude + altitudeVariation;
            }
            if (altitude >= 914.0 * 3 && altitude < 914.0 * 4) {
                liftForce = calculateLiftForceInASegment(aircraft, segment, altitude);
                dragForce = calculateDragForceInASegment(aircraft, segment, altitude);
                thrustAltitude = calculateThrustAltitude(aircraft, segment, altitude);
                maxWeight = calculateAircraftFinalWeight(aircraft);
                thrustAltitude = aircraft.getModel().getNumberMotors() * calculateThrustAltitude(aircraft, segment, altitude);
                climbRate = calculateAircraftClimbRate(aircraft, segment, thrustAltitude, dragForce, maxWeight);
                fuelBurned = calculateFuelBurned(aircraft, dragForce);
                altitudeVariation = calculateAltitudeVariation(speed, thrustAltitude, dragForce, speedVariationWithTime, maxWeight);

                maxWeight = maxWeight + fuelBurned; //fuelBurned já está negativo
                altitude = altitude + altitudeVariation;
            }
            if (altitude >= 914.0 * 4 && altitude < 914.0 * 5) {
                liftForce = calculateLiftForceInASegment(aircraft, segment, altitude);
                dragForce = calculateDragForceInASegment(aircraft, segment, altitude);
                thrustAltitude = calculateThrustAltitude(aircraft, segment, altitude);
                maxWeight = calculateAircraftFinalWeight(aircraft);
                thrustAltitude = aircraft.getModel().getNumberMotors() * calculateThrustAltitude(aircraft, segment, altitude);
                climbRate = calculateAircraftClimbRate(aircraft, segment, thrustAltitude, dragForce, maxWeight);
                fuelBurned = calculateFuelBurned(aircraft, dragForce);
                altitudeVariation = calculateAltitudeVariation(speed, thrustAltitude, dragForce, speedVariationWithTime, maxWeight);

                maxWeight = maxWeight + fuelBurned; //fuelBurned já está negativo
                altitude = altitude + altitudeVariation;
            }
            if (altitude >= 914.0 * 5 && altitude < 914.0 * 6) {
                liftForce = calculateLiftForceInASegment(aircraft, segment, altitude);
                dragForce = calculateDragForceInASegment(aircraft, segment, altitude);
                thrustAltitude = calculateThrustAltitude(aircraft, segment, altitude);
                maxWeight = calculateAircraftFinalWeight(aircraft);
                thrustAltitude = aircraft.getModel().getNumberMotors() * calculateThrustAltitude(aircraft, segment, altitude);
                climbRate = calculateAircraftClimbRate(aircraft, segment, thrustAltitude, dragForce, maxWeight);
                fuelBurned = calculateFuelBurned(aircraft, dragForce);
                altitudeVariation = calculateAltitudeVariation(speed, thrustAltitude, dragForce, speedVariationWithTime, maxWeight);

                maxWeight = maxWeight + fuelBurned; //fuelBurned já está negativo
                altitude = altitude + altitudeVariation;
            }
            if (altitude >= 914.0 * 6 && altitude < 914.0 * 7) {
                liftForce = calculateLiftForceInASegment(aircraft, segment, altitude);
                dragForce = calculateDragForceInASegment(aircraft, segment, altitude);
                thrustAltitude = calculateThrustAltitude(aircraft, segment, altitude);
                maxWeight = calculateAircraftFinalWeight(aircraft);
                thrustAltitude = aircraft.getModel().getNumberMotors() * calculateThrustAltitude(aircraft, segment, altitude);
                climbRate = calculateAircraftClimbRate(aircraft, segment, thrustAltitude, dragForce, maxWeight);
                fuelBurned = calculateFuelBurned(aircraft, dragForce);
                altitudeVariation = calculateAltitudeVariation(speed, thrustAltitude, dragForce, speedVariationWithTime, maxWeight);

                maxWeight = maxWeight + fuelBurned; //fuelBurned já está negativo
                altitude = altitude + altitudeVariation;
            }
            if (altitude >= 914.0 * 7 && altitude < 914.0 * 8) {
                liftForce = calculateLiftForceInASegment(aircraft, segment, altitude);
                dragForce = calculateDragForceInASegment(aircraft, segment, altitude);
                thrustAltitude = calculateThrustAltitude(aircraft, segment, altitude);
                maxWeight = calculateAircraftFinalWeight(aircraft);
                thrustAltitude = aircraft.getModel().getNumberMotors() * calculateThrustAltitude(aircraft, segment, altitude);
                climbRate = calculateAircraftClimbRate(aircraft, segment, thrustAltitude, dragForce, maxWeight);
                fuelBurned = calculateFuelBurned(aircraft, dragForce);
                altitudeVariation = calculateAltitudeVariation(speed, thrustAltitude, dragForce, speedVariationWithTime, maxWeight);

                maxWeight = maxWeight + fuelBurned; //fuelBurned já está negativo
                altitude = altitude + altitudeVariation;
            }
            if (altitude >= 914.0 * 8 && altitude < 914.0 * 9) {
                liftForce = calculateLiftForceInASegment(aircraft, segment, altitude);
                dragForce = calculateDragForceInASegment(aircraft, segment, altitude);
                thrustAltitude = calculateThrustAltitude(aircraft, segment, altitude);
                maxWeight = calculateAircraftFinalWeight(aircraft);
                thrustAltitude = aircraft.getModel().getNumberMotors() * calculateThrustAltitude(aircraft, segment, altitude);
                climbRate = calculateAircraftClimbRate(aircraft, segment, thrustAltitude, dragForce, maxWeight);
                fuelBurned = calculateFuelBurned(aircraft, dragForce);
                altitudeVariation = calculateAltitudeVariation(speed, thrustAltitude, dragForce, speedVariationWithTime, maxWeight);

                maxWeight = maxWeight + fuelBurned; //fuelBurned já está negativo
                altitude = altitude + altitudeVariation;
            }
            if (altitude >= 914.0 * 9 && altitude < 914.0 * 10) {
                liftForce = calculateLiftForceInASegment(aircraft, segment, altitude);
                dragForce = calculateDragForceInASegment(aircraft, segment, altitude);
                thrustAltitude = calculateThrustAltitude(aircraft, segment, altitude);
                maxWeight = calculateAircraftFinalWeight(aircraft);
                thrustAltitude = aircraft.getModel().getNumberMotors() * calculateThrustAltitude(aircraft, segment, altitude);
                climbRate = calculateAircraftClimbRate(aircraft, segment, thrustAltitude, dragForce, maxWeight);
                fuelBurned = calculateFuelBurned(aircraft, dragForce);
                altitudeVariation = calculateAltitudeVariation(speed, thrustAltitude, dragForce, speedVariationWithTime, maxWeight);

                maxWeight = maxWeight + fuelBurned; //fuelBurned já está negativo
                altitude = altitude + altitudeVariation;
            }
            if (altitude >= 914.0 * 10 && altitude < 914.0 * 11) {
                liftForce = calculateLiftForceInASegment(aircraft, segment, altitude);
                dragForce = calculateDragForceInASegment(aircraft, segment, altitude);
                thrustAltitude = calculateThrustAltitude(aircraft, segment, altitude);
                maxWeight = calculateAircraftFinalWeight(aircraft);
                thrustAltitude = aircraft.getModel().getNumberMotors() * calculateThrustAltitude(aircraft, segment, altitude);
                climbRate = calculateAircraftClimbRate(aircraft, segment, thrustAltitude, dragForce, maxWeight);
                fuelBurned = calculateFuelBurned(aircraft, dragForce);
                altitudeVariation = calculateAltitudeVariation(speed, thrustAltitude, dragForce, speedVariationWithTime, maxWeight);

                maxWeight = maxWeight + fuelBurned; //fuelBurned já está negativo
                altitude = altitude + altitudeVariation;
            }
            if (altitude >= 914.0 * 11 && altitude < 914.0 * 12) {
                liftForce = calculateLiftForceInASegment(aircraft, segment, altitude);
                dragForce = calculateDragForceInASegment(aircraft, segment, altitude);
                thrustAltitude = calculateThrustAltitude(aircraft, segment, altitude);
                maxWeight = calculateAircraftFinalWeight(aircraft);
                thrustAltitude = aircraft.getModel().getNumberMotors() * calculateThrustAltitude(aircraft, segment, altitude);
                climbRate = calculateAircraftClimbRate(aircraft, segment, thrustAltitude, dragForce, maxWeight);
                fuelBurned = calculateFuelBurned(aircraft, dragForce);
                altitudeVariation = calculateAltitudeVariation(speed, thrustAltitude, dragForce, speedVariationWithTime, maxWeight);

                maxWeight = maxWeight + fuelBurned; //fuelBurned já está negativo
                altitude = altitude + altitudeVariation;
            }
            if (altitude >= 914.0 * 12 && altitude < 914.0 * 13) {
                liftForce = calculateLiftForceInASegment(aircraft, segment, altitude);
                dragForce = calculateDragForceInASegment(aircraft, segment, altitude);
                thrustAltitude = calculateThrustAltitude(aircraft, segment, altitude);
                maxWeight = calculateAircraftFinalWeight(aircraft);
                thrustAltitude = aircraft.getModel().getNumberMotors() * calculateThrustAltitude(aircraft, segment, altitude);
                climbRate = calculateAircraftClimbRate(aircraft, segment, thrustAltitude, dragForce, maxWeight);
                fuelBurned = calculateFuelBurned(aircraft, dragForce);
                altitudeVariation = calculateAltitudeVariation(speed, thrustAltitude, dragForce, speedVariationWithTime, maxWeight);

                maxWeight = maxWeight + fuelBurned; //fuelBurned já está negativo
                altitude = altitude + altitudeVariation;
            }
            if (altitude >= 914.0 * 13 && altitude < 914.0 * 14) {
                liftForce = calculateLiftForceInASegment(aircraft, segment, altitude);
                dragForce = calculateDragForceInASegment(aircraft, segment, altitude);
                thrustAltitude = calculateThrustAltitude(aircraft, segment, altitude);
                maxWeight = calculateAircraftFinalWeight(aircraft);
                thrustAltitude = aircraft.getModel().getNumberMotors() * calculateThrustAltitude(aircraft, segment, altitude);
                climbRate = calculateAircraftClimbRate(aircraft, segment, thrustAltitude, dragForce, maxWeight);
                fuelBurned = calculateFuelBurned(aircraft, dragForce);
                altitudeVariation = calculateAltitudeVariation(speed, thrustAltitude, dragForce, speedVariationWithTime, maxWeight);

                maxWeight = maxWeight + fuelBurned; //fuelBurned já está negativo
                altitude = altitude + altitudeVariation;
            }
            if (altitude < 914.0 * 14) {
                liftForce = calculateLiftForceInASegment(aircraft, segment, altitude);
                dragForce = calculateDragForceInASegment(aircraft, segment, altitude);
                thrustAltitude = calculateThrustAltitude(aircraft, segment, altitude);
                maxWeight = calculateAircraftFinalWeight(aircraft);
                thrustAltitude = aircraft.getModel().getNumberMotors() * calculateThrustAltitude(aircraft, segment, altitude);
                climbRate = calculateAircraftClimbRate(aircraft, segment, thrustAltitude, dragForce, maxWeight);
                fuelBurned = calculateFuelBurned(aircraft, dragForce);
                altitudeVariation = calculateAltitudeVariation(speed, thrustAltitude, dragForce, speedVariationWithTime, maxWeight);

                maxWeight = maxWeight + fuelBurned; //fuelBurned já está negativo
                altitude = altitude + altitudeVariation;
            }

        }
        return 0;
    }

    public static double calculateAircraftClimbRate(Aircraft aircraft, Segment segment, double thrustTotal, double dragForce, double maxWeight) {

        return ((thrustTotal - dragForce) * aircraft.getModel().getCruiseSpeed() / maxWeight * 9.81);

    }

    public static double calculateFuelBurned(Aircraft aircraft, double dragForce) {

        return -aircraft.getModel().getTSFC() * dragForce;
    }

    public static double calculateAltitudeVariation(double speed, double thrustAltitude, double dragForce, double SpeedVariationWithTime, double maxWeight) {

        return (((thrustAltitude - dragForce) * speed / maxWeight * 9.81) - (speed / 9.81) * SpeedVariationWithTime);
    }

}
