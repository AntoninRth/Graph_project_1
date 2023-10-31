package m1graf2023;

import javax.xml.stream.events.EntityDeclaration;
import java.util.ArrayList;
import java.util.List;

public class UndirectedGraf extends Graf{

    UndirectedGraf(){
        super();
    }

    public List<Node> getSuccessors(Node n){
        return null;
    }
    public List<Node> getSuccessors(int nodeID){
        return null;
    }



    public int nbEdges(){
        return super.nbEdges()*2;
    }
    public void addEdge(Node from, Node to){
        if(!this.existsNode(from.getId())){
            this.addNode(from);
        }
        if(!this.existsNode(to.getId())){
            this.addNode(to);
        }
        Edge e = new Edge(from,to);
        Edge e1 = new Edge(to, from);
        //adjEdList.
    }

    public void addEdge(Node from, Node to, int weight){
        if(!this.existsNode(from.getId())){
            this.addNode(from);
        }

        if(!this.existsNode(to.getId())){
            this.addNode(to);
        }

        Edge e = new Edge(from,to, weight);
        adjEdList.get(from).add(e);
    }

    public void addEdge(Edge e){
        if(!this.existsNode(e.from().getId())){
            this.addNode(e.from());
        }

        if(!this.existsNode(e.to().getId())){
            this.addNode(e.to());
        }

        adjEdList.get(e.from()).add(e);
    }

    public void addEdge(int fromID, int toID){
        Edge e = new Edge(fromID,toID);
        adjEdList.get(new Node(fromID)).add(e);
    }

    public void addEdge(int fromID, int toID, int weight){
        Edge e = new Edge(fromID,toID,weight);
        adjEdList.get(new Node(fromID)).add(e);
    }

}
