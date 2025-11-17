package ro.ase.test_facultate;

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

public class AdaugaInregistrare extends AppCompatActivity {

    public static final String ADD_FACULTATE = "addFacultate";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_adauga_inregistrare);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Intent intent = getIntent();

        EditText etNume = findViewById(R.id.etNume);
        EditText etNrStud = findViewById(R.id.etNrStud);
        EditText etAn = findViewById(R.id.etAn);
        EditText etAdresa = findViewById(R.id.etAdresa);
        CheckBox cbCamin = findViewById(R.id.cbCamin);
        CheckBox cbCantina = findViewById(R.id.cbCantina);
        CheckBox cbBiblioteca = findViewById(R.id.cbBiblioteca);
        CheckBox cbSalaSport = findViewById(R.id.cbSalaSport);

        Button btnAdauga = findViewById(R.id.btnAdauga);

        if (intent.hasExtra(ListaInregistrari.EDIT_FACULTATE)) {
            Facultate facultate = (Facultate) intent.getSerializableExtra(ListaInregistrari.EDIT_FACULTATE);
            if (facultate != null) {
                etNume.setText(facultate.getNume());
                etAdresa.setText(facultate.getAdresa());
                etAn.setText(String.valueOf(facultate.getAnInfiintare()));
                etNrStud.setText(String.valueOf(facultate.getNrStud()));

                ArrayList<String> facilitati = facultate.getFacilitati();
                cbCamin.setChecked(facilitati.contains(cbCamin.getText().toString()));
                cbBiblioteca.setChecked(facilitati.contains(cbBiblioteca.getText().toString()));
                cbCantina.setChecked(facilitati.contains(cbCantina.getText().toString()));
                cbSalaSport.setChecked(facilitati.contains(cbSalaSport.getText().toString()));

            }
            btnAdauga.setText("Editare");
        }


        btnAdauga.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (etNume.getText().toString().isEmpty())
                    etNume.setError("Introduceti numele!");
                else if (etAdresa.getText().toString().isEmpty())
                    etAdresa.setError("Introduceti adresa!");
                else if (etAn.getText().toString().isEmpty())
                    etAn.setError("Introduceti anul infiintarii!");
                else if (etNrStud.getText().toString().isEmpty())
                    etNrStud.setError("Introduceti un numar de studenti valid!");
                else {
                    try {
                        String nume = etNume.getText().toString();
                        String adresa = etAdresa.getText().toString();
                        int nrStud = Integer.parseInt(etNrStud.getText().toString());
                        int an = Integer.parseInt(etAn.getText().toString());

                        ArrayList<String> facilitati = new ArrayList<>();
                        if (cbBiblioteca.isChecked())
                            facilitati.add(cbBiblioteca.getText().toString());
                        if (cbSalaSport.isChecked())
                            facilitati.add(cbSalaSport.getText().toString());
                        if (cbCamin.isChecked())
                            facilitati.add(cbCamin.getText().toString());
                        if (cbCantina.isChecked())
                            facilitati.add(cbCantina.getText().toString());

                        Facultate facultate = new Facultate(nume, adresa, facilitati, nrStud, an);

                        intent.putExtra(ADD_FACULTATE, facultate);
                        setResult(RESULT_OK, intent);
                        finish();
                    } catch (Exception e) {
                        Log.e("Adaugare", "Eroare adaugare facultate");
                        Toast.makeText(getApplicationContext(), "Eroare adaugare facultate!", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }
}