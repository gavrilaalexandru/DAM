package ro.ase.test_facultate;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class CustomListAdapter extends ArrayAdapter<Facultate> {
    private Context context;
    private int resource;
    private List<Facultate> facultateList;
    private LayoutInflater layoutInflater;

    public CustomListAdapter(Context context, int resource, List<Facultate> list, LayoutInflater layoutInflater) {
        super(context, resource, list);
        this.context = context;
        this.resource = resource;
        this.facultateList = list;
        this.layoutInflater = layoutInflater;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = layoutInflater.inflate(resource, parent, false);

        Facultate facultate = facultateList.get(position);
        if (facultate != null) {
            TextView tvNume = view.findViewById(R.id.tvNume);
            tvNume.setText(facultate.getNume());

            TextView tvAdresa = view.findViewById(R.id.tvAdresa);
            tvAdresa.setText(facultate.getAdresa());

            TextView tvAn = view.findViewById(R.id.tvAn);
            tvAn.setText(String.valueOf(facultate.getNume()));

            TextView tvNrStud = view.findViewById(R.id.tvNrStud);
            tvNrStud.setText(String.valueOf(facultate.getNrStud()));

            TextView tvFacilitati = view.findViewById(R.id.tvFacilitati);
            if (facultate.getFacilitati().isEmpty()) tvFacilitati.setText("Aceasta facultate nu are facilitati");
            else tvFacilitati.setText(facultate.getFacilitati().toString());
        }

        return view;
    }
}
