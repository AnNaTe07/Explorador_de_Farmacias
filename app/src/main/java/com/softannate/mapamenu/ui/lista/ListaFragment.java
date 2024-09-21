package com.softannate.mapamenu.ui.lista;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;

import com.softannate.mapamenu.databinding.FragmentListaBinding;
import com.softannate.mapamenu.model.Farmacia;

import java.util.ArrayList;

public class ListaFragment extends Fragment {

    private FragmentListaBinding binding;
    private ListaViewModel vm;


    public static ListaFragment newInstance() {
        return new ListaFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        vm = new ViewModelProvider(this).get(ListaViewModel.class);
        binding = FragmentListaBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        vm.getMLista().observe(getViewLifecycleOwner(), new Observer<ArrayList<Farmacia>>() {
            @Override
            public void onChanged(ArrayList<Farmacia> farmacias) {
               AdapterFarmacia adapter = new AdapterFarmacia(farmacias, inflater);
                GridLayoutManager grid = new GridLayoutManager(getContext(), 1, GridLayoutManager.VERTICAL, false);
                binding.listaFarmacias.setAdapter(adapter);
                binding.listaFarmacias.setLayoutManager(grid);
            }
        });
        vm.imprimirLista();
        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        vm = new ViewModelProvider(this).get(ListaViewModel.class);

    }

}