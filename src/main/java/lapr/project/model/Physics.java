/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

import lapr.project.model.network.Segment;
import java.lang.Math;
import java.util.HashMap;
import java.util.Map;
import static lapr.project.model.Physics.calculateAircraftClimbRate;
import static lapr.project.model.Physics.calculateAltitudeVariation;
import static lapr.project.model.Physics.calculateClimbingAngle;
import static lapr.project.model.Physics.calculateDistanceTraveledWhileClimbing;
import static lapr.project.model.Physics.calculateDragForceInASegment;
import static lapr.project.model.Physics.calculateSegmentDistance;
import static lapr.project.model.Physics.calculateSpeedDueAltitudeClimbing;
import static lapr.project.model.Physics.calculateSpeedOfSoundDueAltitude;
import static lapr.project.model.Physics.calculateThrust;
import static lapr.project.model.Physics.calculateTrueAirSpeed;
import static lapr.project.model.Physics.calculateTrueMachNumber;
import static lapr.project.model.Physics.calculatedWdT;
import lapr.project.model.PhysicsConverters;

/**
 *
 * @author João
 */
public class Physics {

    public static double calculateSpeedDueAltitudeClimbing(double altitude, double[][] matrix) {

        if (altitude == 0) {
            return matrix[1][0];
        }
        for (int i = 0; i < matrix[0].length; i++) {
            if (matrix[0][i] > altitude) {
                return matrix[1][i - 1];
            }
        }
        return matrix[1][matrix[0].length - 1];
    }

