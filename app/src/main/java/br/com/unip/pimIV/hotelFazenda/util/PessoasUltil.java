package br.com.unip.pimIV.hotelFazenda.util;

/**
 * Classe responsável por retornar uma string com a data de ida e de volta selecionadas pelo usuário
 */
public class PessoasUltil {

    /**
     * String "pessoa" ( no singular)
     */
    public static final String PESSOA_SINGULAR = "pessoa";

    /**
     * String "pessoas" ( no plural)
     */
    public static final String PESSOA_PLURAL = PESSOA_SINGULAR + "s";

    /**
     * Formata texto "pessoas" de acordo com a quantidade de pessoas para cada quarto
     * @param quantidadePessoas
     * @return
     */
    public static String formataTextoPessoas(int quantidadePessoas) {

            if( quantidadePessoas > 1){
                return quantidadePessoas + " " + PESSOA_PLURAL;
            }
                return quantidadePessoas + " " + PESSOA_SINGULAR;
        }
}
