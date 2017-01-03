/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

import java.util.Objects;
import lapr.project.model.register.RegimeRegister;

/**
 *
 * @author João
 */
public class AircraftModel { //TODO: Delete marked stuff, add commented stuff

    private String id;
    private String description;
    private String maker;
    private Type type;
    
    private double numberMotors;
    private String motor;
    private MotorType motorType;
    
//    private double cruiseAltitude;
//    private double cruiseSpeed;
//    private double TSFC;
//    private double lapseRateFactor;
//    
//    private double thrust_0;
//    private double thrustMaxSpeed;
//    private double maxSpeed;
    
    private RegimeRegister regimeRegister; //TODO: Delete
    
    private double emptyWeight;
    private double MTOW;
    private double MZFW; //TODO: Delete
    private double maxPayload;
    private double fuelCapacity;
    private double VMO;
    private double MMO;
    private double wingArea;
    private double wingSpan;
    private double dragCoeficient; //TODO: Delete
//    private double aspectRatio;
    private double e;
    
    //    private CDragRegister cdragRegister;
    
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

    public AircraftModel(String id, String description, String maker, Type type, double numberMotors, String motor, MotorType motorType, RegimeRegister regimeRegister, double emptyWeight, double MTOW, double MZFW, double maxPayload, double fuelCapacity, double VMO, double MMO, double wingArea, double wingSpan, double dragCoeficient, double e) {
        this.id = id;
        this.description = description;
        this.maker = maker;
        this.type = type;
        this.numberMotors = numberMotors;
        this.motor = motor;
        this.motorType = motorType;
        this.regimeRegister = regimeRegister;
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

    public String getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public String getMaker() {
        return maker;
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

    public RegimeRegister getRegimeRegister() {
        return regimeRegister;
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
        return Objects.equals(this.id, other.id);
    }

    @Override
    public String toString() {
        return "AircraftModel{" + "id=" + id + ", description=" + description + ", maker=" + maker + ", type=" + type + ", numberMotors=" + numberMotors + ", motor=" + motor + ", motorType=" + motorType + ", emptyWeight=" + emptyWeight + ", MTOW=" + MTOW + ", MZFW=" + MZFW + ", maxPayload=" + maxPayload + ", fuelCapacity=" + fuelCapacity + ", VMO=" + VMO + ", MMO=" + MMO + ", wingArea=" + wingArea + ", wingSpan=" + wingSpan + ", dragCoeficient=" + dragCoeficient + ", e=" + e + ",\n regimeRegister=" + regimeRegister + '}';
    }
      
}
