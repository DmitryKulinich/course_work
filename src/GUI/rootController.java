package GUI;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;


import java.net.URL;
import java.util.ResourceBundle;

public class rootController implements Initializable {

    @FXML TableView firstMatrix;
    @FXML TableView secondMatrix;
    @FXML TableView resultMatrix;
    @FXML ComboBox<String> firstCombobox;
    @FXML ComboBox<String> secondCombobox;
    @FXML ComboBox<String> middleCombobox;
    @FXML TextField firstNumberM;
    @FXML TextField firstNumberN;
    @FXML TextField secondNumberM;
    @FXML TextField secondNumberN;
    @FXML RadioButton simple;
    @FXML RadioButton complex;



    @FXML private void doSimple(){}
    @FXML private void doComplex(){}

    @FXML private void doEnterFirst(){}
    @FXML private void doEnterSecond(){}

    private void doSolve(){

    }

    @FXML private void doChoice1(ActionEvent event){
        if(!firstCombobox.getValue().equals("None")){
            secondCombobox.setValue("None");
            middleCombobox.setValue("None");
        }
        doSolve();
    }
    @FXML private void doChoice2(ActionEvent event){
        if(!secondCombobox.getValue().equals("None")){
            firstCombobox.setValue("None");
            middleCombobox.setValue("None");
        }
        doSolve();
    }
    @FXML private void doChoiceMiddle(ActionEvent event){
        if(!middleCombobox.getValue().equals("None")){
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

        ToggleGroup choiceSimpleOrComplex = new ToggleGroup();
        simple.setToggleGroup(choiceSimpleOrComplex);
        complex.setToggleGroup(choiceSimpleOrComplex);
    }
}
