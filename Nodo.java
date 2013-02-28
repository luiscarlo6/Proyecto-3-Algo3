/**
 * Clase que almacena la informacion de las aristas en el grafo.
 * 
 * Luiscarlo Rivera, 09-11020
 * Jose Prado, 09-11006
 * 
 * Proyecto 1
 */

public class Nodo implements Comparable<Nodo>{

    private String id = null;
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
	this.peso = Integer.MAX_VALUE;
    }


    /**
     * Crea un nodo con id i.
     * @param i id del nodo
     */
    public Nodo (String i) {
	this.id = new String(i);
    }

    /**
     * Crea un nodo con id i y peso p
     * @param i id del nodo
     * @param p peso del nodo
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

    /**
     * @return Costo del nodo
     */
    public int getCosto() {
	return this.costo;
    }

    /**
     * @param a Costo para el nodo
     */
    public void setCosto(int a) {

	this.costo = a;
    }

    /**
     * @param v estado de visita para el nodo
     */
    public void setVisitado(boolean v){
	this.visitado = v;
    }

    /**
     * @return el estado de visita del nodo
     */
    public boolean getVisitado(){
	return this.visitado;
    }
    /**
     * @return peso del nodo
     */
    public int getPeso() {
	return this.peso;
    }

    /**
     * @param a Peso del Nodo
     */
    public void setPeso(int a) {

	this.peso = a;
    }

    /**
     * @return Gas del Nodo
     */
    public int getGas() {
	return this.gasolina;
    }

    /**
     * @param a Gas del Nodo
     */
    public void setGas(int a) {

	this.gasolina = a;
    }

    /**
     * @param e estado a agregar a la cola
     */
    public void agregarEstado(Estado e){
	this.estados.add(e);
    }

    /**
     * @return costo minimo en la cola
     */
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

    /**
     * @return Numero total de estado
     */
    public int numEstados(){
	return this.estados.tam();
    }

    /**
     * @return Arreglo de estados
     */
    public Object[] Estados(){
	return this.estados.toArray();
    }
} /*Fin de nodo*/