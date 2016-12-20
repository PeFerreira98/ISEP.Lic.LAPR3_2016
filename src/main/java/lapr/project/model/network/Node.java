package lapr.project.model.network;

import java.util.Objects;
import lapr.project.model.Location;

/**
 *
 * @author G33
 */
public class Node implements Comparable<Node> {

    private String name;
    private double latitude;
    private double longitude;

    public Node(String name, double latitude, double longitude) {
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
   }
    
    public Node(){
        
    }
    

    /**
     * Clona um determinado Node
     *
     * @return Node clonado
     * @throws CloneNotSupportedException
     */
    @Override
    protected Node clone() throws CloneNotSupportedException {
        Node n = new Node(this.name, this.latitude, this.longitude);
        return n;
    }

    /**
     * Compara dois Nodes por nome
     *
     * @param t outro Node
     * @return 0 se igual. 1 se difere
     */
    @Override
    public int compareTo(Node t) {
        if (this.name.equals(t.getName())) {
            return 0;
        }
        return 1;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Node other = (Node) obj;
        if (!Objects.equals(this.name, other.getName())) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Node{" + name + '}';
    }

    /**
     * @return the node_name
     */
    public String getName() {
        return name;
    }

    /**
     * @param node_name the node_name to set
     */
    public void setName(String node_name) {
        this.name = node_name;
    }

    /**
     * @return the latitude
     */
    public double getLatitude() {
        return latitude;
    }

    /**
     * @param latitude the latitude to set
     */
    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    /**
     * @return the longitude
     */
    public double getLongitude() {
        return longitude;
    }

    /**
     * @param longitude the longitude to set
     */
    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

}
