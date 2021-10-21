package br.com.unip.pimIV.hotelFazenda.util;

import br.com.unip.pimIV.hotelFazenda.model.Usuario;

public interface MandaUsuario {
    void sucesso(Usuario usuario);
    void falha(Exception e);
}
