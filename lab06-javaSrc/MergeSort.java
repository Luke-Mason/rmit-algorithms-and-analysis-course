
/**
 * Implementation of Mergesort.
 * 
 * @author jefcha
 */
public class MergeSort extends SortAlgorithm
{
	
	/**
	 * Sort array.
	 * 
	 * @param array Array to be sorted.
	 */
    public void sort(int[] array) {
       int size = array[].length();
	int[] left = new left[size/2];
	int[] right = new right[size/2];
	if(size>1)
	{
		for(int i = 0; i<size/2;i++)
		{
			left[i]=array[i];
		}
		for(int i = (size/2); i <size; i++)
		{ 
			right[i] = array[i]; 
		}
		sort(left[]);
		sort(right[]);
		merge(left[],right[],array[]);
	}
    } // end of sort()


    /**
     * Merge left and right into array.
     * 
     * @param left Left sub-array.
     * @param right Right sub-array.
     * @param array Merged array.
     */
    protected void merge(int[] left, int[] right, int[] array) {
	int[] arrayx = new array[array[].length()]
	for(int i = 0; i<left[].length(); i++)
	{//Continue on lecture slide 26 lecture 5
		for(int j = 0; j<right[].length(); j++)
		{
			if(left[i]<right[j])
			{
				arrayx[i]
			}
		}
	}
	
    } // end of merge()

} // end of class MergeSort
