package network;

import java.util.ArrayList;

/**
 *
 * @author G33
 */
public class Segment {
 
    private float beginningCoord;
    private float endCoord;
    private ArrayList altitudes_slots;
    private String direction;
    private float wind_direction;
    private float wind_speed;

    
    public Segment(float bCoord, float eCoord, ArrayList a_slots, String direction, float wind_direction, float wind_speed){
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
        this.beginningCoord = 0;
        this.endCoord = 0;
        this.altitudes_slots = new ArrayList<>();
        this.direction = "";
        this.wind_direction = 0;
        this.wind_speed = 0;
    }

    /**
     * @return the beginningCoord
     */
    public float getBeginningCoord() {
        return beginningCoord;
    }

    /**
     * @param beginningCoord the beginningCoord to set
     */
    public void setBeginningCoord(float beginningCoord) {
        this.beginningCoord = beginningCoord;
    }

    /**
     * @return the endCoord
     */
    public float getEndCoord() {
        return endCoord;
    }

    /**
     * @param endCoord the endCoord to set
     */
    public void setEndCoord(float endCoord) {
        this.endCoord = endCoord;
    }

    /**
     * @return the altitudes_slots
     */
    public ArrayList getAltitudes_slots() {
        return altitudes_slots;
    }

    /**
     * @param altitudes_slots the altitudes_slots to set
     */
    public void setAltitudes_slots(ArrayList altitudes_slots) {
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
