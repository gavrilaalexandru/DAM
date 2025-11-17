package ro.ase.club_fitness;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class CustomAdapter extends ArrayAdapter<SalaFitness> {
    private Context context;
    private int resource;
    private List<SalaFitness> saliFitness;
    private LayoutInflater layoutInflater;

    public CustomAdapter(Context context, int resource, List<SalaFitness> list, LayoutInflater layoutInflater) {
        super(context, resource, list);
        this.context = context;
        this.resource = resource;
        this.saliFitness = list;
        this.layoutInflater = layoutInflater;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = layoutInflater.inflate(resource, parent, false);

        SalaFitness salaFitness = saliFitness.get(position);
        if (salaFitness != null) {
            TextView tvNume = view.findViewById(R.id.tvNume);
            tvNume.setText(salaFitness.getNume());

            TextView tvAdresa = view.findViewById(R.id.tvAdresa);
            tvAdresa.setText(salaFitness.getAdresa());

            TextView tvPret = view.findViewById(R.id.tvPret);
            tvPret.setText(String.valueOf(salaFitness.getPretAbonament()));

            TextView tvCapacitate = view.findViewById(R.id.tvCapacitate);
            tvCapacitate.setText(String.valueOf(salaFitness.getCapacitateMax()));

            TextView tvAparate = view.findViewById(R.id.tvAparate);
            if (salaFitness.getAparate().isEmpty()) tvAparate.setText("Aceasta sala nu are niciun aparat!");
            else tvAparate.setText(salaFitness.getAparate().toString());
        }

        return view;
    }
}
