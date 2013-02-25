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
		PriorityQueue<Nodo> cola = new PriorityQueue(); 
		cola.add(s);

//		while (!cola.esVacio()){
		while (!cola.isEmpty()){
//			Nodo u = (Nodo) cola.min();
			Nodo u = (Nodo) cola.peek();
			u = grafo.get(u);
//			cola.remove();
			cola.poll();
//			System.out.println(u);
			Lista<Nodo> ady =  grafo.getSuc(u);
			ListIterator<Nodo> it = ((MiLista<Nodo>)ady).iterator();
			
			int j = 0;
			while (j!=ady.getSize()){
				Nodo v = it.next();
				v = grafo.get(v);
				Arco uv = new Arco(u.toString(),v.toString());

				uv = grafo.get(uv);
				int costov = v.getCosto();
				int costouv = costo(u,v,uv);
				if (uv.getPeso()<=maxGas){
					min(cola,u,v,uv);
				}
				j++;
			}
		}
	}


	public void min(/*BinaryHeap<Nodo>*/PriorityQueue<Nodo> cola, Nodo u, Nodo v, Arco uv){
		
		if (v.getPeso()<=u.getPeso() && uv.getPeso() > u.getGas()){
			v.setCosto(u.getCosto()+(uv.getPeso()-u.getGas())*u.getPeso());
			v.setGas(0);
			this.last = u;
			cola.add(v);
			return;
		}
		if (v.getPeso()>u.getPeso()){
			v.setCosto(u.getCosto()+(this.maxGas-u.getGas())*u.getPeso());
			v.setGas(this.maxGas-uv.getPeso());
			this.last = u;
			cola.add(v);
			return;
		}
		
		if (uv.getPeso() <= u.getGas()){
			v.setCosto(u.getCosto());
			v.setGas(u.getGas()-uv.getPeso());
			cola.add(v);
			return;
		}
	}

	public int costo(Nodo u, Nodo v, Arco uv){
		int sal = 0;
		if (v.getPeso()<=u.getPeso() && uv.getPeso() > u.getGas()){
			sal = (u.getCosto()+(uv.getPeso()-u.getGas())*u.getPeso());
		}
		if (v.getPeso()>u.getPeso()){
			sal = (u.getCosto()+(this.maxGas-u.getGas())*u.getPeso());
		}
		
		if (uv.getPeso() <= u.getGas()){
			sal = (0);

		}
		return sal;
	}
	
	public Nodo getLast(){
		return this.last;
	}
}
