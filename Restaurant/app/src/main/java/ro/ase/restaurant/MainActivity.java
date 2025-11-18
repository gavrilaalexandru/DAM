package ro.ase.restaurant;

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

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private Intent intent;
    private ArrayList<Restaurant> restaurantArrayList = new ArrayList<>();
    public static final int REQUEST_CODE_ADD = 100;
    public static final String LISTA_RESTAURANTE = "listaRestaurant";

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

        Button btnAdauga = findViewById(R.id.btnAdauga);
        Button btnVizualizeaza = findViewById(R.id.btnVizualizare);

        btnAdauga.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(getApplicationContext(), Adauga.class);
                startActivityForResult(intent, REQUEST_CODE_ADD);
            }
        });
        btnVizualizeaza.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(getApplicationContext(), Vizualizeaza.class);
                intent.putExtra(LISTA_RESTAURANTE, restaurantArrayList);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data, @NonNull ComponentCaller caller) {
        super.onActivityResult(requestCode, resultCode, data, caller);

        if (requestCode == REQUEST_CODE_ADD && resultCode == RESULT_OK && data != null) {
            Restaurant restaurant = (Restaurant) data.getSerializableExtra(Adauga.ADD_RESTAURANT);
            if (restaurant != null) {
                restaurantArrayList.add(restaurant);
            }
        }
    }
}