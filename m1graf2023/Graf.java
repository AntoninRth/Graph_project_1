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
        for (int k : SuccessorArray) {
            Edge edge = new Edge(j, k);
            edgeList.add(edge);
            if (k == 0) {
                adjEdList.put(currentNode, edgeList);
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
        List<Node> res = new ArrayList<>();
        for(Edge curr : adjEdList.get(n)){
            if(!res.contains(curr.to())){
                res.add(curr.to());
            }
        }
        return res;
    }

    public List<Node> getSuccessors(int nodeID){
        List<Node> res = new ArrayList<>();
        Node newNode = new Node(nodeID);
        for(Edge curr : adjEdList.get(newNode)){
            if(!res.contains(curr.to())){
                res.add(curr.to());
            }
        }
        return res;
    }

    public List<Node> getSuccessorsMulti(Node n){
        List<Node> res = new ArrayList<>();
        for(Edge curr : adjEdList.get(n)){
                res.add(curr.to());
        }
        return res;
    }

    public List<Node> getSuccessorsMulti(int nodeID){
        List<Node> res = new ArrayList<>();
        Node newNode = new Node(nodeID);
        for(Edge curr : adjEdList.get(newNode)){
                res.add(curr.to());
        }
        return res;
    }

    public boolean adjacent(Node u, Node v){
        for(Edge curr : adjEdList.get(u)){
            if(curr.to().equals(v)){
                return true;
            }
        }

        for(Edge curr : adjEdList.get(v)){
            if(curr.to().equals(u)){
                return true;
            }
        }
        return false;
    }

    public boolean adjacent(int u, int v){
        Node uNode = new Node(u);
        Node vNode = new Node(v);
        for(Edge curr : adjEdList.get(uNode)){
            if(curr.to().equals(vNode)){
                return true;
            }
        }

        for(Edge curr : adjEdList.get(vNode)){
            if(curr.to().equals(uNode)){
                return true;
            }
        }
        return false;
    }

    public List<Node> getAllNodes(){
        return new ArrayList<>(adjEdList.keySet());
    }

    //Demander pour le min et max des ID
    public int largestNodeId(){
        int largestID = 0;

        for(Node n : adjEdList.keySet()){
            if(largestID < n.getId()){
                largestID = n.getId();
            }
        }

        return largestID;
    }

    public int smallestNodeId(){
        int smallID = 1000000;

        for(Node n : adjEdList.keySet()){
            if(smallID > n.getId()){
                smallID = n.getId();
            }
        }

        return smallID;
    }

    public int nbEdges(){
        Collection<List<Edge>> allEdges = adjEdList.values();
        return allEdges.size();
    }

    public boolean existsEdge(Node u, Node v){
        for(Edge curr: adjEdList.get(u)){
            if(curr.to().equals(v)){
                return true;
            }
        }

        for(Edge curr: adjEdList.get(v)){
            if(curr.to().equals(u)){
                return true;
            }
        }
        return false;
    }

    public boolean existsEdge(int uID, int vID){
        Node v = new Node(vID);
        Node u = new Node(uID);
        for(Edge curr: adjEdList.get(u)){
            if(curr.to().equals(v)){
                return true;
            }
        }

        for(Edge curr: adjEdList.get(v)){
            if(curr.to().equals(u)){
                return true;
            }
        }
        return false;
    }

    public void addEdge(Node from, Node to){
        if(!this.existsNode(from.getId())){
            this.addNode(from);
        }

        if(!this.existsNode(to.getId())){
            this.addNode(to);
        }

        Edge e = new Edge(from,to);
        adjEdList.get(from).add(e);
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

    public String toDotString(){
        return "";
    }

    void toDotFile(String fileName){}

    void toDotFile(String fileName, String extension){}


}

