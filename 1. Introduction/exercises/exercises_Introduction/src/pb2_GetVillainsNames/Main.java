package pb2_GetVillainsNames;

import ConnectionCreator.ConnectionCreator;

import java.sql.Connection;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        Connection connection = ConnectionCreator.createConnection("root", "0000");

        ApplicationPb2 applicationPb2 =new ApplicationPb2(connection);
        applicationPb2.run();
    }
}
