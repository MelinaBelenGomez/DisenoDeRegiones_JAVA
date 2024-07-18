package interfaz;
import diseñando_regiones.CoordenadasContinentes;
import diseñando_regiones.DibujanteDeLineas;
import diseñando_regiones.Diseñando_regiones;
import grafos.Grafo;
import metodos_auxiliares.Auxiliar;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import org.openstreetmap.gui.jmapviewer.Coordinate;
import org.openstreetmap.gui.jmapviewer.JMapViewer;
import org.openstreetmap.gui.jmapviewer.MapMarkerDot;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.awt.Toolkit;
import javax.swing.ImageIcon;


public class Mapa 
{

	public JFrame ventanaRegiones;
	private JPanel panelMapa;
	private JPanel panelControles;
	private JMapViewer _mapa;
	private ArrayList<Coordinate> _lasCoordenadas;
	private JButton btnArista ;
	private JButton btnEliminarArista;
	private JButton btnArbol;
	private JButton btnRegiones;
	private Grafo mejorRuta;
	private Diseñando_regiones diseñoRegiones;
	private Auxiliar algoritmos;
	private int  ordenPuntoMarcado;
	private DibujanteDeLineas dibujo;
	private String continenteElegido;
	private int cantProvincias;
	private boolean todasLasCoordenadasMarcadas;
	private Font letra;
	private JButton btnCambiarSimilaridad;
	private JButton btnReinicio;
	private JButton btnEstadisticas;
	private VentanaMenu menu;
	private GraficoEstadisticas graficoEstadisticas;
	

	public Mapa(String continente, int cantProvincias) 
	{
		  ventanaRegiones = new JFrame();
		  ventanaRegiones.setForeground(new Color(255, 255, 255));
		  ventanaRegiones.setBackground(new Color(255, 255, 255));
		  ventanaRegiones.setTitle("¡Diseñemos Regiones!");
		  ventanaRegiones.setIconImage(Toolkit.getDefaultToolkit().getImage(Mapa.class.getResource("/interfaz/imagenes/tituloImg.png")));
		  _mapa = new JMapViewer();
		 

		diseñoRegiones= new Diseñando_regiones(cantProvincias);
		algoritmos= new Auxiliar();
		ordenPuntoMarcado= 0;
		todasLasCoordenadasMarcadas= false;
		dibujo= new DibujanteDeLineas();
		this.cantProvincias= cantProvincias;
		continenteElegido= continente;
		initialize();
		
	}

	private void initialize() 
	{
		ventanaRegiones.setBounds(100, 100, 700, 506);
		ventanaRegiones.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ventanaRegiones.getContentPane().setLayout(null);
		
		panelMapa = new JPanel();
		panelMapa.setBackground(new Color(0, 0, 0));
		panelMapa.setBounds(10, 11, 437, 446);
		ventanaRegiones.getContentPane().add(panelMapa);
		
		panelControles = new JPanel();
		panelControles.setBounds(457, 11, 242, 446);
		ventanaRegiones.getContentPane().add(panelControles);		
		panelControles.setLayout(null);
		

		Coordinate coordenadasPais = CoordenadasContinentes.obtenerCoordenadas(continenteElegido); 
		_mapa.setDisplayPosition(coordenadasPais, 3);
		panelMapa.add(_mapa);
		detectarCoordenadas();
		botonArista();
		botonEliminarArista();	
		botonSimilaridad();
		botonArbol();		
		botonRegiones();
		botonEstadisticas();
		botonReinicio();
		
	}
	
	private void detectarCoordenadas()
	{
	    _lasCoordenadas = new ArrayList<Coordinate>();

	    _mapa.addMouseListener(new MouseAdapter() 
	    {

	        @Override
	        public void mouseClicked(MouseEvent e) 
	        {
	        	
	            if (!todasLasCoordenadasMarcadas) 
	            {
	            	
	                if (e.getButton() == MouseEvent.BUTTON1 && _lasCoordenadas.size() < cantProvincias) 
	                {
	                	
	                    Coordinate puntoMarcado = (Coordinate) _mapa.getPosition(e.getPoint());
	                    
	                    if (puntoMarcado != null) 
	                    {
	                    	_lasCoordenadas.add(puntoMarcado);
	
	                        String orden = "" + ordenPuntoMarcado;
	                        _mapa.addMapMarker(new MapMarkerDot(orden, puntoMarcado));
	                        ordenPuntoMarcado++;
	                        
	                        
	                        if (_lasCoordenadas.size() == cantProvincias) 
	                        {
	                            todasLasCoordenadasMarcadas = true;
	                            JOptionPane.showMessageDialog(null, "Se han marcado la ultima provincia/estado");
	                        }
	                    }
	                    
	                }else 
	                	
	                {
	                    JOptionPane.showMessageDialog(null, "Ya se han marcado todas las coordenadas.");
	                }
	            }
	        }
	    });
	}
	
