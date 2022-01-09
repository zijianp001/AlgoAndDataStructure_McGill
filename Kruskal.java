import java.util.*;

public class Kruskal{

    public static WGraph kruskal(WGraph g){

        /* Fill this method (The statement return null is here only to compile) */
    	DisjointSets set=new DisjointSets(g.getNbNodes());
    	WGraph copy=new WGraph();
        for(int i=0;i<(g.listOfEdgesSorted()).size();i++) {
        	if(IsSafe(set,g.listOfEdgesSorted().get(i))) {
        		copy.addEdge((g.listOfEdgesSorted()).get(i));
        		set.union(g.listOfEdgesSorted().get(i).nodes[0],g.listOfEdgesSorted().get(i).nodes[1]);
        	}
        }
        
        return copy;
    }

    public static Boolean IsSafe(DisjointSets p, Edge e){

        /* Fill this method (The statement return 0 is here only to compile) */
    	if(p.find(e.nodes[0])!=p.find(e.nodes[1])){
    		return true;
    	}
        return false;
    
    }

    public static void main(String[] args){

        String file = args[0];
        WGraph g = new WGraph(file);
        WGraph t = kruskal(g);
        System.out.println(t);

   } 
}
