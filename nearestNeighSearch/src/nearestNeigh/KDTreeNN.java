package nearestNeigh;

import java.util.ArrayList;
import java.util.List;
import static nearestNeigh.Category.*;

/**
 * This class is required to be implemented.  Kd-tree implementation.
 *
 * @author Jeffrey, Youhan
 */
public class KDTreeNN implements NearestNeigh
{
	private int count = 0;
	private Node head;
	private Node current;

	public Node pointIntoNode(Point point)
	{
		Node node = new Node(point);
		return node;
	}
	public Node kdtree(List<Point> pointList, int depth, Node parent)
	{
		List<Point> sortedList = new ArrayList<Point>();
		List<Point> beforeMedian = new ArrayList<Point>();
		List<Point> afterMedian = new ArrayList<Point>();
		Point nextLowestPoint;
		int axis = depth%2;
		int pointListSize = pointList.size();
		if(axis == 0)
		{
			
			for(int i = 0; i < pointListSize; i++)
			{
				nextLowestPoint = pointList.get(0);
				//System.out.println("i = "+i);
				//System.out.println("size = "+pointList.size());
				for(Point p : pointList)
				{
					//System.out.println(p.toString());
					if(p.lat < nextLowestPoint.lat)
					{
						nextLowestPoint = p;
					}
				}
				//System.out.println("found lowest point");
				sortedList.add(nextLowestPoint);//assigns lowest point to sorted list
				//System.out.println("added to sorted list");
				//System.out.println("Index Of point = "+nextLowestPoint.id);
				//System.out.println("removed point "+pointList.indexOf(nextLowestPoint));
				pointList.remove(pointList.indexOf(nextLowestPoint));//deletes lowest point from old list	
			}
			if(pointList.size() != 0)
			{
				System.out.println("ERROR: Point list is not empty for some reason");
				System.out.println("Size is "+pointList.size());
			}
		}
		else if(axis == 1)
		{
			for(int i = 0; i < pointListSize; i++)
			{
				nextLowestPoint = pointList.get(0);
				//System.out.println("i = "+i);
				//System.out.println("size = "+pointList.size());
				for(Point p : pointList)
				{
					if(p.lon < nextLowestPoint.lon)
					{
						nextLowestPoint = p;
					}
				}
				//System.out.println("found lowest point");
				sortedList.add(nextLowestPoint);//assigns lowest point to sorted list
				//System.out.println("added to sorted list");
				//System.out.println("Index Of point = "+nextLowestPoint.id);
				//System.out.println("removed point "+pointList.indexOf(nextLowestPoint));
				pointList.remove(pointList.indexOf(nextLowestPoint));//deletes lowest point from old list
				
			}
			if(pointList.size() != 0)
			{
				System.out.println("ERROR: Point list is not empty for some reason");
				System.out.println("Size is "+pointList.size());
			}	
		}
		else
		{
			System.out.println("axis != 0 or 1");
			return null;
		}
		int size = sortedList.size();
		//System.out.println("sorted size = "+sortedList.size());
		int medianIndex = 0;
		if(size%2 == 1)
		{
			medianIndex = (int)(size)/2;
		}
		if(size%2==0)
		{
			medianIndex = (size/2)-1;
		}
		for(int j = 0; j<medianIndex; j++)
		{
			//System.out.println("bm add id: "+sortedList.get(j).id);
			beforeMedian.add(sortedList.get(j));
		}
		for(int k = medianIndex +1; k<size; k++)
		{
			//System.out.println("am add id: "+sortedList.get(k).id);
			afterMedian.add(sortedList.get(k));
		}
		//System.out.println("m id: "+sortedList.get(medianIndex).id);
		Point median = sortedList.get(medianIndex);
		Node nMedian = pointIntoNode(median);
		System.out.println("Parent = " + nMedian.getParent());
		nMedian.setParent(parent);
		if(beforeMedian.size() > 0)
		{
			//System.out.println("Getting Right node");
			nMedian.setLeftNode(kdtree(beforeMedian,depth+1,nMedian));
		}
		if(afterMedian.size() > 0)
		{	
			//System.out.println("Getting Left Node");
			nMedian.setRightNode(kdtree(afterMedian,depth+1,nMedian));
		}
		++count;
		return nMedian;
	}

