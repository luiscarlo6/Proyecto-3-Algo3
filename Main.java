import java.io.*;
import java.util.*;

public class Main {

	public static void main(String args[]) {

		File archivoIn;
		FileWriter archivoOut;
		Graph grafo;
		Scanner s;
		PrintWriter pw;

		try {
			if (args.length == 2){
				archivoIn = new File(args[0]);
				s = new Scanner(archivoIn);
				grafo = llenar(s);
				
				int numCasos = s.nextInt();
				
				archivoOut = new FileWriter(args[1]);
				pw = new PrintWriter(archivoOut);
				
				int i = 0;
				while (i!=numCasos){
					i++;
					int capacidadCarga = s.nextInt();
					int partida = s.nextInt();
					int llegada = s.nextInt();
					Nodo Src = new Nodo("Ciudad_"+partida);
					Nodo Dst = new Nodo("Ciudad_"+llegada);
					Graph G = (Graph) grafo.clone();
					DijkstraImpl d = new DijkstraImpl();
					d.Dijkstra(G, Src, capacidadCarga);
					/**AQUI VA EL DIJKSTRA
					 * 
					 * DIkJSTRA(G,Src,capacidadCarga)
					 * 
					 * **/
					
					Dst = G.get(Dst);
					
					Nodo ultimo = (Nodo)Dst.getPadre();
					if (ultimo !=null)
						Dst.setCosto(Dst.getCosto()-(Dst.getGas()*ultimo.getPeso()));
					if (Dst.getCosto()!=Integer.MAX_VALUE){
						pw.println(String.format("%d", Dst.getCosto()));
					}
					else{
						pw.println(String.format("-1"));
					}
					
				}
				s.close();
				archivoOut.close();
			}
			else{
				System.out.println("Error en la linea de argumentos");
			}
		}
		catch (FileNotFoundException e){
			System.out.println("Error el archivo no existe");
		}
		catch(IOException e){
			System.out.print("El archivo no existe pero no puede ser creado");
			System.out.println(" o no puede ser abierto por cualquier razon.");
		}
	}

	private static Graph llenar(Scanner s){
		Graph grafo = new DiGraphHash();
		int n = 0;
		int m = 0;
		Scanner linea;
		/**Buscar n y m**/
		if (s.hasNextLine()){
			linea = new Scanner(s.nextLine());
			if (linea.hasNextInt()){
				n = linea.nextInt();
			}
			if (linea.hasNextInt()){
				m = linea.nextInt();
			}
			linea.close();
		}
		else{
			System.out.println("Error, el archivo no cumple el formato");
		}
		/**Fin buscar n y m**/

		if ((n>=1 && n<=1000) && (m>=0 && n<=10000)){
			guardarCiudades(grafo,s,n);
			guardarCarreteras(grafo,s,m);
		}
		else{
			System.out.println("Error, el archivo no cumple el formato");
		}
		return grafo;
	}


	private static void guardarCarreteras(Graph grafo, Scanner s, int m){
		if (s.hasNextLine()){
			int i = 0;
			while (i!=m){
				int src = s.nextInt();
				int dst = s.nextInt();
				//aqui hay que verificar que el peso este entre 1-100
				int peso = s.nextInt();
				String id = "Ciudad_";
				Arco arco = new Arco(id+src,id+dst,peso);
				grafo.add(arco);
				i++;
			}
		}
		else{
			System.out.println("Error, el archivo no cumple el formato");
		}
	}


	private static void guardarCiudades(Graph grafo, Scanner s, int n){
		if (s.hasNextLine()){
			Scanner linea = new Scanner(s.nextLine());

			int i = 0;
			while (i!=n){
				if (linea.hasNextInt()){
					//Aqui hay que verificar si peso esta entre 0 - 100
					int peso = linea.nextInt();
					String id = "Ciudad_"+i;
					Nodo nodo = new Nodo(id,peso);
					grafo.add(nodo);
				}
				else{
					System.out.println("Error, el archivo no cumple el formato");
				}
				i++;
			}
			linea.close();
		}
		else{
			System.out.println("Error, el archivo no cumple el formato");
		}
	}
}
