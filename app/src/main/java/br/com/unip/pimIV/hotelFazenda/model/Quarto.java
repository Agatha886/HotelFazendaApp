package br.com.unip.pimIV.hotelFazenda.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Calendar;

public class Quarto implements Serializable {
    private final String quarto;
    private final String imagem;
    private final int pessoas;
    private  Calendar dateDeIda;
    private Calendar dateDeVoltar;
    private BigDecimal precoDaDiaria;
    private BigDecimal precoTotal;

    public Quarto(String quarto, String imagem, int pessoas, BigDecimal precoDaDiaria) {
        this.quarto = quarto;
        this.imagem = imagem;
        this.pessoas = pessoas;
        this.precoDaDiaria = precoDaDiaria;
        this.dateDeIda = Calendar.getInstance();
        this.dateDeVoltar = Calendar.getInstance();
        this.precoTotal = precoDaDiaria;
    }

    public BigDecimal getPrecoTotal() {
        return precoTotal;
    }

    public String getQuarto() {
        return quarto;
    }

    public String getImagem() {
        return imagem;
    }

    public int getPessoas() {
        return pessoas;
    }

    public BigDecimal getPrecoDaDiaria() {
        return precoDaDiaria;
    }

    public Calendar getDataDeIda() {
        return dateDeIda;
    }

    public void setPrecoTotal(BigDecimal precoDaDiaria){
        this.precoTotal = precoDaDiaria;
    }

    public void setDataDeIda(Calendar dataDeIda) {
       dateDeIda = dataDeIda;
    }

    public Calendar getDataDeVolta() {
        return dateDeVoltar;
    }

    public void setDataDeVolta(Calendar dataDeVolta) {
        dateDeVoltar = dataDeVolta;
    }
}
