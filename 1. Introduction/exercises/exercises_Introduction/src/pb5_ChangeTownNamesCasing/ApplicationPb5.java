package pb5_ChangeTownNamesCasing;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ApplicationPb5 implements Runnable {
    private Connection connection;

    public ApplicationPb5(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void run() {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

            String country=reader.readLine();

            PreparedStatement getLowerCaseTownNames=connection.prepareStatement(
                    "SELECT t.name\n" +
                            "FROM towns AS t\n" +
                            "WHERE UPPER(t.name) != BINARY(t.name) AND t.country =? \n" +
                            "GROUP BY t.id");
            getLowerCaseTownNames.setString(1,country);

            ResultSet townNamesToChange = getLowerCaseTownNames.executeQuery();
            List<String> finalTowns=new ArrayList<>();

            if(townNamesToChange.next()==false){
                System.out.println("No town names were affected.");
            }else {
                townNamesToChange.beforeFirst();
                while (townNamesToChange.next()){
                    finalTowns.add(townNamesToChange.getString("name").toUpperCase());
                }
                PreparedStatement updateLowerCaseTownNames=connection.prepareStatement("UPDATE towns AS t\n" +
                        "SET t.name=UPPER(t.name)\n" +
                        "WHERE UPPER(t.name) != BINARY(t.name) AND t.country=?;");
                updateLowerCaseTownNames.setString(1,country);
                updateLowerCaseTownNames.executeUpdate();

                int affectedTowns=finalTowns.size();

                System.out.println(String.format("%d town name%s were affected.",affectedTowns,
                        affectedTowns>1 ? "s" : ""));
                System.out.println(finalTowns);
            }
            connection.close();

        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }
}
