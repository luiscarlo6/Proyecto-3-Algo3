public class DijkstraImpl {
	private int maxGas;

	public void Dijkstra(Graph grafo, Nodo s, int maxGas) {
		this.maxGas = maxGas;
		s = grafo.get(s);
		s.setGas(0);
		s.setCosto(0);
		BinaryHeap<Nodo> cola = new BinaryHeap<Nodo>();
		cola.add(s);

		while (!cola.esVacio()){

			Nodo salida = (Nodo) cola.min();
			salida = grafo.get(salida);
			cola.remove();
			Lista<Nodo> ady = grafo.getSuc(salida);
			ListIterator<Nodo> it = ((MiLista<Nodo>) ady).iterator();

			int j = 0;
			while (j != ady.getSize()) {
				Nodo llegada = it.next();
				llegada = grafo.get(llegada);
				Arco uv = new Arco(salida.toString(), llegada.toString());

				uv = grafo.get(uv);
				int costov = llegada.getCosto();
				int costouv = costo(salida, llegada, uv);
				if (uv.getPeso() <= maxGas && costov > costouv) {
					min(cola, salida, llegada, uv);
				}
				j++;
			}
		}
	}

	public void min(BinaryHeap<Nodo> cola,
			Nodo salida, Nodo llegada, Arco uv) {

		if (llegada.getPeso() <= salida.getPeso()
				&& uv.getPeso() > salida.getGas()) {
			llegada.setCosto(salida.getCosto()
					+ (uv.getPeso() - salida.getGas()) * salida.getPeso());
			llegada.setGas(0);
			llegada.setPadre(salida);
			cola.add(llegada);
			return;

		} else if (llegada.getPeso() > salida.getPeso()) {
			llegada.setCosto(salida.getCosto()
					+ (this.maxGas - salida.getGas()) * salida.getPeso());
			llegada.setGas(this.maxGas - uv.getPeso());
			llegada.setPadre(salida);
			cola.add(llegada);
			return;

		} else if (uv.getPeso() <= salida.getGas()) {
			llegada.setCosto(salida.getCosto());
			llegada.setGas(salida.getGas() - uv.getPeso());
			cola.add(llegada);
			llegada.setPadre((Nodo) salida.getPadre());
			return;
		}
	}

	public int costo(Nodo salida, Nodo llegada, Arco uv) {
		int sal = 0;
		if (llegada.getPeso() <= salida.getPeso()
				&& uv.getPeso() > salida.getGas()) {
			sal = (salida.getCosto() + (uv.getPeso() - salida.getGas())
					* salida.getPeso());

		} else if (llegada.getPeso() > salida.getPeso()) {
			sal = (salida.getCosto() + (this.maxGas - salida.getGas())
					* salida.getPeso());

		} else if (uv.getPeso() <= salida.getGas()) {
			sal = (salida.getCosto());
		}
		return sal;
	}
}
