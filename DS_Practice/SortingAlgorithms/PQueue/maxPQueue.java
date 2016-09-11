//Class to write priority queue

public class maxPQueue<T extends Comparable<T>>{

  T[] myQueue;
  int capacity = 10;
  int N;

  public maxPQueue(){
		myQueue =(T[]) new Comparable[capacity]; 								
  	N = 0;
	} 

  void checkSize(){
		if(N == capacity){
			capacity = 2*N;
			T[] temp = (T[]) new Object[N];
			for(int i =0; i < N;i++) temp[i] = myQueue[i]; 
			myQueue = (T[]) new Object[capacity];
			for(int i =0; i < N;i++) myQueue[i] = temp[i];
		}
	}

  void swap(int i,int j){
		T temp = myQueue[i];
		myQueue[i] = myQueue[j];
		myQueue[j] = temp; 
	}


	void swim(){
		//Check the Nth element
		int parent = N/2;
    int k = N;
		while(parent >= 1 && myQueue[k].compareTo(myQueue[parent]) > 0){
			swap(k,parent);
		 	k = k/2;
			parent = k/2;
		}					
	}

	void sink(){
		int parent = 1;
		int leftChild	= 2*parent;
		while(leftChild <= N){
			int m =leftChild;
			if(leftChild +1 <= N && myQueue[leftChild].compareTo(myQueue[leftChild+1]) < 0){
				m = leftChild + 1;	
			}
			swap(parent,m);
			parent = m;
			leftChild = 2*parent;		
		}
	}

  public void add(T obj){
    checkSize();
    myQueue[++N] = obj;
		swim();		
	}

  public T delMax(){
		if(N == 0) return null;
		T elem = myQueue[1];
		swap(1,N);
		N--;
		sink();
  	return elem;
	}

	public T peek(int k){
		return myQueue[k];
	} 

	public static void main(String[] args){
		maxPQueue<Integer> PQ = new maxPQueue<Integer>();		
		PQ.add(24);
		PQ.add(1);
		PQ.add(-4);
		PQ.add(6);
		//for(int i =1; i <=4; i ++) System.out.printf("%d\n",PQ.peek(i));
		System.out.printf("%d\n",PQ.delMax());
		System.out.printf("%d\n",PQ.delMax());
		System.out.printf("%d\n",PQ.delMax());
		System.out.printf("%d\n",PQ.delMax());
	}	

}
