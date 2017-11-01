package CourseWork;

import Exceptions.IncorrectSizeException;
import Numbers.Number;

public class MatrixOperationLib {

    public static Matrix multiplication (Matrix first, Matrix second)
            throws IncorrectSizeException {
        if (!first.getColumn().equals(second.getRow())) {
            throw new IncorrectSizeException("Multiplication is impossible, because matrixes" +
                    " has unacceptable size");
        }
        Matrix result = new Matrix(first.getRow(), second.getColumn());
        for (int i = 0; i < first.getRow(); i++) {
            for (int j = 0; j < second.getColumn(); j++) {
                result.setMatrixElement(i, j, first.getMatrixElement(0,0).mulWithDouble(0.0));
                for (int k = 0; k < second.getRow(); k++) {
                    result.setMatrixElement(i, j,result.getMatrixElement(i,j).
                            plus(first.getMatrixElement(i,k).mul(second.getMatrixElement(k,j))));
                }
            }
        }
        return result;
    }

    public static Matrix multiplication(Matrix first, Double x ){
        Matrix result = new Matrix(first.getRow(), first.getColumn());
        for (int i = 0; i < result.getRow();i++){
            for(int j = 0; j < result.getColumn(); j++){
                result.setMatrixElement(i,j,first.getMatrixElement(i,j).mulWithDouble(x));
            }
        }
        return result;
    }
    public static Matrix pow (Matrix first, Integer n) throws IncorrectSizeException{
        if(!first.getRow().equals(first.getColumn())){
            throw new IncorrectSizeException("Matrix must be square");
        }
        Matrix result = new Matrix(first);
        try{
            for(int i = 0; i < n-1; i++) {
                result.setMatrix(multiplication(result, first).getMatrix());
            }
        }catch(IncorrectSizeException e){
            e.getMassage();
            e.printStackTrace();
        }
        return result;
    }
    public static Matrix transpose (Matrix first){
        Matrix result = new Matrix(first.getColumn(), first.getRow());
        for(int i = 0; i< result.getRow(); i++){
            for(int j = 0; j < result.getColumn(); j++){
                result.setMatrixElement(i,j,first.getMatrixElement(j,i));
            }
        }
        return result;
    }
    public static Matrix sum (Matrix first, Matrix second) throws IncorrectSizeException{
        if(!first.getRow().equals(second.getRow()) || !first.getColumn().equals(second.getColumn())){
            throw new IncorrectSizeException("Incorrect size for summation");
        }
        Matrix result = new Matrix(first.getRow(), first.getColumn());
        for( int i = 0; i < result.getRow(); i++){
            for(int j = 0; j < result.getColumn(); j++){
                result.setMatrixElement(i,j,first.getMatrixElement(i,j).plus(second.getMatrixElement(i,j)));
            }
        }
        return result;
    }
    public static Matrix difference (Matrix first, Matrix second) throws IncorrectSizeException{
        if(!first.getRow().equals(second.getRow()) || !first.getColumn().equals(second.getColumn())){
            throw new IncorrectSizeException("Incorrect size for summation");
        }
        Matrix result = new Matrix(first.getRow(), first.getColumn());
        for( int i = 0; i < result.getRow(); i++){
            for(int j = 0; j < result.getColumn(); j++){
                result.setMatrixElement(i,j,first.getMatrixElement(i,j).minus(second.getMatrixElement(i,j)));
            }
        }
        return result;
    }
    public static Matrix inversion (Matrix first) throws IncorrectSizeException {

        if(!first.isSquare()){
            throw new IncorrectSizeException("Matrix is not a square");
        }

        Matrix matrix = (Matrix) Matrix.deepClone(first);

        Integer N = matrix.getRow();
        Number temp;
        Matrix result = new Matrix(N,N);
        Number zero = first.getMatrixElement(0,0).mulWithDouble(0.0);
        Number one = zero.ret1();

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                result.setMatrixElement(i, j, zero);
                if (i == j)
                    result.setMatrixElement(i, j, one); // тут костыль, незнаю как исправить. Нужно присвоить 1
            }
        }
        for (int k = 0; k < N; k++){
            temp = matrix.getMatrixElement(k,k);
            for (int j = 0; j < N; j++) {
                matrix.setMatrixElement(k,j,matrix.getMatrixElement(k,j).div(temp));
                result.setMatrixElement(k,j,result.getMatrixElement(k,j).div(temp));
            }

            for (int i = k + 1; i < N; i++) {
                temp = matrix.getMatrixElement(i,k);
                for (int j = 0; j < N; j++) {
                    matrix.setMatrixElement(i,j, matrix.getMatrixElement(i,j).minus(matrix.getMatrixElement(k,j).mul(temp)));
                    result.setMatrixElement(i,j, result.getMatrixElement(i,j).minus(result.getMatrixElement(k,j).mul(temp)));
                }
            }
        }

        for (int k = N - 1; k > 0; k--) {
            for (int i = k - 1; i >= 0; i--) {
                temp = matrix.getMatrixElement(i,k);
                for (int j = 0; j < N; j++) {
                    matrix.setMatrixElement(i,j, matrix.getMatrixElement(i,j).minus(matrix.getMatrixElement(k,j).mul(temp)));
                    result.setMatrixElement(i,j, result.getMatrixElement(i,j).minus(result.getMatrixElement(k,j).mul(temp)));
                }
            }
        }

        return result;

    }

}

