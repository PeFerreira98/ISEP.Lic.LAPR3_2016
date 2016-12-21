/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

import lapr.project.model.network.Segment;
import lapr.project.model.register.AircraftModelRegister;
import java.lang.Math;
import java.util.Deque;
import lapr.project.model.graph.Edge;
import lapr.project.model.graph.Graph;
import lapr.project.model.graph.Vertex;
import lapr.project.model.network.Node;

/**
 *
 * @author João
 */
public class Physics {

    private AircraftModelRegister AircraftReg;

    public static double calculateLiftForceInASegment(Aircraft aircraft, Segment segment) {
        double AirDensity;
        //if (segment.getAltitudes_slots()<1000.0)
        AirDensity = 0.5;
        double AreaAircraft = 30;
        return calculateLiftCoeficient(aircraft, segment) * (AirDensity * Math.pow(aircraft.getModel().getRegimeRegister().getRegime("cruise").getSpeed(), 2) / 2) * AreaAircraft;
    }

    public static double calculateDragForceInASegment(Aircraft aircraft, Segment segment) {
        double AirDensity = 0.5;  //segment.getAltitudeSlots().getAirDensity();
        double AreaAircraft = 30;

        return aircraft.getModel().getDragCoeficient() * (AirDensity * aircraft.getModel().getRegimeRegister().getRegime("cruise").getSpeed())
                / 2 * AreaAircraft;
    }

    public static double calculateLiftCoeficient(Aircraft aircraft, Segment segment) {
        double AirDensity = 0.5;  //segment.getAltitudeSlots().getAirDensity();
        double AreaAircraft = 30;
        return (2 * aircraft.getModel().getEmptyWeight() * 9.8) / (AirDensity * AreaAircraft * Math.pow(aircraft.getModel().getRegimeRegister().getRegime("cruise").getSpeed(), 2));
    }

    public static double calculateDragCoeficient(Aircraft aircraft, Segment segment) {
        double AirDensity = 0.5;  //segment.getAltitudeSlots().getAirDensity();
        double AreaAircraft = 30;
        return aircraft.getModel().getDragCoeficient() + (Math.pow(calculateLiftCoeficient(aircraft, segment), 2)) / (Math.PI * (Math.pow(aircraft.getModel().getWingSpan(), 2) / aircraft.getModel().getWingArea() * Math.exp(1)));
    }

    public static double calculateRangeEachSegment(Aircraft aircraft, Segment segment) {

        double distance = calculateSegmentDistance(aircraft, segment);

        double time = calculateTravelTimeInASegment(aircraft, segment);

        double fuelComsuption = time * aircraft.getModel().getRegimeRegister().getRegime("cruise").getTSFC(); //(Consumo no segment)

        //Falta calcular o consumo nesta distancia à velocidade cruseiro
        return (aircraft.getModel().getRegimeRegister().getRegime("cruise").getSpeed() / aircraft.getModel().getRegimeRegister().getRegime("cruise").getTSFC())
                * (calculateLiftCoeficient(aircraft, segment) / calculateDragCoeficient(aircraft, segment)
                * Math.log(aircraft.getModel().getEmptyWeight() / calculateAircraftFinalWeight(aircraft)));
    }
    
    public static double calculateTravelTimeInASegment(Aircraft aircraft, Segment segment){
        //System.out.println(segment);
        double distance = calculateSegmentDistance(aircraft, segment);
        //System.out.println(distance);
        return calculateSegmentDistanceInMiles(distance) / aircraft.getModel().getRegimeRegister().getRegime("Cruise").getSpeed(); //tempo(s)=distance(m)/speed(miles/sec?)
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
        double r = 6371000;

        return r * d;
    }

    public static double calculateSegmentDistanceInMiles(double distance) {
        return 0.62 * distance;
    }

    public static double calculateAircraftFinalWeight(Aircraft aircraft) {
        return (aircraft.getNumberElementsCrew() + aircraft.getNumberFirstClass() + aircraft.getNumberNormalClass()) * 195 + aircraft.getModel().getFuelCapacity();
    }
}
