import java.io.*;

/**
 * Demonstration of the stable marriage and Gale-Shapley algorithm.
 * This class handles the preference loading and calling algorithm to compute the stable matching.
 * 
 * @author jefcha
 */
public class StableMarriageDemo
{
    static final String progName = "StableMarriageDemo";

    /**
     * Print usage information.
     */
    public static void usage() {
    	System.err.println("java StableMarriageDemo [parameter file]");
    } // end of usage()
    
    
    /**
     * Main method.
     */
    public static void main(String[] args) {

    	// check number of arguments
        if (args.length < 1) {
            usage();
            System.exit(1);
        }

        // preference file
        String prefFile = args[0];
        
        // preference 2d array
        int[][] femalePrefMatrix = null;
        int[][] malePrefMatrix = null;

        // read in male/female preferences
        try {
	        BufferedReader buffIn = new BufferedReader(new FileReader(prefFile));
	        String line;
	        String delimiter = " ";
	        int femaleNum = 0;
	        int maleNum = 0;
	
	        // read in number of females and males
	        if ((line = buffIn.readLine()) != null) {
	            String[] tokens = line.split(" ");
	            if (tokens.length != 2) {
	                System.err.println("Error: Number of females/males not specified.");
	                System.exit(-1);
	            }
	
	            // readin number of females and males
	            femaleNum = Integer.parseInt(tokens[0]);
	            maleNum = Integer.parseInt(tokens[1]);
	            
	            femalePrefMatrix = new int[femaleNum][maleNum];
	            malePrefMatrix = new int[maleNum][femaleNum];
	 
	            // read in female preferences, which have format
	            // (each line correspond to a female's preference, with the line entries being their male preferences,
	            // most preferred to least.  E.g.,  2 1 0, which means male id 2 is most preferred.
	            for (int f = 0; f < femaleNum; f++) {
	                if ((line = buffIn.readLine()) != null) {
	                    tokens = line.split(" ");
	                    if (tokens.length != maleNum) {
	                        System.err.println("Error: Incorrect number of males in female preferences");
	                        usage();
	                        System.exit(1);
	                    }
	
	                    for (int m = 0; m < maleNum; m++) {
	                        femalePrefMatrix[f][Integer.parseInt(tokens[m])] = m;
	                    }
	                }
	            }
	
	            // read in male preferences
	            for (int m = 0; m < maleNum; m++) {
	                if ((line = buffIn.readLine()) != null) {
	                    tokens = line.split(" ");
	                    if (tokens.length != femaleNum) {
	                        System.err.println("Error: Incorrect number of females in male preferences");
	                        usage();
	                        System.exit(1);
	                    }
	
	                    for (int f = 0; f < maleNum; f++) {
	                        malePrefMatrix[m][Integer.parseInt(tokens[f])] = f;
	                    }
	                }
	            }
	        }
	
	        // find stable marriage
	        int[] matches = StableMarriage.match(femalePrefMatrix, malePrefMatrix);
	
	        // print out results
	        System.out.println("male to female matches");
	        for (int m = 0; m < matches.length; m++) {
	            System.out.println(m + " <---> " + matches[m]);
	        }
        }
        catch(FileNotFoundException exp) {
        	System.err.println("File not found");
        	usage();
        	System.exit(1);
        }
        catch(IOException exp) {
        	System.err.println(exp.getMessage());
        	usage();
        	System.exit(1);
        }
    } // end of main()

} // end of class StableMarriageDemo
