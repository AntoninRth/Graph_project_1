package m1graf2023;

import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * Class for an undirected graph
 * @author Romain CHUAT
 * @author Antonin ROTHE
 */


public class UndirectedGraf extends Graf{

    /**
     * constructor of an empty graph
     */
    public UndirectedGraf(){
        super();
    }

    /**
     * constructor of a graph from an array
     * @param SuccessorArray who contains node and their edges
     */
    public UndirectedGraf(int ... SuccessorArray){
        super(SuccessorArray);
    }

    /**
     * Search the neighbors of a node
     * @param n the node we used
     * @return the list of the successor
     */
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

    /**
     * Search the neighbors of a node
     * @param nodeID the id of the node we used
     * @return the list of the successor
     */
    public List<Node> getSuccessors(int nodeID){
        Node n = new Node(nodeID);
        return getSuccessors(n);
    }

    /**
     * Search the successors of a node, they can be added twice in the list
     * @param n the node we used
     * @return the list of the successor
     */
    public List<Node> getSuccessorsMulti(Node n){
        List<Node> result = super.getSuccessorsMulti(n);
        for(List<Edge> edges: adjEdList.values()){
            for(Edge e : edges){
                if(e.to().equals(n)){
                    result.add(e.from());
                }
            }
        }

        Collections.sort(result);

        return result;
    }

    /**
     * Search the successors of a node, they can be added twice in the list
     * @param nodeID the id of the node we used
     * @return the list of the successor
     */
    public List<Node> getSuccessorsMulti(int nodeID){
        Node newNode = new Node(nodeID);
        return getSuccessorsMulti(newNode);
    }


    /**
     * Add an edge to the current graph
     * @param from first node of the edge
     * @param to second node of the edge
     */
    public void addEdge(Node from, Node to){
        if(!this.existsNode(from.getId())){
            this.addNode(from);
        }
        if(!this.existsNode(to.getId())){
            this.addNode(to);
        }
        Edge e = new Edge(from,to);

        adjEdList.get(from).add(e);
        Collections.sort(adjEdList.get(from));
        Collections.sort(adjEdList.get(to));

    }

    /**
     * Add an edge to the current graph
     * @param from first node of the edge
     * @param to second node of the edge
     * @param weight of the edge
     */
    public void addEdge(Node from, Node to, int weight){
        if(!this.existsNode(from.getId())){
            this.addNode(from);
        }

        if(!this.existsNode(to.getId())){
            this.addNode(to);
        }

        Edge e = new Edge(from,to, weight);
        adjEdList.get(from).add(e);
        Collections.sort(adjEdList.get(from));
    }

    /**
     * Add an edge to the current graph
     * @param fromID of the first node
     * @param toID of the second node
     */
    public void addEdge(int fromID, int toID){
        Edge e = new Edge(fromID,toID);
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
     * In and out degree are the same edges, so we count each edge just one time
     * @param nodeID the node ID
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

        List<Edge> resList = new ArrayList<>();

        for(List<Edge> currList: adjEdList.values()){
            for(Edge e: currList){
                if(e.from().equals(n)){
                    if(!resList.contains(e)){
                        resList.add(e);
                    }
                }else {
                if(e.to().equals(n)){
                    Edge currentEdge = new Edge(e.to(),e.from());
                    if(!resList.contains(currentEdge)){
                        resList.add(currentEdge);
                    }
                }
                }
            }
        }
        return resList;
    }

    /**
     * Get the out edges of the node n
     * @param nodeID the node id
     * @return a list of edges
     */
    public List<Edge> getOutEdges(int nodeID){
        Node n = new Node(nodeID);

        return getOutEdges(n);
    }

    /**
     * Get the in edges of the node n
     * @param n the node
     * @return a list of edges
     */
    public List<Edge> getInEdges(Node n){
        return getOutEdges(n);
    }

    /**
     * Get the in edges of the node n
     * @param nodeID the node id
     * @return a list of edges
     */
    public List<Edge> getInEdges(int nodeID){
        Node n = new Node(nodeID);

        return getInEdges(n);
    }

    /**
     * Get the union between in and out edges of node n
     * @param n the node
     * @return a list of edges
     */
    public List<Edge> getIncidentEdges(Node n){
        return getInEdges(n);
    }

    /**
     * Get the union between in and out edges of node n
     * @param nodeID the node id
     * @return a list of edges
     */
    public List<Edge> getIncidentEdges(int nodeID){
        return getIncidentEdges(new Node(nodeID));
    }


