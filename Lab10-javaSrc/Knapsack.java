import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;



/**
 * Instance of knapsack problem.
 * 
 * @author jefcha
 */
public class Knapsack
{

	/**
	 * Represent an item.
	 */
	public class Item
	{
		/** Weight of item. */
		public int mWeight;
		/** Value of item. */
		public int mValue;
	} // end of inner class Items


	/** Set of items that could be put into knapsack. */
	protected Item[] mItems;
	/** total weight of problem instance. */
	protected int mWeightCapacity;


	public Knapsack() {
		mItems = null;
		// default of zero weight
		mWeightCapacity = 0;
	}

	/**
	 * Returns the number of items, which includes the dummy item.
	 * 
	 * @return Number of real items, + 1
	 */
	public int itemNum() {
		return mItems.length;
	}


	public int weightCapacity() {
		return mWeightCapacity;
	}

	public int getWeight(int itemIndex) {
		return mItems[itemIndex].mWeight;
	}

	public int getValue(int itemIndex) {
		return mItems[itemIndex].mValue;
	}


	/**
	 * Generate random problem instance.
	 * 
	 * @param itemNum Number of items to generate.
	 * @param totalWeight Weight capacity of knapsack.
	 */
	public void generateRandomItems(int itemNum, int totalWeight) {
		Random randGen = new Random();
		
		// we index items from 1 to itemNum (inclusive) to be consistent with the dynamic tables
		mItems = new Item[itemNum+1];
		mWeightCapacity = totalWeight;
		
		// max weight for any item
		int maxWeightPerItem = (int) Math.floor(totalWeight / 2);
		// max value for any item
		int maxItemValue = 20;

		// generate the items
		for (int i = 1; i < itemNum+1; ++i) {
			Item newItem = new Item();
			newItem.mWeight = 1 + (randGen.nextInt(maxWeightPerItem));
			newItem.mValue = 1 + (randGen.nextInt(maxItemValue));
			mItems[i] = newItem;
		}

	} // end of genrateRandomItems()


	/**
	 * Print out the items in this instance.
	 */
	public void printItems() {
		System.out.println("item | weight | value");

		// don't print out dummy
		for (int i = 1; i < mItems.length; i++) {
			Item currItem = mItems[i];
			System.out.println(i + " | " + currItem.mWeight + " | " + currItem.mValue);
		}
	} // end of printItems()
	

	
} // end of class Knapsack
