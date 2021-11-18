
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

/**
 * Classe LoginActivity responsável pela tela de Login
 * @version 1.0.0
 * @author Agatha Monfredini de Paula Faria
 *
 */
public class LoginActivity extends AppCompatActivity {

    /**
     * A variável  usuarioLogado é utilizada para validar se o usuário existe ou não e por meio desta é feita a operção de login
     */
    private Usuario usuarioLogado;

    /**
     * Método responsável por criar/inicializar a LoginActivity
     *
     * @param savedInstanceState
     */
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

    /**
     * Set o click do botão cadastro para quando este for clicado o usuário seja direcionado para tela de FormularioCadastroActivity
     *
     * @param btnCdastrar
     */
    private void cadastra(Button btnCdastrar) {
        btnCdastrar.setOnClickListener(view -> startActivity(new Intent(LoginActivity.this, FormularioCadastroActivity.class)));
    }

    /**
     * Set o click do botão loga para quando este for clicado realize a função de verificar usuário e valida o Login
     *
     * @param list
     * @param email
     * @param senha
     * @param btnLogar
     */
    private void loga(List<Usuario> list, Editable email, Editable senha, Button btnLogar) {
        btnLogar.setOnClickListener(view -> {
            verificaUsuario(list, email, senha);
            validaLogin();
        });
    }

    /**
     * Verifica se a variável usuarioLogado responsável pela validação de login é null ou não.
     *
     * <b>Procedimentos:</b>
     * Caso usuarioLogado seja válido (usuarioLogado!=null) será atribuído seu valor a uma variável estática da classe UsuarioDAO e o usuário voltará para tela de login.
     * Caso <b>não:</b> aparecerá a mensagem "Login Inválido" na tela
     */
    private void validaLogin() {
        if (usuarioLogado != null) {
            UsuarioDAO.usuario = usuarioLogado;
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
        } else {
            Toast.makeText(LoginActivity.this, "Login Inválido", Toast.LENGTH_LONG).show();
        }
    }

    /**
     * Verifica se o e-mail e a senha digitados pertencem a algum usuário cadastrado na lista de usuários do aplicativo.
     *
     * <b>Procedimentos:</b>
     * Caso a função encontre algum usuário com o e-mail e a senha digitados, o valor deste usuário será atribuído para variável usuarioLogado
     * Caso <b>não:</b> o valor de usuarioLogado será null
     *
     * @param list
     * @param email
     * @param senha
     */
    private void verificaUsuario(List<Usuario> list, Editable email, Editable senha) {
        for (Usuario usuario : list) {
            if (verificaEmail(usuario, email) && verficaSenha(usuario, senha)) {
                usuarioLogado = usuario;
                break;
            } else {
                usuarioLogado = null;
            }
        }
    }

    /**
     * Verifica se a senha digitada é igual a senha de algum usuário na lista de usuários
     *
     * @param usuario
     * @param senha
     * @return
     */
    private boolean verficaSenha(Usuario usuario, Editable senha) {
        return usuario.getSenha().equals(senha.toString());
    }

    /**
     * Verifica se a e-mail digitado é igual o e-mail de algum usuário na lista de usuários
     *
     * @param usuario
     * @param email
     * @return
     */
    private boolean verificaEmail(Usuario usuario, Editable email) {
        return usuario.getEmail().equals(email.toString());
    }
}