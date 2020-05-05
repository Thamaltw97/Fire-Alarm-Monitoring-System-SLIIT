
import java.rmi.Remote;
import java.rmi.RemoteException;
import org.json.JSONObject;
import org.json.JSONArray;

/**
 *
 * @author Thamal Wijetunge
 */

public interface Service extends Remote {

    public String adminLogin(String userEmail, String userPassword) throws RemoteException;

    public String[] addSensor(String jsonObj) throws RemoteException;
    
    public String[] updateSensor(String jsonObj) throws RemoteException;
    
    public String[] deleteSensor(String sensorId) throws RemoteException;
    
    public void getSensorDetailsApi()throws RemoteException;
    
    public StringBuffer returnSensorDetailsApi() throws RemoteException;
        
}
