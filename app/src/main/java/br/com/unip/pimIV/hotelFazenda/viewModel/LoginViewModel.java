package br.com.unip.pimIV.hotelFazenda.viewModel;

import android.text.Editable;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import br.com.unip.pimIV.hotelFazenda.dao.UsuarioDAO;
import br.com.unip.pimIV.hotelFazenda.model.Usuario;
import br.com.unip.pimIV.hotelFazenda.util.MandaUsuario;


public class LoginViewModel extends ViewModel {

    private final FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    private UsuarioDAO usuarioDAO;

    public LoginViewModel(UsuarioDAO usuarioDAO) {
        this.usuarioDAO = usuarioDAO;
    }

    public void getUsuario(String id, MandaUsuario mandaUsuario) {
        try {
            usuarioDAO.getUser(id, mandaUsuario);
        } catch (Exception e) {
            Log.e("ERRO USUARIO", "getUsuario: ", e);
            mandaUsuario.falha(e);
        }
    }

    public void verificaUsuario(Editable email, Editable senha, MandaUsuario mandaUsuario ) {
        Task<AuthResult> authResultTaskSinIn = firebaseAuth.signInWithEmailAndPassword(email.toString(), senha.toString());
        authResultTaskSinIn.addOnSuccessListener(new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {
                String uid = authResult.getUser().getUid();
                getUsuario(uid, mandaUsuario);
            }
        });
        authResultTaskSinIn.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                mandaUsuario.falha(e);
            }
        });
    }

}



