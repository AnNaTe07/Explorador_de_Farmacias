package com.softannate.mapamenu.ui.salir;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.softannate.mapamenu.databinding.FragmentSalirBinding;

public class SalirFragment extends Fragment {
    private FragmentSalirBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        // Inflo el layout del fragmento y creo el binding para acceder a las vistas
        binding = FragmentSalirBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        //Retorno la vista raíz del fragmento
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //muestro el dialogo de confirmación cuando se crea la vista
        mostrarDialogoConfSalir();
    }

    private void mostrarDialogoConfSalir(){
        // Creo un nuevo AlertDialog.Builder para mostrar un diálogo de confirmación
        new AlertDialog.Builder(requireContext()).setTitle("Confirmar salida")// título del diálogo
                .setMessage("¿Está seguro que desea salir?")// mensaje del diálogo
                .setPositiveButton("Salir", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //cerrar app
                        requireActivity().finishAffinity();//cierra la app completamente con finishAffinity
                    }
                }).setNegativeButton("Cancelar",null).show();// Configuro el botón de cancelar sin acción adicional
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;// Limpio el binding cuando la vista del fragmento se destruye
    }
}