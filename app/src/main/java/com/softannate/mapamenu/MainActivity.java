package com.softannate.mapamenu;

import android.os.Bundle;
import android.view.Menu;

import com.google.android.material.navigation.NavigationView;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import com.softannate.mapamenu.databinding.ActivityMainBinding;
import com.softannate.mapamenu.model.Farmacia;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMainBinding binding;
    public static ArrayList<Farmacia> listaFarmacias = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        listaFarmacias.add(new Farmacia("Farmacia los Alamos", "Pringles 911", R.drawable.farmacia_los_alamos, "de 19hs a 21hs"));
        listaFarmacias.add(new Farmacia("Farmacity", "Junin 952", R.drawable.farmacity2, "de 8hs a 20hs"));
        listaFarmacias.add(new Farmacia("Farmacia Los Fresnos", "Barrio Fresnoa Americanos manzana 67 casa 18", R.drawable.farmacia_los_fresnos, "de 11hs a 24hs"));
        listaFarmacias.add(new Farmacia("Farmacia del Bosque", "Salvador segado 800", R.drawable.farmacia_del_bosque, "de 10ha a 20hs"));
        listaFarmacias.add(new Farmacia("Farmacity", "Rivadavia 602", R.drawable.farmacity, "de 9hs a 22hs"));


        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.appBarMain.toolbar);
        //    binding.appBarMain.fab.setOnClickListener(new View.OnClickListener() {
        //       @Override
        //       public void onClick(View view) {
        //            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
        //                    .setAction("Action", null)
        //                    .setAnchorView(R.id.fab).show();
        //        }
        //    });
        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                 R.id.nav_lista, R.id.nav_salir, R.id.nav_mapa)
                .setOpenableLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        //para sacar el color que trae por defecto cada item del menú
        navigationView.setItemIconTintList(null);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
}