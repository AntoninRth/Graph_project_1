package m1graf2023;

/**
 * Class representing a node of the graph
 * @author Romain CHUAT
 * @author Antonin ROTHE
 */
public class Node implements Comparable<Node>{
    /**
     * A public integer for the node ID
      */
    int idNode;

    /**
     * A public String for the node name
     */
    String nameNode;

    /**
     * Node constructor
     * @param id as node ID
     */
    public Node(int id){
        this.idNode = id;
    }

    /**
     * Node constructor
     * @param id as node ID
     * @param name as node Name
     */
    public Node(int id, String name){
        this.idNode = id;
        this.nameNode = name;
    }

    /**
     * @return the current node ID
     */
    public int getId(){
        return this.idNode;
    }

    /**
     * Compares the ids of two nodes to find out if they are equal
     * @param n as the node to compare
     * @return true if nodes are equals, false otherwise
     */
    public boolean equals(Node n){
        return this.idNode == n.getId();
    }

    /**
     * Node ID is unique, it can be used to identify a node
     * @return node ID
     */
    public int hashCode(){
        return this.idNode;
    }

    /**
     * Compare current node with object o by ID
     * @param o the object to be compared.
     * @return 1 if current object is greater than o, -1 if o is greater than our current object, 0 otherwise
     */
    @Override
    public int compareTo(Node o) {
        if(o.getId() < this.getId()){
            return 1;
        }else{
            if(o.getId() > this.getId()){
                return -1;
            }
        }
        return 0;
    }

    /**
     * @return node ID as a String
     */
    public String toString(){
        return String.valueOf(this.getId());
    }
}
