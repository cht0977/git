package sebastian.allianz.de.AllianzNavigation;

import android.net.wifi.ScanResult;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Christopher on 19.08.2017.
 */

public class RestServiceConnector {

    public RestServiceConnector(final MainActivity mainActivity) {
        Log.i("TEST", "RestServiceConnector: ");
        RequestQueue queue = Volley.newRequestQueue(mainActivity);
        final String url = "http://192.168.1.2:8080";

        String pfad = "/positions";

        String uri = url + pfad;

        if(true) {
            SQLiteDataSource dataSource = new SQLiteDataSource(mainActivity);
            dataSource.open();
            List<CoordWFP> wfps = dataSource.getAllcoordWFP();
            for(CoordWFP test: wfps) {
                Log.i("TEST: ", "TEST " + test.toString());
            }
        }
        JsonArrayRequest getRequest = new JsonArrayRequest(Request.Method.GET, uri, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.i("Response", "SERVICE:" +response.toString());
                        int length = response.length();
                        Position[] posArray = new Position[length];
                        int i;
                        Position p;
                        JSONObject dasObjekt;
                        for(i = 0; i < length; i++) {
                             p = new Position();
                            try {
                                dasObjekt = response.getJSONObject(i);
                                //p.pos_id = (int) dasObjekt.get("pos_id");
                                //Log.i("RESPONSE", "SERVICE: POS: " + p.pos_id);
                                SQLiteDataSource dataSource = new SQLiteDataSource(mainActivity);
                                p.x=(int)dasObjekt.get("x");
                                p.y=(int)dasObjekt.get("y");
                                p.map_id = dasObjekt.get("map_id").toString();
                                JSONArray wifis = dasObjekt.getJSONArray("wifis");
                                CoordWFP cWFP;
                                List<SSIDLevelPair> Scans;
                                if(wifis.length() > 0) {
                                    int s;
                                    JSONObject wifiJSON;
                                    Scans = new ArrayList<>();
                                    SSIDLevelPair pair;
                                    for(s=0; s < wifis.length();s++) {
                                        wifiJSON = (JSONObject) wifis.get(s);
                                        pair = new SSIDLevelPair(wifiJSON.getString("mac_adress"), wifiJSON.getInt("lvl"));
                                        Scans.add(pair);
                                    }
                                    dataSource.open();
                                    int pos_id = dataSource.insertPos(p.map_id, p.x, p.y);
                                    for(SSIDLevelPair lvlpair: Scans) {
                                        Log.i("TEST", "TEST WIFI_  " + lvlpair.SSID + lvlpair.level);
                                        dataSource.insertWifi(pos_id, lvlpair.SSID, lvlpair.level);
                                    }
                                    dataSource.close();
                                    //MyScanResult myScanResult = new MyScanResult(Scans, 0);
                                    //cWFP = new CoordWFP(p,myScanResult);

                                }


                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.i("Error.Response", "SERVICE: " + error.toString());
                    }
                }
        );
        queue.add(getRequest);
    }
}
