/**
 * Testa la classe DataSetPolimorfo
 */
public class DataSetPolimorfoTestConMain {

    public static void main(String argv[]) {
        // Data set di conti
        DataSetPolimorfo bankData = new DataSetPolimorfo();
        bankData.add(new BankAccount(0));
        bankData.add(new BankAccount(10000));
        bankData.add(new BankAccount(1000));

        System.out.println("Media dei saldi: " + bankData.getAverage());
        Measurable max = bankData.getMaximum();
        Measurable min = bankData.getMinimum();
        System.out.println("Massimo saldo: " + max.getMeasure());
        System.out.println("Minimo saldo: " + min.getMeasure());

        // Data set di monete
        DataSetPolimorfo coinData = new DataSetPolimorfo();
        coinData.add(new Coin(0.25, "Quarter"));
        coinData.add(new Coin(0.10, "Dime"));
        coinData.add(new Coin(0.05, "Nickel"));

        System.out.println("Media dei valori: " + coinData.getAverage());
        max = coinData.getMaximum();
        min = coinData.getMinimum();
        System.out.println("Massimo valore: " + max.getMeasure());
        System.out.println("Minimo valore: " + min.getMeasure());

        // Data set misto!
        DataSetPolimorfo mixData = new DataSetPolimorfo();
        mixData.add(new BankAccount(0.75));
        mixData.add(new Coin(0.01, "Penny"));
        mixData.add(new BankAccount(0.23));

        System.out.println("Media dei valori: " + mixData.getAverage());
        max = mixData.getMaximum();
        min = mixData.getMinimum();
        System.out.println("Massimo valore: " + max.getMeasure());
        System.out.println("Minimo valore: " + min.getMeasure());

        // Conversioni di tipo
        BankAccount account = new BankAccount(10000);
        @SuppressWarnings("unused") Measurable x = account; // Conversione legittima
        // System.out.println(x);  // Stampa il risultato di toString() per BankAccount
        // System.out.println(x.getMeasure()); // Stampa 10000.0
        // x.deposit(100); Non si può fare!
        account.deposit(100); // Si può fare
        Measurable dime = new Coin(0.10, "Dime");
        // Measurable pippo = new Measurable(); // non si può fare
        // String s = dime.getDescription();  // vietato
        // Cast esplicito con controllo instanceof
        if (dime instanceof Coin) {// in questo caso sicuramente vero
            Coin c = (Coin) dime;
            System.out.println("Moneta: " + c.getName());
        }

    }
}
