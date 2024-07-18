package test;
import grafos.Grafo;
import static org.junit.jupiter.api.Assertions.*;

import java.util.HashSet;

import org.junit.jupiter.api.Test;

class AristasTest 
{
	@Test
	public void aristaExistenteTest()
	{
		Grafo grafo = new Grafo(5);
		grafo.agregarArista(2, 3,10);
		assertTrue(grafo.existeArista(2, 3));
	}

	@Test
	public void aristaOpuestaTest()
	{
		Grafo grafo = new Grafo(5);
		grafo.agregarArista(2, 3,7);
		assertTrue(grafo.existeArista(3, 2));
	}
	
	@Test
	public void aristaInexistenteTest()
	{
		Grafo grafo = new Grafo(5);
		grafo.agregarArista(2, 3,2);
		assertFalse(grafo.existeArista(1, 4));
	}

	@Test
	public void agregarAristaDosVecesTest()
	{
		Grafo grafo = new Grafo(5);
		grafo.agregarArista(2, 3,2);
		grafo.agregarArista(2, 3,2);

		assertTrue(grafo.existeArista(2, 3));
	}
	
	@Test
	public void eliminarAristaExistenteTest()
	{
		Grafo grafo = new Grafo(5);
		grafo.agregarArista(2, 4,4);
		
		grafo.eliminarArista(2, 4);
		assertFalse(grafo.existeArista(2, 4));
	}
	
	@Test
	public void eliminarAristaInexistenteTest()
	{
		Grafo grafo = new Grafo(5);
		grafo.eliminarArista(2, 4);
		assertFalse(grafo.existeArista(2, 4));
	}
	
	@Test
	public void eliminarAristaDosVecesTest()
	{
		Grafo grafo = new Grafo(5);
		grafo.agregarArista(2, 4,6);
		
		grafo.eliminarArista(2, 4);
		grafo.eliminarArista(2, 4);
		assertFalse(grafo.existeArista(2, 4));
	}


	
	@Test
	public void verticeUniversalTest()
	{
		Grafo grafo = new Grafo(4);
		grafo.agregarArista(1, 0, 6);
		grafo.agregarArista(1, 2, 9);
		grafo.agregarArista(1, 3, 7);
		
		HashSet<Integer> esperado = new HashSet<>();
	    esperado.add(0);
	    esperado.add(2);
	    esperado.add(3);
	   
		assertEquals(esperado, grafo.getVecinos(1));
	}
	
	@Test
	public void verticeNormalTest()
	{
		Grafo grafo = new Grafo(5);
		grafo.agregarArista(1, 3, 6);
		grafo.agregarArista(2, 3, 9);
		grafo.agregarArista(2, 4, 7);
		
		HashSet<Integer> esperado = new HashSet<>();
	    esperado.add(1);
	    esperado.add(2);
	    
	   
		assertEquals(esperado, grafo.getVecinos(3));
	}


	@Test
	public void primerVerticeNegativoTest()
	{
	    Grafo grafo = new Grafo(5);
	    assertThrows(IllegalArgumentException.class, () -> 
	    {
	        grafo.agregarArista(-1, 3, 9);
	    });
	}

	@Test
	public void primerVerticeExcedidoTest()
	{
	    Grafo grafo = new Grafo(5);
	    assertThrows(IllegalArgumentException.class, () -> 
	    {
	        grafo.agregarArista(5, 3, 9);
	    });
	}
	
	@Test
	public void agregarLoopTest() 
	{
	    Grafo grafo = new Grafo(5);
	    assertThrows(IllegalArgumentException.class, () -> 
	    {
	        grafo.agregarArista(3, 3, 9);
	    });
	}
}
