package Numbers;

import javafx.scene.control.Alert;

import java.io.Serializable;
import java.util.Objects;
import java.util.StringTokenizer;

public class ComplexNumber extends Number implements Serializable{

    private Double re = 0.0;   // the real part
    private Double im = 0.0;   // the imaginary part

    public ComplexNumber(Double re, Double im) {
        this.re = re;
        this.im = im;
    }
    public ComplexNumber(){
        this.setRe(0.0);
        this.setIm(0.0);
    }

    public void setNumber(String num){
        try {
            StringTokenizer st = new StringTokenizer(num, " +-i", true);
            Integer iter = 0;
            this.setRe(1.0);
            while (st.hasMoreTokens()) {
                String temp = st.nextToken();
                if (temp.equals(" ")) {
                    continue;
                }
                if (iter.equals(0) && temp.equals("-")) {
                    this.setRe(-1.0);
                    continue;
                }
                if (iter.equals(0)) {
                    this.setRe(getRe() * Double.parseDouble(temp));
                }
                if (iter.equals(1) && temp.equals("-")) {
                    this.setIm(-1.0);
                    continue;
                }
                if (iter.equals(1) && temp.equals("+")) {
                    this.setIm(1.0);
                    continue;
                }
                if (iter.equals(1)) {
                    this.setIm(getIm() * Double.parseDouble(temp));
                }
                iter++;
            }
        }catch(Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.showAndWait();
        }
    }

    public Double getRe() { return re; }

    public void setRe(Double re){
        this.re = re;
    }

    public Double getIm() { return im; }

    public void setIm(Double im){
        this.im = im;
    }

    public Number ret1(){
        return new ComplexNumber(1.0,0.0);
    }


    public Number plus(Number b){
        return (Number)this.plus((ComplexNumber)b);
    }
    public Number minus(Number b){
        return (Number)this.minus((ComplexNumber)b);
    }
    public Number mul(Number b){
        return (Number)this.mul((ComplexNumber)b);
    }
    public Number div(Number b){
        return (Number)this.divides((ComplexNumber)b);
    }
    public Number mulWithDouble(Double b){
        return new ComplexNumber(this.re*b,this.im*b);
    }

    @Override
    public String toString() {
        if (im == 0) return re + "";
        if (re == 0) return im + "i";
        if (im <  0) return re + " - " + (-im) + "i";
        return re + " + " + im + "i";
    }

    public double abs() {
        return Math.hypot(re, im);
    }
    public double phase() {
        return Math.atan2(im, re);
    }
    private ComplexNumber plus(ComplexNumber b) {
        ComplexNumber a = this;             // invoking object
        double real = a.re + b.re;
        double imag = a.im + b.im;
        return new ComplexNumber(real, imag);
    }
    private ComplexNumber minus(ComplexNumber b) {
        ComplexNumber a = this;
        double real = a.re - b.re;
        double imag = a.im - b.im;
        return new ComplexNumber(real, imag);
    }
    private ComplexNumber mul(ComplexNumber b) {
        ComplexNumber a = this;
        double real = a.re * b.re - a.im * b.im;
        double imag = a.re * b.im + a.im * b.re;
        return new ComplexNumber(real, imag);
    }
    private ComplexNumber divides(ComplexNumber b) {
        ComplexNumber a = this;
        return a.mul(b.reciprocal());
    }

    public ComplexNumber scale(double alpha) {
        return new ComplexNumber(alpha * re, alpha * im);
    }
    public ComplexNumber conjugate() {
        return new ComplexNumber(re, -im);
    }
    public ComplexNumber reciprocal() {
        double scale = re*re + im*im;
        return new ComplexNumber(re / scale, -im / scale);
    }


    public ComplexNumber exp() {
        return new ComplexNumber(Math.exp(re) * Math.cos(im), Math.exp(re) * Math.sin(im));
    }
    public ComplexNumber sin() {
        return new ComplexNumber(Math.sin(re) * Math.cosh(im), Math.cos(re) * Math.sinh(im));
    }
    public ComplexNumber cos() {
        return new ComplexNumber(Math.cos(re) * Math.cosh(im), -Math.sin(re) * Math.sinh(im));
    }
    public ComplexNumber tan() {
        return sin().divides(cos());
    }

    @Override
    public boolean equals(Object x) {
        if (x == null) return false;
        if (this.getClass() != x.getClass()) return false;
        ComplexNumber that = (ComplexNumber) x;
        return (this.re == that.re) && (this.im == that.im);
    }
    @Override
    public int hashCode() {
        return Objects.hash(re, im);
    }
}
