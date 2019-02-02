package pb2_GetVillainsNames;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ApplicationPb2 implements Runnable {
    private Connection connection;

    public ApplicationPb2(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void run() {

        try {
            PreparedStatement getVillainsNames=getVillainsNames = this.connection.prepareStatement(
                    "SELECT v.name,COUNT(mv.minion_id) AS minions_count\n" +
                    "FROM villains AS v \n" +
                    "LEFT JOIN minions_villains AS mv ON v.id=mv.villain_id\n" +
                    "GROUP BY v.id\n" +
                    "HAVING minions_count >3\n" +
                    "ORDER BY minions_count DESC;");

            ResultSet result=getVillainsNames.executeQuery();

            while (result.next()){
                System.out.printf("%s %d%n",result.getString("name"),
                        result.getInt("minions_count"));
            }
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
