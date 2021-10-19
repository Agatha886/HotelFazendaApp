package br.com.unip.pimIV.hotelFazenda.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.FirebaseNetworkException;

import br.com.unip.pimIV.hotelFazenda.R;
import br.com.unip.pimIV.hotelFazenda.dao.UsuarioDAO;
import br.com.unip.pimIV.hotelFazenda.model.Usuario;
import br.com.unip.pimIV.hotelFazenda.viewModel.LoginViewModel;

public class LoginActivity extends AppCompatActivity{

    private final LoginViewModel viewModel = new LoginViewModel();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        TextInputLayout textInputEmail = findViewById(R.id.login_campo_email);
        EditText editTextEmail = textInputEmail.getEditText();
        assert editTextEmail != null;
        Editable email = editTextEmail.getText();

        TextInputLayout inputTextSenha = findViewById(R.id.login_campo_senha);
        EditText editTextSenha = inputTextSenha.getEditText();
        assert editTextSenha != null;
        Editable senha = editTextSenha.getText();

        Button btnLogar = findViewById(R.id.login_botao_logar);
        loga(email, senha, btnLogar);

        Button btnCdastrar = findViewById(R.id.login_botao_cadastrar);
        cadastra(btnCdastrar);
    }

    private void cadastra(Button btnCdastrar) {
        btnCdastrar.setOnClickListener(view -> startActivity(new Intent(LoginActivity.this, FormularioCadastroActivity.class)));
    }

    private void loga(Editable email, Editable senha, Button btnLogar) {
        btnLogar.setOnClickListener(view -> viewModel.verificaUsuario(email, senha, retornaSucesso(), retornaFalha()));
    }

    private OnFailureListener retornaFalha(){
        return exception -> {
            Log.e("USUARIO ERRO", "onFailure:", exception );
            if (exception.getClass().equals(FirebaseNetworkException.class)) {
                Toast.makeText(LoginActivity.this, "Sem internet", Toast.LENGTH_LONG).show();
            }else{
                Toast.makeText(LoginActivity.this, "Login Inválido", Toast.LENGTH_LONG).show();
            }
        };
    }

    private OnSuccessListener retornaSucesso(){
        return success-> {
                Usuario agatha = new Usuario(
                        "Agatha", "12588758895", "(12) 995623589", "agatha@gmail.com", "123");
                UsuarioDAO.usuario = agatha;
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
        };
    }

}