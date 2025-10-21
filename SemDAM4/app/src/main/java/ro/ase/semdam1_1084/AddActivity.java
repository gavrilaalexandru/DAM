package ro.ase.semdam1_1084;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class AddActivity extends AppCompatActivity {

    public static final String ADD_BILET = "addBilet";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add);
        /*ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });*/

        Intent intent = getIntent();

        Spinner spinnerCompanie = findViewById(R.id.spinnerCompanie);
        String[] companii = {"Tarom", "Ryanair", "WizzAir", "HiSky"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getApplicationContext(), androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, companii);
        spinnerCompanie.setAdapter(adapter);

        EditText etDestinatie = findViewById(R.id.editTextDestinatie);
        EditText etDataZbor = findViewById(R.id.editTextData);
        EditText etPret = findViewById(R.id.editTextPret);
        RadioGroup radioGroup = findViewById(R.id.radioGroup);
        Button btnAdauga = findViewById(R.id.btnCreate);

        if (intent.hasExtra(MainActivity.EDIT_BILET)) {
            BiletAvion biletAvion = (BiletAvion) intent.getSerializableExtra(MainActivity.EDIT_BILET);

            if (biletAvion != null) {
                etDestinatie.setText(biletAvion.getDestinatie());
                etDataZbor.setText(new SimpleDateFormat("MM/dd/yyyy", Locale.US).format(biletAvion.getDataZbor()));
                etPret.setText(String.valueOf(biletAvion.getPret())); // sau cu concatenare cu + string vid

                ArrayAdapter<String> adapter1 = (ArrayAdapter<String>) spinnerCompanie.getAdapter();
                for (int i = 0; i < adapter1.getCount(); i++) {
                    if (adapter1.getItem(i).equals(biletAvion.getCompanie())) {
                        spinnerCompanie.setSelection(i);
                        break;
                    }
                }

                if (biletAvion.getCategorie_Bilet().equals("ECONOMY"))
                    radioGroup.check(R.id.radioButtonEconomy);
                else if (biletAvion.getCategorie_Bilet().equals("BUSINESS"))
                    radioGroup.check(R.id.radioButtonBusiness);
            }
            btnAdauga.setText("Editare bilet");
        }

        btnAdauga.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (etDestinatie.getText().toString().isEmpty())
                    etDestinatie.setError("Introduceti destinatia!");
                else if (etDataZbor.getText().toString().isEmpty())
                    etDataZbor.setError("Introduceti data zborului!");
                else if (etPret.getText().toString().isEmpty())
                    etPret.setError("Introduceti pretul!");
                else {
                    SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy", Locale.US);
                    try {
                        sdf.parse(etDataZbor.getText().toString());
                        Date dataZbor = new Date(etDataZbor.getText().toString());
                        String destinatie = etDestinatie.getText().toString();
                        float pret = Float.parseFloat(etPret.getText().toString());
                        String companie = spinnerCompanie.getSelectedItem().toString();
                        RadioButton radioButton = findViewById(radioGroup.getCheckedRadioButtonId());
                        String categorieBilet = radioButton.getText().toString();

                        BiletAvion biletAvion = new BiletAvion(destinatie, dataZbor, pret, companie, categorieBilet);
                        // Toast.makeText(getApplicationContext(), biletAvion.toString(), Toast.LENGTH_LONG).show();

                        intent.putExtra(ADD_BILET, biletAvion);
                        setResult(RESULT_OK, intent);
                        finish();

                    } catch (ParseException e) {
                        throw new RuntimeException(e);
                    } catch (Exception ex) {
                        Log.e("AddActivity", "Eroare introducere date");
                        Toast.makeText(getApplicationContext(), "Eroare introducere date!", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }
}