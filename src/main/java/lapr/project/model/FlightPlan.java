/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;
import lapr.project.model.network.Segment;

/**
 *
 * @author zero_
 */
public class FlightPlan {

    private int id;
    private String name;
    private AircraftModel.Type aircraftType;
    private Airport origin;
    private Airport dest;

    private double nNormalClass;
    private double nFirstClass;
    private double nCrew;

    public FlightPlan(String name, AircraftModel.Type aircraftType, Airport origin, Airport dest, double nNormalClass, double nFirstClass, double nCrew) {
        this.id = 0;
        this.name = name;
        this.aircraftType = aircraftType;
        this.origin = origin;
        this.dest = dest;
        this.nNormalClass = nNormalClass;
        this.nFirstClass = nFirstClass;
        this.nCrew = nCrew;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public AircraftModel.Type getAircraftType() {
        return aircraftType;
    }

    public Airport getOrigin() {
        return origin;
    }

    public Airport getDest() {
        return dest;
    }

    public double getnNormalClass() {
        return nNormalClass;
    }

    public double getnFirstClass() {
        return nFirstClass;
    }

    public double getnCrew() {
        return nCrew;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 19 * hash + Objects.hashCode(this.name);
        return hash;
    }

    @Override
    public boolean equals(Object otherObj) {
        if (this == otherObj) {
            return true;
        }
        if (otherObj == null || this.getClass() != otherObj.getClass()) {
            return false;
        }

        FlightPlan otherFlight = (FlightPlan) otherObj;
        return this.name.equals(otherFlight.name);
    }

    @Override
    public String toString() {
        return "FlightPlan{" + "id=" + id + ", name=" + name + ", aircraftType=" + aircraftType + ", origin=" + origin + ", dest=" + dest + ", nNormalClass=" + nNormalClass + ", nFirstClass=" + nFirstClass + ", nCrew=" + nCrew + '}';
    }

}
