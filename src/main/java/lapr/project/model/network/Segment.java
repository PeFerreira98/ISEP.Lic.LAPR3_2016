package lapr.project.model.network;

import java.util.ArrayList;
import java.util.List;
import lapr.project.model.Location;

/**
 *
 * @author G33
 */
public class Segment {
 
    private Location beginningCoord;
    private Location endCoord;
    private double altitudes_slots[];
    private String direction;
    private float wind_direction;
    private float wind_speed;

    
    public Segment(Location bCoord, Location eCoord, double a_slots[], String direction, float wind_direction, float wind_speed){
        this.beginningCoord = bCoord;
        this.endCoord = eCoord;
        this.altitudes_slots = a_slots;
        this.direction.equals(direction);
        this.wind_direction = wind_direction;
        this.wind_speed = wind_speed;
    }

    /**
     * Construtor vazio de Segment
     */
    public Segment() {
        this.beginningCoord = null;
        this.endCoord = null;
        this.direction = "";
        this.wind_direction = 0;
        this.wind_speed = 0;
    }

    /**
     * @return the beginningCoord
     */
    public Location getBeginningCoord() {
        return beginningCoord;
    }

    /**
     * @param beginningCoord the beginningCoord to set
     */
    public void setBeginningCoord(Location beginningCoord) {
        this.beginningCoord = beginningCoord;
    }

    /**
     * @return the endCoord
     */
    public Location getEndCoord() {
        return endCoord;
    }

    /**
     * @param endCoord the endCoord to set
     */
    public void setEndCoord(Location endCoord) {
        this.endCoord = endCoord;
    }

    /**
     * @return the altitudes_slots
     */
    public double[] getAltitudes_slots() {
        return altitudes_slots;
    }

    /**
     * @param altitudes_slots the altitudes_slots to set
     */
    public void setAltitudes_slots(double altitudes_slots[]) {
        this.altitudes_slots = altitudes_slots;
    }

    /**
     * @return the direction
     */
    public String getDirection() {
        return direction;
    }

    /**
     * @param direction the direction to set
     */
    public void setDirection(String direction) {
        this.direction = direction;
    }

    /**
     * @return the wind_direction
     */
    public float getWind_direction() {
        return wind_direction;
    }

    /**
     * @param wind_direction the wind_direction to set
     */
    public void setWind_direction(float wind_direction) {
        this.wind_direction = wind_direction;
    }

    /**
     * @return the wind_speed
     */
    public float getWind_speed() {
        return wind_speed;
    }

    /**
     * @param wind_speed the wind_speed to set
     */
    public void setWind_speed(float wind_speed) {
        this.wind_speed = wind_speed;
    }   
}
