
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Stack;

import algortimo_de_busca.BuscaGenerica.No;


public class Grafo {
	
	private int labirintoId;
	private String dificuldade;
	private boolean completo;
	private int passos;
	private double exporacao;
	private  final int OBJETIVO =2;
	
	private List<Vertices> vertices = new ArrayList<>();

	public Grafo(List<Vertices> vertices) { // Adicionando listas em uma matriz
		this.vertices.addAll(vertices);
	}
	
	public void adicionarAresta(Vertices v1, Vertices v2, double peso) {
		
		v1.setListaArestas(new Arestas(v2, peso));
		v2.setListaArestas(new Arestas(v1, peso));
	}
	
  //Encontre os vértices aos quais um vértice em algum índice está conectado
    public ArrayList<Vertices> vizinhosVertice(int index){
    	
    	ArrayList<Arestas> arestas = vertices.get(index).getListaArestas();
    	ArrayList<Vertices> vertices = new ArrayList<>();
    	
        for (Arestas aresta : arestas) {
			vertices.add(aresta.getDestino());
		} 
        return vertices; 
    }
    
    public ArrayList<Vertices> vizinhosVertice(Vertices vertice){
    	
    	ArrayList<Arestas> arestas = vertice.getListaArestas();
    	ArrayList<Vertices> vertices = new ArrayList<>();
    	
        for (Arestas aresta : arestas) {
			vertices.add(aresta.getDestino());
		} 
        return vertices; 
    }
    
    public Vertices astr(Vertices raiz) {
        // Lista de prioridades para não visitados
        ArrayList<Vertices> naoVisitados = new ArrayList(); 
        ArrayList<Vertices> visitados = new ArrayList<>(); 

        // Cálculo da heurística da raiz
        double h = heuristica(raiz); // Aqui o objetivo pode ser nulo
        raiz.setHeuristica(h); 
        raiz.setCusto(0.0);
        double f = raiz.getCusto() + h; // f(n) = g(n) + h(n)

        // Adiciona a raiz na lista de não visitados
        adicionaNosNaoVisitados(naoVisitados, raiz, f);
        
        while (!naoVisitados.isEmpty()) {
            // Remove o vértice com menor custo
            Vertices verticeAtual = naoVisitados.removeFirst();
            //System.out.println("verticeAtual é " + verticeAtual.getVerticeId());

            if (verticeAtual.getTipo() == OBJETIVO) {
                //System.out.println("Objetivo encontrado: " + verticeAtual.getVerticeId());
                return verticeAtual;
            }

            for (Vertices verticeVizinho : vizinhosVertice(verticeAtual)) {
                // Calcula o custo de ir para o vizinho
                double novoCusto = verticeAtual.getCusto();
                
                // Encontrar a aresta correspondente entre os vértices
                for (Arestas aresta : verticeAtual.getListaArestas()) {
                	// Entra e coloca o peso arestas incidente, ou seja que, conecta o verticeAtual ao verticeVizinho
                    if (aresta.getDestino().equals(verticeVizinho)) {
                        novoCusto += aresta.getPeso();
                       System.out.printf("Aresta conecta %d -> %d com peso %f\n",verticeAtual.getVerticeId(), verticeVizinho.getVerticeId(), aresta.getPeso());
                        break;
                    }
                }

                if (!visitados.contains(verticeVizinho)) {
                    // Calcula a heurística do vizinho e o valor total de f(n)(Custo Total)
                    h = heuristica(verticeVizinho);
                    f = novoCusto + h;

                    
                    if (!raiz.equals(verticeVizinho)) {
                    	 verticeVizinho.setAnterior(verticeAtual); // Define o anterior
                    } else {
                    	verticeVizinho.setAnterior(null);
                    }
                    
                    verticeVizinho.setCusto(novoCusto); // Atualiza o custo
                    verticeVizinho.setCustoTotal(f); // Atualiza f(n)

                    // Reposiciona o vizinho na lista de não visitados
                    adicionaNosNaoVisitados(naoVisitados, verticeVizinho, f);
                    visitados.add(verticeVizinho); // Marca como visitado
                }
            }
        }
        return null; // Objetivo não encontrado
    }

    
    public Vertices dfs() {
    	
    	Vertices raiz = null;
    	for (Vertices vertice : vertices) {
			if (vertice.getTipo() == 1) {
				raiz = vertice;
				break;
			} 
		}
    	
		ArrayList<Vertices> visitados = new ArrayList<>();
		Stack<Vertices> naoVistidados = new Stack<>();
		
		naoVistidados.push(raiz);
		visitados.add(raiz);
		// Até a pilha ficar vazia
		
		while(!naoVistidados.isEmpty()) {
			
			Vertices noAtual = naoVistidados.pop();
			
			// Se encontrar o objetivo, terminar
			if (noAtual.getTipo() == 2) {
				return noAtual;
			}
			// verificamos para onde podemos ir a seguir e ainda não exploramos
			for (Vertices vizinho: vizinhosVertice(noAtual)) {
				if (visitados.contains(vizinho)) {
					continue;
				}
				// Marca como visitado
				visitados.add(vizinho);
				vizinho.setAnterior(noAtual);
				naoVistidados.push(vizinho);
			}
			raiz.setAnterior(null);
		}
		return null;
    }
    
	public List<Vertices> caminhoNo(Vertices objetivo){
		
		ArrayList<Vertices> caminho = new ArrayList<>();
		caminho.add(objetivo);
		// De trá para frente
		while (objetivo.getAnterior() != null) {
			objetivo = objetivo.getAnterior();
			caminho.add(0, objetivo);// Adiciona sempre no início
		}
		return caminho;

	}

