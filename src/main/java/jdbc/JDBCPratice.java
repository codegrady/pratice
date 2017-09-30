package jdbc;

import java.sql.*;

/**
 * JDBCPratice
 * Created by Grady on 2017/9/21.
 */
public class JDBCPratice {


    public static void main(String[] args) {
        getConn();
    }

    /**
     * jdbc
     */
    static void getConn(){
        String driver = "com.mysql.jdbc.Driver";
        String url = "jdbc:mysql://10.1.1.186:3306/test";
        String username = "root";
        String password = "123456";
        Connection connection = null;
        try {
            Class.forName(driver);
            connection = (Connection) DriverManager.getConnection(url,username,password);
            Statement stmt = connection.createStatement();
            String sql = "";
            ResultSet resultSet = stmt.executeQuery(sql);
            while (resultSet.next()){
                resultSet.getInt("");
                resultSet.getString("");
                //resultSet.get数据类型（字段名称）
            }
            resultSet.close();
            stmt.close();
            connection.close();
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
