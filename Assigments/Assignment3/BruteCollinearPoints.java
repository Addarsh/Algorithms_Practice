/*
A brute force algorithm to compute
all set of 4 points that are collinear
in nature.
Should N^4 in time.
*/

import java.util.Comparator;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
public class BruteCollinearPoints{
	
	private int nsegments;
	private int numpoints;	
	private Point[] mypoints;
	private LineSegment[]  mysegments;
	private double[] mysegmentslopes;
	private int capacity;

	private void resize(){
		if(nsegments == capacity){
			capacity = 2* capacity;		
			LineSegment[] temp = new LineSegment[nsegments];
			double[] slopetemp = new double[nsegments];
			for(int i =0; i < nsegments; i++) {
				temp[i] = mysegments[i];
				slopetemp[i] = mysegmentslopes[i];
			} 
			mysegments = new LineSegment[capacity];
			mysegmentslopes = new double[capacity];
			for(int i =0; i < nsegments; i++) {
				mysegments[i] = temp[i];
				mysegmentslopes[i] = slopetemp[i];
			}
		}
	}
	
	private LineSegment[] adjustArrayLength(){
		LineSegment[] adjustedArray = new LineSegment[nsegments];
		for(int i =0; i < nsegments; i++) adjustedArray[i] = mysegments[i];
		return adjustedArray;
	}

	private boolean checkCollinearity(int i, int j, int k, int l){
		Point one = mypoints[i];
		Point two = mypoints[j];
		Point three = mypoints[k];
		Point four = mypoints[l];
	
		int firstcmp = Double.compare(one.slopeTo(two),one.slopeTo(three));
		int secondcmp = Double.compare(one.slopeTo(three),one.slopeTo(four));
		if(firstcmp == 0 && secondcmp == 0 ) return true;
		return false;
	}

	private boolean checkDuplicates(LineSegment line){
		for(int i =0; i < nsegments;i++){
			if(mysegments[i].isEquals(line)) return true;
		}
		return false;
	}

	private Point findSmallestPoint(int i, int j, int k, int l){
		Point one = mypoints[i];
		Point two = mypoints[j];
		Point three = mypoints[k];
		Point four = mypoints[l];
		if(one.compareTo(two) < 0 && one.compareTo(three) < 0 && one.compareTo(four) < 0) return one;  
		if(two.compareTo(one) < 0 && two.compareTo(three) < 0 && two.compareTo(four) < 0) return two;  
		if(three.compareTo(one) < 0 && three.compareTo(two) < 0 && three.compareTo(four) < 0) return three;  
		if(four.compareTo(one) < 0 && four.compareTo(two) < 0 && four.compareTo(three) < 0) return four;  
		return null;
	}

	private Point findLargestPoint(int i, int j, int k, int l){
		Point one = mypoints[i];
		Point two = mypoints[j];
		Point three = mypoints[k];
		Point four = mypoints[l];
		if(one.compareTo(two) > 0 && one.compareTo(three) > 0 && one.compareTo(four) > 0) return one;  
		if(two.compareTo(one) > 0 && two.compareTo(three) > 0 && two.compareTo(four) > 0) return two;  
		if(three.compareTo(one) > 0 && three.compareTo(two) > 0 && three.compareTo(four) > 0) return three;  
		if(four.compareTo(one) > 0 && four.compareTo(two) > 0 && four.compareTo(three) > 0) return four;  
		return null;
	}

	public BruteCollinearPoints(Point[] point){
		mypoints = point;
		numpoints = point.length;
		nsegments = 0;
		capacity = 4;
		mysegments = new LineSegment[capacity];
		mysegmentslopes = new double[capacity];
	}
	public int numberOfSegments(){
		return nsegments;
	}

	public LineSegment[] segments(){
		//Use 4 for loops and test all combinations
		for(int i =0; i <	numpoints; i++){
			for(int j =i+1; j <	numpoints; j++){
				for(int k =j+1; k <	numpoints; k++){
					for(int l =k+1; l <	numpoints; l++){
						if(checkCollinearity(i,j,k,l)){
		resize();
		Point smallest = findSmallestPoint(i,j,k,l);
		Point largest = findLargestPoint(i,j,k,l);
		LineSegment newLine = new LineSegment(smallest,largest);
		if(!checkDuplicates(newLine)){
			mysegments[nsegments] = newLine;
			nsegments++;
		}
					}
				}
			}
		}
	}
		//Resize the segment array
		return adjustArrayLength();
	}
	public static void main(String[] args) {

    // read the N points from a file
    In in = new In(args[0]);
    int N = in.readInt();
    Point[] points = new Point[N];
    for (int i = 0; i < N; i++) {
        int x = in.readInt();
        int y = in.readInt();
        points[i] = new Point(x, y);
    }

    // draw the points
    StdDraw.show(0);
    StdDraw.setXscale(0, 32768);
    StdDraw.setYscale(0, 32768);
    for (Point p : points) {
        p.draw();
    }
    StdDraw.show();

    // print and draw the line segments
    BruteCollinearPoints collinear = new BruteCollinearPoints(points);
    for (LineSegment segment : collinear.segments()) {
        StdOut.println(segment);
        segment.draw();
    }

	}
}
	

