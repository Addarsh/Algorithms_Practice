//Code to implement
//linear probling as a means to avoid
//collisions in hasing

public class linearProbing<Key,Value>{

	int n = 20;
	int m = n/2;  //Array size
	private class Node{
		Key key;
		Value value;
		Node(Key mykey, Value myvalue){
			key = mykey;
			value = myvalue;
		}
	}
	Object[] myarr;

	public linearProbing(){
		myarr = new Object[n];
	}

	public int hash(Key key){
		int hashvalue = (key.hashCode() & 0x7fffffff) % m;
		return hashvalue;
	}

	public Value get(Key mykey){
		int myIndex = hash(mykey);
		int search = myIndex;
		Node curr;
		do{
				if(myarr[search] == null) return null;
				curr = (Node) myarr[search];
				if(curr.key == mykey) return curr.value;
				search += 1 % n;
			}while(curr != null);
		return null;
	}

	private void rehashCluster(int startIndex){
		for(int i=startIndex+1; myarr[i] != null; i+=1 %n){
			//Rehash the ith index
			Node curr = (Node) myarr[i];
			Key mykey = curr.key;
			Value myvalue = curr.value;
			curr = null;			//Delete the node
			put(mykey,myvalue);		//Hash the node again
		}
	}

	public void delete(Key mykey){
		int myIndex = hash(mykey);
		if(myarr[myIndex] == null) return;
		int start=myIndex;
		for(int i = myIndex; myarr[i] != null;i += 1 % n){
			Node curr = (Node) myarr[i];
			if(curr.key == mykey) {
				myarr[i] = null;
				start = i;
				break;
			}
		} 
		rehashCluster(start);		
	}

	public void put(Key mykey,Value myvalue){
		int myIndex = hash(mykey);
		if(myarr[myIndex] == null){
			myarr[myIndex] = new Node(mykey,myvalue);
			return;
		}
		int i;
		for(i =myIndex+1;myarr[i] != null; i+= 1%n);
		myarr[i] = new Node(mykey,myvalue);	
	}

	public static void main(String[] args){
		linearProbing<String,Integer> mytable = new 	linearProbing<String,Integer>();
		mytable.put("Hello",38);
		mytable.put("Hey",8);
		mytable.put("ok",3);
		mytable.put("bruh",-8);
		
		System.out.println(mytable.get("bruh"));

		System.out.println(mytable.get("ok"));
		mytable.delete("ok");
		System.out.println(mytable.get("ok"));
	}
}
