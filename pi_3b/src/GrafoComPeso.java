import java.lang.Runtime.Version;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Stack;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.ToDoubleFunction;

import BuscaGenerica.No;

public class GrafoComPeso<V extends Vertice> extends Grafo<Vertice, Arestas>{

	public GrafoComPeso(List<Vertice> vertices) {
		super(vertices);
	}
	
	private void adicionarAresta(Arestas aresta) {
		//Par de arestas, uma de ida e outra de vinda
		arestas.get(aresta.u).add(aresta);//Adiciona uma aresta de ida com U e V  
		arestas.get(aresta.v).add(aresta.arestaInvertida());//Adiciona uma aresta de volta com U e V  
	}
	
	// Adicione uma aresta procurando índices de vértices (método de conveniência)

	public void adicionarAresta(V v1, V v2, double peso) {

		adicionarAresta( // Cria um objeto aresta de onde ela saí para onde ela vai
			new Arestas(v1, v2, buscarIndexVertice(v1), buscarIndexVertice(v2), peso
		));
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

	 
	 public double heuristica(Vertice atual, Vertice destino) { // distancia heuclidiana
		 
		 List<Arestas> arestasAtual =  buscarArestas(atual);
		 List<Arestas> arestasDestino = buscarArestas(atual); 
		 
		 Arestas a1 = arestasAtual.getFirst();
		 Arestas a2 = arestasDestino.getFirst();
		  
		 double du = a1.u - a2.u;
		 double dv = a1.v - a2.v;
		 
		return Math.sqrt(Math.pow(du, 2) + Math.pow(dv, 2));
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
	
	public static class No<V> implements Comparable<No<V>> {

		final V estado;
		No<V> pai;
		double custo, heuristica;
		
		public No(V estado) {
			this.estado = estado;
		}
		
		// Para dfs e bfs 
		public No(V estado, No<V> pai) {
			this.estado = estado;
			this.pai = pai;
		}
		// para astr(A*)
		public No(V estado, No<V> pai, double custo, double heuristica) {
			this.estado = estado;
			this.pai = pai;
			this.custo = custo;
			this.heuristica = heuristica;
		}
		public int compareTo(No<V> outro ) {
			Double minha = custo + heuristica;
			Double deles = outro.custo + outro.heuristica;
			return minha.compareTo(deles);
		}
		
	}
	
	public void astr(Vertice inicio, Vertice verticeDeParada){
		
		PriorityQueue<No<V>> naoVisitados = new PriorityQueue<>();
		double h = heuristica(inicio, verticeDeParada);
		naoVisitados.offer(new No<>((V)inicio,null, 0.0, h));
		
		Map<V, Double> visitados = new HashMap<>();
		visitados.put((V) inicio, 0.0);
		
		No<V> noAtual = null;
		// Continue enquanto tiver mais para serem visitados
		while(!naoVisitados.isEmpty()) {
			

			noAtual = naoVisitados.poll();
			
			// Encontra o objetivo
			if (verticeDeParada.equals(noAtual.estado)) {
				break;
			}
			
			// Pra onde pode ir
			for (Vertice vizinho: vizinhosVertice(noAtual.estado)) {
				double novoCusto = noAtual.custo + 1;

				if (!visitados.containsKey(vizinho)) {
					
					visitados.put((V)vizinho, novoCusto);
					h = heuristica(inicio, verticeDeParada);
					naoVisitados.offer(new No<>((V)vizinho, noAtual, novoCusto,h));
				}
			}
			noAtual = null;
		}
		if (noAtual != null) {
			List<V> caminho = new ArrayList<>();
			caminho.add(noAtual.estado);
			
			while (noAtual.pai != null) {
				noAtual = noAtual.pai;
				caminho.add(0, noAtual.estado);
			}
			
			System.out.printf("O objetivo foi encontrado!!\n");
			System.out.printf("O caminho de %d para %d é:\n", caminho.getFirst().getVerticeId(), caminho.getLast().getVerticeId());
			
			StringBuilder sb = new StringBuilder();
			for (Vertice c : caminho) {
				sb.append(c.getVerticeId());
				if (!c.equals(caminho.getLast())) {
					sb.append("->");
				}
			}
			System.out.println(sb.toString());
		} else {
			System.out.printf("Não foi possivel encontrar o objetivo!!\n");	
		}
	}
}
