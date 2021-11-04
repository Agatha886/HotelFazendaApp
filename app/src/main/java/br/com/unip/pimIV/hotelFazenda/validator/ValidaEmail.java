package br.com.unip.pimIV.hotelFazenda.validator;


import android.widget.EditText;

import com.google.android.material.textfield.TextInputLayout;

/**
 * Classe responsável pela validação do e-mail digitado pelo usuário
 */
public class ValidaEmail implements Validador{

    /**
     * Componente responsável pelo layout do campo onde usuário irá digitar seu e-mail
     */
    private final TextInputLayout textInputEmail;
    /**
     * Componente responsável por receber o e-mail digitado pelo usuário
     */
    private final EditText campoEmail;

    /**
     * Validador Padrão
     */
    private final ValidacaoPadrao validadorPadrao;

    /**
     * Construtor Padrão
     * @param textInputEmail
     */
    public ValidaEmail(TextInputLayout textInputEmail) {
        this.textInputEmail = textInputEmail;
        this.campoEmail = this.textInputEmail.getEditText();
        this.validadorPadrao = new ValidacaoPadrao(this.textInputEmail);
    }

    /**
     * Realiza a validação do email
     * @param email
     * @return
     */
    private boolean validaPadrao(String email){
        if(email.matches(".+@.+\\..+")){
            return true;
        }
        textInputEmail.setError("E-mail inválido");
        return false;
    }

    /**
     * Verifica validações, de e-mail e validação padrão
     * @return
     */
    @Override
    public boolean estaValido(){
        if(!validadorPadrao.estaValido()) return false;
        String email = campoEmail.getText().toString();
        if(!validaPadrao(email)) return false;
        return true;
    }
}
