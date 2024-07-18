package interfaz;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaMenu {
	public static JFrame frmDiseemosRegiones;
	private static String paisElegido;
	private static int cantProvincias;
	private static Font letra;
	private static JPanel panelContenido;

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				principal();
			}
		});
	}

	private static void principal() {
		frmDiseemosRegiones = new JFrame();
		frmDiseemosRegiones.setIconImage(Toolkit.getDefaultToolkit()
				.getImage(VentanaMenu.class.getResource("/interfaz/imagenes/tituloImg.png")));
		frmDiseemosRegiones.setTitle("Diseñemos Regiones");
		frmDiseemosRegiones.setBounds(100, 100, 700, 506);
		frmDiseemosRegiones.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel panelPrincipal = new JPanel(new BorderLayout());
		frmDiseemosRegiones.getContentPane().add(panelPrincipal);

		panelContenido = new JPanel(new FlowLayout(FlowLayout.CENTER));
		panelContenido.setBackground(new Color(0, 0, 0));
		panelPrincipal.add(panelContenido, BorderLayout.CENTER);

		componentes(panelContenido, frmDiseemosRegiones);

		JPanel panelImagen = new JPanel();
		panelImagen.setBackground(new Color(0, 0, 0));
		panelImagen.setPreferredSize(new Dimension(700, 300));
		panelImagen.setLayout(new BorderLayout());

		JLabel imagenLabel = new JLabel(
				new ImageIcon(VentanaMenu.class.getResource("/interfaz/imagenes/mundoConectado.jpg")));
		imagenLabel.setBackground(new Color(0, 0, 0));
		imagenLabel.setHorizontalAlignment(SwingConstants.CENTER);
		panelImagen.add(imagenLabel, BorderLayout.SOUTH);
		panelPrincipal.add(panelImagen, BorderLayout.SOUTH);

		frmDiseemosRegiones.pack();
		frmDiseemosRegiones.setVisible(true);
	}

	private static void componentes(JPanel panelPrincipal, JFrame frame) {
		letra = new Font("Comic Sans MS", Font.BOLD, 12);

		JLabel instructionLabel = new JLabel("Por favor elija un Continente:");
		instructionLabel.setForeground(new Color(255, 255, 255));
		instructionLabel.setFont(letra);
		panelPrincipal.add(instructionLabel);

		String[] paises = { "África", "América del Norte", "América del Sur", "Antártida", "Asia", "Europa",
				"Oceanía" };
		JComboBox<String> listadoDePaises = new JComboBox<>(paises);
		listadoDePaises.setFont(letra);
		panelPrincipal.add(listadoDePaises);

		JLabel textoProvincias = new JLabel("Cantidad de provincias a visitar:");
		textoProvincias.setFont(letra);
		JTextField cantidadProvinciasField = new JTextField(5);
		cantidadProvinciasField.setPreferredSize(new Dimension(80, 25));

		JPanel cantidadProvinciasPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		cantidadProvinciasPanel.setForeground(new Color(255, 255, 255));
		cantidadProvinciasPanel.add(textoProvincias);
		cantidadProvinciasPanel.add(cantidadProvinciasField);
		panelPrincipal.add(cantidadProvinciasPanel);

		JButton startButton = new JButton("Comenzar");
		startButton.setFont(letra);
		panelPrincipal.add(startButton);
		panelPrincipal.add(Box.createVerticalStrut(90));

		startButton.addActionListener(new ActionListener() 
		{

			public void actionPerformed(ActionEvent e) 
			{

				paisElegido = (String) listadoDePaises.getSelectedItem();
				
				try 
				{
					cantProvincias = Integer.parseInt(cantidadProvinciasField.getText());
					
					if (cantProvincias < 1) 
					{
						throw new NumberFormatException();
					}

					Mapa window = new Mapa(paisElegido, cantProvincias);
					window.ventanaRegiones.setVisible(true);
					frame.dispose();

				}
				
				catch (NumberFormatException ex) 
				
				{
					JOptionPane.showMessageDialog(frame,
							"Por favor, ingrese un número válido para la cantidad de provincias o estados.");
				}
			}
		});
	}

	public JFrame getFrame() 
	{
		return this.frmDiseemosRegiones;
	}
}
