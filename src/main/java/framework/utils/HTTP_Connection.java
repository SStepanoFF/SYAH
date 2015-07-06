package framework.utils;

import org.apache.http.Consts;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by sergii.stepanov on 27/05/2015.
 */
public abstract class HTTP_Connection {

    private static CloseableHttpClient httpClient = HttpClients.createDefault();

//    public HTTP_Connection(){
//        this.httpClient = HttpClients.createDefault();
//    }

    public static StringBuilder getHttpGET_Response(String urlGET) {
        loginAdminPOST();
        CloseableHttpResponse response = httpGETRequest(urlGET);
        StringBuilder result = null;
        try {
            BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
            String line = "";
            while ((line = rd.readLine()) != null) {
                System.out.println(new StringBuilder().append(line));
                result = new StringBuilder().append(line);
            }
        } catch (IOException e) {
            throw new RuntimeException("ERROR in the GET response!");
        }
        return result;
    }

    private static void loginAdminPOST() {
        httpGETRequest("http://dcig.t1.ssstest.com");
        HttpPost loginPost = new HttpPost("http://dcig.t1.ssstest.com/Login.action");
        try {
            List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
            nameValuePairs.add(new BasicNameValuePair("loginEvent", "Submit"));
            nameValuePairs.add(new BasicNameValuePair("login", Loader.loadProperty("wa.adminLogin")));
            nameValuePairs.add(new BasicNameValuePair("password", Loader.loadProperty("wa.adminPass")));

            loginPost.setEntity(new UrlEncodedFormEntity(nameValuePairs, Consts.UTF_8));
            CloseableHttpResponse response = httpClient.execute(loginPost);
            if (!response.containsHeader("Set-Cookie")) {
                throw new RuntimeException("ERROR! Incorrect loginPOST!");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static CloseableHttpResponse httpGETRequest(String url) {
        HttpGet request = new HttpGet(url);
        CloseableHttpResponse response = null;
        try {
            return response = httpClient.execute(request);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("ERROR! Can't execute httpGET!");
        }
    }

}
