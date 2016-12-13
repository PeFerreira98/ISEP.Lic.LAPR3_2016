/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

/**
 *
 * @author João
 */
public class AircraftModel {
    
    private int id;
    private Type type;
    private Motorization motorization;
    private double emptyWeight;
    private double MTOW;
    private double MZFW;
    private double maximumFuelCapacity;
    private double serviceCeiling;
    private double cruiseSpeed;
    private double wingArea;
    private double dragCoeficient;
    private double liftCoeficient;
    
    public enum Type{
        passenger,
        cargo,
        mixed;
    }
    
    public enum Motorization{
        turboprop,
        turbofan,
        turbojet,
        electricPropeller;
    }
    
    public AircraftModel(int id, Type type, Motorization motorization, double emptyWeight, double MTOW, double MZFW, double maximumFuelCapacity,
            double serviceCeiling, double cruiseSpeed, double wingArea, double dragCoeficient, double liftCoeficient){
        this.id=id;
        this.type=type;
        this.emptyWeight=emptyWeight;
        this.MTOW=MTOW;
        this.MZFW=MZFW;
        this.maximumFuelCapacity=maximumFuelCapacity;
        this.serviceCeiling=serviceCeiling;
        this.cruiseSpeed=cruiseSpeed;
        this.wingArea=wingArea;
        this.dragCoeficient=dragCoeficient;
        this.liftCoeficient=liftCoeficient;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getEmptyWeight() {
        return emptyWeight;
    }

    public void setEmptyWeight(double emptyWeight) {
        this.emptyWeight = emptyWeight;
    }

    public double getMTOW() {
        return MTOW;
    }

    public void setMTOW(double MTOW) {
        this.MTOW = MTOW;
    }

    public double getMZFW() {
        return MZFW;
    }

    public void setMZFW(double MZFW) {
        this.MZFW = MZFW;
    }

    public double getMaximumFuelCapacity() {
        return maximumFuelCapacity;
    }

    public void setMaximumFuelCapacity(double maximumFuelCapacity) {
        this.maximumFuelCapacity = maximumFuelCapacity;
    }

    public double getServiceCeiling() {
        return serviceCeiling;
    }

    public void setServiceCeiling(double serviceCeiling) {
        this.serviceCeiling = serviceCeiling;
    }

    public double getCruiseSpeed() {
        return cruiseSpeed;
    }

    public void setCruiseSpeed(double cruiseSpeed) {
        this.cruiseSpeed = cruiseSpeed;
    }

    public double getWingArea() {
        return wingArea;
    }

    public void setWingArea(double wingArea) {
        this.wingArea = wingArea;
    }

    public double getDragCoeficient() {
        return dragCoeficient;
    }

    public void setDragCoeficient(double dragCoeficient) {
        this.dragCoeficient = dragCoeficient;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "AircraftModel{" + "id=" + id + ", type=" + type + ", motorization=" + motorization + ", emptyWeight=" + emptyWeight + ", MTOW=" + MTOW + ", MZFW=" + MZFW + ", maximumFuelCapacity=" + maximumFuelCapacity + ", serviceCeiling=" + serviceCeiling + ", cruiseSpeed=" + cruiseSpeed + ", wingArea=" + wingArea + ", dragCoeficient=" + dragCoeficient + ", liftCoeficient=" + liftCoeficient + '}';
    }

    public Motorization getMotorization() {
        return motorization;
    }

    public void setMotorization(Motorization motorization) {
        this.motorization = motorization;
    }

    public double getLiftCoeficient() {
        return liftCoeficient;
    }

    public void setLiftCoeficient(double liftCoeficient) {
        this.liftCoeficient = liftCoeficient;
    }
    
}
