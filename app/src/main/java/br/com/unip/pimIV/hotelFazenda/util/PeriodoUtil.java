package br.com.unip.pimIV.hotelFazenda.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class PeriodoUtil {

    public static final String DIA_E_MES = "dd/MM";

    public static String periodoEmTexto(Calendar dataDeIda, Calendar dataDeVolta) {
        SimpleDateFormat dataNoFormatoBrasileiro = new SimpleDateFormat(DIA_E_MES);
        String dataFormatadaDeIda = dataNoFormatoBrasileiro.format(dataDeIda.getTime());
        String dataFormadataDeVolta = dataNoFormatoBrasileiro.format(dataDeVolta.getTime());
        return dataFormatadaDeIda + " - " + dataFormadataDeVolta + " de " + dataDeVolta.get(Calendar.YEAR);
    }
}
