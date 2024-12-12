package Clases;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import javax.swing.JFrame;

import javax.swing.JPanel;

/**
 *
 * @author raulp
 */
public class Graficas {

    public static void mostrarPuntos(ArrayList<Punto> puntos) {
        JFrame frame = new JFrame("Visualización de Puntos con IDs y Ejes");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(800, 800);

        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;

                // Configuración básica
                g2d.setColor(Color.BLUE);
                double scaleX = getWidth() / 2000.0;
                double scaleY = getHeight() / 2000.0;

                // Dibujar ejes de coordenadas
                g2d.setColor(Color.RED);
                g2d.setStroke(new BasicStroke(2));
                int originX = (int) (0 * scaleX);
                int originY = getHeight() - (int) (0 * scaleY);

                // Eje X
                g2d.drawLine(0, originY, getWidth(), originY);
                // Eje Y
                g2d.drawLine(originX, 0, originX, getHeight());

                // Dibujar puntos e IDs
                g2d.setColor(Color.BLUE);
                for (int i = 0; i < puntos.size(); i++) {
                    Punto p = puntos.get(i);
                    int x = (int) (p.getX() * scaleX);
                    int y = getHeight() - (int) (p.getY() * scaleY);

                    // Dibujar el punto
                    g2d.fillOval(x - 3, y - 3, 6, 6);

                    // Dibujar el ID del punto más cerca
                    g2d.setColor(Color.RED);
                    g2d.drawString(String.valueOf(i + 1), x + 2, y - 2);
                    g2d.setColor(Color.BLUE);
                }
            }
        };

        frame.add(panel);
        frame.setVisible(true);
    }

    public static void mostrarPuntosConRuta(ArrayList<Punto> puntos, ArrayList<Punto> ruta, String estrategia) {
        JFrame frame = new JFrame(estrategia);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(800, 800);

        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;

                // Configuración básica
                g2d.setColor(Color.BLUE);
                double scaleX = getWidth() / 2000.0;
                double scaleY = getHeight() / 2000.0;

                // Dibujar ejes de coordenadas
                g2d.setColor(Color.GRAY);
                g2d.setStroke(new BasicStroke(2));
                int originX = (int) (0 * scaleX);
                int originY = getHeight() - (int) (0 * scaleY);

                // Eje X
                g2d.drawLine(0, originY, getWidth(), originY);
                // Eje Y
                g2d.drawLine(originX, 0, originX, getHeight());

                // Dibujar puntos e IDs
                g2d.setColor(Color.BLUE);
                
                for (Punto p : puntos) {
                    int x = (int) (p.getX() * scaleX);
                    int y = getHeight() - (int) (p.getY() * scaleY);

                    // Dibujar el punto
                    g2d.fillOval(x - 3, y - 3, 6, 6);
                    

                    // Dibujar el ID del punto más cerca
                    g2d.setColor(Color.RED);
                    g2d.drawString(String.valueOf(p.getId()), x + 2, y - 2);                   
                    g2d.setColor(Color.BLUE);
                    
                }

                // Dibujar líneas entre puntos según la ruta
                g2d.setColor(Color.BLACK);
                g2d.setStroke(new BasicStroke(1));
                for (int i = 0; i < ruta.size() - 1; i++) {
                    
                    Punto p1 = ruta.get(i);
                    Punto p2 = ruta.get(i + 1);
                    int x = (int) (p1.getX() * scaleX);
                    int y = getHeight() - (int) (p1.getY() * scaleY);

                    int x1 = (int) (p1.getX() * scaleX);
                    int y1 = getHeight() - (int) (p1.getY() * scaleY);
                    int x2 = (int) (p2.getX() * scaleX);
                    int y2 = getHeight() - (int) (p2.getY() * scaleY);

                    g2d.drawLine(x1, y1, x2, y2);
                    if (i==0) {
                        g2d.setColor(Color.GREEN);
                        g2d.fillOval(x - 3, y - 3, 6, 6);
                        g2d.setColor(Color.BLACK);
                    }
                    
                }
            }
        };

        frame.add(panel);
        frame.setVisible(true);
    }
}
