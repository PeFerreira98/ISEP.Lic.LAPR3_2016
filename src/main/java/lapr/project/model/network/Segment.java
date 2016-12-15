package lapr.project.model.network;

import java.util.ArrayList;
import java.util.List;
import lapr.project.model.Location;

/**
 *
 * @author G33
 */
public class Segment {
 
    private String id;
    private Node beginningNode;
    private Node endNode;
    private double altitudes_slots[];
    private String direction;
    private float wind_direction;
    private float wind_speed;

    
    public Segment(String id, Node bCoord, Node eCoord, double a_slots[], String direction, float wind_direction, float wind_speed){
        this.id = id;
        this.beginningNode = bCoord;
        this.endNode = eCoord;
        this.altitudes_slots = a_slots;
        this.direction = direction;
        this.wind_direction = wind_direction;
        this.wind_speed = wind_speed;
    }

    /**
     * Construtor vazio de Segment
     */
    public Segment() {
        this.id = "";
        this.beginningNode = null;
        this.endNode = null;
        this.direction = "";
        this.wind_direction = 0;
        this.wind_speed = 0;
    }

    
    public String getId(){
        return this.id;
    }
    /**
     * @return the beginningCoord
     */
    public Node getBeginningNode() {
        return beginningNode;
    }

    /**
     * @param beginningNode the beginningCoord to set
     */
    public void setBeginningNode(Node beginningNode) {
        this.beginningNode = beginningNode;
    }

    /**
     * @return the endCoord
     */
    public Node getEndNode() {
        return endNode;
    }

    /**
     * @param endNode the endNode to set
     */
    public void setEndNode(Node endNode) {
        this.endNode = endNode;
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
