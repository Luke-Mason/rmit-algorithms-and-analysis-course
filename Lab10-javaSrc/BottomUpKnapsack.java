
/**
 * Implementation for bottom up knapsack.
 * 
 * @param jefcha
 */
public class BottomUpKnapsack extends KnapsackAlgor
{
	
	/**
	 * Bottom up knapsack.
	 * 
	 * @param ksProblem Knapsack instance.
	 */
	public BottomUpKnapsack(Knapsack ksProblem) {
		super(ksProblem);
		int n = ksProblem.mItems.length();
		int W = ksProblem.mWeightCapacity;
		ksProblem[] array2 = ksProblem[n];
		int[][] array = int[n][W];
		for(int i = 0; i < n; i++)
		{
			array[i][0] = 0;
		}
		for(int j = 1; j < W; j++)
		{
			array[0][j] = 0;
		}
		for(int i =0; i < n; i++)
		{
			for(int j = 1; j < W; j++)
			{
				if(j-array2[].mWeight >= 0)
				{
					array[i][j] = max(array[i-1][j],array2[i].mValue+array[i-1][j-array2[i].mWeight]);
				}
				else
				{
					array[i][j] = array[i-1][j];
				}
			}
		}		
	} // end of BottomUpKnapsack()
	

} // end of class BottomUPKnapsack
