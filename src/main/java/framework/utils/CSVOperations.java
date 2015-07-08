package framework.utils;

import au.com.bytecode.opencsv.CSVReader;
import au.com.bytecode.opencsv.CSVWriter;
import framework.CommonOperations;
import javafx.scene.control.Cell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.*;
import java.util.List;
import java.util.Random;

/**
 * Created by sergii.stepanov on 01/07/2015.
 */
public class CSVOperations {

    public static String getCellFromAutofillCSVFile(int cellNumber) {
        String resultCell = null;
            File csvFile = new File(GlobalVariables.AutofillCSVFilePath);
            BufferedReader bufferedReader = null;
            String line = "";
            String cvsSplitBy = ",";
            try {
                bufferedReader = new BufferedReader(new FileReader(csvFile));
                while ((line = bufferedReader.readLine()) != null) {
                    String[] cell = line.split(cvsSplitBy);
                    if (cell[1].equals(GlobalVariables.getSurveyID())) {
                        resultCell = cell[cellNumber];
                        break;
                    }
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (bufferedReader != null) {
                    try {
                        bufferedReader.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        if (resultCell==null){
            throw new RuntimeException("ERROR! No cell in CSV file!");
        }else  return resultCell.toLowerCase();

    }

    public static String getPersonTypeFromCSVFile(){
        if(getCellFromAutofillCSVFile(6).toLowerCase().equals("candidate")){
            return "candidate";
        }else return "client";
    }


    public static void writeCelltoAutofillCSVFile(int cellNumber, String text) throws IOException {
        String csvFilePath= GlobalVariables.AutofillCSVFilePath;
        CSVReader reader = new CSVReader(new FileReader(csvFilePath), ',');
        List<String[]> csvBody = reader.readAll();
        for (int i = 0; i<csvBody.size(); i++) {
            if (csvBody.get(i)[1].equals(GlobalVariables.getSurveyID())) {
                csvBody.get(i)[cellNumber]=text;
                CSVWriter writer = new CSVWriter(new FileWriter(csvFilePath), ',');
                writer.writeAll(csvBody);
                writer.flush();
                writer.close();
            }
        }
        reader.close();
    }

    public static void createCSVAutofillFile(){
        String csvFilePath= GlobalVariables.AutofillCSVFilePath;
        CSVWriter writer=null;
        CSVReader reader = null;
        try {
            reader = new CSVReader(new FileReader(csvFilePath), ',');
            List<String[]> csvBody = reader.readAll();
            for (int i = 1; i<csvBody.size(); i++) {
                    csvBody.get(i)[1] = CommonOperations.generateRandomNumberAndString(32);
                    csvBody.get(i)[2]=Integer.toString(new Random().nextInt(9999999));
                    writer = new CSVWriter(new FileWriter(csvFilePath), ',');
                    writer.writeAll(csvBody);
            }
        }catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                writer.flush();
                writer.close();
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
