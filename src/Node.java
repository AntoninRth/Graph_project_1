package src;

public class Node implements Comparable<Node>{
    int idNode;
    String nameNode;

    public Node(int id){
        this.idNode = id;
    }

    public Node(int id, String name){
        this.idNode = id;
        this.nameNode = name;
    }

    int getId(){
        return this.idNode;
    }

    public boolean equals(Node n){
        return this.idNode == n.getId();
    }

    public int hashCode(){
        return this.idNode;
    }

    @Override
    public int compareTo(Node o) {
        return 0;
    }
}