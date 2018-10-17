import com.sun.prism.paint.Color;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.PopupMenu;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

class Interfaz2 extends JFrame  {

    Panel Panel;
    JLabel Texto;
    JLabel score;
    int[][] mundo;
    String imageURL;
    int filas = 15;
    int columnas = 15;
    int idJugador;
    int scoreAcum;
    int x = 50;
    int y = 50;
    int posX=0;
    int posY=0;


    public Interfaz2(int[][] mundo, int idJugador) {
        this.idJugador = idJugador;
        this.mundo = mundo;
        this.setTitle("Pacman");
        Panel = new Panel(mundo);
        Panel.setBounds(100, 100, 500, 500);
        add(Panel);
        add(getTexto());
        inicializadorInterfaz();

    }

    public void inicializadorInterfaz() {

        setLayout(null);
        setSize(700, 700);
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }
    public void incrementarMarcador(int marcador){
        this.scoreAcum = marcador;
    }

    public JLabel getTexto() {

        Texto = new JLabel("Jugador: " + (this.idJugador - 1) );
        Texto.setBounds(10, 10, 70, 80);
        return Texto;

    }

   /* public JPanel getjPanel() {

        JLabel bMatriz[][] = new JLabel[filas][columnas];
        JPanel Panel = new JPanel();
        Panel.setLayout(new GridLayout(bMatriz.length, bMatriz.length));
        Panel.setBackground(java.awt.Color.black);
        ImageIcon pacman = new ImageIcon("pacman.png");
        ImageIcon comida = new ImageIcon("comida64X64.png");
        ImageIcon fondo = new ImageIcon("fondo.png");
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                System.out.print(mundo[i][j]);
                bMatriz[i][j] = new JLabel();
                if (mundo[i][j] == 1) {
                    bMatriz[i][j].setText(":");
                    bMatriz[i][j].setForeground(java.awt.Color.yellow);
                    bMatriz[i][j].setBackground(java.awt.Color.black);
                } else if (mundo[i][j] >= 2) {
                    bMatriz[i][j].setText(":<");
                    bMatriz[i][j].setForeground(java.awt.Color.red);
                    bMatriz[i][j].setBackground(java.awt.Color.black);
                }else{
                    bMatriz[i][j].setBackground(java.awt.Color.black);
                }
                bMatriz[i][j].setHorizontalAlignment(JLabel.CENTER);
                bMatriz[i][j].setVerticalAlignment(JLabel.CENTER);
                bMatriz[i][j].setBounds(i, j, 40, 40);
                Panel.setBounds(100, 100, 500, 500);
                Panel.add(bMatriz[i][j]);
            }
            System.out.println();
        }

        return Panel;
    }*/

    public int[][] getMundo() {
        return mundo;
    }




}
