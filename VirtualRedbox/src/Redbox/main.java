//Shamsah Hossain
//sxh170009@utdallas.edu
//cs2336 project 3


package Redbox;

import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.regex.*;

public class main 
{
	
	public main()
	{
		
	}
    public static void main(String[] args) throws FileNotFoundException
    {
    	
    	
   // read in from inventory file and from transaction file and update info to insert to redbox kiosk file 
    	
    	
        BST tree = new BST();
        PrintWriter error = new PrintWriter("error.txt");
        try 
        {
        	
            String line, title;
           
            Scanner inventory = new Scanner(new File("inventory.dat"));
            
        // takes in inventory data and inserts into BST     
            while(inventory.hasNext()) 
            {
                line = inventory.nextLine();
                
                Scanner input = new Scanner(line);
                
                input.useDelimiter(",");
                
                title = input.next();
                
                title = title.substring(1,title.length()-1);
                
                tree.insertNode(new Node(title, input.nextInt(), input.nextInt(), null, null));
                
                input.close();
                
           }
            
            inventory.close();

            
        } 
        catch (FileNotFoundException e)
        {
        	System.out.println("File not found" + e);
        }

        Scanner read = new Scanner(new File("transaction.log"));
        PrintWriter fileWriter = new PrintWriter("redbox_kiosk.txt");

  // validates data in transaction data and adds,removes,rents and removes accordingly 
        
        while(read.hasNext())
        {
            String line = read.nextLine();
            
           // System.out.println("here"); debug
              
            switch(line.substring(0, 3))
         {
                case "add":
                	// if input in file matches with pattter.compile, it is not thrown to error file
                        if(Pattern.compile("[A-Za-z]+\\s+\\\"+[A-za-z0-9\\s\\:\\,\\;\\'\\\\\\.\\?\\!\\(\\)\\{\\}\\[\\]]+\\\"+\\,+[0-9]").matcher(line).find())
                        {
                            line = line.substring(5);
                            
                            String title = line.substring(0, line.indexOf('\"'));
                            
                            line = line.substring(line.indexOf("\"")+2);
                            
                            int x ;
                            try
                            {
                            	x = Integer.parseInt(line);
                            	tree.insertNode(new Node(title,x, 0, null,null));

                            }
                            catch(NumberFormatException e)
                            {
                            	 error.print(line);
                                 error.println();
                                 error.flush();
                                 error.close();
                            }
                                                    }
                        else
                        {
                            error.print(line);
                            error.println();
                            error.flush();
                            error.close();
                        }
                        break;
                        
                case "rem":
                        if(Pattern.compile("[A-Za-z]+\\s+\\\"+[A-za-z0-9\\s\\:\\,\\;\\'\\\\\\.\\?\\!\\(\\)\\{\\}\\[\\]]+\\\"+\\,+[0-9]").matcher(line).find())
                        {
                            line = line.substring(8);
                            String title = line.substring(0, line.indexOf('\"'));
                            line = line.substring(line.indexOf("\"")+2);
                            tree.insertNode(new Node(title, Integer.parseInt(line)*-1, 0, null,null));
                        }
                        else
                        {
                            error.print(line);
                            error.println();
                            error.flush();
                            error.close();
                        }
                        break;
                        
                case "ret":
                    if(Pattern.compile("[A-Za-z]+\\s+\\\"+[A-za-z0-9\\s\\:\\,\\;\\'\\\\\\.\\?\\!\\(\\)\\{\\}\\[\\]]+\\\"").matcher(line).find()){
                        line = line.substring(8);
                        tree.insertNode(new Node(line.substring(0, line.indexOf('\"')), +1, -1, null, null));
                    }
                    else
                    {
                        error.print(line);
                        error.println();
                        error.flush();
                        error.close();
                    }
                    break;
                        
                case "ren":
                        if(Pattern.compile("[A-Za-z]+\\s+\\\"+[A-za-z0-9\\s\\:\\,\\;\\'\\\\\\.\\?\\!\\(\\)\\{\\}\\[\\]]+\\\"").matcher(line).find())
                        {
                            line = line.substring(6);
                            tree.insertNode(new Node(line.substring(0, line.indexOf('\"')), -1, +1, null,null));
                        }
                        else
                        {
                            error.print(line);
                            error.println();
                            error.flush();
                            error.close();
                        }
                        break;
                default:
                       // System.out.println("error line");
                        error.print(line);
                        error.println();
                        error.flush();
                        error.close();

            }
                   

        }
     
    // call to function to print to file 
        tree.printTitle(fileWriter);
        
        tree.printToFile(tree.getRoot(), fileWriter);
  // close file writer    
        fileWriter.flush(); 
        fileWriter.close();
      	
        read.close();
        
}
}