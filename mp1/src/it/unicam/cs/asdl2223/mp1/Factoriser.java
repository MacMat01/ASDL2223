package it.unicam.cs.asdl2223.mp1;

/**
 * Un fattorizzatore è un agente che fattorizza un qualsiasi numero naturale nei
 * sui fattori primi.
 *
 * @author Luca Tesei (template) // TODO INSERIRE NOME, COGNOME ED EMAIL
 * xxxx@studenti.unicam.it DELLO STUDENTE (implementazione)
 */
public class Factoriser {

    // TODO definire ulteriori variabili istanza che si ritengono necessarie per
    // implementare tutti i metodi

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
        // TODO implementare
        if (n < 1) {
            throw new IllegalArgumentException("Inserire un numero maggiore di 0");
        }
        if (n == 1) {
            factors = new Factor[0];
            return factors;
        }
        factors = new Factor[(int) Math.sqrt(n)];
        crivello = new CrivelloDiEratostene(n);
        if (crivello.isPrime(n)) {
            factors[0] = new Factor(n, 1);
            return factors;
        }
        int multiplicity = 0;
        int nextPrime;
        int index = 0;
        while (crivello.hasNextPrime()) {
            nextPrime = crivello.nextPrime();
            while (n % nextPrime == 0) {
                multiplicity++;
                n /= nextPrime;
            }
            if (multiplicity != 0) {
                factors[index++] = new Factor(nextPrime, multiplicity);
                multiplicity = 0;
            }
        }
        int newLength = 0;
        for (int i = 0; i < factors.length; i++) {
            if (factors[i] != null) {
                newLength++;
            }
        }
        Factor[] preciseArray = new Factor[newLength];
        for (int i = 0; i < preciseArray.length; i++) {
            preciseArray[i] = factors[i];
        }
        return preciseArray;
    }

    // TODO inserire eventuali metodi accessori privati per fini di
    // implementazione
}
