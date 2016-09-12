/*Implementation of a Binary Search tree 
BST is the data structure used to implement a symbol table
BST is a Binary tree in order i.e left subtree is smaller than node is smaller than right subtree
We will use Linked Lists to implement a Binary Search Tree 
*/

public class BSTree<Key extends Comparable<Key>,Value>{
	
	private class Node{
		Value value;
		Key key;
		Node left, right;
		int count;
		public Node(Key key, Value value){
			this.key = key;
			this.value = value;
			left = null;
			right = null;
			count = 0;
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

	//Insert new key, public method
	public void add(Key key, Value value){
		root = add(root,key,value);
	}	

	//Insert a new key in the tree, private helper
	public Node add(Node node,Key key,Value value){
		Node mynode = new Node(key,value);
		if(node == null){
			 mynode.count = 1;
			 return mynode;
		}
		if(node.getKey().compareTo(key) >0){
			node.left = add(node.left,key,value);
		}else if(node.getKey().compareTo(key) < 0){
			node.right = add(node.right,key,value);
		}else{
			node = mynode; 
		}
		node.count = 1 + size(node.left) + size(node.right);
		return node;
	}

	//Check if given key is containeid
	//in the tree
	public boolean contains(Key key){
		return get(key) != null;
	}

	//Find size of subtree of given key,public method
	public int size(Key key){
		Node curr = root;
		while(curr != null){
			if(key.compareTo(curr.getKey()) > 0){
				curr = curr.right;		
			} else if(key.compareTo(curr.getKey()) < 0){
				curr = curr.left;
			} else{
				return size(curr);
			}
		}
		return 0;
	}

	//Private helper of public method
	public int size(Node node){
		if(node == null) return 0;
		else return node.count;
	}

	//Get value associated with given key
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

	//Find maximum of all keys
	public Key max(){
		if(root == null) return null;
		Node curr = root;
		while(curr.right != null){
			curr = curr.right;
		}
		return curr.getKey();
	}

	//Find number of elements smaller
	//than the given key
	public int rank(Key key){
		int totalSize = rank(root,key);			
		return totalSize;
	}

	public int rank(Node node, Key key){
		if(node == null) return 0;
		if(key.compareTo(node.getKey()) >= 0){
			int totalSize = 1 + size(node.left);
			return totalSize + rank(node.right,key);
		} else{
			return rank(node.left,key);	
		}
	}

	//Find smallest key in tree
	public Key min(){
		if(root == null) return null;
		Node curr = root;
		while(curr.left != null){
			curr = curr.left;
		}
		return curr.getKey();
	}

	public Key floor(Key k){
		if(root == null) return null;
		Node curr = root;
		
		Key floored= null;
		while(curr != null){
			Key mykey = curr.getKey();
			if(mykey.compareTo(k) > 0){
				curr = curr.left;
			}else if (mykey.compareTo(k) < 0){
				if(floored == null) floored = mykey;
				else if(mykey.compareTo(floored) > 0) floored = mykey;
				curr = curr.right;
			} else{
				floored = mykey;
				break;
			}
		}
		return floored;
	}

	public void deleteMin(){
		root = deleteMin(root);
	}

	public Node deleteMin(Node node){
		if(node.left == null){
			return node.right;
		}	
		else {
			node.left =  deleteMin(node.left);
			node.count = 1 + size(node.left) + size(node.right);
			return node;
		}
	}

  public static void main(String[] args){
		BSTree<Integer,String> mytree= new BSTree<Integer,String>();
		mytree.add(5,"Hey");
		mytree.add(10,"Boy");
		mytree.add(15,"dei");
		mytree.add(2,"doi");
		/*System.out.printf("%s\n",mytree.get(10));
		System.out.printf("%d\n",mytree.min());
		System.out.printf("%d\n",mytree.max());
		System.out.printf("%d\n",mytree.floor(17));
		System.out.printf("%d\n",mytree.rank(13));*/
	
		System.out.printf("%d\n",mytree.size(5));
	}
}
