/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

/**
 *
 * @author zero_
 */
public class CDrag {
    
    private double speed;
    private double cDrag0;

    public CDrag(double speed, double cDrag0) {
        this.speed = speed;
        this.cDrag0 = cDrag0;
    }

    public CDrag(CDrag cDrag) {
        this(cDrag.getSpeed(), cDrag.getcDrag0());
    }

    public double getSpeed() {
        return speed;
    }

    public double getcDrag0() {
        return cDrag0;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        
        final CDrag other = (CDrag) obj;
        if (Double.doubleToLongBits(this.speed) != Double.doubleToLongBits(other.speed)) {
            return false;
        }
        return Double.doubleToLongBits(this.cDrag0) == Double.doubleToLongBits(other.cDrag0);
    }

    @Override
    public String toString() {
        return "CDrag{" + "speed=" + speed + ", cDrag0=" + cDrag0 + '}';
    }
    
}
