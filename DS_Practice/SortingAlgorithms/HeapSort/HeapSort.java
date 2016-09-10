/*
HeapSort implemented using a 
binary tree. Build Heap first 
and perform sort after. Total 
time complexity is NlogN and this sort
is inplace. It is still not stable 
and makes poor use of caching.
*/

public class HeapSort{

	private static int N;

	private static <T extends Comparable<T>> void swap(T[] arr,int i , int j){
		T temp = arr[i-1];
		arr[i-1] = arr[j-1];
		arr[j-1] = temp;
	}
	
	private static <T extends Comparable<T>> void sink(T[] arr,int k){
		while(2*k <= N){
			int i = 2*k-1;
			if(i < N-1 && arr[i].compareTo(arr[i+1]) <= 0) i++;
			if(arr[k-1].compareTo(arr[i]) < 0) swap(arr,k,i+1);
			else break;
			k = i+1;
		}
	}	
	
	public static <T extends Comparable<T>> void buildHeap(T[] arr){
		for(int i = N/2; i >= 1; i--){
			sink(arr,i);
		}
	}

	private static <T extends Comparable<T>> void mysort(T[] arr){
		int totalTimes = N;
		for(int i = 1; i <= totalTimes; i++){
			swap(arr,1,N);
			N--;
			sink(arr,1);
		}
	}

	public static <T extends Comparable<T>> void sort(T[] arr){
		N = arr.length;
		buildHeap(arr);
		mysort(arr);
	}
	public static void main(String[] args){
		Integer[] arr = {-2,-4,2,6,77,-42,10};
		HeapSort.sort(arr);
		for(int i =0; i < arr.length;i++){
			System.out.printf("%d\n",arr[i]);
		}
	}	
}	
