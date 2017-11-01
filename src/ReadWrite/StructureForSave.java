package ReadWrite;

import CourseWork.Matrix;
import java.io.Serializable;

public class StructureForSave implements Serializable{
    private Matrix first;
    private Matrix second;
    private Matrix result;
    private Double[] action;

    public StructureForSave(Matrix first, Matrix second, Matrix result, Double[] action) {
        this.first = first;
        this.second = second;
        this.result = result;
        this.action = action;
    }

    public StructureForSave() {
    }

    public Matrix getFirst() {
        return first;
    }
    public void setFirst(Matrix first) {
        this.first = first;
    }

    public Matrix getSecond() {
        return second;
    }
    public void setSecond(Matrix second) {
        this.second = second;
    }

    public Matrix getResult() {
        return result;
    }
    public void setResult(Matrix result) {
        this.result = result;
    }

    public Double[] getAction() {
        return action;
    }
    public void setAction(Double[] action) {
        this.action = action;
    }
}
