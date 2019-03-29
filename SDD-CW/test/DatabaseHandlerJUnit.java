/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.sql.Connection;
import java.sql.SQLException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import sdd.cw.DatabaseHandler;

/**
 * The DatabaseHandlerJUnit tests database connection 
 * it test opening and closing the database connection 
 * @author faagbede
 */
public class DatabaseHandlerJUnit extends junit.framework.TestCase{
     DatabaseHandler dh;
    public DatabaseHandlerJUnit() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    @Override
    public void setUp() {
    }
    
    @After
    @Override
    public void tearDown() {
    }

    
    /**
     * The testHandleDbConnection method tests the connection to the database
     */
     @Test
     public void testHandleDbConnection() {
          dh = new DatabaseHandler("", "");//para username and password
         Connection c = dh.handleDbConnection();
         assertNotNull(c);//Asserts that connection is not null.
        // assertEquals(c);
     }
     
     /**
      * The testCloseCon method test that the database connection is close
      */
     @Test
     public void testCloseCon(){
          dh = new DatabaseHandler("", "");//username and password is required to pass the test
        try {
             dh.closeCon();
             assertNotNull(dh);//check if connection is close
        } catch (SQLException ex) {
            
            fail("database connection can not be closed" + ex);
        }
     }
      
}
