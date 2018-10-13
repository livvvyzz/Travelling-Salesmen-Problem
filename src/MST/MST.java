package MST;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class MST {

	
	private static int dim; //number of nodes
	private static int num_edges;
	private Edge[] edges;
	private static Node[] nodes;
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		
		//load nodes
	    File file = new File("a280.tsp");
	    try {

	        Scanner sc = new Scanner(file);

	        while (sc.hasNextLine()) {
	            String line = sc.nextLine();
	            String[] line_array = line.split("\\s+");
	            //check for dimension
	            int i = 1;
	            if(line_array[0].equals("DIMENSION")) {
	            	dim = Integer.parseInt(line_array[2]);
	            	nodes = new Node[dim+1];
	            	num_edges = (dim*(dim-1))/2;
	            	
	            	for(int c = 1; c < num_edges+1; c++) {
	            		edges[i] = new Edge(c);
	            	}
	            	
	            } else if (line_array[0].matches("-?\\d+")) {
	            	int index = Integer.parseInt(line_array[0]);
	            	int num_one = Integer.parseInt(line_array[1]);
	            	int num_two = Integer.parseInt(line_array[2]);
	            	nodes[i] = new Node(index,num_one, num_two);
	            	i++;
	            }
	        }
	        sc.close();
	    } 
	    catch (FileNotFoundException e) {
	        e.printStackTrace();
	    }
	    System.out.println("Loaded file " + file.getName());

	    findMST();
	    
	    
	}
	
	public static void findMST() {
		
	}

}
