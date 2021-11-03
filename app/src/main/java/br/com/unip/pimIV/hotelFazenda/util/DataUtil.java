package br.com.unip.pimIV.hotelFazenda.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Classe responsável por formatar data no padrão brasileiro
 */
public class DataUtil {

    /**
     * Atributo responsável por conter o formaatdor brasileiro para data
     */
    public static final SimpleDateFormat FORMATO_BRASILEIRO = new SimpleDateFormat("dd/MM/yyyy");

    /**
     * Formata uma instancia de calendar para o padrão brasileiro
     * @param calendar
     * @return
     */
    public static String formataParaBrasileiro(Calendar calendar) {
        return FORMATO_BRASILEIRO.format(calendar.getTime());
    }

}
