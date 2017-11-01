package ReadWrite;


import java.io.*;
import java.util.Date;
import java.util.Scanner;

public class WriteHTML {
    public static void WriteHTML(StructureForSave obj){
        Date date = new Date();
        String first = "";
        String Date = date.toString();
        String[] splitDate = Date.split(" ");
        Date = "";
        for(int i = 0; i<splitDate.length; i++){
            if(i == splitDate.length -1){
                Date += splitDate[i];
            }else {
                Date += splitDate[i] + "_";
            }
        }
        String title = "./HtmlFiles/Report_"+Date+".html";
        Scanner in = null;
        try {
            in = new Scanner(new File("./HtmlFiles/Components/1Part.txt"));
        }catch(IOException e){
            e.printStackTrace();
        }
        while(in.hasNext()){
            first += in.nextLine();
            if(!in.hasNext())
                continue;
            first+= "\r\n";
        }
        in.close();


        String second = "";
        try {
            in = new Scanner(new File("./HtmlFiles/Components/2Part.txt"));
        }catch(IOException e){
            e.printStackTrace();
        }
        while(in.hasNext()) {
            second += in.nextLine();
            if(!in.hasNext())
                continue;
            second+= "\r\n";
        }
        in.close();


        String third = "";
        try {
            in = new Scanner(new File("./HtmlFiles/Components/3Part.txt"));
        }catch(IOException e){
            e.printStackTrace();
        }
        while(in.hasNext())
            third += in.nextLine() + "\r\n";
        in.close();


        String fourth = "";
        try {
            in = new Scanner(new File("./HtmlFiles/Components/4Part.txt"));
        }catch(IOException e){
            e.printStackTrace();
        }
        while(in.hasNext())
            fourth += in.nextLine() + "\r\n";
        in.close();

        String html = first + date.toString()+second;
        System.out.println("Enter Your name: ");
        String name= "";
        Scanner fromConsole = new Scanner(System.in);
        name = fromConsole.nextLine();
        html+=name+third;

        String firstMatrix = "\t<pre>\n\t<h3>\nFirst Matrix\n"+ obj.getFirst().toString()+"\t</h3>\n\t</pre>\n";
        String secondMatrix = "\t<pre>\n\t<h3>\nSecond Matrix\n"+ obj.getSecond().toString()+"\t</h3>\n\t</pre>\n";
        String thirdMatrix = ChoiceAction(obj.getAction()) +"\t<pre>\n\t<h3>\nResult Matrix\n"+ obj.getResult().toString()+
                "\t</h3>\n\t</pre>\n";
        html+=firstMatrix+secondMatrix+thirdMatrix;
        html+="\t"+fourth;
        try(FileWriter writer = new FileWriter(title, false))
        {
            writer.write(html);
            writer.flush();
        }
        catch(IOException ex){

            System.out.println(ex.getMessage());
        }
    }

    private static String ChoiceAction(Double[] i){
        String choice = Double.toString(i[0]);
        String action = "";
        switch(choice){
            case "0.0":
                action = "First"+"<sup><small>"+ i[1] +"</small></sup> = ";
                break;
            case "1.0":
                action = "Second"+"<sup><small>"+ i[1] +"</small></sup> = ";
                break;
            case "2.0":
                action = "First  + Second = ";
                break;
            case "3.0":
                action = "First  - Second = ";
                break;
            case "4.0":
                action = "First<sup><small>T</small></sup> = ";
                break;
            case "5.0":
                action = "Second<sup><small>T</small></sup> = ";
                break;
            case "6.0":
                action = "First<sup><small>-1</small></sup> = ";
                break;
            case "7.0":
                action = "Second<sup><small>-1</small></sup> = ";
                break;
            case "8.0":
                action = "First * Second  = ";
                break;
            case "9.0":
                action = "First * "+ i[1] +" = ";
                break;
            case "10.0":
                action = "Second * "+ i[1] + " = ";
                break;
            default:
                action = "Unknown operation";
                break;
        }
        return "\t<h3>"+action+"</h3>\n";
    }
}
