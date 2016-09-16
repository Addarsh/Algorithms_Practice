/*
 * Red Black Tree implementation
 * Implement range search and nearest neighbors 
 * on 2D points. Implementation is Brute Force in nature.
*/

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.SET;
import java.util.ArrayList;
import java.util.Iterator;

public class PointSET{

	SET<Point2D> allpoints;		
	ArrayList<Point2D> insidepoints;

	public PointSET(){
		allpoints = new SET<Point2D>();
		insidepoints = new ArrayList<Point2D>();
	}

	public boolean isEmpty(){
		return allpoints.isEmpty();
	}

	public int size(){
		return allpoints.size();
	}

	public void insert(Point2D p){
		allpoints.add(p);
	}

	boolean contains(Point2D p){
		return allpoints.contains(p);
	}

	void draw(){
		for(Point2D point:allpoints){
			point.draw();
		}
	}

	public Iterable<Point2D> range(RectHV rect){
		for(Point2D point:allpoints){
			if(rect.contains(point))insidepoints.add(point);
		}
		return new Point2DIterable();
	}

	public Point2D nearest(Point2D p){
		double minDistance = -1;
		Point2D minPoint = null;
		for(Point2D point:allpoints){
			double distance = p.distanceTo(point);
			if(distance < minDistance || minDistance == -1){
				minDistance = distance;
				minPoint = point;
			}
		}
		return minPoint;
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
		PointSET myset = new PointSET();
		Point2D mypoint = new Point2D(2,3);
		Point2D okpoint = new Point2D(-1,0.2);
		myset.insert(mypoint);
		myset.insert(okpoint);
		
		Point2D npoint = new Point2D(5,2);
		System.out.println(myset.nearest(npoint).toString());
	}
}
