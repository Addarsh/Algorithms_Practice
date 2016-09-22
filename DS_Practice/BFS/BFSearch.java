/*
 * Implementation of Breadth First Search
 * on graph objects. BFS is non-recursive and
 * the standard algo to find shortest path
 * from a node to another node
 */
package BFS;
import Graphs.Graph;
import edu.princeton.cs.algs4.Queue;

public class BFSearch{

	boolean[] marked;
	int[] edgeTo; 	
	int source;
	
	public BFSearch(Graph G, int s){
		source = s;
		marked = new boolean[G.V()];
		for(int i=0; i < G.V();i++) marked[i] = false;
		edgeTo = new int[G.V()];
		edgeTo[s] = s;	
		bfs(G,s);
	}

	//Process the Breadth firsy method
	private void bfs(Graph G, int s){
		Queue<Integer> myq = new Queue<Integer>();	
		myq.enqueue(s);
		while(!myq.isEmpty()){
			int head = myq.dequeue();
			for(Integer friend : G.adj(head)){
				if(marked[friend] == false){
					edgeTo[friend] = head;
					marked[head] = true;
					myq.enqueue(friend);
				}
			}
		}
	}

	//Print the BFS path of int to source 
	public void printPath(int v){
		int parent;
		do{
			System.out.printf("%d ---> ",v);
			parent = edgeTo[v];
			v = parent;
		}while(parent != source);
		System.out.printf("%d\n",source);
	}
	
	public static void main(String[] args){
		Graph G = new Graph(6);		
		G.addEdge(1,3);
		G.addEdge(0,2);
		G.addEdge(1,5);
		G.addEdge(5,2);
		G.addEdge(3,4);

		BFSearch bfs = new BFSearch(G,1);
		bfs.printPath(4);	
	}
}
