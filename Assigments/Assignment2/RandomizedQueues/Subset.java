/*Client that reads N inputs
from StdIn and prints out k
of them at random where 
0<=k<=N.
*/

import edu.princeton.cs.algs4.StdIn;
import java.util.Iterator;
public class Subset{
	
	public static void main(String[] args){
		RandomizedQueue<String> rq = new RandomizedQueue<String>();
		//Read from std in (all strings)
		int k = Integer.parseInt(args[0]);
		String[] myinputs = StdIn.readAllStrings();			
		for(int i =0; i <myinputs.length;i++){
			rq.enqueue(myinputs[i]);
		}
	
		//Random dequeue k elements
		Iterator<String> it = rq.iterator();
		for(int i =0; i < k; i++){
			System.out.printf("%s\n",it.next());	
		}
	}

}
