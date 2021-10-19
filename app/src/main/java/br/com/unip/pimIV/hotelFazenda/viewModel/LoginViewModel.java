package br.com.unip.pimIV.hotelFazenda.viewModel;

import android.text.Editable;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class LoginViewModel extends ViewModel {

    public LoginViewModel() {

    }
    private FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

    public void verificaUsuario(Editable email, Editable senha, OnSuccessListener onSuccessListener, OnFailureListener onFailureListener) {
        Task<AuthResult> authResultTaskSinIn = firebaseAuth.signInWithEmailAndPassword(email.toString(), senha.toString());
        authResultTaskSinIn.addOnSuccessListener(onSuccessListener);

        authResultTaskSinIn.addOnFailureListener(onFailureListener) ;
    }
}


