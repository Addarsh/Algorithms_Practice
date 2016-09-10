/*
Mergesort is one of the most
standard sorting algorithms in use.
It uses NlogN time (without optimizations).
Good for large arrays and overkill for small ones.
A few optimizations include
1. Stop comparisons for sorted subarrays
2. Use insertion sort on smaller subarrays
3. One more optimization which I did not understand :P
*/

public class MergeSort{

	private static int CUTOFF = 2;
	private static void swap(int[] arr,int i, int j){
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;	
	}

	private static void insertionSort(int[] arr,int start, int end){
		int arrlen = end - start + 1;
		for(int i =0; i < arrlen-1;i++){
			for(int j = i+1; j > 0;j--){
				if(arr[j] < arr[j-1]) swap(arr,j,j-1);
				else break;
			}
		}
	}	

	public static void merge(int[] arr,int[] mergearr,int start,int mid,int end){
		//Copy elements into merge
		for(int i =start; i <=end;i++) mergearr[i] = arr[i];		
		int i = start; int j = mid+1; int k = start;
		while(i <= mid && j <= end){
			if(mergearr[i] <= mergearr[j]){
				arr[k] = mergearr[i]; i++;
			} else {
				arr[k] = mergearr[j]; j++;	
			}
			k++;
		}
	  while(i <= mid){
			arr[k] = mergearr[i]; k++;i++;
		}
		while(j <= end){
			arr[k] = mergearr[j]; k++;j++;
		}
	}

	public static void sort(int[] arr,int[] mergearr, int start, int end){
		if(start >= end) return;
		//Add insertion sort optimization
		if(end-start + 1 <= CUTOFF){
			insertionSort(arr,start,end);
			return;
		}
		int mid = (start+end)/2;
		sort(arr,mergearr,start,mid);
		sort(arr,mergearr,mid+1,end);
		//Add optimization to check if both arrays are in sorted order
		if(arr[mid] < arr[mid+1]) return;
		merge(arr,mergearr,start,mid,end);	
	}

	public static void main(String[] args){
		int[] arr = {99,-2,56,4,77};
		int[] mergearr = new int[arr.length];
		MergeSort.sort(arr,mergearr,0,arr.length-1);
		for(int i =0; i < arr.length;i++){
			System.out.printf("%d\n",arr[i]);
		}
	}
}
