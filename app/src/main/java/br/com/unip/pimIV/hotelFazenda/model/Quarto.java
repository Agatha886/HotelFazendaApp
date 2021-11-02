package br.com.unip.pimIV.hotelFazenda.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Calendar;

/**
 * Classe modelo Quartos, representa os quartos do aplicativo
 *
 * @author Agatha Monfredini de Paula Faria
 * @version 1.0.0
 */
public class Quarto implements Serializable {

    /**
     * Parâmetro responsável por guardar o nome do quarto
     */
    private final String quarto;
    /**
     * Parâmetro responsável por guardar o nome da imagem do quarto
     */
    private final String imagem;

    /**
     * Parâmetro responsável por guardar a capacidade de pessoas do guarto
     */
    private final int pessoas;

    /**
     * Parâmetro responsável por guardar a data de ida (check-in) do aluguel do quarto
     */
    protected Calendar dateDeIda;

    /**
     * Parâmetro responsável por guardar a data de volta (check-out) do aluguel do quarto
     */
    protected Calendar dateDeVoltar;

    /**
     * Parâmetro responsável por guardar o valor da diária do quarto
     */
    private BigDecimal precoDaDiaria;

    /**
     * Parâmetro responsável por guardar o valor total da hospedagem
     */
    private BigDecimal precoTotal;

    /**
     * Construtor padrão da classe Quarto
     *
     * @param quarto
     * @param imagem
     * @param pessoas
     * @param precoDaDiaria
     */
    public Quarto(String quarto, String imagem, int pessoas, BigDecimal precoDaDiaria) {
        this.quarto = quarto;
        this.imagem = imagem;
        this.pessoas = pessoas;
        this.precoDaDiaria = precoDaDiaria;
        this.dateDeIda = Calendar.getInstance();
        this.dateDeVoltar = Calendar.getInstance();
        this.precoTotal = precoDaDiaria;
    }

    /**
     * Retorna valor do parâmetro </b>precoTotal</b>
     *
     * @return
     */
    public BigDecimal getPrecoTotal() {
        return precoTotal;
    }

    /**
     * Retorna valor do parâmetro </b>quarto</b>
     *
     * @return
     */
    public String getQuarto() {
        return quarto;
    }

    /**
     * Retorna valor do parâmetro </b>imagem</b>
     *
     * @return
     */
    public String getImagem() {
        return imagem;
    }

    /**
     * Retorna valor do parâmetro </b>pessoas</b>
     *
     * @return
     */
    public int getPessoas() {
        return pessoas;
    }

    /**
     * Retorna valor do parâmetro </b>precoDaDiaria</b>
     *
     * @return
     */
    public BigDecimal getPrecoDaDiaria() {
        return precoDaDiaria;
    }

    /**
     * Retorna valor do parâmetro </b>dateDeIda</b>
     *
     * @return
     */
    public Calendar getDataDeIda() {
        return dateDeIda;
    }

    /**
     * Atribui valor ao parâmetro </b>precoTotal</b>
     * @param precoDaDiaria
     */
    public void setPrecoTotal(BigDecimal precoDaDiaria) {
        this.precoTotal = precoDaDiaria;
    }

    /**
     * Atribui valor ao parâmetro </b>dateDeIda</b>
     * @param dataDeIda
     */
    public void setDataDeIda(Calendar dataDeIda) {
        dateDeIda = dataDeIda;
    }

    /**
     * Retorna valor do parâmetro </b>dateDeVoltar</b>
     * @return
     */
    public Calendar getDataDeVolta() {
        return dateDeVoltar;
    }

    /**
     * Atribui valor ao parâmetro </b>dataDeVolta</b>
     * @param dataDeVolta
     */
    public void setDataDeVolta(Calendar dataDeVolta) {
        dateDeVoltar = dataDeVolta;
    }
}
