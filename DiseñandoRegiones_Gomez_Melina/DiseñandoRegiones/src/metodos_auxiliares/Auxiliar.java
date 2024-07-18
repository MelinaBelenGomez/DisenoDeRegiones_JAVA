package metodos_auxiliares;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import diseñando_regiones.EstadisticasRegiones;
import grafos.Grafo;
public class Auxiliar
{
	private List<Integer> estadisticas;
	private boolean grafoDividido=false;
	
	
	public static Grafo AGM(Grafo grafo) 
	{
	    if (grafo.getCantNodos() > 0) 
	    {
	        Grafo nuevoGrafo = new Grafo(grafo.getCantNodos());
	        HashSet<Integer> marcados = new HashSet<Integer>();
	        marcados.add(0);
	        int cantMarcados = 1;

	        while (cantMarcados < grafo.getCantNodos()) 
	        {
	            int menorPeso = Integer.MAX_VALUE;
	            int origen = -1;
	            int destino = -1;

	            for (Integer nodoMarcado : marcados) 
	            {
	            	for (Integer vecino : grafo.getVecinos(nodoMarcado)) 
	                { 
	                    if (!marcados.contains(vecino) && grafo.getPeso(nodoMarcado, vecino) < menorPeso)
	                    { 
	                            origen = nodoMarcado;
	                            destino = vecino;
	                            menorPeso = grafo.getPeso(nodoMarcado, vecino); 
	                    }
	                }
	            }
	            
	           
	            nuevoGrafo.agregarArista(origen, destino, menorPeso);  
	            marcados.add(destino);
	            cantMarcados++;
	        }
	        return nuevoGrafo;
	    } 
	    else 
	    {
	        throw new RuntimeException("El grafo ingresado está vacío");
	    }
	}
	
	public static boolean esGrafoConexo(Grafo grafo) 
	{
	    if (grafo.getCantNodos() == 0 || grafo.getCantNodos() == 1) 
	    {
	        return true;
	    } 
	    else 
	    {
	    	Queue<Integer> listaPendientes = new LinkedList<Integer>();
	        HashSet<Integer> marcados = new HashSet<Integer>();
	        listaPendientes.add(0);

	        while (!listaPendientes.isEmpty())
	        {
	            Integer actual = listaPendientes.poll();
	            marcados.add(actual);  
	            for (Integer vecino : grafo.getVecinos(actual)) 
	            {
	                if (!marcados.contains(vecino)) 
	                {
	                    listaPendientes.add(vecino);
	                }
	            }
	        }
	       return marcados.size() == grafo.getCantNodos();
	    }
	}
	
	public  Grafo generarComponentesConexas(Grafo grafo, int k) 
	{
		
		while (k-1 > 0) {
	        int mayorPeso = 0;
	        int origen = -1;
	        int destino = -1;

	        for (int i = 0; i < grafo.getCantNodos(); i++) 
	        {
	            for (int j = 0; j < grafo.getCantNodos(); j++) 
	            {
	                if (grafo.existeArista(i, j) && grafo.getPeso(i, j) > mayorPeso) 
	                {
	                    mayorPeso = grafo.getPeso(i, j);
	                    origen = i;
	                    destino = j;
	                }
	            }
	        }
	        if (origen != -1 && destino != -1) 
	        {
	            grafo.eliminarArista(origen, destino);
	            k--;
	        }
	    }
		
	    EstadisticasRegiones estadisticasRegiones = new EstadisticasRegiones();
	    this.estadisticas = estadisticasRegiones.contarNodosEnComponentesConexas(grafo);
	    grafoDividido = true;
	   
	    return grafo;
	}
		
	public List<Integer> listaEstadisticas() {
	    return this.estadisticas;
	}
	
	public boolean grafoDividido() {
		return grafoDividido;
	}
	}