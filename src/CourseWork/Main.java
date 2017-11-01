package CourseWork;

import Exceptions.IncorrectSizeException;
import Numbers.Number;
import ReadWrite.ReadWriteXML;
import Numbers.ComplexNumber;
import Numbers.SimpleNumber;
import ReadWrite.StructureForSave;
import ReadWrite.WriteHTML;

import java.io.File;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
	    Matrix first = new Matrix();
	    Matrix second = new Matrix();
	    Matrix result = new Matrix();
        Menu(first, second, result);
    }

    private static void enterData(Matrix obj ){
        System.out.println("Enter matrix size (row,column): ");
        Scanner in = new Scanner(System.in);
        obj.setRow(in.nextInt());
        obj.setColumn(in.nextInt());
        System.out.println("Choose element type:\n1. Simple Numbers;\n2. Complex Numbers");
        Integer choice = in.nextInt();
        switch (choice){
            case 1:
                obj.setMatrix(forSimple(obj.getRow(), obj.getColumn()));
                break;
            case 2:
                obj.setMatrix(forComplex(obj.getRow(), obj.getColumn()));

        }


    }

    private static SimpleNumber[][] forSimple(int row, int column){
        Scanner in = new Scanner(System.in);
        SimpleNumber[][] result = new SimpleNumber[row][column];
        for(int i = 0; i < row; i++){
            for(int j = 0; j < column; j++){
                result[i][j] = new SimpleNumber();
                result[i][j].setX(in.nextDouble());
            }
        }
        return result;
    }

    private static ComplexNumber[][] forComplex(int row, int column){
        Scanner in = new Scanner(System.in);
        ComplexNumber[][] result = new ComplexNumber[row][column];
        for(int i = 0; i < row; i++){
            for(int j = 0; j < column; j++){
                result[i][j] = new ComplexNumber();
                result[i][j].setNumber(in.nextLine());
            }
        }
        return result;
    }

    private static void Menu(Matrix first, Matrix second, Matrix result) {

        Boolean isEmptyF = true, isEmptyS = true, isEmptyR = true;
        Scanner in = new Scanner(System.in);
        Integer choice = 0;
        Double[] action = new Double[2];//powF -0, powS-1, plus-2, minus-3, TransposeF-4, transposeS-5, InverseF-6,InverseS-7,
        action[0] = 128d;                  //multiply M*M -8, multiplyF M*N -9, multiplyS M*N -10
        action[1] =256d;


        while(!choice.equals(13)) {
            String f = first.toString(), s = second.toString(), r = result.toString();
            System.out.println("\n\n\n\nMenu:\n\t\tFirst:\n\n" + f + "\t\tSecond:\n\n" + s + "\t\tResult:\n\n" + r +
                    "Action:\n1.Set Matrix\n2.Power \n3.First + Second\n4.First - Second\n5.Transpose\n6.Inverse \n" +
                    "7.Multiply\n8.Result to first\n9.Result to second\n10.Save to XML\n11.Get from XML\n" +
                    "12.Save to HTML\n13.Exit\nPlease select any action");
            choice = in.nextInt();
            switch (choice) {
                case 1: {  //set matrix

                    System.out.println("Please select matrix:\n1.first\n2.second");
                    Integer subChoice = in.nextInt();
                    switch (subChoice) {
                        case 1:
                            enterData(first);
                            isEmptyF = false;
                            break;
                        case 2:
                            enterData(second);
                            isEmptyS = false;
                            break;
                    }
                    break;
                }
                case 2: {   //pow
                    System.out.println("Please select matrix:\n1.first\n2.second");
                    Integer subChoice = in.nextInt();
                    System.out.print("Enter the power:");
                    int pow = in.nextInt();
                    switch (subChoice) {
                        case 1:
                            if (isEmptyF) {
                                System.out.println("First matrix is empty!");
                                break;
                            }
                            try {
                                action[0] = 0d;
                                action[1] = (double)pow;
                                result = MatrixOperationLib.pow(first, pow);
                                isEmptyR = false;
                            } catch (IncorrectSizeException e) {
                                e.getMassage();
                                e.printStackTrace();
                            }
                            break;
                        case 2:
                            if (isEmptyS) {
                                System.out.println("Second matrix is empty!");
                                break;
                            }
                            try {
                                action[0] = 1d;
                                action[1] = (double)pow;
                                result = MatrixOperationLib.pow(second, pow);
                                isEmptyR = false;
                            } catch (IncorrectSizeException e) {
                                e.getMassage();
                                e.printStackTrace();
                            }
                            break;
                    }
                    break;
                }
                case 3: { //plus
                    if (isEmptyF) {
                        System.out.println("First matrix is empty!");
                        break;
                    }
                    if (isEmptyS) {
                        System.out.println("Second matrix is empty!");
                        break;
                    }
                    try {
                        action[0] = 2d;
                        action[1] = 0d;
                        result = MatrixOperationLib.sum(first, second);
                        isEmptyR = false;
                    } catch (IncorrectSizeException e) {
                        e.getMassage();
                        e.printStackTrace();
                    }
                    break;
                }
                case 4: { //minus
                    if (isEmptyF) {
                        System.out.println("First matrix is empty!");
                        break;
                    }
                    if (isEmptyS) {
                        System.out.println("Second matrix is empty!");
                        break;
                    }
                    try {
                        action[0] = 3d;
                        action[1] = 0d;
                        result = MatrixOperationLib.difference(first, second);
                        isEmptyR = false;
                    } catch (IncorrectSizeException e) {
                        e.getMassage();
                        e.printStackTrace();
                    }
                    break;
                }
                case 5: { // transpose
                    System.out.println("Please select matrix:\n1.first\n2.second");
                    Integer subChoice = in.nextInt();
                    switch (subChoice) {
                        case 1:
                            if (isEmptyF) {
                                System.out.println("First matrix is empty!");
                                break;
                            }
                            action[0] = 4d;
                            action[1] = 0d;
                            result = MatrixOperationLib.transpose(first);
                            isEmptyR = false;
                            break;
                        case 2:
                            if (isEmptyS) {
                                System.out.println("Second matrix is empty!");
                                break;
                            }
                            action[0] = 5d;
                            action[1] = 0d;
                            result = MatrixOperationLib.transpose(second);
                            isEmptyR = false;
                            break;
                    }
                    break;
                }
                case 6: { // inverse
                    System.out.println("Please select matrix:\n1.first\n2.second");
                    Integer subChoice = in.nextInt();
                    switch (subChoice) {
                        case 1:
                            if (isEmptyF) {
                                System.out.println("First matrix is empty!");
                                break;
                            }
                            try {
                                action[0] = 6d;
                                result = MatrixOperationLib.inversion(first);
                            }catch(IncorrectSizeException e){
                                e.getMassage();
                                e.printStackTrace();
                            }
                            isEmptyR = false;
                            break;
                        case 2:
                            if (isEmptyS) {
                                System.out.println("Second matrix is empty!");
                                break;
                            }
                            try {
                                action[0] = 7d;
                                result = MatrixOperationLib.inversion(second);
                            }catch(IncorrectSizeException e){
                                e.getMassage();
                                e.printStackTrace();
                            }
                            isEmptyR = false;
                            break;
                    }
                    break;
                }
                case 7: { // multiplication
                    System.out.println("Select type of multiplication:\n 1. Matrix*Matrix\n2. Matrix*Number");
                    Integer subChoice = in.nextInt();
                    switch (subChoice) {
                        case 1:
                            if (isEmptyF) {
                                System.out.println("First matrix is empty!");
                                break;
                            }
                            if (isEmptyS) {
                                System.out.println("Second matrix is empty!");
                                break;
                            }
                            try {
                                action[0] = 8d;
                                result = MatrixOperationLib.multiplication(first, second);
                                isEmptyR = false;
                            } catch (IncorrectSizeException e) {
                                e.getMassage();
                                e.printStackTrace();
                            }
                            break;
                        case 2: {
                            System.out.println("Please select matrix:\n1.first\n2.second");
                            Integer subChoice2 = in.nextInt();
                            System.out.println("Enter number:");
                            Double num = in.nextDouble();
                            switch (subChoice2) {
                                case 1:
                                    if (isEmptyF) {
                                        System.out.println("First matrix is empty!");
                                        break;
                                    }
                                    action[0] = 9d;
                                    action[1] = num;
                                    result = MatrixOperationLib.multiplication(first, num);
                                    isEmptyR = false;
                                    break;
                                case 2:
                                    if (isEmptyS) {
                                        System.out.println("Second matrix is empty!");
                                        break;
                                    }
                                    action[0] = 10d;
                                    action[1] = num;
                                    result = MatrixOperationLib.multiplication(second, num);
                                    isEmptyR = false;
                                    break;
                                default:
                                    System.out.println("Unknown option");
                                    break;
                            }
                            break;
                        }
                        default:
                            System.out.println("Unknown option");
                            break;
                    }
                    break;
                }
                case 8: //result to first
                    if (isEmptyR) {
                        System.out.println("Result matrix is empty!");
                        break;
                    }
                    first.setRow(result.getRow());
                    first.setColumn(result.getColumn());
                    first.setMatrix(result.getMatrix());
                    break;
                case 9: //result to second
                    if (isEmptyR) {
                        System.out.println("Result matrix is empty!");
                        break;
                    }
                    second.setRow(result.getRow());
                    second.setColumn(result.getColumn());
                    second.setMatrix(result.getMatrix());
                    isEmptyS = false;
                    break;
                case 10: // save to xml
                    StructureForSave proc = new StructureForSave(first, second, result, action);
                    ReadWriteXML.WriteXML(proc);
                    break;
                case 11:{ //get from xml
                    System.out.println("Select the XML file what are you want to get and enter its name:");
                    if(!ListOfXmlFiles()){
                        break;
                    }
                    in.nextLine();
                    String name = in.nextLine();
                    StructureForSave temp = ReadWriteXML.ReadXML(name);
                    first = temp.getFirst();
                    second = temp.getSecond();
                    result = temp.getResult();
                    break;}
                case 12: //WriteHTML
                    StructureForSave temp = new StructureForSave(first,second,result,action);
                    WriteHTML.WriteHTML(temp);
                    break;
                case 13: // exit
                    return;
                default:
                    System.out.println("Unknown option");
                    break;
            }
        }
    }

    public static Boolean ListOfXmlFiles() {
        try {
            String path = "./XmlFiles";
            File dir = new File(path);
            File[] list = dir.listFiles();
            if(list.length == 0) {
                System.out.println("No saved files");
                return false;
            }
            for (File file : list) {
                System.out.println(file.getName());
            }
        }
        catch (NullPointerException e){
            System.out.println("Such directory does not exist!");
        }
        return true;
    }
}
