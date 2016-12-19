/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

import java.util.Objects;

/**
 *
 * @author zero_
 */
public class Regime {
    
    private String id;
    private double TSFC;
    private double speed;
    private double thrust;
    private double altitude;

    public Regime(String id, double TSFC, double speed, double thrust, double altitude) {
        this.id = id;
        this.TSFC = TSFC;
        this.speed = speed;
        this.thrust = thrust;
        this.altitude = altitude;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getTSFC() {
        return TSFC;
    }

    public void setTSFC(double TSFC) {
        this.TSFC = TSFC;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public double getThrust() {
        return thrust;
    }

    public void setThrust(double thrust) {
        this.thrust = thrust;
    }

    public double getAltitude() {
        return altitude;
    }

    public void setAltitude(double altitude) {
        this.altitude = altitude;
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
        
        final Regime other = (Regime) obj;
        return Objects.equals(this.id, other.id);
    }

    @Override
    public String toString() {
        return "Regime{" + "id=" + id + ", TSFC=" + TSFC + ", speed=" + speed + ", thrust=" + thrust + ", altitude=" + altitude + '}';
    }
    
}
