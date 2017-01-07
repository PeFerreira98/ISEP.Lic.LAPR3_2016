/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

import java.util.ArrayList;
import java.util.Date;
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

    private double nNormalClass, nFirstClass, nCrew;

    //Option Stuff
//    private Date departure_day;
//    private double minimun_stop;
//    private Date scheduled_arrival;
    //private ArrayList<Segment> flight_plan;
//    public FlightPlan(int id, FlightType type, Date d, double ms, Date sa, ArrayList<Segment> fp, double nNormalClass,double nCrew, double nFirstClass, double fuel, double load){
//        this.id = id;
//        this.type = type;
//        this.departure_day = d;
//        this.minimun_stop = ms;
//        this.scheduled_arrival = sa;
//        this.flight_plan = fp;
//        this.nNormalClass = nNormalClass;
//        this.nFirstClass = nFirstClass;
//        this.nCrew = nCrew;
//    }

    public FlightPlan(String name, AircraftModel.Type aircraftType, Airport origin, Airport dest, double nNormalClass, double nFirstClass, double nCrew) {
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

    public boolean equals(Object otherObj) {
        if (this == otherObj) {
            return true;
        }
        if (otherObj == null || this.getClass() != otherObj.getClass()) {
            return false;
        }
        FlightPlan otherFlight = (FlightPlan) otherObj;
        return this.id == otherFlight.id;
    }

    @Override
    public String toString() {
        return "Flight{" + "id=" + id + ", name=" + name + '}';
    }

}
