import java.util.*;
import java.io.*;

/**
 * Compute depth first search traversal on a graph.
 * 
 *  @author jefcha
 */
public class DFS 
{
    /** Name of the class. */
    private static final String progName = "DFS";       

    
    /** 
     * Depth first search traversal of input graph g from seed vertex s.
     * 
     * @param g Input graph.
     * @param s Seed vertex to start traversal from.
     * 
     * @returns ArrayList of vertices, stored in the order they were visited in 
     *      DFS traversal.
     */
    public static ArrayList<Integer> traverse(Graph g, int s) {
	ArrayList<Integer> traversalOrder = new ArrayList<Integer>();

    	// IMPLEMENT ME
    	
    	return traversalOrder;
    } // end of traverse()



    
    /**
     * Print out usage messge and exits from program.
     */    
    protected static void usage() {
    	System.err.println(DFS.progName + ": <input graph file> <starting vertex>");
    	System.exit(1);
    }
    
    
    /**
     * Perform DFS traversal for input graph.
     */
    public static void main(String[] args) {
    	if (args.length != 2) {
    		usage();
    	}
    	
    	try {
            // input graph
            InputStream in = new FileInputStream(args[0]);
            Graph g = new Graph(in);
        
            // seed vertex
            int s = Integer.parseInt(args[1]);
    		
            // perform DFS
            ArrayList<Integer> traversalOrder = DFS.traverse(g, s);

            // print out the traversal order of the BFS
            Iterator<Integer> it = traversalOrder.iterator();
            while (it.hasNext()) {
    		System.out.print(it.next() + " ");
            }
            
            System.out.println("");
    	} 
    	catch (FileNotFoundException e) {
            System.err.println(e.getMessage());
    	}
    } // end of main()

} // end of class DFS
