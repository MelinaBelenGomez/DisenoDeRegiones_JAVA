package diseñando_regiones;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import grafos.Grafo;

public class EstadisticasRegiones {


    public List<Integer> contarNodosEnComponentesConexas(Grafo grafo) 
    {
       

    	List<Integer> tamanosRegiones = new ArrayList<>();
    	HashSet<Integer> nodosVisitados = new HashSet<>();


        for (int i = 0; i < grafo.getCantNodos(); i++) 
        {
            if (!nodosVisitados.contains(i)) 
            {
            	HashSet<Integer> componenteConexa = new HashSet<>();
            	busquedaComponenteConexas(grafo, i, componenteConexa, nodosVisitados);
                tamanosRegiones.add(componenteConexa.size());
            }
        }

        return tamanosRegiones;
    }
 

    private void busquedaComponenteConexas(Grafo grafo, int nodo, HashSet<Integer> componenteConexa, HashSet<Integer> nodosVisitados)
    {
    	nodosVisitados.add(nodo);
        componenteConexa.add(nodo);

        for (int vecino : grafo.getVecinos(nodo)) 
        {
            if (!nodosVisitados.contains(vecino)) 
            {
                busquedaComponenteConexas(grafo, vecino, componenteConexa, nodosVisitados);
            }
        }
    }
}