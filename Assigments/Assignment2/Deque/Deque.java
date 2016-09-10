/*
Implementation of a deque that can add/remove
elements from the front and back of the data structure.
Use the prescribed API in the assigment.
All operations have to constant time in the worst case.
I will use 	a Linked List to implement it.
*/

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.lang.NullPointerException;
import java.lang.UnsupportedOperationException;

public class Deque<Item> implements Iterable<Item>{

	private class Node{
		Item value;
		Node next;
		Node prev;
	}

	private Node first;
	private Node last;
	private int N;

	public Iterator<Item> iterator(){
		return new DeqIterator();
	}

	private class DeqIterator implements Iterator<Item>{
		private Node curr = first;
		public boolean hasNext() {return curr != null;}
		public Item next(){
			if(!hasNext()) throw new NoSuchElementException();
			Item item = curr.value;
			curr = curr.next;
			return item;			
		}
		public void remove(){throw new UnsupportedOperationException();}
	}

	public Deque(){
		first = null;
		last = null;
	}

	public boolean isEmpty() {return first == null;}

	public int size() {return N;}
	
	public void addFirst(Item item){
		Node newNode = new Node();
		newNode.value = item;
		newNode.prev = null;		
		newNode.next = first;
		
		if(isEmpty()) {
			last = newNode;
		} else {
			first.prev = newNode;
		}
		first = newNode;
		N++;
	}

	public void addLast(Item item){
		Node oldlast = last;
		last = new Node();
		last.value = item;
		last.prev = oldlast;
		last.next = null;
		if(isEmpty())	{
			first = last;
		} else{
			oldlast.next = last;
		}
		N++;
	}

	public Item removeFirst(){
		if(isEmpty()) throw new NullPointerException();
		Node oldfirst = first;
		first = first.next;
		if(isEmpty()) {
			last = null;
		}else{
			first.prev = null;
		}
		N--;
		return oldfirst.value;
	}

	public Item removeLast(){
		if(isEmpty()) throw new NullPointerException();
		Node oldlast = last;
		last = oldlast.prev;
		if(last == null){
			first = null;
		} else{
			last.next = null;
		}
		N--;
		return oldlast.value;	
	}
	
	public static void main(String[] args){
 		Deque<Integer> d = new Deque<Integer>();
		Iterator<Integer> iter = d.iterator();
		if(d.isEmpty()) System.out.println("No elements in deque");
		while(iter.hasNext()){
			System.out.printf("Printing element: %d\n",iter.next());
		}
		System.out.println("All tests successful");	
	}
}
