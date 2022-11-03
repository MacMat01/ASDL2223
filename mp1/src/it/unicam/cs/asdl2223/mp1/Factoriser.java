package it.unicam.cs.asdl2223.mp1;

/**
 * Un fattorizzatore è un agente che fattorizza un qualsiasi numero naturale nei
 * sui fattori primi.
 *
 * @author Matteo Machella
 * matteo.machella@studenti.unicam.it
 */
public class Factoriser {

    private CrivelloDiEratostene crivello;

    private Factor[] factors;

    /**
     * Fattorizza un numero restituendo la sequenza crescente dei suoi fattori
     * primi. La molteplicità di ogni fattore primo esprime quante volte il
     * fattore stesso divide il numero fattorizzato. Per convenzione non viene
     * mai restituito il fattore 1. Il minimo numero fattorizzabile è 1. In
     * questo caso viene restituito un array vuoto.
     *
     * @param n un numero intero da fattorizzare
     * @return un array contenente i fattori primi di n
     * @throws IllegalArgumentException se si chiede di fattorizzare un
     *                                  numero minore di 1.
     */
    public Factor[] getFactors(int n) {
        if (n < 1) {
            throw new IllegalArgumentException("Inserire un numero maggiore di 0");
        }
        // se n è uguale a 1, creo il nuovo fattore e lo restituisco
        if (n == 1) {
            this.factors = new Factor[0];
            return this.factors;
        }
        // creo un array di fattori con lunghezza imprecisa usando la radice di n
        this.factors = new Factor[(int) Math.sqrt(n)];
        this.crivello = new CrivelloDiEratostene(n);
        // se n è primo restituisco factors
        if (this.crivello.isPrime(n)) {
            this.factors[0] = new Factor(n, 1);
            return this.factors;
        }
        int multiplicity = 0;
        int nextPrime;
        int index = 0;
        // controllo se nel crivello ci sono altri primi
        while (this.crivello.hasNextPrime()) {
            nextPrime = this.crivello.nextPrime();
            // se n è divisibile per il numero primo allora incremento la molteplicità e lo divido
            while (n % nextPrime == 0) {
                multiplicity++;
                n /= nextPrime;
            }
            // se la moltiplicità è stata modificata allora inserisco il fattore primo e resetto la molteplicità
            if (multiplicity != 0) {
                this.factors[index++] = new Factor(nextPrime, multiplicity);
                multiplicity = 0;
            }
        }
        int newLength = 0;
        // prendo tutte le caselle occupate di factors e incremento ogni volta newLength
        for (Factor factor : this.factors) {
            if (factor != null) {
                newLength++;
            }
        }
        // creo un nuovo array passandogli la lunghezza precisa usando newLength e passandogli i fattori
        Factor[] preciseArray = new Factor[newLength];
        for (int i = 0; i < preciseArray.length; i++) {
            preciseArray[i] = this.factors[i];
        }
        return preciseArray;
    }
}
