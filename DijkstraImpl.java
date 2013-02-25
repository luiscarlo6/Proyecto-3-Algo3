import java.util.PriorityQueue;/**
 * 
 */

/**
 * @author Luiscarlo
 *
 */
public class DijkstraImpl {
	private int maxGas;
	private Nodo last;

	public void Dijkstra(Graph grafo, Nodo s, int maxGas){
		this.maxGas = maxGas;
		s = grafo.get(s);
		s.setGas(0);
		s.setCosto(0);
//		BinaryHeap<Nodo> cola = new BinaryHeap<Nodo>();
		PriorityQueue<Nodo> cola = new PriorityQueue<Nodo>(); 
		cola.add(s);

//		while (!cola.esVacio()){
		while (!cola.isEmpty()){
//			Nodo salida = (Nodo) cola.min();
			Nodo salida = (Nodo) cola.peek();
			salida = grafo.get(salida);
//			cola.remove();
			cola.poll();
//			System.out.println(salida);
			Lista<Nodo> ady =  grafo.getSuc(salida);
			ListIterator<Nodo> it = ((MiLista<Nodo>)ady).iterator();
			
			int j = 0;
			while (j!=ady.getSize()){
				Nodo llegada = it.next();
				llegada = grafo.get(llegada);
				Arco uv = new Arco(salida.toString(),llegada.toString());

				uv = grafo.get(uv);
				int costov = llegada.getCosto();
				int costouv = costo(salida,llegada,uv);
				if (uv.getPeso()<=maxGas && costov > costouv){
					min(cola,salida,llegada,uv);
				}
				
				j++;
			}
		}
	}


	public void min(/*BinaryHeap<Nodo>*/PriorityQueue<Nodo> cola, Nodo salida, Nodo llegada, Arco uv){

		if (llegada.getPeso()<=salida.getPeso() && uv.getPeso() > salida.getGas()){
			llegada.setCosto(salida.getCosto()+(uv.getPeso()-salida.getGas())*salida.getPeso());
			llegada.setGas(0);
//			this.last = salida;
			llegada.setPadre(salida);
			cola.add(llegada);
			return;
		}
		if (llegada.getPeso()>salida.getPeso()){
			llegada.setCosto(salida.getCosto()+(this.maxGas-salida.getGas())*salida.getPeso());
			llegada.setGas(this.maxGas-uv.getPeso());
//			this.last = salida;
			llegada.setPadre(salida);
			cola.add(llegada);
			return;
		}
		
		if (uv.getPeso() <= salida.getGas()){
			llegada.setCosto(salida.getCosto());
			llegada.setGas(salida.getGas()-uv.getPeso());
			cola.add(llegada);
			llegada.setPadre((Nodo)salida.getPadre());
			return;
		}
	}

	public int costo(Nodo salida, Nodo llegada, Arco uv){
		int sal = 0;
		if (llegada.getPeso()<=salida.getPeso() && uv.getPeso() > salida.getGas()){
			sal = (salida.getCosto()+(uv.getPeso()-salida.getGas())*salida.getPeso());
		}
		if (llegada.getPeso()>salida.getPeso()){
			sal = (salida.getCosto()+(this.maxGas-salida.getGas())*salida.getPeso());
		}
		
		if (uv.getPeso() <= salida.getGas()){
			sal = (salida.getCosto());

		}
		return sal;
	}
	
	public Nodo getLast(){
		return this.last;
	}
}
