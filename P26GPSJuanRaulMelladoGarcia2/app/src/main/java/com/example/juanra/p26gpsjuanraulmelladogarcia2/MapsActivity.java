package com.example.juanra.p26gpsjuanraulmelladogarcia2;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    //El LocationListener obtiene nuestra posicion y el LocationManager maneja esa posicion obtenida

    private LocationManager locationmanager;
    private LocationListener locationlistener;

    //Tiempo minimo de actualizacion de la localizacion en ms (1000=1s)

    private final long MIN_TIME=0;

    //Distancia minima de actualizacion de la localizacion en metors

    private final long MIN_DIST=0;

    //Objeto que guarda la latitud y la longitud posteriormente se transformará en un punto en el mapa

    private LatLng latlng;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        if(ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)==PackageManager.PERMISSION_DENIED||ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)==PackageManager.PERMISSION_DENIED){
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.ACCESS_COARSE_LOCATION}, 1);
        }
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
        LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));

        localizarGPS();
    }

    public void localizarGPS(){
        locationmanager=(LocationManager) MapsActivity.this.getSystemService(Context.LOCATION_SERVICE);
        locationlistener=new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                latlng = new LatLng(location.getLatitude(), location.getLongitude());
                if(latlng!=null){
                    mMap.clear();
                }
                mMap.addMarker(new MarkerOptions().position(latlng).title("Mi posicion"));
                mMap.moveCamera(CameraUpdateFactory.newLatLng(latlng));
            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }

            @Override
            public void onProviderEnabled(String provider) {

            }

            @Override
            public void onProviderDisabled(String provider) {

            }
        };
        //Solicitamos que se actualice frecuentemente, pasandole el servicio de GPS que utilizamos (GPS_PROVIDER), el tiempo minimo de actualizacion (MIN_TIME), la distancia mínima de actualizacion (MIN_DIST) y el listener que nos coge/encuentra la localizacion
        try {
            locationmanager.requestLocationUpdates(LocationManager.GPS_PROVIDER, MIN_TIME, MIN_DIST, locationlistener);
        }catch (SecurityException e){
            Toast.makeText(this, "Falló", Toast.LENGTH_SHORT).show();
        }
    }
}
