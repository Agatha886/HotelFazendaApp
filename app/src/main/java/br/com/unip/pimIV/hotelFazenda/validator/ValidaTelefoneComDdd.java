package br.com.unip.pimIV.hotelFazenda.validator;


import android.widget.EditText;

import com.google.android.material.textfield.TextInputLayout;

import br.com.unip.pimIV.hotelFazenda.formatter.FormataTelefoneComDdd;

/**
 * Classe responsável pela validação do telefone digitado pelo usuário
 */
public class ValidaTelefoneComDdd implements Validador {

    /**
     * Mensagem "Telefone deve ter entre 10 a 11 dígitos"
     */
    public static final String DEVE_TER_DEZ_OU_ONZE_DIGITOS = "Telefone deve ter entre 10 a 11 dígitos";

    /**
     *  Componente responsável pelo layout do campo onde usuário irá digitar seu telefone
     */
    private final TextInputLayout textInputTelefoneComDdd;

    /**
     * Componente responsável por receber o telefone digitado pelo usuário
     */
    private final EditText campoTelefoneComDdd;

    /**
     * Validador Padrão
     */
    private final ValidacaoPadrao validacaoPadrao;

    /**
     * Formatador para formato de telefone brasileiro
     */
    private final FormataTelefoneComDdd formatador = new FormataTelefoneComDdd();

    /**
     * Construtor padrão
     * @param textInputTelefoneComDdd
     */
    public ValidaTelefoneComDdd(TextInputLayout textInputTelefoneComDdd) {
        this.textInputTelefoneComDdd = textInputTelefoneComDdd;
        this.campoTelefoneComDdd = textInputTelefoneComDdd.getEditText();
        this.validacaoPadrao = new ValidacaoPadrao(textInputTelefoneComDdd);
    }

    /**
     * Valida se telefone tem 10 ou 11 dígitos
     * @param telefoneComDdd
     * @return
     */
    private boolean validaEntreDezOuOnzeDigitos(String telefoneComDdd){
        int digitos = telefoneComDdd.length();
        if(digitos < 10 || digitos > 11){
            textInputTelefoneComDdd.setError(DEVE_TER_DEZ_OU_ONZE_DIGITOS);
            return false;
        }
        return true;
    }

    /**
     * Verifca se telefone digitado é válido
     * @return
     */
    @Override
    public boolean estaValido(){
        if(!validacaoPadrao.estaValido()) return false;
        String telefoneComDdd = campoTelefoneComDdd.getText().toString();
        String telefoneComDddSemFormatacao = formatador.remove(telefoneComDdd);
        if(!validaEntreDezOuOnzeDigitos(telefoneComDddSemFormatacao)) return false;
        adicionaFormatacao(telefoneComDddSemFormatacao);
        return true;
    }

    /**
     * Adiciona formatação de telefone a string digitada pelo usuário
     * @param telefoneComDdd
     */
    private void adicionaFormatacao(String telefoneComDdd) {
        String telefoneComDddFormatado = formatador.formata(telefoneComDdd);
        campoTelefoneComDdd.setText(telefoneComDddFormatado);
    }


}
