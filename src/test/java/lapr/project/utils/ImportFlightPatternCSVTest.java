/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.utils;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author zero_
 */
public class ImportFlightPatternCSVTest {
    
    public ImportFlightPatternCSVTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of CSVImport method, of class ImportFlightPatternCSV.
     */
    @Test
    public void testCSVImport() {
        System.out.println("CSVImport");
        String filePath = "inOutFiles/Flight_pattern_A380_v1a.csv";
        ImportFlightPatternCSV instance = new ImportFlightPatternCSV();
        instance.CSVImport(filePath);
    }
    
    /**
     * Test of CSVImport method, of class ImportFlightPatternCSV.
     */
    @Test
    public void testCSVImport2() {
        System.out.println("CSVImport2");
        String filePath = "inOutFiles/Flight_pattern_B777-200_v1a.csv";
        ImportFlightPatternCSV instance = new ImportFlightPatternCSV();
        instance.CSVImport(filePath);
    }
    
}
