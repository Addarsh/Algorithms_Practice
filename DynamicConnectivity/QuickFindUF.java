public class QuickFindUF{
	private int[] id;
	private int size;	

	public QuickFindUF(int N){
		id = new int[N];
		for(int i =0; i < N;i++){
			id[i] = i;
		}
		size = N;
	}

	public boolean connected(int p, int q){
		return (id[p] == id[q]);	
	}

	public void union(int p, int q){
		if(id[p] == id[q]) return;
		int searchelem = id[p];
		int replaceelem = id[q];
		for(int i =0; i < N;i++){	
			if(id[i] == searchelem) id[i] = replaceelem;		
		}
	}
}
