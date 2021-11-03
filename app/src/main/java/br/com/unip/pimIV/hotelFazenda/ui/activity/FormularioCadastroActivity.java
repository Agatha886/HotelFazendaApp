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


/**
 * Classe da FormularioCadastroActivity responsável pela tela no qual terá o formulário de cadastro de usuário
 *
 * @author Agatha Monfredini de Paula Faria
 * @version 1.0.0
 */
public class FormularioCadastroActivity extends AppCompatActivity {

    /**
     * Mensagem de erro ao formatar o CPF
     */
    private static final String ERRO_FORMATAO_CPF = "erro formatação cpf";
    /**
     * Array de validadores usados nos campos do formulário
     */
    private final List<Validador> validadores = new ArrayList<>();

    /**
     * Componente responsável pelo layout do campo onde usuário irá digitar a senha deseja cadastrar
     */
    private TextInputLayout textInputSenha;

    /**
     * Componente responsável pelo layout do campo onde usuário irá digitar a senha que deseja cadastrar
     */
    private TextInputLayout textInputEmail;
    /**
     * Componente responsável pelo layout do campo onde usuário irá digitar o telefone que deseja cadastrar
     */
    private TextInputLayout textInputTelefoneComDdd;

    /**
     * Componente responsável pelo layout do campo onde usuário irá digitar seu CPF para realização do cadastro
     */
    private TextInputLayout textInputCpf;

    /**
     * Componente responsável pelo layout do campo onde usuário irá digitar digitar seu nome para realização do cadastro
     */
    private TextInputLayout textInputNomeCompleto;

    /**
     * Objeto de UsuarioDao responsável pelo banco de dados dos usuários no aplicativo
     */
    private UsuarioDAO usuarioDao = new UsuarioDAO();

    /**
     * Método responsável por criar/inicializar a CompraComcluida
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_cadastro);
        inicializaCampos();
    }

    /**
     * Método responsável por chamar as funções de configurações de cada componente de entrada de texto
     */
    private void inicializaCampos() {
        configuraCampoNomeCompleto();
        configuraCampoCpf();
        configuraCampoTelefoneComDdd();
        configuraCampoEmail();
        configuraCampoSenha();
        configuraBotaoCadastrar();
    }

    /**
     * Set click do botão cadastrar
     * <b>Procedimentos:</b>
     * Caso todas as validações sejam verdadeiras no Array de validadores é realizado o método cadastroRealizado
     * Caso <b>não:</b>, não será feio nada e cada componente realizará sua função de erro
     */
    private void configuraBotaoCadastrar() {
        Button botaoCadastrar = findViewById(R.id.formulario_cadastro_botao_cadastrar);
        botaoCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean formularioEstaValido = validaTodosCampos();
                if (formularioEstaValido) {
                    cadastroRealizado();
                }
            }
        });
    }

    /**
     * Cria um novo usuário a partir das informações digitadas nos componentes de texto do formulário
     */
    private void cadastroRealizado() {
        String cpf = textInputCpf.getEditText().getText().toString();
        String email = textInputEmail.getEditText().getText().toString();
        String nome = textInputNomeCompleto.getEditText().getText().toString();
        String senha = textInputSenha.getEditText().getText().toString();
        String telefone = textInputTelefoneComDdd.getEditText().getText().toString();
        Usuario usuarioCadastrado = new Usuario(nome, cpf, telefone, email, senha);

        if (usuarioCadastrado != null) {
            usuarioDao.adicionaUsuario(usuarioCadastrado);
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);

        }
    }

    /**
     * Realiza a validação de todos os campos do formulário
     *
     * @return
     */
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

    /**
     * Atribui validação padrão ao campo senha
     */
    private void configuraCampoSenha() {
        textInputSenha = findViewById(R.id.login_campo_senha);
        adicionaValidacaoPadrao(textInputSenha);
    }

    /**
     * Atribui validações personalizadas ao campo e-mail
     */
    private void configuraCampoEmail() {
        textInputEmail = findViewById(R.id.login_campo_email);
        EditText campoEmail = textInputEmail.getEditText();
        final ValidaEmail validador = new ValidaEmail(textInputEmail);
        validadores.add(validador);
        campoEmail.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    validador.estaValido();
                }
            }
        });
    }

    /**
     * Atribui validações personalizadas ao campo telefone
     */
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
                if (hasFocus) {
                    String telefoneComDddSemFormatacao = formatador.remove(telefoneComDdd);
                    campoTelefoneComDdd.setText(telefoneComDddSemFormatacao);
                } else {
                    validador.estaValido();
                }
            }
        });
    }


    /**
     * Atribui validações personalizadas ao campo CPF
     */
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

    /**
     * Remove formatação do campo CPF
     *
     * @param formatador
     * @param campoCpf
     */
    private void removeFormatacao(CPFFormatter formatador, EditText campoCpf) {
        String cpf = campoCpf.getText().toString();
        try {
            String cpfSemFormato = formatador.unformat(cpf);
            campoCpf.setText(cpfSemFormato);
        } catch (IllegalArgumentException e) {
            Log.e(ERRO_FORMATAO_CPF, e.getMessage());
        }
    }

    /**
     * Atribui validação padrão ao campo nome
     */
    private void configuraCampoNomeCompleto() {
        textInputNomeCompleto = findViewById(R.id.formulario_cadastro_campo_nome_completo);
        adicionaValidacaoPadrao(textInputNomeCompleto);
    }

    /**
     * Adiciona validação padrão ao array de validadores e realiza função validaCampo.
     *
     * @param textInputCampo
     */
    private void adicionaValidacaoPadrao(final TextInputLayout textInputCampo) {
        final EditText campo = textInputCampo.getEditText();
        final ValidacaoPadrao validador = new ValidacaoPadrao(textInputCampo);
        validadores.add(validador);
        validaCampo(campo, validador);
    }

    /**
     * Verifica se o texto digitado no campo é vazio
     *
     * @param campo
     * @param validador
     */
    private void validaCampo(EditText campo, ValidacaoPadrao validador) {
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