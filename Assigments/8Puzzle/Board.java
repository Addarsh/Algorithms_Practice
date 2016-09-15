/*
 * Create a board class here 
 * for solving the 8 Puzzle problem
*/

import edu.princeton.cs.algs4.StdRandom;
import java.util.Iterator;
import java.util.ArrayList;
public class Board{
	int n;
	int[][] myboard;
	int[] myarr;
	
	static final int DEBUG  = 1;
	
	//Constructor
	public Board(int[][] blocks){
		n = blocks.length;
		myboard = new int[n][n];
		myarr = new int[n*n];
		for(int i =0; i < n; i++){
			for(int j =0; j < n; j++){
				myboard[i][j] = blocks[i][j];
				myarr[n*i+j] = blocks[i][j];
			}
		}
	}

	public int size(){
		return n;
	}

	public int hamming(){
		int distance = 0;
		for(int i =0; i < n;i++){
			int num =  n*i+1;
			for(int j =0; j < n;j++){
				if(myboard[i][j] != num+j){
				 distance += 1;	
				}
			}
		}
		return distance-1; //discounting the exra count for board[2][2] 
	}

	public int manhattan(){
		int distance = 0;
		for(int i =0; i < n; i++){
			for(int j = 0; j < n; j++){
				if(myboard[i][j] == 0) continue;
				if(myboard[i][j] != (n+1)*i+j+1){
					int row, col;
					if(myboard[i][j] %n ==0){
						col =2;
						row = myboard[i][j]/n-1;
					}else{
						col = myboard[i][j] %n -1;
						row = myboard[i][j] /n;
					}

				} 
			}
		}
		return distance;
	}

	public boolean isGoal(){
		return hamming() == 0;
	}

	public boolean equals(Object y){
		if(y == null) return false;
		Board other = (Board) y;
		if(this.toString() != other.toString()) return false;
		return true;
	}

	private void searchForElem(int i,int[] myarr){
		for(int j = 0; j <n; j++){
			if(myboard[i][j] != 0){
				myarr[i] = myboard[i][j];
				myarr[i+2] = j;
				break;
			}
		}
	}

	private void getInversions(int[] arr){
		int numInversions = 0;
		for(int i =1; i < myarr.length;i++){
			int num = myarr[i];
			if(num == 0) {
				arr[0] = i/n;
				continue;
			}
			for(int j=0; j < i;j++){
				if(num < myarr[j]) numInversions++;
			}
		} 
		arr[1] = numInversions;
	}

	public boolean isSolvable(){
		int[] arr = new int[2];
		getInversions(arr);
		
		if(n % 2 != 0){
			//Odd size
			if(arr[1] % 2 != 0) return false;
			return true;	
		} else{
			//Even size
			if((arr[0] + arr[1]) % 2 == 0) return false;
			return true;
		}
	}

	private void swap(int i1,int j1,int i2, int j2,int[][] blocks){
		int temp = blocks[i1][j1];	
		blocks[i1][j1] = blocks[i2][j2];
		blocks[i2][j2] = temp;
	}

	//Wrapper around iterator method
	public Iterable<Board> neighbors(){
		myIterables obj = new myIterables();
		return obj;
	}

	private class myIterables implements Iterable<Board>{

		public myIterables(){}
 
		//Returns an iterator of boards
		public Iterator<Board> iterator(){
			return new BneighborsIterator();
		}

		//Private class implementing Iterator
		private class BneighborsIterator implements Iterator<Board>{
			ArrayList<Board> nuburs; //Neighbor boards		
			int[][]  meblocks;
			int index;
			int numNeighbors;

			//Find the blank space on the board	
			private void findBlankSpace(int[] indices){
				for(int m =0;m < n*n; m++){
					if(myarr[m] == 0){
						indices[0] = m/n;
						indices[1] = m % n;
						break;
					}
				} 	
			}		

			private void computeNeighbors(){
				int[] indices	= new int[2];
				findBlankSpace(indices);
				int i = indices[0];
				int j = indices[1];
	
				for(int r = i-1; r <= i+1; r++){
					for(int c = j-1; c <= j+1; c++){
						if((r >= 0&& r<=n-1)&&(c >= 0 && c<=n-1)){
							if((r==i && (c==j-1 || c==j+1)) ||	(c==j && (r==i-1 || r==i+1))){
								//Swap and add to arraylist
								swap(i,j,r,c,meblocks); //Swap once
								Board newBoard = new Board(meblocks);
								nuburs.add(newBoard);  //Add to arraylist
								swap(i,j,r,c,meblocks); //Swap back
							}
						}
					}
				}	
			}		

			//Class constructor
			public BneighborsIterator(){
				nuburs = new ArrayList<Board>();
				meblocks = new int[n][n];
				//Copy all elements 
				for(int i=0; i < n;i++){
					for(int j=0; j < n;j++){
						meblocks[i][j] = myboard[i][j];
					}
				}
				computeNeighbors();
				index = 0;
				numNeighbors = nuburs.size();	
			}

			//Does the iterable have a next
			public boolean hasNext(){
				return index < numNeighbors;				
			}

			public Board next(){
				if(hasNext()){
					Board neibur = nuburs.get(index);
					index++;
					return neibur;
				}
				return null;
			}
			public void remove(){
				throw new UnsupportedOperationException();
			}
		}
	}

	public String toString(){
		String myBoardString = "";
		String space = " ";
		for(int i =0; i < n; i++){
			myBoardString += space;
			for(int j =0;j < n; j++){
				myBoardString+= Integer.toString(myboard[i][j]);	
				myBoardString += " ";
			}
			myBoardString += "\n";
		}
		return myBoardString;
	}

	private static void shuffle(int[] arr){
		for(int i =0; i < arr.length; i++){
			int randpos = StdRandom.uniform(arr.length);
			int temp = arr[i];
			arr[i] = arr[randpos];
			arr[randpos] = temp;
		}
	}

	private static void create2Dmatrix(int[][] blocks){
		int[] myarr;
		if(DEBUG == 0){
			myarr = new int[blocks.length*blocks.length];
			for(int i =0; i < myarr.length; i++){
				myarr[i] = i;
			}
			//Shuffle array
			shuffle(myarr);
		}else{
			//myarr = new int[]{0,2,3,4,5,6,1,8,9,10,7,11,13,14,15,12};	
			myarr = new int[]{1,2,3,4,5,6,7,8,0};	
		}
		int totalindex = 0;
		for(int i =0; i < blocks.length;i++){	
			for(int j =0; j < blocks.length;j++){
				blocks[i][j] = myarr[totalindex];
				totalindex++;	
			}	
		}
	}

	public static void main(String args[]){
		int size = 3;
		int[][] blocks = new int[size][size];
		create2Dmatrix(blocks);
		Board myboard = new Board(blocks);		
		System.out.printf("%s",myboard.toString());
		//System.out.println(myboard.isSolvable());	
		System.out.println(myboard.isGoal());
		System.out.println("Printing neighbors");
	
		Iterable<Board> myneiburs = myboard.neighbors();
		for(Board nboard: myneiburs){
			System.out.println(nboard.toString());
		}
	}
}

