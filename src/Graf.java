import java.util.*;

import com.sun.net.httpserver.Authenticator;
import src.Node;

public class Graf {
    private Map<Node,List<Edge>> adjEdList;

    public Graf(){
        adjEdList = new TreeMap<>();
    }
    public Graf(int ... SucessorArray){
        int j = 0;
        Node currentNode = new Node(j);
        List<Edge> edgeList =  new ArrayList<>();
        for(int i = 0; i< SucessorArray.length; i++){
            Edge edge = new Edge(j, SucessorArray[i]);
            edgeList.add(edge);
            if(SucessorArray[i] == 0){
                adjEdList.put(currentNode,edgeList);
                edgeList.clear();
                j++;
            }
        }
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

