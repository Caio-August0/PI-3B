
public class Vertice {
	
    private int verticeId;
    private Vertice anterior;
    private double custo;
    private double heuristica;

    public Vertice(int verticeId) {
        this.verticeId = verticeId;
    }
    
    public Vertice(Vertice anterior, double custo, double heuristica) {
        this.anterior = anterior;
        this.custo = custo;
        this.heuristica = heuristica;
    }

    
    public Vertice(int verticeId, Vertice anterior, double custo, double heuristica) {
        this.verticeId = verticeId;
        this.anterior = anterior;
        this.custo = custo;
        this.heuristica = heuristica;
    }


    public int getVerticeId() {
        return verticeId;
    }

    public void setVerticeId(int verticeId) {
        this.verticeId = verticeId;
    }

	public Vertice getAnterior() {
		return anterior;
	}

	public void setAnterior(Vertice anterior) {
		this.anterior = anterior;
	}

	public double getCusto() {
		return custo;
	}

	public void setCusto(double custo) {
		this.custo = custo;
	}

	public double getHeuristica() {
		return heuristica;
	}

	public void setHeuristica(double heuristica) {
		this.heuristica = heuristica;
	}
	
}
