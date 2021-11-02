package br.com.unip.pimIV.hotelFazenda.dao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import br.com.unip.pimIV.hotelFazenda.model.Quarto;
import br.com.unip.pimIV.hotelFazenda.model.Usuario;

/**
 * Classe responsável pelo banco de dados dos usuários do aplicativo
 *
 * @author Agatha Monfredini de Paula Faria
 * @version 1.0.0
 */
public class UsuarioDAO {

    /**
     * A variável estático usuario é utilizada para retornar as informações do usuário logado em qualquer parte do aplicativo
     */
    public static Usuario usuario;

    /**
     * Método retorna uma lista estática dos usuários cadastrados no aplicativo
     */
    public static List<Usuario> listaUsuarios = new ArrayList<Usuario>(Arrays.asList(new Usuario(
            "Agatha", "12588758895", "(12) 995623589", "agatha@gmail.com", "123")));

    /**
     * Método responsável por adicionar um novo usuário na lista de usuários do aplicativo
     *
     * @param user
     */
    public void adicionaUsuario(Usuario user) {
        listaUsuarios.add(user);
    }


    /**
     * Método responsável por adicionar uma nova compra a lista de compras do usuário logado
     *
     * @param quarto
     */
    public void adicionaCompra(Quarto quarto) {
        usuario.listaDeCompras.add(quarto);
    }

}
