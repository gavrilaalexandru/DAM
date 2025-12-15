package ro.ase.clientijson;

import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ExtractJSON extends AsyncTask<URL, Void, String> {
    List<Client> listClient = new ArrayList<>();

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

            while ((linie = bufferedReader.readLine()) != null) {
                rezultat += linie;
            }

            parsareJSON(rezultat);

            return rezultat;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void parsareJSON(String rezultat) {
        try {
            JSONObject jsonObject = new JSONObject(rezultat);
            JSONArray clienti = jsonObject.getJSONArray("clienti");
            for (int i = 0; i < clienti.length(); i++) {
                JSONObject obj = clienti.getJSONObject(i);

                String nume = obj.getString("Nume");
                Date dataContract = new Date(obj.getString("DataContract"));
                String tipAbonament = obj.getString("TipAbonament");
                float pret = Float.parseFloat(obj.getString("Pret"));
                boolean extraoptiuni = obj.getString("Extraoptiuni").equalsIgnoreCase("DA");

                Client client = new Client(dataContract, extraoptiuni, nume, pret, tipAbonament);
                listClient.add(client);
            }
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }
}
