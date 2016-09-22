/*
 * Implemting efficient 2D range search and 
 * nearest neighbour search using 2D -trees
 * A 2D tree is like a BST but with 2 keys
 * At each node, one of the two keys decides direction
 * of insertion of a new child node 
 * The keys alternate at each height of the 2D Tree
*/
import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.SET;
import java.util.ArrayList;
import java.util.Iterator;

public class KdTree{

	private class Node{
		Point2D point;
		int mysize;
		Node left;
		Node right;
		public Node(Point2D thepoint){
			point = thepoint;
			mysize = 1;		
			left = right = null;
		}
	}

	private Node root;
	private int treesize;
	ArrayList<Point2D> insidepoints;
	double minDistance;
	Point2D minPoint;

	public KdTree(){
		root = null;
		treesize = 0;
	}
	
	public boolean isEmpty(){
		return root == null;
	}

	public int size(){
		return treesize;
	}

	public void insert(Point2D p){
		//Similar to put method in a BST
		int height = 0;
		root = insert(root,p,height);
	}

	//Private recursive method to insert node
  //in a 2D tree
	private Node insert(Node node,Point2D p,int height){
		if(node == null) return new Node(p);
		if(height % 2 == 0){
			//Compare using x cord
			if(p.x() < node.point.x()){
				node.left = insert(node.left,p,++height);
			}else{
				node.right = insert(node.right,p,++height);
			}
		} else if (height % 2 == 1){
			//Compare using y cord
			if(p.y() < node.point.y()){
				node.left = insert(node.left,p,++height);
			}else{
				node.right = insert(node.right,p,++height);
			}
		}
		//Update size
		node.mysize = 1 + size(node.left) + size(node.right); 
		return node;
	}

	public int size(Node node){
		if(node == null) return 0;
		return node.mysize;
	}

	boolean contains(Point2D p){
		return contains(root,p,0);		 
	}

	boolean contains(Node node,Point2D p,int height){
		if(node == null) return false;
		if(node.point.equals(p)) return true;
		if(height % 2==0){
			//Compare using x coords
			if(p.x() < node.point.x()) return contains(node.left,p,++height);
		}else{
			//Compare using x coords
			if(p.y() < node.point.y()) return contains(node.left,p,++height);
		}
		return contains(node.right,p,++height);
	}

	void draw(){}

	public Iterable<Point2D> range(RectHV rect){	
		insidepoints = new ArrayList<Point2D>();
		range(root,rect,0);
		return new Point2DIterable();			
	}

	//Private function to get range
	private void range(Node node,RectHV rect,int height){
		if(rect.contains(node.point)){
			//Add to arraylist
			insidepoints.add(node.point);
			//Look at both children
			range(node.left,rect,height+1);	
			range(node.right,rect,height+1);		
		}else{
			if(height % 2 == 0){
				//Use x cord to compare
				if(rect.xmax() < node.point.x()){
					//go left
					range(node.left,rect,++height);
				}else{
					range(node.right,rect,++height);
				}
			}else{
				//Use y cord to compare
				if(rect.ymax() < node.point.y()){
					//go left
					range(node.left,rect,++height);
				}else{
					range(node.right,rect,++height);
				}	
			}
		}
	} 

	public Point2D nearest(Point2D p){
		if(isEmpty()) return null;
		minDistance = root.point.distanceTo(p);
		minPoint = root.point;
		nearest(root,p,0);	
		return minPoint;
	}

	private void nearest(Node node,Point2D p,int height){
		if(node == null) return;
		double distance = node.point.distanceTo(p);
		if(distance < minDistance){
			minDistance = distance;
			minPoint = node.point;
		}			
		if(height % 2 == 0){
			//Compare using x cord
			if(p.x() < node.point.x()){
				nearest(node.left,p,height+1);
				if(minDistance >= distance) nearest(node.right,p,height+1);
			}else{
				nearest(node.right,p,height+1);
				if(minDistance >= distance) nearest(node.left,p,height+1);
			}
		}else{
			//Compare using y cord
			if(p.y() < node.point.y()){
				nearest(node.left,p,height+1);
				if(minDistance >= distance) nearest(node.right,p,height+1);
			}else{
				nearest(node.right,p,height+1);
				if(minDistance >= distance) nearest(node.left,p,height+1);
			}
		}
	}
	
	private class Point2DIterable implements Iterable<Point2D>{
		public Point2DIterable(){};
		
		public Iterator<Point2D> iterator(){
			return new Point2DIterator();
		}

		private class Point2DIterator implements Iterator<Point2D>{
			int index;			

			public Point2DIterator(){
				index = 0;
			}

			public boolean hasNext(){
				return index < insidepoints.size();
			}

			public Point2D next(){
				if(hasNext()){
					Point2D mypoint = insidepoints.get(index);
					index++;
					return mypoint;
				}
				return null;
			}
	
			public void remove(){};
	
		}
	}

	public static void main(String[] args){
		Point2D one = new Point2D(-1,2);
		Point2D two = new Point2D(1,5);
		Point2D three = new Point2D(0,15);
		Point2D four = new Point2D(-30,1);
		Point2D five = new Point2D(4,4);
		KdTree mytree = new KdTree();	
		mytree.insert(one);
		mytree.insert(two);
		mytree.insert(three);
		mytree.insert(five);
		mytree.insert(four);
		
		System.out.println(mytree.contains(five));
		System.out.println(mytree.nearest(new Point2D(8,3)));

	}
}
