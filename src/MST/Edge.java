package MST;

public class Edge {

	private Node a;
	private Node b;
	private int dist;
	
	public Edge(Node a, Node b) {
		this.a = a;
		this.b = b;
		this.dist = getDistance(a, b);
	}
	
	
	public int getDistance(Node a, Node b) {
		
		int x_1 = a.getX();
		int y_1 = a.getY();
		int x_2 = b.getX();
		int y_2 = b.getY();
		
		int d = (int) Math.sqrt(Math.pow((x_1 - x_2),2) + Math.pow((y_1 - y_2),2));
		
		return d; 
		
	}
}
