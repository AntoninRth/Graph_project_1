package m1graf2023;

/**
 * Class for the information of a node in DFS
 */
public class NodeVisitInfo {
    /**
     * public color of a node
     */
    NodeColour colour;
    /**
     * Node parent of the current node
     */
    Node predecessor;
    /**
     * Moment when we discover the node
     */
    Integer discovery;
    /**
     * Moment when we finished with the current node
     */
    Integer finished;

    /**
     * NodeVisitInfo constructor
     * @param colour of the node
     * @param predecessor of the node
     */
    public NodeVisitInfo (NodeColour colour, Node predecessor){
        this.colour = colour;
        this.predecessor = predecessor;
    }
}
