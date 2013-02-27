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
	 * 
	 */
	public Estado(int p, int c, int g) {
		this.precio = p;
		this.costo = c;
		this.gasolina = g;
	}//Fin Constructor
	
	public int getCosto() {
		return this.costo;
	}

	public void setCosto(int a) {

		this.costo = a;
	}
	
	public int getGas() {
		return this.gasolina;
	}

	public void setGas(int a) {

		this.gasolina = a;
	}
	
	public int getPrecio() {
		return this.precio;
	}

	public void setPrecio(int a) {

		this.precio = a;
	}
	
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
	}//Fin CompareTo
}//Fin clase Estado