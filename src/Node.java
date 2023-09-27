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
        if(this.idNode == n.getId()){
            return true;
        }
        return false;
    }

    public int hashCode(){
        return this.idNode;
    }
}
