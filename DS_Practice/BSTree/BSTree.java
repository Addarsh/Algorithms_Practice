/*Implementation of a Binary Search tree 
BST is the data structure used to implement a symbol table
BST is a Binary tree in order i.e left subtree is smaller than node is smaller than right subtree
We will use Linked Lists to implement a BST 
*/

public class BSTree<Key extends Comparable<Key>,Value>{
	
	private class Node{
		Value value;
		Key key;
		Node left, right;
		public Node(Key key, Value value){
			this.key = key;
			this.value = value;
			left = null;
			right = null;
		}
		public Key getKey(){
			return key;
		}

		public Value getValue(){
			return value;
		}
	}

	private Node root;

	public BSTree(){
		root = null;			
	}

	public void add(Key key, Value value){
		root = add(root,key,value);
	}	

	public Node add(Node node,Key key,Value value){
		Node mynode = new Node(key,value);
		if(node == null) return mynode;
		if(node.getKey().compareTo(key) >0){
			node.left = add(node.left,key,value);
		}else if(node.getKey().compareTo(key) < 0){
			node.right = add(node.right,key,value);
		}else{
			node = mynode; 
		}
		return node;
	}

	public Value get(Key key){
		Node curr = root;
		while(curr != null){
			Key mykey = curr.getKey();
			if(mykey.compareTo(key) == 0){
				return  curr.getValue(); 
			}else if (mykey.compareTo(key) < 0){
				curr = curr.right;
			} else{
				curr = curr.left;	
			}		
		}
		return null;
	}

  public static void main(String[] args){
		BSTree<Integer,String> mytree= new BSTree<Integer,String>();
		mytree.add(5,"Hey");
		mytree.add(10,"Boy");
		mytree.add(15,"dei");
		mytree.add(2,"doi");
		System.out.printf("%s\n",mytree.get(2));
	}
}
