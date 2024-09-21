package com.softannate.mapamenu.ui.lista;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.softannate.mapamenu.MainActivity;
import com.softannate.mapamenu.model.Farmacia;

import java.util.ArrayList;

public class ListaViewModel extends AndroidViewModel {

    private  MutableLiveData<ArrayList<Farmacia>> mLista;

    public ListaViewModel(@NonNull Application application) {
        super(application);
    }
    public LiveData<ArrayList<Farmacia>> getMLista(){
        if(mLista == null){
            mLista = new MutableLiveData<>();
        }
        return mLista;
    }

    public void imprimirLista(){
        mLista.setValue(MainActivity.listaFarmacias);
    }

}





