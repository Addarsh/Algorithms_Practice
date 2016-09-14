import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Vector;
import java.util.Comparator;

public class Solver{

	//Private variables
	MinPQ<searchNode> myq;
	int minMoves;	
	boolean solvable;

	private class searchNode{
		Board thisBoard; 
		int movesMade;
		searchNode prev;
		public searchNode(Board board){
			thisBoard = board;
			movesMade = 0;
			prev = null;
		}
	}

	//Comparator to compare searchNode objects
	public class myComparator implements Comparator<searchNode>{

		public int compare(searchNode a, searchNode b){
			int a_term = a.thisBoard.manhattan() + a.movesMade; 
			int b_term = b.thisBoard.manhattan() + b.movesMade; 
			return Integer.compare(a_term,b_term);			
		}
	}


	private void aStar(){
		SeachNode currNode;
		while(true){
			currNode = myq.delMin();
			//Find all boards with one move difference	
		}	
	}

	//Solve initial board using A*
	public Solver(Board initial){
		
		if(initial.isSolvable()){
			solvable = true;	
			searchNode initNode = new searchNode(initial);
			myq = new MinPQ(10,new myComparator());
			myq.insert(initNode);
		
			//Use A* now
			aStar();
		} else{
			solvable = false;
		}
	}

	//Returns min number of moves to solve board
	public int moves(){
		return minMoves;
	}

	public Iterable<Board> solution(){
		if(!solvable) return null;
		return null;
	}
}
