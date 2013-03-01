/**
 * Clase que almacena la informacion de las aristas en el grafo.
 *
 * Luiscarlo Rivera, 09-11020
 * Jose Prado, 09-11006
 *
 * Proyecto 3
 * Prof Lab: Juan Arocha
 */
public class Nodo implements Comparable<Nodo> {
  
  private String id = null;
  private int peso = Integer.MAX_VALUE;
  private int gasolina = 0;
  private int costo = Integer.MAX_VALUE;
  private BinaryHeap<Estado> estados = new BinaryHeap<Estado>();
  private boolean visitado = false;
  
  /**
	* Constructor por defecto
	**/
  public Nodo() {
	 this.id = "";
	 this.peso = Integer.MAX_VALUE;
  }
  
  /**
	* Crea un nodo con id i.
	* @param i id del nodo
	*/
  public Nodo(String i) {
	 this.id = new String(i);
  }
  
  /**
	* Crea un nodo con id i y peso p
	* @param i id del nodo
	* @param p peso del nodo
	*/
  public Nodo(String i, int p) {
	 this.id = new String(i);
	 this.peso = p;
  }
  
  /**
	* Retorna un nuevo nodo que es copia de this.
	*/
  @Override
  protected Object clone() {
	 Nodo sal = new Nodo(new String(this.id), this.peso);
	 sal.setCosto(this.costo);
	 sal.setGas(this.gasolina);
	 return sal;
  }
  
  /**
	* Retorna la representacion en String del nodo.
	*/
  @Override
  public String toString() {
	 
	 return new String(new String(this.id));
  }
  
  /**
	* Indica si el nodo de entrada es igual a this.
	*/
  @Override
  public boolean equals(Object o) {
	 Nodo n;
	 
	 if (o == null) {
		return false;
	 }
	 
	 if (!(o instanceof Nodo)) {
		return false;
	 }
	 
	 n = (Nodo) o;
	 
	 if (this.id.equalsIgnoreCase(n.id)) {
		return true;
	 }
	 
	 return false;
  }
  
  /**
	* devuelve el costo del nodo
	* @return Costo del nodo
	*/
  public int getCosto() {
	 return this.costo;
  }
  
  /**
	* reemplaza el costo del nodo
	* @param a Costo para el nodo
	*/
  public void setCosto(int a) {
	 
	 this.costo = a;
  }
  
  /**
	* reemplaza la condicion de visitado
	* @param v estado de visita para el nodo
	*/
  public void setVisitado(boolean v) {
	 this.visitado = v;
  }
  
  /**
	* retorna el status de visitado
	* @return el estado de visita del nodo
	*/
  public boolean getVisitado() {
	 return this.visitado;
  }
  
  /**
	* retorna el peso del nodo
	* @return peso del nodo
	*/
  public int getPeso() {
	 return this.peso;
  }
  
  /**
	* reemplaza el peso del nodo
	* @param a Peso del Nodo
	*/
  public void setPeso(int a) {
	 
	 this.peso = a;
  }
  
  /**
	* retorna la gasolina del nodo
	* @return Gas del Nodo
	*/
  public int getGas() {
	 return this.gasolina;
  }
  
  /**
	* reemplaza la gasolina del nodo
	* @param a Gas del Nodo
	*/
  public void setGas(int a) {
	 
	 this.gasolina = a;
  }
  
  /**
	* Agrega un estado del nodo
	* @param e estado a agregar a la cola
	*/
  public void agregarEstado(Estado e) {
	 this.estados.add(e);
  }
  
  /**
	* extrae el estado con menor costo
	* @return costo minimo en la cola
	*/
  public int extraerCostoMin() {
	 return ((Estado) this.estados.min()).getCosto();
  }
  
  /**
	* Retorna el codigo hash para un nodo.
	*/
  @Override
  public int hashCode() {
	 return this.id.hashCode();
  }
  
  @Override
  public int compareTo(Nodo n) {
	 
	 if (this.extraerCostoMin() == n.extraerCostoMin()) {
		return 0;
	 } else if (this.extraerCostoMin() < n.extraerCostoMin()) {
		return -1;
	 }
	 return 1;
  }
  
  /**
	* retorna el numero de estados del nodo
	* @return Numero total de estado
	*/
  public int numEstados() {
	 return this.estados.tam();
  }
  
  /**
	* Retorna un areglo de los estados
	* @return Arreglo de estados
	*/
  public Object[] Estados() {
	 return this.estados.toArray();
  }
} /*Fin de nodo*/