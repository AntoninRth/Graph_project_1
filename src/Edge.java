import src.Node;
import src.Edge;

public class Edge implements Comparable<Edge>{
    Node fromNode, toNode;

    public Edge(Node from, Node to){
        this.fromNode = from;
        this.toNode = to;
    }

    public Edge(int idFrom, int idTo){
        Node from = new Node(idFrom);
        Node to = new Node(idTo);
        this.fromNode = from;
        this.toNode = to;
    }

    public Node from(){
        return this.fromNode;
    }

    public Node to(){
        return this.toNode;
    }

    public boolean equals(src.Edge e){
        if((this.fromNode.equals(e.from()))||(this.toNode.equals(e.to()))){
            return true;
        }
        return false;
    }

    public String toString(){
        return "("+this.fromNode+","+this.toNode+")";
    }

    public String hashCode(){
        this.toString().hashCode();
    }

    public boolean isSelfLoop(){
        if(this.fromNode.equals(this.toNode)){
            return true;
        }
        return false;
    }
}
