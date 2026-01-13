package ro.ase.semdam1_1084;

import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BarChartActivity extends AppCompatActivity {

    ArrayList<BiletAvion> list;
    LinearLayout layout;
    Map<String, Integer> source;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_bar_chart);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Intent intent = getIntent();

        list = (ArrayList<BiletAvion>) intent.getSerializableExtra("list");

        source = getSource2(list);

        layout = findViewById(R.id.layoutBar);
        layout.addView(new BarChartView(getApplicationContext(), source));

    }

    private Map<String, Integer> getSource(List<BiletAvion> biletAvionList) {
        if (biletAvionList == null || biletAvionList.isEmpty())
            return new HashMap<>();
        else {
            Map<String, Integer> results = new HashMap<>();
            for (BiletAvion biletAvion : list)
                if (results.containsKey(biletAvion.getCategorie_Bilet()))
                    results.put(biletAvion.getCategorie_Bilet(), results.get(biletAvion.getCategorie_Bilet()) + 1);
                else
                    results.put(biletAvion.getCategorie_Bilet(), 1);
            return results;
        }
    }

    private Map<String, Integer> getSource2(List<BiletAvion> biletAvionList) {
        if (biletAvionList == null || biletAvionList.isEmpty())
            return new HashMap<>();
        else {
            Map<String, Integer> results = new HashMap<>();
            for (BiletAvion biletAvion : list)
                if (results.containsKey(biletAvion.getCompanie()))
                    results.put(biletAvion.getCompanie(), results.get(biletAvion.getCompanie()) + 1);
                else
                    results.put(biletAvion.getCompanie(), 1);
            return results;
        }
    }
}