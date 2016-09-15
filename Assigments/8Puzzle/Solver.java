import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Vector;
import java.util.Comparator;
import java.util.ArrayList;
import java.util.Iterator;
import java.io.*;

public class Solver{
	//Private variables
	MinPQ<searchNode> myq;
	int minMoves;	
	boolean solvable;
	ArrayList<Board> mysolution;

	private class searchNode{
		Board thisBoard; 
		int movesMade;
		searchNode prev;
		public searchNode(Board board){
			thisBoard = board;
			movesMade = 0;
			prev = null;
		}
		public searchNode(Board board,int moves, searchNode myprev){
			thisBoard = board;
			movesMade = moves;
			prev = myprev;
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

	private class SolutionSet implements Iterable<Board>{

		public SolutionSet(){};

		public Iterator<Board> iterator(){
			return new SolutionIterator();
		}
		
		private class SolutionIterator implements Iterator<Board>{
			int index;
			int seqSize;
			public SolutionIterator(){
				seqSize = mysolution.size(); //Size of the sequence of boards
				index = seqSize-1;	
			}

			public boolean hasNext(){
				return index >= 0;
			}	
			
			public Board next(){
				if(hasNext()){
					Board curr = mysolution.get(index);
					index--;
					return curr;
				}
				return null;
			}

			public void remove(){
				throw new UnsupportedOperationException();
			}	
		}
	}

	private void saveSolnSequence(searchNode endNode){
		searchNode curr = endNode;		
		minMoves = curr.movesMade;
		while(curr != null){
			mysolution.add(curr.thisBoard);		
			curr = curr.prev;
		}
	}

	private void aStar(){
		searchNode currNode;
		boolean done = false;
		do{
			currNode = myq.delMin();
			//Find all boards with one move difference	
			//Find all neighbors of the current board
			Board currBoard = currNode.thisBoard;
			Iterable<Board> neiboards =  currBoard.neighbors();
			for(Board nubur: neiboards){
				//Do not add repetitions to the solution
				if(currNode.prev != null){
					if(nubur.equals(currNode.prev.thisBoard)) continue;		
				}				

				//Create a new search node
				searchNode newNode = new searchNode(nubur,currNode.movesMade+1,currNode);
				if(nubur.isGoal()){
					//Save solution sequence and break
					saveSolnSequence(newNode);
					done = true;
					break;
				}else{
					//Add currNode to priority queue
					myq.insert(newNode);	
				}
			}
		}while(!myq.isEmpty() && !done);	
	}

	//Solve initial board using A*
	public Solver(Board initial){
		if(initial.isSolvable()){
			solvable = true;	
			searchNode initNode = new searchNode(initial);
			myq = new MinPQ(10,new myComparator());
			myq.insert(initNode);

			mysolution = new ArrayList<Board>();
		
			//Use A* to solve
			aStar();

		} else{
			solvable = false;
		}
	}

	//Returns min number of moves to solve board
	public int moves(){
		if(solvable) return minMoves;
		return -1;
	}

	public Iterable<Board> solution(){
		if(!solvable) return null;
		return new SolutionSet();
	}
	
	public static void main(String[] args){
		BufferedReader in;
		int[][] blocks;
		try{
			in = new BufferedReader(new FileReader(args[0]));
			int N = Integer.parseInt(in.readLine());
			blocks = new int[N][N];
			for(int i =0; i < N;i++){
				for(int j =0; j < N;j++){
					blocks[i][j] = Integer.parseInt(in.readLine());
				}
			}
			Board myboard = new Board(blocks);
			Solver mysolver = new Solver(myboard);
			//Print solution
			Iterable<Board> solution = mysolver.solution();
			System.out.println("Min moves: "+mysolver.moves());
			for(Board board:solution){
				System.out.println(board.toString());
			}
		}catch(IOException e){
			System.out.println("Caught exception: "+e.getMessage());
		}
	}
}
