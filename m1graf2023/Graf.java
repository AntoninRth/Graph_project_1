package m1graf2023;
import java.io.FileReader;
import java.util.*;
import java.io.File;
import java.io.IOException;
import java.io.FileWriter;

/**
 * Class for a directed graph
 * @author Romain CHUAT
 * @author Antonin ROTHE
 */

public class Graf {
    /**
     * protected map for all the nodes and their edges of the graph
     */
    protected Map<Node,List<Edge>> adjEdList;

    /**
     * private list for the visited Nodes
     */
    private static List<Node> visitedNodes = new ArrayList<>();

    /**
     * private Int for the moment we visited a node in DFS
     */
    private static int time;

    /**
     * constructor of an empty graph
     */
    public Graf(){
        adjEdList = new TreeMap<>();
    }

    /**
     * constructor of a graph from an array
     * @param SuccessorArray who contains node and their edges
     */
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

    /**
     * @return the number of nodes in the graph
     */
    public int nbNodes(){
        return adjEdList.size();
    }

    /**
     * Search if a node is present in the graph
     * @param n the node to search
     * @return true if the node is in the graph, false otherwise
     */
    public boolean existsNode(Node n){
        return adjEdList.containsKey(n);
    }

    /**
     * Search if a node is present in the graph
     * @param nodeID the ID of the node to search
     * @return true if the node is in the graph, false otherwise
     */
    public boolean existsNode(int nodeID){
        Node n = new Node(nodeID);
        return existsNode(n);
    }

    /**
     * Return a node of the graph from his ID
     * @param id of the node
     * @return the node if exists, or null otherwise
     */
    public Node getNode(int id){
        for(Node n : adjEdList.keySet()){
            if(n.getId() == id){
                return n;
            }
        }
        return null;
    }

    /**
     * Add a node in the graph
     * @param n the node to add
     * @return true if node has been added, false otherwise
     */
    public boolean addNode(Node n){
        List<Edge> edgeList =  new ArrayList<>();
        return adjEdList.put(n, edgeList) == null;
    }

    /**
     * Add a node in the graph from his ID
     * @param idNode the id of the node to add
     * @return true if node has been added, false otherwise
     */
    public boolean addNode(int idNode){
        Node n = new Node(idNode);
        return addNode(n);
    }

    /**
     * remove a node in the graph
     * @param n the node to removed
     * @return true if the node has been removed, false otherwise
     */
    public boolean removeNode(Node n){
        for(Node u : adjEdList.keySet()){
            adjEdList.get(u).removeIf(curr -> curr.to().equals(n));
        }

        return adjEdList.remove(n) != null;
    }

    /**
     * remove a node in the graph from his id
     * @param nodeID the if of the node to removed
     * @return true if the node has been removed, false otherwise
     */
    public boolean removeNode(int nodeID){
        Node n = new Node(nodeID);
        return removeNode(n);
    }

    /**
     * Search the successors of a node
     * @param n the node we used
     * @return the list of the successor
     */
    public List<Node> getSuccessors(Node n){
        List<Node> res = new ArrayList<>();
        boolean present = false;
        for(Edge curr : adjEdList.get(n)){
            present = false;
            for(Node u: res){
                if(u.equals(curr.to())){
                    present = true;
                }
            }
            if(!present){
                res.add(curr.to());
            }

        }
        return res;
    }

    /**
     * Search the successors of a node
     * @param nodeID the id of the node we used
     * @return the list of the successor
     */
    public List<Node> getSuccessors(int nodeID){
        Node newNode = new Node(nodeID);
        return getSuccessors(newNode);
    }

