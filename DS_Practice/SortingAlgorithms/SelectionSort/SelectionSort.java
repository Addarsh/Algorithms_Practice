/*Implementation of the selection
sort algorithm. During selection sort,
every element to the left of the counter
is arranged in ascending order and every
element to the right is random. No element
on the right is smaller than elements on
the lef. It takes about N^2/2 time to run.
*/


public class SelectionSort{
	private static void swap(int[] arr,int i, int j){
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}

	public static void sort(int[] arr){
		int arrlength = arr.length;
		for(int i = 0; i < arrlength; i++){
			int minpos = i;
			int j;
			for(j = i+1; j < arrlength;j++){
				if(arr[j] < arr[minpos]){
					minpos = j;
				}
			}
			swap(arr,i,minpos);
		}
	}

	public static void main(String[] args){
		int[] arr = {4,5,-3,6,77,-24};
		SelectionSort.sort(arr);
		for(int i =0; i < arr.length;i++){
			System.out.printf("%d\n",arr[i]);
		}
	}
}
