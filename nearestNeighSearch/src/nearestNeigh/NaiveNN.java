package nearestNeigh;

import java.util.Arrays;
import java.util.Collections;
import java.util.ArrayList;
import java.util.List;

/**
 * This class is required to be implemented.  Naive approach implementation.
 *
 * @author Jeffrey, Youhan
 */
public class NaiveNN implements NearestNeigh{
	
	private ArrayList<Point> index;
	
	@Override
	public void buildIndex(List<Point> points) {
        // To be implemented.
        
//	this.index = points;
    }

    @Override
    public List<Point> search(Point searchTerm, int k) {
	List<Point> points = this.index;
        int n = this.index.size();

        List<Point> result = new ArrayList<Point>();

        for (int i = 0; i < n; i++)
        {
            for (int j = 0; j < n-1; j++)
            {
                Point p = points.get(j);
                Point next = points.get(j+1);

                double p_distance = p.distTo(searchTerm);
                double next_distance = next.distTo(searchTerm);

                if (p_distance > next_distance)
                {
                    points.set(j, next);
                    points.set(j+1, p);
                }
            }
        } 

        for (int i = 0; i < n; i++)
        {
            if (points.get(i).cat == searchTerm.cat)
            {
                result.add(points.get(i));
                k--;

                if (k == 0)
                {
                    return result;
                }       
            } 
        }

        return result;
    }

    @Override
    public boolean addPoint(Point point) 
    {
        if(this.isPointIn(point))
	{
            return false;
	}
        this.index.add(point);
	return true; 
    }

    @Override
    public boolean deletePoint(Point point) {
        return false;
    }

    @Override
    public boolean isPointIn(Point point) {
        
        return false;
    }

}
