package ro.ase.semdam1_1084;

import android.app.ProgressDialog;
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
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
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
         EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

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

                        BiletAvionDB database = BiletAvionDB.getInstance(getApplicationContext());
                        database.getBileteDAO().delete(biletAvion);


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
    protected void onStart() {
        super.onStart();

        BiletAvionDB database = BiletAvionDB.getInstance(getApplicationContext());
        // listaBilete = database.getBileteDAO().getAll();
        listaBilete = database.getBileteDAO().getBileteByCompanie("Tarom");

        CustomAdapter adapter = new CustomAdapter(getApplicationContext(), R.layout.elem_listview, listaBilete, getLayoutInflater());
        listView.setAdapter(adapter);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE_ADD && resultCode == RESULT_OK && data != null) {
            BiletAvion biletAvion = (BiletAvion) data.getSerializableExtra(AddActivity.ADD_BILET);
            if (biletAvion != null) {
                // Toast.makeText(getApplicationContext(), biletAvion.toString(), Toast.LENGTH_LONG).show();
                listaBilete.add(biletAvion);

                BiletAvionDB database = BiletAvionDB.getInstance(getApplicationContext());
                database.getBileteDAO().insert(biletAvion);
//                ArrayAdapter<BiletAvion> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listaBilete);
                CustomAdapter adapter = new CustomAdapter(getApplicationContext(), R.layout.elem_listview, listaBilete, getLayoutInflater());
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

                BiletAvionDB database = BiletAvionDB.getInstance(getApplicationContext());
                database.getBileteDAO().update(listaBilete.get(poz));

                // ArrayAdapter adapter = (ArrayAdapter) listView.getAdapter();
                CustomAdapter adapter = (CustomAdapter) listView.getAdapter();
                adapter.notifyDataSetChanged();
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.meniu_principal, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.optiune1) {
            Intent intent1 = new Intent(getApplicationContext(), BNRActivity.class);
            startActivity(intent1);
            return true;
        } else if (item.getItemId() == R.id.optiune2) {
            ExtractXML extractXML = new ExtractXML() {
                @Override
                protected void onPostExecute(InputStream inputStream) {

                    listaBilete.addAll(this.biletAvionList);

                    BiletAvionDB database = BiletAvionDB.getInstance(getApplicationContext());
                    database.getBileteDAO().insert(biletAvionList);

                    CustomAdapter adapter = new CustomAdapter(getApplicationContext(), R.layout.elem_listview, listaBilete, getLayoutInflater());
                    listView.setAdapter(adapter);
                }
            };
            try {
                extractXML.execute(new URL("https://pastebin.com/raw/q6qfdGyF"));
            } catch (MalformedURLException e) {
                throw new RuntimeException(e);
            }
            return true;
        } else if (item.getItemId() == R.id.optiune3) {
            ExtractJSON extractJSON = new ExtractJSON() {
                ProgressDialog progressDialog;

                @Override
                protected void onPreExecute() {
                    progressDialog = new ProgressDialog(MainActivity.this);
                    progressDialog.setMessage("Please wait...");
                    progressDialog.show();
                }

                @Override
                protected void onPostExecute(String s) {
                    progressDialog.cancel();
                    listaBilete.addAll(this.biletAvionListJSON);
                    BiletAvionDB database = BiletAvionDB.getInstance(getApplicationContext());
                    database.getBileteDAO().insert(biletAvionListJSON);
                    CustomAdapter adapter = new CustomAdapter(getApplicationContext(), R.layout.elem_listview, listaBilete, getLayoutInflater());
                    listView.setAdapter(adapter);
                }
            };
            try {
                extractJSON.execute(new URL("https://pastebin.com/raw/zxR0U2mC"));
            } catch (MalformedURLException e) {
                throw new RuntimeException(e);
            }
            return true;
        } else return item.getItemId() == R.id.optiune4;
    }
}
