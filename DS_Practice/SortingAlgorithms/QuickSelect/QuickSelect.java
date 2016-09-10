/*
Implementation of QuickSelect method 
to select kth highest element in an 
array using quicksort
*/
import edu.princeton.cs.algs4.StdRandom;
public class QuickSelect{

	private static <T extends Comparable<T>> void swap(T[] arr, int i, int j){
		T temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp; 
	}	

	private static <T extends Comparable<T>> void Shuffle(T[] arr){
		for(int i =1; i < arr.length;i++){
			int swapIndex = StdRandom.uniform(i);
			swap(arr,i,swapIndex);
		}
	}

	public static <T extends Comparable<T>> T select(T[] arr, int k){
		Shuffle(arr);	
		return myselect(arr,0,arr.length-1,k-1);
	}
	
	private static<T extends Comparable<T>> int partition(T[] arr, int start, int end){
		int pivot = start;
		int i = start + 1;
		int j = end;
		while(true){
			while(i < end && arr[i].compareTo(arr[pivot]) >= 0)  i++;
			while(j > (start + 1) && arr[j].compareTo(arr[pivot]) <= 0)  j--;
			if(i > j || (i==j && arr[j].compareTo(arr[pivot]) >= 0)){
				swap(arr,j,pivot);
				return j;
			}
			swap(arr,i,j);
			i++;j--;
		}
	}

	private static <T extends Comparable<T>> T myselect(T[] arr, int start, int end, int k){
		int pindex = partition(arr,start,end);
		if(pindex == k) return arr[pindex];
		if(pindex > k) return myselect(arr,start,pindex-1,k);
		if(pindex < k) return myselect(arr,pindex+1,end,k);
		return null;
	}

	public static void main(String[] args){
		Integer[] arr = {1,-4,5,7,89,-23};
		int k = 1; //1 for highest element, 2 for second highest and so on..	
		System.out.printf("QuickSelect for k: %d is %d\n",k,QuickSelect.select(arr,k));	
	}
}
