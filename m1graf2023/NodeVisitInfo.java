package m1graf2023;

public class NodeVisitInfo {
    NodeColour colour;
    Node predecessor;
    Integer discovery;
    Integer finished;

    public NodeVisitInfo (NodeColour colour, Node predecessor){
        this.colour = colour;
        this.predecessor = predecessor;
    }
}
