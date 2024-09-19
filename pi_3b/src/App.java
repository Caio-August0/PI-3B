import java.util.List;

public class App {

	public static void main(String[] args) {
		
		Vertice v1 = new Vertice(1);
		Vertice v2 = new Vertice(2);
		Vertice v3 = new Vertice(3);
		Vertice v4 = new Vertice(4); 
		Vertice v5 = new Vertice(5); 
		Vertice v6 = new Vertice(6);
		
		
		GrafoSemPeso<Vertice> grafo = new GrafoSemPeso<>(
				List.of(v1, v2, v3, v4,v5,v6) );

		grafo.adicionarAresta(v1, v2);
		grafo.adicionarAresta(v2, v3);
		
		
		/*System.out.println(grafo.toString());

		Node<String> bfsResult = GenericSearch.bfs("Boston",
				v -> v.equals("Miami"),
				cityGraph::neighborsOf);
		if (bfsResult == null) {
			System.out.println("No solution found using breadth-first search!");
		} else {
			List<String> path = GenericSearch.nodeToPath(bfsResult);
			System.out.println("Path from Boston to Miami:");
			System.out.println(path);
		}*/
	}
}
