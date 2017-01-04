/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

import java.util.Objects;
import lapr.project.model.register.CDragRegister;

/**
 *
 * @author João
 */
public class AircraftModel {

    private String id;
    private String description;
    private String maker;
    private Type type;
    
    private double numberMotors;
    private String motor;
    private MotorType motorType;
    
    private double cruiseAltitude;
    private double cruiseSpeed;
    private double TSFC;
    private double lapseRateFactor;
    
    private double thrust_0;
    private double thrustMaxSpeed;
    private double maxSpeed;
    
    private double emptyWeight;
    private double MTOW;
    private double maxPayload;
    private double fuelCapacity;
    private double VMO;
    private double MMO;
    private double wingArea;
    private double wingSpan;
    private double aspectRatio;
    private double e;
    
    private CDragRegister cdragRegister;
    
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

    
    public AircraftModel(String id, String description, String maker, Type type, double numberMotors, String motor, MotorType motorType, double cruiseAltitude, double cruiseSpeed, double TSFC, double lapseRateFactor, double thrust_0, double thrustMaxSpeed, double maxSpeed, double emptyWeight, double MTOW, double maxPayload, double fuelCapacity, double VMO, double MMO, double wingArea, double wingSpan, double aspectRatio, double e, CDragRegister cdragRegister) {
        this.id = id;
        this.description = description;
        this.maker = maker;
        this.type = type;
        this.numberMotors = numberMotors;
        this.motor = motor;
        this.motorType = motorType;
        this.cruiseAltitude = cruiseAltitude;
        this.cruiseSpeed = cruiseSpeed;
        this.TSFC = TSFC;
        this.lapseRateFactor = lapseRateFactor;
        this.thrust_0 = thrust_0;
        this.thrustMaxSpeed = thrustMaxSpeed;
        this.maxSpeed = maxSpeed;
        this.emptyWeight = emptyWeight;
        this.MTOW = MTOW;
        this.maxPayload = maxPayload;
        this.fuelCapacity = fuelCapacity;
        this.VMO = VMO;
        this.MMO = MMO;
        this.wingArea = wingArea;
        this.wingSpan = wingSpan;
        this.aspectRatio = aspectRatio;
        this.e = e;
        this.cdragRegister = cdragRegister;
    }

    public AircraftModel(String id, String description, String maker, Type type, double numberMotors, String motor, MotorType motorType, double cruiseAltitude, double cruiseSpeed, double TSFC, double lapseRateFactor, double thrust_0, double thrustMaxSpeed, double maxSpeed, double emptyWeight, double MTOW, double maxPayload, double fuelCapacity, double VMO, double MMO, double wingArea, double wingSpan, double aspectRatio, double e) {
        this.id = id;
        this.description = description;
        this.maker = maker;
        this.type = type;
        this.numberMotors = numberMotors;
        this.motor = motor;
        this.motorType = motorType;
        this.cruiseAltitude = cruiseAltitude;
        this.cruiseSpeed = cruiseSpeed;
        this.TSFC = TSFC;
        this.lapseRateFactor = lapseRateFactor;
        this.thrust_0 = thrust_0;
        this.thrustMaxSpeed = thrustMaxSpeed;
        this.maxSpeed = maxSpeed;
        this.emptyWeight = emptyWeight;
        this.MTOW = MTOW;
        this.maxPayload = maxPayload;
        this.fuelCapacity = fuelCapacity;
        this.VMO = VMO;
        this.MMO = MMO;
        this.wingArea = wingArea;
        this.wingSpan = wingSpan;
        this.aspectRatio = aspectRatio;
        this.e = e;
        this.cdragRegister = new CDragRegister();
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

    public double getCruiseAltitude() {
        return cruiseAltitude;
    }

    public double getCruiseSpeed() {
        return cruiseSpeed;
    }

    public double getTSFC() {
        return TSFC;
    }

    public double getLapseRateFactor() {
        return lapseRateFactor;
    }

    public double getThrust_0() {
        return thrust_0;
    }

    public double getThrustMaxSpeed() {
        return thrustMaxSpeed;
    }

    public double getMaxSpeed() {
        return maxSpeed;
    }

    public double getEmptyWeight() {
        return emptyWeight;
    }

    public double getMTOW() {
        return MTOW;
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

    public double getAspectRatio() {
        return aspectRatio;
    }

    public double getE() {
        return e;
    }

    public CDragRegister getCdragRegister() {
        return cdragRegister;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setMaker(String maker) {
        this.maker = maker;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public void setNumberMotors(double numberMotors) {
        this.numberMotors = numberMotors;
    }

    public void setMotor(String motor) {
        this.motor = motor;
    }

    public void setMotorType(MotorType motorType) {
        this.motorType = motorType;
    }

    public void setCruiseAltitude(double cruiseAltitude) {
        this.cruiseAltitude = cruiseAltitude;
    }

    public void setCruiseSpeed(double cruiseSpeed) {
        this.cruiseSpeed = cruiseSpeed;
    }

    public void setTSFC(double TSFC) {
        this.TSFC = TSFC;
    }

    public void setLapseRateFactor(double lapseRateFactor) {
        this.lapseRateFactor = lapseRateFactor;
    }

    public void setThrust_0(double thrust_0) {
        this.thrust_0 = thrust_0;
    }

    public void setThrustMaxSpeed(double thrustMaxSpeed) {
        this.thrustMaxSpeed = thrustMaxSpeed;
    }

    public void setMaxSpeed(double maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    public void setEmptyWeight(double emptyWeight) {
        this.emptyWeight = emptyWeight;
    }

    public void setMTOW(double MTOW) {
        this.MTOW = MTOW;
    }

    public void setMaxPayload(double maxPayload) {
        this.maxPayload = maxPayload;
    }

    public void setFuelCapacity(double fuelCapacity) {
        this.fuelCapacity = fuelCapacity;
    }

    public void setVMO(double VMO) {
        this.VMO = VMO;
    }

    public void setMMO(double MMO) {
        this.MMO = MMO;
    }

    public void setWingArea(double wingArea) {
        this.wingArea = wingArea;
    }

    public void setWingSpan(double wingSpan) {
        this.wingSpan = wingSpan;
    }

    public void setAspectRatio(double aspectRatio) {
        this.aspectRatio = aspectRatio;
    }

    public void setE(double e) {
        this.e = e;
    }

    public void setCdragRegister(CDragRegister cdragRegister) {
        this.cdragRegister = cdragRegister;
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
        return "AircraftModel{" + "id=" + id + ", description=" + description + ", maker=" + maker + ", type=" + type + ", numberMotors=" + numberMotors + ", motor=" + motor + ", motorType=" + motorType + ", cruiseAltitude=" + cruiseAltitude + ", cruiseSpeed=" + cruiseSpeed + ", TSFC=" + TSFC + ", lapseRateFactor=" + lapseRateFactor + ", thrust_0=" + thrust_0 + ", thrustMaxSpeed=" + thrustMaxSpeed + ", maxSpeed=" + maxSpeed + ", emptyWeight=" + emptyWeight + ", MTOW=" + MTOW + ", maxPayload=" + maxPayload + ", fuelCapacity=" + fuelCapacity + ", VMO=" + VMO + ", MMO=" + MMO + ", wingArea=" + wingArea + ", wingSpan=" + wingSpan + ", aspectRatio=" + aspectRatio + ", e=" + e + ", \ncdragRegister=" + cdragRegister + '}';
    }
      
}
