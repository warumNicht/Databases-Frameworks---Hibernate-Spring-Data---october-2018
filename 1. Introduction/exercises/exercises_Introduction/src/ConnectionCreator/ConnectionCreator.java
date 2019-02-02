package ConnectionCreator;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionCreator {
    public static Connection createConnection(String user,String password) throws SQLException {
        Properties props = new Properties();
        props.setProperty("user",user);
        props.setProperty("password",password);
        Connection connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/minions_db",props);
        return connection;
    }
}
