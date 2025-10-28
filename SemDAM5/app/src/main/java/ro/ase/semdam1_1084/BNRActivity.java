package ro.ase.semdam1_1084;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class BNRActivity extends AppCompatActivity implements View.OnClickListener {

    TextView tv1;
    EditText etEUR, etUSD, etGBP, etXAU;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
         EdgeToEdge.enable(this);
        setContentView(R.layout.activity_bnr); // aici accesam subclasele clasei R (resources) si putem accesa ce este in folderul res
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        /*tv1 = findViewById(R.id.textView2);
        tv1.setText("Text modificat din cod");*/

        Button btnShow = findViewById(R.id.btnShow);
        btnShow.setOnClickListener(this);

        // mai multe butoane, mai multe imprementari
        /*btnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Click pe buton!", Toast.LENGTH_LONG).show();
            }
        });*/


        etEUR = findViewById(R.id.editTextEUR);
        etUSD = findViewById(R.id.editTextUSD);
        etGBP = findViewById(R.id.editTextGBP);
        etXAU = findViewById(R.id.editTextXAU);
    }

    // daca avem doar un buton ( o singura implementare)
    @Override
    public void onClick(View view) {
        etEUR.setText("5.23");
        etUSD.setText("4.34");
        etGBP.setText("6.58");
        etXAU.setText("524.56");
    }
}