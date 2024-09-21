
public class Arestas {

	public final int u;
	public final int v;
	public final double peso;
	public final Vertice origem;
	public final Vertice destino;
		
	public Arestas(Vertice origem, Vertice destino, int u, int v, double peso) {
		this.origem = origem;
		this.destino = destino;
		this.u = u; // de 
		this.v = v; // para
		this.peso = peso;
	}
	
	public Arestas arestaInvertida() {
		return new Arestas(destino, origem,v,u,peso);
	}

	@Override
	public String toString() {
		return  u + "->" + v;
	}	
	
}
