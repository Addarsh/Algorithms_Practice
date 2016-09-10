/*
Implementation of insertion sort which
is another form of sorting. The left side of
the counter is always in ascending order and 
the right side of the counter is not seen.
Insertion sort depends on the input and can
take between linear time(best case) to N^2/2 in the worst
case. Insertion sort works in linear time for
a partially sorted array and is hence, useful.
*/

public class InsertionSort{
	
	public static void swap(int[] arr, int i, int j){
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}

	public static void sort(int[] arr){
		int arrlen = arr.length;
		for(int i =0; i < arrlen-1;i++){
			for(int j = i+1; j > 0;j--){
				if(arr[j] < arr[j-1]) swap(arr,j,j-1);
				else break;
			} 
		}
	}

	public static void main(String[] args){
		int[] arr = {78,92,-4,45,-304};
		InsertionSort.sort(arr);
		for(int i =0; i< arr.length; i++){
			System.out.printf("%d\n",arr[i]);
		}
	}
}
