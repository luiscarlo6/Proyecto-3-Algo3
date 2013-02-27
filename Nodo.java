/**
 * Clase que almacena la informacion de las aristas en el grafo.
 * 
 * Luiscarlo Rivera, 09-11020
 * Jose Prado, 09-11006
 * 
 * Proyecto 1
 */

public class Nodo implements Comparable<Nodo>{

	//id es unico
	private String id = null;
	private Nodo padre = null;
	private int peso = Integer.MAX_VALUE;
	private int gasolina = 0;
	private int costo = Integer.MAX_VALUE;
	private BinaryHeap<Estado> estados = new BinaryHeap<Estado>();
	private boolean visitado = false;

	/**
	 * Constructor por defecto
	 **/

	public Nodo(){
		this.id = "";
		this.padre =  null;
		this.peso = Integer.MAX_VALUE;
	}


	/**
	 * Crea un nodo con id i.
	 */
	public Nodo (String i) {
		this.id = new String(i);
	}

	/**
	 * Crea un nodo con id i y peso p
	 */
	public Nodo (String i,int p) {
		this.id = new String(i);
		this.peso = p;
	}

	/**
	 * Retorna un nuevo nodo que es copia de this.
	 */
	@Override
	protected Object clone() {
		Nodo sal = new Nodo(new String(this.id),this.peso);
		sal.setCosto(this.costo);
		sal.setGas(this.gasolina);
		sal.setPadre(this.padre);
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

		if (o == null)
			return false;

		if (!(o instanceof Nodo))
			return false;

		n = (Nodo) o;

		if (this.id.equalsIgnoreCase(n.id))
			return true;

		return false;
	}

	public int getCosto() {
		return this.costo;
	}

	public void setCosto(int a) {

		this.costo = a;
	}

	public void setVisitado(boolean v){
		this.visitado = v;
	}
	
	public boolean getVisitado(){
		return this.visitado;
	}
	public int getPeso() {
		return this.peso;
	}

	public void setPeso(int a) {

		this.peso = a;
	}

	public int getGas() {
		return this.gasolina;
	}

	public void setGas(int a) {

		this.gasolina = a;
	}

	public Object getPadre() {
		return this.padre;
	}

	public void setPadre(Nodo n) {

		this.padre = n;
	}
	
	public void agregarEstado(Estado e){
		this.estados.add(e);
	}
	
	public int extraerCostoMin(){
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

		if(this.extraerCostoMin() == n.extraerCostoMin()){
			return 0;
		}else if(this.extraerCostoMin() < n.extraerCostoMin()){
			return -1;
		}
		return 1;
	}
	
	public int numEstados(){
		return this.estados.tam();
	}
	
	public Object[] Estados(){
		return this.estados.toArray();
	}
} /*Fin de nodo*/