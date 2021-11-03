package br.com.unip.pimIV.hotelFazenda.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

/**
 * Classe responsável por formatar moeda no padrão brasileiro
 */

public class MoedaUtil {

    /**
     * Atribuito linguagem português
     */
    public static final String PORTUGUES = "pt";

    /**
     * Atributo pais Brasil
     */
    public static final String BRASIL = "br";

    /**
     * Formata um BigDecimal para o formatado padrão da moeda brasileira
     * @param valor
     * @return
     */
    public static String formataParaBrasileiro(BigDecimal valor){
        NumberFormat formatoBrasileiro = DecimalFormat.getCurrencyInstance(new Locale(PORTUGUES, BRASIL));
        return  formatoBrasileiro.format(valor);
    }

}
