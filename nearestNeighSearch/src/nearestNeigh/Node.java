
package nearestNeigh;
public class Node 
{
	private Point data;
	private Node parent = null;
	private Node leftChild = null;
	private Node rightChild = null;
	public Node (Point data)
	{
		this.data = data;
	}
	public Node nextLeftNode()
	{
		return leftChild;
	}
	public Node nextRightNode()
	{
		return rightChild;
	}
	public Node getParent()
	{
		return parent;
	}
	public Point getData()
	{
		return data;
	}
	public void setRightNode(Node node)
	{
		rightChild = node;
	}
	public void setLeftNode(Node node)
	{
		leftChild = node;
	}
	public void setParent(Node node)
	{
		parent = node;
	}
	public void setData(Point point)
	{
		data = point;
	}
}

