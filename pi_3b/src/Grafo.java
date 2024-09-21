
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Stack;
import java.util.stream.Collectors;

import GrafoComPeso.No;

public class Grafo<V extends Vertice, A extends Arestas> {
	
	private List<V> vertices = new ArrayList<>(); 
	protected ArrayList<ArrayList<A>> arestas = new ArrayList<>();

	public Grafo(List<V> vertices) { // Adicionando listas em uma matriz
		this.vertices.addAll(vertices);
		// para cada vértice cria-se uma lista de arestas
		for (V vertice : vertices) {
			arestas.add(new ArrayList<A>());
		}
	}
	// Interliga os vertices 
    public int adicionarVertices(V vertice) {
    	vertices.add(vertice);// adiciona vértice na lista de vértices
    	arestas.add(new ArrayList<A>()); 
    	return getQtdVertices()-1;
    }
  

  //Encontre os vértices aos quais um vértice em algum índice está conectado
    public List<V> vizinhosVertice(int index){
        return arestas.get(index).stream().map(
        		aresta -> buscarVertice(aresta.v)
        		).collect(Collectors.toList());
    }
 // Procura um índice de vértices e encontra seus vizinhos (método de conveniência)
    public List<V> vizinhosVertice(V vertice){
    	return vizinhosVertice(buscarIndexVertice(vertice));
    }
 // Retorna todas as arestas associadas a um vértice em algum índice
    public List<A> buscarArestas(int index) {
    	return arestas.get(index);
    }
 // Procura o índice de um vértice e retorna suas arestas (método de conveniência)
    public List<A> buscarArestas(V vertice) {
    	return buscarArestas(buscarIndexVertice(vertice));
    }
    
    public V buscarVertice(int index) {
    	return vertices.get(index);
    }
    
    public int buscarIndexVertice(V vertice) {
    	return vertices.indexOf(vertice);
    }
	
	public int getQtdVertices() {
		return vertices.size();
	}
	
	public int getQtdArestas() {
		return arestas.stream().mapToInt(ArrayList::size).sum();
	}
	
	 
	

	@Override
	public String toString() {
		
		StringBuilder sb = new StringBuilder();
		
		for(int i = 0; i < getQtdVertices(); i++) {
			
			List<V> vizinhos = vizinhosVertice(i);
					
			
			sb.append(buscarVertice(i).getVerticeId());
			sb.append("->");
			
			if (vizinhos.isEmpty()) {
				sb.append("vazio");
			}
			
			for (Iterator iterator = vizinhos.iterator(); iterator.hasNext();) {
				V vizinho = (V) iterator.next();
				sb.append(vizinho.getVerticeId() +" ");
			}
			sb.append(System.lineSeparator()); // pula uma linha

		}
		return sb.toString();
	}
    
	
   
}
