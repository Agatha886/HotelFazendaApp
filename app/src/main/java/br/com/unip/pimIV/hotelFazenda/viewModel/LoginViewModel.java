package br.com.unip.pimIV.hotelFazenda.viewModel;

import android.text.Editable;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import br.com.unip.pimIV.hotelFazenda.dao.UsuarioDAO;
import br.com.unip.pimIV.hotelFazenda.util.RetornoLogin;


public class LoginViewModel extends ViewModel {

    private final FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    private UsuarioDAO usuarioDAO;

    public LoginViewModel(UsuarioDAO usuarioDAO) {
        this.usuarioDAO = usuarioDAO;
    }

    private final MutableLiveData<RetornoLogin> retorno = new MutableLiveData<>();
    public LiveData<RetornoLogin> retornoLiveDate = retorno;
    public Exception ex = new Exception();


    public void verificaUsuario(Editable email, Editable senha) {

        Task<AuthResult> authResultTaskSinIn = firebaseAuth.signInWithEmailAndPassword(email.toString(), senha.toString());
        authResultTaskSinIn.addOnSuccessListener(new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {
                String uid = authResult.getUser().getUid();
                usuarioDAO.getUser(uid);
                retorno.setValue(RetornoLogin.SUCESSO);
            }
        });

        authResultTaskSinIn.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                retorno.setValue(RetornoLogin.FALHA);
                ex = e;
            }
        });
    }

}



