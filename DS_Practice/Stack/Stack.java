//This is my implementation
//of a stack data structure in java
//I am planning to use a linked list to implement it

public class Stack{
	
	//Inner class Node takes
	//8 additional bytes for storage
	private class Node{
		int n; //Takes 4 bytes
		Node next; //Takes 8 bytes
	}	
	private Node first;

	public Stack(){
		first = null;
	}	

	public void push(int a){
		Node mynode = new Node();
		mynode.n = a;
		mynode.next = first;
		first = mynode;
	}

	public int pop(){
		Node old = first;
		first = first.next;
		return old.n;
	}

	public boolean isEmpty(){
		return first == null;
	}

	public static void main(String[] args){
		Stack s = new Stack();
		if(s.isEmpty()){
			System.out.println("Stack is empty now!");
		}
	}

}
