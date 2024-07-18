package diseñando_regiones;

import org.openstreetmap.gui.jmapviewer.Coordinate;
import java.util.HashMap;
import java.util.Map;

public class CoordenadasContinentes {
  
    private static final Map<String, Coordinate> coordenadasContinentes = new HashMap<>();

    static {
    	coordenadasContinentes.put("África", new Coordinate(0, 20));
    	coordenadasContinentes.put("América del Norte", new Coordinate(50, -100)); 
    	coordenadasContinentes.put("América del Sur", new Coordinate(-10, -60)); 
    	coordenadasContinentes.put("Antártida", new Coordinate(-90, 0)); 
    	coordenadasContinentes.put("Asia", new Coordinate(40, 100));
    	coordenadasContinentes.put("Europa", new Coordinate(50, 10)); 
    	coordenadasContinentes.put("Oceanía", new Coordinate(-20, 140));
        
    }


    public static Coordinate obtenerCoordenadas(String nombrePais) {
        return coordenadasContinentes.get(nombrePais);
    }
}