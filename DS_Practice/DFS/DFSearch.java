/*
 * Implementation of Depth First Search 
 * on a Graph object (API implemented in Graph.java)
*/
package DFS;
import Graphs.Graph;


public class DFSearch{

	boolean[] marked;
	int[] edgeTo;

	public DFSearch(Graph G, int s){
		marked = new boolean[G.V()];	
		for(int i =0; i < G.V(); i++){
			marked[i] = false;
		}
		edgeTo = new int[G.V()];	
		dfs(G,s);  //Process the graph
	}

	private void dfs(Graph G, int s){
		//Mark s
		marked[s] = true;
		for(Integer v : G.adj(s)){
			if(marked[v] == false){
				edgeTo[v] = s;
				dfs(G,v);
			}
		}			
	}

	public void printEdgeTo(){
		for(int i =0; i < edgeTo.length; i++){
			System.out.printf("%d ",edgeTo[i]);
		}
		System.out.println();
	}

	public static void main(String[] args){
		Graph G = new Graph(6);		
		G.addEdge(1,3);
		G.addEdge(0,2);
		G.addEdge(1,5);
		G.addEdge(5,2);
		G.addEdge(3,4);
		DFSearch dfs = new DFSearch(G,0);
		dfs.printEdgeTo();		
	}
}
