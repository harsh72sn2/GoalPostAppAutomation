package Utils;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import com.opencsv.CSVWriter;

public class WriteToCSV {

    private static Logger logger= LogManager.getLogger(CSVWriter.class);

    //give header array, filename,status,datalist to write
    public static void writeCSV( String file,boolean append, String [] header, List<String[]> dataList) {
        try {
            File fileName = new File("src/test/resources/" + file);
            if (fileName.exists()) {
                logger.info("File exists... Deleting it !!!");
                fileName.delete();
            }
            fileName.createNewFile();
            CSVWriter csvWriter = new CSVWriter(new FileWriter(fileName, append));
            logger.info("Writing in the header");
            csvWriter.writeNext(header);
            for (String[] data : dataList) {
                csvWriter.writeNext(data);
            }
            csvWriter.flush();
            csvWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void writeCSVdeeplink( String file,boolean append, String [] header, List<String[]> dataList) {
        try {
            File fileName = new File("src/main/resources/" + file);
            if (fileName.exists()) {
                logger.info("File exists... Deleting it !!!");
                fileName.delete();
            }
            fileName.createNewFile();
            CSVWriter csvWriter = new CSVWriter(new FileWriter(fileName, append));
            logger.info("Writing in the header");
            csvWriter.writeNext(header);
            for (String[] data : dataList) {
                csvWriter.writeNext(data);
            }
            csvWriter.flush();
            csvWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
