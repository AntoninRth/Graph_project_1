package m1graf2023;

import java.util.*;

public class Graf {
    private Map<Node,List<Edge>> adjEdList;

    public Graf(){
        adjEdList = new TreeMap<>();
    }
    public Graf(int ... SuccessorArray){
        int j = 0;
        Node currentNode = new Node(j);
        List<Edge> edgeList =  new ArrayList<>();
        for(int i = 0; i< SuccessorArray.length; i++){
            Edge edge = new Edge(j, SuccessorArray[i]);
            edgeList.add(edge);
            if(SuccessorArray[i] == 0){
                adjEdList.put(currentNode,edgeList);
                edgeList.clear();
                j++;
            }
        }
    }

    public int nbNodes(){
        return adjEdList.size();
    }

    public boolean existsNode(Node n){
        return adjEdList.containsKey(n);
    }

    public boolean existsNode(int nodeID){
        Node n = new Node(nodeID);
        return adjEdList.containsKey(n);
    }

    public Node getNode(int id){
        for(Node n : adjEdList.keySet()){
            if(n.getId() == id){
                return n;
            }
        }
        return null;
    }

    public boolean addNode(Node n){
        return this.adjEdList.put(n, new ArrayList<>()) != null;
    }

    public boolean addNode(int idNode){
        Node n = new Node(idNode);
        return this.adjEdList.put(n, new ArrayList<>()) != null;
    }

    //Demander pour le type de retour de la fonction
    public boolean removeNode(Node n){
        return this.adjEdList.remove(n) != null;
    }

    public boolean removeNode(int nodeID){
        Node n = new Node(nodeID);
        return this.adjEdList.remove(n) != null;
    }

    public List<Node> getSuccessors(Node n){
        return null;
    }

    public List<Node> getSuccessors(int nodeID){
        return null;
    }


    int[] toSuccessorArray(){
        return null;
    }

    int[][] toAdjMatrix(){
        return null;
    }

    Graf getReverse(){
        return null;
    }

    Graf getTransitiveClosure(){
        return null;
    }

    List<Node> getDFS(){
        return null;
    }
    List<Node> getDFS(Node u){
        return null;
    }

    List<Node> getBFS(){
        return null;
    }

    List<Node> getBFS(Node u){
        return null;
    }

    List<Node> getDFSWithVisitInfo(Map<Node, NodeVisitInfo> nodeVisit,
                                   Map<Edge, EdgeVisitType> edgeVisit){
        return null;
    }
    List<Node> getDFSWithVisitInfo(Node u, Map<Node, NodeVisitInfo> nodeVisit,
                                   Map<Edge, EdgeVisitType> edgeVisit){
        return null;
    }

    static Graf fromDotFile(String filename){
        return null;
    }

    static Graf fromDotFile(String filename, String extension){
        return null;
    }

    String toDotString(){
        return "";
    }

    void toDotFile(String fileName){}

    void toDotFile(String fileName, String extension){}


}

