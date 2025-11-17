package ro.ase.club_fitness;

import android.app.ComponentCaller;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.Serializable;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private Intent intent;
    public static final int REQUEST_CODE_ADD = 100;
    public static final String LISTA_SALI_COMPLETA = "listaSaliCompleta";
    private ArrayList<SalaFitness> salaFitnessList = new ArrayList<>();

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

        Button btnAdauga = findViewById(R.id.btnInregistrare);
        Button btnVizualizare = findViewById(R.id.btnLista);
        btnAdauga.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(getApplicationContext(), AdaugareInregistrare.class);
                startActivityForResult(intent, REQUEST_CODE_ADD);
            }
        });
        btnVizualizare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(getApplicationContext(), ListaInregistrari.class);
                intent.putExtra(LISTA_SALI_COMPLETA, salaFitnessList);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data, @NonNull ComponentCaller caller) {
        super.onActivityResult(requestCode, resultCode, data, caller);

        if (requestCode == REQUEST_CODE_ADD && resultCode == RESULT_OK && data != null) {
            SalaFitness salaFitness = (SalaFitness) data.getSerializableExtra(AdaugareInregistrare.ADD_SALA);
            if (salaFitness != null) {
                salaFitnessList.add(salaFitness);
            }
        }
    }
}