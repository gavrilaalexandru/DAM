package ro.ase.club_fitness;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class AdaugareInregistrare extends AppCompatActivity {
    public static final String ADD_SALA = "addSala";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_adaugare_inregistrare);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Intent intent = getIntent();

        EditText etNume = findViewById(R.id.etNume);
        EditText etAdresa = findViewById(R.id.etAdresa);
        EditText etPret = findViewById(R.id.etPret);
        EditText etCapacitate = findViewById(R.id.etCapacitate);
        CheckBox cbBiceps = findViewById(R.id.cbBiceps);
        CheckBox cbUmeri = findViewById(R.id.cbUmeri);
        CheckBox cbSpate = findViewById(R.id.cbSpate);
        CheckBox cbPicioare = findViewById(R.id.cbPicioare);

        Button btnSalveaza = findViewById(R.id.btnSalveaza);

        btnSalveaza.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (etNume.getText().toString().isEmpty())
                    etNume.setError("Introduceti numele!");
                else if (etAdresa.getText().toString().isEmpty())
                    etAdresa.setError("Introduceti adresa!");
                else if (etPret.getText().toString().isEmpty())
                    etPret.setError("Introduceti numele!");
                else if (etCapacitate.getText().toString().isEmpty())
                    etCapacitate.setError("Introduceti capacitatea maxima!");
                else {
                    try {
                        String nume = etNume.getText().toString();
                        String adresa = etAdresa.getText().toString();
                        float pret = Float.parseFloat(etPret.getText().toString());
                        int capacitate = Integer.parseInt(etCapacitate.getText().toString());
                        ArrayList<String> aparate = new ArrayList<>();
                        if (cbBiceps.isChecked()) aparate.add(cbBiceps.getText().toString());
                        if (cbUmeri.isChecked()) aparate.add(cbUmeri.getText().toString());
                        if (cbPicioare.isChecked()) aparate.add(cbPicioare.getText().toString());
                        if (cbSpate.isChecked()) aparate.add(cbSpate.getText().toString());

                        SalaFitness salaFitness = new SalaFitness(pret, capacitate, nume, adresa, aparate);

                        intent.putExtra(ADD_SALA, salaFitness);
                        setResult(RESULT_OK, intent);
                        finish();
                    } catch (Exception e) {
                        Log.e("Adaugare", "Eroare la adaugare");
                        Toast.makeText(getApplicationContext(), "Eroare la adaugare", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}