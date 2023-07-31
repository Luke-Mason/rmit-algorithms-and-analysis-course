import java.util.*;

/**
 * Class to implement Gale-Shapley algorithm for stable marriage.
 * 
 * @param jefcha
 */
public class StableMarriage
{
	
	/**
	 * Female proposing Gale-Shapley algorithm.
	 * 
	 * @param femalePrefs Array of female preferences.
	 * @param maleprefs Array of male prefernces.
	 * 
	 * @return Array of matches.  A[m] = f, indicates male 'm' is matched to female 'f'.
	 */
    public static int[] match(int[][] femalePrefs, int[][] malePrefs) {

        // matches of male to female.  Array indices map to males (id), while
        // array entries map to females (id).
        int[] matches = new int[femalePrefs.length];

	// IMPLEMENT ME!

        return matches;
    } // end of match()
} // end of class StableMariage
