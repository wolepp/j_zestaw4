package mandelbrot;

/**
 * Klasa do obsługi liczb zespolonych.
 *
 * @author Wojciech Lepich
 */

public class Complex implements Field<Complex> {

    private double r, i;

    /**
     * Konstruuje liczbę <i>Complex</i> = 0
     */
    public Complex() {
        this(0d);
    }

    /**
     * Konstruuje liczbę <i>Complex</i> = <i>r</i> + 0i
     */
    public Complex(double r) {
        this(r, 0);
    }

    /**
     * Konstruuje liczbę <i>Complex</i> = <i>r</i> + <i>i</i>*i
     */
    public Complex(double r, double i) {    //Główny konstruktor
        this.r = r;
        this.i = i;
    }

    /**
     * Konstruuje liczbę <i>Complex</i> o wartości reprezentowanej
     * przez String s
     *
     * @param s String w formacie "-1.23+4.56i"
     */
    public Complex(String s) {
        this(Complex.valueOf(s));
    }

    /**
     * Konstruuje liczbę <i>Complex</i> o wartości równej liczbie c
     *
     * @param c liczba Complex
     */
    public Complex(Complex c) {
        this(c.r, c.i);
    }


    /**
     * Dodaje liczbę c
     *
     * @param c dodawana liczba Complex
     * @return ten obiekt
     */
    public Complex add(Complex c) {
        this.r += c.r;
        this.i += c.i;
        return this;
    }

    /**
     * Odejmuje liczbę c
     *
     * @param c odjemnik typu Complex
     * @return ten obiekt
     */
    public Complex sub(Complex c) {
        this.r -= c.r;
        this.i -= c.i;
        return this;
    }

    /**
     * Mnoży z liczbą c
     *
     * @param c czynnik mnożenia
     * @return ten obiekt pomnożony przez c
     */
    public Complex mul(Complex c) {
        double r = this.r * c.r - this.i * c.i;
        double i = this.r * c.i + this.i * c.r;
        this.r = r;
        this.i = i;
        return this;
    }

    /**
     * Dzieli przez liczbę c
     *
     * @param c dzielnik
     * @return ten obiekt podzielony przez c
     * @throws ArithmeticException jeżeli następuje dzielenie przez 0
     */
    public Complex div(Complex c) throws ArithmeticException {
        if (c.r == 0 && c.i == 0)
            throw new ArithmeticException("Dzielenie przez 0");
        else {
            double mianownik = (c.r * c.r) + (c.i * c.i);
            double r = this.r * c.r + this.i * c.i;
            double i = this.i * c.r - this.r * c.i;
            this.r = r;
            this.i = i;
            this.r /= mianownik;
            this.i /= mianownik;
        }
        return this;
    }

    /**
     * Moduł liczby zespolonej
     *
     * @return zwraca moduł double liczby zespolonej
     */
    public double abs() {
        return Math.sqrt(sqrAbs());
    }

    /**
     * Kwadrat modułu liczby zespolonej
     *
     * @return Kwadrat modułu liczby zespolonej
     */
    public double sqrAbs() {
        return (Math.pow(this.r, 2) + Math.pow(this.i, 2));
    }

    /**
     * Faza liczby zespolonej. Wartości z zakresu <i>pi</i> do <i>-pi</i>
     *
     * @return fazę liczby zespolonej
     */
    public double phase() {
        return Math.atan2(this.i, this.r);
    }

    /**
     * Część rzeczywista liczby urojonej
     *
     * @return część rzeczywisą liczby zespolonej
     */
    public double re() {    // Część rzeczywista
        return this.r;
    }

    /**
     * Część urojona liczby zespolonej
     *
     * @return część rzeczywisą liczby zespolonej
     */
    public double im() {    // Część urojona
        return this.i;
    }

    /**
     * Dodaje dwie liczby zespolone.
     *
     * @param a pierwszy składnik sumy
     * @param b drugi składnik sumy
     * @return nową liczbę zespoloną, będącą sumą <i>a</i> i <i>b</i>
     */
    public static Complex add(Complex a, Complex b) {
        return new Complex(a).add(b);
    }

