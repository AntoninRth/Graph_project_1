public class Edges implements Comparable<Edges>{
    Node fromNode, toNode;

    public Edges(Node from, Node to){
        this.fromNode = from;
        this.toNode = to;
    }

    public Edges(int idFrom, int idTo){
        Node from = new Node(idFrom);
        Node to = new Node(idTo);
        this.fromNode = from;
        this.toNode = to;
    }

    public Node getFromNode(){
        return this.fromNode;
    }

    public Node getToNode(){
        return this.toNode;
    }

    public boolean equals(Edges e){
        if((this.fromNode.equals(e.getFromNode()))||(this.toNode.equals(e.getToNode()))){
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
}
