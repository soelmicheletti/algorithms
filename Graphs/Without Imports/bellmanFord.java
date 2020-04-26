// AUTHOR: Soel Micheletti
import java.util.Arrays;

class Node{
	int dest; 
	int weight; 
	Node next; 
	
	public Node(int dest, int weight) {
		this.dest = dest; 
		this.weight = weight; 
	}
}

class List{
	Node first; 
	Node last; 
	int size; 
	
	public boolean isEmpty() {
		return size == 0; 
	}
	
	public void add(int dest, int weight) {
		Node n = new Node(dest, weight); 
		if(isEmpty()) {
			first = last = n; 
			size = 1; 
		}
		else {
			last.next = n; 
			size++; 
		}
	}
}

class Graph{
	int V; 
	List[] L; 
	
	public Graph(int v) {
		V = v; 
		L = new List[V]; 
		for(int i = 0; i<v; i++)
			L[i] = new List(); 
	}
	
	public void edge(int src, int dest, int weight) {
		L[src].add(dest, weight);
	}
}

public class BellmanFord{
	public static void bellmanFord(Graph g, int s) {
		int[] d = new int[g.V]; 
		int[] p = new int[g.V]; 
		
		for(int i = 0; i<g.V; i++)
			d[i] = p[i] = 1030; 
		
		d[s] = 0; 
		p[s] = -1; 
		
		for(int i = 0; i<g.V-1; i++) {
			for(int j = 0; j<g.L.length; j++) {
				Oggetto a = g.L[j].first; 
				while(a != null) {
					if(d[j] + a.weight <d[a.dest]) {
						d[a.dest] = d[j] + a.weight; 
						p[a.dest] = j; 
					}
					a = a.next; 
				}
			}
		}
		
		for(int j = 0; j<g.L.length; j++) {
			Oggetto a = g.L[j].first; 
			while(a != null) {
				if(d[j] + a.weight <d[a.dest]) {
					System.out.println("Negative cycle!"); 
				}
				a = a.next; 
			}
		}
		System.out.println(Arrays.toString(d)); 
		System.out.println(Arrays.toString(p)); 
	}
	
	public static void main(String[]args) {
		Graph g = new Graph(6); 
		g.edge(0, 1, 10);
		g.edge(0, 5, 8);
		g.edge(1, 3, 2);
		g.edge(2, 1, 1);
		g.edge(3, 2, -2);
		g.edge(4, 1, -4);
		g.edge(4, 3, -1);
		g.edge(5, 4, 1);
		
		bellmanFord(g, 0); 
	}
}
