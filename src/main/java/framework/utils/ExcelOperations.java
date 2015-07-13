package framework.utils;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by sergii.stepanov on 01/07/2015.
 */
public class ExcelOperations {

    public static String getLinkFromExcelFile() {
        String cell=null;
        try {
            File xlsFile=new File(GlobalVariables.DownloadExcelFilePath);
            POIFSFileSystem fs = new POIFSFileSystem(new FileInputStream(xlsFile));
            HSSFWorkbook wb = new HSSFWorkbook(fs);
            HSSFSheet sheet = wb.getSheetAt(0);
            HSSFRow row;

            int rows = sheet.getPhysicalNumberOfRows();
            for(int r = 0; r < rows; r++) {
                row = sheet.getRow(r);
//                if(row != null) {
                    if (row.getCell(8)==null){
                        cell = row.getCell(7).toString();
                        GlobalVariables.setSurveyID(row.getCell(0).toString());
                        row.createCell(8).setCellValue("used");
                        FileOutputStream fileOut = new FileOutputStream(xlsFile);
                        wb.write(fileOut);
                        fileOut.flush();
                        fileOut.close();
                        break;
                    }
//                }
            }
        } catch(IOException ioe) {
            ioe.printStackTrace();
            throw new RuntimeException("Error with Excel file!");
        }
        if (cell==null){
            throw new RuntimeException("There is no free Survey link!");
        }else return cell;
    }
}
