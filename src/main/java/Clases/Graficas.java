package Clases;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 *
 * @author raulp
 */
public class Graficas {



    public static void mostrarPuntosConRuta(ArrayList<Punto> puntos, ArrayList<Punto> ruta, String estrategia) {
        JFrame frame = new JFrame(estrategia);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(800, 800);

        // Crear panel de dibujo con funcionalidad de zoom
        ZoomablePanel panel = new ZoomablePanel(puntos, ruta);
        JScrollPane scrollPane = new JScrollPane(panel);

        frame.add(scrollPane);
        frame.setVisible(true);
    }

    // Clase interna para el panel de dibujo con zoom
    static class ZoomablePanel extends JPanel {

        private final ArrayList<Punto> puntos;
        private final ArrayList<Punto> ruta;
        private double zoomFactor = 1.0; // Factor de zoom inicial

        public ZoomablePanel(ArrayList<Punto> puntos, ArrayList<Punto> ruta) {
            this.puntos = puntos;
            this.ruta = ruta;

            // Añadir listener para las teclas + y -
            setFocusable(true);
            addKeyListener(new KeyAdapter() {
                @Override
                public void keyPressed(KeyEvent e) {
                    if (e.getKeyCode() == KeyEvent.VK_PLUS || e.getKeyCode() == KeyEvent.VK_ADD) {
                        zoomFactor *= 1.1; // Zoom in
                    } else if (e.getKeyCode() == KeyEvent.VK_MINUS || e.getKeyCode() == KeyEvent.VK_SUBTRACT) {
                        zoomFactor /= 1.1; // Zoom out
                    }
                    revalidate(); // Ajustar el tamaño preferido del panel
                    repaint();
                }
            });
        }

        @Override
        public Dimension getPreferredSize() {
            // Ajustar el tamaño virtual del panel según el zoom
            int baseSize = 2000; // Tamaño base para el rango de coordenadas
            int width = (int) (baseSize * zoomFactor);
            int height = (int) (baseSize * zoomFactor);
            return new Dimension(width, height);
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;

            // Configuración básica
            g2d.scale(zoomFactor, zoomFactor); // Aplicar zoom

            // Dibujar puntos e IDs
            g2d.setColor(Color.BLUE);
            for (Punto p : puntos) {
                int x = (int) (p.getX());
                int y = 2000 - (int) (p.getY()); // Coordenada Y invertida para la pantalla

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

                int x1 = (int) (p1.getX());
                int y1 = 2000 - (int) (p1.getY());
                int x2 = (int) (p2.getX());
                int y2 = 2000 - (int) (p2.getY());

                g2d.drawLine(x1, y1, x2, y2);

                if (i == 0) {
                    g2d.setColor(Color.GREEN);
                    g2d.fillOval(x1 - 3, y1 - 3, 6, 6);
                    g2d.setColor(Color.BLACK);
                } else if (i == 1) {
                    g2d.setColor(Color.MAGENTA);
                    g2d.fillOval(x1 - 3, y1 - 3, 6, 6);
                    g2d.setColor(Color.BLACK);
                }
            }
        }
    }
}
