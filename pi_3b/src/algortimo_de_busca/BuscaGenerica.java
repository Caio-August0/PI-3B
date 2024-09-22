package algortimo_de_busca;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Stack;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.ToDoubleFunction;


public class BuscaGenerica {
	
	public static class No<T> implements Comparable<No<T>> {

		final T estado;
		No<T> pai;
		double custo, heuristica;
		
		public No(T estado) {
			this.estado = estado;
		}
		
		// Para dfs e bfs 
		public No(T estado, No<T> pai) {
			this.estado = estado;
			this.pai = pai;
		}
		// para astr(A*)
		public No(T estado, No<T> pai, double custo, double heuristica) {
			this.estado = estado;
			this.pai = pai;
			this.custo = custo;
			this.heuristica = heuristica;
		}
		@Override
		public int compareTo(No<T> outro ) {
			Double minha = custo + heuristica;
			Double deles = outro.custo + outro.heuristica;
			return minha.compareTo(deles);
		}
		
	}
	
	public static <T>No<T> astr(T inicio, Predicate<T> objetivo, Function<T, 
			List<T>> sucessor,ToDoubleFunction<T> heuristica){
		
		PriorityQueue<No<T>> naoVisitados = new PriorityQueue<>();
		naoVisitados.offer(new No<>(inicio,null, 0.0, heuristica.applyAsDouble(inicio)));
		
		Map<T, Double> visitados = new HashMap<>();
		visitados.put(inicio, 0.0);
		
		// Continue enquanto tiver mais para serem visitados
		while(!naoVisitados.isEmpty()) {
			
			No<T> noAtual = naoVisitados.poll();
			T estadoAtual = noAtual.estado;
			
			// Encontra o objetivo
			if (objetivo.test(estadoAtual)) {
				return noAtual;
			}
			// Pra onde pode ir
			for (T vizinho : sucessor.apply(estadoAtual)) {
				//1 aqui assume uma grade, precisa de uma função de custo para aplicativos mais sofisticados
				double novoCusto = noAtual.custo + 1;
				if (!visitados.containsKey(vizinho)) {
					visitados.put(vizinho, novoCusto); // custo para ir para o vizinho
					naoVisitados.offer(new No<> (vizinho, noAtual, novoCusto, heuristica.applyAsDouble(vizinho)));
					
					// heuristica.applyAsDouble(vizinho) calcula a heurística do estado atual para o meu vizinho
				}
			}
			
		}
		return null;// Se não encontrar o objetivo
	}
	
	
	public static <T> Node<T> bfs(T initial, Predicate<T> goalTest,
			Function<T, List<T>> successors) {
		// frontier is where we've yet to go
		Queue<Node<T>> frontier = new LinkedList<>();
		frontier.offer(new Node<>(initial, null));
		// explored is where we've been
		Set<T> explored = new HashSet<>();
		explored.add(initial);

		// keep going while there is more to explore
		while (!frontier.isEmpty()) {
			Node<T> currentNode = frontier.poll();
			T currentState = currentNode.state;
			// if we found the goal, we're done
			if (goalTest.test(currentState)) {
				return currentNode;
			}
			// check where we can go next and haven't explored
			for (T child : successors.apply(currentState)) {
				if (explored.contains(child)) {
					continue; // skip children we already explored
				}
				explored.add(child);
				frontier.offer(new Node<>(child, currentNode));
			}
		}
		return null; // went through everything and never found goal
	}
	
	public static <T>No<T> dfs(T inicio, Predicate<T> objetivo, Function<T, List<T>> sucessor){
		
		ArrayList<T> visitados = new ArrayList<>();
		Stack<No<T>> naoVistidados = new Stack<>();
		
		naoVistidados.push(new No<T>(inicio));
		visitados.add(inicio);
		// Vai retirando ons não visitados
		while(!naoVistidados.isEmpty()) {
			
			No<T> noAtual = naoVistidados.pop();
			T estadoAtual = noAtual.estado; // passa o inicio
			
			// Se encontrar o objetivo, terminar
			if (objetivo.test(estadoAtual)) {
				return noAtual; // retorna o nosso objetivo/parada
			}
			// verificamos para onde podemos ir a seguir e ainda não exploramos
			for (T vizinho: sucessor.apply(estadoAtual)) {
				if (visitados.contains(vizinho)) {
					continue;
				}
				// Marca como visitado
				visitados.add(vizinho);
				naoVistidados.push(new No<>(vizinho, noAtual));
			}
		}
		return null;
	}
	
	
	
	
	public static <T> List<T> caminhoNo(No<T> no){
		List<T> caminho = new ArrayList<T>();
		caminho.add(no.estado);
		
		while (no.pai != null) {
			no = no.pai;
			caminho.add(0, no.estado);
		}
		return caminho;
	}

	
}
