package br.com.unip.pimIV.hotelFazenda.validator;

import android.util.Log;
import android.widget.EditText;

import androidx.annotation.NonNull;

import com.google.android.material.textfield.TextInputLayout;

import br.com.caelum.stella.format.CPFFormatter;
import br.com.caelum.stella.validation.CPFValidator;
import br.com.caelum.stella.validation.InvalidStateException;

/**
 * Classe responsável pela validação do CPF digitado pelo usuário
 */
public class ValidaCpf implements Validador {

    /**
     * Mensagem "CPF inválido"
     */
    public static final String CPF_INVALIDO = "CPF inválido";

    /**
     * Mensagem "O CPF precisa ter 11 dígitos"
     */
    public static final String DEVE_TER_ONZE_DIGITOS = "O CPF precisa ter 11 dígitos";
    /**
     * Mensagem "erro formatação cpf";
     */
    private static final String ERRO_FORMATAO_CPF = "erro formatação cpf";

    /**
     * Componente responsável pelo layout do campo onde usuário irá digitar seu CPF
     */
    private final TextInputLayout textInputCpf;

    /**
     * Componente responsável por receber o CPF digitado pelo usuário
     */
    private final EditText campoCpf;
    /**
     * Validador Padrão
     */
    private final ValidacaoPadrao validadorPadrao;

    /**
     * Formatador para formato de CPF brasileiro
     */
    private final CPFFormatter formatador = new CPFFormatter();

    /**
     * Construtor Padrão
     * @param textInputCpf
     */
    public ValidaCpf(TextInputLayout textInputCpf) {
        this.textInputCpf = textInputCpf;
        this.campoCpf = textInputCpf.getEditText();
        this.validadorPadrao = new ValidacaoPadrao(textInputCpf);
    }

    /**
     * Verifica, através de um cáculo, se o CPF é válido
     * @param cpf
     * @return
     */
    private boolean validaCalculoCpf(String cpf) {
        try {
            CPFValidator cpfValidator = new CPFValidator();
            cpfValidator.assertValid(cpf);
        } catch (InvalidStateException e){
            textInputCpf.setError(CPF_INVALIDO);
            return false;
        }
        return true;
    }

    /**
     * Valida se o CPF possui os 11 digitos
     * @param cpf
     * @return
     */
    private boolean validaCampoComOnzeDigitos(String cpf) {
        if (cpf.length() != 11) {
            textInputCpf.setError(DEVE_TER_ONZE_DIGITOS);
            return false;
        }
        return true;
    }

    /**
     * Verifica todas as validações e retorna verdadeiro caso todas passem
     * @return
     */
    @Override
    public boolean estaValido(){
        if(!validadorPadrao.estaValido()) return false;
        String cpf = getCpf();
        String cpfSemFormato = cpf;
        try {
            cpfSemFormato = formatador.unformat(cpf);
        } catch (IllegalArgumentException e){
            Log.e(ERRO_FORMATAO_CPF, e.getMessage());
        }
        if(!validaCampoComOnzeDigitos(cpfSemFormato)) return false;
        if(!validaCalculoCpf(cpfSemFormato)) return false;
        adicionaFormatacao(cpfSemFormato);
        return true;
    }

    /**
     * Adiciona formatação brasileiro para CPF a string digitada pelo usuário
     * @param cpf
     */
    private void adicionaFormatacao(String cpf) {
        String cpfFormatado = formatador.format(cpf);
        campoCpf.setText(cpfFormatado);
    }

    /**
     * Retorna o CPF digitado pelo usuário
     * @return
     */
    @NonNull
    private String getCpf() {
        return campoCpf.getText().toString();
    }

}
