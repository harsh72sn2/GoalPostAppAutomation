package Utils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

public class PropertiesFile {

    Properties properties;

    {
        properties = new Properties();
    }

    public PropertiesFile (String filename){
        try{
            InputStream input = new FileInputStream(System.getProperty("user.dir")+"/src/main/resources/"+filename);
             properties.load(input);
        } catch(Exception e) {
            e.printStackTrace();
        }
//        return properties;
    }

    public  String getValue(String property) {
        return properties.getProperty(property);
    }
    public void setValue(String filename,String key,String value) {
        try{
            FileOutputStream outputStream = new FileOutputStream(System.getProperty("user.dir")+"/src/main/resources/"+filename);
            properties.setProperty(key,value);
            properties.store(outputStream,null);
            outputStream.close();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
