/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.utils;

import lapr.project.model.Project;
import lapr.project.model.network.AirNetwork;
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
public class NetworkStAXParserTest {
    
    public NetworkStAXParserTest() {
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
     * Test of XMLReader method, of class NetworkStAXParser.
     */
    @Test
    public void testXMLReader() {
        System.out.println("XMLReader");
        String filePath = "inOutFiles/TestSet01a_Network.xml";
        
        Project expProject = new Project(0, "Project0","Description0");
        NetworkStAXParser instance = new NetworkStAXParser(expProject);
       
        AirNetwork result = instance.XMLReader(filePath);
        AirNetwork expResult = expProject.getAirNetwork();
        
        assertEquals(expResult, result);        
    }
    
}
