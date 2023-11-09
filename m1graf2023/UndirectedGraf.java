package m1graf2023;

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
        return inDegree(n);
    }

    /**
     * Get the out degree of the node n
     * @param nodeID the node id
     * @return the number of out edges of the node n
     */
    public int outDegree(int nodeID){
        return inDegree(new Node(nodeID));
    }

    /**
     * Get the union between in and out degree of the node n
     * In and out degree are the same edges so we count each edge just one time
     * @param n the node
     * @return the number of edges where n is involved
     */
    public int degree(Node n){
        return inDegree(n);
    }

    /**
     * Get the union between in and out degree of the node n
     * In and out degree are the same edges so we count each edge just one time
     * @param n the node
     * @return the number of edges where n is involved
     */
    public int degree(int nodeID){
        return degree(new Node(nodeID));
    }

    /**
     * Get the out edges of the node n
     * @param n the node
     * @return a list of edges
     */
    public List<Edge> getOutEdges(Node n){

        return new ArrayList<>(adjEdList.get(n));
    }

    /**
     * Get the out edges of the node n
     * @param nodeID the node id
     * @return a list of edges
     */
    public List<Edge> getOutEdges(int nodeID){
        Node n = new Node(nodeID);

        return new ArrayList<>(adjEdList.get(n));
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
    /**
     * Get the transitive closure of the current graph
     * @return a graph with transitive closure
     */
    @Override
    public UndirectedGraf getTransitiveClosure() {
        List<Edge> tempEdges = getAllEdges();
        UndirectedGraf result = new UndirectedGraf();
        List<Edge> listOfEdge = new ArrayList<>();
        boolean present = false;

        for(Edge e: tempEdges){
            if(!e.isSelfLoop()){
                for(Edge currEdge: listOfEdge){
                    if(e.equals(currEdge)){
                        present = true;
                        break;
                    }
                }
                if(!present){
                    listOfEdge.add(e);
                    result.addEdge(e);
                }else{
                    present = false;
                }
            }

        }

        boolean change = false;
        do{
            change = false;
            //On parcourt tous les noeuds du graph
            for(Node n : result.getAllNodes()){
                //On passe par tous les edges entrants dans ce noeud
                for(Edge eIn : result.getInEdges(n)){
                    //On passe par tous les edges sortants de ce noeud
                    for(Edge eOut : result.getOutEdges(n)){
                        //On évite les boucles sur le même noeud
                        if(!eIn.from().equals(eOut.to())){
                            //On vérifie que le lien que l'on veut créer n'existe pas déjà
                            List<Edge> existEdge = result.getOutEdges(eIn.from());
                            for(Edge e : existEdge){
                                if( ( (e.from().equals(eIn.from())) && (e.to().equals(eOut.to())) ) /*|| ( (e.from().equals(eIn.from())) && (e.to().equals(eOut.to())) ) */){
                                    present = true;
                                    break;
                                }
                            }

                            //S'il n'existe pas, on l'ajoute au nouveau graph
                            if(!present) {
                                result.addEdge(eIn.from(), eOut.to());
                               // result.addEdge(eIn.to(), eOut.from());
                                 result.addEdge(eOut.to(), eIn.from());

                                change = true;

                            }
                            present = false;
                        }
                    }
                }
            }
        }while (change);

        return result;
    }

  public static UndirectedGraf fromDotFile(String filename){
        return null;
  }

  // voir si utile
  public List<Node> getSuccessorsMulti(Node n){
        return null;
  }

  /*
  /dfs
   */

    /*
    BFS
     */

}
