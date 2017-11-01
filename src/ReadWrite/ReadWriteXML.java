package ReadWrite;

import CourseWork.Matrix;
import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

public class ReadWriteXML {
    public static void WriteXML(StructureForSave first) {
        Date date = new Date();
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
        String title = "./XmlFiles/SavedProcess_"+Date+".xml";
        try (XMLEncoder xmlEncoder = new XMLEncoder(new FileOutputStream(title))) {
            xmlEncoder.writeObject(first);
            xmlEncoder.flush();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static StructureForSave ReadXML(String title) {
        title = "./XmlFiles/" + title;
        StructureForSave first = null;
        try (XMLDecoder xmlDecoder = new XMLDecoder(new FileInputStream(title))) {
            first = (StructureForSave) xmlDecoder.readObject();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return first;
    }
}
