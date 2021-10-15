package br.com.unip.pimIV.hotelFazenda.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseNetworkException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


import java.util.List;

import br.com.unip.pimIV.hotelFazenda.R;
import br.com.unip.pimIV.hotelFazenda.dao.UsuarioDAO;
import br.com.unip.pimIV.hotelFazenda.model.Usuario;

public class LoginActivity extends AppCompatActivity {
    private Usuario usuarioLogado;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        firebaseAuth = FirebaseAuth.getInstance();

        List<Usuario> list = UsuarioDAO.listaUsuarios;

        TextInputLayout textInputEmail = findViewById(R.id.login_campo_email);
        EditText editTextEmail = textInputEmail.getEditText();
        assert editTextEmail != null;
        Editable email = editTextEmail.getText();

        TextInputLayout inputTextSenha = findViewById(R.id.login_campo_senha);
        EditText editTextSenha = inputTextSenha.getEditText();
        assert editTextSenha != null;
        Editable senha = editTextSenha.getText();

        Button btnLogar = findViewById(R.id.login_botao_logar);
        loga(list, email, senha, btnLogar);

        Button btnCdastrar = findViewById(R.id.login_botao_cadastrar);
        cadastra(btnCdastrar);
    }

    private void cadastra(Button btnCdastrar) {
        btnCdastrar.setOnClickListener(view -> startActivity(new Intent(LoginActivity.this, FormularioCadastroActivity.class)));
    }

    private void loga(List<Usuario> list, Editable email, Editable senha, Button btnLogar) {
        btnLogar.setOnClickListener(view -> {
            verificaUsuario(email, senha);
        });
    }

    private void verificaUsuario(Editable email, Editable senha) {
        Task<AuthResult> authResultTaskSinIn = firebaseAuth.signInWithEmailAndPassword(email.toString(), senha.toString());
        authResultTaskSinIn.addOnSuccessListener(this, new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {
                Usuario agatha = new Usuario(
                        "Agatha", "12588758895", "(12) 995623589", "agatha@gmail.com", "123");
                UsuarioDAO.usuario = agatha;
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        authResultTaskSinIn.addOnFailureListener(this, new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.e("USUARIO ERRO", "onFailure:", e );
                if (e.getClass().equals(FirebaseNetworkException.class)) {
                    Toast.makeText(LoginActivity.this, "Sem internet", Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(LoginActivity.this, "Login Inválido", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private boolean verficaSenha(Usuario usuario, Editable senha) {
        return usuario.getSenha().equals(senha.toString());
    }

    private boolean verificaEmail(Usuario usuario, Editable email) {
        return usuario.getEmail().equals(email.toString());
    }
}