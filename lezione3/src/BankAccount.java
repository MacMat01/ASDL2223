/**
 * Un conto bancario ha un saldo che può essere modificato da depositi e
 * prelievi. La sua misura secondo l'interfaccia <code>Measurable</code> è data
 * dal saldo.
 *
 * @author Luca Tesei
 */
public class BankAccount implements Measurable {

    /* Saldo del conto */
    private double balance;

    /**
     * Costruisce un conto bancario con saldo uguale a zero
     */
    public BankAccount() {
        this.balance = 0.0;
    }

    /**
     * Restituisce la misura associata a questo conto, cioè il saldo.
     */
    public double getMeasure() {
        return this.balance;
    }

    /**
     * Costruisce un conto bancario con un saldo assegnato.
     *
     * @param initialBalance il saldo iniziale
     */
    public BankAccount(double initialBalance) {
        this.balance = initialBalance;
    }

    /**
     * Versa denaro nel conto bancario.
     *
     * @param amount l'importo da versare
     */
    public void deposit(double amount) {
        this.balance += amount;
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
     * Restituisce il valore del saldo attuale del conto bancario.
     *
     * @return il balance attuale
     */
    public double getBalance() {
        return this.balance;
    }

}
