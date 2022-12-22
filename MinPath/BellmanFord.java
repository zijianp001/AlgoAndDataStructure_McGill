import java.util.*;

public class BellmanFord{

    private int[] distances = null;
    private int[] predecessors = null;
    private int source;

    class BellmanFordException extends Exception{
        public BellmanFordException(String str){
            super(str);
        }
    }

    class NegativeWeightException extends BellmanFordException{
        public NegativeWeightException(String str){
            super(str);
        }
    }

    class PathDoesNotExistException extends BellmanFordException{
        public PathDoesNotExistException(String str){
            super(str);
        }
    }

    BellmanFord(WGraph g, int source) throws NegativeWeightException{
        /* Constructor, input a graph and a source
         * Computes the Bellman Ford algorithm to populate the
         * attributes 
         *  distances - at position "n" the distance of node "n" to the source is kept
         *  predecessors - at position "n" the predecessor of node "n" on the path
         *                 to the source is kept
         *  source - the source node
         *
         *  If the node is not reachable from the source, the
         *  distance value must be Integer.MAX_VALUE
         */
    	this.distances=new int[g.getNbNodes()];
    	this.predecessors=new int[g.getNbNodes()];
    	for(int i=0;i<g.getNbNodes();i++) {
    		this.distances[i]=Integer.MAX_VALUE;
    		this.predecessors[i]=-1;
    	}
    	this.distances[source]=0;
    	
    	for(int i=1;i<g.getNbNodes();i++) {
    		for(int j=0;j<g.getEdges().size();j++) {
    			int node1=g.getEdges().get(j).nodes[0];
    			int node2=g.getEdges().get(j).nodes[1];
    			if (distances[node1]+g.getEdges().get(j).weight<distances[node2]) {
    				 distances[node2]=distances[node1]+g.getEdges().get(j).weight;
                     predecessors[node2]=node1;
    			}
                   
    		}
    	}
    	boolean noNegative=true;
    	for(int j=0;j<g.getEdges().size();j++) {
    		int node1=g.getEdges().get(j).nodes[0];
			int node2=g.getEdges().get(j).nodes[1];
			if(distances[node2]>distances[node1]+g.getEdges().get(j).weight) {
				noNegative=false;
			}
    	}
    	if(noNegative==false) {
    		throw new NegativeWeightException("Negative cycle exists");
    	}

    }

    public int[] shortestPath(int destination) throws PathDoesNotExistException{
        /*Returns the list of nodes along the shortest path from 
         * the object source to the input destination
         * If not path exists an Error is thrown
         */
    	ArrayList<Integer> path = new ArrayList<Integer>();
    	int trace=destination;
    	while(trace!=-1) {
    		path.add(0,trace);
    		trace=this.predecessors[trace];
    	}
    	if(path.contains(this.source)==false) {
    		throw new PathDoesNotExistException("Path does not exist");
    	}
    	int[] answer=new int[path.size()];
    	for(int i=0;i<path.size();i++) {
    		answer[i]=path.get(i);
    	}
        return answer;
    }

    public void printPath(int destination){
        /*Print the path in the format s->n1->n2->destination
         *if the path exists, else catch the Error and 
         *prints it
         */
        try {
            int[] path = this.shortestPath(destination);
            for (int i = 0; i < path.length; i++){
                int next = path[i];
                if (next == destination){
                    System.out.println(destination);
                }
                else {
                    System.out.print(next + "-->");
                }
            }
        }
        catch (Exception e){
            System.out.println(e);
        }
    }

    public static void main(String[] args){

        String file = args[0];
        WGraph g = new WGraph(file);
        try{
            BellmanFord bf = new BellmanFord(g, g.getSource());
            bf.printPath(g.getDestination());
        }
        catch (Exception e){
            System.out.println(e);
        }

   } 
}

