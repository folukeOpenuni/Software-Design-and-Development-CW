package sdd.cw;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * The DataQualityCheck class DataQualityCheck class finds all records within the database that have an error 
 * value for engine capacity and export these records to a text file called nocc.txt
 * @author faagbede
 */
public class DataQualityCheck {

    /**
     *
     */
    private Statement stmt;
    private String query;
    private ResultSet rs = null;
    private String fileName;
    private PrintWriter outputStream;

    DatabaseHandler dbCon;

    /**
     * Parameterless constructor for DataQualityCheck
     */
    public DataQualityCheck() {

    }

    /**
     * The findRecord method finds specified record within the database by
     * instantiating the DatabaseHandler to establish a connection, creates a
     * Statement object for sending SQL statements to the database, Execute SQL
     * Statement/query the database and get the resultSet when the Engine
     * capacity is null or -1
     *
     * @return the resultSet of Engine capacity error
     * @throws sdd.cw.UnknownColumnException if there is an error in the sql statement
     */
    public ResultSet findRecord() throws UnknownColumnException {
        dbCon = new DatabaseHandler("", "");//instaintite DatabaseHandler
        try {
            stmt = dbCon.handleDbConnection().createStatement();//Creates a Statement object for sending SQL statements to the database
        } catch (SQLException ex) {
            System.out.println("mysql error syntax " + ex);
            Logger.getLogger(DataQualityCheck.class.getName()).log(Level.SEVERE, null, ex);
        }
        //sql statement to query the database
        query = "SELECT  `Accident_Index` ,  `Vehicle_Type` ,  `Vehicle_Manoeuvre` ,  `Sex_of_Driver` ,  `Age_Band_of_Driver` ,  `Engine_Capacity_(CC)` ,  `Age_of_Vehicle` ,  `make` ,  `model` \n"
                        + "FROM  `make_model` \n"
                        + "WHERE  `Engine_Capacity_(CC)` = -1\n"
                        + "OR  `Engine_Capacity_(CC)` IS NULL ";

//        query = "SELECT * \n"
//                + "FROM  `make_model` \n"
//                + "WHERE  `Engine_Capacity_(CC)` = -1";
        try {
            rs = stmt.executeQuery(query);//SQL statement to be sent to the database is executed 
        } catch (SQLException ex) {
            System.out.println("sql exception");
            throw new UnknownColumnException("there is no such column in the database");
        }

        return rs;
    }

    /**
     * The exportRecord calls findRecord method and export records for Engine
     * capacity Error value to a text file
     *
     * @return resultSet
     * @throws SQLException when findRecord method throws an exception
     * @throws sdd.cw.UnknownColumnException
     */
    public ResultSet exportRecord() throws SQLException, UnknownColumnException {

        fileName = "noCC.txt"; //name of file to write/export to 
        outputStream = null;

        try {
            outputStream = new PrintWriter(fileName); //invoke PrintWriter constructor 
            rs = findRecord();
            while (rs.next()) {

                String ai = rs.getString("Accident_Index");
                String vt = rs.getString("Vehicle_Type");
                String vm = rs.getString("Vehicle_Manoeuvre");
                String sod = rs.getString("Sex_of_Driver");
                String abod = rs.getString("Age_Band_of_Driver");
                String ec = rs.getString("Engine_Capacity_(CC)");
                String aov = rs.getString("Age_of_Vehicle");
                String make = rs.getString("Make");
                String model = rs.getString("Model");

                outputStream.print(ai + ", ");
                outputStream.print(vt + ", ");
                outputStream.print(vm + ", ");
                outputStream.print(sod + ", ");
                outputStream.print(abod + ", ");
                outputStream.print(ec + ", ");
                outputStream.print(aov + ", ");
                outputStream.print(make + ", ");
                outputStream.println(model);

            }

            //read a text file that was exported
//            ArrayList<String> arr = new ArrayList<>();//array to keep track of vaule in the file  
//            BufferedReader readNoCCTextFile = new BufferedReader(new FileReader("noCC.txt"));
//            String line = readNoCCTextFile.readLine();
//
//            while(line != null){
//                System.out.println(line);
//                line = readNoCCTextFile.readLine();
//            }
        } catch (FileNotFoundException ex) {

            System.out.println(ex.getMessage());
            Logger.getLogger(DataQualityCheck.class.getName()).log(Level.SEVERE, null, ex);

        } finally {
            //close result set
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                }
            }
            //close statement
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                }
            }
            //close connection
            if (dbCon.handleDbConnection().isClosed() == false) {
                dbCon.closeCon();
            }
        }

        return rs;
    }

    /**
     * The DefaultTableModel method 
     * ResultSetMetaData is An object that can be used to
     * get information about the types and properties of the COLUMNS in a
     * ResultSet object
     *
     * @param rs the resultSet
     * @return table model data
     * @throws SQLException if an error occurs
     */
    public DefaultTableModel buildDtm(ResultSet rs) throws SQLException {

        ResultSetMetaData md = rs.getMetaData();

        // names of columns in make_model table 
        Vector<String> columnNames = new Vector<>();
        int columnCount = md.getColumnCount();
        for (int column = 1; column <= columnCount; column++) {
            columnNames.add(md.getColumnName(column));
        }

        // data of the make_model table 
        Vector<Vector<Object>> data = new Vector<>();
        while (rs.next()) {
            Vector<Object> vector = new Vector<>();
            for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
                vector.add(rs.getObject(columnIndex));
            }
            data.add(vector);
        }

        return new DefaultTableModel(data, columnNames);
    }
}
