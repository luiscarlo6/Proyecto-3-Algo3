/**
 * Clase que almacena los estados del recorrido
 * Luiscarlo Rivera, 09-11020
 * Jose Prado, 09-11006
 *
 * Proyecto 3
 * Prof Lab Juan Arocha
 **/
public class Estado implements Comparable<Estado> {
  
  private int precio;
  private int costo;
  private int gasolina;
  
  /**
	* Costructor de un estado
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
	* Obtiene el costo del estado
	* @return Costo del Estado
	*/
  public int getCosto() {
	 return this.costo;
  }
  
  
  /**
	* Reemplaza el costo del estado 
	* @param a Costo para el Estado
	*/
  public void setCosto(int a) {
	 
	 this.costo = a;
  }
  
  
  /**
	* Retorna la gasolina del estado
	* @return Gasolina del Estado
	*/
  public int getGas() {
	 return this.gasolina;
  }
  
  
  /**
	* Reemplaza la gasolina del estado
	* @param a Gasolina para el Estado
	*/
  public void setGas(int a) {
	 
	 this.gasolina = a;
  }
  
  
  /**
	*  Retorna el precio del estado
	* @return Precio del Estado
	*/
  public int getPrecio() {
	 return this.precio;
  }
  
  
  /**
	* Reemplaza la precio del estado
	* @param a Precio para Estado
	*/
  public void setPrecio(int a) {
	 this.precio = a;
  }
  
  
  /**
	* Retorna la representacion en String del estado
	*/
  @Override
  public String toString() {
	 return this.precio + " " + this.costo + " " + this.gasolina;
  }
  
  
  /**
	* Compara dos estados en funcion de su costo
	*/
  @Override
  public int compareTo(Estado n) {
	 
	 if (this.costo == n.costo) {
		return 0;
	 } else if (this.costo < n.costo) {
		return -1;
	 }
	 return 1;
  }
  
}//fin de Estado