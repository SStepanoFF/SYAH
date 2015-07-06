package framework.utils;

import java.io.*;

/**
 * Created by sergii.stepanov on 01/07/2015.
 */
public class CSVOperations {

    public static String getCellFromAutofillCSVFile(int cellNumber) {
        String resultCell = "";
            File csvFile = new File(GlobalVariables.AutofillExcelFilePath);
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
        return resultCell;

    }
}
