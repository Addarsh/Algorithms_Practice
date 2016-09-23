/*
 *Implement a cycle class to know if the given
 *graph has a cycle or not. Use Depth First search
 *to solve the problem.
*/
package cycle;
import Graphs.Graph;

public class cycle{
	boolean[] marked;
	int[] edgeTo;
	boolean cycle;

	public cycle(Graph g){
		marked = new boolean[g.V()];
		for(int i =0; i < marked.length;i++) marked[i] = false;
		edgeTo = new int[g.V()];
		cycle = false;
		edgeTo[0] = 0;
		dfs(g,0);	
	}

	private void dfs(Graph g, int s){
		marked[s] = true;
		for(Integer a: g.adj(s)){
			if(cycle) return;
			if(!marked[a]){
				edgeTo[a] = s;
				dfs(g,a);
			}else if(edgeTo[s] != a){
				//If a is parent of s, then 
				cycle = true;
			}
		}	
	}

	public boolean isCyclic(){
		return cycle;
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
		
		cycle mycy = new cycle(g);
		System.out.printf("Is cyclic: %b\n",mycy.isCyclic());
	}


}