	@Override
	public void buildIndex(List<Point> points) 
	{
		head = kdtree(points, 0,null);
		/*double highest_x_value = 0;
		double lowest_x_value = null;
		double highest_y_value = 0;;
		double lowest_y_value = null;
		int size = points.size();
		for(Point p2: points)
		{
			if(lowest_x_value == null)
			{
				lowest_x_value = p2.lat;
				lowest_y_value = p2.lon;
			}//initiating variables
			if(p2.lat > highest_x_value)
			{
				highest_x_value = p2.lat;
			}
			if(p2.lat < lowest_y_value)
			{
				lowest_x_value = p2.lat;
			}
			if(p2.lon > highest_y_value)
			{
				highest_y_value = p2.lon;
			}
			if(p2.lon < lowest_y_value)
			{
				lowest_y_value = p2.lon;
			}
		}
		double medianX = (highest_x_value - lowest_x_value)/2;
		double medianY = (highest_y_value - lowest_y_value)/2;
		for(Point p3 : points)
		{
			
		}
		for(Point p: points)
		{
			count++;
			addPoint(p);
		}
		System.out.println();*/
		print(head, "h");

	//	System.out.println("head left: " + head.nextLeftNode().getData().id);

	}



	public void print(Node node, String direction)
	{
		System.out.println(direction + " : " + node.getData().lat + ", " + node.getData().lon);

		if (node.nextLeftNode() != null)
			print(node.nextLeftNode(), direction+"l");
		if (node.nextRightNode() != null)
			print(node.nextRightNode(), direction+"r");


	}
	public double checkNodeDist(Node node, Point searchTerm)
	{
		return current.getData().distTo(searchTerm);
	}

	public Node checkSmallestDist(Node root, Point searchTerm, int depth)
	{
		current = root;
		Node closestNode = current;
		while(current.getParent() != null)
		{
			if(current.getParent().nextRightNode().getData().equals(current.getData()))
			{
				current = current.getParent();
				depth--;
				if(checkNodeDist(closestNode, searchTerm) > checkNodeDist(current, searchTerm))
				{
					closestNode = current;
					if(current.nextLeftNode() != null);
					{
						Node placeHolder = penetration(current.nextLeftNode(), searchTerm,depth);
						if(checkNodeDist(placeHolder,searchTerm) < checkNodeDist(closestNode, searchTerm))
						{
							closestNode = placeHolder;
						}
					}
						
				}
				else
				{
					Node placeHolder = checkSmallestDist(current,searchTerm, depth);
					if(checkNodeDist(placeHolder,searchTerm) < checkNodeDist(closestNode, searchTerm))
					{
						closestNode = placeHolder;
					}
				}
			}
			else if(current.getParent().nextLeftNode().getData().equals(current.getData()))
			{	
				current = current.getParent();
				depth--;
				if(checkNodeDist(closestNode, searchTerm) > checkNodeDist(current, searchTerm))
				{
					closestNode = current;
					if(current.nextRightNode() != null);
					{
						Node placeHolder = penetration(current.nextRightNode(), searchTerm,depth);
						if(checkNodeDist(placeHolder,searchTerm) < checkNodeDist(closestNode, searchTerm))
						{
							closestNode = placeHolder;
						}
					}
						
				}
				else
				{
					Node placeHolder = checkSmallestDist(current,searchTerm, depth);
					if(checkNodeDist(placeHolder, searchTerm) < checkNodeDist(closestNode, searchTerm))
					{
						closestNode = placeHolder;
					}
				}
			}
			else
			{
				System.out.println("Error, node does not match either parent children, WTF??!");
			}
		
		}
		return closestNode;
	}
	public Node penetration(Node head, Point searchTerm, int depth)
	{	
		current = head;
		int i = depth;
		while(true)
		{
			if(i%2 == 0)//Compare X-coords
			{
					if(current.getData().lat < searchTerm.lat)
					{
						if(current.nextRightNode() != null)
						{
							current = current.nextRightNode();	
						}
						else
						{
							return checkSmallestDist(current, searchTerm, i);
						}
					}
					else
					{
						if(current.nextLeftNode() != null)
						{
							current = current.nextLeftNode();
						}
						else
						{
							return checkSmallestDist(current, searchTerm, i);
						}
					}
				
			}
			if(i%2 == 1)//Compare Y-cords
			{
			    if(current.getData().lon < searchTerm.lon)
				{
					if(current.nextRightNode() != null)
					{
						current = current.nextRightNode();
					}
					else
					{
						return checkSmallestDist(current, searchTerm, i);
					}
				}
				else
				{
					if(current.nextLeftNode() != null)
					{
						current = current.nextLeftNode();
					}
					else
					{
						return checkSmallestDist(current, searchTerm, i);
					}
				}
			}
			++i;
		    }	
	}

