import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface PacmanInterface extends Remote{
    public int nuevoJugador() throws RemoteException;
    public void movimientoJugador(int cActualx,int cActualy, int cAnteriorx,int cAnteriory, int idJugador) throws RemoteException;
    public ArrayList<Object> obtenerEstado() throws RemoteException;
}
