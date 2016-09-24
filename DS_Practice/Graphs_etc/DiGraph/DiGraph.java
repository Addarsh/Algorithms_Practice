/*
 * Implementation of a DiGraph i.e. a Directed Graph
 * The implementation is very similar to a Graph class.
 */
package DiGraph; 
import edu.princeton.cs.algs4.Bag;

public class DiGraph{
	int numVertices;
	private Bag<Integer>[] adlist;

	public DiGraph(int v){
		numVertices = v;
		adlist = (Bag<Integer>[]) new Bag[v];
		for(int i=0; i < v; i++){
			adlist[i] = new Bag<Integer>();
		}
	}

	//Check for duplicates to remove redundancy
	private boolean checkForDuplicates(int s, int v){
		for(int a : adlist[s]){
			if(a == v) return true;
		}
		return false;
	}

	//Add an edge to the graph, from v to w
	public void addEdge(int v, int w){
		if( v >= numVertices || w >= numVertices || v < 0 || w < 0) return;
		if(checkForDuplicates(v,w)) return; 
		adlist[v].add(w);
	}

	public Iterable<Integer> adj(int v){
		return adlist[v];
	}	

	public int V(){
		return numVertices;
	}
	
	public int E(){
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
		DiGraph dg = new DiGraph(4);
		dg.addEdge(0,3);	
		dg.addEdge(2,3);	
		dg.addEdge(1,2);	
		dg.addEdge(0,1);
		dg.addEdge(3,2);

		System.out.printf("%s",dg.toString());	
	}

}
