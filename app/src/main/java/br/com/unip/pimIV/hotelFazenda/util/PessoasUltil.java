package br.com.unip.pimIV.hotelFazenda.util;

public class PessoasUltil {

    public static final String PESSOA_SINGULAR = "pessoa";
    public static final String PESSOA_PLURAL = PESSOA_SINGULAR + "s";

    public static String formataTextoPessoas(int quantidadePessoas) {

            if( quantidadePessoas > 1){
                return quantidadePessoas + " " + PESSOA_PLURAL;
            }
                return quantidadePessoas + " " + PESSOA_SINGULAR;
        }
}
