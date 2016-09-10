//Implementation of a stack using a
//resizable array. When stack is full,
//array is doubled and when stack is quarter
//array size, array is halved in size.

import java.util.ArrayList;
public class StackArrayImp<E>{

	private ArrayList<E> arr;

	public StackArrayImp(){
		arr = new ArrayList<E>();
	}
	
	public void push(E e){
		arr.add(e);
	}

	public E pop(){
		return arr.remove(arr.size()-1);			
	}

	public boolean isEmpty(){
		return arr.size() == 0;
	}

	public static void main(String[] args){
		StackArrayImp<Integer> s = new StackArrayImp<Integer>();
		for(int i =0; i < 6; i++) s.push(i);
		for(int i =0; i < 6; i++) System.out.printf("Popping element:  %d\n",s.pop());
		if(s.isEmpty()) System.out.println("Stack is empty now!");
	}
}
