/*
First implementation
of quicksort. let's see how it goes.
Array must be randomly shuffled first.
*/

import java.lang.Comparable;
import edu.princeton.cs.algs4.StdRandom;
public class QuickSort{
	//Fisher Yates shuffle
	private static<T extends Comparable<T>> void Shuffle(T[] arr){
		for(int i =1; i < arr.length;i++){
			int swapindex = StdRandom.uniform(i);
			swap(arr,i,swapindex);
		}
	}

	private static <T extends Comparable<T>>void swap(T[] arr, int i,int j){
		T temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}

	private static<T extends Comparable<T>> int partition(T[] arr, int start, int end){
		int pivot = start;
		int i = start + 1;
		int j = end;
		while(true){	
			while(i < end && arr[i].compareTo(arr[pivot]) < 0) i++;
			while(j > (start+1) && arr[j].compareTo(arr[pivot]) > 0) j--;
			if(i > j || (i == j && arr[j].compareTo(arr[pivot]) <= 0)){	
				swap(arr,pivot,j);
				return j;
			}
			swap(arr,i,j);
			i++;j--;
		}
	}
	
	public static <T extends Comparable<T>> void  sort(T[] arr){
		//Shuffle(arr);
		int start = 0;
		int end = arr.length-1;
		sort(arr,start, end);
	}
	
	private static <T extends Comparable<T>> void  sort(T[] arr, int start, int end){
		if(start < 0 || end > arr.length-1 || start >= end) return;
		//Traverse array from left and right until parition
		int pivotpos = partition(arr,start,end);
		sort(arr,start,pivotpos-1);
		sort(arr,pivotpos+1,end);
	}

	public static void main(String[] args){
		//Integer[] arr = {4,3,8,-32,7,2,77,49};
		//Integer[] arr = {8,4,7,2,3};
		Integer[] arr = {2,7,4,5,3};
		QuickSort.sort(arr);
		for(int i =0; i < arr.length; i++){
			System.out.printf("%d\n",arr[i]);
		}
	}
}
