package br.com.unip.pimIV.hotelFazenda.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;

import java.util.List;

import br.com.unip.pimIV.hotelFazenda.R;
import br.com.unip.pimIV.hotelFazenda.dao.UsuarioDAO;
import br.com.unip.pimIV.hotelFazenda.model.Usuario;

public class LoginActivity extends AppCompatActivity {
    private Usuario usuarioLogado ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
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
            verificaUsuario(list, email, senha);
            validaLogin();
        });
    }

    private void validaLogin() {
        if(usuarioLogado != null){
            UsuarioDAO.usuario = usuarioLogado;
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
        }else{
            Toast.makeText(LoginActivity.this, "Login Inválido", Toast.LENGTH_LONG).show();
        }
    }

    private void verificaUsuario(List<Usuario> list, Editable email, Editable senha) {
        for (Usuario usuario : list) {
            if(verificaEmail(usuario, email) && verficaSenha(usuario, senha)){
                usuarioLogado = usuario;
                break;
            }else{
                usuarioLogado = null;
            }
        }
    }

    private boolean verficaSenha(Usuario usuario, Editable senha) {
        return usuario.getSenha().equals(senha.toString());
    }

    private boolean verificaEmail(Usuario usuario, Editable email) {
        return usuario.getEmail().equals(email.toString());
    }
}