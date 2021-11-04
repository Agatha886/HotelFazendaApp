package br.com.unip.pimIV.hotelFazenda.validator;

/**
 * Interface ultilizada para validar todos os componentes do formulário de cadastro
 */
public interface Validador {
    /**
     * Retorna verdadeiro caso campo válido, caso não é falso.
     * @return
     */
    boolean estaValido();
}
