package GUI;

import Adapter.DefaultTableAdapter;
import CourseWork.Matrix;
import Exceptions.IncorrectSizeException;
import Numbers.ComplexNumber;
import Numbers.SimpleNumber;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.util.StringConverter;

import java.net.URL;
import java.util.ResourceBundle;

import static CourseWork.MatrixOperationLib.*;


public class rootController implements Initializable {
    @FXML
    private TextField FirstMultiplyNumber;
    @FXML
    private TextField FirstPowNumber;
    @FXML
    private TextField SecondMultiplyNumber;
    @FXML
    private TextField SecondPowNumber;
    @FXML
    TableView firstTable;
    @FXML
    TableView secondTable;
    @FXML
    TableView resultTable;
    @FXML
    TextField firstNumberM;
    @FXML
    TextField firstNumberN;
    @FXML
    TextField secondNumberM;
    @FXML
    TextField secondNumberN;
    @FXML
    RadioButton simple;
    @FXML
    RadioButton complex;

    ToggleGroup choiceSimpleOrComplex = new ToggleGroup();
    Class Class;
    StringConverter str = null;

    private Matrix first;
    private Matrix second;
    private Matrix result;

    private void alert(String s, Alert.AlertType type) {
        Alert alert = new Alert(type);
        alert.setTitle("");
        alert.setContentText(s);
        alert.showAndWait();
    }

    @FXML
    private void doSimple() {
        Class = SimpleNumber.class;
    }

    @FXML
    private void doComplex() {
        Class = ComplexNumber.class;
    }

    @FXML
    private void doEnterFirst() {
        if (Class == null) {
            alert("Choice simple or complex number!", Alert.AlertType.ERROR);
            return;
        }
        if (firstNumberM.getText().equals("") && firstNumberN.getText().equals("")) {
            alert("Enter size of matrix. M*N", Alert.AlertType.ERROR);
            return;
        }
        int m = Integer.parseInt(firstNumberM.getText());
        int n = Integer.parseInt(firstNumberN.getText());
        first = new Matrix(m, n);
        enterData(first, m, n);
        updateTable(first, firstTable);
    }

    @FXML
    private void doEnterSecond() {
        if (Class == null) {
            alert("Choice simple or complex number!", Alert.AlertType.ERROR);
            return;
        }
        if (secondNumberM.getText().equals("") && secondNumberN.getText().equals("")) {
            alert("Enter size of matrix. M*N", Alert.AlertType.ERROR);
            return;
        }
        int m = Integer.parseInt(secondNumberM.getText());
        int n = Integer.parseInt(secondNumberN.getText());
        second = new Matrix(m, n);
        enterData(second, m, n);
        updateTable(second, secondTable);
    }

