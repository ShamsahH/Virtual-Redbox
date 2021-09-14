//Shamsah Hossain
//sxh170009@utdallas.edu
//cs2336 project 3


package Redbox;

public class Node
{
	//members 
	
	protected String title;
	protected int available;
	protected int rented;
	protected Node left;
	protected Node right;
	
	//methods 
	
	public Node()
	{
		title = ""; available = 0; rented = 0; left = null; right = null;
	}
	
	public Node(String title, int available, int rented, Node left, Node right)
	{
		this.title = title; this.available = available; this.rented = rented; this.left = left; this.right = right;
	}
	
	//mutators and accessors
	
	public String getTitle()
	{
		return title;
	}
	
	public int getAvailable()
	{
		return available;
	}
    public int getRented()
    {
    	return rented; 	
    }
    public Node getLeft()
    {
    	return left;
    }
    public Node getRight()
    {
    	return right;
    }
	
    public void setTitle(String title)
    {
    	this.title = title;
    }
    public void setAvailable(int available)
    {
    	this.available = available;
    }
    public void setRented(int rented)
    {
    	this.rented = rented;
    }
    public void setRight(Node right)
    {
    	this.right = right;
    }
    public void setLeft(Node left)
    {
    	this.left = left;
    }
}
