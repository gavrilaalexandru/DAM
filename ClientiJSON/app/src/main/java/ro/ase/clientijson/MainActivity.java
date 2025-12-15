package ro.ase.clientijson;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    List<Client> listaClienti = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.meniu_principal, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.optiune1) {
            ExtractJSON extractJSON = new ExtractJSON() {
                @Override
                protected void onPostExecute(String s) {
                    listaClienti.addAll(this.listClient);
                    Toast.makeText(MainActivity.this, "Au fost incarcate " + this.listClient.size() + " elemente", Toast.LENGTH_LONG).show();
                    TextView tv1 = findViewById(R.id.tv1);
                    tv1.setText(listaClienti.toString());
                }
            };
            try {
                extractJSON.execute(new URL("https://cristianciurea.ase.ro/upload/customers.txt"));
            } catch (MalformedURLException e) {
                throw new RuntimeException(e);
            }
            return true;
        } else if (item.getItemId() == R.id.optiune2) {
            ClientDB database = ClientDB.getInstance(getApplicationContext());
            database.getBileteDAO().insert(listaClienti);
            Toast.makeText(this, "Au fost salvate " + listaClienti.size() + " elemente in baza de date", Toast.LENGTH_SHORT).show();
            return true;
        } else if (item.getItemId() == R.id.optiune3) {
            TextView tv1 = findViewById(R.id.tv1);
            tv1.setText("");
            ClientDB database = ClientDB.getInstance(getApplicationContext());
            List<Client> listaSortata = database.getBileteDAO().sortareDupaCamp();
            tv1.setText(listaSortata.toString());
            return true;
        } else if (item.getItemId() == R.id.optiune4) {
            return true;
        } else if (item.getItemId() == R.id.optiune5) {
            Intent intent = new Intent(getApplicationContext(), Statistica.class);
            startActivity(intent);
            return true;
        }
        return false;
    }
}