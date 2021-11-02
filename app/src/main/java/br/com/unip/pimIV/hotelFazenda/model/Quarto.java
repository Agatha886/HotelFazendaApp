package br.com.unip.pimIV.hotelFazenda.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Calendar;

/**
 * Classe modelo Quarto, representa os quartos do aplicativo
 *
 * @author Agatha Monfredini de Paula Faria
 * @version 1.0.0
 */
public class Quarto implements Serializable {

    /**
     * Atributo responsável por guardar o nome do quarto
     */
    private final String quarto;
    /**
     * Atributo responsável por guardar o nome da imagem do quarto
     */
    private final String imagem;

    /**
     * Atributo responsável por guardar a capacidade de pessoas do guarto
     */
    private final int pessoas;

    /**
     * Atributo responsável por guardar a data de ida (check-in) do aluguel do quarto
     */
    protected Calendar dateDeIda;

    /**
     * Atributo responsável por guardar a data de volta (check-out) do aluguel do quarto
     */
    protected Calendar dateDeVoltar;

    /**
     * Atributo responsável por guardar o valor da diária do quarto
     */
    private BigDecimal precoDaDiaria;

    /**
     * Atributo responsável por guardar o valor total da hospedagem
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
     * Retorna valor do atributo </b>precoTotal</b>
     *
     * @return
     */
    public BigDecimal getPrecoTotal() {
        return precoTotal;
    }

    /**
     * Retorna valor do atributo </b>quarto</b>
     *
     * @return
     */
    public String getQuarto() {
        return quarto;
    }

    /**
     * Retorna valor do atributo </b>imagem</b>
     *
     * @return
     */
    public String getImagem() {
        return imagem;
    }

    /**
     * Retorna valor do atributo </b>pessoas</b>
     *
     * @return
     */
    public int getPessoas() {
        return pessoas;
    }

    /**
     * Retorna valor do atributo </b>precoDaDiaria</b>
     *
     * @return
     */
    public BigDecimal getPrecoDaDiaria() {
        return precoDaDiaria;
    }

    /**
     * Retorna valor do atributo </b>dateDeIda</b>
     *
     * @return
     */
    public Calendar getDataDeIda() {
        return dateDeIda;
    }

    /**
     * Atribui valor ao atributo </b>precoTotal</b>
     * @param precoDaDiaria
     */
    public void setPrecoTotal(BigDecimal precoDaDiaria) {
        this.precoTotal = precoDaDiaria;
    }

    /**
     * Atribui valor ao atributo </b>dateDeIda</b>
     * @param dataDeIda
     */
    public void setDataDeIda(Calendar dataDeIda) {
        dateDeIda = dataDeIda;
    }

    /**
     * Retorna valor do atributo </b>dateDeVoltar</b>
     * @return
     */
    public Calendar getDataDeVolta() {
        return dateDeVoltar;
    }

    /**
     * Atribui valor ao atributo </b>dataDeVolta</b>
     * @param dataDeVolta
     */
    public void setDataDeVolta(Calendar dataDeVolta) {
        dateDeVoltar = dataDeVolta;
    }
}