	public void botonArista() 
	{

		JLabel labelTexto = new JLabel("Marcar las provincias con click izquierdo");
		labelTexto.setForeground(new Color(255, 255, 102));
		labelTexto.setBackground(new Color(0, 0, 255));
	    labelTexto.setBounds(10, 81, 195, 20); 
	    
	    letra = new Font("Comic Sans MS", Font.BOLD, 9);
	    labelTexto.setFont(letra);
	    
	    panelControles.add(labelTexto); 
	    
	    btnArista = new JButton("Agregar Camino");
	    letra = new Font("Comic Sans MS", Font.BOLD, 12);
	    btnArista.setFont(letra);
	    
	    btnArista.addActionListener(new ActionListener() 
	    {
	    	
	        public void actionPerformed(ActionEvent arg0) 
	        {
	        	

	    	    JTextField origenField = new JTextField(5);
	    	    JTextField destinoField = new JTextField(5);
	    	    JTextField pesoField = new JTextField(5);

	    	    JPanel panel = new JPanel(new GridLayout(0, 1));
	    	    panel.add(new JLabel("Origen:"));
	    	    panel.add(origenField);
	    	    panel.add(new JLabel("Destino:"));
	    	    panel.add(destinoField);
	    	    panel.add(new JLabel("Similaridad:"));
	    	    panel.add(pesoField);

	    	    int result = JOptionPane.showConfirmDialog(null, panel, "Agregar Camino",
	    	        JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
	    	    
	    	    if (result == JOptionPane.OK_OPTION) 
	    	    {
	    	        try 
	    	        {

	    	            int origen = Integer.parseInt(origenField.getText());
	    	            int destino = Integer.parseInt(destinoField.getText());
	    	            int peso = Integer.parseInt(pesoField.getText());
	    	        

	    	            if(diseñoRegiones.aristaValida(origen, destino) && mejorRuta==null) 
	    	            	
	    	            {

	    	            Coordinate coordenadaOrigen = _lasCoordenadas.get(origen);
	    	            Coordinate coordenadaDestino = _lasCoordenadas.get(destino);	    	            

	    	            dibujo.dibujarLineaEntrePuntos(_mapa,coordenadaOrigen, coordenadaDestino, Color.BLACK);

	    	           diseñoRegiones.agregarArista(origen, destino, peso);
	    	          
	    	            }
	    	            else {
	    	            	JOptionPane.showMessageDialog(null, "No son coordenadas validas");
	    	            }
	    	        }catch (NumberFormatException e) {

	    	            JOptionPane.showMessageDialog(null, "Por favor, ingrese datos válidos.");
	    	        }		    	        
	    	    }	 	    	    
	        }	    	    
	    });	   
	    
	    btnArista.setBounds(10, 140, 195, 23);
	    panelControles.add(btnArista);
	}
	
	public void botonEliminarArista()
	{
		btnEliminarArista = new JButton("Eliminar Camino");
		 letra = new Font("Comic Sans MS", Font.BOLD, 12);
		    btnEliminarArista.setFont(letra);
		    
		btnEliminarArista.addActionListener(new ActionListener() 
		{
	        public void actionPerformed(ActionEvent arg0) 
	        {
	        	if(diseñoRegiones.cantAristas() >0) 
	        	{ 
	        		JTextField origenField = new JTextField(5);
	        		JTextField destinoField = new JTextField(5);
	        		JPanel panel = new JPanel(new GridLayout(0, 1));
	        		panel.add(new JLabel("Origen:"));
	        		panel.add(origenField);
	        		panel.add(new JLabel("Destino:"));
	        		panel.add(destinoField);
	    	  
	    	    int result = JOptionPane.showConfirmDialog(null, panel, "Eliminar Camino",
	    	        JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
	    	    
	    	    if (result == JOptionPane.OK_OPTION) 
	    	    {
	    	        try 
	    	        {
	    	            
	    	            int origen = Integer.parseInt(origenField.getText());
	    	            int destino = Integer.parseInt(destinoField.getText());
	    	            
	    	            if(diseñoRegiones.existeArista(origen, destino) && mejorRuta ==null) 
	    	            {
	    	        
	    	            Coordinate coordenadaOrigen = _lasCoordenadas.get(origen);
	    	            Coordinate coordenadaDestino = _lasCoordenadas.get(destino);

	    	            dibujo.borrarLineaEntrePuntos(_mapa,coordenadaOrigen, coordenadaDestino);
	    	            diseñoRegiones.eliminarArista(origen, destino);
	    	            }
	    	            
	    	            else 
	    	            
	    	            {
	    	            	JOptionPane.showMessageDialog(null, "El camino entre el punto de origen y destino no existe");
	    	            }
	    	          
	    	        } catch (NumberFormatException e) {
	    	          
	    	            JOptionPane.showMessageDialog(null, "Por favor, ingrese datos válidos.");
	    	        }  
	    	    }
	        }
	        	else {
	        		JOptionPane.showMessageDialog(null, "Debe haber al menos un camino");
	        	}
	        }
	       
	    });
	   
		btnEliminarArista.setBounds(10, 140 + 23 + 10, 195, 23);
	    panelControles.add(btnEliminarArista);
	}
	
	private void botonSimilaridad()
	{
		
	    btnCambiarSimilaridad = new JButton("Cambiar similaridad");
	    letra = new Font("Comic Sans MS", Font.BOLD, 12);
	    btnCambiarSimilaridad.setFont(letra);
	    
	    btnCambiarSimilaridad.addActionListener(new ActionListener() 
	    {
	        public void actionPerformed(ActionEvent arg0) {
	        	

	    	    JTextField origenField = new JTextField(5);
	    	    JTextField destinoField = new JTextField(5);
	    	    JTextField pesoField = new JTextField(5);

	    	    JPanel panel = new JPanel(new GridLayout(0, 1));
	    	    panel.add(new JLabel("Origen:"));
	    	    panel.add(origenField);
	    	    panel.add(new JLabel("Destino:"));
	    	    panel.add(destinoField);
	    	    panel.add(new JLabel("Similaridad nueva:"));
	    	    panel.add(pesoField);

	    	    int result = JOptionPane.showConfirmDialog(null, panel, "Agregar Camino",
	    	        JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
	    	    if (result == JOptionPane.OK_OPTION) 
	    	    {
	    	        try 
	    	        {

	    	            int origen = Integer.parseInt(origenField.getText());
	    	            int destino = Integer.parseInt(destinoField.getText());
	    	            int peso = Integer.parseInt(pesoField.getText());
	    	            
	    	            if(diseñoRegiones.existeArista(origen, destino) && mejorRuta==null) 
	    	            {	    	       	            

	    	            diseñoRegiones.cambiarSimilaridad(origen, destino, peso);
	    	            }
	    	            else {
	    	            	JOptionPane.showMessageDialog(null,"El camino entre el punto de origen y destino no existe");
	    	            }
	    	            
	    	        } catch (NumberFormatException e) {

	    	            JOptionPane.showMessageDialog(null, "Por favor, ingrese datos válidos.");
	    	        }	    	       
	    	    }	           
	        }	        		        	       
	    });
	    
	    int yPosition = btnEliminarArista.getY() + btnEliminarArista.getHeight() + 10;
	    btnCambiarSimilaridad.setBounds(10, yPosition, 195, 23);
        panelControles.add(btnCambiarSimilaridad);	    
	}
	
	  private void botonArbol()
	  {
	        btnArbol = new JButton("Crear mejor ruta");
	        letra = new Font("Comic Sans MS", Font.BOLD, 12);
		    btnArbol.setFont(letra);
		    
	        btnArbol.addActionListener(new ActionListener() 
	        {
	            public void actionPerformed(ActionEvent arg0) 
	            { 
	            	if(diseñoRegiones.cantAristas()>0 && mejorRuta==null && Auxiliar.esGrafoConexo(diseñoRegiones.getPais())) 
	            	
	            	{
	            	diseñoRegiones.generarAGM();
	            	mejorRuta= diseñoRegiones.getAGM();
	            	dibujarAristasAGM(mejorRuta);	       		            	
	            }
	            	else 
	            	{
	            		JOptionPane.showMessageDialog(null, "Por favor, todos los caminos deben estar conectados");
		            }
	            }
	            
	        });
	        
	        int yPosition = btnCambiarSimilaridad.getY() + btnCambiarSimilaridad.getHeight() + 10;
	        btnArbol.setBounds(10, yPosition, 195, 23);
	        panelControles.add(btnArbol);
	    }
	

	   private void dibujarAristasAGM(Grafo agm)
	   {
		   limpiarMapa();

	        for (int i = 0; i < agm.getCantNodos(); i++) 
	        {
	            for (int j = i + 1; j < agm.getCantNodos(); j++) 
	            {

	                if (agm.existeArista(i, j)) 
	                {

	                    Coordinate coordenadaOrigen = _lasCoordenadas.get(i);
	                    Coordinate coordenadaDestino = _lasCoordenadas.get(j);
	                    dibujo.dibujarLineaEntrePuntos(_mapa, coordenadaOrigen, coordenadaDestino, Color.GREEN);
	                }
	            }
	        }
	    }
	
	   
	   public void limpiarMapa() 
	   {

		    Graphics2D g2d = (Graphics2D) _mapa.getGraphics();

		    for (int i = 0; i < _lasCoordenadas.size(); i++) 
		    {
		        for (int j = i + 1; j < _lasCoordenadas.size(); j++) 
		        {
		            Coordinate punto1 = _lasCoordenadas.get(i);
		            Coordinate punto2 = _lasCoordenadas.get(j);
		            
		            if(diseñoRegiones.existeArista(i, j)) 
		            {
		            DibujanteDeLineas.borrarLineaEntrePuntos(_mapa, punto1, punto2);
		            }
		        }
		    }
		}
	   
	   
	   public void dividirArbolEnRegiones(int cantidadRegiones)
	   {
	        

	            if (mejorRuta != null) 
	            {
	                
	                Grafo grafoDividido = algoritmos.generarComponentesConexas(mejorRuta,  cantidadRegiones );
	                
	                dibujarAristasAGM(grafoDividido);
	            } else {
	                JOptionPane.showMessageDialog(null, "Primero debe crear la mejor ruta");
	            }        
	    }
	   

private void botonRegiones()
{
	 btnRegiones = new JButton("Dividir en Regiones"); 
	    letra = new Font("Comic Sans MS", Font.BOLD, 12);
	    btnRegiones.setFont(letra);
	    
    btnRegiones.addActionListener(new ActionListener() 
    {
        public void actionPerformed(ActionEvent arg0) 
        {
            
            JTextField cantidadRegionesField = new JTextField(5);
            JPanel panel = new JPanel(new GridLayout(0, 1));
            panel.add(new JLabel("Cantidad de regiones:"));
            panel.add(cantidadRegionesField);
            int result = JOptionPane.showConfirmDialog(null, panel, "Dividir en Regiones",
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

            if (result == JOptionPane.OK_OPTION) {
                try {

                    int cantidadRegiones = Integer.parseInt(cantidadRegionesField.getText());
                    

                    if (cantidadRegiones >= 1 && cantidadRegiones<= diseñoRegiones.cantNodos()) {
                      
                        dividirArbolEnRegiones(cantidadRegiones);
                    } else {
                        JOptionPane.showMessageDialog(null, "La cantidad de regiones debe ser mayor que 1 y menor que "+ diseñoRegiones.cantNodos());
                    }
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Por favor, ingrese un número válido.");
                }
            }
        }
    });

    int yPositionRegiones = btnArbol.getY() + btnArbol.getHeight() + 10;
    btnRegiones.setBounds(10, yPositionRegiones, 195, 23);
    panelControles.add(btnRegiones);
}

private void botonEstadisticas()
{
	btnEstadisticas = new JButton("Estadisticas");
    letra = new Font("Comic Sans MS", Font.BOLD, 12);
    btnEstadisticas.setFont(letra);
    
    btnEstadisticas.addActionListener(new ActionListener() 
    {
        public void actionPerformed(ActionEvent arg0) 
        { 
        	 if (algoritmos.grafoDividido()) 
        	 {
        		 graficoEstadisticas = new GraficoEstadisticas(algoritmos.listaEstadisticas());
        	        List listaEstadisticas = algoritmos.listaEstadisticas();
        	        graficoEstadisticas.mostrarGrafico(listaEstadisticas);
        	        graficoEstadisticas.setSize(500, 400);
        	        graficoEstadisticas.setVisible(true);
             }
        	 else {
        		 JOptionPane.showMessageDialog(null, "Primero debe dividir en regiones");
        	 }
         }        
     });
    
    int yPosition = btnRegiones.getY() + btnRegiones.getHeight() + 10;
    btnEstadisticas.setBounds(10, yPosition, 195, 23);
    panelControles.add(btnEstadisticas);
    
   
}

private void botonReinicio()
{
	 btnReinicio = new JButton("Volver al menu");
     letra = new Font("Comic Sans MS", Font.BOLD, 12);
     btnReinicio.setFont(letra);
     
     btnReinicio.addActionListener(new ActionListener() 
     {
         public void actionPerformed(ActionEvent arg0) 
         { 

             menu = new VentanaMenu();
             menu.getFrame().setVisible(true); 
             ventanaRegiones.dispose();
         }
         
     });
     
     int yPosition = btnEstadisticas.getY() + btnEstadisticas.getHeight() + 10;
     btnReinicio.setBounds(10, yPosition, 195, 23);
     panelControles.add(btnReinicio);
     
     JLabel imagenDeFondo = new JLabel("");
     imagenDeFondo.setIcon(new ImageIcon(Mapa.class.getResource("/interfaz/imagenes/fondo2.png")));
     imagenDeFondo.setBounds(0, 0, 232, 446);
     panelControles.add(imagenDeFondo);
 
}

public JFrame getFrame() {
    return ventanaRegiones;
}
}