import ConnectionCreator.ConnectionCreator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.*;

public class pb9_IncreaseAgeStoredProcedure {
    public static void main(String[] args) throws IOException, SQLException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        Connection connection=ConnectionCreator.createConnection("root","0000");

        int minionId=Integer.parseInt(reader.readLine());
        PreparedStatement statement=connection.prepareStatement("CALL usp_get_older(?);");
        statement.setInt(1,minionId);
        statement.executeUpdate();

        PreparedStatement printMinionById=connection.prepareStatement("SELECT m.name,m.age\n" +
                "FROM minions AS m\n" +
                "WHERE m.id=?;");
        printMinionById.setInt(1,minionId);
        ResultSet resultSet=printMinionById.executeQuery();

        if(resultSet.first()){
            System.out.println(String.format("%s %d",resultSet.getString("name"),
                    resultSet.getInt("age")));
        }
        connection.close();
    }
}
