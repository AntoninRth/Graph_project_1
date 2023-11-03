package m1graf2023;

/**
 * Class representing an edge of the graph
 * @author Romain CHUAT
 * @author Antonin ROTHE
 */
public class Edge implements Comparable<Edge>{
    /**
     * Public nodes for the nodes from and to of the edge
     */
    Node fromNode, toNode;

    /**
     * public Integer for the weight of the edge
     */
    Integer weight;

    /**
     * public Int for the label of the edge
     */
    int label;

    /**
     * Edge constructor
     * @param from node
     * @param to node
     */
    public Edge(Node from, Node to){
        this.fromNode = from;
        this.toNode = to;
    }

    /**
     * Edge constructor
     * @param from node
     * @param to node
     * @param weight of the edge
     */
    public Edge(Node from, Node to, int weight){
        this.fromNode = from;
        this.toNode = to;
        this.weight = weight;
    }

    /**
     * Edge constructor
     * @param from node
     * @param to node
     * @param weight of the edge
     * @param label of the edge
     */
    public Edge(Node from, Node to, int weight, int label){
        this.fromNode = from;
        this.toNode = to;
        this.weight = weight;
        this.label = label;
    }

    /**
     * Edge constructor
     * @param idFrom of the from node
     * @param idTo of the to node
     */
    public Edge(int idFrom, int idTo){
        Node from = new Node(idFrom);
        Node to = new Node(idTo);
        this.fromNode = from;
        this.toNode = to;
    }

    /**
     * Edge constructor
     * @param idFrom of the from node
     * @param idTo of the to node
     * @param weight of the edge
     */
    public Edge(int idFrom, int idTo, int weight){
        Node from = new Node(idFrom);
        Node to = new Node(idTo);
        this.fromNode = from;
        this.toNode = to;
        this.weight = weight;
    }

    /**
     * Edge constructor
     * @param idFrom of the from node
     * @param idTo of the to node
     * @param weight of the edge
     * @param label of the edge
     */
    public Edge(int idFrom, int idTo, int weight, int label){
        Node from = new Node(idFrom);
        Node to = new Node(idTo);
        this.fromNode = from;
        this.toNode = to;
        this.weight = weight;
        this.label = label;
    }

    /**
     * @return the node from of the edge
     */
    public Node from(){
        return this.fromNode;
    }

    /**
     * @return the node to of the edge
     */
    public Node to(){
        return this.toNode;
    }

    /**
     * Compare two edges
     * @param e the edge to compare with our current edge
     * @return true if edges are equals, false otherwise
     */
    public boolean equals(Edge e){
        return (this.fromNode.equals(e.from())) && (this.toNode.equals(e.to()));
    }

    /**
     * @return a representation of an edge with fromNode and toNode
     */
    public String toString(){
        return this.fromNode.idNode+" -> "+this.toNode.idNode;
    }

    /**
     * @return a hashCode of a String
     */
    public int hashCode(){
        return this.toString().hashCode();
    }

    /**
     * @return true if node From equals node To, false otherwise
     */
    public boolean isSelfLoop(){
        return this.fromNode.equals(this.toNode);
    }

    /**
     * A symmetric of an edge is a new edge with to node and from node of the previous edge as from and to nodes
     * @return the symmetric of our current edge
     */
    public Edge getSymmetric(){
        return new Edge(to(),from());
    }

    /**
     * @return true if the weight of the edge is not null, false otherwise
     */
    public boolean isWeighted(){
        return weight != null;
    }

    /**
     * @return weight edge
     */
    public Integer getWeight(){
        return weight;
    }

    /**
     * @return label edge
     */
    public int getLabel(){ return label;}

    /**
     * Compare our current edge with another edge by ID of the nodes and weight
     * @param o the object to be compared.
     * @return 1 if current edge is greater than o, -1 if o is greater than current edge, 0 otherwise
     */
    @Override
    public int compareTo(Edge o) {
        if(o.from().getId() < this.from().getId()){
            return 1;
        }else{
            if(o.from().getId() > this.from().getId()){
                return -1;
            }else{
                if(o.to().getId() < this.to().getId()){
                    return 1;
                }else{
                    if(o.to().getId() > this.to().getId()){
                        return -1;
                    }else{
                        if((o.getWeight() != null && this.getWeight() !=  null) && (o.getWeight() < this.getWeight())){
                            return 1;
                        }else{
                            if((o.getWeight() != null && this.getWeight() !=  null) && (o.getWeight() > this.getWeight())){
                                return -1;
                            }
                        }
                    }
                }
            }
        }
        return 0;
    }
}