	@Override
	public List<Point> search(Point searchTerm, int k) //Only finds one closest Node so far
	{	
		ArrayList<Point> list =  new ArrayList<Point>();
		current = head;
		int i = 0;
		while(i != k)
		{
			list.add(penetration(current, searchTerm,0).getData());	//Need to delete the node, just added to list, from the tree then rerun the pentration test for the next closest node
			//delete Node from tree
			++i;
		}
		return list;
	}

	@Override
	public boolean addPoint(Point point) 
	{
		Node newNode = pointIntoNode(point);
                if(count == 1)
		{
			head = newNode;
			++count;
		}
		else
		{
			current = head;
			int i = 1;
			while(true)
			{
				if(i%2 == 1)
				{
				if(current.getData().lat < newNode.getData().lat)//comparing x-coords
				    {
					if(current.nextRightNode() != null)
					{
						current = current.nextRightNode();
						i++;
					}
					else
					{
						current.setRightNode(newNode);
						current.nextRightNode().setParent(current);
						return true;
					}
				    }
				    else
				    {
					if(current.nextLeftNode() != null)
					{
						current = current.nextLeftNode();
						i++;
					}
					else
					{
						current.setLeftNode(newNode);
						current.nextLeftNode().setParent(current);
						return true;
					}
				    }
				}
				if(i%2 == 0)
				{
					System.out.println(i);
				    if(current.getData().lon < newNode.getData().lon)
				    {
					if(current.nextRightNode() != null)
					{
						current = current.nextRightNode();
						i++;
					}
					else
					{
						current.setRightNode(newNode);
						return true;
					}
				    }
				    else
				    {
					if(current.nextLeftNode() != null)
					{
						current = current.nextLeftNode();
						i++;
					}
					else
					{
						current.setLeftNode(newNode);
						return true;
					}
				    }
				} 
			}
		}
		System.out.print("\n			Didn't add point ID: "+newNode.getData().id+" - ("+newNode.getData().lat+","+newNode.getData().lon+")");
                return false;
	}

	@Override
	public boolean deletePoint(Point point) {
	// To be implemented.
	return false;
	}

	@Override
	public boolean isPointIn(Point point) 
	{
		current = head;
		int i = 1;
		while(true)
		{
			if(current.getData().equals(point))
			{
			    return true;
			}
			if(i%2 == 1)//Compare X-coords
			{
				if(current.getData().lat < point.lat)
				{
					if(current.nextRightNode() != null)
					{
						current = current.nextRightNode();
						i++;
					}
					else
					{
						return false;
					}
				}
				else
				{
					if(current.nextLeftNode() != null)
					{
						current = current.nextLeftNode();
						i++;
					}
					else
					{
						return false;
					}
				}
			}
			if(current.getData().equals(point))
			{
			    return true;
			}
			if(i%2 == 0)//Compare Y-cords
			{
			    if(current.getData().lon < point.lon)
				{
					if(current.nextRightNode() != null)
					{
						current = current.nextRightNode();
						i++;
					}
					else
					{
						return false;
					}
				}
				else
				{
					if(current.nextLeftNode() != null)
					{
						current = current.nextLeftNode();
						i++;
					}
					else
					{
						return false;
					}
				}
			}
		    }
	}
}
