package com.company;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter username default (root): ");
        //String user = sc.nextLine();
        //user = user.equals("") ? "root" : user;
        //System.out.println();

        //System.out.print("Enter password default (empty):");
        //String password = sc.nextLine().trim();
        //System.out.println();

        Properties props = new Properties();
        props.setProperty("user", "root");
        props.setProperty("password", "0000");

        Connection connection = DriverManager
                .getConnection("jdbc:mysql://localhost:3306/diablo", props);

        PreparedStatement stmt =
                connection.prepareStatement("SELECT *, COUNT(ug.id) AS rt\n" +
                        "FROM users AS u\n" +
                        "LEFT JOIN users_games AS ug ON ug.user_id=u.id\n" +
                        "WHERE u.user_name=?");

        String username = sc.nextLine();
        stmt.setString(1, username);
        ResultSet rs = stmt.executeQuery();


        rs.next();
        String proba=rs.getString("user_name");
        if(proba==null){
            System.out.println("No such user exists");
        }else{
            System.out.println( "User: "+rs.getString("user_name"));
            System.out.print(String.format("%s %s has played %d games",
                    rs.getString("first_name"),
                    rs.getString("last_name"), rs.getInt("rt")));
        }
        connection.close();
    }
}