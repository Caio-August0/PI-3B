import java.util.List;

public class GrafoSemPeso<V extends Vertice> extends Grafo<Vertice, Aresta>{

	public GrafoSemPeso(List<Vertice> vertices) {
		super(vertices);
	}
	
	public void adicionarAresta(Aresta aresta) {
		arestas.get(aresta.u).add(aresta);
		arestas.get(aresta.v).add(aresta.aloca_Aresta());
	}
	// Adiciona usando os vertices do index
	public void adicionarAresta(int u, int v) {
		adicionarAresta(u, v);
	}
	// Adiciona 
	public void adicionarAresta(V v1, V v2) {
		adicionarAresta( 
				new Aresta( buscarIndexVertice(v1), buscarIndexVertice(v2)
		));
	}

}
