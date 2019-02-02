package pb3_GetMinionNames;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ApplicationPb3 implements Runnable {
    private Connection connection;

    public ApplicationPb3(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void run() {

        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            PreparedStatement ifExistVillain=this.connection.prepareStatement("SELECT v.name\n" +
                    "FROM villains AS v\n" +
                    "WHERE v.id=?;");
            int villainId=Integer.parseInt(reader.readLine());
            ifExistVillain.setInt(1,villainId);

            ResultSet wantedVillain=ifExistVillain.executeQuery();

            if(wantedVillain.next()==false){
                System.out.println(String.format("No villain with ID %d exists in the database.",villainId));
            }else{
                System.out.println(String.format("Villain: %s",wantedVillain.getString("name")));
                PreparedStatement minions=this.connection.prepareStatement("SELECT m.name,m.age\n" +
                        "FROM minions AS m\n" +
                        "JOIN minions_villains AS mv ON m.id=mv.minion_id\n" +
                        "WHERE mv.villain_id=?\n" +
                        "GROUP BY m.id;");
                minions.setInt(1,villainId);

                ResultSet minionsSet=minions.executeQuery();

                if(minionsSet.next()==false){
                    System.out.println("<no minions>");
                }else {
                    minionsSet.beforeFirst();
                    int index=0;
                    while (minionsSet.next()){
                        System.out.println(String.format("%d. %s %d",
                                ++index,minionsSet.getString("name"), minionsSet.getInt("age")));
                    }
                }
            }
            connection.close();

        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }
}
