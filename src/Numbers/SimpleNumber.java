package Numbers;

import java.io.Serializable;

public class SimpleNumber extends Number implements Serializable{

    private Double x = 0.0;

    public SimpleNumber() {
        x = 0.0;
    }
    public SimpleNumber(Double value) {
        x = value;
    }

    public Double getX() {
        return x;
    }
    public void setX(Double x) {
        this.x = x;
    }
    public void setNumber(String x){this.x = Double.parseDouble(x);}
    public  Number ret1(){
        return new SimpleNumber(1.0);
    }

    public Number mulWithDouble(Double b){
        return new SimpleNumber(this.getX()*b);
    }
    public Number plus(Number b) {
        return (Number) this.plus((SimpleNumber) b);
    }
    public Number minus(Number b) {
        return (Number) this.minus((SimpleNumber) b);
    }
    public Number mul(Number b) {
        return (Number) this.mul((SimpleNumber) b);
    }
    public Number div(Number b) {
        return (Number) this.divides((SimpleNumber) b);
    }

    private SimpleNumber plus(SimpleNumber b) {
        return new SimpleNumber(this.getX() + b.getX());
    }
    private SimpleNumber minus(SimpleNumber b) {
        return new SimpleNumber(this.getX() - b.getX());
    }
    private SimpleNumber mul(SimpleNumber b) {
        return new SimpleNumber(this.getX() * b.getX());
    }
    private SimpleNumber divides(SimpleNumber b) {
        return new SimpleNumber(this.getX() / b.getX());
    }

    @Override
    public String toString() {
        return x.toString();
    }
}
