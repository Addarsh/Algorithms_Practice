//Implementig a Pair class
//of type K and V
public class Pair{

	private Point first;
	private Point second;

	public Pair(Point pehla, Point doosra){
		first = pehla;
		second = doosra;
	}

	public Point getFirst(){
		return first;
	}
	
	public Point getSecond(){
		return second;
	}
	
	public String toString(){
		return "("+first.toString()+","+second.toString()+")";
	}
}
