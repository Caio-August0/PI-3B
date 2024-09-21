import java.util.List;

import BuscaGenerica.No;

public class App {

	public static void main(String[] args) {
		
		Vertice v1 = new Vertice(1);
		Vertice v2 = new Vertice(2);
		Vertice v3 = new Vertice(3);
		Vertice v4 = new Vertice(4); 
		Vertice v5 = new Vertice(5); 
		Vertice v6 = new Vertice(6);
		Vertice v7 = new Vertice(7);
		
		GrafoComPeso<Vertice> grafo = new GrafoComPeso<Vertice>(
				List.of(v1, v2, v3, v4,v5,v6,v7));
		//Cria as arestas incidentes 
		grafo.adicionarAresta(v1, v2, 0);;
		grafo.adicionarAresta(v2, v3,2);
		grafo.adicionarAresta(v2, v4,1);
		grafo.adicionarAresta(v3, v5,4);
		grafo.adicionarAresta(v6, v7,5);

		//System.out.println(grafo.toString());
	
		//grafo.dfs(v6, v7);
		 grafo.astr(v6,v7);

	
	}
}
