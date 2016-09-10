/*
Given a set of points, to find 
all possible lines of maximal length.
We will achieve this using Merge sort.
For N points, the total time is NlogN.
*/

import java.util.Comparator;
import java.util.Arrays;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
public class FastCollinearPoints{
	
	private int nsegments;
	private int npairs;
	private int numpoints;	
	private Point[] mypoints;
	private LineSegment[]  mysegments;
	private Pair[] mypairs;
	private int capacity;
	private static int CUTOFF = 4;

	private void resize(){
		if(npairs == capacity){
			capacity = 2* capacity;		
			Pair[] temp = new Pair[npairs];
			for(int i =0; i < npairs; i++) {
				temp[i] = mypairs[i];
			} 
			mypairs = new Pair[capacity];
			for(int i =0; i < npairs; i++) {
				mypairs[i] = temp[i];
			}
		}
	}

	public FastCollinearPoints(Point[] point){
		numpoints = point.length;
		capacity = 4;	
		nsegments = 0;
		npairs = 0;
		mypoints = point;
		mypairs = new Pair[capacity];
	}
	
	public int numberOfSegments(){
		return nsegments;	
	}

	private boolean checkifCollinear(Point[] ptArr, Comparator<Point> cmp, int start){
		//Check if the three or more points in the aray are collinear
		if(start +2 >= ptArr.length) return false;	
		if(cmp.compare(ptArr[start],ptArr[start+1]) == cmp.compare(ptArr[start+1],ptArr[start+2]) && cmp.compare(ptArr[start],ptArr[start+1]) == 0){
			return true;
		}		
		return false;	
	}

	private void FindEndPoints(Point[] ptArr,Point[] subArray,int start){
		subArray[0] = ptArr[0];
		for(int i =1;i <4;i++) {
			subArray[i] = ptArr[start+i-1];
		}
		Arrays.sort(subArray);
	}

	private int slopeCompare(Pair oldp, Pair newp){
		//Check if slopes are the same
		if(oldp.getFirst().slopeTo(oldp.getSecond()) == newp.getFirst().slopeTo(newp.getSecond())){
			Comparator<Point> cmp = oldp.getFirst().slopeOrder();
			if(newp.getFirst().compareTo(oldp.getFirst()) == 0 || newp.getSecond().compareTo(oldp.getSecond()) == 0 || cmp.compare(newp.getFirst(),newp.getSecond()) == 0){
				if(newp.getFirst().compareTo(oldp.getFirst()) <= 0 && newp.getSecond().compareTo(oldp.getSecond()) >= 0){
					return 1;		
				}
			return 0;	
			}
		}
		return -1;
	}

	private void addLineToSegments(Pair newPair){
		//Linear search the mysegments array	
		for(int i =0; i < npairs;i++){
			int result = slopeCompare(mypairs[i],newPair);
			if(result == -1) continue;
			if(result == 1) mypairs[i] = newPair;
			return;
		}
		//Add to segment
		resize();
		mypairs[npairs] = newPair;
		npairs++;
	}

	private void findAllCollinearPts(Point[] ptArr,Point origin){
		Comparator<Point> cmp = origin.slopeOrder();
		for(int i =1; i < numpoints;i++){
			if(checkifCollinear(ptArr,cmp,i)){
				//Find largest and smallest points
				Point[] subArray = new Point[4];
				FindEndPoints(ptArr,subArray,i);
				Point smallest = subArray[0];
				Point largest = subArray[3];

				//Create line segment
				Pair newPair = new Pair(smallest,largest);
				//Add to the segment array		
				addLineToSegments(newPair);		
			}	
		}
	}

	private LineSegment[] getSegments(){
		nsegments = npairs;
		mysegments = new LineSegment[nsegments]; 
		for(int i =0; i < nsegments;i++){
			LineSegment newLine = new LineSegment(mypairs[i].getFirst(),mypairs[i].getSecond());	
			mysegments[i] = newLine;
		}
		return mysegments;
	}

	public LineSegment[] segments(){
		//For each point, calculate the angles
		Point[] ptArr = new Point[numpoints];
		for(int i =0; i < numpoints; i++){
			for(int j =0; j < numpoints;j++) ptArr[j] = mypoints[j];
			Point origin = mypoints[i];		
			//Sort the points using mergeSort for doubles
			Arrays.sort(ptArr,origin.slopeOrder());	
			findAllCollinearPts(ptArr,origin);
		}
		return getSegments();
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
    FastCollinearPoints collinear = new FastCollinearPoints(points);
    for (LineSegment segment : collinear.segments()) {
        StdOut.println(segment);
        segment.draw();
    }

	}

}
