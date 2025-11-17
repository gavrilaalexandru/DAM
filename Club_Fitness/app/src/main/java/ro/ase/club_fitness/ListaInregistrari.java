package ro.ase.club_fitness;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class ListaInregistrari extends AppCompatActivity {
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_lista_inregistrari);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        listView = findViewById(R.id.listView);
        ArrayList<SalaFitness> salaFitnessList = (ArrayList<SalaFitness>) getIntent().getSerializableExtra(MainActivity.LISTA_SALI_COMPLETA);

        if (salaFitnessList != null) {
            // ArrayAdapter<SalaFitness> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, salaFitnessList);
            CustomAdapter adapter = new CustomAdapter(getApplicationContext(), R.layout.custom_list_view, salaFitnessList, getLayoutInflater());
            listView.setAdapter(adapter);
        }

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int pos, long id) {
                SalaFitness salaFitness = salaFitnessList.get(pos);
                CustomAdapter adapter = (CustomAdapter) listView.getAdapter();
                AlertDialog alertDialog = new AlertDialog.Builder(ListaInregistrari.this)
                        .setTitle("Stergere sala fitness")
                        .setMessage("Doriti sa stergeti sala?")
                        .setNegativeButton("NU", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Toast.makeText(getApplicationContext(), "Nu s-a sters nimic!", Toast.LENGTH_SHORT).show();
                                dialogInterface.cancel();
                            }
                        })
                        .setPositiveButton("DA", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                salaFitnessList.remove(salaFitness);
                                adapter.notifyDataSetChanged();
                                Toast.makeText(getApplicationContext(), "Am sters " + salaFitness.toString(), Toast.LENGTH_SHORT).show();
                                dialogInterface.cancel();
                            }
                        })
                        .create();
                alertDialog.show();

                return true;
            }
        });
    }
}