
/**
 * Abstract class for common functionality among knapsack algorithms.
 * 
 * @author jefcha
 */
public abstract class KnapsackAlgor
{
	/** Dynamic table. */
	protected int[][] mTable;
	
	/**
	 * Initialise dynamic table.
	 * Note the dynamic table is has a row representing 0 items, and a column for 0 weight capacity.
	 * 
	 * @param ksProblem Knapsack instance.
	 */
	public KnapsackAlgor(Knapsack ksProblem) {
		// ksProblem itemNum would include an extra dummy item, so don't need to add 1 to include the 0 item or 0 weight
		// row/column in dynamic table, but need to add 1 to weightCapacity
		mTable = new int[ksProblem.itemNum()][ksProblem.weightCapacity()+1];
		
		// initialise the first row and column with 0
		for (int w = 0; w < ksProblem.weightCapacity()+1; w++) {
			mTable[0][w] = 0;
		}

		for (int i = 0; i < ksProblem.itemNum(); i++) {
			mTable[i][0] = 0;
		}		
	} // end of KnapsackAlgor()

	
	/**
	 * Get value of table.
	 * 
	 * @param item Which item.
	 * @param weight Which weight capacity.
	 * 
	 * @return Total value for the sub-problem with that amount of considered items and weight capacity.
	 */
	public int getValue(int item, int weight) {
		return mTable[item][weight];
	}
	
	
	/**
	 * Prints out the dynamic table.
	 */
    public void printTable() {
        for (int i = 0; i < mTable.length; i++) {
            for (int j = 0; j < mTable[i].length; j++) {
                System.out.print(mTable[i][j] + " ");
            }
            System.out.println();
        }
    } // end of printTable()

} // end of class KnapsackAlgor
