package framework;


import framework.utils.DataBase;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by sergii.stepanov on 08/05/2015.
 */
public class CommonOperations {

    private DataBase dataBase;

    public String parsTextByRegEx(String text, String regExpr) {
        String result = "";
        Pattern p = Pattern.compile(regExpr);
        Matcher m = p.matcher(text);
        while (m.find()) {
            result += m.group();
        }
        return result;
    }

    public void waitForQVupdate() {
        dataBase = new DataBase();
        int logId = dataBase.getLogIdForQVadmin();
        int count = 0;
        while (count < 66) {
            if (logId == dataBase.getLogIdForQVadmin()) {
                try {
                    Thread.sleep(30000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                count++;
            } else {
                try {
                    Thread.sleep(60000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                break;
            }
        }
        if (count >= 66) throw new RuntimeException("ERROR!!! QV was not updated!");
    }
}
