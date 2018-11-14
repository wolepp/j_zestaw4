package mandelbrot;

import javafx.event.ActionEvent;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.effect.BlendMode;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.control.TextField;

import java.util.Scanner;

public class MandelController {

    public Canvas canvas;
    public TextField paramR;
    public TextField complexA, complexB;
    public TextField width, height;
    private GraphicsContext gc;
    final private double Re1 = -2;
    final private double Re2 = 2;
    final private double Im1 = -2;
    final private double Im2 = 2;
    private double re1 = Re1;
    private double im1 = Im1;
    private double re2 = Re2;
    private double im2 = Im2;

    //współrzędne ramki
    private double x1, y1, x2, y2;

    private static double defaultHeight;
    private static double defaultWidth;

    public void initialize() {
        gc = canvas.getGraphicsContext2D();
        defaultHeight = canvas.getHeight();
        defaultWidth = canvas.getWidth();
        clear(gc);
    }

    public void mousePressed(MouseEvent mouseEvent) {
        x1 = mouseEvent.getX();
        y1 = mouseEvent.getY();
        x2 = x1;
        y2 = y1;
    }

    public void mouseMoves(MouseEvent mouseEvent) {
        double x = mouseEvent.getX();
        double y = mouseEvent.getY();
        gc.setGlobalBlendMode(BlendMode.DIFFERENCE);
        gc.setStroke(Color.WHITE);
        rect(gc);
        x2 = x;
        y2 = y;
        rect(gc);
    }

    public void mouseReleased(MouseEvent mouseEvent) {
        x2 = mouseEvent.getX();
        y2 = mouseEvent.getY();

        rect(gc);
        double deltaRe = re2-re1;
        double deltaIm = im2-im1;
        double tmpre1 = this.re1;
        double tmpim1 = this.im2;
        this.re1 = tmpre1 + ((x1 / canvas.getWidth()) * deltaRe);
        this.re2 = tmpre1 + ((x2 / canvas.getWidth()) * deltaRe);
        this.im1 = tmpim1 - ((y1 / canvas.getHeight()) * deltaIm);
        this.im2 = tmpim1 - ((y2 / canvas.getHeight()) * deltaIm);

        System.out.format("%f %f %f %f\n", x1, y1, x2, y2);

        draw();
    }

    public void clearCanvas(ActionEvent actionEvent) {
        clear(gc);
    }

    private void clear(GraphicsContext gc) {
        gc.setFill(Color.WHITE);
        gc.setGlobalBlendMode(BlendMode.SRC_OVER);
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
        complexA.clear();
        complexB.clear();
        paramR.clear();
        width.clear();
        height.clear();
        re1 = Re1;
        im1 = Im1;
        re2 = Re2;
        im2 = Im2;
    }

    private void rect(GraphicsContext gc) {
        //rysuje prostokąt o rogach (x1, y1) i (x2, y2)
        double x = x1;
        double y = y1;
        double w = x2 - x1;
        double h = y2 - y1;

        if (w < 0) {
            x = x2;
            w = -w;
        }

        if (h < 0) {
            y = y2;
            h = -h;
        }

        gc.strokeRect(x + 0.5, y + 0.5, w, h);
    }

    public void draw() {

        double r = getR();
        setParams();
        Complex a = new Complex(re1, im1);
        Complex b = new Complex(re2, im2);

        WritableImage wr = new WritableImage((int) canvas.getWidth(), (int) canvas.getHeight());
        PixelWriter pw = wr.getPixelWriter();

        MandelFractal mf = new MandelFractal(r);

        mf.draw(pw, a, b, (int) canvas.getWidth(), (int) canvas.getHeight());
        gc.setGlobalBlendMode(BlendMode.SRC_OVER);
        gc.drawImage(wr, 0, 0, canvas.getWidth(), canvas.getHeight());

    }

    private void setParams() {
        Scanner sc = new Scanner(height.getText());
        if (sc.hasNextInt())
            canvas.setHeight(sc.nextInt());
        else
            canvas.setHeight(defaultHeight);
        sc.close();

        sc = new Scanner(width.getText());
        if (sc.hasNextInt())
            canvas.setWidth(sc.nextInt());
        else
            canvas.setWidth(defaultWidth);
        sc.close();
    }

    private double getR() {
        double r = 4;
        Scanner sc = new Scanner(paramR.getText());
        if (sc.hasNextDouble())
            r = sc.nextDouble();
        sc.close();
        return r;
    }

    private Complex getA() {
        if (!complexA.getText().isEmpty())
            return new Complex(complexA.getText());
        else
            return new Complex(re1, im1);
    }

    private Complex getB() {
        if (!complexB.getText().isEmpty())
            return new Complex(complexB.getText());
        else
            return new Complex(re2, im2);
    }

    public void set(ActionEvent actionEvent) {
        this.re1 = getA().re();
        this.re2 = getB().re();
        this.im1 = getA().im();
        this.im2 = getB().im();
    }
}