    public static double calculateSpeedDueAltitudeDescending(double altitude, double[][] matrix) {

        if (altitude == 0) {
            return matrix[2][0];
        }
        for (int i = 0; i < matrix[0].length; i++) {
            if (matrix[0][i] > altitude) {
                return matrix[2][i - 1];
            }
        }
        return matrix[2][matrix[0].length - 1];
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
    public static double calculateLiftForceInASegment(Aircraft aircraft, double altitude, double[][] matrix) {
        double AirDensity = 0;
        if (altitude == -1) {
            altitude = aircraft.getModel().getCruiseAltitude();
        }

        double temperature = calculateTemperatureDueAltitude(altitude);
        double pressure = calculatePressureDueAltitude(altitude);
        AirDensity = calculateDensityDueAltitude(altitude, pressure, temperature);

        return calculateLiftCoeficient(aircraft, altitude, matrix) * (AirDensity * Math.pow(aircraft.getModel().getCruiseSpeed(), 2) / 2) * aircraft.getModel().getWingArea();
    }

    public static double calculateDragForceInASegment(Aircraft aircraft, double altitude, double[][] matrix) {
        double AirDensity = 0;
        if (altitude == -1) {
            altitude = aircraft.getModel().getCruiseAltitude();
        }

        double temperature = calculateTemperatureDueAltitude(altitude);
        double pressure = calculatePressureDueAltitude(altitude);
        double speedVIAS = 0;
        AirDensity = calculateDensityDueAltitude(altitude, pressure, temperature);
        speedVIAS = calculateSpeedDueAltitudeClimbing(altitude, matrix);
        double machNumber = calculateTrueMachNumber(aircraft, altitude, speedVIAS);
        double speedOfSound = calculateSpeedOfSoundDueAltitude(altitude);
        double TAS = calculateTrueAirSpeed(machNumber, speedOfSound);
        double dragCoef = calculateDragCoeficient(aircraft, altitude, matrix);

        return 0.5 * AirDensity * Math.pow(TAS, 2)
                * aircraft.getModel().getWingArea() * dragCoef;
    }

    public static double calculateLiftCoeficient(Aircraft aircraft, double altitude, double[][] matrix) {
        double AirDensity = 0;
        if (altitude == -1) {
            altitude = aircraft.getModel().getCruiseAltitude();
        }

        double temperature = calculateTemperatureDueAltitude(altitude);
        double pressure = calculatePressureDueAltitude(altitude);
        AirDensity = calculateDensityDueAltitude(altitude, pressure, temperature);
        double speedVIAS = 0;
        speedVIAS = calculateSpeedDueAltitudeClimbing(altitude, matrix);
        double machNumber = calculateTrueMachNumber(aircraft, altitude, speedVIAS);
        double speedOfSound = calculateSpeedOfSoundDueAltitude(altitude);
        double TAS = calculateTrueAirSpeed(machNumber, speedOfSound);
        double finalWeight = calculateAircraftFinalWeight(aircraft);
        return (2 * finalWeight * 9.81) / (AirDensity * aircraft.getModel().getWingArea() * Math.pow(TAS, 2));
    }

    public static double calculateDragCoeficient(Aircraft aircraft, double altitude, double[][] matrix) {
        if (altitude == -1) {
            altitude = aircraft.getModel().getCruiseAltitude();
        }

        double liftcoeficient = calculateLiftCoeficient(aircraft, altitude, matrix);
        return aircraft.getModel().getCdragRegister().getCDrag(0).getcDrag0() + (Math.pow(liftcoeficient, 2) / (Math.PI * aircraft.getModel().getAspectRatio() * aircraft.getModel().getE()));
        //Cdrag 0 varia consoante a velocidade, falta depois compor, no excel o drag é sempre o mesmo
    }

//    public static double calculateRangeEachSegment(Aircraft aircraft, Segment segment, double altitude, double[][] matrix) {
//        if (altitude == -1) {
//            altitude = aircraft.getModel().getCruiseAltitude();
//        }
//
//        double distance = calculateSegmentDistance(aircraft, segment);
//
//        double time = calculateTravelTimeInASegment(aircraft, segment);
//
//        double fuelComsuption = time * aircraft.getModel().getTSFC(); //(Consumo no segment)
//
//        //Falta calcular o consumo nesta distancia à velocidade cruseiro
//        return (aircraft.getModel().getCruiseSpeed() / aircraft.getModel().getTSFC())
//                * (calculateLiftCoeficient(aircraft, altitude, matrix) / calculateDragCoeficient(aircraft, altitude, matrix)
//                * Math.log(aircraft.getModel().getEmptyWeight() / calculateAircraftFinalWeight(aircraft)));
//    }
    public static double calculateTravelTimeInASegment2(Aircraft aircraft, Segment segment, double altitude, double[][] matrix) {

        double speedVIAS = calculateSpeedDueAltitudeClimbing(altitude, matrix);
        double speedOfSound = calculateSpeedOfSoundDueAltitude(altitude);
        double tMach = calculateTrueMachNumber(aircraft, altitude, speedVIAS);
        double speed = calculateTrueAirSpeed(tMach, speedOfSound);

        double time = calculateSegmentDistance(aircraft, segment) / speed;

        return time;
    }

    public static double calculateSegmentDistance(Aircraft aircraft, Segment segment) {

        double latitude1 = segment.getBeginningNode().getLatitude();
        double longitude1 = segment.getBeginningNode().getLongitude();

        double latitude2 = segment.getEndNode().getLatitude();
        double longitude2 = segment.getEndNode().getLongitude();

        double a = Math.toRadians(latitude2 - latitude1);
        double b = Math.toRadians(longitude2 - longitude1);

        double a1 = Math.sin(a / 2) * Math.sin(a / 2)
                + Math.cos(Math.toRadians(latitude1)) * Math.cos(Math.toRadians(latitude2))
                * Math.sin(b / 2) * Math.sin(b / 2);

        double c = 2 * Math.atan2(Math.sqrt(a1), Math.sqrt(1 - a1));
        double r = 6371000;

        return r * c;
    }

    public static double calculateFuelComsuptionEachSegment(Aircraft aircraft, Segment segment, double[][] matrix, double altitude) {

        double time = calculateTravelTimeInASegment2(aircraft, segment, altitude, matrix);
        double speedVIAS = calculateSpeedDueAltitudeClimbing(altitude, matrix);
        double tMach = calculateTrueMachNumber(aircraft, altitude, speedVIAS);
        double thrust = calculateThrust(aircraft, altitude, tMach);
        double totalThrust = calculateTotalThrust(aircraft, thrust);
        double fuelComsumptionSegment = calculatedWdT(aircraft, time, totalThrust);

        return fuelComsumptionSegment;
    }

    public static void setsToAircraftValues(Aircraft aircraft) {

    }

    //__________________________________________Trip related formulas_______________________________________________
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
        double thrustSeaLevelMach0 = aircraft.getModel().getThrust_0(); // em newtons
        double lambda = 1.87E+05;

        return (thrustSeaLevelMach0 - lambda * trueMachNumber) * Math.pow(AirDensity / 1.225, aircraft.getModel().getLapseRateFactor());

    }

    public static double calculateTotalThrust(Aircraft aircraft, double thrust) {
        return aircraft.getModel().getNumberMotors() * thrust;
    }

    public static double calculateTrueAirSpeed(double trueMachNumber, double speedOfSound) {
        return trueMachNumber * speedOfSound; //valor em metros por segundo
    }

