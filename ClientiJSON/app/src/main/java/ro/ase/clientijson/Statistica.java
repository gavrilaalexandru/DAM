package ro.ase.clientijson;

import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.List;

public class Statistica extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_statistica);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        TextView tv2 = findViewById(R.id.tv2);
        String abonament = "SILVER";
        ClientDB database = ClientDB.getInstance(getApplicationContext());
        List<Client> clientiSpeciali = database.getBileteDAO().clientiCuAnumitAbonament(abonament);
        tv2.setText("Exista " + clientiSpeciali.size() + " clienti cu abonamentul " + abonament);
    }
}