public class QuickUnionUF(){
	
	private int[] id;

	public QuickUnionUF(int n){
		id = new int[n];
		for(int i =0; i < n; i++) id[i] = i;
	}

	public int findroot(int p){
		int oldroot;
		int newroot = p;
		do{	
			oldroot = newroot;
			newroot = id[oldroot];
		}while(oldroot != newroot);
		return newroot;
	}

	public void union(int p, int q){
		//Search for both roots and connect
		if(id[p]== id[q]) return;	
		//Otherwise join them
		rootq = findroot(q);
		id[p] =rootq;
	}

	public void connected(int p, int q){
		//Search if both roots are connected or not
		rootq = findroot(q);
	
		rootq = findroot(q);
			
	}

}
