import ConnectionCreator.ConnectionCreator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.*;

public class pb6_RemoveVillain {
    public static void main(String[] args) throws SQLException, IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        Connection connection=ConnectionCreator.createConnection("root","0000");

        int villainId=Integer.parseInt(reader.readLine());

        PreparedStatement isExistingVillain=connection.prepareStatement("SELECT v.name FROM villains AS v WHERE v.id=?;");
        isExistingVillain.setInt(1,villainId);
        ResultSet villainName=isExistingVillain.executeQuery();
        if(villainName.next()==false){
            System.out.println("No such villain was found");
        }else {
            connection.setAutoCommit(false);
            try {
                String foundVillain=villainName.getString("name");

                PreparedStatement releaseMinions=connection.prepareStatement(
                        "DELETE FROM minions_villains \n" +
                                "WHERE villain_id=?;");
                releaseMinions.setInt(1,villainId);
                int affectedMinionsCount=releaseMinions.executeUpdate();

                PreparedStatement deleteVillain=connection.prepareStatement("DELETE FROM villains WHERE id=?;");
                deleteVillain.setInt(1,villainId);
                deleteVillain.executeUpdate();
                System.out.println(String.format("%s was deleted",foundVillain));
                System.out.println(String.format("%d minion%s released",affectedMinionsCount,
                        affectedMinionsCount>1||affectedMinionsCount==0 ? "s" : ""));

                connection.commit();
            }catch (Exception e){
                connection.rollback();
            }
        }
        connection.close();
    }
}
