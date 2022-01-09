import java.util.*;
import java.io.File;

public class FordFulkerson {

	public static ArrayList<Integer> pathDFS(Integer source, Integer destination, WGraph graph){
		ArrayList<Integer> path = new ArrayList<Integer>();
		/* YOUR CODE GOES HERE*/
		int[] nodes=new int[graph.getNbNodes()];
		nodes[source]=-1;
		boolean[] visited=new boolean[graph.getNbNodes()];
		ArrayList<Integer> copy=new ArrayList<Integer>();
		copy.add(source);
		while(copy.size()>0) {
			int nodeToMove=copy.remove(0);
			visited[nodeToMove]=true;
			if(nodeToMove==destination) {
				break;
			}
			for (Edge EDGE:graph.listOfEdgesSorted()) {
                if(nodeToMove==EDGE.nodes[0]) {
                	int adj=EDGE.nodes[1];
                	if(visited[adj]==false && graph.getEdge(nodeToMove, adj).weight>0) {
                		nodes[adj]=nodeToMove;
                		copy.add(adj);
                	}
                }
			}
		}
		if(visited[destination]==false || destination==source) {
			return path;
		}
		int trace=destination;
		while(trace!=-1) {
			path.add(0,trace);
			trace=nodes[trace];
		}
		return path;
	}


	public static String fordfulkerson( WGraph graph){
		String answer="";
		int maxFlow = 0;
		
		/* YOUR CODE GOES HERE		*/
		WGraph changeGraph=new WGraph(graph);
		for(int i=0;i<changeGraph.getNbNodes();i++) {
			for(int j=0;j<changeGraph.getNbNodes();j++) {
				if(i!=j && changeGraph.getEdge(i, j)==null) {
					Edge newEdge =new Edge(i,j,0);
					changeGraph.addEdge(newEdge);
				}
			}
		}
		for(Edge EDGE:graph.listOfEdgesSorted()) {
			EDGE.weight=0;
		}
		ArrayList<Integer> path=pathDFS(graph.getSource(),graph.getDestination(),changeGraph);
		while(path.size()>1) {
			int minweight=Integer.MAX_VALUE;
			for(int i=0;i<path.size()-1;i++) {
				int node1=path.get(i);
				int node2=path.get(i+1);
				minweight=Math.min(minweight,changeGraph.getEdge(node1, node2).weight);
			}
			for(int i=0;i<path.size()-1;i++) {
				int node1=path.get(i);
				int node2=path.get(i+1);
				int oriW=changeGraph.getEdge(node1, node2).weight;
				int oriW2=changeGraph.getEdge(node2, node1).weight;
				changeGraph.setEdge(node1, node2,oriW-minweight);
				changeGraph.setEdge(node2, node1, oriW2+minweight);
				if(graph.getEdge(node1, node2)!=null) {
					graph.getEdge(node1, node2).weight+=minweight;
				}
				else {
					graph.getEdge(node2, node1).weight-=minweight;
				}
				
			}
			maxFlow=maxFlow+minweight;
			path=pathDFS(graph.getSource(),graph.getDestination(),changeGraph);
		}
		answer += maxFlow + "\n" + graph.toString();	
		return answer;
	}
	

	 public static void main(String[] args){
		 String file = args[0];
		 File f = new File(file);
		 WGraph g = new WGraph(file);
	         System.out.println(fordfulkerson(g));
	 }
}

