/*
 * Implementation of the Connected Components
 * Graph routine. Can find out if two vertices are
 * connected or not in constant time. Uses DFS to implement
 * unions of connected vertices.
 */
package CCGraph;
import Graphs.Graph;

public class CC{

	boolean[] marked;
	int[] id;

	public CC(Graph G){
		marked = new boolean[G.V()];
		id = new int[G.V()];
		for(int i =0; i < G.V();i++)marked[i]= false;
		cc_dfs(G);		
	}

	private void cc_dfs(Graph G){
		int myid = 0;
		for(int i =0; i < marked.length;i++){
			if(marked[i] == true) continue;
			id[i] = i;
			dfs(G,i,myid);
			myid++;		
		}

	}

	private void dfs(Graph G,int s,int myid){
		marked[s] = true;
		for(Integer a : G.adj(s)){
			if(marked[a] == false){
				dfs(G,a,myid);	
				id[a] = myid;
			}
		}
	}

	public boolean isConnected(int v, int w){
		return id[v] == id[w];
	}

	public static void main(String[] args){
		Graph G = new Graph(5);
		G.addEdge(0,3);
		G.addEdge(1,2);
		G.addEdge(2,4);
		CC mycc = new CC(G);
		System.out.printf("%b\n",mycc.isConnected(0,3));
		System.out.printf("%b\n",mycc.isConnected(1,3));
		System.out.printf("%b\n",mycc.isConnected(0,4));
		System.out.printf("%b\n",mycc.isConnected(4,1));
	}

}
