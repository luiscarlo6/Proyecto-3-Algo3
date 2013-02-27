public class DijkstraImpl {
	private int maxGas;

	public void Dijkstra(Graph grafo, Nodo s, int maxGas) {
		this.maxGas = maxGas;
		s = grafo.get(s);
		Estado inicial = new Estado(0,0,0);
		s.agregarEstado(inicial);
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
				Arco SaliLleg = new Arco(salida.toString(), llegada.toString());

				SaliLleg = grafo.get(SaliLleg);
				if (SaliLleg.getPeso() <= maxGas) {
					min(cola, salida, llegada, SaliLleg);
				}
				j++;
			}
		}
	}

	public void min(BinaryHeap<Nodo> cola,Nodo salida, Nodo llegada, Arco SaliLleg) {

		int tam = salida.numEstados();
		Object estados[] = salida.Estados();

		int i = 0;
		int gas = 0;
		int costo = Integer.MAX_VALUE;
		int precio = 0;
		while (i!=tam){
			int gasN = 0;
			int costoN = Integer.MAX_VALUE;
			int precioN = 0;
			if (llegada.getPeso() <= salida.getPeso() && SaliLleg.getPeso() > ((Estado)estados[i]).getGas()) {
				gasN = 0;
				costoN = ((Estado)estados[i]).getCosto() + (SaliLleg.getPeso() - ((Estado)estados[i]).getGas()) * salida.getPeso();
				precioN = salida.getPeso();

			} if (llegada.getPeso() > salida.getPeso()) {
				gasN = (this.maxGas-SaliLleg.getPeso());
				costoN = ((Estado)estados[i]).getCosto() + (this.maxGas - ((Estado)estados[i]).getGas()) * salida.getPeso();
				precioN = salida.getPeso();

			} if (SaliLleg.getPeso() <= ((Estado)estados[i]).getGas()) {
				gasN = ((Estado)estados[i]).getGas()- SaliLleg.getPeso();
				costoN = ((Estado)estados[i]).getCosto();
				precioN = ((Estado)estados[i]).getPrecio();
			}

			if (costo > costoN){
				costo = costoN;
				gas = gasN;
				precio = precioN;

			}
			i++;
		}
		
		Estado mejorEstado = new Estado(precio,costo,gas);
		llegada.agregarEstado(mejorEstado);
		cola.add(llegada);
	}

	public int costo(Nodo salida, Nodo llegada, Arco SaliLleg) {
		int sal = 0;
		if (llegada.getPeso() <= salida.getPeso()
				&& SaliLleg.getPeso() > salida.getGas()) {
			sal = ((SaliLleg.getPeso() - salida.getGas())
					* salida.getPeso());

		} else if (llegada.getPeso() > salida.getPeso()) {
			sal = ((this.maxGas - salida.getGas())
					* salida.getPeso());

		} else if (SaliLleg.getPeso() <= salida.getGas()) {
			sal = (0);
		}
		return sal;
	}

}
