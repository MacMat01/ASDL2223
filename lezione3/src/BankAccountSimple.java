/**
 * Un oggetto conto bancario ha un balance che può essere modificato da depositi
 * e prelievi.
 */
public class BankAccountSimple {

    /* Il balance del conto */
    private double balance;

    /**
     * Costruisce un conto bancario con saldo uguale a zero.
     */
    public BankAccountSimple() {
        this.balance = 0.0;
    }

    /**
     * Costruisce un conto bancario con un saldo assegnato.
     *
     * @param initialBalance il saldo iniziale
     */
    public BankAccountSimple(double initialBalance) {
        this.balance = initialBalance;
    }

    /**
     * Versa denaro nel conto bancario.
     *
     * @param amount l'importo da versare
     */
    public void deposit(double amount) {
        this.balance = this.balance + amount;
    }

    /**
     * Preleva denaro dal conto bancario.
     *
     * @param amount l'importo da prelevare
     */
    public void withdraw(double amount) {
        this.balance -= amount;
    }

    /**
     * Ispeziona il valore del saldo attuale del conto bancario.
     *
     * @return il saldo attuale
     */
    public double getBalance() {
        return this.balance;
    }

}
