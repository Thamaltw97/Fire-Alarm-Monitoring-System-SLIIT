
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.rmi.AlreadyBoundException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author Thamal Wijetunge
 */
public class Server extends UnicastRemoteObject implements Service {

    StringBuffer response = new StringBuffer();
    StringBuffer responseUsers = new StringBuffer();
    public static ArrayList<String> criticalAlarmIds = new ArrayList<String>();
    public static ArrayList<String> criticalAlarmIdsTemp = new ArrayList<String>();
    public static Email emailService = null;

    public Server() throws RemoteException {
        super();
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                try {

                    System.out.println("Hello from Server !");
                    getSensorDetailsApi();

                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }, 0, 15000);

    }

    public static void main(String[] args) {

        System.setProperty("java.security.policy", "file:allowall.policy");

        try {

            Server svr = new Server();
            Registry registry = LocateRegistry.getRegistry();
            registry.bind("SensorService", svr);
            System.out.println("RMI initialized....");

        } catch (RemoteException re) {
            System.err.println(re.getMessage());
        } catch (AlreadyBoundException abe) {
            System.err.println(abe.getMessage());
        }

    }

    @Override
    public String adminLogin(String userName, String password) throws RemoteException {

        String jsonObj = "";
        try {
            jsonObj = new JSONObject().put("userEmail", userName).put("userPassword", password).toString();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        try {
            Unirest.setTimeouts(0, 0);
            HttpResponse<String> response = Unirest.post("http://localhost:44381/api/user/signin")
                    .header("Content-Type", "application/json").body(jsonObj).asString();

            JSONObject myResponse = new JSONObject(response.getBody().toString());

            if (response.getStatus() == 200) {
                return String.valueOf(response.getStatus());
            } else {
                return "404";
            }

        } catch (UnirestException ex) {
            System.out.println("out : " + ex);
        } catch (JSONException ex) {
            System.out.println("JSONException : " + ex);
        } catch (Exception ex) {
            System.out.println("Exception : " + ex);
        }

        return null;

    }

    @Override
    public void getSensorDetailsApi() {
        String url = "http://localhost:44381/api/sensor/getsensors";

        try {
            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();

            con.setRequestMethod("GET");
            con.setRequestProperty("User-Agent", "Mozilla/5.0");

            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine = in.readLine();

            StringBuilder sb = new StringBuilder(inputLine);
            StringBuilder afterRemove = sb.delete(0, 21);

            String finJStr = afterRemove.substring(0, afterRemove.length() - 2) + "]";

            while ((finJStr) != null) {
                this.response = null;
                this.response = new StringBuffer();
                this.response.append(finJStr);
                finJStr = null;
            }

            ArrayList<Sensor> emailArray = new ArrayList<>();
            emailArray = getSensors(response.toString());

            for (int i = 0; i < emailArray.size(); i++) {
                if (emailArray.get(i).smokeLevel > 5 || emailArray.get(i).co2Level > 5) {
                    emailService.sendMail();
                }
            }

            in.close();

        } catch (Exception e) {
            System.out.println("ERROR : " + e);
        }
    }

    public ArrayList<Sensor> getSensors(String jsonRes) {

        ArrayList<Sensor> arrayList = new ArrayList<>();
        try {
            JSONArray jArray = new JSONArray(jsonRes);
            for (int count = 0; count < jArray.length(); count++) {
                Sensor sensorObj = new Sensor();
                JSONObject jsonObj = jArray.getJSONObject(count);
                sensorObj.setSensorId(jsonObj.getInt("sensorId"));
                sensorObj.setSensorName(jsonObj.getString("sensorName"));
                sensorObj.setFloorNumber(jsonObj.getInt("floorNo"));
                sensorObj.setRoomNumber(jsonObj.getInt("roomNo"));
                sensorObj.setSmokeLevel(jsonObj.getInt("smokeLevel"));
                sensorObj.setCo2Level(jsonObj.getInt("coLevel"));
                sensorObj.setStatus(jsonObj.getString("sensorStatus"));

                arrayList.add(sensorObj);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return arrayList;
    }

    @Override
    public String[] addSensor(String jsonObj) throws RemoteException {
        String[] response = new String[1];
        try {

            Unirest.setTimeouts(0, 0);
            HttpResponse<String> responseGet = Unirest.post("http://localhost:44381/api/sensor/addsensor")
                    .header("Content-Type", "application/json").body(jsonObj).asString();

            if (responseGet.getStatus() == 200) {
                response[0] = String.valueOf(responseGet.getStatus());
                getSensorDetailsApi();

            } else {
                response[0] = String.valueOf(responseGet.getStatus());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return response;
    }

    @Override
    public String[] updateSensor(String jsonObj) throws RemoteException {

        String[] values = new String[2];
        try {
            Unirest.setTimeouts(0, 0);
            HttpResponse<String> response = Unirest.put("http://localhost:44381/api/sensor/editsensor")
                    .header("Content-Type", "application/json").body(jsonObj).asString();

            if (response.getStatus() == 200) {
                values[0] = String.valueOf(response.getStatus());
            } else {
                values[0] = String.valueOf(response.getStatus());
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return values;
    }

    @Override
    public String[] deleteSensor(String id) throws RemoteException {
        String[] response = new String[1];
        try {

            Unirest.setTimeouts(0, 0);
            HttpResponse<String> responseGet = Unirest.delete("http://localhost:44381/api/sensor/deletesensor/" + id)
                    .header("Content-Type", "application/json").asString();

            if (responseGet.getStatus() == 200) {

                response[0] = String.valueOf(responseGet.getStatus());
                getSensorDetailsApi();

            } else {
                response[0] = String.valueOf(responseGet.getStatus());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return response;
    }

    @Override
    public StringBuffer returnSensorDetailsApi() throws RemoteException {

        return response;

    }

}
