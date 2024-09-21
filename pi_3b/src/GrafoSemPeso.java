import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Stack;
import java.util.function.Function;
import java.util.function.Predicate;

import BuscaGenerica.No;

public class GrafoSemPeso<V extends Vertice> extends Grafo<Vertice, Arestas>{

	public GrafoSemPeso(List<Vertice> vertices) {
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
	
	
	public Vertice dfs(Vertice raiz, Vertice verticeDeParada){
		
		ArrayList<Vertice> visitados = new ArrayList<>();
		Stack<Vertice> naoVistidados = new Stack<>();
		
		naoVistidados.push(raiz);
		visitados.add(raiz);
		// Até a pilha ficar vazia
		while(!naoVistidados.isEmpty()) {
			
			Vertice noAtual = naoVistidados.pop();
			
			// Se encontrar o objetivo, terminar
			if (verticeDeParada.equals(noAtual)) {
				return noAtual;
			}
			// verificamos para onde podemos ir a seguir e ainda não exploramos
			for (Vertice vizinho: vizinhosVertice(noAtual)) {
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
	
	
	public List<Vertice> caminhoNo(Vertice objetivo){
		List<Vertice> caminho = new ArrayList<>();
		caminho.add(objetivo);
		
		while (objetivo.getAnterior() != null) {
			objetivo = objetivo.getAnterior();
			caminho.add(0, objetivo);
		}
		return caminho;
	}

	public Vertice astr(Vertice raiz, Vertice verticeDeParada) {
		
		ArrayList<Vertice> visitados = new ArrayList<>();
		Stack<Vertice> naoVistidados = new Stack<>();
		
		naoVistidados.push(raiz);
		visitados.add(raiz);
		// Até a pilha ficar vazia
		while(!naoVistidados.isEmpty()) {
			
			Vertice noAtual = naoVistidados.pop();
			
			// Se encontrar o objetivo, terminar
			if (verticeDeParada.equals(noAtual)) {
				return noAtual;
			}
			// verificamos para onde podemos ir a seguir e ainda não exploramos
			for (Vertice vizinho: vizinhosVertice(noAtual)) {
				
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
