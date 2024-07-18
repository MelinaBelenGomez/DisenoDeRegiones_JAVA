package diseñando_regiones;

import java.awt.*;
import org.openstreetmap.gui.jmapviewer.Coordinate;
import org.openstreetmap.gui.jmapviewer.JMapViewer;

public class DibujanteDeLineas {

	public static void dibujarLineaEntrePuntos(JMapViewer mapa, Coordinate punto1, Coordinate punto2, Color color) {

	    Point puntoEnPanel1 = mapa.getMapPosition(punto1);
	    Point puntoEnPanel2 = mapa.getMapPosition(punto2);
	    

	    Graphics2D g2d = (Graphics2D) mapa.getGraphics();
	    g2d.setColor(color);
	    g2d.setStroke(new BasicStroke(2)); 
	    g2d.drawLine(puntoEnPanel1.x, puntoEnPanel1.y, puntoEnPanel2.x, puntoEnPanel2.y);
	}
	
	public static void borrarLineaEntrePuntos(JMapViewer mapa, Coordinate punto1, Coordinate punto2) {

	    Point puntoEnPanel1 = mapa.getMapPosition(punto1);
	    Point puntoEnPanel2 = mapa.getMapPosition(punto2);
	    

	    Color colorFondo = mapa.getBackground();
	    

	    Graphics2D g2d = (Graphics2D) mapa.getGraphics();
	    g2d.setColor(colorFondo);
	    g2d.setStroke(new BasicStroke(2));
	    g2d.drawLine(puntoEnPanel1.x, puntoEnPanel1.y, puntoEnPanel2.x, puntoEnPanel2.y);
	}

}