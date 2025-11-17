package ro.ase.test_facultate;

import android.app.AlertDialog;
import android.app.ComponentCaller;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class ListaInregistrari extends AppCompatActivity {

    private Intent intent;
    public static final int REQUEST_CODE_ADD = 100;
    public static final int REQUEST_CODE_EDIT = 200;
    public static final String EDIT_FACULTATE = "editFacultate";
    ArrayList<Facultate> facultati = new ArrayList<>();
    private ListView listView;
    int pozitie;

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

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                pozitie = position;
                intent = new Intent(getApplicationContext(), AdaugaInregistrare.class);
                intent.putExtra(EDIT_FACULTATE, facultati.get(position));
                startActivityForResult(intent, REQUEST_CODE_EDIT);
            }
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long id) {
                Facultate facultate = facultati.get(position);
                ArrayAdapter adapter = (ArrayAdapter) listView.getAdapter();
                AlertDialog dialog = new AlertDialog.Builder(ListaInregistrari.this)
                        .setTitle("Confirmare stergere")
                        .setMessage("Doriti sa stergeti?")
                        .setNegativeButton("NU", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Toast.makeText(getApplicationContext(), "Nu am sters nimic", Toast.LENGTH_SHORT).show();
                                dialogInterface.cancel();
                            }
                        })
                        .setPositiveButton("DA", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                facultati.remove(facultate);
                                adapter.notifyDataSetChanged();
                                Toast.makeText(getApplicationContext(), "Am sters " + facultate.toString(), Toast.LENGTH_SHORT).show();
                                dialogInterface.cancel();
                            }
                        }).create();
                dialog.show();

                return true;
            }
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
            Intent intentInregistrare = new Intent(getApplicationContext(), AdaugaInregistrare.class);
            startActivityForResult(intentInregistrare, REQUEST_CODE_ADD);
            return true;
        } else if (item.getItemId() == R.id.optiune2) {
            finishAffinity();
            return true;
        }
        return false;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data, @NonNull ComponentCaller caller) {
        super.onActivityResult(requestCode, resultCode, data, caller);

        if (requestCode == REQUEST_CODE_ADD && resultCode == RESULT_OK && data != null) {
            Facultate facultate = (Facultate) data.getSerializableExtra(AdaugaInregistrare.ADD_FACULTATE);
            if (facultate != null) {
                facultati.add(facultate);
                // ArrayAdapter<Facultate> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, facultati);
                CustomListAdapter adapter = new CustomListAdapter(getApplicationContext(), R.layout.custom_listview, facultati, getLayoutInflater());
                listView.setAdapter(adapter);
            }
        } else if (requestCode == REQUEST_CODE_EDIT && resultCode == RESULT_OK && data != null) {
            Facultate facultate = (Facultate) data.getSerializableExtra(AdaugaInregistrare.ADD_FACULTATE);
            if (facultate != null) {
                facultati.get(pozitie).setNume(facultate.getNume());
                facultati.get(pozitie).setAdresa(facultate.getAdresa());
                facultati.get(pozitie).setAnInfiintare(facultate.getAnInfiintare());
                facultati.get(pozitie).setNrStud(facultate.getNrStud());
                facultati.get(pozitie).setFacilitati(facultate.getFacilitati());

                CustomListAdapter adapter = (CustomListAdapter) listView.getAdapter();
                adapter.notifyDataSetChanged();
            }
        }
    }
}