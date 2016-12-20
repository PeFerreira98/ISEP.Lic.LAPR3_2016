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

    private AircraftModelRegister AircraftReg;

    public double calculateLiftForceInASegment(Aircraft aircraft, Segment segment) {
        double AirDensity;
        //if (segment.getAltitudes_slots()<1000.0)
        AirDensity = 0.5;
        double AreaAircraft = 30;
        return calculateLiftCoeficient(aircraft, segment) * (AirDensity * Math.pow(aircraft.getModel().getRegimeRegister().getRegime("cruise").getSpeed(), 2) / 2) * AreaAircraft;
    }

    public double calculateDragForceInASegment(Aircraft aircraft, Segment segment) {
        double AirDensity = 0.5;  //segment.getAltitudeSlots().getAirDensity();
        double AreaAircraft = 30;

        return aircraft.getModel().getDragCoeficient() * (AirDensity * aircraft.getModel().getRegimeRegister().getRegime("cruise").getSpeed())
                / 2 * AreaAircraft;
    }

    public double calculateLiftCoeficient(Aircraft aircraft, Segment segment) {
        double AirDensity = 0.5;  //segment.getAltitudeSlots().getAirDensity();
        double AreaAircraft = 30;
        return (2 * aircraft.getModel().getEmptyWeight() * 9.8) / (AirDensity * AreaAircraft * Math.pow(aircraft.getModel().getRegimeRegister().getRegime("cruise").getSpeed(), 2));
    }

    public double calculateDragCoeficient(Aircraft aircraft, Segment segment) {
        double AirDensity = 0.5;  //segment.getAltitudeSlots().getAirDensity();
        double AreaAircraft = 30;
        return aircraft.getModel().getDragCoeficient() + (Math.pow(calculateLiftCoeficient(aircraft, segment), 2)) / (Math.PI * (Math.pow(aircraft.getModel().getWingSpan(), 2) / aircraft.getModel().getWingArea() * Math.exp(1)));
    }

    public double calculateRangeEachSegment(Aircraft aircraft, Segment segment) {

        double latitude1 = segment.getBeginningNode().getLatitude();
        double longitude1 = segment.getBeginningNode().getLongitude();
        double latitude2 = segment.getEndNode().getLatitude();
        double longitude2 = segment.getEndNode().getLongitude();

        double a = Math.toRadians(latitude2 - latitude1);
        double b = Math.toRadians(longitude2 - longitude1);

        double c = Math.sin(a / 2) * Math.sin(b / 2) + Math.cos(latitude1) * Math.cos(latitude2) * Math.sin(b / 2) * Math.sin(b / 2);

        double d = 2 * Math.atan2(Math.sqrt(c), Math.sqrt(1 - c));
        double r = 6371000;
        double distancia = r * d;

        //Falta converter para miles e falta calcular o consumo nesta distancia à velocidade cruseiro
        return (aircraft.getModel().getRegimeRegister().getRegime("cruise").getSpeed() / aircraft.getModel().getRegimeRegister().getRegime("cruise").getTSFC())
                * (calculateLiftCoeficient(aircraft, segment) / calculateDragCoeficient(aircraft, segment)
                * Math.log(aircraft.getModel().getEmptyWeight() / calculateAircraftFinalWeight(aircraft)));
    }

    public double calculateAircraftFinalWeight(Aircraft aircraft) {
        return (aircraft.getNumberElementsCrew() + aircraft.getNumberFirstClass() + aircraft.getNumberNormalClass()) * 195 + aircraft.getModel().getFuelCapacity();
    }

}
