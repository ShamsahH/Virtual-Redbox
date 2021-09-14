//Shamsah Hossain
//sxh170009@utdallas.edu
//cs2336 project 3

package Redbox;

import java.io.PrintWriter;

public class BST 
{
 //members 
	protected Node root; 
	
	//methods
	
	 public BST()
	 {
		 root = null;
	 }
	 public BST(Node root)
	 {
		 this.root = root;
     }
	  
	 public Node getRoot()
	 {
	    return root;
	 }
	 public void setRoot(Node root)
	 {
		 this.root = root;
     }
// method to insert a node into the binary search tree from both files 
	 // handles duplicates
protected Node insertNode( Node root , Node n, Node parent ) 
{
		
	        if (root == null)
	        
	        {	
	        	return n;
	        }      
	        else if (n.getTitle().compareTo(root.getTitle()) < 0)
	        {
	        	root.setLeft(insertNode(root.getLeft(), n, root));
	        }
	        else if (n.getTitle().compareTo(root.getTitle()) > 0)
	        	
	        {
	        	root.setRight(insertNode(root.getRight(), n, root));
	        }
	        
	        else    	
	        {
	        	//System.out.println(root.getAvailable() + n.getAvailable()); debug
	        	root.setAvailable(root.getAvailable() + n.getAvailable());
	            root.setRented(root.getRented() + n.getRented());         
	     }

	 		 
	 return root;
}
// recursive insert node function 	    
public void insertNode(Node n) 
{ 
   root = insertNode(root, n, null);
}

	public boolean search(Node n, String title, Node parent)
		{ //find a node recursively to remove
		    if(n == null)
		        return false;
		    if(n.getTitle().equals(title)){
		            delete(n, parent);
		            return true;
		    }
		    
		    return search(n.getLeft(), title, n) || search(n.getRight(), title, n);
		}

//method to remove node 
	  private Node delete(Node n, Node parent)
	  {
		  if(n == root) 
	        {
	            
	            parent = n;
	            n = getLastOnTheLeft(n);
	            if(n != null)
	            {
	                n.setLeft( parent.getLeft() );
	                n.setRight( parent.getRight() );
	                return n;
	            }
	        }
	        else if(n.getLeft() == null && n.getRight() == null)
	        {
	            if(n == parent.getLeft() )
	            {
	                parent.setLeft(null);
	            }
	            else
	            {
	                parent.setRight(null);
	            }
	        }
	        else if (n.getLeft() != null && n.getRight() != null) //two children
	        {
	            if(n == parent.getLeft() )
	            {
	                parent.setLeft( n.getRight() );
	                parent.getLeft().setLeft( n.getLeft() );
	            }
	            else
	            {
	                parent.setRight( n.getRight() );
	                parent.getRight().setLeft( n.getLeft() );
	            }
	        }
	        else    //one child
	        {
	            if (n.getLeft() == null)
	            {
	                if(n == parent.getLeft() )
	                {
	                    parent.setLeft( n.getLeft() );
	                }
	                else
	                {
	                    parent.setRight( n.getLeft() );
	                }
	            }
	            else
	            {
	                if(n == parent.getLeft() )
	                {
	                    parent.setLeft( n.getRight() );
	                }
	                else
	                {
	                    parent.setRight( n.getRight() );
	                }
	            }
	        }
	        return this.root;
	    }

	  // gets the last node on the left of the root node 
	  private Node getLastOnTheLeft(Node start)
	  {
	        Node candidate = null;
	        Node parent = null;
	        Node node = start;

	        while (node != null)
	        {
	            if ( node.getLeft() != null )
	            {
	                parent = node;
	                candidate = node.getLeft();
	            }

	            node = node.getLeft();
	        }

	        if (parent != null)
	        {
	            parent.setLeft(null);
	        }

	        return candidate;
	        
	}
	
    
// prints header to redbox kiosk file 
	  public void printTitle(PrintWriter out)
	  {
		  out.println("    Title                 Available  rented");
	  }
// prints all data to file   
	  public void printToFile(Node n, PrintWriter out)
    {
		  //recursive print function
		 
	        if(n != null) 
	        {
	            printToFile(n.getLeft(), out);//go down the left
	            printToFile(n.getRight(), out);//go down the right
	            out.printf("%-30s%5d%5d",n.getTitle(), n.getAvailable(), n.getRented());//print it with the correct formatting
	            out.println();
	        }
	    }

}
