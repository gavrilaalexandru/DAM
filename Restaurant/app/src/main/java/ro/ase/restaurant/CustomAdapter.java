package ro.ase.restaurant;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class CustomAdapter extends ArrayAdapter<Restaurant> {
    private Context context;
    private int resource;
    private List<Restaurant> list;
    private LayoutInflater layoutInflater;

    public CustomAdapter(Context context, int resource, List<Restaurant> list, LayoutInflater layoutInflater) {
        super(context, resource, list);
        this.context = context;
        this.resource = resource;
        this.list = list;
        this.layoutInflater = layoutInflater;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = layoutInflater.inflate(resource, parent, false);

        Restaurant restaurant = list.get(position);
        if (restaurant != null) {
            TextView tvNume = view.findViewById(R.id.tvNume);
            tvNume.setText(restaurant.getName());
            TextView tvSpecific = view.findViewById(R.id.tvSpecific);
            tvSpecific.setText(restaurant.getSpecific());
        }

        return view;
    }
}
