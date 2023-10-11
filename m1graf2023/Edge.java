package m1graf2023;

public class Edge implements Comparable<Edge>{
    Node fromNode, toNode;
    Integer weight;

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

    public boolean equals(Edge e){
        return (this.fromNode.equals(e.from())) || (this.toNode.equals(e.to()));
    }

    public String toString(){
        return this.fromNode.idNode+" -> "+this.toNode.idNode;
    }

    public int hashCode(){
        return this.toString().hashCode();
    }

    public boolean isSelfLoop(){
        return this.fromNode.equals(this.toNode);
    }

    public Edge getSymmetric(){
        return new Edge(to(),from());
    }

    public boolean isWeighted(){
        return weight != null;
    }

    public Integer getWeight(){
        return weight;
    }

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
