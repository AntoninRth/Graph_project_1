package m1graf2023;

import java.util.*;

public class Graf {
    private Map<Node,List<Edge>> adjEdList;

    public Graf(){
        adjEdList = new TreeMap<>();
    }
    public Graf(int ... SuccessorArray){
        int j = 0;
        adjEdList = new TreeMap<>();
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

    //Demander pour le poids des edges (inclure dans toutes les fonctions ?)
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
        Node from = new Node(fromID);
        Node to = new Node(toID);

        if(!this.existsNode(from.getId())){
            this.addNode(from);
        }

        if(!this.existsNode(to.getId())){
            this.addNode(to);
        }

        Edge e = new Edge(from,to);
        adjEdList.get(from).add(e);
    }

    public boolean removeEdge(Node from, Node to){
        return adjEdList.get(from).remove(new Edge(from, to));
    }

    public boolean removeEdge(int fromID, int toID){
        Node from = new Node(fromID);
        Node to = new Node(toID);
        return adjEdList.get(from).remove(new Edge(from, to));
    }

    public boolean removeEdge(Edge e){
        return adjEdList.get(e.from()).remove(e);
    }

    public List<Edge> getOutEdges(Node n){

        return new ArrayList<>(adjEdList.get(n));
    }

    public List<Edge> getOutEdges(int nodeID){
        Node n = new Node(nodeID);

        return new ArrayList<>(adjEdList.get(n));
    }

    public List<Edge> getInEdges(Node n){
        List<Edge> resList = new ArrayList<>();

        for(List<Edge> currList: adjEdList.values()){
            for(Edge e: currList){
                if(e.to() == n){
                    if(!resList.contains(e)){
                        resList.add(e);
                    }
                }
            }
        }
        return resList;
    }

    public List<Edge> getInEdges(int nodeID){
        List<Edge> resList = new ArrayList<>();

        Node n = new Node(nodeID);

        for(List<Edge> currList: adjEdList.values()){
            for(Edge e: currList){
                if(e.to() == n){
                    if(!resList.contains(e)){
                        resList.add(e);
                    }
                }
            }
        }
        return resList;
    }

    public List<Edge> getIncidentEdges(Node n){
        List<Edge> resList = new ArrayList<>();
        resList = getInEdges(n);

        List <Edge> tempList = getOutEdges(n);
        resList.addAll(tempList);

        return resList;
    }

    public List<Edge> getIncidentEdges(int nodeID){
        Node n = new Node(nodeID);
        List<Edge> resList = new ArrayList<>();
        resList = getInEdges(n);

        List <Edge> tempList = getOutEdges(n);
        resList.addAll(tempList);

        return resList;
    }

    public List<Edge> getEdges(Node u, Node v){
        List<Edge> resList = new ArrayList<>();

        for(Edge e: adjEdList.get(u)){
            if(e.to().equals(v)){
                resList.add(e);
            }
        }

        return resList;
    }

    public List<Edge> getEdges(int fromID, int toID){
        Node u = new Node(fromID);
        Node v = new Node(toID);
        List<Edge> resList = new ArrayList<>();

        for(Edge e: adjEdList.get(u)){
            if(e.to().equals(v)){
                resList.add(e);
            }
        }

        return resList;
    }

    public List<Edge> getAllEdges(){
        List<Edge> resList = new ArrayList<>();

        for(List<Edge> currList: adjEdList.values()){
            resList.addAll(currList);
        }

        return resList;
    }

    public int inDegree(Node n){
        List<Edge> nEdge = adjEdList.get(n);
        return nEdge.size();
    }

    public int inDegree(int nodeID){
        Node n = new Node(nodeID);
        List<Edge> nEdge = adjEdList.get(n);
        return nEdge.size();
    }

    public int outDegree(Node n){
        int res = 0;
        for(List<Edge> edgeList: adjEdList.values()){
            for(Edge e: edgeList){
                if(e.to().equals(n)){
                    res++;
                }
            }
        }
        return res;
    }

    public int outDegree(int nodeID){
        Node n = new Node(nodeID);
        int res = 0;
        for(List<Edge> edgeList: adjEdList.values()){
            for(Edge e: edgeList){
                if(e.to().equals(n)){
                    res++;
                }
            }
        }
        return res;
    }

    public int degree(Node n){
        int res = this.inDegree(n);
        res += this.outDegree(n);
        return res;
    }

    public int degree(int nodeID){
        Node n = new Node(nodeID);
        int res = this.inDegree(n);
        res += this.outDegree(n);
        return res;
    }

    public int[] toSuccessorArray(){
        return null;
    }

    public int[][] toAdjMatrix(){
        return null;
    }

    public Graf getReverse(){
        return null;
    }

    public Graf getTransitiveClosure(){
        return null;
    }

    public List<Node> getDFS(){
        return null;
    }
    public List<Node> getDFS(Node u){
        return null;
    }

    public List<Node> getBFS(){
        return null;
    }

    public List<Node> getBFS(Node u){
        return null;
    }

    public List<Node> getDFSWithVisitInfo(Map<Node, NodeVisitInfo> nodeVisit,
                                   Map<Edge, EdgeVisitType> edgeVisit){
        return null;
    }
    public List<Node> getDFSWithVisitInfo(Node u, Map<Node, NodeVisitInfo> nodeVisit,
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

    public void toDotFile(String fileName){}

    public void toDotFile(String fileName, String extension){}


}

