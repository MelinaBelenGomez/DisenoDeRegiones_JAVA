package diseñando_regiones;

import java.util.List;
import grafos.Grafo;
import metodos_auxiliares.Auxiliar;

public class Diseñando_regiones 
{
	private Grafo pais;
	private Grafo paisAGM;
	private Auxiliar aux;
	
	
	public  Diseñando_regiones(int cantVertices) 
	{
		pais = new Grafo(cantVertices);
		aux= new Auxiliar();
	}
	
	public int cantNodos() {
		return this.pais.getCantNodos();
	}
	public void agregarArista(int i, int j, int peso) 
	{

		pais.agregarArista(i, j, peso);
	}
	
	public int cantAristas() {
		return this.pais.getCantAristas();
	}
	
	public boolean existeArista(int i, int j) {
		return this.pais.existeArista(i, j);	
	}
	public boolean aristaValida(int i, int j) {
		return this.pais.esAristaValida(i, j);
	}
	
	public void eliminarArista(int i, int j) 
	{

		pais.eliminarArista(i, j);
	}
	
	public void cambiarSimilaridad(int i, int j, int peso) 
	{

		pais.cambiarPeso(i, j, peso);
	}
	

	public void crearRegiones(int k) 
	{
		if(k <= pais.getCantNodos()) 
		{
			int mayorPeso = 0;
			int origen = -1;
			int destino = -1;

			for(int i = 0; i < pais.getCantNodos(); i++) 
			{
				for(int j = 0; j < pais.getCantNodos(); j++) 
				{
					if(pais.getPeso(i, j) > mayorPeso) 
					{
						mayorPeso = pais.getPeso(i, j);
						origen = i;
						destino = j;
					}
				}
			}
			if(origen != -1 && destino != -1)
				pais.eliminarArista(origen, destino);
		}
	}
	

	
	public void generarAGM() 
	{
		this.paisAGM = aux.AGM(pais);
	}
	
	public Grafo getPais()
	{
		return this.pais;
	}
	
	public Grafo getAGM() {
		return this.paisAGM;
	}
		
}
