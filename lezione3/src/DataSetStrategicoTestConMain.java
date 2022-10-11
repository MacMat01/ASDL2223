import java.awt.Rectangle;

/**
 * Testa la classe DataSetStrategico che utilizza l'interfaccia strategica
 * Measurer
 */
public class DataSetStrategicoTestConMain {

    public static void main(String argv[]) {
        /**
         * Un oggetto RectangleAreaMeasurer è in grado di misurare un oggetto
         * della classe <code>Rectangle</code>. La misura restituita dalla
         * misurazione è l'area del rettangolo.
         *
         * @author Luca Tesei
         *
         */
        class RectangleAreaMeasurer implements Measurer {
            /**
             * Misura un oggetto che deve essere della classe
             * <code>Rectangle</code>.
             *
             * @param anObject
             *                     l'oggetto <code>Rectangle</code> da misurare
             * @return l'area del rettangolo passato
             * @throws IllegalArgumentException
             *                                      se anObject non è un oggetto
             *                                      <code>Rectangle</code>
             */
            public double measure(Object anObject) {
                if (!(anObject instanceof Rectangle))
                    throw new IllegalArgumentException("Tentativo di misurare" + " un oggetto che non è un Rectangle con un " + "misuratore strategico che misura solo Rectangle");
                Rectangle aRectangle = (Rectangle) anObject;
                double area = aRectangle.getHeight() * aRectangle.getWidth();
                return area;
            }
        }

        class RectanglePerimeterMeasurer implements Measurer {
            /**
             * Misura un oggetto che deve essere della classe
             * <code>Rectangle</code>.
             *
             * @param anObject
             *                     l'oggetto <code>Rectangle</code> da misurare
             * @return l'area del rettangolo passato
             * @throws IllegalArgumentException
             *                                      se anObject non è un oggetto
             *                                      <code>Rectangle</code>
             */
            public double measure(Object anObject) {
                if (!(anObject instanceof Rectangle))
                    throw new IllegalArgumentException("Tentativo di misurare" + " un oggetto che non è un Rectangle con un " + "misuratore strategico che misura solo Rectangle");
                Rectangle aRectangle = (Rectangle) anObject;
                double perimetro = 2 * aRectangle.getHeight() + 2 * aRectangle.getWidth();
                return perimetro;
            }
        }
        // Creo il DataSetStrategico con il misuratore di area
        // Variabile polimorfa
        Measurer m = new RectangleAreaMeasurer();
        DataSetStrategico data = new DataSetStrategico(m);
        // inserisco alcuni rettangoli
        Rectangle r1 = new Rectangle(0, 4, 20, 12);
        data.add(r1);
        System.out.println("Rettangolo 1 - Coordinate: (0, 4) Base: " + r1.getWidth() + " Altezza: " + r1.getHeight() + " Area: " + r1.getHeight() * r1.getWidth() + " Perimetro: " + (2 * r1.getHeight() + 2 * r1.getWidth()));
        Rectangle r2 = new Rectangle(0, 2, 15, 15);
        data.add(r2);
        System.out.println("Rettangolo 2 - Coordinate: (0, 2) Base: " + r2.getWidth() + " Altezza: " + r2.getHeight() + " Area: " + r2.getHeight() * r2.getWidth() + " Perimetro: " + (2 * r2.getHeight() + 2 * r2.getWidth()));
        Rectangle r3 = new Rectangle(0, 0, 10, 20);
        data.add(r3);
        System.out.println("Rettangolo 3 - Coordinate: (0, 0) Base: " + r3.getWidth() + " Altezza: " + r3.getHeight() + " Area: " + r3.getHeight() * r3.getWidth() + " Perimetro: " + (2 * r3.getHeight() + 2 * r3.getWidth()));
        // Chiedo media, massimo e minimo
        System.out.println("Media delle aree: " + data.getAverage());
        System.out.println("Rettangolo con area maggiore: " + data.getMaximum());
        System.out.println("Rettangolo con area minore: " + data.getMinimum());

        // Creo il DataSetStrategico con il misuratore di perimetro
        // Variabile polimorfa
        m = new RectanglePerimeterMeasurer();
        DataSetStrategico datap = new DataSetStrategico(m);
        // inserisco i rettangoli
        datap.add(r1);
        datap.add(r2);
        datap.add(r3);
        // Chiedo media, massimo e minimo
        System.out.println("Media dei perimetri: " + datap.getAverage());
        System.out.println("Rettangolo con perimetro maggiore: " + datap.getMaximum());
        System.out.println("Rettangolo con perimetro minore: " + datap.getMinimum());
    }
}
