//Implementation of the queue class
//using a linked list. We maintain
//two pointers, one at front and the other
//at the back to do this

import java.util.EmptyStackException;

public class QueueLL<E>{
	private class Node{
		E elem;
		Node next;
	}
	private Node first;
	private Node last;

	public QueueLL(){
		last = null;
		first = null;
	}	
	
	public boolean isEmpty(){
		return first == null;
	}	
	
	public void enqueue(E e){
			
		Node oldlast = last;
		last = new Node();
		last.elem = e;
		last.next = null;
		if(isEmpty()){
			first = last;
			return;
		}
		oldlast.next = last;
	}

	public E dequeue(){
		Node oldfirst = first;
		first = first.next;
		if(isEmpty()) last = null;
		return oldfirst.elem;
	}

	public static void main(String[] args){
		QueueLL<Integer> q = new QueueLL<Integer>();
		for(int i =0; i < 6;i++) q.enqueue(i);
		for(int i =0; i < 6;i++) System.out.printf("Dequeued elem: %d\n",q.dequeue());
		if(q.isEmpty()) System.out.println("Queue is empty now!");
	}
}
