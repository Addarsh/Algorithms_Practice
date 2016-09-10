/*
Shell Sort is like Insertion sort
whith h sorting. In h-sorting, we run
insertion sort by skipping h places
back instead of 1. This makes the array
partially sorted before we can finally apply
hsort. The algorithm runs at N^(1.5) or so.
The h sequence we will use will be 3K+1.
*/

public class ShellSort{

	private static void swap(int[] arr, int i, int j){
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}

	public static void sort(int[] arr){
		int arrlen = arr.length;
		if(arrlen == 0) return;
		int k =0;
		while((3*k+1) < arrlen){
			k++;
		}
		int h = 3*(k-1) + 1;
		while(h > 0){
			//Perform h sort repeatedly
			for(int i =0; i < arrlen-1;i++){
				for(int j = i+1;j>0;j--){
					if((j-h >= 0) && (arr[j] < arr[j-h])) swap(arr,j,j-h);
					else break;
				}
			}
			h = h/3;	
		}
	}

	public static void main(String[] args){
		int[] arr = {4,89,-9,-12,34}; 
		ShellSort.sort(arr);
		for(int i =0; i < arr.length; i++){
			System.out.printf("%d\n",arr[i]);
		}
	}
}
