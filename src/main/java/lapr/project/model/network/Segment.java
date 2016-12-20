package lapr.project.model.network;

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
    private double wind_direction;
    private double wind_speed;
    private double distance;

    public Segment(String id, Node bNode, Node eNode, double a_slots[], String direction, double wind_direction, double wind_speed) {
        this.id = id;
        this.beginningNode = bNode;
        this.endNode = eNode;
        this.altitudes_slots = a_slots;
        this.direction = direction;
        this.wind_direction = wind_direction;
        this.wind_speed = wind_speed;
        this.distance = distanceBetweenNodes(bNode, eNode);
    }

    private double distanceBetweenNodes(Node n1, Node n2) {
        double c = n2.getLatitude() - n1.getLatitude();
        double c2 = n2.getLongitude() - n1.getLongitude();
        double h = Math.sqrt(Math.pow(c, 2) + Math.pow(c2, 2));

        return h;
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

    public String getId() {
        return this.id;
    }

    /**
     * @return the beginningCoord
     */
    public Node getBeginningNode() {
        return beginningNode;
    }

    /**
     * @return the endCoord
     */
    public Node getEndNode() {
        return endNode;
    }

    /**
     * @return the altitudes_slots
     */
    public double[] getAltitudes_slots() {
        return altitudes_slots;
    }

    /**
     * @return the direction
     */
    public String getDirection() {
        return direction;
    }

    /**
     * @return the wind_direction
     */
    public double getWind_direction() {
        return wind_direction;
    }

    /**
     * @return the wind_speed
     */
    public double getWind_speed() {
        return wind_speed;
    }

    public double getDistance() {
        return distance;
    }

    @Override
    public String toString() {
        return "Segment{" + "id=" + id + ", bNode=" + beginningNode + ", eNode=" + endNode + ", d=" + distance + '}';
    }
    
    
}
