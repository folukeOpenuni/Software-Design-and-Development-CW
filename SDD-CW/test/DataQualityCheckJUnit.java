/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.sql.ResultSet;
import java.sql.SQLException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import sdd.cw.DataQualityCheck;
import sdd.cw.UnknownColumnException;

/**
 *The DataQualityCheckJUnit class tests the error value for engine capacity 
 * @author faagbede
 */
public class DataQualityCheckJUnit extends junit.framework.TestCase{
    
    private static DataQualityCheck dqc;
    
    public DataQualityCheckJUnit() {
    }
    
    @BeforeClass
    public static void setUpClass() {
//        dqc = new DataQualityCheck();
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
     * The testFindRecord method tests 
     */
    @Test
    public void testFindRecord() {
         dqc = new DataQualityCheck();
        try {
            assertNotNull(dqc.findRecord()); 
        } catch (UnknownColumnException ex) {
            fail("Records can not be found" + ex);
        }
    }
     
    /**
     * The testExportRecord method tests that the exportRecord method is 
     */
    @Test
    public void testExportRecord() {
         dqc = new DataQualityCheck();
        try {
             try {
                 assertNotNull(dqc.exportRecord());
             } catch (UnknownColumnException ex) {
                 System.out.println(ex.getCause());
             }
        } catch (SQLException ex) {
            fail("Records can not be exported" + ex);
        }
     }
     
    /**
     * The testDtm method test 
     * @throws UnknownColumnException 
     */
      @Test
     public void testBuildDtm() throws UnknownColumnException {//DefaultTableModel
         ResultSet rs;
          dqc = new DataQualityCheck();
        try {
            rs = dqc.findRecord();
            assertNotNull(dqc.buildDtm(rs)); //
        } catch (SQLException ex) {
            fail("table model could not be build" + ex);   
        }
         
     }
}