    /**
     * Odejmuje dwie liczby zespolone.
     *
     * @param a odjemna
     * @param b odjemnik
     * @return nową liczbę zespoloną, będącą różnicą <i>a</i> i <i>b</i>
     */
    public static Complex sub(Complex a, Complex b) {
        return new Complex(a).sub(b);
    }

    /**
     * Mnoży dwie liczby zespolone.
     *
     * @param a pierwszy czynnik
     * @param b drugi czynnik
     * @return nową liczbę zespoloną, będącą iloczynem <i>a</i> i <i>b</i>
     */
    public static Complex mul(Complex a, Complex b) {
        return new Complex(a).mul(b);
    }

    /**
     * Dzieli dwie liczby zespolone. Wyrzuca wyjątek jeżeli <tt>b = 0</tt>
     *
     * @param a dzielna
     * @param b dzielnik
     * @return nową liczbę zespoloną, będąca ilorazem <i>a</i> i <i>b</i>
     */
    public static Complex div(Complex a, Complex b) throws ArithmeticException {
        return new Complex(a).div(b);
    }

    /**
     * Zwraca moduł liczby <i>c</i>
     *
     * @param c liczba zespolona
     * @return moduł liczby <i>c</i>
     */
    public static double abs(Complex c) {
        return c.abs();
    }

    /**
     * Zwraca fazę liczby <i>c</i>. Faza jest z przedziału <i>-pi</i> do <i>pi</i>
     *
     * @param c liczba zespolona
     * @return fazę liczby <i>c</i>
     */
    public static double phase(Complex c) {
        return c.phase();
    }

    /**
     * Kwadrat modułu liczby zespolonej <i>c</i>
     *
     * @param c liczba zespolona
     * @return kwadrat modułu liczby <i>c</i>
     */
    public static double sqrAbs(Complex c) {
        return c.sqrAbs();
    }

    /**
     * Zwraca część rzeczywistą liczby <i>c</i>
     *
     * @param c liczba zespolona
     * @return część rzeczywistą liczby <i>c</i>
     */
    public static double re(Complex c) {
        return c.re();
    }

    /**
     * Zwraca część urojoną liczby zespolonej <i>c</i>
     *
     * @param c liczba zespolona
     * @return część urojoną liczby <i>c</i>
     */
    static double im(Complex c) {
        return c.im();
    }

    /**
     * Zwraca String reprezentujący wartość liczby zespolonej.
     * String jest w formacie "-1.23+4.56i"
     *
     * @return String reprezentujący liczbę zespoloną
     */
    @Override
    public String toString() {

        if (this.i >= 0)
            return String.valueOf(this.r) + "+" + String.valueOf(this.i) + "i";
        return String.valueOf(this.r) + String.valueOf(this.i) + "i";
    }

    /**
     * Zwraca Complex z wartościami z argumentu s
     *
     * @param s String z wartością liczby zespolonej
     * @return liczbę zespoloną o wartości reprezentowanej przez s
     */
    public static Complex valueOf(String s) {

        // pozbycie się białych spacji z przodu i z tyłu
        s = s.trim();

        // indeks znaku '+' lub '-' stojącego przy części urojonej
        int beginOfImag = s.indexOf('-', 1) * s.indexOf('+', 1) * (-1);

        String real = s.substring(0, beginOfImag);
        String imag = s.substring(beginOfImag, s.length() - 1);

        return new Complex(Double.valueOf(real), Double.valueOf(imag));
    }

    /**
     * Ustawia wartość części rzeczywistej liczby zespolonej
     *
     * @param r wartość rzeczywista
     */
    public void setRe(double r) {
        this.r = r;
    }

    /**
     * Ustawia wartość części urojonej liczby zespolonej
     *
     * @param i wartość urojona
     */
    public void setIm(double i) {
        this.i = i;
    }

    /**
     * Ustawia wartość liczby zespolonej na wartość
     * równą liczbie zespolonej <i>c</i> podanej w argumencie
     *
     * @param c liczba zespolona
     */
    public void setVal(Complex c) {
        this.r = c.r;
        this.i = c.i;
    }

    /**
     * Ustawia wartości rzeczywistą i urojoną liczby zespolonej
     * na wartości podane w argumentach
     *
     * @param r część rzeczywista
     * @param i część urojona
     */
    public void setVal(double r, double i) {
        this.r = r;
        this.i = i;
    }
}