package br.com.unip.pimIV.hotelFazenda.viewModel;

import androidx.lifecycle.ViewModel;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import br.com.unip.pimIV.hotelFazenda.dao.UsuarioDAO;


public class FormularioCadastroViewModel extends ViewModel {

    private final FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    private final UsuarioDAO usuarioDAO = new UsuarioDAO();

    public void cadastroRealizado(String cpf, String email, String nome, String senha, String telefone, OnFailureListener onFailureListener, OnSuccessListener<AuthResult> onSuccessListener) {


        Task<AuthResult> userWithEmailAndPassword = firebaseAuth.createUserWithEmailAndPassword(email, senha);
        userWithEmailAndPassword.addOnSuccessListener(new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {
                String id = authResult.getUser().getUid();
                usuarioDAO.adicionaUsuario(id,cpf, email, nome, telefone, onFailureListener, onSuccessListener);
            }
        });
        userWithEmailAndPassword.addOnFailureListener(onFailureListener);
    }

}
