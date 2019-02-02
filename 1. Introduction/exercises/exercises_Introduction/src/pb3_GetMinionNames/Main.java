package pb3_GetMinionNames;

import ConnectionCreator.ConnectionCreator;

import java.sql.Connection;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        Connection connection = ConnectionCreator.createConnection("root", "0000");

        ApplicationPb3 applicationPb3 =new ApplicationPb3(connection);
        applicationPb3.run();
    }
}
