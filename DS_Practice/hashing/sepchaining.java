/*
 * The idea is to create a separate chaining
 * hastable (chaining by linked lists)
 */

public class sepchaining<Key,Value>{

	private class Node{
		Key key;
		Value value;
		Node next;
		public Node(Key mykey, Value myvalue){
			key = mykey;
			value = myvalue;
			next = null;
		}
		public Node(){
			next = null;
		}
			
	}

	int n = 100;
	int m = 5;  //Number of hashings
	Object[] chainStarts;

	public sepchaining(){
		chainStarts = new Object[m];
	}

	public int hash(Key key){
		int hashvalue = (key.hashCode() & 0x7fffffff) % m;
		return hashvalue;
	}

	public Value get(Key mykey){
		int myindex = hash(mykey);
		if(chainStarts[myindex] == null){
			//Return empty
			return null;
		}else {
			Node curr = (Node)chainStarts[myindex];
			while(curr != null){
				if(mykey == curr.key) return (Value)curr.value;
				curr = curr.next;
			}
			return null; 
		}
	}

	public void delete(Key mykey){
		int myindex = hash(mykey);
		if(chainStarts[myindex] != null){
			//Find the key and delete it
			Node curr = (Node)chainStarts[myindex];						
			Node prev = null;
			while(curr != null){	
				if(curr.key == mykey){
					if(prev == null) chainStarts[myindex] = null;
					else{
						prev.next = curr.next;
						return;
					}
				}
				prev = curr;
				curr = curr.next;	
			}
		}
	}

	public void put(Key mykey,Value myvalue){
		int myindex = hash(mykey);
		System.out.println(myindex);
		if(chainStarts[myindex] == null){
			chainStarts[myindex] = new Node(mykey,myvalue);
		} else{
			Node curr = (Node)chainStarts[myindex];
			if(curr.key == mykey){
				curr.value = myvalue;
				return;
			}
			while(curr.next != null){
				if(curr.key == mykey) {
					curr.value = myvalue;
					return;
				}
				curr = curr.next;
			}	
			curr.next = new Node(mykey,myvalue);
		}
		System.out.println("Done");		
	}

	public static void main(String[] args){
		sepchaining<String,Integer> mytable = new sepchaining<String,Integer>();
		mytable.put("Addarsh",23);	
		mytable.put("Ali",27);
		mytable.put("Alia",37);
		mytable.put("Batman",7);
		mytable.put("Denis",35);
		mytable.put("Addarsh",33);	

		System.out.println("Addarsh's value: "+mytable.get("Addarsh"));
		System.out.println("Alia's value: "+mytable.get("Alia"));
		System.out.println("Batman's value: "+mytable.get("Batman"));
		mytable.delete("Batman");

	
		System.out.println("Addarsh's value: "+mytable.get("Addarsh"));
		System.out.println("Alia's value: "+mytable.get("Alia"));
		System.out.println("Batman's value: "+mytable.get("Batman"));
	}

}
