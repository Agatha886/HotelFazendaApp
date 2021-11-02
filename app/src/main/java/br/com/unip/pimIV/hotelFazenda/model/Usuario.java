package br.com.unip.pimIV.hotelFazenda.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe modelo Usuario, representa os usuários do aplicativo
 *
 * @author Agatha Monfredini de Paula Faria
 * @version 1.0.0
 */
public class Usuario implements Serializable {
    /**
     * Parâmetro responsável por guardar o nome do usuário
     */
    private String nome;

    /**
     * Parâmetro responsável por guardar o CPF do usuário
     */
    private String cpf;

    /**
     * Parâmetro responsável por guardar o telefone do usuário
     */
    private String telefone;

    /**
     * Parâmetro responsável por guardar o e-mail do usuário
     */
    private String email;

    /**
     * Parâmetro responsável por guardar a senha do usuário
     */
    private String senha;

    /**
     * Parâmetro responsável por guardar a lista de compras do usuário
     */
    public List<Quarto> listaDeCompras;

    /**
     * Construtor padrão da classe Usuario
     *
     * @param nome
     * @param cpf
     * @param telefone
     * @param email
     * @param senha
     */
    public Usuario(String nome, String cpf, String telefone, String email, String senha) {
        this.nome = nome;
        this.cpf = cpf;
        this.telefone = telefone;
        this.email = email;
        this.senha = senha;
        this.listaDeCompras = new ArrayList<>();
    }

    /**
     * Retorna valor do parâmetro </b>email</b>
     *
     * @return
     */
    public String getEmail() {
        return email;
    }

    /**
     * Retorna valor do parâmetro </b>senha</b>
     *
     * @return
     */
    public String getSenha() {
        return senha;
    }

    /**
     * Retorna valor do parâmetro </b>nome</b>
     *
     * @return
     */
    public String getNome() {
        return nome;
    }

    /**
     * Retorna valor do parâmetro </b>cpf</b>
     *
     * @return
     */
    public String getCpf() {
        return cpf;
    }

    /**
     * Retorna valor do parâmetro </b>telefone</b>
     *
     * @return
     */
    public String getTelefone() {
        return telefone;
    }


    /**
     * Sobreescrita do método equals.
     * Responsável por verificar se um usuário é igual ao outro através dos parâmetros de e-mail e senha
     *
     * @param o
     * @return
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Usuario usuario = (Usuario) o;

        if (email != null ? !email.equals(usuario.email) : usuario.email != null) return false;
        return senha != null ? senha.equals(usuario.senha) : usuario.senha == null;
    }

    @Override
    public int hashCode() {
        int result = email != null ? email.hashCode() : 0;
        result = 31 * result + (senha != null ? senha.hashCode() : 0);
        return result;
    }
}
