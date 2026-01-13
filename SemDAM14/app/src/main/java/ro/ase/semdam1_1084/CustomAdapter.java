package ro.ase.semdam1_1084;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class CustomAdapter extends ArrayAdapter<BiletAvion> {

    private Context context;
    private int resource; // id de resursa
    private List<BiletAvion> biletAvionList;
    private LayoutInflater layoutInflater;

    public CustomAdapter(@NonNull Context context, int resource, List<BiletAvion> list, LayoutInflater layoutInflater) {
        super(context, resource, list);
        this.context = context;
        this.resource = resource;
        this.biletAvionList = list;
        this.layoutInflater = layoutInflater;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View view = layoutInflater.inflate(resource, parent, false);

        BiletAvion biletAvion = biletAvionList.get(position);
        if (biletAvion != null) {
            TextView tvDestinatie = view.findViewById(R.id.tvDestinatie);
            tvDestinatie.setText(biletAvion.getDestinatie());

            TextView tvDataZbor = view.findViewById(R.id.tvDataZbor);
            tvDataZbor.setText(biletAvion.getDataZbor().toString());

            TextView tvPret = view.findViewById(R.id.tvPret);
            tvPret.setText(String.valueOf(biletAvion.getPret()));

            TextView tvCompanie = view.findViewById(R.id.tvCompanie);
            tvCompanie.setText(biletAvion.getCompanie());

            TextView tvCategorieBilet = view.findViewById(R.id.tvCategorieBilet);
            tvCategorieBilet.setText(biletAvion.getCategorie_Bilet());
        }

        return view;
    }
}
