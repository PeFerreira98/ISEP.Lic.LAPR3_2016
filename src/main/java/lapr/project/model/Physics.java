/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

import lapr.project.model.network.Segment;
import lapr.project.model.register.AircraftModelRegister;
import java.lang.Math;
import lapr.project.model.PhysicsConverters;
import lapr.project.model.network.AirNetwork;

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
        } else if (altitude > 7000 && altitude <= 8000) {
            p = 5.9;
            t = -30.45;
        } else if (altitude > 8000 && altitude <= 9000) {
            p = 5.258;
            t = -36.94;
        } else if (altitude > 9000 && altitude <= 10000) {
            p = 4.671;
            t = -43.42;
        } else if (altitude > 10000 && altitude <= 11000) {
            p = 4.135;
            t = -49.90;
        } else if (altitude > 11000 && altitude <= 12000) {
            p = 4.135;
            t = -49.90;
        } else if (altitude > 12000 && altitude <= 13000) {
            p = 4.135;
            t = -49.90;
        } else if (altitude > 13000 && altitude <= 14000) {
            p = 4.135;
            t = -49.90;
        }
        //Valores iguais entre 10km e 15km
    }

    public static double calculateSpeedDueAltitudeClimbing(Aircraft aircraft, double altitude, double speed) {

        if (altitude >= 0 && altitude <= 1000) {
            speed = 210;
        }
        if (altitude > 1000 && altitude <= 2000) {
            speed = 210;
        }
        if (altitude > 2000 && altitude <= 3000) {
            speed = 220;
        }
        if (altitude > 3000 && altitude <= 4000) {
            speed = 230;
        }
        if (altitude > 4000 && altitude <= 5000) {
            speed = 250;
        }
        if (altitude > 5000 && altitude <= 6000) {
            speed = 260;
        }
        if (altitude > 6000 && altitude <= 7000) {
            speed = 290;
        }
        if (altitude > 7000 && altitude <= 8000) {
            speed = 290;
        }
        if (altitude > 8000 && altitude <= 9000) {
            speed = 290;
        }
        if (altitude > 9000 && altitude <= 10000) {
            speed = 290;
        }
        if (altitude > 10000 && altitude <= 11000) {
            speed = 290;
        }
        if (altitude > 11000 && altitude <= 12000) {
            speed = 300;
        }
        if (altitude > 12000 && altitude <= 13000) {
            speed = 300;
        }
        if (altitude > 13000 && altitude <= 14000) {
            speed = 300;
        }
        if (altitude > 14000) {
            speed = 300;
        }
        return speed;
    }

    public static double calculateSpeedDueAltitudeDescending(Aircraft aircraft, double altitude, double speed) {

        if (altitude >= 0 && altitude <= 1000) {
            speed = 180;
        }
        if (altitude > 1000 && altitude <= 2000) {
            speed = 200;
        }
        if (altitude > 2000 && altitude <= 3000) {
            speed = 250;
        }
        if (altitude > 3000 && altitude <= 4000) {
            speed = 250;
        }
        if (altitude > 4000 && altitude <= 5000) {
            speed = 270;
        }
        if (altitude > 5000 && altitude <= 6000) {
            speed = 300;
        }
        if (altitude > 6000 && altitude <= 7000) {
            speed = 300;
        }
        if (altitude > 7000 && altitude <= 8000) {
            speed = 300;
        }
        if (altitude > 8000 && altitude <= 9000) {
            speed = 300;
        }
        if (altitude > 9000 && altitude <= 10000) {
            speed = 300;
        }
        if (altitude > 10000 && altitude <= 11000) {
            speed = 300;
        }
        if (altitude > 11000 && altitude <= 12000) {
            speed = 300;
        }
        if (altitude > 12000 && altitude <= 13000) {
            speed = 300;
        }
        if (altitude > 13000 && altitude <= 14000) {
            speed = 300;
        }
        if (altitude > 14000) {
            speed = 300;
        }
        return speed;
    }

    //__________________________________________Variations with altitude formulas___________________________
    public static double calculateTemperatureDueAltitude(double altitude) {
        double temperatureLapseRate = -0.0065;
        if (altitude < 14000) {
            return altitude * temperatureLapseRate + 288.2;
        }
        return 216.7;
    }

    public static double calculateDensityDueAltitude(double altitude, double pressure, double temperature) {
        double universalGasConstant = 287;
        return pressure / (universalGasConstant * temperature);
    }

    public static double calculateSpeedOfSoundDueAltitude(double altitude) {

        double temperature = calculateTemperatureDueAltitude(altitude);

        double gamma = 1.4;
        double universalGasConstant = 287;
        return Math.sqrt(gamma * universalGasConstant * temperature); //valor em metros por segundo
    }

    public static double calculatePressureDueAltitude(double altitude) {

        return 101325 * Math.pow(1 - 0.0065 * altitude / 288.2, 5.2561); //288.2-Temp kelvin sea level
        //return a temperatura em kelvins
        //primeiro valor é a pressao ao nivel do mar
    }

    //___________________________________Forces and aircraft related formulas_____________________________________
    public static double calculateLiftForceInASegment(Aircraft aircraft, Segment segment, double altitude) {
        double AirDensity = 0;
        if (altitude == -1) {
            altitude = aircraft.getModel().getCruiseAltitude();
        }

        double temperature = calculateTemperatureDueAltitude(altitude);
        double pressure = calculatePressureDueAltitude(altitude);
        AirDensity = calculateDensityDueAltitude(altitude, pressure, temperature);
        //calculateAirDensityTemperatureDueAltitude(altitude, AirDensity, 0);

        return calculateLiftCoeficient(aircraft, segment, altitude) * (AirDensity * Math.pow(aircraft.getModel().getCruiseSpeed(), 2) / 2) * aircraft.getModel().getWingArea();
    }

    public static double calculateDragForceInASegment(Aircraft aircraft, Segment segment, double altitude) {
        double AirDensity = 0;
        if (altitude == -1) {
            altitude = aircraft.getModel().getCruiseAltitude();
        }

        double temperature = calculateTemperatureDueAltitude(altitude);
        double pressure = calculatePressureDueAltitude(altitude);
        double speedVIAS = 0;
        AirDensity = calculateDensityDueAltitude(altitude, pressure, temperature);
        speedVIAS = calculateSpeedDueAltitudeClimbing(aircraft, altitude, speedVIAS);
        double machNumber = calculateTrueMachNumber(aircraft, altitude, speedVIAS);

        double speedOfSound = calculateSpeedOfSoundDueAltitude(altitude);

        double TAS = calculateTrueAirSpeed(machNumber, speedOfSound);
        double dragCoef = calculateDragCoeficient(aircraft, segment, altitude);

        //calculateAirDensityTemperatureDueAltitude(altitude, AirDensity, 0);
        return 0.5 * AirDensity * Math.pow(TAS, 2)
                * aircraft.getModel().getWingArea() * dragCoef;
    }

    public static double calculateLiftCoeficient(Aircraft aircraft, Segment segment, double altitude) {
        double AirDensity = 0;
        if (altitude == -1) {
            altitude = aircraft.getModel().getCruiseAltitude();
        }
        double temperature = calculateTemperatureDueAltitude(altitude);
        double pressure = calculatePressureDueAltitude(altitude);
        AirDensity = calculateDensityDueAltitude(altitude, pressure, temperature);
        double speedVIAS = 0;
        speedVIAS = calculateSpeedDueAltitudeClimbing(aircraft, altitude, speedVIAS);
        double machNumber = calculateTrueMachNumber(aircraft, altitude, speedVIAS);

        double speedOfSound = calculateSpeedOfSoundDueAltitude(altitude);

        double TAS = calculateTrueAirSpeed(machNumber, speedOfSound);
        //calculateAirDensityTemperatureDueAltitude(altitude, AirDensity, 0);
        double finalWeight = calculateAircraftFinalWeight(aircraft);
        return (2 * finalWeight * 9.81) / (AirDensity * aircraft.getModel().getWingArea() * Math.pow(TAS, 2));
    }

    public static double calculateDragCoeficient(Aircraft aircraft, Segment segment, Double altitude) {
        if (altitude == -1) {
            altitude = aircraft.getModel().getCruiseAltitude();
        }

        double liftcoeficient = calculateLiftCoeficient(aircraft, segment, altitude);
        //calculateAirDensityTemperatureDueAltitude(altitude, AirDensity, 0);
        //return aircraft.getModel().getCdragRegister().getCDrag(0).getcDrag0() + (Math.pow(calculateLiftCoeficient(aircraft, segment, altitude), 2)) / (Math.PI * (Math.pow(aircraft.getModel().getWingSpan(), 2) / aircraft.getModel().getWingArea()) * aircraft.getModel().getE());
        return aircraft.getModel().getCdragRegister().getCDrag(0).getcDrag0() + (Math.pow(liftcoeficient, 2) / (Math.PI * aircraft.getModel().getAspectRatio() * aircraft.getModel().getE()));
        //Cdrag 0 varia consoante a velocidade, falta depois compor, no excel o drag é sempre o mesmo
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
        return distance / PhysicsConverters.speedAndMMOConverterMachToKmsHour(aircraft.getModel().getCruiseSpeed()); //tempo(s)=distance(m)/speed(miles/sec?)
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

    public static void setsToAircraftValues(Aircraft aircraft) {

    }

    //__________________________________________Trip related formulas_______________________________________________
//    public static double calculateAircraftTrueAirspeed(Aircraft aircraft, Double altitude) {
//        double p = 0;
//        double t = 0;
//        calculateAirDensityTemperatureDueAltitude(altitude, p, t);
//
//        return 661.47 * aircraft.getModel().getMaxSpeed() * Math.sqrt(t / 288.15);
//
//        //TAS=a0*M*Sqrt(T/T0)
//        //a0=speed of sound(knot)
//        //M=Mach number
//        //T= temperature in kelvin
//        //T0=temperature at standard sea level in kelvin
//    }
    public static double calculateTrueMachNumber(Aircraft aircraft, double altitude, double speedVIAS) {

        double temperature = calculateTemperatureDueAltitude(altitude);
        double pressure = calculatePressureDueAltitude(altitude);
        double airDensity = calculateDensityDueAltitude(altitude, pressure, temperature);

        double a = Math.pow((1 + 0.2 * Math.pow((speedVIAS / 661.5), 2)), 3.5) - 1;
        return Math.sqrt(5 * (Math.pow((1.225 / airDensity) * (a) + 1, 0.286) - 1));
    }

    public static double calculateThrust(Aircraft aircraft, double altitude, double trueMachNumber) {

        double temperature = calculateTemperatureDueAltitude(altitude);
        double pressure = calculatePressureDueAltitude(altitude);
        double AirDensity = calculateDensityDueAltitude(altitude, pressure, temperature);
        double thrustSeaLevelMach0 = 338000; // em newtons
        double thrustChangeRate = (thrustSeaLevelMach0 - 176000) / 0.9; //duvida no Lapse Rate

        return thrustSeaLevelMach0 - thrustChangeRate * trueMachNumber;

    }

    public static double calculateThrustAltitude(Aircraft aircraft, Segment segment, double altitude, double trueMachNumber) {

        double temperature = calculateTemperatureDueAltitude(altitude);
        double pressure = calculatePressureDueAltitude(altitude);
        double AirDensity = calculateDensityDueAltitude(altitude, pressure, temperature);
        double thrust = calculateThrust(aircraft, altitude, trueMachNumber);
        return thrust * Math.pow((AirDensity / 1.225), 0.96); //0.96, valor dado pelo prof

    }

    public static double calculateTotalThrust(Aircraft aircraft, double thrust) {
        return aircraft.getModel().getNumberMotors() * thrust;
    }

    public static double calculateTrueAirSpeed(double trueMachNumber, double speedOfSound) {
        return trueMachNumber * speedOfSound; //valor em metros por segundo
    }

    public static double calculateAircraftClimbRate(Aircraft aircraft, Segment segment, double thrustTotal, double dragForce, double maxWeight, double trueAirSpeed) {

        return ((thrustTotal - dragForce) * trueAirSpeed / maxWeight / 9.81); // dividir por gravidade ou multiplicar?

    }

    public static double calculateFuelBurned(Aircraft aircraft, double dragForce) {

        return -aircraft.getModel().getTSFC() * dragForce;
    }

    public static double calculateAltitudeVariation(double trueAirSpeed, double totalThrust, double dragForce, double maxWeight) {

        return (totalThrust - dragForce) * trueAirSpeed / maxWeight / 9.81;
    }

    public static double calculateClimbingAngle(double trueAirSpeed, double climbRate) {

        return Math.asin(climbRate / trueAirSpeed);
    }

    public static double calculatedWdT(Aircraft aircraft, double time, double totalThrust) {

        return totalThrust * time * aircraft.getModel().getTSFC() / 9.81;
    }

    public static double calculateDistanceTraveledWhileClimbing(double trueAirspeed, double climbAngle, double time) {

        return trueAirspeed * Math.cos(climbAngle) * time;
    }

    public static double calculateDistanceEach120MinAtCruiseAltitude(Aircraft aircraft, double speed) {

        return speed * 120;
    }

    public static double calculateAircraftFinalWeight(Aircraft aircraft) {
        // return (aircraft.getNumberElementsCrew() + aircraft.getNumberFirstClass() + aircraft.getNumberNormalClass()) * 195 + aircraft.getModel().getFuelCapacity() + aircraft.getModel().getEmptyWeight();
        double litersInKg = PhysicsConverters.litersToKgConverter(aircraft.getModel().getFuelCapacity());

        return aircraft.getModel().getEmptyWeight() + aircraft.getModel().getMaxPayload() + litersInKg;
    }

    public static double[] aircraftClimb(Aircraft aircraft, Segment segment, Airport airport) {


        double distanceSegment = calculateSegmentDistance(aircraft, segment);
        double[] vec = new double[10];
        double liftForce = 0;
        double dragForce = 0;
        double thrustAltitude = 0;
        double maxWeight = 0;
        double climbRate = 0;
        double altitude = airport.getLocation().getAltitude();
        double time = 120;
        double fuelBurned = 0;
        double totalFuelBurned = 0;
        double distanceTraveled = 0;
        double distance = 0;
        double altitudeVariation = 0;
        double speed = 0;
        double trueMachNumber = 0;
        double trueAirSpeed = 0;
        double speedOfSound = 0;
        double climbAngle = 0;

            while (altitude <= aircraft.getModel().getCruiseAltitude()) {

                speed = calculateSpeedDueAltitudeClimbing(aircraft, altitude, speed); //Speed Due Altitude
                trueMachNumber = calculateTrueMachNumber(aircraft, altitude, speed); //MachNumber
                speedOfSound = calculateSpeedOfSoundDueAltitude(altitude);
                trueAirSpeed = calculateTrueAirSpeed(trueMachNumber, speedOfSound);
                liftForce = calculateLiftForceInASegment(aircraft, segment, altitude);
                dragForce = calculateDragForceInASegment(aircraft, segment, altitude);
                thrustAltitude = calculateThrustAltitude(aircraft, segment, altitude, trueMachNumber);
                thrustAltitude = aircraft.getModel().getNumberMotors() * thrustAltitude;
                maxWeight = calculateAircraftFinalWeight(aircraft);
                climbRate = calculateAircraftClimbRate(aircraft, segment, thrustAltitude, dragForce, maxWeight, trueAirSpeed); //climbRate
                climbAngle = calculateClimbingAngle(trueAirSpeed, climbRate);
                distance = calculateDistanceTraveledWhileClimbing(trueAirSpeed, climbAngle, 120);
                fuelBurned = calculatedWdT(aircraft, 120, thrustAltitude);
                altitudeVariation = calculateAltitudeVariation(trueAirSpeed, thrustAltitude, dragForce, maxWeight);
                maxWeight = maxWeight - fuelBurned;
                totalFuelBurned = totalFuelBurned + fuelBurned;
                altitude = altitude + altitudeVariation * 120;
                distanceTraveled = distanceTraveled + distance;
                time = time + 120;
             
            }

        
        vec[0] = altitude;
        vec[1] = maxWeight;
        vec[2] = distanceTraveled;
        vec[3] = totalFuelBurned;
        vec[4] = time;

        return vec;
    }

    public static double[] aircraftDescent(Aircraft aircraft, Segment segment, double[] valuesVec, Airport airport) {

        double altitudeAirport = airport.getLocation().getAltitude();

        double[] vec = new double[10];
        double liftForce = 0;
        double dragForce = 0;
        double thrustAltitude = 0;
        double maxWeight = vec[1];
        double descentRate = 0;
        double altitude = aircraft.getModel().getCruiseAltitude();
        double time = vec[4];
        double fuelBurned = 0;
        double totalFuelBurned = vec[3];
        double distanceTraveled = vec[2];
        double distance = 0;
        double altitudeVariation = 0;
        double speed = 0;
        double trueMachNumber = 0;
        double trueAirSpeed = 0;
        double speedOfSound = 0;
        double descentAngle = 0;

        while (altitude > altitudeAirport) {

            speed = calculateSpeedDueAltitudeClimbing(aircraft, altitude, speed); //Speed Due Altitude
            trueMachNumber = calculateTrueMachNumber(aircraft, altitude, speed); //MachNumber
            speedOfSound = calculateSpeedOfSoundDueAltitude(altitude);
            trueAirSpeed = calculateTrueAirSpeed(trueMachNumber, speedOfSound);
            liftForce = calculateLiftForceInASegment(aircraft, segment, altitude);
            dragForce = calculateDragForceInASegment(aircraft, segment, altitude);
            thrustAltitude = calculateThrustAltitude(aircraft, segment, altitude, trueMachNumber);
            thrustAltitude = 0.1 * aircraft.getModel().getNumberMotors() * thrustAltitude; //thrust quando está a descer
            descentRate = calculateAircraftClimbRate(aircraft, segment, thrustAltitude, dragForce, maxWeight, trueAirSpeed); //climbRate
            descentAngle = calculateClimbingAngle(trueAirSpeed, descentRate);
            distance = calculateDistanceTraveledWhileClimbing(trueAirSpeed, descentAngle, time);

            fuelBurned = calculatedWdT(aircraft, 120, thrustAltitude);
            altitudeVariation = calculateAltitudeVariation(trueAirSpeed, thrustAltitude, dragForce, maxWeight);
            maxWeight = maxWeight - fuelBurned;
            totalFuelBurned = totalFuelBurned + fuelBurned;
            altitude = altitude - altitudeVariation;
            distanceTraveled = distanceTraveled + distance;
            time = time + 120;

            //duvida onde entra o calculo do liftcoeficient
        }

        vec[0] = altitude;
        vec[1] = maxWeight;
        vec[2] = distanceTraveled;
        vec[3] = totalFuelBurned;
        vec[4] = time;

        return vec;

    }

//    public static double calculateDistanceNeededToDescent(Aircraft aircraft, Segment segment1, Segment segment2){
//        
//        double altitude = aircraft.getModel().getCruiseAltitude();
//        
//        
//    }
    public static double[] aircraftCruiseAltitudeCalculations(Aircraft aircraft, Segment segment, double[] valuesVec) {

        double[] vec = new double[10];
        double segmentDistance = calculateSegmentDistance(aircraft, segment);
        double liftForce = 0;
        double dragForce = 0;
        double thrustAltitude = 0;
        double maxWeight = vec[1];
        double altitude = aircraft.getModel().getCruiseAltitude();
        double time = vec[4];
        double fuelBurned = 0;
        double totalFuelBurned = vec[3];
        double distanceTraveled = vec[2];
        double distanceTraveledInSegment = 0;
        double speed = 0;
        double trueMachNumber = 0;
        double trueAirSpeed = 0;
        double speedOfSound = 0;

        maxWeight = calculateAircraftFinalWeight(aircraft);
        speed = calculateSpeedDueAltitudeClimbing(aircraft, altitude, speed); //Speed Due Altitude
        trueMachNumber = calculateTrueMachNumber(aircraft, altitude, speed); //MachNumber
        speedOfSound = calculateSpeedOfSoundDueAltitude(altitude);
        trueAirSpeed = calculateTrueAirSpeed(trueMachNumber, speedOfSound);
        liftForce = calculateLiftForceInASegment(aircraft, segment, altitude);
        dragForce = calculateDragForceInASegment(aircraft, segment, altitude);
        thrustAltitude = calculateThrustAltitude(aircraft, segment, altitude, trueMachNumber);
        thrustAltitude = aircraft.getModel().getNumberMotors() * thrustAltitude;

        double distanceTraveledEach120secs = calculateDistanceEach120MinAtCruiseAltitude(aircraft, trueAirSpeed);

        while (distanceTraveledInSegment < segmentDistance) {//altitude <= aircraft.getModel().getCruiseAltitude()) {

            fuelBurned = calculatedWdT(aircraft, 120, thrustAltitude);
            maxWeight = maxWeight - fuelBurned;
            totalFuelBurned = totalFuelBurned + fuelBurned;
            distanceTraveled = distanceTraveled + distanceTraveledEach120secs;
            distanceTraveledInSegment = distanceTraveledInSegment + distanceTraveledEach120secs;
            time = time + 120;
        }

        vec[0] = altitude;
        vec[1] = maxWeight;
        vec[2] = distanceTraveled;
        vec[3] = totalFuelBurned;
        vec[4] = time;

        return vec;

    }

    public static double[] allFlightCalculations(Aircraft aircraft, Segment[] segmentVec, Airport initialAirport, Airport endAirport) {

        Segment initialSegment = segmentVec[0];
        double[] valuesVec = new double[5];

        valuesVec = aircraftClimb(aircraft, segmentVec[0], initialAirport);

        for (int i = 0; i < segmentVec.length - 1; i++) {
            valuesVec = aircraftCruiseAltitudeCalculations(aircraft, initialSegment, valuesVec);
        }

        valuesVec = aircraftDescent(aircraft, segmentVec[segmentVec.length], valuesVec, endAirport);

        return valuesVec;
    }

}
