import java.awt.EventQueue;
import java.awt.HeadlessException;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Client {

    private int[][] mundo;
    private int idJugador;
    private int[] marcs;
    private Interfaz2 ui;

    public static void main(String[] args) {

        try {
            int loop = 0;
            Client c = new Client();

            String host = (args.length < 1) ? null : args[0];
            Registry registry = LocateRegistry.getRegistry(host);
            PacmanInterface stub = (PacmanInterface) registry.lookup("Pacman");

            System.out.println("Tablero inicial ------ \n0-:casilla vacia\n1-:comida\nmayor a uno-: es jugador");
            c.idJugador = stub.nuevoJugador();
            c.setMundo((int[][]) stub.obtenerEstado().get(0));
            c.imprimeMundo();

            EventQueue.invokeLater(new Runnable() {
                public void run() {
                    try {
                        c.ui = new Interfaz2(c.getMundo(), c.idJugador);
                        c.ui.addKeyListener(new KeyListener() {
                            @Override
                            public void keyTyped(KeyEvent e) {
                            }

                            @Override
                            public void keyPressed(KeyEvent e) {
                                int key = e.getKeyCode();
                                int[] anterior = new int[2];

                                switch (key) {
                                    case KeyEvent.VK_UP:
                                        if (c.ui.posY != 0) {
                                            c.ui.y -= 25;
                                            c.ui.posY--;
                                            //this.repaint();
                                            anterior = c.ubicarJugador(c.mundo);
                                            try {
                                                stub.movimientoJugador(c.ui.posX, c.ui.posY, anterior[0], anterior[1], c.idJugador);
                                                System.out.println();
                                                c.setMundo((int[][]) stub.obtenerEstado().get(0));
                                                c.marcs = (int[]) stub.obtenerEstado().get(1);
                                                c.ui.incrementarMarcador(c.marcs[c.idJugador - 1]);
                                                c.ui.Panel.setMundo(c.mundo);
                                                c.ui.Panel.updateUI();
                                                System.out.println("Movimiento\n");
                                                c.imprimeMundo();

                                            } catch (RemoteException ex) {
                                                Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
                                            }
                                        }
                                        System.out.println(c.ui.posX + " , " + c.ui.posY);
                                        break;
                                    case KeyEvent.VK_DOWN:
                                        if (c.ui.posY != c.ui.filas - 1) {
                                            c.ui.y += 25;
                                            c.ui.posY++;
                                            anterior = c.ubicarJugador(c.mundo);
                                            try {
                                                stub.movimientoJugador(c.ui.posX, c.ui.posY, anterior[0], anterior[1], c.idJugador);
                                                c.setMundo((int[][]) stub.obtenerEstado().get(0));
                                                c.marcs = (int[]) stub.obtenerEstado().get(1);
                                                c.ui.incrementarMarcador(c.marcs[c.idJugador - 1]);
                                                c.ui.Panel.setMundo(c.mundo);
                                                c.ui.Panel.updateUI();
                                                System.out.println("Movimiento\n");
                                                c.imprimeMundo();
                                            } catch (RemoteException ex) {
                                                Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
                                            }
                                        }
                                        System.out.println(c.ui.posX + " , " + c.ui.posY);
                                        break;
                                    case KeyEvent.VK_LEFT:
                                        if (c.ui.posX != 0) {
                                            c.ui.x -= 25;
                                            c.ui.posX--;
                                            anterior = c.ubicarJugador(c.mundo);
                                            try {
                                                stub.movimientoJugador(c.ui.posX, c.ui.posY, anterior[0], anterior[1], c.idJugador);
                                                c.setMundo((int[][]) stub.obtenerEstado().get(0));
                                                c.marcs = (int[]) stub.obtenerEstado().get(1);
                                                c.ui.incrementarMarcador(c.marcs[c.idJugador - 1]);
                                                c.ui.Panel.setMundo(c.mundo);
                                                c.ui.Panel.updateUI();
                                                System.out.println("Movimiento\n");
                                                c.imprimeMundo();
                                            } catch (RemoteException ex) {
                                                Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
                                            }
                                        }
                                        System.out.println(c.ui.posX + " , " + c.ui.posY);
                                        break;
                                    case KeyEvent.VK_RIGHT:
                                        if (c.ui.posX != c.ui.columnas - 1) {
                                            c.ui.x += 25;
                                            c.ui.posX++;
                                            anterior = c.ubicarJugador(c.mundo);
                                            try {
                                                stub.movimientoJugador(c.ui.posX, c.ui.posY, anterior[0], anterior[1], c.idJugador);
                                                c.setMundo((int[][]) stub.obtenerEstado().get(0));
                                                c.marcs = (int[]) stub.obtenerEstado().get(1);
                                                c.ui.incrementarMarcador(c.marcs[c.idJugador - 1]);
                                                c.ui.Panel.setMundo(c.mundo);
                                                c.ui.Panel.updateUI();
                                                System.out.println("Movimiento\n");
                                                c.imprimeMundo();
                                            } catch (RemoteException ex) {
                                                Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
                                            }
                                        }
                                        System.out.println(c.ui.posX + " , " + c.ui.posY);
                                        break;
                                    default:
                                        break;
                                }

                            }

                            @Override
                            public void keyReleased(KeyEvent e) {
                                if (!c.existeComida()) {

                                    try {
                                        c.marcs = (int[]) stub.obtenerEstado().get(1);
                                        JOptionPane.showMessageDialog(c.ui, "Ups! Game Over you score is: " + c.marcs[c.idJugador - 2]);
                                        Thread.sleep(500);
                                    } catch (RemoteException ex) {
                                        Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
                                    } catch (InterruptedException ex) {
                                        Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
                                    }

                                }
                            }
                        });

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });

        } catch (Exception e) {
            System.err.println("Excepcion del Cliente: " + e.toString());
            e.printStackTrace();
        }

    }

    public int[][] getMundo() {
        return mundo;
    }

    public void setMundo(int[][] mundo) {
        this.mundo = mundo;
    }

    public void imprimeMundo() {
        for (int[] mundo1 : mundo) {
            for (int j = 0; j < mundo.length; j++) {
                System.out.print(" |" + mundo1[j] + "|");
            }
            System.out.println();
        }
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int[] ubicarJugador(int[][] mundo) {
        int[] c = new int[2];
        boolean encontrado = false;
        for (int i = 0; i < 15 && encontrado == false; i++) {
            for (int j = 0; j < 15 && encontrado == false; j++) {
                if (mundo[i][j] == idJugador) {
                    c[0] = i;
                    c[1] = j;
                    encontrado = true;
                }
            }
        }

        return c;
    }

    public boolean existeComida() {
        boolean notify = false;
        for (int i = 0; i < mundo.length && notify == false; i++) {
            for (int j = 0; j < mundo[i].length && notify == false; j++) {
                if (mundo[i][j] == 1) {
                    notify = true;
                }
            }
        }
        return notify;
    }

}
