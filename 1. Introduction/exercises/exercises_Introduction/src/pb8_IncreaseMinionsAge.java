import ConnectionCreator.ConnectionCreator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.*;

public class pb8_IncreaseMinionsAge {
    public static void main(String[] args) throws IOException, SQLException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Connection connection=ConnectionCreator.createConnection("root","0000");

        String ids= reader.readLine();
        ids=ids.trim().replace(" ",", ");

        updateNamesIncreaseAge(connection,ids);
        printNamesAgesFromMinions(connection);

        connection.close();
    }
    private static void updateAge(Connection connection,int[] minions_id,String placeholders) throws SQLException {
        PreparedStatement statement=connection.prepareStatement("UPDATE minions AS m\n" +
                "SET m.age=m.age+1\n" +
                "WHERE m.id IN ("+placeholders+");");


        for (int i = 0; i < minions_id.length; i++) {
            statement.setInt(i+1,minions_id[i]);
        }
        statement.executeUpdate();
    }

    private static void updateNamesIncreaseAge(Connection connection, String placeholders) throws SQLException {
        Statement minionsStatement =
                connection.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE);


        ResultSet resultSet = minionsStatement.executeQuery("SELECT * FROM minions AS m\n" +
                "WHERE m.id IN ("+placeholders+");");
        while (resultSet.next()){
            String wanted=resultSet.getString("name");
            resultSet.updateString("name",updateCaseNames( wanted));
            resultSet.updateInt("age",resultSet.getInt("age")+1);
            resultSet.updateRow();
        }
    }

    private static String updateCaseNames(String name) throws SQLException {
        StringBuilder toChange=new StringBuilder(name);
        toChange.setCharAt(0,Character.toUpperCase(toChange.charAt(0)));
        for (int i = 1; i < toChange.length(); i++) {
            char c = toChange.charAt(i);
            if(toChange.charAt(i-1)==' '&&toChange.charAt(i)!=' '){
                toChange.setCharAt(i,Character.toUpperCase(toChange.charAt(i)));
            }
        }
        String res=toChange.toString();
        return res;
    }
    private static void printNamesAgesFromMinions(Connection connection) throws SQLException {
        PreparedStatement statement=connection.prepareStatement("SELECT m.name,m.age\n" +
                "FROM minions AS m;");
        ResultSet resultSet=statement.executeQuery();

        while (resultSet.next()){
            String res=String.format("%s %d",resultSet.getString("name"),resultSet.getInt("age"));
            System.out.println(res);
        }
    }
}
