package GUI;

import Adapter.DefaultTableAdapter;
import CourseWork.Matrix;
import Numbers.ComplexNumber;
import Numbers.Number;
import Numbers.SimpleNumber;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.util.StringConverter;
import javafx.util.converter.DoubleStringConverter;


import java.net.URL;
import java.util.ResourceBundle;

public class rootController implements Initializable {

    @FXML
    TableView firstTable;
    @FXML
    TableView secondTable;
    @FXML
    TableView resultTable;
    @FXML
    ComboBox<String> firstCombobox;
    @FXML
    ComboBox<String> secondCombobox;
    @FXML
    ComboBox<String> middleCombobox;
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

    private Matrix first = new Matrix();
    private Matrix second = new Matrix();

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
        enterData(second,m,n);
        updateTable(second,secondTable);
    }

    private void enterData(Matrix matrix, int m, int n){
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

    private void updateTable(Matrix matrix, TableView table){
        DefaultTableAdapter TA = new DefaultTableAdapter(table, matrix.getMatrix(),str);

    }

    private void doSolve() {

    }

    @FXML
    private void doChoice1(ActionEvent event) {
        if (!firstCombobox.getValue().equals("None")) {
            secondCombobox.setValue("None");
            middleCombobox.setValue("None");
        }
        doSolve();
    }

    @FXML
    private void doChoice2(ActionEvent event) {
        if (!secondCombobox.getValue().equals("None")) {
            firstCombobox.setValue("None");
            middleCombobox.setValue("None");
        }
        doSolve();
    }

    @FXML
    private void doChoiceMiddle(ActionEvent event) {
        if (!middleCombobox.getValue().equals("None")) {
            firstCombobox.setValue("None");
            secondCombobox.setValue("None");
        }
        doSolve();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<String> list = FXCollections.observableArrayList();
        list.add("None");
        list.add("Multiply with number");
        list.add("Transpose");
        list.add("Inversion");
        list.add("Pow");
        ObservableList<String> middlelist = FXCollections.observableArrayList();
        middlelist.add("None");
        middlelist.add("Multiply");
        middlelist.add("Sum");
        middlelist.add("Differense");
        firstCombobox.setItems(list);
        secondCombobox.setItems(list);
        middleCombobox.setItems(middlelist);

        choiceSimpleOrComplex = new ToggleGroup();
        simple.setToggleGroup(choiceSimpleOrComplex);
        complex.setToggleGroup(choiceSimpleOrComplex);
    }
}
