package pb4_AddMinion;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;

public class ApplicationPb4 implements Runnable {
    private Connection connection;

    public ApplicationPb4(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void run() {

        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

            this.connection.setAutoCommit(false);

            String[] minionTokens = reader.readLine().split("\\s+");
            String villainName = reader.readLine().split("\\s+")[1];

            String minionName = minionTokens[1];
            int minionAge = Integer.parseInt(minionTokens[2]);
            String minionTown = minionTokens[3];

            if (minionAge < 1) {
                throw new IllegalArgumentException("Age must be positive");
            }
            StringBuilder finalMessage = new StringBuilder();

            String minionInsertionMessage = this.insertMinionIfNotExists(minionName, minionAge, minionTown);
            if (minionInsertionMessage != null) {
                finalMessage.append(minionInsertionMessage);
            }
            String villainInsertionMessage = this.insertVillainIfNotExists(villainName);
            if (villainInsertionMessage != null) {
                finalMessage.append(villainInsertionMessage);
            }

            int villainId = this.getVillainId(villainName);
            int minionId = this.getMinionId(minionName,minionAge,minionTown);

            PreparedStatement setMinionToVillain = this.connection
                    .prepareStatement("INSERT INTO minions_villains(minion_id,villain_id)\n" +
                            "VALUES(?,?);");
            setMinionToVillain.setInt(1, minionId);
            setMinionToVillain.setInt(2, villainId);
            setMinionToVillain.executeUpdate();

            finalMessage.append(String.format("Successfully added %s to be minion of %s",
                    minionName, villainName));

            this.connection.commit();

            System.out.println(finalMessage);

        } catch (SQLException | IOException e) {
            try {
                connection.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        }
    }
    private  int getMinionId(String minionName,int minionAge,String minionTown) throws SQLException {
        int townId=getTownId(minionTown);
        //two minions are considered equal if they have name,age and town equal
        PreparedStatement getMinionId = connection.prepareStatement("SELECT m.id\n" +
                "FROM minions AS m\n" +
                " WHERE m.name=? AND m.age=? AND m.town_id=?");
        getMinionId.setString(1, minionName);
        getMinionId.setInt(2,minionAge);
        getMinionId.setInt(3,townId);
        ResultSet minionIdSet = getMinionId.executeQuery();

        minionIdSet.next();
        int minionId = minionIdSet.getInt("id");
        return minionId;
    }

    private  int getVillainId(String villainName) throws SQLException {
        PreparedStatement getVillainId = this.connection.prepareStatement("SELECT v.id FROM villains AS v " +
                "WHERE  v.name=?");
        getVillainId.setString(1, villainName);
        ResultSet villainIdSet = getVillainId.executeQuery();
        villainIdSet.next();
        int villainId = villainIdSet.getInt("id");
        return villainId;
    }

    private  String insertMinionIfNotExists(String minionName, int minionAge, String minionTown) throws SQLException {
        String result = null;
        if (!this.isExistingTown(minionTown)) {
            PreparedStatement insertNewTown = this.connection.prepareStatement("INSERT INTO towns(name,country)\n" +
                    "VALUES (?,?);");


            insertNewTown.setString(1, minionTown);
            insertNewTown.setString(2,"Deutschland");
            insertNewTown.executeUpdate();
            result = String.format("Town %s was added to the database.%n", minionTown);
        }
        int townId = this.getTownId(minionTown);

        PreparedStatement ifMinionExists = this.connection.prepareStatement("SELECT * FROM minions AS m WHERE m.name=?" +
                "AND m.age=? AND m.town_id=?");
        ifMinionExists.setString(1, minionName);
        ifMinionExists.setInt(2,minionAge);
        ifMinionExists.setInt(3,townId);

        if (ifMinionExists.executeQuery().next() == false) {
            PreparedStatement insertMinion = this.connection.prepareStatement("INSERT INTO minions(name,age,town_id)" +
                    " VALUES (?,?,?)");
            insertMinion.setString(1, minionName);
            insertMinion.setInt(2, minionAge);
            insertMinion.setInt(3, townId);
            int i = insertMinion.executeUpdate();
        }

        return result;
    }

    private  String insertVillainIfNotExists(String villainName) throws SQLException {
        PreparedStatement ifVillainExists = this.connection.prepareStatement("SELECT v.name\n" +
                "FROM villains AS v\n" +
                "WHERE v.name=?;");
        ifVillainExists.setString(1, villainName);
        ResultSet existingVillain = ifVillainExists.executeQuery();

        if (existingVillain.next() == false) {
            PreparedStatement insertVillain = this.connection.prepareStatement("INSERT INTO villains(name,evilness_factor)\n" +
                    "VALUES(?,'evil');");
            insertVillain.setString(1, villainName);
            insertVillain.executeUpdate();
            return String.format("Villain %s was added to the database.%n", villainName);
        }
        return null;
    }

    private  int getTownId(String minionTown) throws SQLException {
        PreparedStatement getTownId = this.connection.prepareStatement("SELECT t.id\n" +
                "FROM towns AS t\n" +
                "WHERE t.name=?");
        getTownId.setString(1, minionTown);
        ResultSet townSet = getTownId.executeQuery();
        townSet.next();
        int res = townSet.getInt("id");
        return res;
    }

    private  boolean isExistingTown(String minionTown) throws SQLException {
        PreparedStatement ifTownExist = this.connection.prepareStatement("SELECT t.name\n" +
                "FROM towns AS t\n" +
                "WHERE t.name=?;");
        ifTownExist.setString(1, minionTown);
        ResultSet existingTown = ifTownExist.executeQuery();
        return existingTown.next();
    }



}
