package test;
import grafos.Grafo;
import static org.junit.jupiter.api.Assertions.*;

import java.util.HashSet;

import org.junit.jupiter.api.Test;

class VecinosTest {

	
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


}
