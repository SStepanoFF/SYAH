package framework.utils;


import java.sql.*;

public class DataBase {
    private static Connection conn;
    private static Statement statement;
    private static ResultSet resultSets;

    public DataBase() {
//        try {
//            Class.forName("com.mysql.jdbc.Driver");//Регистрируем драйвер
//            conn = DriverManager.getConnection("jdbc:mysql://mysql-kstest2.t1.tenet:3306/hays_ssstest_com_PRR_2032",
//                    "member", "1234");//Установка соединения с БД
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }
//        catch(Exception e){
//            e.printStackTrace();
//            Loader.logWritter("ERROR! Can't connect to DB");
//        }
    }

    public static String executeSQLQuery(String query, String columnName){
        try {
            Class.forName("com.mysql.jdbc.Driver");//Регистрируем драйвер
            conn = DriverManager.getConnection("jdbc:mysql://mysql-kstest2.t1.tenet:3306/hays_ssstest_com_PRR_2032",
                    "member", "1234");//Установка соединения с БД
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        catch(Exception e){
            e.printStackTrace();
            Loader.logWritter("ERROR! Can't connect to DB");
        }


        String result = "Can't find userID!";
        try {
            statement = conn.createStatement();//Готовим запрос
            resultSets = statement.executeQuery(query);
            while (resultSets.next()) {
                result = resultSets.getString(columnName);
            }
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return result;
        } finally {
            try {
                resultSets.close();
                statement.close();
//                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public int getUserNmb(String userLog){
        int result=-1;
        int userNmb = 0;
        if (userLog.contains("User")) {
            if (userLog.contains("Gr")) {
                userNmb = Integer.parseInt(Loader.loadProperty("userNumb2"));
            } else userNmb = Integer.parseInt(Loader.loadProperty("userNumb1"));
        } else userNmb = Integer.parseInt(Loader.loadProperty("analystNumb"));
        try{
            statement = conn.createStatement();//Готовим запрос
            while (result!=0){
            resultSets = statement.executeQuery("SELECT COUNT(*) as C FROM users WHERE Login='"+userLog+userNmb+"'");
            while(resultSets.next()){
                result=resultSets.getInt("C");
            }
                if(result!=0) userNmb++;
            }
            return userNmb;
        } catch(Exception e){
            e.printStackTrace();
            return result;
        }
        finally{
            try {
                resultSets.close();
                statement.close();
                conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public String getUserRegName() {
        int result = -1;
        int userNmb = 0;
        userNmb = Integer.parseInt(Loader.loadProperty("userRegNumb"));
        try {
            statement = conn.createStatement();//Готовим запрос
            while (result != 0) {
                resultSets = statement.executeQuery("SELECT COUNT(*) as C FROM users WHERE Login='autoTestUserReg" + userNmb + "@test.com'");
                while (resultSets.next()) {
                    result = resultSets.getInt("C");
                }
                if (result != 0) userNmb++;
            }
            Loader.updateProperty("userReg", "autoTestUserReg" + userNmb + "@test.com");
            return "autoTestUserReg" + userNmb + "@test.com";
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                resultSets.close();
                statement.close();
//                conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public String getUserId(String userLogin) {
        String result = "Can't find userID!";
        try {
            statement = conn.createStatement();//Готовим запрос
            resultSets = statement.executeQuery("SELECT UserId FROM users WHERE Login='" + userLogin + "'");
            while (resultSets.next()) {
                result = resultSets.getString("UserId");
            }
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return result;
        } finally {
            try {
                resultSets.close();
                statement.close();
//                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public String getGroupId(String groupName) {
        String result = "Can't find GroupID!";
        try{
            statement = conn.createStatement();//Готовим запрос
            resultSets = statement.executeQuery("SELECT GroupId FROM groups WHERE  GroupKey='" + groupName + "'");
            while(resultSets.next()){
                result = resultSets.getString("GroupId");
            }
            return result;
        } catch(Exception e){
            e.printStackTrace();
            return result;
        }
        finally{
            try {
                resultSets.close();
                statement.close();
//               conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean getBGStatus(String bgID) {
        String result = null;
        try{
            statement = conn.createStatement();//Готовим запрос
            resultSets = statement.executeQuery("SELECT isShow FROM buyersguides WHERE BuyersGuideId=" + bgID);
            while(resultSets.next()){
                result = resultSets.getString("isShow");
            }
        } catch(Exception e){
            e.printStackTrace();
        } finally {
            try {
                resultSets.close();
                statement.close();
//                conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (result == null) {
            return false;
        } else return true;
    }

    public boolean getIBGStatus(String bgID) {
        int result = 0;
        try {
            statement = conn.createStatement();//Готовим запрос
            resultSets = statement.executeQuery("SELECT isShow FROM ibgstatuses WHERE IBGStatusId=" + bgID);
            while (resultSets.next()) {
                result = resultSets.getInt("isShow");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                resultSets.close();
                statement.close();
//                conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (result == 0) {
            return false;
        } else return true;
    }

    public int getCountShownBG_DB() {
        int result=-1;
        try{
            statement = conn.createStatement();//Готовим запрос
            resultSets = statement.executeQuery("SELECT COUNT(isShow) as C FROM buyersguides Where ActivityStatusId=0 AND isShow=1");
            while(resultSets.next()){
                result = resultSets.getInt("C");
            }
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return result;
        } finally {
            try {
                resultSets.close();
                statement.close();
//                conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public int getCountShownIBG_DB() {
        int result = -1;
        try {
            statement = conn.createStatement();//Готовим запрос
            resultSets = statement.executeQuery("SELECT COUNT(isShow) as C FROM ibgstatuses WHERE ActivityStatusId=1 AND isShow=1");
            while (resultSets.next()) {
                result = resultSets.getInt("C");
            }
            return result;
        } catch(Exception e){
            e.printStackTrace();
            return result;
        }
        finally{
            try {
                resultSets.close();
                statement.close();
//                conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public int getLogIdForQVadmin() {
        int result = -1;
        try {
            statement = conn.createStatement();//Готовим запрос
            resultSets = statement.executeQuery("SELECT max(logId) as maxLog FROM logs WHERE userId=209");
            while (resultSets.next()) {
                result = resultSets.getInt("maxLog");
            }
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("ERROR! LogId was not found in DB!");
        } finally {
            try {
                resultSets.close();
                statement.close();
//                conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void setExpiredGroup(String groupName) {
        try {
            statement = conn.createStatement();//Готовим запрос
            int rowsUpdate = statement.executeUpdate("UPDATE groups SET ExpirationDate='2015-05-05 00:00:00'  where GroupKey='" + groupName + "' and ActivityStatusId=1");
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Can't set Group expiration date at the DB!");
        } finally {
            try {
                resultSets.close();
                statement.close();
//                conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void setExpiredUser(String userLogin) {
        try {
            statement = conn.createStatement();//Готовим запрос
            int rowsUpdate = statement.executeUpdate("UPDATE users SET NextPaymentDate='2015-05-05 00:00:00', CanDownloadBgToDate='2015-05-05 00:00:00'  WHERE Login='" + userLogin + "'");
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Can't set User expiration date at the DB!");
        } finally {
            try {
                statement.close();
//                conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public int getUserStatus(String userLogin) {
        int result = -1;
        try {
            statement = conn.createStatement();//Готовим запрос
            resultSets = statement.executeQuery("SELECT ActivityStatusId FROM users WHERE Login='" + userLogin + "'");
            while (resultSets.next()) {
                result = resultSets.getInt("ActivityStatusId");
            }
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Can't find userStatus in DB!");
        } finally {
            try {
                resultSets.close();
                statement.close();
//                 conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean userAccesToBGandIBG(String userLogin) {
        int IBG = -1;
        int BG = -1;
        try {
            statement = conn.createStatement();//Готовим запрос
            resultSets = statement.executeQuery("SELECT HasAccessToDashboard, HasAccessToBuyersGuides FROM users WHERE Login='" + userLogin + "'");
            while (resultSets.next()) {
                IBG = resultSets.getInt("HasAccessToDashboard");
                BG = resultSets.getInt("HasAccessToBuyersGuides");
            }
            if (IBG == 0 && BG == 0) {
                return false;
            } else return true;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Can't find userStatus in DB!");
        } finally {
            try {
                resultSets.close();
                statement.close();
//                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void closeDBConnection() {
        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
