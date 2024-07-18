package interfaz;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class GraficoEstadisticas extends JPanel 
{

    private final List<Integer> data;
    private Font letra;

    public GraficoEstadisticas(List<Integer> data) 
    {
        this.data = data;
        letra = new Font("Comic Sans MS", Font.BOLD, 10);
    }

    @Override
    protected void paintComponent(Graphics g) 
    {
        super.paintComponent(g);

        int width = getWidth();
        int height = getHeight();
        int barWidth = width / data.size();

        int maxValue = Integer.MIN_VALUE;
        
        for (int value : data) 
        {
            if (value > maxValue) 
            {
                maxValue = value;
            }
        }

        double scale = (double) (height - 20) / maxValue;

        for (int i = 0; i < data.size(); i++)
        {
            int barHeight = (int) (data.get(i) * scale);
            int x = i * barWidth;
            int y = height - barHeight;

            Color azul = new Color(70, 130, 180);
            g.setColor(azul);
            g.fillRect(x, y, barWidth, barHeight);
            g.setColor(Color.BLACK);
            g.drawRect(x, y, barWidth, barHeight);


            String labelTextProvincias = data.get(i) + " Provincias";
            g.setFont(letra);
            FontMetrics metrics = g.getFontMetrics();
            
            int labelWidthProvincias = metrics.stringWidth(labelTextProvincias);
            int labelHeightProvincias = metrics.getHeight();
            int labelXProvincias = x + (barWidth - labelWidthProvincias) / 2;
            int labelYProvincias = y - labelHeightProvincias + 8;
            
            g.drawString(labelTextProvincias, labelXProvincias, labelYProvincias);


            String textoRegion = "Región " + (i + 1);
            g.setColor(Color.WHITE);
            g.setFont(letra);
            
            int labelWidthRegion = metrics.stringWidth(textoRegion);
            int labelHeightRegion = metrics.getHeight();
            int labelXRegion = x + (barWidth - labelWidthRegion) / 2;
            int labelYRegion = height - 5 - labelHeightRegion;
            
            g.drawString(textoRegion, labelXRegion, labelYRegion);
        }
    }

    public static void mostrarGrafico(List<Integer> data) 
    {
        JFrame frame = new JFrame("Estadísticas de Regiones");
        GraficoEstadisticas panel = new GraficoEstadisticas(data);
        
        frame.add(panel);
        frame.setSize(400, 300);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);
    }
  
}
