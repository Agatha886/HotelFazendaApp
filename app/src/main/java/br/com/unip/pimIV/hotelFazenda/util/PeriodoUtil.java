package br.com.unip.pimIV.hotelFazenda.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Classe responsável por retornar uma string com a data de ida e de volta selecionadas pelo usuário
 */
public class PeriodoUtil {

    /**
     * Atributo formatador para mostrar dia e mês
     */
    public static final String DIA_E_MES = "dd/MM";

    /**
     * Realiza a junção da data de ida e de volta, sendo este então o período no qual o usuário se hospedará
     * @param dataDeIda
     * @param dataDeVolta
     * @return
     */
    public static String periodoEmTexto(Calendar dataDeIda, Calendar dataDeVolta) {
        SimpleDateFormat dataNoFormatoBrasileiro = new SimpleDateFormat(DIA_E_MES);
        String dataFormatadaDeIda = dataNoFormatoBrasileiro.format(dataDeIda.getTime());
        String dataFormadataDeVolta = dataNoFormatoBrasileiro.format(dataDeVolta.getTime());
        return dataFormatadaDeIda + " - " + dataFormadataDeVolta + " de " + dataDeVolta.get(Calendar.YEAR);
    }
}