    private void enterData(Matrix matrix, int m, int n) {
        if (Class == ComplexNumber.class) {
            matrix.setMatrix(new Numbers.Number[m][n]);
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    matrix.getMatrix()[i][j] = new ComplexNumber();
                }

            }
        } else {
            matrix.setMatrix(new Numbers.Number[m][n]);
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    matrix.getMatrix()[i][j] = new SimpleNumber();
                }

            }
        }
    }

    private void updateTable(Matrix matrix, TableView table) {
        DefaultTableAdapter TA = new DefaultTableAdapter(table, matrix.getMatrix(), str);
    }

    @FXML
    void doInverseFirst(ActionEvent event) {
        doInverse(first);
    }

    @FXML
    void doInverseSecond(ActionEvent event) {
        doInverse(second);
    }

    private void doInverse(Matrix matrix){
        if (matrix != null) {
            try {
                result = inversion(matrix);
                updateTable(result, resultTable);
            } catch (IncorrectSizeException e) {
                alert("Incorrect size for this operation", Alert.AlertType.ERROR);
            }
        } else {
            alert("Enter Matrix!", Alert.AlertType.ERROR);
        }
    }

    @FXML
    void doMultiply_FirstNumber(ActionEvent event) {
        doMultiplyByNumber(first,FirstMultiplyNumber.getText());
    }

    @FXML
    void doMultiply_FirstSecond(ActionEvent event) {
        if(first != null && second != null){
            try{
                result = multiplication(first, second);
                updateTable(result, resultTable);
            }catch(IncorrectSizeException e){
                alert("Incorrect size of matrixes", Alert.AlertType.ERROR);
            }
        }else{
            alert("Enter the matrixes!", Alert.AlertType.ERROR);
        }
    }

    @FXML
    void doMultiply_SecondNumber(ActionEvent event) {
        doMultiplyByNumber(second, SecondMultiplyNumber.getText());
    }

    private void doMultiplyByNumber(Matrix matrix,String number){
        if(matrix != null){
            if(!number.equals("")){
                Double n = null;
                try {
                    n = Double.parseDouble(number);
                } catch(Exception e){
                    alert("Incorrect number for multiply!", Alert.AlertType.ERROR);
                    return;
                }
                result = multiplication(matrix,n);
                updateTable(result,resultTable);
            }else{
                alert("Enter the number for multiply!", Alert.AlertType.ERROR);
            }
        }else{
            alert("Enter the matrix!", Alert.AlertType.ERROR);
        }
    }

    @FXML
    void doPowFirst(ActionEvent event) {
        doPow(first, FirstPowNumber.getText());
    }

    @FXML
    void doPowSecond(ActionEvent event) {
        doPow(second,SecondPowNumber.getText());
    }

    private void doPow(Matrix matrix, String number){
        if(matrix != null){
            if(!number.equals("")){
                Integer n = null;
                try {
                    n = Integer.parseInt(number);
                } catch(Exception e){
                    alert("Incorrect exponent!", Alert.AlertType.ERROR);
                    return;
                }
                if(n < 1){
                    alert("Incorrect exponent!", Alert.AlertType.ERROR);
                    return;
                }
                try {
                    result = pow(matrix, n);
                    updateTable(result, resultTable);
                } catch(IncorrectSizeException e){
                    alert("Incorrect size of the matrix!", Alert.AlertType.ERROR);
                }
            }else{
                alert("Enter the exponent!", Alert.AlertType.ERROR);
            }
        }else{
            alert("Enter the matrix!", Alert.AlertType.ERROR);
        }
    }

    @FXML
    void doSum(ActionEvent event) {
        if(first != null && second != null){
            try{
                result = sum(first, second);
                updateTable(result, resultTable);
            } catch(IncorrectSizeException e) {
                alert("Incorrect size of matrixes for this operation!", Alert.AlertType.ERROR);
            }
        }
    }

    @FXML
    void doTransposeFirst(ActionEvent event) {
        doTranspose(first);
    }

    @FXML
    void doTransposeSecond(ActionEvent event) {
        doTranspose(second);
    }

    private void doTranspose(Matrix matrix){
        if (matrix != null) {
                result = transpose(matrix);
                updateTable(result, resultTable);
        } else {
            alert("Enter Matrix!", Alert.AlertType.ERROR);
        }
    }

    @FXML
    void doDiff(ActionEvent event) {
        if(first != null && second != null){
            try{
                result = difference(first, second);
                updateTable(result, resultTable);
            } catch(IncorrectSizeException e) {
                alert("Incorrect size of matrixes for this operation!", Alert.AlertType.ERROR);
            }
        }
    }

    @FXML
    void doChange(ActionEvent event) {
        if (first != null && second != null) {
            Matrix temp = first;
            first = second;
            second = temp;
            updateTable(first, firstTable);
            updateTable(second, secondTable);
            String m = firstNumberM.getText();
            String n = firstNumberN.getText();
            firstNumberM.setText(secondNumberM.getText());
            firstNumberN.setText(secondNumberN.getText());
            secondNumberN.setText(n);
            secondNumberM.setText(m);
        } else
            alert("Fill the matrix A and B!", Alert.AlertType.ERROR);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        choiceSimpleOrComplex = new ToggleGroup();
        simple.setToggleGroup(choiceSimpleOrComplex);
        complex.setToggleGroup(choiceSimpleOrComplex);
    }
}
