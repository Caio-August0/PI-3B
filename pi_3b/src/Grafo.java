
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public abstract class Grafo<V extends Vertice, A extends Aresta> {
	
	private List<V> vertices = new ArrayList<>(); 
	protected List<ArrayList<A>> arestas = new ArrayList<>();

	public Grafo(List<V> vertices) { // Adicionando listas em uma matriz
		this.vertices.addAll(vertices);
		// para cada vertice cria uma lista de adjacência
		for (V vertice : vertices) {
			arestas.add(new ArrayList<A>());
		}
	}

    public int adicionarVertices(V vertice) {
    	vertices.add(vertice);
    	arestas.add(new ArrayList<A>());
    	return getQtdVertices()-1;
    }
    
    public V buscarVertice(int index) {
    	return vertices.get(index);
    }
    
    public int buscarIndexVertice(V vertice) {
    	return vertices.indexOf(vertice);
    }
 // Find the vertices that a vertex at some index is connected to
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
			sb.append(buscarVertice(i));
			sb.append("->");
			sb.append(Arrays.toString(vizinhosVertice(i).toArray()));
			sb.append(System.lineSeparator());
		}
		return sb.toString();
	}
    
	
   
}
