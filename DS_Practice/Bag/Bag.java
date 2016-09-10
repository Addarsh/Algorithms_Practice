//The Bag class holds items inside of without any
//specified order. As a result, it can be implemented
//via a stack or queue, as need be without the pop or
//dequeue operations respectively.

//Here I will implement the Bag as a Stack LL (LInked list) 
import java.util.Iterator;

public class Bag<Item> implements Iterable<Item>{

	private class Node{
		public Item e;
		public Node next;
	}
	private Node first;
	private int size;

	private class BagIterator implements Iterator<Item>{
		private Node currNode = first;
		public boolean hasNext(){ return currNode == null;}
		public Item next(){
			Item item =  currNode.e; 		
			currNode = currNode.next;
			return item;
		}
		public void remove(){};
	}

	public Bag(){
		first = null;		
		size = 0;
	}

	public Iterator<Item> iterator(){
		return new BagIterator();
	}

	public void add(Item a){
		Node newNode = new Node();
		newNode.e = a;
		newNode.next = first;
		first = newNode;
		size++;
	}

	public int size(){
		return size;
	}
}
