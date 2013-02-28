/**
 * 
 */

/**
 * @author luiscarlo
 *
 */
public class Estado implements Comparable<Estado>{
    private int precio;
    private int costo;
    private int gasolina;

    /**
     * @param p Precio para el Estado
     * @param c Costo para el Estado
     * @param g Gasolina para el Estado
     * 
     */
    public Estado(int p, int c, int g) {
	this.precio = p;
	this.costo = c;
	this.gasolina = g;
    }//Fin Constructor

    /**
     * @return Costo del Estado
     */
    public int getCosto() {
	return this.costo;
    }

    /**
     * @param a Costo para el Estado
     */
    public void setCosto(int a) {

	this.costo = a;
    }

    /**
     * @return Gasolina del Estado
     */
    public int getGas() {
	return this.gasolina;
    }

    /**
     * @param a Gasolina para el Estado
     */
    public void setGas(int a) {

	this.gasolina = a;
    }

    /**
     * @return Precio del Estado
     */
    public int getPrecio() {
	return this.precio;
    }

    /**
     * @param a Precio para Estado
     */
    public void setPrecio(int a) {

	this.precio = a;
    }

    @Override
    public String toString(){
	return this.precio+" "+this.costo+" "+this.gasolina;
    }

    @Override
    public int compareTo(Estado n) {

	if(this.costo == n.costo){
	    return 0;
	}else if(this.costo < n.costo){
	    return -1;
	}
	return 1;
    }
}