/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sdd.cw;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * 
 * The role of this class is to create a connection to the database, by Loading the vendor specific driver and
 * establish a connection to the database. It calls the handleDbConnection method to make connection to the database.
 * It allows other classes to connect and query the database
 * with its handleDbConnection method which returns an object of the type Connection.
 * @author faagbede
 */
public class DatabaseHandler {

    private Connection conDb = null;
    private final String userName;
    private final String pwd;
    
    
    public DatabaseHandler(){
        this("","");
    }
    
    /**
     * The constructor for the DatabaseHandler class.  
     * @param userName the user name for lamp server or mysql credentials 
     * @param pwd user password for lamp server or mysql credentials password
     */

    public DatabaseHandler(String userName, String pwd) {
        this.userName = userName;
        this.pwd = pwd;
        try {
            Class.forName("com.mysql.jdbc.Driver");  //Dynamically loads a driver class, for MySQL database
            conDb = DriverManager.getConnection("jdbc:mysql://lamp.scim.brad.ac.uk/faagbede", userName, pwd);//Establishes connection to database by obtaining a Connection object
            System.out.println("200 succesfull connection");//this statement is print out if there is a succeful connection to the database
        } catch (ClassNotFoundException | SQLException cnfe) { //param: no definition for the class with the specified name could be found | error in database connection
            System.out.println(cnfe.getMessage());//prints out the error maassage
        }
    }


    /**
     * This method returns the connection object
     * @return java.sql.Connection
     */
    public Connection handleDbConnection() {
        return conDb; //return database connection
    }
    
    /**
     * The closeCon method closes the database connection
     * @throws SQLException when connection can not be close
     */
    public void closeCon() throws SQLException{
        conDb.close();
    }
}
