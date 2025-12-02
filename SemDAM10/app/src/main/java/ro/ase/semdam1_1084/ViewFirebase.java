package ro.ase.semdam1_1084;

import static ro.ase.semdam1_1084.MainActivity.EDIT_BILET;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ViewFirebase extends AppCompatActivity {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ListView listView = new ListView(this);

        List<BiletAvion> biletAvionList = new ArrayList<>();

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("semdam1084-alex-default-rtdb");

        ValueEventListener listener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    biletAvionList.clear();
                    for (DataSnapshot dn: snapshot.getChildren()) {
                        BiletAvion biletAvion = dn.getValue(BiletAvion.class);
                        biletAvionList.add(biletAvion);
                    }
                }
                ArrayAdapter<BiletAvion> adapter = new ArrayAdapter<>(getApplicationContext(),
                        android.R.layout.simple_list_item_1,
                        biletAvionList);
                listView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };
        myRef.child("semdam1084-alex-default-rtdb").addValueEventListener(listener);

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int pos, long id) {

                BiletAvion biletAvion = biletAvionList.get(pos);
                biletAvionList.remove(biletAvion);

                myRef.child("semdam1084-alex-default-rtdb").child(biletAvion.getUid()).removeValue();

                ArrayAdapter<BiletAvion> adapter = new ArrayAdapter<>(getApplicationContext(),
                        android.R.layout.simple_list_item_1,
                        biletAvionList);
                listView.setAdapter(adapter);

                return true;
            }
        });

        listView.setOnItemClickListener((adapterView, view, pos, id) -> {

            BiletAvion bilet = biletAvionList.get(pos);

            myRef.child("semdam1084-alex-default-rtdb")
                    .child(bilet.getUid())
                    .addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                            String newValue = "TEST_VALUE";

                            dataSnapshot.getRef()
                                    .child("destinatie")
                                    .setValue(newValue);

                            bilet.setDestinatie(newValue);

                            ArrayAdapter<BiletAvion> adapter = (ArrayAdapter<BiletAvion>) listView.getAdapter();
                            adapter.notifyDataSetChanged();
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
        });


        HorizontalScrollView sv = new HorizontalScrollView(this);
        LinearLayout ll = new LinearLayout(this);
        ll.setOrientation(LinearLayout.VERTICAL);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);

        params.setMargins(0, 300, 0, 0);


        TextView textView = new TextView(this);
        textView.setText("Lista bilete din Firebase\n");

        ll.addView(textView);
        ll.addView(listView);
        sv.addView(ll);
        sv.setLayoutParams(params);

        setContentView(sv);
    }
}
