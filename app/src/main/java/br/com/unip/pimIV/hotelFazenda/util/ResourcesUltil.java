package br.com.unip.pimIV.hotelFazenda.util;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;

/**
 * Classe responsável por retornar uma imagem dos resources do aplicativo de acordo com o nome do arquivo
 */
public class ResourcesUltil {

    /**
     * String "drawable"
     */
    public static final String DRAWABLE = "drawable";

    /**
     * Retorna a imagem de quarto de acordo com o nome do arquivo passado
     * @param context
     * @param drawableEmTexto
     * @return
     */
    public static Drawable getDrawable(Context context, String drawableEmTexto) {
        Resources resources = context.getResources();
        int idRecursoEncontrado = resources.getIdentifier(drawableEmTexto, DRAWABLE, context.getPackageName());
        return resources.getDrawable(idRecursoEncontrado);
    }

}
