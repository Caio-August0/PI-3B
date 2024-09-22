import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Stack;
import java.util.function.Function;
import java.util.function.Predicate;

import algortimo_de_busca.BuscaGenerica.No;

public class GrafoSemPeso<V extends Vertices> extends Grafo<Vertices, Arestas>{

	public GrafoSemPeso(List<Vertices> vertices) {
		super(vertices);
	}
	
	private void adicionarAresta(Arestas aresta) {
		// Par de arestas, uma de ida e outra de vinda
		arestas.get(aresta.u).add(aresta);//Adiciona uma aresta de ida no array de arestas de idas  
		arestas.get(aresta.v).add(aresta.arestaInvertida());//Adiciona uma aresta de volta no array de arestas de vindas  
	}
	
	// Adicione uma aresta procurando índices de vértices (método de conveniência)
	public void adicionarAresta(V v1, V v2) {
		adicionarAresta( // Cria um objeto aresta de onde ela saí para onde ela vai
				new Arestas(v1, v2, buscarIndexVertice(v1), buscarIndexVertice(v2)
		));
	}
	
	
	public Vertices dfs(Vertices raiz, Vertices verticeDeParada){
		
		ArrayList<Vertices> visitados = new ArrayList<>();
		Stack<Vertices> naoVistidados = new Stack<>();
		
		naoVistidados.push(raiz);
		visitados.add(raiz);
		// Até a pilha ficar vazia
		while(!naoVistidados.isEmpty()) {
			
			Vertices noAtual = naoVistidados.pop();
			
			// Se encontrar o objetivo, terminar
			if (verticeDeParada.equals(noAtual)) {
				return noAtual;
			}
			// verificamos para onde podemos ir a seguir e ainda não exploramos
			for (Vertices vizinho: vizinhosVertice(noAtual)) {
				if (visitados.contains(vizinho)) {
					continue;
				}
				// Marca como visitado
				visitados.add(vizinho);
				vizinho.setAnterior(noAtual);
				naoVistidados.push(vizinho);
			}
		}
		return null;
	}
	
	
	public List<Vertices> caminhoNo(Vertices objetivo){
		List<Vertices> caminho = new ArrayList<>();
		caminho.add(objetivo);
		
		while (objetivo.getAnterior() != null) {
			objetivo = objetivo.getAnterior();
			caminho.add(0, objetivo);
		}
		return caminho;
	}

	public Vertices astr(Vertices raiz, Vertices verticeDeParada) {
		
		ArrayList<Vertices> visitados = new ArrayList<>();
		Stack<Vertices> naoVistidados = new Stack<>();
		
		naoVistidados.push(raiz);
		visitados.add(raiz);
		// Até a pilha ficar vazia
		while(!naoVistidados.isEmpty()) {
			
			Vertices noAtual = naoVistidados.pop();
			
			// Se encontrar o objetivo, terminar
			if (verticeDeParada.equals(noAtual)) {
				return noAtual;
			}
			// verificamos para onde podemos ir a seguir e ainda não exploramos
			for (Vertices vizinho: vizinhosVertice(noAtual)) {
				
				if (visitados.contains(vizinho)) {
					continue;
				}
				// Marca como visitado
				visitados.add(vizinho);
				vizinho.setAnterior(noAtual);
				naoVistidados.push(vizinho);
			}
		}
		return null;
	}
	


}
