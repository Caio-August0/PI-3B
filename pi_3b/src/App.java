import java.util.List;

import algortimo_de_busca.BuscaGenerica.No;

public class App {

	public static void main(String[] args) {
		
		Vertices v1 = new Vertices(1,1);
		Vertices v2 = new Vertices(2,0);
		Vertices v3 = new Vertices(3,0);
		Vertices v4 = new Vertices(4,0); 
		Vertices v5 = new Vertices(5,0); 
		Vertices v6 = new Vertices(6,0);
		Vertices v7 = new Vertices(7,0);
		Vertices v8 = new Vertices(8,0);
		Vertices v9 = new Vertices(9,0);
		Vertices v10 = new Vertices(10,0);
		Vertices v11 = new Vertices(11,0);
		Vertices v12 = new Vertices(12,0);
		Vertices v13 = new Vertices(13,0);
		Vertices v14 = new Vertices(14,0);
		Vertices v15 = new Vertices(15,0);
		Vertices v16 = new Vertices(16,0);
		Vertices v17 = new Vertices(17,0);
		Vertices v18 = new Vertices(18,0);
		Vertices v19 = new Vertices(19,0);
		Vertices v20 = new Vertices(20,2);
		
		
		Grafo grafo = new Grafo(
				List.of(v1, v2, v3, v4,v5,v6,v7,v8,v9,v10,v11,v12,v13,v14,v15,v16,v17,v18,v19,v20));
		//Cria as arestas incidentes 
		grafo.adicionarAresta(v1, v2,16);
		grafo.adicionarAresta(v1, v6,1);
		grafo.adicionarAresta(v1, v20,1);
		
		grafo.adicionarAresta(v2, v8,8);
		grafo.adicionarAresta(v2, v15,7);
		grafo.adicionarAresta(v2, v3,14);
		
		grafo.adicionarAresta(v3, v4,12);
		grafo.adicionarAresta(v3, v16,11);
		grafo.adicionarAresta(v3, v13,13);
		
		grafo.adicionarAresta(v4, v5,12);
		grafo.adicionarAresta(v5, v6,14);
		grafo.adicionarAresta(v5, v14,9);
		grafo.adicionarAresta(v5, v17,10);
		
		grafo.adicionarAresta(v6,v11,15);
		
		grafo.adicionarAresta(v8,v7,15);
		grafo.adicionarAresta(v8,v9,15);
		grafo.adicionarAresta(v8,v12,11);
		
		
		grafo.adicionarAresta(v9,v12,17);
		grafo.adicionarAresta(v9,v10,13);
		grafo.adicionarAresta(v9,v13,7);
		
		grafo.adicionarAresta(v10,v13,11);
		grafo.adicionarAresta(v10,v11,16);
		
		grafo.adicionarAresta(v13,v14,9);
		grafo.adicionarAresta(v15,v16,7);
		grafo.adicionarAresta(v15,v16,7);
		
		grafo.adicionarAresta(v16,v17,12);
		grafo.adicionarAresta(v17,v18,9);
		grafo.adicionarAresta(v16,v19,13);
		grafo.adicionarAresta(v19,v20,12);


		System.out.println(grafo.toString());
	
		Vertices dfsResultado = grafo.astr(v1);
		
		List<Vertices> vertices = grafo.caminhoNo(dfsResultado);
		//System.out.println(dfsResultado.getAnterior().getAnterior().getAnterior().getAnterior().getAnterior().getAnterior().getVerticeId());
		
		for (Vertices vertice : vertices) {
			System.out.print(vertice.getVerticeId() +" ");
		}

	
	}
}
