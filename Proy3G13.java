/**
 * Clase principal que ejecuta la aplicacion
 *
 * Luiscarlo Rivera, 09-11020
 * Jose Prado, 09-11006
 *
 * Proyecto 3
 * Prof Lab: Juan Arocha
 */

import java.io.*;
import java.util.*;


public class Proy3G13 {
  
  
  /**
	* @param args Argumentos de entrada, <Archivo.in> <Archivo.out>
	*/
  public static void main(String args[]) {
	 
	 File archivoIn;
	 FileWriter archivoOut;
	 Graph grafo;
	 Scanner s;
	 PrintWriter pw;
	 
	 try {
		if (args.length == 2) {
		  archivoIn = new File(args[0]);
		  s = new Scanner(archivoIn);
		  grafo = llenar(s);
		  
		  int numCasos = s.nextInt();
		  
		  archivoOut = new FileWriter(args[1]);
		  pw = new PrintWriter(archivoOut);
		  
		  int i = 0;
		  while (i != numCasos) {
			 i++;
			 int capacidadCarga = s.nextInt();
			 int partida = s.nextInt();
			 int llegada = s.nextInt();
			 Nodo Src = new Nodo("Ciudad_" + partida);
			 Nodo Dst = new Nodo("Ciudad_" + llegada);
			 
			 // se chequea que ambos nodos pertenescan al grafo
			 // en caso contrario se imprime -1
			 if (!(grafo.contains(Src) && grafo.contains(Dst))) {
				pw.println(String.format("-1"));
				continue;
			 }
			 
			 Graph G = (Graph) grafo.clone();
			 DijkstraImpl d = new DijkstraImpl(capacidadCarga);
			 d.Dijkstra(G, Src);
			 
			 Dst = G.get(Dst);
			 
			 int tam = Dst.numEstados();
			 Object estados[] = Dst.Estados();
			 int min = Integer.MAX_VALUE;
			 int g = 0;
			 while (g != tam) {
				Estado act = (Estado) estados[g];
				if (act == null) {
				  break;
				}
				int costo = act.getCosto()
				- (act.getGas() * act.getPrecio());
				if (min > costo) {
				  min = costo;
				}
				g++;
			 }
			 
			 if (min != Integer.MAX_VALUE) {
				pw.println(String.format("%d", min));
			 } else {
				pw.println(String.format("-1"));
			 }
		  }
		  pw.close();
		  s.close();
		  archivoOut.close();
		} else {
		  System.out.println("Error en la linea de argumentos");
		}
	 } catch (FileNotFoundException e) {
		System.out.println("Error el archivo no existe");
	 } catch (IOException e) {
		System.out.print("El archivo no existe pero no puede ser creado");
		System.out.println(" o no puede ser abierto por cualquier razon.");
	 }
  }
  
  
  /**
	* carga los datos del archivoIn en el grafo
	* @param s con un scanner de archivo, genera un grafo
	* @return el grafo
	*/
  private static Graph llenar(Scanner s) {
	 Graph grafo = new DiGraphHash();
	 int n = 0;
	 int m = 0;
	 Scanner linea;
	 /** Buscar n y m **/
	 if (s.hasNextLine()) {
		linea = new Scanner(s.nextLine());
		if (linea.hasNextInt()) {
		  n = linea.nextInt();
		}
		if (linea.hasNextInt()) {
		  m = linea.nextInt();
		}
		linea.close();
	 } else {
		System.out.println("Error, el archivo no cumple el formato");
	 }
	 /** Fin buscar n y m **/
	 if ((n >= 1 && n <= 1000) && (m >= 0 && n <= 10000)) {
		guardarCiudades(grafo, s, n);
		guardarCarreteras(grafo, s, m);
	 } else {
		System.out.println("Error, el archivo no cumple el formato");
	 }
	 return grafo;
  }
  
  
  /**
	* Añade la informacion de los arcos
	* @param grafo donde se guardaran las carreteras
	* @param s     Sanner del archivo
	* @param m     numero de carreteras
	*/
  private static void guardarCarreteras(Graph grafo, Scanner s, int m) {
	 if (s.hasNextLine()) {
		int i = 0;
		while (i != m) {
		  int src = s.nextInt();
		  int dst = s.nextInt();
		  // verificar que el peso este entre 1-100
		  int peso = s.nextInt();
		  if (!(peso >= 1 && peso <= 100)) {
			 System.out.println("Error, el archivo no cumple el formato");
			 System.exit(0);
		  }
		  
		  String id = "Ciudad_";
		  Arco arco = new Arco(id + src, id + dst, peso);
		  grafo.add(arco);
		  i++;
		}
	 } else {
		System.out.println("Error, el archivo no cumple el formato");
	 }
  }
  
  
  /**
	* Añade la informacion de los nodos
	* @param grafo donde se guardaran las ciudades
	* @param s     scanner de archivo
	* @param n     numero de ciudades
	*/
  private static void guardarCiudades(Graph grafo, Scanner s, int n) {
	 if (s.hasNextLine()) {
		Scanner linea = new Scanner(s.nextLine());
		
		int i = 0;
		while (i != n) {
		  if (linea.hasNextInt()) {
			 
			 int peso = linea.nextInt();
			 // verificar si peso esta entre 0 - 100
			 if (!(peso >= 0 && peso <= 100)) {
				System.out.println("Error, el archivo no cumple el formato");
				System.exit(0);
			 }
			 
			 String id = "Ciudad_" + i;
			 Nodo nodo = new Nodo(id, peso);
			 grafo.add(nodo);
		  } else {
			 System.out.println("Error, el archivo no cumple el formato");
		  }
		  i++;
		}
		linea.close();
	 } else {
		System.out.println("Error, el archivo no cumple el formato");
	 }
  }
  
}//fin Proy3G13
