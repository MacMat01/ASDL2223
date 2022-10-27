/**
 * Esempi di uso delle {@code ADTConsList<E>}.
 *
 * @author Luca Tesei
 */
public class ADTListsExamples {

    @SuppressWarnings("unchecked")
    public static void main(String[] args) {
        ADTConsList<Integer> l1 = ADTConsList.EMPTY_LIST.cons(3);
        ADTConsList<Integer> l2 = l1.cons(4);
        ADTConsList<Integer> l3 = l2.cons(5);
        ADTConsList<Integer> l4 = l3.cons(4);
        ADTConsList<Integer> l5 = l4.cons(6);
        ADTConsList<Integer> l6 = l5.cons(7);
        ADTConsList<Integer> l7 = l6.cons(8);
        ADTConsList<Integer> l8 = l7.cons(9);
        System.out.println(l8.print());
        if (l8.find(4)) System.out.println("4 è contenuto");
        else System.out.println("4 non è contenuto");
        ADTConsList<Integer> l9 = l8.removeFirst(4);
        System.out.println(l9.print());
        ADTConsList<Integer> l10 = l8.removeAll(4);
        System.out.println(l10.print());
        if (l10.find(4)) System.out.println("4 è contenuto");
        else System.out.println("4 non è contenuto");
        ADTConsList<Integer> l11 = l3.cons(6);
        ADTConsList<Integer> l12 = l11.cons(10);
        ADTConsList<Integer> l13 = l12.cons(11);
        System.out.println(l13.print());
        ADTConsList<Integer> l14 = l13.append(l10);
        System.out.println("Append: " + l14.print());
        System.out.println("Reversed: " + l14.reverse().print());
        System.out.println("Aggiornato il primo 6 con 100: " + l14.updateFirst(6, 100).print());
        System.out.println("Aggiornati tutti i 6 con 100: " + l14.updateAll(6, 100).print());
        ADTConsList<Integer> l15 = ADTConsList.EMPTY_LIST.cons(1);
        ADTConsList<Integer> l16 = l15.cons(1);
        System.out.println(l16.print());
        ADTConsList<Integer> l17 = l16.removeAll(1);
        if (l17.isEmpty()) System.out.println("Lista vuota");
        else System.out.println("Lista non vuota");
    }

}
