import com.sun.net.httpserver.Authenticator;

import java.util.*;


public class Graf {
    private Map<Node,List<Edge>> adjEdList;

    public Graf(){
        adjEdList = new TreeMap<>();
    }
    public Graf(int [] SucessorArray){
        for(int i = 0; i< SucessorArray.length; i++){
            while(SucessorArray[i] != 0){

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

