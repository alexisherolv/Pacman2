import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import static jdk.nashorn.internal.objects.NativeError.printStackTrace;

public class Panel extends JPanel {

    int filas = 15;
    int columnas = 15;
    int[][] mundo;
    JLabel bMatriz[][];
    String url;
    String fondo;
    String comida;
    Image imag;
    int x = 50;
    int y = 50;
    int posX=0;
    int posY=0;

    public Panel(int[][] mundo) {
        this.mundo = mundo;
        System.out.println("Constructor Panel");
        /*bMatriz = new JLabel[filas][columnas];
        this.setLayout(new GridLayout(bMatriz.length, bMatriz.length));*/
        //this.setBackground(java.awt.Color.black);
        this.repaint();
    }

    public void paint(Graphics g) {
        System.out.println("Paint");
        Graphics2D g2 = (Graphics2D) g;
        Dimension d = this.getSize();
        Image img = createImage(d.width, d.height);
        Graphics2D f = (Graphics2D) img.getGraphics();
        dibujar(f);
        g2.drawImage(img, 0, 0, this);
    }

    public void dibujar(Graphics g2) {
        System.out.println("Dibujar");
        Graphics2D g = (Graphics2D) g2;
        int x = 50;
        int y = 50;
        for (int i = 0; i < mundo.length; i++) {
            for (int j = 0; j < mundo.length; j++) {
                //System.out.print(mundo[i][j]);
                if (mundo[i][j] == 1) {
                    g.setColor(Color.green);
                    g.drawOval(x, y, 25, 25);

                } else if (mundo[i][j] >= 2) {
                    g.setColor(Color.BLUE);
                    g.drawOval(x, y, 25, 25);
                }

                x += 25;
            }
            y += 25;
            x = 50;
        }
    }

    public void setMundo(int[][] mundo) {
        this.mundo = mundo;
    }

}