    /**
     * Print the current graph
     * @return the printing of the graph
     */
    public String toDotString(){
        StringBuilder str = new StringBuilder("graph {\nrankdir=LR\n");
        for (Map.Entry<Node, List<Edge>> entry : adjEdList.entrySet()) {

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
                                if( ( (e.from().equals(eIn.from())) && (e.to().equals(eOut.to())) ) /*|| ( (e.to().equals(eIn.to())) && (e.from().equals(eOut.from())) )*/ ){
                                    present = true;
                                    break;
                                }
                            }
                            //S'il n'existe pas, on l'ajoute au nouveau graph
                            if(!present) {
                                result.addEdge(eIn.from(), eOut.to());
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

    /**
     * Get a graph from a file
     * @param filename name of the file
     * @return a new graph
     */
  public static UndirectedGraf fromDotFile(String filename){
      try {
          File myObj = new File(filename+".gv");
          if(!myObj.exists()){
              myObj = new File(filename+".dot");
          }
          Scanner myReader = new Scanner(myObj);
          UndirectedGraf g =  new UndirectedGraf();
          while (myReader.hasNextLine()) {
              String line = myReader.nextLine().trim();
              if (line.startsWith("graph")) {
                  continue;
              }
              if (line.startsWith("rankdir")) {
                  continue;
              }
              if (line.equals("}")) {
                  break;
              }
              String[] firstPart = line.split("\\[");
              String[] nodeParth = firstPart[0].split("--");


              if (nodeParth.length == 2) {
                  int fromNode = Integer.parseInt(nodeParth[0].trim());
                  int toNode = Integer.parseInt(nodeParth[1].trim());

                  Node from = new Node(fromNode);
                  Node to = new Node(toNode);

                  if(!g.existsNode(from)){
                      g.addNode(from);
                  }
                  if(!g.existsNode(to)){
                      g.addNode(to);
                  }

                  if(firstPart.length == 2){
                      String[] secondPart = firstPart[1].split(",");
                      String[] labelPart = secondPart[0].split("=");
                      String[] lenPart = secondPart[1].split("=");
                      g.addEdge(from,to, Integer.parseInt(lenPart[1].substring(0, lenPart[1].length()-1)), Integer.parseInt(labelPart[1]));
                  }else{
                      g.addEdge(from,to);
                  }
              } else {
                  int node = Integer.parseInt(nodeParth[0].trim());

                  Node n = new Node(node);
                  if(!g.existsNode(n)){
                      g.addNode(n);
                  }
              }

          }
          myReader.close();
          return g;
      } catch (IOException e) {
          System.out.println("An error occurred.");
          e.printStackTrace();
      }
      return null;
  }

    /**
     * Get a graph from a file
     * @param filename name of the file
     * @param extension extension of the file
     * @return a new graph
     */
    public static Graf fromDotFile(String filename, String extension){
        try {
            File myObj = new File(filename+extension);
            Scanner myReader = new Scanner(myObj);
            Graf g =  new Graf();
            while (myReader.hasNextLine()) {
                String line = myReader.nextLine().trim();
                if (line.startsWith("graph")) {
                    continue;
                }
                if (line.equals("}")) {
                    break;
                }
                String[] parts = line.split("--");
                if (parts.length == 2) {
                    int fromNode = Integer.parseInt(parts[0].trim());
                    int toNode = Integer.parseInt(parts[1].trim());

                    Node from = new Node(fromNode);
                    Node to = new Node(toNode);

                    if(!g.existsNode(from)){
                        g.addNode(from);
                    }
                    if(!g.existsNode(to)){
                        g.addNode(to);
                    }
                    if(!g.existsEdge(from,to)){
                        g.addEdge(from,to);
                    }
                }
            }
            myReader.close();
            return g;
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return null;
    }


    /**
     * Print the current graph in a matrix
     * @return the matrix
     */
    public int[][] toAdjMatrix(){
        int MAX = largestNodeId();
        int[][] matrixResult = new int[MAX][MAX];
        for(int i = 0; i < MAX; i++){
            for(int j = 0; j < MAX; j++){
                matrixResult[i][j] = 0;
            }
        }

        for(Node n: adjEdList.keySet()){
            for(Edge e: getIncidentEdges(n)){
                matrixResult[n.getId()-1][e.to().getId()-1]++;
            }
        }
        return matrixResult;
    }

    /**
     * Get the reverse of the current graph
     * @return the reversed graph
     */
    public Graf getReverse(){
        Graf reversedGraf = new UndirectedGraf();
        for(Node n : adjEdList.keySet()){
            List<Edge> neighbors = adjEdList.get(n);
            for(Edge neighbor : neighbors){
                reversedGraf.addEdge(neighbor.to(),n);
            }
        }
        return reversedGraf;
    }


  /*
  /dfs
   */

    /*
    BFS
     */

}
