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

    public static void calculateSpeedDueAltitudeClimbing(Aircraft aircraft, double altitude, double speed) {
        if (altitude > 0 && altitude <= 1000) {
            speed = 210 * 1.852;
        }
        if (altitude > 1000 && altitude <= 2000) {
            speed = 210 * 1.852;
        }
        if (altitude > 2000 && altitude <= 3000) {
            speed = 220 * 1.852;
        }
        if (altitude > 3000 && altitude <= 4000) {
            speed = 230 * 1.852;
        }
        if (altitude > 4000 && altitude <= 5000) {
            speed = 250 * 1.852;
        }
        if (altitude > 5000 && altitude <= 6000) {
            speed = 260 * 1.852;
        }
        if (altitude > 6000 && altitude <= 7000) {
            speed = 290 * 1.852;
        }
        if (altitude > 7000 && altitude <= 8000) {
            speed = 290 * 1.852;
        }
        if (altitude > 8000 && altitude <= 9000) {
            speed = 290 * 1.852;
        }
        if (altitude > 9000 && altitude <= 10000) {
            speed = 290 * 1.852;
        }
        if (altitude > 10000 && altitude <= 11000) {
            speed = 290 * 1.852;
        }
        if (altitude > 11000 && altitude <= 12000) {
            speed = 300 * 1.852;
        }
        if (altitude > 12000 && altitude <= 13000) {
            speed = 300 * 1.852;
        }
        if (altitude > 13000 && altitude <= 14000) {
            speed = 300 * 1.852;
        }
        if (altitude > 14000) {
            speed = 300 * 1.852;
        }

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
        double t = 0;
        calculateAirDensityTemperatureDueAltitude(altitude, 0, t);
        t = t + 273.15;
        double gamma = 1.4;
        double universalGasConstant = 287;
        return Math.sqrt(gamma * universalGasConstant * t);
    }

    public static double calculatePressureDueAltitude(double altitude) {

        return 101325 * Math.pow(1 - 0.0065 * altitude / 288.2, 5.2561); //288.2-Temp kelvin sea level
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
        AirDensity = calculateDensityDueAltitude(altitude, pressure, temperature);

        //calculateAirDensityTemperatureDueAltitude(altitude, AirDensity, 0);
        return aircraft.getModel().getCdragRegister().getCDrag(0).getcDrag0() * (AirDensity * Math.pow(aircraft.getModel().getCruiseSpeed(), 2) / 2)
                * aircraft.getModel().getWingArea();
    }

    public static double calculateLiftCoeficient(Aircraft aircraft, Segment segment, double altitude) {
        double AirDensity = 0;
        if (altitude == -1) {
            altitude = aircraft.getModel().getCruiseAltitude();
        }
        double temperature = calculateTemperatureDueAltitude(altitude);
        double pressure = calculatePressureDueAltitude(altitude);
        AirDensity = calculateDensityDueAltitude(altitude, pressure, temperature);
        //calculateAirDensityTemperatureDueAltitude(altitude, AirDensity, 0);

        return (2 * calculateAircraftFinalWeight(aircraft) * 9.8) / (AirDensity * aircraft.getModel().getWingArea() * Math.pow(aircraft.getModel().getCruiseSpeed(), 2));
    }

    public static double calculateDragCoeficient(Aircraft aircraft, Segment segment, Double altitude) {
        double AirDensity = 0;
        if (altitude == -1) {
            altitude = aircraft.getModel().getCruiseAltitude();
        }
        double temperature = calculateTemperatureDueAltitude(altitude);
        double pressure = calculatePressureDueAltitude(altitude);
        AirDensity = calculateDensityDueAltitude(altitude, pressure, temperature);

        //calculateAirDensityTemperatureDueAltitude(altitude, AirDensity, 0);
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

    public static void setsToAircraftValues(Aircraft aircraft) {

    }
    
    
    
    
    
    
    //__________________________________________Trip related formulas_______________________________________________
    
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

    public static double calculateTrueMachNumber(Aircraft aircraft, double altitude, double speedVIAS) {
        double p = 0;
        calculateAirDensityTemperatureDueAltitude(altitude, p, 0);
        double a = Math.pow((1 + 0.2 * Math.pow((speedVIAS / 661.5), 2)), 3.5) - 1;
        return Math.sqrt(5 * (Math.pow((12.25 / p) * (a) + 1, 0.286) - 1));
    }

    public static double calculateThrust(Aircraft aircraft, double altitude, double trueMachNumber) {
        double p = 0;
        double t = 0;
        calculateAirDensityTemperatureDueAltitude(altitude, p, t);
        double thrustSeaLevelMach0 = 338000; // em newtons
        double thrustChangeRate = (thrustSeaLevelMach0 - 180000) / 0.9; //duvida no Lapse Rate

        return thrustSeaLevelMach0 - thrustChangeRate * trueMachNumber;

    }

    public static double calculateThrustAltitude(Aircraft aircraft, Segment segment, double altitude, double trueMachNumber) {
        double AirDensity = 0;
        calculateAirDensityTemperatureDueAltitude(altitude, AirDensity, 0);
        double thrust = calculateThrust(aircraft, altitude, trueMachNumber);
        return thrust * Math.pow((AirDensity / 12.25), 0.96); //0.96, valor dado pelo prof

    }

    public static double calculateTrueAirSpeed(double trueMachNumber, double speedOfSound) {
        return trueMachNumber * speedOfSound;
    }

    public static double calculateAircraftClimbRate(Aircraft aircraft, Segment segment, double thrustTotal, double dragForce, double maxWeight, double trueAirSpeed) {

        return ((thrustTotal - dragForce) * trueAirSpeed / maxWeight / 9.81); // dividir por gravidade ou multiplicar?

    }

    public static double calculateFuelBurned(Aircraft aircraft, double dragForce) {

        return -aircraft.getModel().getTSFC() * dragForce;
    }

    public static double calculateAltitudeVariation(double speed, double thrustAltitude, double dragForce, double SpeedVariationWithTime, double maxWeight) {

        return (((thrustAltitude - dragForce) * speed / maxWeight * 9.81) - (speed / 9.81) * SpeedVariationWithTime);
    }

    public static double calculateClimbingAngle(double trueAirSpeed, double climbRate) {

        return Math.sin(climbRate / trueAirSpeed);
    }

    public static double calculatedWdT(Aircraft aircraft, double time, double totalThrust) {

        return totalThrust * time * aircraft.getModel().getTSFC() / 9.81;
    }

    public static double calculateDistanceTraveledWhileClimbing(double trueAirspeed, double climbAngle, double time) {

        return trueAirspeed * Math.cos(climbAngle) * time;
    }
    
    public static void aircraftClimb(Aircraft aircraft, Segment segment) {

        double liftForce = 0;
        double dragForce = 0;
        double thrustAltitude = 0;
        double maxWeight = 0;
        double climbRate = 0;
        double altitude = 0;
        double time = 0;
        double fuelBurned = 0;
        double distanceTraveled = 0;
        double distance = 0;
        double altitudeVariation = 0;
        double speed = 0;
        double speedVariationWithTime = 0;
        double trueMachNumber = 0;
        double trueAirSpeed = 0;
        double speedOfSound = 0;
        double climbAngle = 0;
        double dWdT = 0;

        while (altitude <= aircraft.getModel().getCruiseAltitude()) {

            calculateSpeedDueAltitudeClimbing(aircraft, altitude, speed); //Speed Due Altitude
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
            dWdT = calculatedWdT(aircraft, time, altitude);
            distance = calculateDistanceTraveledWhileClimbing(trueAirSpeed, climbAngle, time);
            fuelBurned = calculateFuelBurned(aircraft, dragForce); //fuel burned ou o dw/dt ? é o mesmo?
            altitudeVariation = calculateAltitudeVariation(speed, thrustAltitude, dragForce, speedVariationWithTime, maxWeight);
            maxWeight = maxWeight + fuelBurned; //fuelBurned já está negativo
            altitude = altitude + altitudeVariation;
            distanceTraveled = distanceTraveled + distance;
            time = time + 120;

            //duvida em como calcular a distancia, e a parte do TrueAirSpeed e descolagem(inicio dos slides)
            //duvida em relação à velocidade do aviao, consoante a altitude(preciso de alterá-la consoante a altitude)
        }
    }
    
    
    
    
    
    
    //__________________________________________________Converters_______________________________________________
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

    public static double temperatudeConverterKelvinToCelsius(double temperature) {

        return temperature - 273.15;
    }
}
