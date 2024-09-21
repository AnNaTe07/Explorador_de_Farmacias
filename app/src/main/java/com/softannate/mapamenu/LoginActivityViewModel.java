package com.softannate.mapamenu;

import android.app.Application;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;


        public class LoginActivityViewModel extends AndroidViewModel {
            private MutableLiveData<String> mUsuario;

            public LoginActivityViewModel(@NonNull Application application) {
                super(application);
            }

            public LiveData<String> getMUsuario(){
                if(mUsuario == null){
                    mUsuario = new MutableLiveData<>();
                }
                return mUsuario;
            }

            public void verificarIngreso(String usuario, String password){
                if(usuario.equalsIgnoreCase("admin") && password.equals("123")){
                    //mUsuario.setValue("correcto");
                    Intent intent = new Intent(getApplication(), MainActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    getApplication().startActivity(intent);
                }else{
                    mUsuario.setValue("Usuario y/o contraseña incorrectos");
                }
            }
        }