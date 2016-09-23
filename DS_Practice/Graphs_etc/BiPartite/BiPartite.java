/*
 * Check if a graph is bipartite or not
 * A bipartite graph is one where each edge
 * connects one kind of vertex to another
 * if we decide to breakup a graph into two
 * kinds of vertices. 
*/
package BiPartite;
import Graphs.Graph;

public class BiPartite{
	boolean[] marked;
	int[] color;//0 for red and 1 for black
	boolean biPartite;

	public BiPartite(Graph G){
		marked = new boolean[G.V()];
		for(int i =0; i < marked.length;i++) marked[i] = false;
		color = new int[G.V()];
		biPartite = true;			
		color[0] = 0;
		dfs(G,0);
	}

	private void dfs(Graph G, int s){
		marked[s] = true;
		for(Integer a : G.adj(s)){
			if(!marked[a]){
				color[a] = color[s] ^ 1;
				dfs(G,a);
			}else if(color[a] == color[s]){	
				biPartite = false;	
				return;
			}
		}
	}

	public boolean isBipartite(){
		return biPartite;
	}

	public void printAll(){
		System.out.println("Printing marked");
		for(int i =0; i < marked.length;i++){
			System.out.printf("%b ",marked[i]);
		}
		System.out.println("\nPrinting color");
		for(int i =0; i < color.length;i++){
			System.out.printf("%d ",color[i]);
		}
		System.out.printf("\n");
	}

	public static void main(String[] args){
		//Write test case
		int numVertices = 7;
		Graph g = new Graph(numVertices);			
		g.addEdge(0,1);
		g.addEdge(0,5);
		g.addEdge(0,2);
		g.addEdge(0,6);
		g.addEdge(1,3);
		g.addEdge(2,3);
		g.addEdge(2,4);
		g.addEdge(6,4);
		g.addEdge(4,5);
		
		BiPartite bp = new BiPartite(g);
		System.out.printf("%b\n",bp.isBipartite());
	}

}
