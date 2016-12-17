/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.utils;

import lapr.project.model.Project;
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
public class AirportStAXParserTest {
    
    public AirportStAXParserTest() {
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
     * Test of XMLReader method, of class AirportStAXParser.
     */
    @Test
    public void testXMLReader() {
        System.out.println("XMLReader");
        String filePath = "inOutFiles/TestSet01a_Airports.xml";
        
        Project expResult = new Project(0, "Project0");
        AirportStAXParser instance = new AirportStAXParser(expResult);
        Project result = instance.XMLReader(filePath);
        
        assertEquals(expResult, result);
    }
    
}
