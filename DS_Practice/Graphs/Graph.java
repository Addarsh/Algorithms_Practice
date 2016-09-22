//Implementation of the Graph API
//using adjacency list representation
import edu.princeton.cs.algs4.Bag;

public class Graph{

	int numVertices;
	private Bag<Integer>[] adlist;

	public Graph(int v){
		numVertices = v;
		adlist = (Bag<Integer>[]) new Bag[v];
		for(int i=0; i < v; i++){
			adlist[i] = new Bag<Integer>();
		}
	}

	//Check for duplicates to remove redundancy
	boolean checkForDuplicates(int s, int v){
		for(int a : adlist[s]){
			if(a == v) return true;
		}
		return false;
	}

	//Add an edge to the graph
	public void addEdge(int v, int w){
		if( v >= numVertices || w >= numVertices || v < 0 || w < 0) return;
		if(checkForDuplicates(v,w) || checkForDuplicates(w,v)) return; 
		adlist[v].add(w);
		adlist[w].add(v);
	}

	Iterable<Integer> adj(int v){
		return adlist[v];
	}	

	int V(){
		return numVertices;
	}
	
	int E(){
		int numEdges = 0;
		for(int i =0; i < numVertices; i++){
			numEdges += adlist[i].size();
		}
		return numEdges/2;
	}

	public String toString(){
		String mystr= "";
		for(int i =0; i < numVertices; i++){
			for(Integer myelem: adlist[i]){
				mystr += Integer.toString(myelem) + " ";
			}	
			mystr += "\n";
		}
		return mystr;
	}
		
	public static void main(String[] args){
		int numVertices = 6;
		Graph g = new Graph(numVertices);			
		g.addEdge(0,4);
		g.addEdge(1,3);
		g.addEdge(1,2);
		g.addEdge(3,1);
		System.out.printf(g.toString());
	}
}
