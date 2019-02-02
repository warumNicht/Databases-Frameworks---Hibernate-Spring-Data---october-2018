package ORM;

import ORM.annotations.Column;
import ORM.annotations.Entity;
import ORM.annotations.Id;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Type;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class EntityManager<E> implements DbContext<E> {
    private Connection connection;

    public EntityManager(Connection connection) {
        this.connection = connection;
    }

    public  boolean persist(E entity) throws IllegalAccessException, ClassNotFoundException, SQLException {

        //this.doAlterTable(entity.getClass());
        Field primary=this.getFieldId(entity.getClass());
        primary.setAccessible(true);
        Object valueId = primary.get(entity);

        if(valueId==null||(int)valueId<=0){
            return this.doInsert(entity,primary);
        }
        return doUpdate(entity,primary);
    }

    public Iterable<E> find(Class<E> table) throws SQLException, IllegalAccessException, InvocationTargetException, InstantiationException {
        String tableName=this.getTableName(table);
        String query="SELECT * FROM " + tableName + ";";

        return this.getEntityCollection(table,query);
    }

    public Iterable<E> find(Class<E> table, String where) throws SQLException, IllegalAccessException, InvocationTargetException, InstantiationException {
        String tableName=this.getTableName(table);
        String query="SELECT * FROM " + tableName + " WHERE "+ (where!=null ? where : "1") +";";

        return this.getEntityCollection(table,query);
    }

    public E findFirst(Class<E> table) throws InvocationTargetException, SQLException, InstantiationException, IllegalAccessException {
        String tableName=this.getTableName(table);
        String query="SELECT * FROM " + tableName  +" LIMIT 1;";

        return this.getFirstEntity(table,query);
    }

    public E findFirst(Class<E> table, String where) throws SQLException, IllegalAccessException, InstantiationException, InvocationTargetException {
        String tableName=this.getTableName(table);
        String query="SELECT * FROM " + tableName + " WHERE "+ (where!=null ? where : "1") +" LIMIT 1;";

        return this.getFirstEntity(table,query);
    }

    public void doDelete(Class<?> table,String condition) throws Exception {
        String tableName=this.getTableName(table);
        if(this.checkIfTableExists(tableName)){
            String query=String.format("DELETE FROM %s WHERE %s;",tableName,condition);
            connection.prepareStatement(query).execute();
        }else {
            throw new Exception("Table does not exists!");
        }
    }

    private E getFirstEntity(Class<E> table, String query) throws SQLException, IllegalAccessException, InvocationTargetException, InstantiationException {
        PreparedStatement statement=this.connection.prepareStatement(query);
        ResultSet resultSet=statement.executeQuery();
        if(resultSet.first()==false){
            return null;
        }

        E entity=  table.cast(table.getConstructors()[0].newInstance());
        this.fillEntity(table,resultSet,entity);
        return entity;
    }

    private Iterable<E> getEntityCollection(Class<E> table, String query) throws SQLException, IllegalAccessException, InvocationTargetException, InstantiationException {
        PreparedStatement statement=connection.prepareStatement(query);
        ResultSet resultSet=statement.executeQuery();

        List<E>  res=new ArrayList<>();
        while (resultSet.next()){
            E entity= (E) table.getConstructors()[0].newInstance();
            this.fillEntity(table,resultSet,entity);
            res.add(entity);
        }
        return res;
    }

    private boolean doUpdate(E entity, Field primary) throws ClassNotFoundException, IllegalAccessException, SQLException {
        String tableName=this.getTableName(entity.getClass());
        Field[] declaredFields = entity.getClass().getDeclaredFields();
        String[] columns=this.getColumnsNames(declaredFields).split(", ");
        String[] valuesToInsert=this.getValues(declaredFields,entity).split(", ");

        Field id=this.getFieldId(entity.getClass());
        String fildIdColumnName=id.getAnnotation(Column.class).name();
        id.setAccessible(true);
        int wantedId= (int) id.get(entity);

        StringBuilder query=new StringBuilder("UPDATE "+tableName+" SET ");

        for (int i = 0; i < valuesToInsert.length; i++) {
            String currentValue=String.format("%s=%s, ",columns[i],valuesToInsert[i]);
            query.append(currentValue);
        }
        query.append(String.format(" WHERE %s=%d;",fildIdColumnName,wantedId));
        query.replace(query.lastIndexOf(", "),query.lastIndexOf(",")+2,"");

        String finalQuery=query.toString();

        return this.connection.prepareStatement(finalQuery).execute();
    }

    private boolean doInsert(E entity, Field primary) throws IllegalAccessException, ClassNotFoundException, SQLException {
        String tableName=this.getTableName(entity.getClass());
        if(this.checkIfTableExists(tableName)==false){
            this.doCreateTable(entity.getClass());
        }

        Field[] declaredFields = entity.getClass().getDeclaredFields();

        for (Field declaredField : declaredFields) {
            if(!this.checkifFieldExists(tableName,declaredField)){
                this.doAlterTable(entity.getClass());
                break;
            }
        }

        String columns=this.getColumnsNames(declaredFields);
        String valuesToInsert=this.getValues(declaredFields,entity);

        String query=String.format("INSERT INTO %s(%s) VALUES (%s);",
                tableName,columns,valuesToInsert);
        boolean res=this.connection.prepareStatement(query).execute();

        int lastId=getLastId(tableName,entity.getClass());

        for (Field declaredField : declaredFields) {

            if(declaredField.isAnnotationPresent(Id.class)){
                declaredField.setAccessible(true);
                declaredField.set(entity,lastId);
                break;
            }
        }
        return res;
    }

    private int getLastId(String tableName,Class entity) throws SQLException {
        Field fieldId=this.getFieldId(entity);
        String idColumnName=fieldId.getDeclaredAnnotation(Column.class).name();

        String query=String.format("SELECT %1$s FROM %2$s ORDER BY %1$s DESC LIMIT 1",idColumnName,tableName);
        PreparedStatement statement=this.connection.prepareStatement(query);

        ResultSet resultSet=statement.executeQuery();

        if(resultSet.first()){
            return resultSet.getInt(idColumnName);
        }
        return 0;
    }

    private String getValues(Field[] declaredFields, E entity) throws IllegalAccessException, ClassNotFoundException {
        StringBuilder res=new StringBuilder();
        for (Field declaredField : declaredFields) {
            if(declaredField.isAnnotationPresent(Column.class) &&
                    !declaredField.isAnnotationPresent(Id.class)){
                declaredField.setAccessible(true);

                Class<?> type = declaredField.getType();
                String currValue=null;
              // String number = type.getSuperclass().getSimpleName();

                if(type.getSimpleName().equals("Date")){
                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

                    String dateString = format.format(  declaredField.get(entity)  );
                    currValue="'"+dateString+"'";
                }else if(type==double.class||type==int.class||type==Integer.class){
                    currValue=""+declaredField.get(entity);
                }else{
                    currValue="'"+declaredField.get(entity)+"'";
                }
                res.append(currValue).append(", ");
            }
        }
        return res.substring(0,res.lastIndexOf(", "));
    }

    private String getColumnsNames(Field[] declaredFields) {
        StringBuilder res=new StringBuilder();
        for (Field declaredField : declaredFields) {
            if(declaredField.isAnnotationPresent(Column.class) &&
                    !declaredField.isAnnotationPresent(Id.class)){
                String currName=declaredField.getAnnotation(Column.class).name();
                res.append(currName).append(", ");
            }
        }
        return res.substring(0,res.lastIndexOf(", "));
    }

    private String getTableName(Class<?> entity) {
        if(entity.isAnnotationPresent(Entity.class)){
            return entity.getAnnotation(Entity.class).name();
        }
        return entity.getClass().getSimpleName();
    }


    private void fillEntity(Class<E> table, ResultSet resultSet, E entity) throws SQLException, IllegalAccessException {
        Field[] fields=table.getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            Field field = fields[i];
            field.setAccessible(true);
            if(field.isAnnotationPresent(Column.class)){
                this.fillField(field,entity,resultSet,field.getAnnotation(Column.class).name());
            }
        }
    }

    private void fillField(Field field, E entity, ResultSet resultSet, String name) throws SQLException, IllegalAccessException {
        field.setAccessible(true);
        String type = field.getType().getSimpleName();
//        resultSet.beforeFirst();
//        resultSet.next();
        switch (type) {
            case "int":
            case "Integer":{
                field.set(entity,resultSet.getInt(name));
            }break;
            case "Date":{
                Date date=resultSet.getTimestamp(name);
                field.set(entity,date);
            }break;
            case "String":{
                field.set(entity,resultSet.getString(name));
            }break;
            case "double":
            case "Double":{
                field.set(entity,resultSet.getDouble(name));
            }break;
        }
    }

    private Field getFieldId(Class entity){
        return  Arrays.stream(entity.getDeclaredFields())
                .filter(field->field.isAnnotationPresent(Id.class))
                .findFirst()
                .orElseThrow(()->new UnsupportedOperationException(
                        "Entity does not have primary key"
                ));
    }

    private <E> void doCreateTable(Class entity) throws SQLException {
        String tableName=this.getTableName(entity);
        Field[] fields=entity.getDeclaredFields();
        StringBuilder columns=new StringBuilder();

        for (int i = 0; i < fields.length; i++) {
            Field field = fields[i];
            field.setAccessible(true);
            
            String columnName=field.getAnnotation(Column.class).name();
            columns.append(columnName).append(" ");
            String columnSQL_type=this.getDBType(field);
            columns.append(columnSQL_type);
            if(field.isAnnotationPresent(Id.class)){
                columns.append(" PRIMARY KEY AUTO_INCREMENT");
            }
            if(i<fields.length-1){
                columns.append(",").append(System.lineSeparator());
            }
        }
        String query=String.format("CREATE TABLE %s (%s);", tableName,columns);
        connection.prepareStatement(query).execute();
    }

    private String getDBType(Field field) {
        field.setAccessible(true);
        String fieldType=field.getType().getSimpleName();
        String res="";
        switch (fieldType){
            case "int":
            case "Integer":{
                res="INT";
            }break;
            case "double":
            case "Double":{
                res="DOUBLE";
            }break;
            case "String":{
                res="VARCHAR(40)";
            }break;
            case "Date":{
                res="TIMESTAMP";
            }break;
            case "Boolean":
            case "boolean":{
                res="BOOLEAN";
            }break;
        }
        return res;
    }
    private <E> void doAlterTable(Class entity) throws SQLException {
        String tableName=this.getTableName(entity);
        Field[] fields=entity.getDeclaredFields();
        StringBuilder types=new StringBuilder();

        for (Field field : fields) {
            if(!this.checkifFieldExists(tableName,field)){
                String fieldName=field.getAnnotation(Column.class).name();
                String fieldSQLType=this.getDBType(field);
                types.append(String.format("ADD COLUMN %s %s,%n",fieldName,fieldSQLType));
            }
        }
        types.setCharAt(types.lastIndexOf(","),' ');
        String query=String.format("ALTER TABLE %s%n%s;",tableName,types.toString().trim());
        connection.prepareStatement(query).execute();
    }
    private boolean checkIfExistsFieldInTable(Field field, Class entity) throws SQLException {
        String fieldName=field.getAnnotation(Column.class).name();
        String tableName=this.getTableName(entity);
        String query=String.format("SELECT column_name FROM INFORMATION_SCHEMA.COLUMNS\n" +
                "WHERE TABLE_SCHEMA='testorm' AND TABLE_NAME ='%s';",tableName);
        ResultSet resultSet = connection.prepareStatement(query).executeQuery();

        while (resultSet.next()){
            String currentFieldName=resultSet.getString("column_name");
            if(currentFieldName.equals(fieldName)){
                return true;
            }
        }
        return false;
    }
    private boolean checkIfTableExists(String tableName) throws SQLException {
        String query=String.format("SELECT TABLE_NAME FROM INFORMATION_SCHEMA.`TABLES`\n" +
                "WHERE TABLE_SCHEMA = 'testorm' AND TABLE_NAME='%s';",tableName);
        ResultSet resultSet = connection.prepareStatement(query).executeQuery();

        if(resultSet.first()){
            return true;
        }else {
            return false;
        }
    }
    private boolean checkifFieldExists(String tableName,Field field) throws SQLException {
        String query=String.format("SELECT COLUMN_NAME\n" +
                "FROM   INFORMATION_SCHEMA.COLUMNS\n" +
                "WHERE  TABLE_NAME = '%s'\n" +
                "AND COLUMN_NAME = '%s' AND TABLE_SCHEMA ='testorm';",tableName,
                field.getAnnotation(Column.class).name());
        ResultSet set = connection.prepareStatement(query).executeQuery();
        if(set.first()){
            return true;
        }
        return false;
    }
}
