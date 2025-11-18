package ro.ase.restaurant;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Adauga extends AppCompatActivity {
    public static final String ADD_RESTAURANT = "addRestaurant";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_adauga);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Intent intent = getIntent();
        EditText etNume = findViewById(R.id.etNume);
        EditText etAdresa = findViewById(R.id.etAdresa);
        EditText etCapacitate = findViewById(R.id.etCapacitate);
        EditText etNrProd = findViewById(R.id.etNrProduse);
        RadioGroup radioGroup = findViewById(R.id.radioGroup);
        Button btnSalveaza = findViewById(R.id.btnSalveaza);

        btnSalveaza.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (etNume.getText().toString().isEmpty())
                    etNume.setError("Nu lasa campul gol!");
                else if (etAdresa.getText().toString().isEmpty())
                    etAdresa.setError("Nu lasa campul gol!");
                else if (etCapacitate.getText().toString().isEmpty())
                    etCapacitate.setError("Nu lasa campul gol");
                else if (etNrProd.getText().toString().isEmpty())
                    etNrProd.setError("Nu lasa campul gol!");
                else {
                    try {
                        String nume = etNume.getText().toString();
                        String adresa = etAdresa.getText().toString();
                        int capacitate = Integer.parseInt(etCapacitate.getText().toString());
                        int nrProd = Integer.parseInt(etNrProd.getText().toString());
                        RadioButton radioButton = findViewById(radioGroup.getCheckedRadioButtonId());
                        String categorie = radioButton.getText().toString();

                        Restaurant restaurant = new Restaurant(adresa, capacitate, nume, categorie, nrProd);
                        intent.putExtra(ADD_RESTAURANT, restaurant);
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