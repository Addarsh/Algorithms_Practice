/*
Implementation of a random queue that can
be accessed. Item to be added is chosen at random
from any of the given items in the data structure.
Also, has to be iterable with differnt iterators having
differnt random orders.
Here, since we need random dequeues and enqueue at the end
to be achieved in constant amortized time, we use a resizable
array.
*/

import edu.princeton.cs.algs4.StdRandom;
import java.util.Stack;
import java.util.Iterator;
public class RandomizedQueue<Item> implements Iterable<Item>{		
	
	private Item[] arr;
	private int N;
	private int capacity;
	private int lastIndex;
	private Stack<Integer> EmptyIndices;

	private void resize(){
		capacity = 2*N;
		//Add more lines here
		Item[] temp = (Item[])new Object[N];
		for(int i =0; i < N;i++)temp[i] = arr[i];
		arr = (Item[]) new Object[capacity];
		for(int i =0; i < N;i++)arr[i] = temp[i];
	}

	private class RandIterator implements Iterator<Item>{
		private int[] myit;
		private int currIndex;
		private int count;
		private void Shuffle(){
			for(int i = lastIndex-1; i > 0 ;i--){
				int r = StdRandom.uniform(i);
				//Swapping with a random element
				//appearing before current index
				int temp = myit[r];
				myit[r] = myit[i];
				myit[i] = temp; 	
			}
		}

		public RandIterator(){
			myit = new int[lastIndex];
			for(int i =0; i < lastIndex; i++) myit[i] = i;
			Shuffle();
			currIndex = 0;
			count = N;
		}
		
		public boolean hasNext() {
			if(count > 0) {
				return true;
			}
			return false;
		}
	
		public Item next(){
			int oldIndex;
			do{
				oldIndex = currIndex;
				currIndex++;
			}while(hasNext() && (arr[myit[oldIndex]] == null));
			count--;
			return arr[myit[oldIndex]];	
		}
		public void remove(){};
	}


	public Iterator<Item> iterator(){
		return new RandIterator();
	}

	public RandomizedQueue(){
		N = 0;
		lastIndex = 0;
		EmptyIndices = new Stack<Integer>();
		capacity = 1;
		arr = (Item[])new Object[capacity];
	} 
	
	public boolean isEmpty(){
		return N == 0;
	}

	public int size(){ return N;}

	public Item dequeue(){
		//if(isEmpty())
		int rIndex;
		do{
			rIndex = StdRandom.uniform(size());	
		}while(arr[rIndex] == null);
		
		Item todeq = arr[rIndex];
		EmptyIndices.push(rIndex);
		N--;
		arr[rIndex] = null;
		return todeq;
	}	
	
	public void enqueue(Item item){
		if(N == capacity){	
			resize();		
		}
		N++;
		if(!EmptyIndices.isEmpty()){				
			arr[EmptyIndices.pop()] = item;
		} else{
			arr[N-1] = item;
			lastIndex = N;	
		}			
	}

	public static void main(String[] args){
		RandomizedQueue<String> rq = new RandomizedQueue<String>();			
		rq.enqueue("abcd");
		rq.enqueue("lrs");
		rq.enqueue("baab");
		rq.dequeue();
		rq.enqueue("lauda");
		rq.dequeue();
		Iterator<String> it = rq.iterator();
		while(it.hasNext()){
			System.out.printf("Element in rq: %s\n",it.next());	
		}
	}
}
