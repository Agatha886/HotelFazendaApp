package br.com.unip.pimIV.hotelFazenda.validator;

import android.widget.EditText;

import com.google.android.material.textfield.TextInputLayout;

/**
 * Classe responsável pela validação padrão dos campos do formulário de cadastro de usuário
 */
public class ValidacaoPadrao implements Validador{

    /**
     * Mensagem "Campo obrigatório"
     */
    private static final String CAMPO_OBRIGATORIO = "Campo obrigatório";
    /**
     * Componente responsável pelo layout do campo onde usuário irá digitar suas informções
     */
    private final TextInputLayout textInputCampo;
    /**
     * Componente responsável por receber as informações digitadas pelo usuario
     */
    private final EditText campo;

    /**
     * Construtor Padrão
     * @param textInputCampo
     */
    public ValidacaoPadrao(TextInputLayout textInputCampo) {
        this.textInputCampo = textInputCampo;
        this.campo = this.textInputCampo.getEditText();
    }

    /**
     * Realiza a validação do campo para verificar se ele estar vazio e caso sim atribui a mensagem de erro ao componente de layout
     * @return
     */
    private boolean validaCampoObrigatorio() {
        String texto = campo.getText().toString();
        if (texto.isEmpty()) {
            textInputCampo.setError(CAMPO_OBRIGATORIO);
            return false;
        }
        return true;
    }

    /**
     * Verfica o resulatdo da validação realizada pelo método validaCampoObrigatorio, para tirar a mensagem de erro do componente de layout
     * @return
     */
    @Override
    public boolean estaValido(){
        if(!validaCampoObrigatorio()) return false;
        removeErro();
        return true;
    }

    /**
     * Tira mensagem de erro do componente de layout
     */

    private void removeErro() {
        textInputCampo.setError(null);
        textInputCampo.setErrorEnabled(false);
    }

}
