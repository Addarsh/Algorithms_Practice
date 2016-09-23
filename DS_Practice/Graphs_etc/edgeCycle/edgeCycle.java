/*
 * Implement a solution to find if there is a cycle connecting
 * each edge of the graph once and only once.
 * Use DFS for the implementation.
 */
package edgeCycle;
import Graphs.Graph;
import java.util.ArrayList;

public class edgeCycle{
	int[] edgeTo;
	int[] sonTo;
	boolean oneCycle;
	int numEdges;
	ArrayList<Integer> mypath;

	public edgeCycle(Graph g){
		oneCycle = false;
		numEdges = 0;
		edgeTo = new int[g.V()];
		for(int i=0;i < g.V();i++) edgeTo[i] = -1;
		
		sonTo = new int[g.V()];
		for(int i=0;i < g.V();i++) sonTo[i] = -1;
	
		mypath = new ArrayList<Integer>();
		mypath.add(0);
		csearch(g,0);
	}

	public void printPath(){
		for(int i =0; i < mypath.size();i++){
			System.out.printf("%d --> ",mypath.get(i));
		}
		System.out.println("END");
	}	


	private void csearch(Graph g,int head){
		int source = head;
		while(true){
			boolean cornered = true;
			boolean nochoice = false;
			for(Integer v: g.adj(head)){
				if(v == source){
					if(numEdges == g.E() -1){
						mypath.add(v);
						oneCycle = true; //Found a cycle
						return;
					}
					nochoice = true;
					continue;
				}
				if(edgeTo[v] != head && edgeTo[head] != v && sonTo[v] != head){
					cornered = false;
					nochoice = false;
					edgeTo[v] = head;
					sonTo[head] = v;
					mypath.add(v);
					printPath();
					head = v;
					numEdges++;
					break;	
				}
			}
			if(cornered){
				if(nochoice){
					edgeTo[source] = head;
					sonTo[head] = source;
					mypath.add(source);
					head = source;
					numEdges++;
				} else break;
			}	
		}
	}

	public boolean isEdgeCyclic(){
		return oneCycle;
	}

	public static void main(String[] args){
		//Write test case
		int numVertices = 7;
		Graph g = new Graph(numVertices);			
		g.addEdge(0,1);
		g.addEdge(0,5);
		g.addEdge(0,2);
		g.addEdge(0,6);
		g.addEdge(1,2);
		g.addEdge(2,3);
		g.addEdge(3,4);
		g.addEdge(2,4);
		g.addEdge(6,4);
		g.addEdge(4,5);
		
		/*g.addEdge(0,1);
		g.addEdge(1,2);
		g.addEdge(2,3);*/
		
		edgeCycle ecycle = new edgeCycle(g);
		System.out.printf("isEdgeCyclic: %b\n",ecycle.isEdgeCyclic());		
	}
}
