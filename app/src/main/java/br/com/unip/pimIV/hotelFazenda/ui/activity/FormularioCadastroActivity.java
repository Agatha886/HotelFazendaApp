package br.com.unip.pimIV.hotelFazenda.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.FirebaseNetworkException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import br.com.caelum.stella.format.CPFFormatter;
import br.com.unip.pimIV.hotelFazenda.R;
import br.com.unip.pimIV.hotelFazenda.formatter.FormataTelefoneComDdd;
import br.com.unip.pimIV.hotelFazenda.validator.ValidaCpf;
import br.com.unip.pimIV.hotelFazenda.validator.ValidaEmail;
import br.com.unip.pimIV.hotelFazenda.validator.ValidaSenha;
import br.com.unip.pimIV.hotelFazenda.validator.ValidaTelefoneComDdd;
import br.com.unip.pimIV.hotelFazenda.validator.ValidacaoPadrao;
import br.com.unip.pimIV.hotelFazenda.validator.Validador;
import br.com.unip.pimIV.hotelFazenda.viewModel.FormularioCadastroViewModel;

public class FormularioCadastroActivity extends AppCompatActivity {

    private static final String ERRO_FORMATAO_CPF = "erro formatação cpf";
    private final List<Validador> validadores = new ArrayList<>();

    private TextInputLayout textInputSenha;
    private TextInputLayout textInputEmail;
    private TextInputLayout textInputTelefoneComDdd;
    private TextInputLayout textInputCpf;
    private TextInputLayout textInputNomeCompleto;
    private final FormularioCadastroViewModel formularioCadastroViewModel = new FormularioCadastroViewModel();

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
        botaoCadastrar.setOnClickListener(v -> {
            boolean formularioEstaValido = validaTodosCampos();
            if (formularioEstaValido) {
                formularioCadastroViewModel.cadastroRealizado(
                        Objects.requireNonNull(textInputCpf.getEditText()).getText().toString(),
                        Objects.requireNonNull(textInputEmail.getEditText()).getText().toString(),
                        Objects.requireNonNull(textInputNomeCompleto.getEditText()).getText().toString(),
                        Objects.requireNonNull(textInputSenha.getEditText()).getText().toString(),
                        Objects.requireNonNull(textInputTelefoneComDdd.getEditText()).getText().toString(),
                        getOnFailureListener(),
                        getOnSuccessListener());
            }
        });
    }

    private OnSuccessListener<AuthResult> getOnSuccessListener() {
        return authResult -> {
            Intent intent = new Intent(FormularioCadastroActivity.this, LoginActivity.class);
            startActivity(intent);
        };
    }

    private OnFailureListener getOnFailureListener() {
        return e -> {
            if (e.getClass().equals(FirebaseNetworkException.class)) {
                Toast.makeText(FormularioCadastroActivity.this, "Sem internet", Toast.LENGTH_LONG).show();
            }else if(e.getClass() == FirebaseAuthUserCollisionException.class){
                Toast.makeText(FormularioCadastroActivity.this, "Email já cadastrado", Toast.LENGTH_LONG).show();

            }else{
                Toast.makeText(FormularioCadastroActivity.this, "Falha ao cadastrar", Toast.LENGTH_LONG).show();
            }
        };
    }

    private boolean validaTodosCampos() {
        boolean formularioEstaValido = true;
        for (Validador validador :
                validadores) {
            if (!validador.estaValido()) {
                formularioEstaValido = false;
            }
        }
        return formularioEstaValido;
    }

    private void configuraCampoSenha() {
        textInputSenha = findViewById(R.id.login_campo_senha);
        EditText campoSenha = textInputSenha.getEditText();
        final ValidaSenha validador = new ValidaSenha(textInputSenha);
        validadores.add(validador);
        assert campoSenha != null;
        campoSenha.setOnFocusChangeListener((view, hasFocus) -> {
            if (!hasFocus) {
                validador.estaValido();
            }
        });
    }

    private void configuraCampoEmail() {
        textInputEmail = findViewById(R.id.login_campo_email);
        EditText campoEmail = textInputEmail.getEditText();
        final ValidaEmail validador = new ValidaEmail(textInputEmail);
        validadores.add(validador);
        assert campoEmail != null;
        campoEmail.setOnFocusChangeListener((v, hasFocus) -> {
            if (!hasFocus) {
                validador.estaValido();
            }
        });
    }

    private void configuraCampoTelefoneComDdd() {
        textInputTelefoneComDdd = findViewById(R.id.formulario_cadastro_campo_telefone_com_ddd);
        final EditText campoTelefoneComDdd = textInputTelefoneComDdd.getEditText();
        final ValidaTelefoneComDdd validador = new ValidaTelefoneComDdd(textInputTelefoneComDdd);
        validadores.add(validador);
        final FormataTelefoneComDdd formatador = new FormataTelefoneComDdd();
        assert campoTelefoneComDdd != null;
        campoTelefoneComDdd.setOnFocusChangeListener((v, hasFocus) -> {
            String telefoneComDdd = campoTelefoneComDdd.getText().toString();
            if (hasFocus) {
                String telefoneComDddSemFormatacao = formatador.remove(telefoneComDdd);
                campoTelefoneComDdd.setText(telefoneComDddSemFormatacao);
            } else {
                validador.estaValido();
            }
        });
    }


    private void configuraCampoCpf() {
        textInputCpf = findViewById(R.id.formulario_cadastro_campo_cpf);
        final EditText campoCpf = textInputCpf.getEditText();
        final CPFFormatter formatador = new CPFFormatter();
        final ValidaCpf validador = new ValidaCpf(textInputCpf);
        validadores.add(validador);
        assert campoCpf != null;
        campoCpf.setOnFocusChangeListener((v, hasFocus) -> {
            if (hasFocus) {
                removeFormatacao(formatador, campoCpf);
            } else {
                validador.estaValido();
            }
        });
    }

    private void removeFormatacao(CPFFormatter formatador, EditText campoCpf) {
        String cpf = campoCpf.getText().toString();
        try {
            String cpfSemFormato = formatador.unformat(cpf);
            campoCpf.setText(cpfSemFormato);
        } catch (IllegalArgumentException e) {
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
        assert campo != null;
        campo.setOnFocusChangeListener((v, hasFocus) -> {
            if (!hasFocus) {
                validador.estaValido();
            }
        });
    }


}