import java.util.ArrayList;

public class Vertices  {
	
    private int verticeId;
    private int tipo;
    private ArrayList<Arestas> listaArestas = new ArrayList<Arestas>();
    private boolean visitado;
    
    
    private Vertices anterior;
    private double custo;
    private double custoTotal;
    private double heuristica;

    public Vertices(int verticeId, int tipo) {
        this.verticeId = verticeId;
        this.tipo = tipo;
    }
    
    public Vertices(Vertices anterior, double custo, double heuristica) {
        this.anterior = anterior;
        this.custo = custo;
        this.heuristica = heuristica;
    }

    
    public Vertices(int verticeId, Vertices anterior, double custo, double heuristica) {
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

	public int getTipo() {
		return tipo;
	}

	public void setTipo(int tipo) {
		this.tipo = tipo;
	}

	public ArrayList<Arestas> getListaArestas() {
		return listaArestas;
	}

	public void setListaArestas(Arestas ida) {
		this.listaArestas.add(ida);
	}

	public Vertices getAnterior() {
		return anterior;
	}

	public void setAnterior(Vertices anterior) {
		this.anterior = anterior;
	}

	public double getCusto() {
		return custo;
	}

	public void setCusto(double custo) {
		this.custo = custo;
	}

	public double getCustoTotal() {
		return custoTotal;
	}

	public void setCustoTotal(double custoTotal) {
		this.custoTotal = custoTotal;
	}

	public double getHeuristica() {
		return heuristica;
	}

	public void setHeuristica(double heuristica) {
		this.heuristica = heuristica;
	}

	public boolean isVisitado() {
		return visitado;
	}

	public void setVisitado(boolean visitado) {
		this.visitado = visitado;
	}
	
	
	
	
}
