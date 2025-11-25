package ro.ase.semdam1_1084;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

public class BNRActivity extends AppCompatActivity implements View.OnClickListener {

    TextView tvData;
    EditText etEUR, etUSD, etGBP, etXAU;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
         EdgeToEdge.enable(this);
        setContentView(R.layout.activity_bnr); // aici accesam subclasele clasei R (resources) si putem accesa ce este in folderul res
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        /*tv1 = findViewById(R.id.textView2);
        tv1.setText("Text modificat din cod");*/

        Button btnShow = findViewById(R.id.btnShow);
        btnShow.setOnClickListener(this);

        // mai multe butoane, mai multe imprementari
        /*btnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Click pe buton!", Toast.LENGTH_LONG).show();
            }
        });*/

        tvData = findViewById(R.id.tvData);
        etEUR = findViewById(R.id.editTextEUR);
        etUSD = findViewById(R.id.editTextUSD);
        etGBP = findViewById(R.id.editTextGBP);
        etXAU = findViewById(R.id.editTextXAU);
    }

    // daca avem doar un buton ( o singura implementare)
    @Override
    public void onClick(View view) {
        /*etEUR.setText("5.23");
        etUSD.setText("4.34");
        etGBP.setText("6.58");
        etXAU.setText("524.56");*/
        @SuppressLint("StaticFieldLeak") Network network = new Network() {
            @Override
            protected void onPostExecute(InputStream inputStream) {
                /*Toast.makeText(getApplicationContext(),
                        Network.rezultat, Toast.LENGTH_LONG).show();*/
                tvData.setText(cv.getDataCurs());
                etEUR.setText(cv.getCursEUR());
                etUSD.setText(cv.getCursUSD());
                etGBP.setText(cv.getCursGBP());
                etXAU.setText(cv.getCursXAU());
            }
        };

        try {
            network.execute(new URL("https://bnr.ro/nbrfxrates.xml"));
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    private void scrieInFisier(CursValutar cursValutar, String denumireFisier) throws IOException {

        FileOutputStream fileOutputStream = openFileOutput(denumireFisier, BNRActivity.MODE_PRIVATE);
        DataOutputStream dos = new DataOutputStream(fileOutputStream);
        dos.writeUTF(cursValutar.getDataCurs());
        dos.writeUTF(cursValutar.getCursEUR());
        dos.writeUTF(cursValutar.getCursUSD());
        dos.writeUTF(cursValutar.getCursGBP());
        dos.writeUTF(cursValutar.getCursXAU());

        dos.flush();
        fileOutputStream.close();
    }

    private CursValutar citesteDinFisier(String denumireFisier) throws IOException {

        FileInputStream fileInputStream = openFileInput(denumireFisier);
        DataInputStream dis = new DataInputStream(fileInputStream);
        String dataCurs = dis.readUTF();
        String cursEUR = dis.readUTF();
        String cursUSD = dis.readUTF();
        String cursGBP = dis.readUTF();
        String cursXAU = dis.readUTF();
        CursValutar cursValutar = new CursValutar(dataCurs, cursEUR, cursUSD, cursGBP, cursXAU);
        fileInputStream.close();
        return cursValutar;
    }
}