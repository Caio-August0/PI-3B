
public class Arestas {

	private final double peso;
	//private final Vertices origem;
	private final Vertices destino;
	
	public double getPeso() {
		return peso;
	}


	public Vertices getDestino() {
		return destino;
	}


	public Arestas(Vertices destino, double peso) {
		this.destino = destino;
		this.peso = peso;
	}

	
	public Arestas arestaInvertida() {
		return new Arestas(destino,peso);
	}
	
	
	
	
}
