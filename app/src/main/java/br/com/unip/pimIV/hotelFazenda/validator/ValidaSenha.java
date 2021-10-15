package br.com.unip.pimIV.hotelFazenda.validator;


import android.widget.EditText;

import com.google.android.material.textfield.TextInputLayout;

public class ValidaSenha implements Validador{

    private final TextInputLayout textInputSenha;
    private final EditText campoSenha;
    private final ValidacaoPadrao validadorPadrao;

    public ValidaSenha(TextInputLayout textInputSenha) {
        this.textInputSenha = textInputSenha;
        this.campoSenha = this.textInputSenha.getEditText();
        this.validadorPadrao = new ValidacaoPadrao(this.textInputSenha);
    }

    private boolean validaPadrao(String senha){
        if(senha.length() >= 6){
            return true;
        }
        textInputSenha.setError("A senha deve ter 6 digitos ou mais");
        return false;
    }

    @Override
    public boolean estaValido(){
        if(!validadorPadrao.estaValido()) return false;
        String email = campoSenha.getText().toString();
        if(!validaPadrao(email)) return false;
        return true;
    }
}
