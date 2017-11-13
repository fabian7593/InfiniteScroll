package github.frosquivel.infinitescrollapp.Activities;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import github.frosquivel.infinitescrollapp.Models.Country;
import github.frosquivel.infinitescrollapp.R;

/**
 * Created by Fabian on 06/11/2017.
 * The google maps class for shown the position of the selected country
 */

public class GoogleMapsActivity  extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private Country country;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        country = (Country) getIntent().getSerializableExtra("Country");
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
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng currentCountry = new LatLng(Double.parseDouble(country.getLatitude()), Double.parseDouble(country.getLongitude()));
        mMap.addMarker(
                new MarkerOptions()
                        .position(currentCountry)
                        .title(country.getName())
                );

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(currentCountry,5));
    }
}
