package io.atmos;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Gerar Número
 *
 * @author Marley de S. Santos
 * @version 1.0.0
 * @since 06-03-2026
 * */
public class GerarNumero {
    private int numero;

    /**
     * Construtor
     * */
    public GerarNumero() {
        this.numero = ThreadLocalRandom.current().nextInt(1, 5);
    }

    /**
     * Obtém um número aleatório gerado
     *
     * @return int
     * */
    public int getNumero() {
        return numero;
    }
}
