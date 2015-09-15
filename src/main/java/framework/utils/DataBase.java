package framework.utils;


import java.sql.*;
import java.text.SimpleDateFormat;

public class DataBase {
    private static Connection conn;
    private static Statement statement;
    private static ResultSet resultSets;

    private DataBase() {
        try {
            Class.forName("com.mysql.jdbc.Driver");//Регистрируем драйвер
            conn = DriverManager.getConnection("jdbc:mysql://mysql-kstest2.t1.tenet:3306/hays_ssstest_com_PRR_2032",
                    Loader.loadProperty("dbLogin"), Loader.loadProperty("dbPass"));//Установка соединения с БД
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        catch(Exception e){
            e.printStackTrace();
            Loader.logWritter("ERROR! Can't connect to DB");
        }

        System.out.println("Connections: ");
    }

    private static DataBase _instance=null;
    public static DataBase getInstance(){
        if(_instance==null){
            _instance=new DataBase();
        }
        return _instance;
    }

    public String executeSQLQuery(String query, String columnName){
        String result = "Can't_find";
        try {
            statement = conn.createStatement();//Готовим запрос
            resultSets = statement.executeQuery(query);
            while (resultSets.next()) {
                if (query.toLowerCase().contains("date")) {
                    SimpleDateFormat dateFormat = new SimpleDateFormat(Loader.loadProperty("dateFormat"));
                    Timestamp date = resultSets.getTimestamp(columnName);
                    result=dateFormat.format(date);
                }else result=resultSets.getString(columnName);
            }
        } catch (Exception e) {
            e.printStackTrace();
//            return result;
        } finally {
            try {
                resultSets.close();
                statement.close();
//                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }if (result.equals("Can't_find")){
            throw new RuntimeException("ERROR! Can't find in DB!");
        } else return result;

    }

    public void closeDBConnection() {
        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
