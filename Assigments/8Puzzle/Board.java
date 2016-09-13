/*
 * Create a board class here 
 * for solving the 8 Puzzle problem
*/
import edu.princeton.cs.algs4.StdRandom;
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
			int num =  3*i+1;
			for(int j =0; j < n;j++){
				if(myboard[i][j] != num+i+j){
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
				if(myboard[i][j] != 4*i+j+1){
					int row, col;
					if(myboard[i][j] %3 ==0){
						col =2;
						row = myboard[i][j]/3-1;
					}else{
						col = myboard[i][j] % 3-1;
						row = myboard[i][j] /3;
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
			System.out.printf("%d %d\n",arr[0],arr[1]);
			if(arr[1] % 2 != 0) return false;
			return true;	
		} else{
			//Even size
			System.out.printf("%d %d\n",arr[0],arr[1]);
			if((arr[0] + arr[1]) % 2 == 0) return false;
			return true;
		}
	}

	private void swap(int i1,int j1,int i2, int j2,int[][] twinBlocks){
		int temp = twinBlocks[i1][j1];	
		twinBlocks[i1][j1] = twinBlocks[i2][j2];
		twinBlocks[i2][j2] = temp;
	}

	public Board twin(){
		//Swap any two pieces on board
		//One piece is 0,0
		int[] myarr = new int[4];
		searchForElem(0,myarr);
		searchForElem(1,myarr);
		
		//Create new board
		int[][] twinBlocks= new int[n][n];
		swap(myarr[0],myarr[1],myarr[2],myarr[3],twinBlocks);
	
		Board twinBoard = new Board(twinBlocks);	
		return twinBoard;
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
			myarr = new int[]{1,2,3,4,5,6,0,8,9,10,7,11,13,14,15,12};	
			//myarr = new int[]{0,1,3,4,2,5,7,8,6};	
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
		int size = 4;
		int[][] blocks = new int[size][size];
		create2Dmatrix(blocks);
		Board myboard = new Board(blocks);		
		System.out.printf("%s",myboard.toString());
		System.out.println(myboard.isSolvable());	
	}
}

