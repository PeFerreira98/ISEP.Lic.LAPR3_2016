package lapr.project.model.network;

import java.util.Objects;
import lapr.project.model.Location;

/**
 *
 * @author G33
 */
public class Node implements Comparable<Node> {

    private String id;
    private Location location;

    public Node(String id, Location location) {
        this.id = id;
        this.location = location;
    }

    public String getId() {
        return id;
    }

    public Location getLatitude() {
        return location;
    }

    /**
     * Clona um determinado Node
     *
     * @return Node clonado
     * @throws CloneNotSupportedException
     */
    @Override
    protected Node clone() throws CloneNotSupportedException {
        Node n = new Node(this.id, this.location);
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
        if (this.id.equals(t.getId())) {
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
        if (!Objects.equals(this.id, other.getId())) {
            return false;
        }
        return true;
    }

}
