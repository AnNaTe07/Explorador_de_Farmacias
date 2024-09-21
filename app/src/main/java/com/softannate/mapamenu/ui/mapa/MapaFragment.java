package com.softannate.mapamenu.ui.mapa;

import static android.Manifest.permission.ACCESS_COARSE_LOCATION;
import static android.Manifest.permission.ACCESS_FINE_LOCATION;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.SupportMapFragment;
import com.softannate.mapamenu.R;

public class MapaFragment extends Fragment {
    private MapaFragmentViewModel vm;
    private FusedLocationProviderClient fusedLocationClient;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mapa, container, false);
        vm = new ViewModelProvider(this).get(MapaFragmentViewModel.class);
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(requireActivity());

        solicitarPermisos();

        vm.getMMapaActual().observe(getViewLifecycleOwner(), new Observer<MapaFragmentViewModel.MapaActual>() {
            @Override
            public void onChanged(MapaFragmentViewModel.MapaActual mapaActual) {
                ((SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map)).getMapAsync(mapaActual);
            }
        });

        vm.obtenerMapa();
        return view;
    }

    private void solicitarPermisos() {
        requestPermissions(new String[]{ACCESS_FINE_LOCATION}, 1000);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(vm.getMMapaActual().getValue());
    }
}
