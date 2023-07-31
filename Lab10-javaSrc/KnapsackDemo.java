
/**
 * Demonstrate knapsack algorithms.
 * 
 * @param jefcha
 */
public class KnapsackDemo
{
	protected static final String progName = "KnapsackDemo";
	
    /**
     * Print usage information.
     * 
     * @param progName Program name.
     */
    protected static void printUsage(String progName) {
      	System.err.println("USAGE: " + progName + " [item number] [weight capacity] [verbose (true|false)]");
    } // end of printUsage()	
	
	public static void main(String[] args) {
		
        // not enough arguments
        if (args.length != 3) {
            printUsage(progName);
            System.exit(1);
        }
        
        // number of items
        int itemNum = Integer.parseInt(args[0]);
        // total weight capacity of knapsack
        int weightCap = Integer.parseInt(args[1]);	
        // verbose or not
        boolean verbose = Boolean.parseBoolean(args[2]);
		
		
		Knapsack problem = new Knapsack();
		
		// generate random instance of problem
		problem.generateRandomItems(itemNum, weightCap);
		
		// print generated knapsack instance
		problem.printItems();

		
		// solve

		// bottom up knapsack
		long startTime = System.nanoTime();
		BottomUpKnapsack buKnapsack = new BottomUpKnapsack(problem);
		long endTime = System.nanoTime();
		
		// print out information
        double timeElapsed = (endTime - startTime) / Math.pow(10, 9);
		System.out.println("Bottom up Knapsack time taken = " + timeElapsed);
		System.out.println("Maximum value = " + buKnapsack.getValue(itemNum, weightCap));
		
		// print out dynamic table
		if (verbose) {
			buKnapsack.printTable();
		}
		
		
		// top down knapsack
		startTime = System.nanoTime();
		TopDownKnapsack tdKnapsack = new TopDownKnapsack(problem);
		endTime = System.nanoTime();
		
		// print out information
        timeElapsed = (endTime - startTime) / Math.pow(10, 9);
		System.out.println("Bottom up Knapsack time taken = " + timeElapsed);
		System.out.println("Maximum value = " + tdKnapsack.getValue(itemNum, weightCap));		

		// print out dynamic table
		if (verbose) {
			tdKnapsack.printTable();
		}		
		
	} // end of main()
	
} // end of class KnapsackDemo
