package ro.ase.semdam1_1084;

import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class ExtractJSON extends AsyncTask<URL, Void, String> {

    List<BiletAvion> biletAvionListJSON = new ArrayList<>();

    @Override
    protected String doInBackground(URL... urls) {

        HttpURLConnection connection;

        try {
            connection = (HttpURLConnection) urls[0].openConnection();
            connection.setRequestMethod("GET");
            InputStream inputStream = connection.getInputStream();

            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String linie = null;
            String rezultat = "";
            while ((linie = bufferedReader.readLine()) != null)  {
                rezultat += linie;
            }

            parsareJSON(rezultat);

            return rezultat;

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void parsareJSON(String jsonStr) {
        try {
            JSONObject jsonObject = new JSONObject(jsonStr);
            JSONArray bileteAvion = jsonObject.getJSONArray("BileteAvion");
            for (int i = 0; i < bileteAvion.length() ; i++) {
                JSONObject obj = bileteAvion.getJSONObject(i);

                String destinatie = obj.getString("Destinatie");
                Date dataZbor = new Date(obj.getString("DataZbor"));
                float pret = Float.parseFloat(obj.getString("Pret"));
                String companie = obj.getString("Companie");
                String categorieBilet = obj.getString("Categorie");

                BiletAvion biletAvion = new BiletAvion(destinatie, dataZbor, pret, companie, categorieBilet);
                biletAvionListJSON.add(biletAvion);
            }
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }
}
