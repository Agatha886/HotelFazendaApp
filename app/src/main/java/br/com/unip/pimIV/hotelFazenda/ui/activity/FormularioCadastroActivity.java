package br.com.unip.pimIV.hotelFazenda.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.List;

import br.com.caelum.stella.format.CPFFormatter;


import br.com.unip.pimIV.hotelFazenda.dao.UsuarioDAO;
import br.com.unip.pimIV.hotelFazenda.formatter.FormataTelefoneComDdd;
import br.com.unip.pimIV.hotelFazenda.model.Usuario;
import br.com.unip.pimIV.hotelFazenda.validator.ValidaCpf;
import br.com.unip.pimIV.hotelFazenda.validator.ValidaEmail;
import br.com.unip.pimIV.hotelFazenda.validator.ValidaTelefoneComDdd;
import br.com.unip.pimIV.hotelFazenda.validator.ValidacaoPadrao;
import br.com.unip.pimIV.hotelFazenda.validator.Validador;


public class FormularioCadastroActivity extends AppCompatActivity {

    private static final String ERRO_FORMATAO_CPF = "erro formatação cpf";
    private final List<Validador> validadores = new ArrayList<>();

    private TextInputLayout textInputSenha;
    private TextInputLayout textInputEmail;
    private TextInputLayout textInputTelefoneComDdd;
    private TextInputLayout textInputCpf;
    private TextInputLayout textInputNomeCompleto;
    private UsuarioDAO usuarioDao = new UsuarioDAO();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_cadastro);
        inicializaCampos();
    }

    private void inicializaCampos() {
        configuraCampoNomeCompleto();
        configuraCampoCpf();
        configuraCampoTelefoneComDdd();
        configuraCampoEmail();
        configuraCampoSenha();
        configuraBotaoCadastrar();
    }

    private void configuraBotaoCadastrar() {
        Button botaoCadastrar = findViewById(R.id.formulario_cadastro_botao_cadastrar);
        botaoCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean formularioEstaValido = validaTodosCampos();
                if(formularioEstaValido){
                    cadastroRealizado();
                }
            }
        });
    }

    private void cadastroRealizado() {
        String cpf = textInputCpf.getEditText().getText().toString();
        String email = textInputEmail.getEditText().getText().toString();
        String nome = textInputNomeCompleto.getEditText().getText().toString();
        String senha = textInputSenha.getEditText().getText().toString();
        String telefone = textInputTelefoneComDdd.getEditText().getText().toString();
        Usuario usuarioCadastrado = new Usuario(nome,cpf,telefone,email,senha);

        if(usuarioCadastrado != null){
            usuarioDao.adicionaUsuario(usuarioCadastrado);
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);

        }
    }

    private boolean validaTodosCampos() {
        boolean formularioEstaValido = true;
        for (Validador validador :
                validadores) {
            if(!validador.estaValido()){
                formularioEstaValido = false;
            }
        }
        return formularioEstaValido;
    }

    private void configuraCampoSenha() {
        textInputSenha = findViewById(R.id.login_campo_senha);
        adicionaValidacaoPadrao(textInputSenha);
    }

    private void configuraCampoEmail() {
        textInputEmail = findViewById(R.id.login_campo_email);
        EditText campoEmail = textInputEmail.getEditText();
        final ValidaEmail validador = new ValidaEmail(textInputEmail);
        validadores.add(validador);
        campoEmail.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus){
                    validador.estaValido();
                }
            }
        });
    }

    private void configuraCampoTelefoneComDdd() {
        textInputTelefoneComDdd = findViewById(R.id.formulario_cadastro_campo_telefone_com_ddd);
        final EditText campoTelefoneComDdd = textInputTelefoneComDdd.getEditText();
        final ValidaTelefoneComDdd validador = new ValidaTelefoneComDdd(textInputTelefoneComDdd);
        validadores.add(validador);
        final FormataTelefoneComDdd formatador = new FormataTelefoneComDdd();
        campoTelefoneComDdd.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                String telefoneComDdd = campoTelefoneComDdd.getText().toString();
                if(hasFocus){
                    String telefoneComDddSemFormatacao = formatador.remove(telefoneComDdd);
                    campoTelefoneComDdd.setText(telefoneComDddSemFormatacao);
                } else {
                    validador.estaValido();
                }
            }
        });
    }


    private void configuraCampoCpf() {
        textInputCpf = findViewById(R.id.formulario_cadastro_campo_cpf);
        final EditText campoCpf = textInputCpf.getEditText();
        final CPFFormatter formatador = new CPFFormatter();
        final ValidaCpf validador = new ValidaCpf(textInputCpf);
        validadores.add(validador);
        campoCpf.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    removeFormatacao(formatador, campoCpf);
                } else {
                    validador.estaValido();
                }
            }
        });
    }

    private void removeFormatacao(CPFFormatter formatador, EditText campoCpf) {
        String cpf = campoCpf.getText().toString();
        try {
            String cpfSemFormato = formatador.unformat(cpf);
            campoCpf.setText(cpfSemFormato);
        } catch (IllegalArgumentException e){
            Log.e(ERRO_FORMATAO_CPF, e.getMessage());
        }
    }


    private void configuraCampoNomeCompleto() {
        textInputNomeCompleto = findViewById(R.id.formulario_cadastro_campo_nome_completo);
        adicionaValidacaoPadrao(textInputNomeCompleto);
    }

    private void adicionaValidacaoPadrao(final TextInputLayout textInputCampo) {
        final EditText campo = textInputCampo.getEditText();
        final ValidacaoPadrao validador = new ValidacaoPadrao(textInputCampo);
        validadores.add(validador);
        campo.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    validador.estaValido();
                }
            }
        });
    }



}