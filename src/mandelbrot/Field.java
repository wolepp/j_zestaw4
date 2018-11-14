package mandelbrot;

/**
 * Ciało (matematyczne). Deklaruje metody dodawania, odejmowania,
 * mnożenia oraz dzielenia.
 * Posiada również metodę toString, zwracającą String reprezentujący
 * obiekt w czytelny sposób.
 *
 * @param <T> typ
 */
public interface Field<T> {
    /**
     * Dodaje wartość obiektu <tt>t</tt> do obiektu na którym metoda jest
     * wywołana i zwraca ten obiekt.
     *
     * @param t dodawany obiekt
     * @return ten sam obiekt zsumowany z <tt>t</tt>
     */
    T add(T t);

    /**
     * Odejmuje wartość obiektu <tt>t</tt> od obiektu na którym metoda jest
     * wywołana i zwraca ten obiekt.
     *
     * @param t odejmowany obiekt
     * @return ten sam obiekt, pomniejszony o wartość <tt>t</tt>
     */
    T sub(T t);

    /**
     * Mnoży wartość obiektu na którym metoda jest wywołana przez <tt>t</tt>
     * i zwraca ten obiekt
     *
     * @param t mnożony obiekt
     * @return ten sam obiekt, pomnożony przez wartość <tt>t</tt>
     */
    T mul(T t);

    /**
     * Dzieli wartość obiektu na którym metoda jest wywołana przez <tt>t</tt>
     * i zwraca ten obiekt
     *
     * @param t obiekt będący dzielnikiem
     * @return ten sam obiekt, podzielony przez wartość <tt>t</tt>
     */
    T div(T t);

    /**
     * Zwraca String będący czytelną reprezentacją obiektu typu <tt>T</tt>
     *
     * @return String będący czytelną reprezentacją obiektu typu <tt>T</tt>
     */
    String toString();
}
