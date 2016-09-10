/*
Impementing a binary search
algorithm
Executes in time logn
*/

import java.lang.Comparable;

public class BinarySearch{
	
	public static<T extends Comparable<T>> boolean sort(T[] arr,T search,int start, int end){
		if(start > end) return false;
		int mid = (start + end)/2;
		if(arr[mid].compareTo(search) == 0) return true;
		if(arr[mid].compareTo(search) > 0) return sort(arr,search,start,mid-1);	
		if(arr[mid].compareTo(search) < 0) return sort(arr,search,mid+1,end);	
		return false;
	}

	public static void main(String[] args){
		String[] arr = {"a","b","c","d","e"};
		boolean result = BinarySearch.sort(arr,"e",0,arr.length-1);
		if(result) System.out.printf("Element found!\n");
		else System.out.printf("Element not found!\n");	
	}
}

