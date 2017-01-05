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
    private String type;
    private String name;
    private Airport origin;
    private Airport dest;
    
    private double nNormalClass, nFirstClass, nCrew;
    
    //Option Stuff
//    private Date departure_day;
//    private double minimun_stop;
//    private Date scheduled_arrival;
    //private ArrayList<Segment> flight_plan;
    public enum FlightType {
        regular,
        charter;
    }
    
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
    
    public FlightPlan(String name, String type, double nNormalClass, double nFirstClass, double nCrew, Airport origin, Airport dest){
        this.name = name;
        this.type = type;
        this.nNormalClass = nNormalClass;
        this.nFirstClass = nFirstClass;
        this.nCrew = nCrew;
        this.origin = origin;
        this.dest = dest;
    }
    
        
    public FlightPlan(){
        
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public String getFlightType(){
        return this.type;
    }
    
    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @return the origin
     */
    public Airport getOrigin() {
        return origin;
    }

    /**
     * @return the dest
     */
    public Airport getDest() {
        return dest;
    }

    /**
     * @return the nNormalClass
     */
    public double getnNormalClass() {
        return nNormalClass;
    }

    /**
     * @return the nFirstClass
     */
    public double getnFirstClass() {
        return nFirstClass;
    }

    /**
     * @return the nCrew
     */
    public double getnCrew() {
        return nCrew;
    }

    
    public boolean equals(Object otherObj) {
        if (this == otherObj) {
            return true;
        }
        if (otherObj == null || this.getClass() != otherObj.getClass()){
            return false;
        }
        FlightPlan otherFlight = (FlightPlan) otherObj;
        return this.id == otherFlight.id;
    }
    


    @Override
    public String toString() {
        return "Flight{" + "id=" + id + ", type=" + type +'}';
    }

    
    
    
}
