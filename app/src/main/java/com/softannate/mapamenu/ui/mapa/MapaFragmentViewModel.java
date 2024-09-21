package com.softannate.mapamenu.ui.mapa;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapaFragmentViewModel extends AndroidViewModel {
    private MutableLiveData<MapaActual> mMapaActual;
    private FusedLocationProviderClient fusedLocationClient;

    public MapaFragmentViewModel(@NonNull Application application) {
        super(application);
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(application);
    }

    public LiveData<MapaActual> getMMapaActual() {
        if (mMapaActual == null) {
            mMapaActual = new MutableLiveData<>();
        }
        return mMapaActual;
    }

    public void obtenerMapa() {
        MapaActual mapaActual = new MapaActual();
        mMapaActual.setValue(mapaActual);
    }

    public class MapaActual implements OnMapReadyCallback {
        LatLng farmacity2 = new LatLng(-33.3014405, -66.3371776);
        LatLng farmacia_los_fresnos = new LatLng(-33.3183132, -66.3220369);
        LatLng farmacity = new LatLng(-33.3039796, -66.3356117);
        LatLng farmacia_del_bosque = new LatLng(-33.3163269, -66.3279237);
        LatLng farmacia_los_alamos= new LatLng(-33.3026272, -66.3362293);

        @Override
        public void onMapReady(@NonNull GoogleMap googleMap) {
            googleMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
            googleMap.addMarker(new MarkerOptions().position(farmacity2).title("Farmacity"));
            googleMap.addMarker(new MarkerOptions().position(farmacia_los_alamos).title("Farmacia los Alamos"));
            googleMap.addMarker(new MarkerOptions().position(farmacia_los_fresnos).title("Farmacia Los Fresnos").snippet("De turno"));
            googleMap.addMarker(new MarkerOptions().position(farmacia_del_bosque).title("Farmacia del Bosque").snippet("De turno"));
            googleMap.addMarker(new MarkerOptions().position(farmacity).title("Farmacity").snippet("Hoy Ofertas"));

            mostrarUbicacion(googleMap);
        }

        private void mostrarUbicacion(GoogleMap googleMap) {
            googleMap.setMyLocationEnabled(true);

            fusedLocationClient.getLastLocation()
                    .addOnSuccessListener(location -> {
                        LatLng userLocation = new LatLng(location.getLatitude(), location.getLongitude());
                        googleMap.addMarker(new MarkerOptions()
                                .position(userLocation)
                                .title("Estoy aquí!!")
                                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)));

                        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(userLocation, 15));
                    });
        }
    }
}
