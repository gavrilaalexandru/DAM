package ro.ase.semdam1_1084;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class AddActivity extends AppCompatActivity {

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

        Spinner spinnerCompanie = findViewById(R.id.spinnerCompanie);
        String[] companii = {"Tarom", "Ryanair", "WizzAir", "HiSky"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getApplicationContext(), androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, companii);
        spinnerCompanie.setAdapter(adapter);

        EditText etDestinatie = findViewById(R.id.editTextDestinatie);
        EditText etDataZbor = findViewById(R.id.editTextData);
        EditText etPret = findViewById(R.id.editTextPret);
        RadioGroup radioGroup = findViewById(R.id.radioGroup);
    }
}