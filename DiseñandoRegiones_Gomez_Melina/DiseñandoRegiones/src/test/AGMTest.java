package test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import grafos.Grafo;
import metodos_auxiliares.Auxiliar;

class AGMTest {@Test
	public void agmTest() 
	{
		Grafo grafo = new Grafo(4);
        
        grafo.agregarArista(0, 1, 1);
        grafo.agregarArista(1, 2, 6);
        grafo.agregarArista(2, 3, 7);
        grafo.agregarArista(0, 3, 5);
        grafo.agregarArista(0, 2, 8);
        
        Grafo grafoAGM = Auxiliar.AGM(grafo);
        
        assertTrue(grafoAGM.existeArista(0, 1));
        assertTrue(grafoAGM.existeArista(1, 2));
        assertTrue(grafoAGM.existeArista(3, 0));
        assertFalse(grafoAGM.existeArista(2, 3));
        assertFalse(grafoAGM.existeArista(0, 2));   
	}
	
	@Test
	public void generarComponentesConexasTest()
	{
		Grafo g= new Grafo(4);
		Auxiliar aux= new Auxiliar();
	
	  	g.agregarArista(0, 1, 1);
	  	g.agregarArista(1, 2, 6);
	  	g.agregarArista(2, 3, 7);
	  	g.agregarArista(0, 3, 5);
	  	g.agregarArista(0, 2, 8);
	  	
	   	aux.generarComponentesConexas(g, 3);
	  
	  	assertFalse(g.existeArista(0, 2));	  	
	    assertFalse(g.existeArista(2,3));
	}
	
	@Test
	public void grafoConexoTest() 
	{
		Grafo g2 = new Grafo(4);
        
        g2.agregarArista(0, 1, 1);
        g2.agregarArista(1, 2, 6);
        g2.agregarArista(2, 3, 7);
        g2.agregarArista(0, 3, 5);
        g2.agregarArista(0, 2, 8);
        
        Grafo grafoConexo = Auxiliar.AGM(g2);
    
        assertTrue(Auxiliar.esGrafoConexo(grafoConexo));
	}
	
	@Test
	public void grafoNoConexoTest() 
	{
		Grafo g3 = new Grafo(4);
        
        g3.agregarArista(0, 1, 1);
        g3.agregarArista(1, 2, 6);
        g3.agregarArista(2, 3, 7);
        g3.agregarArista(0, 3, 5);
        g3.agregarArista(0, 2, 8);
        
        Grafo grafoConexo = Auxiliar.AGM(g3);
        grafoConexo.eliminarArista(0, 1);
    
        assertFalse(Auxiliar.esGrafoConexo(grafoConexo));
	}
	
	@Test
	public void grafoConexoVacioTest() 
	{
		Grafo g4 = new Grafo(0);
		
		assertTrue(Auxiliar.esGrafoConexo(g4));
	}
}
