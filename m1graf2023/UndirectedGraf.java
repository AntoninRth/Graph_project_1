package m1graf2023;

import javax.xml.stream.events.EntityDeclaration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class UndirectedGraf extends Graf{

    public UndirectedGraf(){
        super();
    }

    public UndirectedGraf(int ... SuccessorArray){
        super(SuccessorArray);
    }

    public List<Node> getSuccessors(Node n){
        List<Node> result = super.getSuccessors(n);
        boolean present;
        for(List<Edge> edges: adjEdList.values()){
            for(Edge e : edges){
                if(e.to().equals(n)){
                    present = false;
                    for(Node u: result){
                        if(u.equals(e.from())){
                            present = true;
                        }
                    }
                    if(!present){
                        result.add(e.from());
                    }
                }
            }
        }

        Collections.sort(result);

        return result;
    }
    public List<Node> getSuccessors(int nodeID){
        Node n = new Node(nodeID);
        return getSuccessors(n);
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

        adjEdList.get(from).add(e);
        adjEdList.get(to).add(e1);
        Collections.sort(adjEdList.get(from));
        Collections.sort(adjEdList.get(to));

    }

    public void addEdge(Node from, Node to, int weight){
        if(!this.existsNode(from.getId())){
            this.addNode(from);
        }

        if(!this.existsNode(to.getId())){
            this.addNode(to);
        }

        Edge e = new Edge(from,to, weight);
        Edge e1 = new Edge(to, from);

        adjEdList.get(from).add(e);
        Collections.sort(adjEdList.get(from));
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

    /**
     * Get the in degree of the node n
     * @param n the node
     * @return number of in edges of the node n
     */
    public int inDegree(Node n){
        int res = 0;
        for(List<Edge> edgeList: adjEdList.values()){
            for(Edge e: edgeList){
                if(e.to().equals(n)){
                    res++;
                }
            }
        }

        for(Edge to: adjEdList.get(n)){
            res++;
        }
        return res;
    }

    /**
     * Get the in degree of the node n
     * @param nodeID the node id
     * @return number of in edges of the node n
     */
    public int inDegree(int nodeID){
        Node n = new Node(nodeID);
        return inDegree(n);
    }

    /**
     * Get the out degree of the node n
     * @param n the node
     * @return the number of out edges of the node n
     */
    public int outDegree(Node n){
        List<Edge> nEdge = adjEdList.get(n);

        int res = nEdge.size();

        for(List<Edge> edgeList: adjEdList.values()){
            for(Edge e: edgeList){
                if(e.from().equals(n)){
                    res++;
                }
            }
        }

        return res;
    }

    /**
     * Get the out degree of the node n
     * @param nodeID the node id
     * @return the number of out edges of the node n
     */
    public int outDegree(int nodeID){
        Node n = new Node(nodeID);
        List<Edge> nEdge = adjEdList.get(n);
        return nEdge.size();
    }


    /**
     * Print the current graph
     * @return the printing of the graph
     */
    public String toDotString(){
        StringBuilder str = new StringBuilder("digraph {\nrankdir=LR\n");
        for (Map.Entry<Node, List<Edge>> entry : adjEdList.entrySet()) {
            if(degree(entry.getKey()) == 0){
                str.append(entry.getKey().toString()).append("\n");
            }else{

                List<Edge> edges = entry.getValue();
                for(int i = 0; i< edges.size(); i++){
                    str.append(edges.get(i).from()+" -- "+edges.get(i).to());
                    if(edges.get(i).getWeight() != null){
                        str.append(" [label = "+edges.get(i).getLabel()+", len = "+edges.get(i).getWeight()+"]\n");
                    }else{
                        str.append("\n");
                    }

                }
            }

        }
        str.append("}");
        return str.toString();
    }

}
