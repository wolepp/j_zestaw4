package mandelbrot;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.PixelFormat;
import javafx.scene.image.PixelWriter;
import javafx.stage.Stage;

public class MandelFractal extends Application implements ComplexDrawable {

    private double r;
    final private int N;

    public MandelFractal() {
        this(2d);
    }

    public MandelFractal(double r) {
        this(r, 100);
    }

    public MandelFractal(double r, int N) {
        this.r = r;
        this.N = N;
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("mandel.fxml"));
        primaryStage.setTitle("Mandelbrot");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    @Override
    public void draw(PixelWriter pw, Complex a, Complex b, int w, int h) {

        double maxRe = Math.max(a.re(), b.re());
        double minRe = Math.min(a.re(), b.re());
        double maxIm = Math.max(a.im(), b.im());
        double minIm = Math.min(a.im(), b.im());
        double deltaRe = Math.abs(maxRe - minRe) / w;
        double deltaIm = Math.abs(maxIm - minIm) / h;
        Complex p = new Complex(minRe, maxIm);

        for (int y = 0; y < h; y++) {
            for (int x = 0; x < w; x++) {
                pw.setArgb(x, y, colour(p));
                p.setRe(p.re() + deltaRe);
            }
            p.setRe(minRe);
            p.setIm(p.im()-deltaIm);
        }
    }

    /**
     * Funkcja zwraca kolor piksela
     * @param c - liczba Complex
     * @return
     */
    private int colour(Complex c) {

        int predkosc = predkoscUcieczki(c);

        //zbieżny
        if (predkosc == N)
            return 0xFF000000;  //czarny

        // 0xAARRGGBB
        // A - alfa: przezroczystosc (FF - pełny kolor, 00 - pełna przezroczystosc)

        //predkosc ucieczki, w przedziale [0, 100]
        double procentPredkosci = ((double) predkosc / (double) N) * 100;
        int red, green, blue;
        red = green = blue = 0;

        if (procentPredkosci <= 50) {      //R
            //ratio - transformacja z [0, 50] na [0, 1]
            double ratio = 1d - 2d * procentPredkosci;
            //pomnożenie przez maksymalną wartość
            red = (int) ratio * 0xFF;
            //przesunięcie: 0x000000RR -> 0x00RR000000
            red = red << 16;
        }

        if (25 < procentPredkosci && procentPredkosci < 75) {   //G

            double ratio = (procentPredkosci - 0.25) * 2;
            green = (int) ratio * 0xFF;
            green = green << 8;
        }

        if (procentPredkosci > 50) {    //B
            blue = (int) (((procentPredkosci - 0.5) * 2) * 0xFF);
        }

        int kolor = (red | green | blue);
        kolor |= 0xFF000000;

        return kolor;
    }

    private int predkoscUcieczki(Complex c) {

        int v = 0;
        Complex z1 = new Complex(c);    //z1 = 0+0i + c
        for (int n = 2; n <= N; n++) {
            v = n;
            z1.mul(z1).add(c);
            if (z1.sqrAbs() > (r * r))
                break;
        }

        return v;
    }

}
