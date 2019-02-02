import ConnectionCreator.ConnectionCreator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class pb7_PrintAllMinionNames {
    public static void main(String[] args) throws IOException, SQLException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        Connection connection=ConnectionCreator.createConnection("root","0000");

        PreparedStatement stat = connection.prepareStatement("SELECT m.name FROM  minions AS m;");

        ResultSet minionsNames = stat.executeQuery();
        List<String> names = new ArrayList<>();
        while (minionsNames.next()) {
            names.add(minionsNames.getString("name"));
        }
        int lastIndex = (names.size() - 1) / 2;
        if(!names.isEmpty())
        for (int i = 0; i <= lastIndex; i++) {
            System.out.println(names.get(i));
            int innerIndex = names.size() - 1 - i;
            if(innerIndex!=lastIndex){
                System.out.println(names.get(innerIndex));
            }

        }

        connection.close();
    }
}
