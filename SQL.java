package Utilities;

import java.sql.*;

public class SQL
{
    private String host;
    private String user;
    private String pass;
    private String database;
    public Connection connection;
    
    public String getHost() {
        return this.host;
    }
    
    public String getUser() {
        return this.user;
    }
    
    public String getPassword() {
        return this.pass;
    }
    
    public String getDatabase() {
        return this.database;
    }
    
    public void setHost(final String host) {
        this.host = host;
    }
    
    public void setUser(final String user) {
        this.user = user;
    }
    
    public void setPassword(final String pass) {
        this.pass = pass;
    }
    
    public void setDatabase(final String database) {
        this.database = database;
    }
    
    public SQL() {
    }
    
    public SQL(final String ip, final String userName, final String access, final String db) {
        this.host = ip;
        this.user = userName;
        this.pass = access;
        this.database = db;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            this.connection = DriverManager.getConnection("jdbc:mysql://" + ip + "/" + db + "?user=" + userName + "&password=" + access);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public Connection getConnection() {
        return this.connection;
    }
}
