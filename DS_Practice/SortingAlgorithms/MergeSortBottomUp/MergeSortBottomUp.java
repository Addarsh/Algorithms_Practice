/*
 *COde to perform merge sort non-recursively.
*/

class MergeSortBottomUp{

  public static void merge(int[] arr, int start, int mid, int end){

    int[] temp = new int[arr.length];
    for(int m=0; m < arr.length;m++) temp[m] = arr[m];
    int i = start;
    int j= mid+1;
    for(int k = start;k < end-start+1; k++){
      if(i > mid) arr[k] = temp[j++];
      else if (j > end) arr[k] = temp[i++];
      else if (temp[i] < temp[j]) arr[k] = temp[i++];
      else arr[k] = temp[j++];
    }

	}

  public static int myMin(int a, int b){
   if (a <b) return a;
   return b;				
	}

  public static void mergeSort(int[] arr){
    int blockSize=1;  
    int len = arr.length;
    
    //Break into blocks and then merge
		while(blockSize < len){
			int index = 0;
			while(index < len-blockSize){
   			merge(arr,index,index+blockSize-1,myMin(len-1,index+blockSize*2-1));
 				index += 2*blockSize;
			}
			blockSize *= 2;
		}
  }

  public static void main(String[] args){
    int[] arr = {10,2,-3,4};
		mergeSort(arr);
	
		for(int i =0; i < arr.length; i++){
			System.out.printf("%d\n",arr[i]);
  	}

	}

}