	private void adicionaNosNaoVisitados(ArrayList<Vertices> naoVisitados, Vertices vertice, double custoTotal) {
		
		    if (naoVisitados.isEmpty()) {
		        naoVisitados.add(vertice);
		        return;
		    }
		    
		    // Quanto menor o custo MAIOR a prioridade
		    for (Vertices verticeNaoVisitado : naoVisitados) {
		        if (custoTotal <= verticeNaoVisitado.getCustoTotal()) {
		            naoVisitados.add(naoVisitados.indexOf(verticeNaoVisitado),vertice);
		            return;
		        }
		    }
		    naoVisitados.addLast(vertice); // Se o custo for o maior, insere no final

	}
	
	public double heuristica(Vertices verticeAtual) {
	    double somaPesos = 0, media;
	    int numArestas = verticeAtual.getListaArestas().size();
	    
	    // Soma os pesos de todas as arestas do vértice atual
	    for (Arestas aresta : verticeAtual.getListaArestas()) {
	        somaPesos += aresta.getPeso();
	    }
	    
	    // Retorna a média dos pesos, ou um valor alto se não houver arestas
	    
	    media =  somaPesos / numArestas;
	    
	    return numArestas == 0 ? Double.MAX_VALUE : media;
	}

	 

	@Override
	public String toString() {
		
		StringBuilder sb = new StringBuilder();
		
		for(int i = 0; i < vertices.size(); i++) {
			
			List<Vertices> vizinhos = vizinhosVertice(i);
					
			
			sb.append(vertices.get(i).getVerticeId());
			sb.append("->");
			
			if (vizinhos.isEmpty()) {
				sb.append("vazio");
			}
			
			for (Iterator iterator = vizinhos.iterator(); iterator.hasNext();) {
				Vertices vizinho = (Vertices) iterator.next();
				sb.append(vizinho.getVerticeId() +" ");
			}
			sb.append(System.lineSeparator()); // pula uma linha

		}
		return sb.toString();
	}
   
}



/*if (naoVisitados.isEmpty() ||
				custoTotal <= naoVisitados.getFirst().getCustoTotal()) {
			naoVisitados.addFirst(raiz);
		} else if (custoTotal >= naoVisitados.getLast().getCustoTotal()) {
			naoVisitados.addLast(raiz);
		} else {
			for (Vertices vertice : naoVisitados) {
				  if (custoTotal <= vertice.getCustoTotal()) {
					  naoVisitados.add(naoVisitados.indexOf(naoVisitados),raiz);
				  }
			}
		}*/

/*
 * 
		// Os que não foram visitados serão armazenados de acordo com sua prioridade(menor f(n))
		ArrayList<Vertices> naoVisitados = new ArrayList<>(); 
		ArrayList<Vertices> visitados = new ArrayList<>(); 
		
		// Devo calcular a heuristica do minha raiz(inicio) e adicionar na lista	
		double h = heuristica(raiz, null);
		raiz.setHeuristica(h); 
		raiz.setCusto(0.0);
		double f = raiz.getCusto() + h;

		// f(n) = g(n) + h(n)
		posicionarNaoVisitados(naoVisitados, raiz, f);
				
		// Enquanto todos não tiverem sidos visitados continue 
		while (!naoVisitados.isEmpty()) {
			
			// Remover o elemento de maior prioridade que é o primeiro elemento
			Vertices verticeAtual = naoVisitados.removeFirst();
			System.out.println("verticeAtual é " + verticeAtual.getVerticeId());
			
			if (verticeAtual.getTipo() == objetivo) {
				System.out.println(" ENTREI, verticeAtual é igual ao objetivo ");
				return verticeAtual;
			}
			
			for (Vertices verticeVizinho: vizinhosVertice(verticeAtual)) {
				// Calcula o custo do  custo do verticeVizinho
				// que é a quantidade de passsos + 1 passos para CHEGAR ATE ELE
				
				// o custo é o peso da aresta  verticeAtual.getCusto() + 1
				double novoCusto = 0.0;
				
				for (Arestas aresta : verticeAtual.getListaArestas()) {
					if (aresta.getDestino().equals(verticeVizinho)) {
						novoCusto += aresta.getPeso();
						System.out.printf("Aresta conecta o vertice %d (atual)ao vertice %d com o peso %f\n",verticeAtual.getVerticeId(),  aresta.getDestino().getVerticeId(), novoCusto);
						break;
					}
				}
				
				if (!visitados.contains(verticeVizinho)) { // vizinho está dentro de visitados
					
					h = heuristica(verticeAtual , verticeVizinho);
					f = novoCusto + h;//f(n) = g(n) + h(n)
					
					verticeVizinho.setAnterior(verticeAtual); // adiciona o paiDoVerticeVizinho
					System.out.printf("Vertice ANTERIOR e %d do vertice atual do %d\n",verticeAtual.getVerticeId(), verticeVizinho.getVerticeId());
					verticeVizinho.setCusto(novoCusto); // Adiciona o custo do verticeVizinho
					System.out.println("CUSTO ATUAAL "+ verticeVizinho.getCusto());
					verticeVizinho.setCustoTotal(f);
					System.out.println("Valor calculado pela função heurística:"+ h);
					System.out.printf("CUSTO TOTAL %f do vértice %d \n",verticeVizinho.getCusto(),verticeVizinho.getVerticeId());
					
					posicionarNaoVisitados(naoVisitados,verticeVizinho,f);
					visitados.add(verticeVizinho); // Marca com visitado
					naoVisitados.add(verticeVizinho);
			
					//calcula a heurística do estado atual para o meu vizinho
				}
			}
		}
		return null;*/

