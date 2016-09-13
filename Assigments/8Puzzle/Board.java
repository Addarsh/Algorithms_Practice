/*
 * Create a board class here 
 * for solving the 8 Puzzle problem
*/


public class Board{

	int n;
	int[][] myboard;

	//Constructor
	public Board(int[][] blocks){
		n = blocks.length;
		myboard = new int[n][n];
	}

	public int dimension(){
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
					distance += col- j + row - i;
				} 
			}
		}
		return distance;
	}

}
