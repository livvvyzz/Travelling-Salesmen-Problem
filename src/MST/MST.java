package MST;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class MST {

	private static int dim; // number of nodes
	private static int num_edges;
	private Edge[] edges;
	private static Node[] nodes;
	private static double[][] distanceMap;

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		// load nodes
		File file = new File("a280.tsp");
		try {

			Scanner sc = new Scanner(file);
			int i = 1;

			while (sc.hasNextLine()) {
				String line = sc.nextLine();
				String[] line_array = line.split("\\s+");

				if(line_array.length == 4){
					line_array[0] = line_array[1];
					line_array[1] = line_array[2];
					line_array[2] = line_array[3];
				}
				// check for dimension
				if (line_array[0].equals("DIMENSION:")) {
					dim = Integer.parseInt(line_array[1]);
					nodes = new Node[dim + 1];
					num_edges = (dim * (dim - 1)) / 2;
					// add nodes to array
				} else if (isInteger(line_array[0])) {
					int index = Integer.parseInt(line_array[0]);
					int num_one = Integer.parseInt(line_array[1]);
					int num_two = Integer.parseInt(line_array[2]);
					nodes[i] = new Node(index, num_one, num_two);
					i++;
				}
			}
			sc.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		System.out.println("Loaded file " + file.getName() + " with " + nodes.length + " nodes");

		// initialise distance map
		distanceMap = new double[dim + 1][dim + 1];
		for (int i = 1; i < dim + 1; i++) {
			for (int j = 1; j < i; j++) {
				distanceMap[i][j] = distance(nodes[i], nodes[j]);
				distanceMap[j][i] = distanceMap[i][j];
			}
		}

		for (int i = 1; i < dim + 1; i++) {
			distanceMap[i][i] = 0;
		}

		findMST();

	}

	public static boolean isInteger(String str) {
		try {
			str.replaceAll(" ","");
			int num = Integer.parseInt(str);
			return true;
			// is an integer!
		} catch (NumberFormatException e) {
			return false;
			// not an integer!
		}
	}

	private static double distance(Node n1, Node n2) {
		return Math.sqrt(Math.pow((n1.getX() - n2.getX()), 2) + Math.pow((n1.getY() - n2.getY()), 2));
	}

	public static void findMST() {

		// adjacent matrix to implement the fully connected graph
		int size = dim + 1;
		// initialize
		final double[] dist = new double[size];
		final int[] pred = new int[size];
		final boolean[] visited = new boolean[size];
		// set distance to infinity
		for (int i = 1; i < dist.length; i++) {
			dist[i] = Double.MAX_VALUE;
		}
		// starting from the first node, can be changed.
		dist[1] = 0;

		for (int i = 1; i < dist.length; i++) {
			final int next = minDist(dist, visited);
			visited[next] = true;
			// find all unvisited neighbors of the current node
			for (int j = 1; j < size; j++) {
				if (j != next && !visited[j]) {
					double d = distanceMap[next][j];
					// update distance and pred array
					if (dist[j] > d) {
						dist[j] = d;
						pred[j] = next;
					}
				}
			}
		}

		// calculate the sum of edge values of the minimum spanning tree
		double score = 0.0;
		for (int i = 0; i < pred.length; i++) {
			score = distanceMap[i][pred[i]] + score;
		}

		for (int i : pred) {
			System.out.println(i);
		}
		System.out.println("mst length " + pred.length);

	}

	// Traverse the distance array to obtain the nearest node to the explored set.
	// Can be optimized, e.g. use heap.
	private static int minDist(double[] dist, boolean[] v) {
		double x = Double.MAX_VALUE;
		int y = -1;
		for (int i = 1; i < dist.length; i++) {
			if(dist[i] < x) {

				if(!v[i]) {
					y = i;
					x = dist[i];
				}
			}
		}
		return y;
	}
}