    public static double calculateAircraftClimbRate(Aircraft aircraft, double thrustTotal, double dragForce, double maxWeight, double trueAirSpeed) {

        return ((thrustTotal - dragForce) * trueAirSpeed / maxWeight / 9.81); // dividir por gravidade ou multiplicar?

    }

    public static double calculateFuelBurned(Aircraft aircraft, double totalthrust, double time) {

        return -totalthrust * time * aircraft.getModel().getTSFC() / 9.81;
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

    public static double calculateDistanceEach60SecAtCruiseAltitude(Aircraft aircraft, double speed) {

        return speed * 60;
    }

    public static double calculateAircraftFinalWeight(Aircraft aircraft) {
        //return (aircraft.getNumberElementsCrew() + aircraft.getNumberFirstClass() + aircraft.getNumberNormalClass()) * 195 + aircraft.getModel().getFuelCapacity() + aircraft.getModel().getEmptyWeight();
        double litersInKg = PhysicsConverters.litersToKgConverter(aircraft.getFuel());

        return aircraft.getCargo() + litersInKg
                + (aircraft.getNumberElementsCrew() + aircraft.getNumberFirstClass() + aircraft.getNumberNormalClass()) * 88.5
                + aircraft.getModel().getEmptyWeight()
                + aircraft.getModel().getMaxPayload();
    }

    public static double[] aircraftClimb(Aircraft aircraft, double[] valuesVec, Airport airport, Segment[] segments, double[][] matrix,
            double totalDistance, HashMap<Integer, double[]> ap, double[] vecAuxAux) {

        double dragForce = 0;
        double thrustAltitude = 0;
        double maxWeight = 0;
        double climbRate = 0;
        double altitude = airport.getLocation().getAltitude();
        double time = 1;
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
        double distanceTraveledInSegment = 0;
        double fuelConsumptionSegment = 0;
        double percAux;
        double i = valuesVec[5];
        int a = ((int) i);
        Segment segment = segments[a];
        double segmentDistance = calculateSegmentDistance(aircraft, segment);
        vecAuxAux[4] = 1;
        vecAuxAux[0] = trueAirSpeed;
        vecAuxAux[1] = altitude;
        vecAuxAux[2] = fuelConsumptionSegment;
        vecAuxAux[3] = aircraft.getFuel() - fuelConsumptionSegment;
        ap.put((int) vecAuxAux[4], vecAuxAux);
        vecAuxAux[4] = vecAuxAux[4] + 1;
        
        maxWeight = calculateAircraftFinalWeight(aircraft);
        do {

            speed = calculateSpeedDueAltitudeClimbing(altitude, matrix); //Speed Due Altitude
            trueMachNumber = calculateTrueMachNumber(aircraft, altitude, speed); //MachNumber
            speedOfSound = calculateSpeedOfSoundDueAltitude(altitude);
            trueAirSpeed = calculateTrueAirSpeed(trueMachNumber, speedOfSound);
            dragForce = calculateDragForceInASegment(aircraft, altitude, matrix);
            thrustAltitude = calculateThrust(aircraft, altitude, trueMachNumber);
            thrustAltitude = aircraft.getModel().getNumberMotors() * thrustAltitude;
            //maxWeight = calculateAircraftFinalWeight(aircraft);
            climbRate = calculateAircraftClimbRate(aircraft, thrustAltitude, dragForce, maxWeight, trueAirSpeed); //climbRate
            climbAngle = calculateClimbingAngle(trueAirSpeed, climbRate);
            distance = calculateDistanceTraveledWhileClimbing(trueAirSpeed, climbAngle, 60);
            fuelBurned = calculatedWdT(aircraft, 60, thrustAltitude);
            altitudeVariation = calculateAltitudeVariation(trueAirSpeed, thrustAltitude, dragForce, maxWeight);
            distanceTraveled = distanceTraveled + distance;
            distanceTraveledInSegment = distanceTraveledInSegment + distance;

            if (distanceTraveledInSegment > segmentDistance) {
                percAux = (distanceTraveled - segmentDistance) / distance;
                maxWeight = maxWeight - fuelBurned * percAux;
                totalFuelBurned = totalFuelBurned + fuelBurned * percAux;
                altitude = altitude + altitudeVariation * 60 * percAux;
                time = time + 60 * percAux;

                fuelConsumptionSegment = fuelConsumptionSegment + fuelConsumptionSegment * percAux;

                vecAuxAux[0] = trueAirSpeed;
                vecAuxAux[1] = altitude;
                vecAuxAux[2] = fuelConsumptionSegment;
                vecAuxAux[3] = aircraft.getFuel() - fuelConsumptionSegment;
                ap.put((int) vecAuxAux[4], vecAuxAux);
                vecAuxAux[4] = vecAuxAux[4] + 1;
                fuelConsumptionSegment = 0;
                if (segments[(int) i + 1] != null && i + 1 <= segments.length) {
                    segment = segments[(int) i + 1];
                    i = i + 1;
                    distanceTraveledInSegment = 0;
                }
            }
            maxWeight = maxWeight - fuelBurned;
            totalFuelBurned = totalFuelBurned + fuelBurned;
            altitude = altitude + altitudeVariation * 60;
            time = time + 60;

            if (distanceTraveled > (totalDistance - 120000)) {
                percAux = (distanceTraveled - (totalDistance - 120000)) / distanceTraveled;
                maxWeight = maxWeight - fuelBurned * percAux;
                totalFuelBurned = totalFuelBurned + fuelBurned * percAux;
                altitude = altitude + altitudeVariation * 60 * percAux;
                time = time + 60 * percAux;
                break;
            }
            if (altitudeVariation < 0.2) {
                break;
            }
        } while (distanceTraveled < totalDistance);

        valuesVec[0] = altitude;
        valuesVec[1] = maxWeight;
        valuesVec[2] = distanceTraveled;
        valuesVec[3] = totalFuelBurned;
        valuesVec[4] = time;
        valuesVec[5] = i;
        valuesVec[6] = distanceTraveledInSegment;
        valuesVec[8] = fuelConsumptionSegment;

        return valuesVec;
    }

    public static double[] aircraftDescent(Aircraft aircraft, double[] valuesVec, Airport airport, Segment[] segments, double[][] matrix,
            HashMap<Integer, double[]> ap, double[] vecAuxAux) {

        double altitudeAirport = airport.getLocation().getAltitude();

        double dragForce = 0;
        double thrustAltitude = 0;
        double maxWeight = valuesVec[1];
        double descentRate = 0;
        double altitude = valuesVec[0];
        double time = valuesVec[4];
        double fuelBurned = 0;
        double totalFuelBurned = valuesVec[3];
        double distanceTraveled = valuesVec[2];
        double distance = 0;
        double distanceDescending = 0;
        double altitudeVariation = 0;
        double altitudeAux = 0;
        double speed = 0;
        double trueMachNumber = 0;
        double trueAirSpeed = 0;
        double speedOfSound = 0;
        double descentAngle = 0;
        double percAux = 0;
        double fuelConsumptionSegment = valuesVec[8];
        double distanceTraveledInSegment = valuesVec[6];
        double i = valuesVec[5];
        Segment segment = segments[(int) i];
        double segmentDistance = calculateSegmentDistance(aircraft, segment);

        do {

            speed = calculateSpeedDueAltitudeClimbing(altitude, matrix); //Speed Due Altitude
            trueMachNumber = calculateTrueMachNumber(aircraft, altitude, speed); //MachNumber
            speedOfSound = calculateSpeedOfSoundDueAltitude(altitude);
            trueAirSpeed = calculateTrueAirSpeed(trueMachNumber, speedOfSound);
            dragForce = calculateDragForceInASegment(aircraft, altitude, matrix);
            thrustAltitude = calculateThrust(aircraft, altitude, trueMachNumber);
            thrustAltitude = 0.1 * aircraft.getModel().getNumberMotors() * thrustAltitude; //thrust quando está a descer
            descentRate = calculateAircraftClimbRate(aircraft, thrustAltitude, dragForce, maxWeight, trueAirSpeed); //climbRate
            descentAngle = calculateClimbingAngle(trueAirSpeed, descentRate);
            distance = calculateDistanceTraveledWhileClimbing(trueAirSpeed, descentAngle, 60);

            if (distanceTraveledInSegment > segmentDistance) {
                percAux = (distanceTraveled - segmentDistance) / distance;
                maxWeight = maxWeight - fuelBurned * percAux;
                totalFuelBurned = totalFuelBurned + fuelBurned * percAux;
                altitude = altitude + altitudeVariation * 60 * percAux;

                fuelConsumptionSegment = fuelConsumptionSegment + fuelConsumptionSegment * percAux;

                vecAuxAux[0] = trueAirSpeed;
                vecAuxAux[1] = altitude;
                vecAuxAux[2] = fuelConsumptionSegment;
                vecAuxAux[3] = aircraft.getFuel() - fuelConsumptionSegment;
                ap.put((int) vecAuxAux[4], vecAuxAux);
                vecAuxAux[4] = vecAuxAux[4] + 1;
                fuelConsumptionSegment = 0;

                time = time + 60 * percAux;
                if (segments[(int) i + 1] != null && i + 1 <= segments.length) {
                    segment = segments[(int) i + 1];
                    i = i + 1;
                    distanceTraveledInSegment = 0;
                }
            }

            fuelBurned = calculatedWdT(aircraft, 60, thrustAltitude);
            altitudeVariation = calculateAltitudeVariation(trueAirSpeed, thrustAltitude, dragForce, maxWeight);
            altitudeAux = altitude;
            altitude = altitude + altitudeVariation * 60;
            fuelConsumptionSegment = fuelConsumptionSegment + fuelBurned;

            if (altitude < altitudeAirport) {
                percAux = -((altitudeAux - altitudeAirport) / (altitudeVariation * 60));
                altitude = altitudeAux - (-(altitudeVariation * 60)) * percAux;
                maxWeight = maxWeight - fuelBurned * percAux;
                totalFuelBurned = totalFuelBurned + fuelBurned * percAux;
                distanceTraveled = distanceTraveled + distance * percAux;
                time = time + 60 * percAux;
                break;
            }
            maxWeight = maxWeight - fuelBurned;
            totalFuelBurned = totalFuelBurned + fuelBurned;
            distanceDescending = distanceDescending + distance;
            distanceTraveled = distanceTraveled + distance;
            time = time + 60;
            distanceTraveledInSegment = distanceTraveledInSegment + distance;

        } while (altitude > altitudeAirport);

        vecAuxAux[0] = trueAirSpeed;
        vecAuxAux[1] = altitude;
        vecAuxAux[2] = fuelConsumptionSegment;
        vecAuxAux[3] = aircraft.getFuel() - fuelConsumptionSegment;
        ap.put((int) vecAuxAux[4], vecAuxAux);

        valuesVec[0] = altitude;
        valuesVec[1] = maxWeight;
        valuesVec[2] = distanceTraveled;
        valuesVec[3] = totalFuelBurned;
        valuesVec[4] = time;
        valuesVec[5] = i;
        valuesVec[6] = distanceTraveledInSegment + distanceDescending;
        valuesVec[7] = distanceDescending;
        valuesVec[8] = fuelConsumptionSegment;

        return valuesVec;

    }

    public static double[] aircraftDistanceToDescent(Aircraft aircraft, double[] valuesVec, double totalDist, double[] valuesVecAux, Segment[] segments, double[][] matrix) {

        double dragForce = 0;
        double thrustAltitude = 0;
        double maxWeight = valuesVec[1];
        double altitude = valuesVec[0];
        double time = valuesVec[4];
        double fuelBurned = 0;
        double totalFuelBurned = valuesVec[3];
        double distanceTraveled = valuesVec[2];
        double distanceTraveledInSegment = valuesVec[6];
        double speed = 0;
        double trueMachNumber = 0;
        double trueAirSpeed = 0;
        double speedOfSound = 0;
        double i = valuesVec[5];
        Segment segment = segments[(int) i];
        double segmentDistance = calculateSegmentDistance(aircraft, segment);
        double percAux;

        maxWeight = calculateAircraftFinalWeight(aircraft);
        speed = calculateSpeedDueAltitudeClimbing(altitude, matrix); //Speed Due Altitude
        trueMachNumber = calculateTrueMachNumber(aircraft, altitude, speed); //MachNumber
        speedOfSound = calculateSpeedOfSoundDueAltitude(altitude);
        trueAirSpeed = calculateTrueAirSpeed(trueMachNumber, speedOfSound);
        dragForce = calculateDragForceInASegment(aircraft, altitude, matrix);
        thrustAltitude = calculateThrust(aircraft, altitude, trueMachNumber);
        thrustAltitude = aircraft.getModel().getNumberMotors() * thrustAltitude;

        double distanceTraveledEach60secs = calculateDistanceEach60SecAtCruiseAltitude(aircraft, trueAirSpeed);

        while (distanceTraveled < (totalDist - 120000)) {

            fuelBurned = calculatedWdT(aircraft, 60, thrustAltitude);
            distanceTraveledInSegment = distanceTraveledInSegment + distanceTraveledEach60secs;
            if (distanceTraveledInSegment > segmentDistance) {
                percAux = (distanceTraveledInSegment - segmentDistance) / distanceTraveledEach60secs;
                maxWeight = maxWeight - fuelBurned * percAux;
                totalFuelBurned = totalFuelBurned + fuelBurned * percAux;
                distanceTraveled = distanceTraveled + distanceTraveledEach60secs * percAux;
                distanceTraveledInSegment = distanceTraveledInSegment + distanceTraveledEach60secs * percAux;
                if (segments[(int) i + 1] != null && i + 1 <= segments.length) {
                    segment = segments[(int) i + 1];
                    i = i + 1;
                }
                distanceTraveledInSegment = 0;
                segmentDistance = calculateSegmentDistance(aircraft, segment);
            }
            maxWeight = maxWeight - fuelBurned;
            totalFuelBurned = totalFuelBurned + fuelBurned;
            distanceTraveled = distanceTraveled + distanceTraveledEach60secs;
            time = time + 60;

            if (distanceTraveled < (totalDist - 120000)) {
                break;
            }

        }

        valuesVecAux[0] = altitude;
        valuesVecAux[1] = maxWeight;
        valuesVecAux[2] = distanceTraveled;
        valuesVecAux[3] = totalFuelBurned;
        valuesVecAux[4] = time;
        valuesVecAux[5] = i;
        valuesVecAux[6] = distanceTraveledInSegment;

        return valuesVecAux;

    }

    public static double[] aircraftCruiseAltitudeCalculations(Aircraft aircraft, double[] valuesVec, double totalDist, double distanceToDescend, Segment[] segments, double[][] matrix,
            HashMap<Integer, double[]> ap, double[] vecAuxAux) {

        double dragForce = 0;
        double thrustAltitude = 0;
        double maxWeight = valuesVec[1];
        double altitude = valuesVec[0];
        double time = valuesVec[4];
        double fuelBurned = 0;
        double totalFuelBurned = valuesVec[3];
        double distanceTraveled = valuesVec[2];
        double distanceTraveledInSegment = valuesVec[6];
        double totalDistance = totalDist;
        double speed = 0;
        double trueMachNumber = 0;
        double trueAirSpeed = 0;
        double speedOfSound = 0;
        double percAux;
        double fuelConsumptionSegment = valuesVec[8];
        double i = valuesVec[5];
        Segment segment = segments[(int) i];
        double segmentDistance = calculateSegmentDistance(aircraft, segment);

        maxWeight = calculateAircraftFinalWeight(aircraft);
        speed = calculateSpeedDueAltitudeClimbing(altitude, matrix); //Speed Due Altitude
        trueMachNumber = calculateTrueMachNumber(aircraft, altitude, speed); //MachNumber
        speedOfSound = calculateSpeedOfSoundDueAltitude(altitude);
        trueAirSpeed = calculateTrueAirSpeed(trueMachNumber, speedOfSound);
        dragForce = calculateDragForceInASegment(aircraft, altitude, matrix);
        thrustAltitude = calculateThrust(aircraft, altitude, trueMachNumber);
        thrustAltitude = aircraft.getModel().getNumberMotors() * thrustAltitude;

        double distanceTraveledEach60secs = calculateDistanceEach60SecAtCruiseAltitude(aircraft, trueAirSpeed);

        while (distanceTraveled < (totalDistance - distanceToDescend)) {//altitude <= aircraft.getModetitude()) {

            fuelBurned = calculatedWdT(aircraft, 60, thrustAltitude);
            distanceTraveledInSegment = distanceTraveledInSegment + distanceTraveledEach60secs;

            if (distanceTraveledInSegment > segmentDistance) {
                percAux = (distanceTraveledInSegment - segmentDistance) / distanceTraveledEach60secs;
                maxWeight = maxWeight - fuelBurned * percAux;
                totalFuelBurned = totalFuelBurned + fuelBurned * percAux;
                distanceTraveled = distanceTraveled + distanceTraveledEach60secs * percAux;
                distanceTraveledInSegment = distanceTraveledInSegment + distanceTraveledEach60secs * percAux;

                fuelConsumptionSegment = fuelConsumptionSegment + fuelConsumptionSegment * percAux;

                vecAuxAux[0] = trueAirSpeed;
                vecAuxAux[1] = altitude;
                vecAuxAux[3] = fuelConsumptionSegment;
                vecAuxAux[4] = aircraft.getFuel() - fuelConsumptionSegment;
                ap.put((int) vecAuxAux[5], vecAuxAux);
                vecAuxAux[5] = vecAuxAux[5] + 1;
                fuelConsumptionSegment = 0;

                if (segments[(int) i + 1] != null && i + 1 <= segments.length) {
                    segment = segments[(int) i + 1];
                    i = i + 1;
                }
                distanceTraveledInSegment = 0;
                segmentDistance = calculateSegmentDistance(aircraft, segment);
            }
            fuelConsumptionSegment = fuelConsumptionSegment + fuelBurned;
            maxWeight = maxWeight - fuelBurned;
            totalFuelBurned = totalFuelBurned + fuelBurned;
            distanceTraveled = distanceTraveled + distanceTraveledEach60secs;
            time = time + 60;

            if (distanceTraveled > (totalDistance - distanceToDescend)) {
                break;
            }

        }

        valuesVec[0] = altitude;
        valuesVec[1] = maxWeight;
        valuesVec[2] = distanceTraveled;
        valuesVec[3] = totalFuelBurned;
        valuesVec[4] = time;
        valuesVec[5] = i;
        valuesVec[6] = distanceTraveledInSegment;
        valuesVec[8] = fuelConsumptionSegment;

        return valuesVec;

    }

    public static double[] allFlightCalculations(Aircraft aircraft, Airport initialAirport, Airport endAirport, double dist, Segment[] segments, double[][] matrix, HashMap<Integer, double[]> ap) {

        double[] valuesVec = new double[9];
        double[] valuesVecAux = new double[9];
        valuesVec[5] = 0;
        double totalDistance = 0;

        for (int i = 0; i <= segments.length; i++) {
            if (segments[i] != null) {
                totalDistance = totalDistance + calculateSegmentDistance(aircraft, segments[i]);
            } else {
                break;
            }
        }

        HashMap<Integer, double[]> apAux = new HashMap<>();
        double[] vecAuxAux = new double[5];
        for (int i = 0; i < vecAuxAux.length; i++) {
            vecAuxAux[i] = 0;
        }

        double[] vecAuxAuxAux = new double[6];

        valuesVec = aircraftClimb(aircraft, valuesVec, initialAirport, segments, matrix, totalDistance, ap, vecAuxAux);
        valuesVecAux = aircraftDistanceToDescent(aircraft, valuesVec, totalDistance, valuesVecAux, segments, matrix); //este método é para saber os valores do vec até 120km antes do aeropor(valor dos slides para quando aviao costuma começar a descer)
        valuesVecAux = aircraftDescent(aircraft, valuesVecAux, endAirport, segments, matrix, apAux, vecAuxAuxAux);
        valuesVec = aircraftCruiseAltitudeCalculations(aircraft, valuesVec, totalDistance, valuesVecAux[7], segments, matrix, ap, vecAuxAux);
        valuesVec = aircraftDescent(aircraft, valuesVec, endAirport, segments, matrix, ap, vecAuxAux);

        return valuesVec;
    }

    public static double[] aircraftClimb3(Aircraft aircraft, double[] valuesVec, Airport airport, double[][] matrix) {

        double dragForce = 0;
        double thrustAltitude = 0;
        double maxWeight = 0;
        double climbRate = 0;
        double altitude = airport.getLocation().getAltitude();
        double time = 1;
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
        double distanceTraveledInSegment = 0;
        double percAux;
        double i = valuesVec[5];
        double segmentDistance = Double.MAX_VALUE;

        do {

            speed = calculateSpeedDueAltitudeClimbing(altitude, matrix); //Speed Due Altitude
            trueMachNumber = calculateTrueMachNumber(aircraft, altitude, speed); //MachNumber
            speedOfSound = calculateSpeedOfSoundDueAltitude(altitude);
            trueAirSpeed = calculateTrueAirSpeed(trueMachNumber, speedOfSound);
            dragForce = calculateDragForceInASegment(aircraft, altitude, matrix);
            thrustAltitude = calculateThrust(aircraft, altitude, trueMachNumber);
            thrustAltitude = aircraft.getModel().getNumberMotors() * thrustAltitude;
            maxWeight = calculateAircraftFinalWeight(aircraft);
            climbRate = calculateAircraftClimbRate(aircraft, thrustAltitude, dragForce, maxWeight, trueAirSpeed); //climbRate
            climbAngle = calculateClimbingAngle(trueAirSpeed, climbRate);
            distance = calculateDistanceTraveledWhileClimbing(trueAirSpeed, climbAngle, 60);
            fuelBurned = calculatedWdT(aircraft, 60, thrustAltitude);
            altitudeVariation = calculateAltitudeVariation(trueAirSpeed, thrustAltitude, dragForce, maxWeight);
            distanceTraveled = distanceTraveled + distance;
            distanceTraveledInSegment = distanceTraveledInSegment + distance;

            maxWeight = maxWeight - fuelBurned;
            totalFuelBurned = totalFuelBurned + fuelBurned;
            altitude = altitude + altitudeVariation * 60;
            time = time + 60;

            if (altitudeVariation < 0.2) {
                break;
            }
        } while (altitudeVariation > 0.2);

        valuesVec[0] = altitude;
        valuesVec[1] = maxWeight;
        valuesVec[2] = distanceTraveled;
        valuesVec[3] = totalFuelBurned;
        valuesVec[4] = time;
        valuesVec[5] = i;
        valuesVec[6] = distanceTraveledInSegment;

        return valuesVec;
    }

    public static double[] aircraftDescent3(Aircraft aircraft, double[] valuesVec, Airport airport, double[][] matrix) {

        double altitudeAirport = airport.getLocation().getAltitude();

        double dragForce = 0;
        double thrustAltitude = 0;
        double maxWeight = valuesVec[1];
        double descentRate = 0;
        double altitude = valuesVec[0];
        double time = valuesVec[4];
        double fuelBurned = 0;
        double totalFuelBurned = valuesVec[3];
        double distanceTraveled = valuesVec[2];
        double distance = 0;
        double distanceDescending = 0;
        double altitudeVariation = 0;
        double altitudeAux = 0;
        double speed = 0;
        double trueMachNumber = 0;
        double trueAirSpeed = 0;
        double speedOfSound = 0;
        double descentAngle = 0;
        double percAux = 0;

        do {

            speed = calculateSpeedDueAltitudeClimbing(altitude, matrix); //Speed Due Altitude
            trueMachNumber = calculateTrueMachNumber(aircraft, altitude, speed); //MachNumber
            speedOfSound = calculateSpeedOfSoundDueAltitude(altitude);
            trueAirSpeed = calculateTrueAirSpeed(trueMachNumber, speedOfSound);
            dragForce = calculateDragForceInASegment(aircraft, altitude, matrix);
            thrustAltitude = calculateThrust(aircraft, altitude, trueMachNumber);
            thrustAltitude = 0.1 * aircraft.getModel().getNumberMotors() * thrustAltitude; //thrust quando está a descer
            descentRate = calculateAircraftClimbRate(aircraft, thrustAltitude, dragForce, maxWeight, trueAirSpeed); //climbRate
            descentAngle = calculateClimbingAngle(trueAirSpeed, descentRate);
            distance = calculateDistanceTraveledWhileClimbing(trueAirSpeed, descentAngle, 60);

            fuelBurned = calculatedWdT(aircraft, 60, thrustAltitude);
            altitudeVariation = calculateAltitudeVariation(trueAirSpeed, thrustAltitude, dragForce, maxWeight);
            altitudeAux = altitude;
            altitude = altitude + altitudeVariation * 60;

            if (altitude < altitudeAirport) {
                percAux = -((altitudeAux - altitudeAirport) / (altitudeVariation * 60));
                altitude = altitudeAux - (-(altitudeVariation * 60)) * percAux;
                maxWeight = maxWeight - fuelBurned * percAux;
                totalFuelBurned = totalFuelBurned + fuelBurned * percAux;
                distanceTraveled = distanceTraveled + distance * percAux;
                time = time + 60 * percAux;
                break;
            }
            maxWeight = maxWeight - fuelBurned;
            totalFuelBurned = totalFuelBurned + fuelBurned;
            distanceDescending = distanceDescending + distance;
            distanceTraveled = distanceTraveled + distance;
            time = time + 60;

            if (altitude < altitudeAirport) {
                break;
            }

        } while (altitude > altitudeAirport);

        valuesVec[0] = altitude;
        valuesVec[1] = maxWeight;
        valuesVec[2] = distanceTraveled;
        valuesVec[3] = totalFuelBurned;
        valuesVec[4] = time;
        valuesVec[7] = distanceDescending;

        return valuesVec;
    }

    public static boolean checkReserve(Aircraft aircraft, double flightTime, double altitude, double speed, double fuelBurned) {

        double tMach = calculateTrueMachNumber(aircraft, altitude, speed);
        double thrust = calculateThrust(aircraft, altitude, tMach);
        double totalThrust = calculateTotalThrust(aircraft, thrust);
        double fuelConsumption30 = calculatedWdT(aircraft, 30, totalThrust);
        double fuelConsumption60 = calculatedWdT(aircraft, 60, totalThrust);

        if (flightTime < 10800) {
            if ((aircraft.getFuel() - (fuelConsumption30 + fuelBurned)) > 0) {
                return true;
            } else {
                return false;
            }
        } else if ((aircraft.getFuel() - (fuelConsumption60 + fuelBurned)) > 0) {
            return true;
        } else {
            return false;
        }

    }
}
