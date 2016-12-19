/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

import java.util.Objects;

/**
 *
 * @author João
 */
public class AircraftModel {

    private Type type;
    private double numberMotors;
    private String motor;
    private MotorType motorType;
    private double emptyWeight;
    private double MTOW;
    private double MZFW;
    private double maxPayload;
    private double fuelCapacity;
    private double VMO;
    private double MMO;
    private double wingArea;
    private double wingSpan;
    private double dragCoeficient;
    private double e;
    
    public enum Type {
        PASSENGER,
        CARGO,
        MIXED;
    }
    public enum MotorType {
        TURBOPROP,
        TURBOFAN,
        TURBOJET,
        ELECTRICPROPELER;
    }

    public AircraftModel(Type type, double numberMotors, String motor, MotorType motorType, double emptyWeight, double MTOW, double MZFW, double maxPayload, double fuelCapacity, double VMO, double MMO, double wingArea, double wingSpan, double dragCoeficient, double e) {
        this.type = type;
        this.numberMotors = numberMotors;
        this.motor = motor;
        this.motorType = motorType;
        this.emptyWeight = emptyWeight;
        this.MTOW = MTOW;
        this.MZFW = MZFW;
        this.maxPayload = maxPayload;
        this.fuelCapacity = fuelCapacity;
        this.VMO = VMO;
        this.MMO = MMO;
        this.wingArea = wingArea;
        this.wingSpan = wingSpan;
        this.dragCoeficient = dragCoeficient;
        this.e = e;
    }

    public Type getType() {
        return type;
    }

    public double getNumberMotors() {
        return numberMotors;
    }

    public String getMotor() {
        return motor;
    }

    public MotorType getMotorType() {
        return motorType;
    }

    public double getEmptyWeight() {
        return emptyWeight;
    }

    public double getMTOW() {
        return MTOW;
    }

    public double getMZFW() {
        return MZFW;
    }

    public double getMaxPayload() {
        return maxPayload;
    }

    public double getFuelCapacity() {
        return fuelCapacity;
    }

    public double getVMO() {
        return VMO;
    }

    public double getMMO() {
        return MMO;
    }

    public double getWingArea() {
        return wingArea;
    }

    public double getWingSpan() {
        return wingSpan;
    }

    public double getDragCoeficient() {
        return dragCoeficient;
    }

    public double getE() {
        return e;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        
        final AircraftModel other = (AircraftModel) obj;
        return Objects.equals(this.motor, other.motor);
    }

    @Override
    public String toString() {
        return "AircraftModel{" + "type=" + type + ", numberMotors=" + numberMotors + ", motor=" + motor + ", motorType=" + motorType + ", emptyWeight=" + emptyWeight + ", MTOW=" + MTOW + ", MZFW=" + MZFW + ", maxPayload=" + maxPayload + ", fuelCapacity=" + fuelCapacity + ", VMO=" + VMO + ", MMO=" + MMO + ", wingArea=" + wingArea + ", wingSpan=" + wingSpan + ", dragCoeficient=" + dragCoeficient + ", e=" + e + '}';
    }
      
}
