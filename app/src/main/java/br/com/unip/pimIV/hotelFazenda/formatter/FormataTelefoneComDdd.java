package br.com.unip.pimIV.hotelFazenda.formatter;

/**
 * Classe resposável por fomatar o txto digitidado no campo Telefone do formulário de cadstro de usuário
 *
 * @author Agatha Monfredini de Paula Faria
 * @version 1.0.0
 */
public class FormataTelefoneComDdd {

    /**
     * Método formata uma String para o formato de telefone padrão no Brasil
     *
     * @param telefoneComDdd
     * @return
     */
    public String formata(String telefoneComDdd) {
        return telefoneComDdd
                .replaceAll("([0-9]{2})([0-9]{4,5})([0-9]{4})", "($1) $2-$3");
    }

    /**
     * Método retira de uma String a formatação padrão de telefone do aplicativo
     *
     * @param telefoneComDdd
     * @return
     */
    public String remove(String telefoneComDdd) {
        return telefoneComDdd
                .replace("(", "")
                .replace(")", "")
                .replace(" ", "")
                .replace("-", "");
    }

}
