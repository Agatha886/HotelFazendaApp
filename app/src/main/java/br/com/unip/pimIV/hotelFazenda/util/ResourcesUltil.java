package br.com.unip.pimIV.hotelFazenda.util;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;

public class ResourcesUltil {

    public static final String DRAWABLE = "drawable";

    public static Drawable getDrawable(Context context, String drawableEmTexto) {
        Resources resources = context.getResources();
        int idRecursoEncontrado = resources.getIdentifier(drawableEmTexto, DRAWABLE, context.getPackageName());
        return resources.getDrawable(idRecursoEncontrado);
    }

}
