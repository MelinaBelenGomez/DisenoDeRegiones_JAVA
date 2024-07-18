package grafos;

import java.util.ArrayList;
import java.util.HashSet;


public class Grafo 
{
	//Lista de vecinos para tener getVecinos en O(1)
	private ArrayList<HashSet<Integer>> vecinos;

	//Matriz para acceder en O(1) al peso de las aristas
	private int[][] pesos;
	private int cantAristas;
	
		//Constructor
		public Grafo(int cantNodos) 
		{
			cantAristas= 0;
			vecinos = new ArrayList<HashSet<Integer>>();
			for(int i = 0; i < cantNodos; i++) 
			{
				vecinos.add(new HashSet<Integer>());
			}
			pesos = new int[cantNodos][cantNodos];
		}
	
		public void agregarArista(int i, int j, int peso) 
		{
			if(esAristaValida(i, j) && i != j) 
			{
				vecinos.get(i).add(j);
				vecinos.get(j).add(i);
				pesos[i][j] = peso;
				pesos[j][i] = peso;
				cantAristas++;
			}
			else 
			{
				throw new IllegalArgumentException("Arista no valida");
			}
		}
		
		public void eliminarArista(int i, int j) 
		{
			if(existeArista(i, j)) 
			{
				vecinos.get(i).remove(j);
				vecinos.get(j).remove(i);
				pesos[i][j] = -1;
				pesos[j][i] = -1;
				cantAristas--;
			}
		}
		
		public boolean esAristaValida(int i, int j) 
		{
			return i >= 0 && j >= 0 && i<getCantNodos() && j<getCantNodos();
		}
		
		public boolean existeArista(int i, int j) 
		{
			if (esAristaValida(i, j))
			{
				return vecinos.get(i).contains(j);
			}
			return false;
		}
		
		public int getCantNodos() 
		{
			return vecinos.size();
		}
		
		public int getCantAristas()
		{
			return cantAristas;
		}
		public void cambiarPeso(int i, int j, int peso) 
		{
			if(existeArista(i, j)) 
			{
				pesos[i][j] = peso;
			}
		}
		
		public int getPeso(int i, int j) 
		{
			if(existeArista(i, j)) 
			{
				return pesos[i][j];
			}
			return -1;
		}
	
		public HashSet<Integer> getVecinos(int i) {
		    
		    return vecinos.get(i);
		}
			
}