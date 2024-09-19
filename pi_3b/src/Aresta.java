
public class Aresta {

	public final int u;
	public final int v;
	
	public Aresta(int u, int v) {
		this.u = u;
		this.v = v;
	}
	
	public Aresta aloca_Aresta() {
		return new Aresta(v,u);
	}



	@Override
	public String toString() {
		return  u + "->" + v;
	}
	
}