    /**
     * Search the successors of a node, they can be added twice in the list
     * @param n the node we used
     * @return the list of the successor
     */
    public List<Node> getSuccessorsMulti(Node n){
        List<Node> res = new ArrayList<>();
        for(Edge curr : adjEdList.get(n)){
                res.add(curr.to());
        }
        return res;
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
     * Two nodes are adjacent if they share a common edge
     * @param u the first node
     * @param v the second node
     * @return true if the nodes share an edge, false otherwise
     */
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

    /**
     * Two nodes are adjacent if they share a common edge
     * @param u the first node id
     * @param v the second node id
     * @return true if the nodes share an edge, false otherwise
     */
    public boolean adjacent(int u, int v){
        Node uNode = new Node(u);
        Node vNode = new Node(v);
        return adjacent(uNode, vNode);
    }

    /**
     * @return all the nodes of the graph
     */
    public List<Node> getAllNodes(){
        return new ArrayList<>(adjEdList.keySet());
    }

    /**
     * @return the largest id of a node found in the graph
     */
    public int largestNodeId(){
        int largestID = -1000000;

        for(Node n : adjEdList.keySet()){
            if(largestID < n.getId()){
                largestID = n.getId();
            }
        }

        return largestID;
    }

    /**
     * @return the smallest id found in the graph
     */
    public int smallestNodeId(){
        int smallID = 1000000;

        for(Node n : adjEdList.keySet()){
            if(smallID > n.getId()){
                smallID = n.getId();
            }
        }

        return smallID;
    }

    /**
     * @return the number of the edges in the graph
     */
    public int nbEdges(){
        int res = 0;
        for(List<Edge> currList: adjEdList.values()){
            res += currList.size();
        }
        return res;
    }

    /**
     * Search an edge in the graph
     * @param u the first node
     * @param v the second node
     * @return true if the edge exist, false otherwise
     */
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

    /**
     * Search an edge in the graph
     * @param uID the id of the first node
     * @param vID the id of the second node
     * @return true if the edge exist, false otherwise
     */
    public boolean existsEdge(int uID, int vID){
        Node v = new Node(vID);
        Node u = new Node(uID);
        return existsEdge(u,v);
    }

    /**
     * Add an edge to the current graph
     * @param from node of the edge
     * @param to node of the edge
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
    }

    /**
     * Add an edge to the current graph
     * @param from node of the edge
     * @param to node of the edge
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
     * @param from node of the edge
     * @param to node of the edge
     * @param weight of the edge
     * @param label of the edge
     */
    public void addEdge(Node from, Node to, int weight, int label){
        if(!this.existsNode(from.getId())){
            this.addNode(from);
        }

        if(!this.existsNode(to.getId())){
            this.addNode(to);
        }

        Edge e = new Edge(from,to, weight, label);
        adjEdList.get(from).add(e);
        Collections.sort(adjEdList.get(from));
    }

    /**
     * Add an edge to the current graph
     * @param e the edge
     */
    public void addEdge(Edge e){
        if(!this.existsNode(e.from().getId())){
            this.addNode(e.from());
        }

        if(!this.existsNode(e.to().getId())){
            this.addNode(e.to());
        }

        adjEdList.get(e.from()).add(e);
        Collections.sort(adjEdList.get(e.from()));
    }

    /**
     * Add an edge to the current graph
     * @param fromID of the from node
     * @param toID of the to node
     */
    public void addEdge(int fromID, int toID){
        if(!this.existsNode(fromID)){
            this.addNode(fromID);
        }

        if(!this.existsNode(toID)){
            this.addNode(toID);
        }
        Edge e = new Edge(fromID,toID);
        adjEdList.get(new Node(fromID)).add(e);
        Collections.sort(adjEdList.get(new Node(fromID)));
    }

    /**
     * Add an edge to the current graph
     * @param fromID of the from node
     * @param toID of the to node
     * @param weight of the edge
     */
    public void addEdge(int fromID, int toID, int weight){
        if(!this.existsNode(fromID)){
            this.addNode(fromID);
        }

        if(!this.existsNode(toID)){
            this.addNode(toID);
        }
        Edge e = new Edge(fromID,toID,weight);
        adjEdList.get(new Node(fromID)).add(e);
    }

    /**
     * Add an edge to the current graph
     * @param fromID of the from node
     * @param toID of the to node
     * @param weight of the edge
     * @param label of the edge
     */
    public void addEdge(int fromID, int toID, int weight, int label){
        if(!this.existsNode(fromID)){
            this.addNode(fromID);
        }

        if(!this.existsNode(toID)){
            this.addNode(toID);
        }
        Edge e = new Edge(fromID,toID,weight, label);
        adjEdList.get(new Node(fromID)).add(e);
    }

    /**
     * Remove an edge from the graph
     * @param from node of the edge
     * @param to node of the edge
     * @return true if edge is removed, false otherwise
     */
    public boolean removeEdge(Node from, Node to){
        for(Edge e : adjEdList.get(from)){
            if(e.to().equals(to)){
                return adjEdList.get(from).remove(e);
            }
        }
        return false;
    }

    /**
     * Remove an edge from the graph
     * @param fromID id of the from node of the edge
     * @param toID id of the to node of the edge
     * @return true if edge is removed, false otherwise
     */
    public boolean removeEdge(int fromID, int toID){
        Node from = new Node(fromID);
        Node to = new Node(toID);
        if(existsNode(from) && existsNode(to) && existsEdge(from,to)){
            return removeEdge(from,to);
        }
        return false;
    }

    /**
     * Remove an edge from the graph
     * @param e the edge to remove
     * @return true if edge is removed, false otherwise
     */
    public boolean removeEdge(Edge e){
        if(existsNode(e.from()) && existsNode(e.to()) && existsEdge(e.from(),e.to())){
            return removeEdge(e.from(),e.to());
        }
        return false;
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
     * Get the in edges of the node n
     * @param n the node
     * @return a list of edges
     */
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
        List<Edge> resList = new ArrayList<>();
        resList = getInEdges(n);

        List <Edge> tempList = getOutEdges(n);
        resList.addAll(tempList);

        return resList;
    }

    /**
     * Get the union between in and out edges of node n
     * @param nodeID the node id
     * @return a list of edges
     */
    public List<Edge> getIncidentEdges(int nodeID){
        Node n = new Node(nodeID);

        return getInEdges(n);
    }

    /**
     * Get edges going from u to v
     * @param u first node
     * @param v second node
     * @return a list of edges
     */
    public List<Edge> getEdges(Node u, Node v){
        List<Edge> resList = new ArrayList<>();

        for(Edge e: adjEdList.get(u)){
            if(e.to().equals(v)){
                resList.add(e);
            }
        }

        return resList;
    }

    /**
     * Get edges going from u to v
     * @param fromID first node id
     * @param toID second node id
     * @return a list of edges
     */
    public List<Edge> getEdges(int fromID, int toID){
        Node u = new Node(fromID);
        Node v = new Node(toID);

        return getEdges(u,v);
    }

    /**
     * @return all the edges of the graph
     */
    public List<Edge> getAllEdges(){
        List<Edge> resList = new ArrayList<>();

        for(List<Edge> currList: adjEdList.values()){
            resList.addAll(currList);
        }

        return resList;
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
        return nEdge.size();
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
     * Get the union between in and out degree of the node n
     * @param n the node
     * @return the number of edges where n is involved
     */
    public int degree(Node n){
        int res = this.inDegree(n);
        res += this.outDegree(n);
        return res;
    }

    /**
     * Get the union between in and out degree of the node n
     * @param nodeID the node id
     * @return the number of edges where n is involved
     */
    public int degree(int nodeID){
        Node n = new Node(nodeID);
        int res = this.inDegree(n);
        res += this.outDegree(n);
        return res;
    }

    /**
     * Print the current graph in a successor array
     * @return the successor array
     */
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
            for(Edge e : adjEdList.get(n)){
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
        Graf reversedGraf = new Graf();
        for(Node n : adjEdList.keySet()){
            List<Edge> neighbors = adjEdList.get(n);
            for(Edge neighbor : neighbors){
                reversedGraf.addEdge(neighbor.to(),n);
            }
        }
        return reversedGraf;
    }

    /**
     * Get the transitive closure of the current graph
     * @return a graph with transitive closure
     */
    public Graf getTransitiveClosure(){
        List<Edge> tempEdges = getAllEdges();
        Graf result = new Graf();
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
                                if((e.from().equals(eIn.from())) && (e.to().equals(eOut.to()))){
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
     * Get the DFS of the graph from the first node
     * @return list of nodes
     */
    public List<Node> getDFS(){

        visitedNodes.clear();

        int smallestID = smallestNodeId();

        return getDFS(smallestID);
    }

    /**
     * Get the DFS of the graph from a node
     * @param u the node
     * @return list of nodes
     */
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

    /**
     * Get the DFS of the graph from a node
     * @param nodeID id of the node
     * @return list of nodes
     */
    public List<Node> getDFS(int nodeID){
        Node u = new Node(nodeID);
        return getDFS(u);
    }

    /**
     * Get the BFS of the graph from the first node
     * @return list of nodes
     */
    public List<Node> getBFS(){
        visitedNodes.clear();
        int smallestID = smallestNodeId();
        return getBFS(smallestID);
    }

    /**
     * Get the BFS of the graph from a node u
     * @param u the node
     * @return list of nodes
     */
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

    /**
     * Get BFS of the graph from a node u
     * @param nodeID id of the node
     * @return list of nodes
     */
    public List<Node> getBFS(int nodeID){
        Node u = new Node(nodeID);
        return getBFS(u);
    }

    /**
     * Get DFS of the graph with information of nodes
     * @param nodeVisit information of nodes
     * @param edgeVisit information of edges
     * @return list of nodes
     */
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

    /**
     * Get DFS of the graph with information about the nodes
     * @param u the first node
     * @param nodeVisit information on the nodes
     * @param edgeVisit information on the edges
     * @return list of nodes
     */
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

    /**
     * Get a graph from a file
     * @param filename of the file
     * @return a new graph
     */
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
                if (line.startsWith("rankdir")) {
                    continue;
                }
                if (line.equals("}")) {
                    break;
                }
                String[] firstPart = line.split("\\[");
                String[] nodeParth = firstPart[0].split("->");


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
     @TO-DO : faire 3 methodes pour convertir le graph en string compatible avec
     d'autre format que .gv
     */
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
     * Print the current graph
     * @return the printing of the graph
     */
    public String toDotString(){
        StringBuilder str = new StringBuilder("digraph {\n");
        for (Map.Entry<Node, List<Edge>> entry : adjEdList.entrySet()) {
            if(degree(entry.getKey()) == 0){
                str.append(entry.getKey().toString()).append("\n");
            }else{

                List<Edge> edges = entry.getValue();
                for(int i = 0; i< edges.size(); i++){
                    str.append(edges.get(i));
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
     * Send a graph to a file
     * @param fileName name of the file
     */
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
    /**
     * Send a graph to a file
     * @param fileName name of the file
     * @param extension extension of the file
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

