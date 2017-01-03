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
 * @author Tiago
 */
public class Flight {
    
    private int id;
    private FlightType type;
    private Aircraft aircraft;
    private Date departure_day;
    private double minimun_stop;
    private Date scheduled_arrival;
    private ArrayList<Segment> flight_plan;
    
    public enum FlightType {
        regular,
        charter;
    }
    
    public Flight(int id, FlightType type, Date d, double ms, Date sa, ArrayList<Segment> fp,Aircraft aircraf){
        this.id = id;
        this.type = type;
        this.departure_day = d;
        this.minimun_stop = ms;
        this.scheduled_arrival = sa;
        this.flight_plan = fp;
        this.aircraft = aircraf;
    }
    
    public Flight(int id, FlightType type, Date d, double ms, Date sa,Aircraft aircraf){
        this.id = id;
        this.type = type;
        this.departure_day = d;
        this.minimun_stop = ms;
        this.scheduled_arrival = sa;
        this.flight_plan = new ArrayList<>();
        this.aircraft = aircraf;
    }
        
    public Flight(){
        
    }
    public Aircraft getAircraft(){
        return this.aircraft;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public FlightType getType() {
        return type;
    }

    public void setType(FlightType type) {
        this.type = type;
    }

    public Date getDeparture_day() {
        return departure_day;
    }

    public void setDeparture_day(Date departure_day) {
        this.departure_day = departure_day;
    }

    public double getMinimun_stop() {
        return minimun_stop;
    }

    public void setMinimun_stop(double minimun_stop) {
        this.minimun_stop = minimun_stop;
    }

    public Date getScheduled_arrival() {
        return scheduled_arrival;
    }

    public void setScheduled_arrival(Date scheduled_arrival) {
        this.scheduled_arrival = scheduled_arrival;
    }

    public ArrayList<Segment> getFlight_plan() {
        return flight_plan;
    }

    public void setFlight_plan(ArrayList<Segment> flight_plan) {
        this.flight_plan = flight_plan;
    }
    
    public boolean equals(Object otherObj) {
        if (this == otherObj) {
            return true;
        }
        if (otherObj == null || this.getClass() != otherObj.getClass()){
            return false;
        }
        Flight otherFlight = (Flight) otherObj;
        return this.id == otherFlight.id;
    }
    


    @Override
    public String toString() {
        return "Flight{" + "id=" + id + ", type=" + type + ", departure_day=" + departure_day + ", minimun_stop=" + minimun_stop + ", scheduled_arrival=" + scheduled_arrival + ", flight_plan=" + flight_plan + '}';
    }
    
    
}