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
     * Atributo responsável por guardar o nome do usuário
     */
    private String nome;

    /**
     * Atributo responsável por guardar o CPF do usuário
     */
    private String cpf;

    /**
     * Atributo responsável por guardar o telefone do usuário
     */
    private String telefone;

    /**
     * Atributo responsável por guardar o e-mail do usuário
     */
    private String email;

    /**
     * Atributo responsável por guardar a senha do usuário
     */
    private String senha;

    /**
     * Atributo responsável por guardar a lista de compras do usuário
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
     * Retorna valor do atributo </b>email</b>
     *
     * @return
     */
    public String getEmail() {
        return email;
    }

    /**
     * Retorna valor do atributo </b>senha</b>
     *
     * @return
     */
    public String getSenha() {
        return senha;
    }

    /**
     * Retorna valor do atributo </b>nome</b>
     *
     * @return
     */
    public String getNome() {
        return nome;
    }

    /**
     * Retorna valor do atributo </b>cpf</b>
     *
     * @return
     */
    public String getCpf() {
        return cpf;
    }

    /**
     * Retorna valor do atributo </b>telefone</b>
     *
     * @return
     */
    public String getTelefone() {
        return telefone;
    }


    /**
     * Sobreescrita do método equals.
     * Responsável por verificar se um usuário é igual ao outro através dos atributos de e-mail e senha
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

    /**
     * Sobreescrita do método hashCode.
     * @return
     */
    @Override
    public int hashCode() {
        int result = email != null ? email.hashCode() : 0;
        result = 31 * result + (senha != null ? senha.hashCode() : 0);
        return result;
    }
}
