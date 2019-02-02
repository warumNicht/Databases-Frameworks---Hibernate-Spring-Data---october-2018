package ORM;

import java.sql.Connection;

public class EntityManagerBuilder {
    private Connection connection;
    private String dataSource;

    public Connector configureConnectionString(){
        return null;
    }
    public EntityManager build(){

        return null;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public Connection getConnection() {
        return connection;
    }

    public String getDataSource() {
        return dataSource;
    }
}
