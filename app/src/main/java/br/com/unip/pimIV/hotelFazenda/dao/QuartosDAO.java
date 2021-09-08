package br.com.unip.pimIV.hotelFazenda.dao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import br.com.unip.pimIV.hotelFazenda.model.Quarto;

public class QuartosDAO {
    public List<Quarto> lista() {

        List<Quarto> pacotes = new ArrayList<>(Arrays.asList(
                new Quarto("Quarto Casal Simples", "quartocasal", 2, new BigDecimal(243.99)),
                new Quarto("Quarto Casal Com Vista", "quartocasalcomvista", 2, new BigDecimal(321.50)),
                new Quarto("Quarto Solteiro Duplo", "quartosolteiroduplo", 2, new BigDecimal(214.20)),
                new Quarto("Dormitório", "dormitorio", 8, new BigDecimal(432.55)),
                new Quarto("Quarto Triplo Solteiro", "quartotriplosolteiro", 3, new BigDecimal(250.99)),
                new Quarto("Quarto Solteiro", "quartosolteiro", 1, new BigDecimal(189.90)),
                new Quarto("Suíte", "suite", 2, new BigDecimal(549.90))));
        return pacotes;
    }
}
