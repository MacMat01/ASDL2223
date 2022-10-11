/**
 * Classe di InteractiveTest Per DataSet
 *
 * @author luca
 */
public class DataSetTestConMain {

    public static void main(String[] args) {
        DataSet d = new DataSet();
        System.out.println("Media corrente dei valori: " + d.getAverage());
        d.add(1.5);
        d.add(3);
        d.add(5.5);
        System.out.println("Valore massimo aggiunto finora: " + d.getMaximum());
        System.out.println("Valore minimo aggiunto finora: " + d.getMinimum());
        System.out.println("Media corrente dei valori: " + d.getAverage());
    }

}
