package lapr.project.model.network;

import java.util.Arrays;
import java.util.Objects;
import lapr.project.model.Location;

/**
 *
 * @author G33
 */
public class Node implements Comparable<Node>{

    private static int maxid = 0;
    private int id;
    private Location location;

    /**
     * Construtor vazio
     */
    public Node() {
        this.id = Node.maxid++;
    }

    /**
     * Construtor de objecto Node
     *
     * @param bCoord
     * @param eCoord
     */
    public Node(Location location) {
        this.location=location;
    }

    /**
     * Devolve as coordenadas iniciais
     *
     * @return nome do Node
     */
    public Location getLocalizacaoNode(){
        return this.location;
    }

    /**
     * Insere o ID
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Devolve o ID
     *
     * @return ID
     */
    public int getID() {
        return this.id;
    }

    /**
     * Define as coordenadas iniciais
     *
     * @param coord
     * @param id novo nome de Node
     */
    

    /**
     * Clona um determinado Node
     *
     * @return Node clonado
     * @throws CloneNotSupportedException
     */
    @Override
    protected Node clone() throws CloneNotSupportedException {
        Node n = new Node(location);
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
        if (this.location == t.location) {
            return 0;
        }
        return 1;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 53 * hash + Objects.hashCode(this.id);
        return hash;
    }

    /**
     * Compara dois objectos Node
     *
     * @param obj outro Node
     * @return false se nome ou classe difere ou se obj nao existir. True caso
     * seja igual
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        
        final Node other = (Node) obj;
        return !(!Objects.equals(this.location, other.location)
                && !Objects.equals(this.location, other.location));
    }

    /**
     * Cria uma String com informação do Node
     *
     * @return string com informação
     */
    @Override
    public String toString() {
        return "Node{" + location + "'}'";
    }

}
