package br.com.unip.pimIV.hotelFazenda.dao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import br.com.unip.pimIV.hotelFazenda.model.Quarto;
import br.com.unip.pimIV.hotelFazenda.model.Usuario;

public class UsuarioDAO {

  public static Usuario usuario;

  public static List<Usuario> listaUsuarios = new ArrayList<Usuario>(Arrays.asList(new Usuario(
          "Agatha", "12588758895","(12) 995623589","agatha@gmail.com", "123") ));

  public void adicionaUsuario(Usuario user){
    listaUsuarios.add(user);
  }

  public void adicionaCompra(Quarto quarto){
    usuario.listaDeCompras.add(quarto);
  }

}
