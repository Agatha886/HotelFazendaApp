package br.com.unip.pimIV.hotelFazenda.model;

import java.io.Serializable;

public class QuartoDocumento implements Serializable {
    private  String quarto;
    private  String imagem;
    private  int pessoas;
    private String dateDeIda;
    private String dateDeVoltar;
    private Double precoDaDiaria;
    private Double precoTotal;
    private String idUser;

    public QuartoDocumento(String quarto, String imagem, int pessoas, String dateDeIda, String dateDeVoltar, Double precoDaDiaria, Double precoTotal, String idUser) {
        this.quarto = quarto;
        this.imagem = imagem;
        this.pessoas = pessoas;
        this.dateDeIda = dateDeIda;
        this.dateDeVoltar = dateDeVoltar;
        this.precoDaDiaria = precoDaDiaria;
        this.precoTotal = precoTotal;
        this.idUser = idUser;
    }

    public String getQuarto() {
        return quarto;
    }
}
