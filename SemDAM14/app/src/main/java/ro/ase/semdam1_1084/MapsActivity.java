package ro.ase.semdam1_1084;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import ro.ase.semdam1_1084.databinding.ActivityMapsBinding;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ActivityMapsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        mMap = googleMap;

        mMap.setMinZoomPreference(14);
        mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);

        LatLng ASE = new LatLng(44.4474931324284, 26.09794210647024);
        mMap.addMarker(new MarkerOptions().position(ASE).title("Marker la ASE"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(ASE));

        LatLng ateneu = new LatLng(44.44144897160384, 26.097328526237657);
        mMap.addMarker(new MarkerOptions().position(ateneu).title("Marker la Ateneu"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(ateneu));

        PolylineOptions plo = new PolylineOptions();
        plo.add(ASE);
        plo.add(ateneu);
        plo.color(Color.RED);
        plo.width(20);
        mMap.addPolyline(plo);

        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(ASE).title("Academia de Studii Economice")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN));
        Marker m = mMap.addMarker(markerOptions);
        m.showInfoWindow();

        LatLng unirii = new LatLng(44.42729087085585, 26.10243749740146);
        mMap.addMarker(new MarkerOptions().position(unirii).title("Marker la Unirii"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(unirii));

        MarkerOptions markerOptions2 = new MarkerOptions();
        markerOptions2.position(unirii).title("Piata Unirii")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE));
        Marker m2 = mMap.addMarker(markerOptions2);
        m2.showInfoWindow();

        PolylineOptions plo2 = new PolylineOptions();
        plo2.add(ASE);
        plo2.add(unirii);
        plo2.color(Color.BLUE);
        plo2.width(20);
        mMap.addPolyline(plo2);

        PolylineOptions plo3 = new PolylineOptions();
        plo3.add(ateneu);
        plo3.add(unirii);
        plo3.color(Color.GREEN);
        plo3.width(20);
        mMap.addPolyline(plo3);

        if (ActivityCompat.checkSelfPermission(getApplicationContext(),
                Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(getApplicationContext(),
                Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(MapsActivity.this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION}, 0);
        }

        mMap.setMyLocationEnabled(true);
        mMap.getUiSettings().setMyLocationButtonEnabled(true);
    }
}