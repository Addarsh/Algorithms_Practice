
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation{

	private int[]	id;
	private int[] size;
	private int N;
	private int[] state; //0 is closed state and 1 is open state
	

	private int root(int p){
		while(p != id[p]){
			//Merge with grandfather node
			id[p] = id[id[p]]; //This line for path shortening
			p =  id[p];
		}
		return p;
	}

	private void union(int p, int q){
		//Perform weighted union of the two numbers	
		int rootp = root(p);
		int rootq = root(q);
		if(rootp == rootq) return;
	
		if(size[rootp] <= size[rootq]){ //This line for performing weighted unions
			//Attach p to q and modify size
			// of q
			id[rootp] = rootq;
			size[rootq] += size[rootp];	
		}
		else{
			id[rootq] = rootp;
			size[rootp] += size[rootq];	
		}
	}

	private boolean connected(int p, int q){
		if(root(p) == root(q)) return true;
		return false;
	}
	
	public Percolation(int N){
		id = new int[N*N + 2]; //N by N grid plus 2 states
													//for the top and bottom of the grid
		size = new int[N*N+2];
		state = new int[N*N];

		for(int i =0; i < N*N;i++){
			size[i] = 1;
			id[i] = i;
		}
		//Adding source and sink sites
		for(int i =0; i < N; i++){
			id[i] = N*N;
		}
		for(int i = N*N-N; i < N*N; i++){
			id[i] = N*N+1;			
		}

		id[N*N] = N*N;
		size[N*N] =	N;
		id[N*N+1] = N*N+1;
		size[N*N+1] =	N;
		this.N = N;
	}

	public int getSize(){
		return N;
	}

	public void open(int i, int j){
		int myid = N*(i-1) + j-1;	
		//Set state as open
		state[myid] = 1;	
		//Perform a union around all neighbouring
		//points on the grid
		int x,y;
		int nid;
		//left
		x = i; y = j-1; 
		nid = N*(x-1) + (y-1);
		if(isOpen(x,y)) union(myid,nid);
		
		//right
		x = i; y = j+1; 
		nid = N*(x-1) + (y-1);
		if(isOpen(x,y)) union(myid,nid);

		//top
		x = i+1; y = j; 
		nid = N*(x-1) + (y-1);
		if(isOpen(x,y)) union(myid,nid);
	
		//bottom
		x = i-1; y = j; 
		nid = N*(x-1) + (y-1);
		if(isOpen(x,y)) union(myid,nid);
	}

	public boolean isOpen(int i, int j){

		if(i < 1 || j < 1 || i > N || j > N){
			return false;
		}
		int index = N*(i-1) + (j-1);
		if(state[index] == 1) return true; //state is open
		return false; 	
	}
	
	public boolean isFull(int i, int j){
		if(i < 1 || j < 1 || i > N || j > N){
			//throw new IndexOutOfBoundsException("Index is out of bounds for sure!");
			return false;
		}	
		int index = N*(i-1) + (j-1);
		//Check if this site is connected to the top row
		return connected(index,N*N);
	}
	
	public boolean percolates(){
		return connected(N*N,N*N+1);
	}

	public void printAllIds(){
		for(int i =0; i < N*N+2; i++){
			System.out.printf("id i: %d, value: %d\n",i,id[i]);
		}
	}

	public static void main(String[] args){
		Percolation p = new Percolation(100);
		//Choose a random number between 0,N*N to open
		int N = p.getSize();
		/*p.open(1,1);
		p.open(1,2);
		p.open(2,3);
		p.open(3,1);
		p.open(3,2);
		p.open(4,1);
		p.open(5,2);
		p.printAllIds();*/		
		int numopen = 0;
		while(!p.percolates()){
			int x = StdRandom.uniform(1,N+1);	
			int y = StdRandom.uniform(1,N+1);
			//System.out.printf("x is: %d, y is: %d\n",x,y);
			if(!p.isOpen(x,y)){
				numopen++;
				p.open(x,y);
			}
		}
		if(p.percolates())System.out.println("Everything connects now!");
		double fraction = ((double)numopen)/(N*N);	
	
		System.out.printf("Num open sites: %d\n",numopen);
		System.out.printf("Num lattice points: %d\n",N*N);
		System.out.printf("Fraction is: %f\n",fraction);
		
	}
}
