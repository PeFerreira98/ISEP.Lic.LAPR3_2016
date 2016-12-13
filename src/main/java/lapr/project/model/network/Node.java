package lapr.project.model.network;

import java.util.Objects;

/**
 *
 * @author G33
 */
public class Node implements Comparable<Node> {
    
    private static int maxid = 0;
    private int id;
    private float beginningCoord[];
    private float endCoord[];
    /**
     * Construtor vazio
     */
    public Node() {
        this.id = Node.maxid++;
    }

    /**
     * Construtor de objecto Node
     * @param bCoord
     * @param eCoord
     */
    public Node(float bCoord[], float eCoord[]) {
        this.beginningCoord = bCoord;
        this.endCoord = eCoord;
    }

    /**
     * Devolve as coordenadas iniciais
     * @return nome do Node
     */
    public float[] getBeginningCoord() {
        return this.beginningCoord;
    }
    
    public float[] getEndCoord(){
        return this.endCoord;
    }
    /**
     * Insere o ID
     */
    public void setId(int id){
         this.id=id;
    }
    
    
    /**
     * Devolve o ID
     * @return ID
     */
    public int getID(){
        return this.id;
    }

    /**
     * Define as coordenadas iniciais
     * @param id novo nome de Node
     */
    public void setBeginningCoord(float coord[]) {
        this.beginningCoord = coord;
    }
    
    public void setEndCoord(float coord[]){
        this.endCoord = coord;
    }

    /**
     * Clona um determinado Node
     * @return Node clonado
     * @throws CloneNotSupportedException 
     */
    @Override
    protected Node clone() throws CloneNotSupportedException {
        Node n = new Node(beginningCoord,endCoord);
        return n;
    }

    /**
     * Compara dois Nodes por nome
     * @param t outro Node
     * @return 0 se igual. 1 se difere
     */
    @Override
    public int compareTo(Node t) {
        if (this.beginningCoord == t.getBeginningCoord()) {
            return 0;
        }
        return 1;
    }

   @Override
    public int hashCode() {
        int hash = 3;
        hash = 53 * hash + Objects.hashCode(this.beginningCoord);
        return hash;
    }

    /**
     * Compara dois objectos Node
     * @param obj outro Node
     * @return false se nome ou classe difere ou se obj nao existir. True caso seja igual
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
        if (!Objects.equals(this.beginningCoord, other.beginningCoord) &&
                !Objects.equals(this.endCoord, other.endCoord)) {
            return false;
        }
        return true;
    }
    
    /**
     * Cria uma String com informação do Node
     * @return string com informação
     */
    @Override
    public String toString() {
        return "Node{" + beginningCoord + " : " + endCoord + '}';
    }
}
