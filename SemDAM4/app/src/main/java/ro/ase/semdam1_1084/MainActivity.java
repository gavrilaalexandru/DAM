package ro.ase.semdam1_1084;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Intent intent;
    public static final int REQUEST_CODE_ADD = 100;

    List<BiletAvion> listaBilete = new ArrayList<>();

    private ListView listView;

    int poz;

    public static final int REQUEST_CODE_EDIT = 200;

    public static final String EDIT_BILET = "editBilet";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
       /* ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });*/

        FloatingActionButton floatingActionButton = findViewById(R.id.floatingActionButton);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(getApplicationContext(), AddActivity.class);
                // startActivity(intent);
                startActivityForResult(intent, REQUEST_CODE_ADD);
            }
        });

        listView = findViewById(R.id.listView);

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long id) {
                BiletAvion biletAvion = listaBilete.get(position);
                ArrayAdapter adapter = (ArrayAdapter) listView.getAdapter();

                AlertDialog dialog = new AlertDialog.Builder(MainActivity.this).setTitle("Confirmare stergere").setMessage("Doriti sa stergeti?").setNegativeButton("NU", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(getApplicationContext(), "Nu am sters nimic!", Toast.LENGTH_LONG).show();
                        dialogInterface.cancel();
                    }
                }).setPositiveButton("DA", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        listaBilete.remove(biletAvion);
                        adapter.notifyDataSetChanged();
                        Toast.makeText(getApplicationContext(), "Am sters " + biletAvion.toString(), Toast.LENGTH_LONG).show();
                        dialogInterface.cancel();
                    }
                }).create();

                dialog.show();

                return true;
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                poz = position;
                intent = new Intent(getApplicationContext(), AddActivity.class);
                intent.putExtra(EDIT_BILET, listaBilete.get(position));
                startActivityForResult(intent, REQUEST_CODE_EDIT);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE_ADD && resultCode == RESULT_OK && data != null) {
            BiletAvion biletAvion = (BiletAvion) data.getSerializableExtra(AddActivity.ADD_BILET);
            if (biletAvion != null) {
                // Toast.makeText(getApplicationContext(), biletAvion.toString(), Toast.LENGTH_LONG).show();
                listaBilete.add(biletAvion);
                ArrayAdapter<BiletAvion> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listaBilete);
                listView.setAdapter(adapter);
            }
        } else if (requestCode == REQUEST_CODE_EDIT && resultCode == RESULT_OK && data != null) {
            BiletAvion biletAvion = (BiletAvion) data.getSerializableExtra(AddActivity.ADD_BILET);
            if (biletAvion != null) {
                listaBilete.get(poz).setDestinatie(biletAvion.getDestinatie());
                listaBilete.get(poz).setDataZbor(biletAvion.getDataZbor());
                listaBilete.get(poz).setPret(biletAvion.getPret());
                listaBilete.get(poz).setCompanie(biletAvion.getCompanie());
                listaBilete.get(poz).setCategorie_Bilet(biletAvion.getCategorie_Bilet());

                ArrayAdapter adapter = (ArrayAdapter) listView.getAdapter();
                adapter.notifyDataSetChanged();
            }
        }
    }
}
