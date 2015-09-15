
import KS_API_FormResultManagementService.FormResultManagementLocator;
import KS_API_FormResultManagementService.FormResultManagementService;
import KS_API_FormResultManagementService.WSRespondent;
import Pages.KS.KSLoginPage;
import Pages.KS.KSMainPage;
import Pages.KS.KSManageResponsePage;
import Pages.Portal.AlertTabPage;
import com.google.common.hash.HashCode;
import framework.CommonOperations;
import framework.DriverOperations;
import framework.utils.CSVOperations;
import framework.utils.DataBase;
import framework.utils.GlobalVariables;
import framework.webDriver.Driver;
import org.apache.axis.client.Stub;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import javax.xml.rpc.ServiceException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/**
* Created by sergii.stepanov on 20.01.2015.
*/
public class test extends CommonOperations{

    public static void main(String []args) throws Exception {
        Map<Integer, HashCode> map = new HashMap<Integer, HashCode>();
        for (Integer i = 15; i > 0; i--) {
            map.put(i, HashCode.fromInt(i.hashCode()));
        }
        Iterator<Integer> iterator=map.keySet().iterator();
        while (iterator.hasNext()){
            if(iterator.next()<5){
                iterator.remove();
            }
        }

        Set<Map.Entry<Integer,HashCode>> entrySet= map.entrySet();
        for (Map.Entry<Integer,HashCode> mapEl:entrySet){
            mapEl.getKey();
            }
        
        System.out.println("Map: "+map);

        Collection<Integer> collection2 = new ArrayList<Integer>();
        for (Integer i = 10; i > 0; i--) {
            collection2.add(i);
        }
        map.remove(7);
        Iterator<Integer> iterator2=collection2.iterator();
        while (iterator.hasNext()){
            if (iterator.next()<5){
                iterator.remove();
            }
        }
    }

}

