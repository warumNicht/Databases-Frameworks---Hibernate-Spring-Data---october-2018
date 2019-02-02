package pb4_AddMinion;

import ConnectionCreator.ConnectionCreator;

import java.sql.Connection;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        Connection connection = ConnectionCreator.createConnection("root", "0000");
        
        ApplicationPb4 applicationPb4 =new ApplicationPb4(connection);
        applicationPb4.run();
    }
}
