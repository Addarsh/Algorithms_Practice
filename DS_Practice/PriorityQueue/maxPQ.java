/*
Implement a maximum heap queue
that implements the Queue API.
Insertion and deletion take logN time
while returning max takes constant time.
Priority Queue should ideally be immutable
but we will not do so in our implementation.
*/

public class maxPQ <T extends Comparable<T>>{

	private T[] myQ;
	private int capacity;
	private int N;
	
	private void swap(int i , int j){
		T temp = myQ[i];
		myQ[i] = myQ[j];
		myQ[j] = temp;
	}

	private void resize(){
		if(N+1 == capacity){
			capacity = 2* capacity;
			T[] temp = (T[]) new Comparable[capacity];
			for(int i =0; i < N+1;i++) temp[i] = myQ[i];
			myQ = temp;
		}
	}

	private void swim(int k){
		//Swim until k is less than parent
		int parent = k/2;
		while(parent > 0 && myQ[k].compareTo(myQ[parent]) > 0){
			swap(k,parent);
			k = parent;
			parent = k/2;
		}
	}
	
	private void sink(int k){
		while(2*k <= N){
			int i = 2*k;
			if(i < N && myQ[i].compareTo(myQ[i]) <= 0) i++;
			if(myQ[k].compareTo(myQ[i]) < 0) swap(k,i);
			else break;
			k = i;
		}
	}	

	public T max(){
		return myQ[1];
	}

	public maxPQ(){
		capacity = 2;
		N = 0;
		myQ = (T[])(new Comparable[capacity]);
	}

	public void insert(T item){
		resize();
		N++;
		myQ[N] = item;
		swim(N);
	}
	
	public boolean isEmpty(){
		return N == 0;
	}

	public void delMax(){
		if(isEmpty()) return;
		swap(1,N);
		N--;
		sink(1);
	}

	public static void main(String[] args){
		maxPQ<Integer> q = new maxPQ<Integer>();
		q.insert(1);
		q.insert(5);
		q.insert(-2);
		q.insert(7);
		q.insert(3);
		q.delMax();
		q.delMax();
		System.out.printf("Max value: %d\n",q.max());		
	}
}
