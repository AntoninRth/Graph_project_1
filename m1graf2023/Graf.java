package m1graf2023;

import java.io.FileReader;
import java.util.*;
import java.io.File;
import java.io.IOException;
import java.io.FileWriter;

public class Graf {
    protected static Map<Node,List<Edge>> adjEdList;

    private static List<Node> visitedNodes = new ArrayList<>();
    private static int time;

    public Graf(){
        adjEdList = new TreeMap<>();
    }
    public Graf(int ... SuccessorArray){
        int j = 1;
        adjEdList = new TreeMap<>();
        List<Edge> edgeList =  new ArrayList<>();
        for (int k : SuccessorArray) {
            if(k != 0){
                Edge edge = new Edge(j, k);
                edgeList.add(edge);
            }else{
                adjEdList.put(new Node(j), new ArrayList<>(edgeList));
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
        int largestID = -1000000;

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
        int res = 0;
        for(List<Edge> currList: adjEdList.values()){
            res += currList.size();
        }
        return res;
    }

    public boolean existsEdge(Node u, Node v){
        if(adjEdList.get(v) == null || adjEdList.get(u) == null){
            return false;
        }

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
        if(adjEdList.get(v) == null || adjEdList.get(u) == null){
            return false;
        }

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

    public boolean removeEdge(Node from, Node to){
        return adjEdList.get(from).remove(new Edge(from, to));
    }

    public boolean removeEdge(int fromID, int toID){
        Node from = new Node(fromID);
        Node to = new Node(toID);
        if(existsNode(from) && existsNode(to) && existsEdge(from,to)){
            return adjEdList.get(from).remove(new Edge(from, to));
        }
        return false;
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
                if(e.to().equals(n)){
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
        int i = 0;
        String str = "";
        for(Map.Entry<Node, List<Edge>> entry  : adjEdList.entrySet()){
            List<Edge> edges = entry.getValue();
                i++;
                for(int j = 0;  j < edges.size();  j ++ ){
                    str += "" +edges.get(j).toNode.idNode;
                }
            str += "0";
        }
        int [] successorArray = new int [str.length()];
        for(int k = 0; k < str.length(); k++){
            int val = Character.getNumericValue(str.charAt(k));
            successorArray[k] = val;
        }
        return successorArray;
    }

    public int[][] toAdjMatrix(){
        int MAX = adjEdList.size();
        int[][] matrixResult = new int[MAX][MAX];
        for(int i = 0; i < MAX; i++){
            for(int j = 0; j < MAX; j++){
                matrixResult[i][j] = 0;
            }
        }

        for(Node n: adjEdList.keySet()){
            for(Edge e : adjEdList.get(n)){
                matrixResult[n.getId()-1][e.to().getId()-1]++;
            }
        }
        return matrixResult;
    }

    public Graf getReverse(){
        Graf reversedGraf = new Graf();

        for(Node n : adjEdList.keySet()){
            List<Edge> neighbors = adjEdList.get(n);
            for(Edge neighbor : neighbors){
                reversedGraf.addEdge(neighbor.to(),n);
            }
        }
        return reversedGraf;
    }

    public Graf getTransitiveClosure(){
        Graf result = new Graf();
        List<Edge> listOfEdge = new ArrayList<>();
        boolean present = false;

        for(Edge e: getAllEdges()){
            if(!e.isSelfLoop()){
                for(Edge currEdge: listOfEdge){
                    if((e.from().equals(currEdge.from())) && (e.to().equals(currEdge.to()))){
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
        for(Node n : this.getAllNodes()){
            for(Edge eIn : this.getInEdges(n)){
                for(Edge eOut : this.getOutEdges(n)){
                    if(!eIn.from().equals(eOut.to())){
                        List<Edge> existEdge = result.getOutEdges(eIn.from());
                        for(Edge e : existEdge){
                            if((e.from().equals(eIn.from())) && (e.to().equals(eOut.to()))){
                                present = true;
                                break;
                            }
                        }
                        if(!present) {
                            result.addEdge(eIn.from(), eOut.to());
                        }
                        present = false;
                    }
                }
            }
        }
        return result;
    }

    public List<Node> getDFS(){

        visitedNodes.clear();

        int smallestID = smallestNodeId();

        return getDFS(smallestID);
    }
    public List<Node> getDFS(Node u){
        visitedNodes.add(u);

        boolean present = false;

        for (Edge e: getOutEdges(u)){
            for(Node n: visitedNodes){
                if(e.to().equals(n)){
                    present = true;
                }
            }
            if(!present){
                getDFS(e.to());
            }
            present = false;
        }
        return visitedNodes;
    }

    public List<Node> getDFS(int nodeID){
        Node u = new Node(nodeID);
        visitedNodes.add(u);

        boolean present = false;

        for (Edge e: getOutEdges(u)){
            for(Node n: visitedNodes){
                if(e.to().equals(n)){
                    present = true;
                }
            }
            if(!present){
                getDFS(e.to());
            }
            present = false;
        }
        return visitedNodes;
    }

    public List<Node> getBFS(){
        visitedNodes.clear();
        int smallestID = smallestNodeId();
        return getBFS(smallestID);
    }

    public List<Node> getBFS(Node u){
        Queue<Node> Q = new ArrayDeque<>();
        boolean present = false;
        visitedNodes.add(u);
        Q.add(u);

        while(!Q.isEmpty()){
            Node i = Q.poll();
            for (Edge e: getOutEdges(i)){
                for(Node n: visitedNodes){
                    if(e.to().equals(n)){
                        present = true;
                    }
                }
                if(!present){
                    visitedNodes.add(e.to());
                    Q.add(e.to());
                }
                present = false;
            }
        }
        return visitedNodes;
    }

    public List<Node> getBFS(int nodeID){
        Node u = new Node(nodeID);
        Queue<Node> Q = new ArrayDeque<>();
        boolean present = false;
        visitedNodes.add(u);
        Q.add(u);

        while(!Q.isEmpty()){
            Node i = Q.poll();
            for (Edge e: getOutEdges(i)){
                for(Node n: visitedNodes){
                    if(e.to().equals(n)){
                        present = true;
                    }
                }
                if(!present){
                    visitedNodes.add(e.to());
                    Q.add(e.to());
                }
                present = false;
            }
        }
        return visitedNodes;
    }

    public List<Node> getDFSWithVisitInfo(Map<Node, NodeVisitInfo> nodeVisit,
                                   Map<Edge, EdgeVisitType> edgeVisit){
        visitedNodes.clear();

        for(Node n: adjEdList.keySet()){
            NodeVisitInfo nodeInfo = new NodeVisitInfo(NodeColour.WHITE, null);
            nodeVisit.put(n,nodeInfo);
        }

        time = 0;

        for(Node n: adjEdList.keySet()){
            if(nodeVisit.get(n).colour == NodeColour.WHITE){
                getDFSWithVisitInfo(n,nodeVisit,edgeVisit);
            }
        }

        return visitedNodes;
    }
    public List<Node> getDFSWithVisitInfo(Node u, Map<Node, NodeVisitInfo> nodeVisit,
                                   Map<Edge, EdgeVisitType> edgeVisit){
        time++;
        nodeVisit.get(u).discovery = time;
        nodeVisit.get(u).colour = NodeColour.GRAY;
        visitedNodes.add(u);
        for(Edge v: adjEdList.get(u)){
            Node to = null;
            for(Node n: nodeVisit.keySet()){
                if(n.equals(v.to())){
                    to = n;
                }
            }
            if(nodeVisit.get(to).colour == NodeColour.BLACK){
                edgeVisit.put(v,EdgeVisitType.BACKWARD);
            } else if (nodeVisit.get(u).colour == NodeColour.BLACK){
                edgeVisit.put(v,EdgeVisitType.FORWARD);
            } else if (nodeVisit.get(to).colour == NodeColour.GRAY) {
                edgeVisit.put(v,EdgeVisitType.CROSS);
            }else{
                edgeVisit.put(v,EdgeVisitType.TREE);
            }
            if(nodeVisit.get(to).colour == NodeColour.WHITE){
                nodeVisit.get(to).predecessor = u;
                getDFSWithVisitInfo(to,nodeVisit,edgeVisit);
            }
        }
        nodeVisit.get(u).colour = NodeColour.BLACK;
        time++;
        nodeVisit.get(u).finished = time;
        return visitedNodes;
    }

    public static Graf fromDotFile(String filename){
        try {
            File myObj = new File(filename+".gv");
            if(!myObj.exists()){
                myObj = new File(filename+".dot");
            }
            Scanner myReader = new Scanner(myObj);
            Graf g =  new Graf();
            while (myReader.hasNextLine()) {
                String line = myReader.nextLine().trim();
                if (line.startsWith("digraph")) {
                    continue;
                }
                if (line.equals("}")) {
                    break;
                }
                String[] parts = line.split("->");
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
     @TO-DO : faire 3 methodes pour convertir le graph en string compatible avec
     d'autre format que .gv
     */
    public static Graf fromDotFile(String filename, String extension){
        try {
            File myObj = new File(filename+extension);
            Scanner myReader = new Scanner(myObj);
            Graf g =  new Graf();
            while (myReader.hasNextLine()) {
                String line = myReader.nextLine().trim();
                if (line.startsWith("digraph")) {
                    continue;
                }
                if (line.equals("}")) {
                    break;
                }
                String[] parts = line.split("->");
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

    public String toDotString(){
        String str = "digraph figureOne {\n";
        for (Map.Entry<Node, List<Edge>> entry : adjEdList.entrySet()) {
            List<Edge> edges = entry.getValue();
            for(int i = 0; i< edges.size(); i++){
                str += edges.get(i)+"\n";
            }
        }
        str += "}";
        return str;
    }

    public void toDotFile(String fileName){
        try {
            File myObj = new File(fileName+".gv");
            if (myObj.createNewFile()) {
                System.out.println("File created: " + myObj.getName());

            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("Error");
            e.printStackTrace();
        }
        try {
            FileWriter myWriter = new FileWriter(fileName+".gv");
            String graphe_toString = this.toDotString();
            myWriter.write(graphe_toString);
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }


    }

    /**
        @TO-DO : faire 3 methodes pour convertir le graph en string compatible avec
        d'autre format que .gv
     */

    public void toDotFile(String fileName, String extension){
        try {
            File myObj = new File(fileName+extension);
            if (myObj.createNewFile()) {
                System.out.println("File created: " + myObj.getName());

            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("Error");
            e.printStackTrace();
        }
        try {
            FileWriter myWriter = new FileWriter(fileName+extension);
            String graphe_toString = this.toDotString();
            myWriter.write(graphe_toString);
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }


}

