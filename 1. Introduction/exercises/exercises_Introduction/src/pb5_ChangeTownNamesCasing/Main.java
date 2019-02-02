package pb5_ChangeTownNamesCasing;

import ConnectionCreator.ConnectionCreator;

import java.sql.Connection;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        Connection connection = ConnectionCreator.createConnection("root", "0000");
        
        ApplicationPb5 applicationPb5 =new ApplicationPb5(connection);
        applicationPb5.run();
    }
}
