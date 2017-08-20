package sebastian.allianz.de.AllianzNavigation;


import android.os.AsyncTask;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by dregm on 14.08.2017.
 */

public class ContactServer extends AsyncTask {
    //Initialisierung der Variablen
    public boolean connected = false;
    public MainActivity mainActivity;




    //Konstruktor
    public ContactServer(MainActivity mainActivity){
//        this.isConnected();
        this.mainActivity = mainActivity;
    }



    @Override
    protected void onPreExecute(){
        Log.i("AsyncTask", "Test");
    }


    //Thread zum Download der Daten
    @Override
    protected  Object doInBackground(Object... params){
        //Versuch eine Verbindung zum Server herzustellen
        try {
            //Initialisierung einer URL mit der IP des Servers
            URL url = new URL("http://192.168.1.2:8080/test");

            //Aufbau einer Verbindung
            HttpURLConnection urlConn = (HttpURLConnection) url.openConnection();

            //Setzen der Wartezeit auf eine Antwort
            urlConn.setConnectTimeout(5000);

            //Prüfung, ob eine Verbindung möglich ist
            if(urlConn.getResponseCode() == 200){
                //Setzten des Attributes zur Erkennung, ob eine Verbindung möglich ist
                this.connected = true;
            }
        }catch (Exception e){
            this.connected = false;
        }

        //Aufrufen der Methode zur Wiedergabe des Ergebnisses des Verbindungsaufbaus
        this.commToActivity();
        return null;
    }




    //KOmmunikation mit der Activity
    private void commToActivity(){
        Handler handler = new Handler(this.mainActivity.getMainLooper());
        handler.post(new Runnable() {
            @Override
            public void run() {
                if(connected) {
                    Toast.makeText(mainActivity, "Verbindung aufgebaut", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(mainActivity, "Verbindung fehlgeschlagen", Toast.LENGTH_SHORT).show(); //wieder auf fehlgeschlagen ändern.
                    cancel(true);
                }
            }
        });
    }




    //Methode zum Prüfen, ob Daten automatisch geladen werden sollen
    private boolean loadAutomativ(){
        //Datenbankabfrage der personenbezogenen Einstellungen, ob Daten automatisch geladen werden sollen
        return false;   //Noch nicht programmiert
    }



    //Methode zum Laden neuer POIS
    private List<POI> loadnewPOIs(){
        List<POI> newPOIs = new ArrayList<>();

        return  newPOIs;
    }




    //Methode zum Laden neuer Karten
    private List<Map> loadnewMaps(){
        List<Map> newMaps = new ArrayList<>();

        return newMaps;
    }




    //Methode zum Laden neuer Wifi
    private List<WifiScanObject> loadnewWifiScanObjects(){
        List<WifiScanObject> newWifiScanObjects = new ArrayList<>();

        return newWifiScanObjects;
    }




    //MEthode zum Laden neuer Positionen
    private List<Position> loadnewPositions(){
        List<Position> newPositions = new ArrayList<>();

        return newPositions;
    }
}
