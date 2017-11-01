package CourseWork;

import Exceptions.OutOfMatrixException;
import Numbers.Number;
import java.io.*;

public class Matrix implements Cloneable, Serializable {

    private Integer row = 0;
    private Integer column = 0;
    private Number[][] matrix = new Number[row][column];

    public Matrix() {
    }
    public Matrix(Integer row, Integer column, Number[][] matrix) {
        this.row = row;
        this.column = column;
        this.matrix = matrix;
    }
    public Matrix(Integer row, Integer column) {

        this.row = row;
        this.column = column;
        matrix = new Number[row][column];
    }
    public Matrix(Matrix other) {
        row = other.getRow();
        column = other.getColumn();
        matrix = other.getMatrix();
    }

    public static Object deepClone(Object object) {
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(object);
            ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
            ObjectInputStream ois = new ObjectInputStream(bais);
            return ois.readObject();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Integer getRow() {
        return row;
    }

    public void setRow(Integer row) {
        this.row = row;
    }

    public Integer getColumn() {
        return column;
    }

    public void setColumn(Integer column) {
        this.column = column;
    }

    public Number[][] getMatrix() {
        return matrix;
    }

    public void setMatrix(Number[][] matrix) {
        this.matrix = matrix;
    }

    public Number getMatrixElement(Integer row, Integer column) {
        if (row > this.row)
            if (column > this.column)
                throw new OutOfMatrixException();
        return matrix[row][column];
    }

    public void setMatrixElement(Integer row, Integer column, Number n) {
        if (row < this.row)
            if (column < this.column)
                matrix[row][column] = n;
            else
                throw new OutOfMatrixException();
        else
            throw new OutOfMatrixException();
    }

    public Boolean isSquare() {
        return row.equals(column);
    }

    @Override
    public String toString() {
        String MatrixToString = "";
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                MatrixToString += matrix[i][j] + "\t";
            }
            MatrixToString += "\n";
        }
        return MatrixToString;
    }

    @Override
    public Matrix clone() {
        try {
            return (Matrix) super.clone();
        } catch (CloneNotSupportedException ex) {
            throw new InternalError();
        }
    }
}
