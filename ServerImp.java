import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;

public class ServerImp  implements PacmanInterface{
    private int tablero[][];
    private int filas = 15;
    private int columnas = 15;
    private int jugadores = 2;
    private int[] marcadores;

    public ServerImp() throws RemoteException{
        super();
        inicializarTablero();
    }
    public void inicializarTablero(){
        tablero = new int[filas][columnas];
        for(int i = 0; i<filas ; i++){
            for(int j=0; j<columnas; j++){
                tablero[i][j] = (int) (2 * Math.random());
            }
        }
    }
    @Override
    public int nuevoJugador() throws RemoteException {
        boolean flag = false;
        int p = jugadores++;
        for(int i = 0; i<filas && flag == false; i++){
            for(int j = 0; j<columnas && flag == false; j++){
                if(tablero[i][j] == 0){
                    tablero[i][j] = p;
                    flag = true;
                }

            }

        }

        marcadores = new int[p];
        System.out.print("nuevo jugador: " +p + "\n");
        return p;
    }


    @Override
    public void movimientoJugador(int cActualy,int cActualx, int cAnteriorx,int cAnteriory, int idJugador) throws RemoteException {

        if( tablero[cActualx][cActualy] == 1){
           marcadores[idJugador-2] = marcadores[idJugador-2] + 1;
           tablero[cActualx][cActualy] = idJugador;
           tablero[cAnteriorx][cAnteriory] = 0;
        }else if (!(tablero[cActualx][cActualy] > idJugador && tablero[cActualx][cActualy] < idJugador)) {
           tablero[cActualx][cActualy] = idJugador;
           tablero[cAnteriorx][cAnteriory] = 0;
        }

    }

    @Override
    public ArrayList<Object> obtenerEstado() throws RemoteException {
        ArrayList<Object> c = new ArrayList<>();
        c.add(this.tablero);
        c.add(this.marcadores);
        return c;

    }

    public static void main(String args[]) {
        try {
            ServerImp obj = new ServerImp();
            PacmanInterface stub = (PacmanInterface) UnicastRemoteObject.exportObject(obj, 0);

            // Agrega el stud del objeto remoto al registro RMI
            Registry registry = LocateRegistry.getRegistry();
            registry.bind("Pacman", stub);
            System.err.println("Servidor Listo!");
        }  catch (Exception e) {
            System.err.println("Servidor exception: " + e.toString());
            e.printStackTrace();
        }

    }

}
