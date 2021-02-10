package connectionUtil;

import util.LoadProperties;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class DBManager {

   private static Connection connection=null;

    private DBManager() {
        Properties properties = new Properties();
        LoadProperties.loadProperties(properties,"DBData.properties");

        String URL = (String) properties.get("connection.url");
        String Login = (String) properties.get("user");
        String Password = (String) properties.get("password");

        try {
            connection = DriverManager.getConnection(URL, Login, Password);
        } catch (SQLException  e) {
            System.out.println(e);
        }
    }




    public static Connection getConnection() {
        if (connection == null){
            new DBManager();
        }

        return connection;
    }

    public static void closeStatement(Statement statement){
        try {
            if (statement!=null && !statement.isClosed()){
                statement.close();
            }
        }catch (SQLException e){
            System.err.println(e);
        }
    }

    public static void closePreparedStatement(PreparedStatement preparedStatement){
        try {
            if (preparedStatement!=null && !preparedStatement.isClosed()){
                preparedStatement.close();
            }
        }catch (SQLException e){
            System.err.println(e);
        }
    }


    public static void closeResultSet(ResultSet resultSet){
        try {
            if (resultSet!=null && !resultSet.isClosed()){
                resultSet.close();
            }
        }catch (SQLException e){
            System.err.println(e);
        }
    }
}
