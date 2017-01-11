/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model.register;

import lapr.project.model.CDrag;
import lapr.project.model.Project;
import lapr.project.utils.AircraftStAXParser;
import lapr.project.utils.AirportStAXParser;
import lapr.project.utils.NetworkStAXParser;
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
public class CDragRegisterTest {

    Project project;
    CDrag cDrag1, cDrag2;

    public CDragRegisterTest() {
        defaultProject();
        
        cDrag1 = project.getAircraftModelRegister().getAircraftModel("777-200ER").getCdragRegister().getCDrag(0);
        cDrag2 = project.getAircraftModelRegister().getAircraftModel("777-200ER").getCdragRegister().getCDrag(1);
    }

    private void defaultProject() {
        project = new Project("proj0", "proj");

        NetworkStAXParser network = new NetworkStAXParser(project);
        AircraftStAXParser instance = new AircraftStAXParser(project);
        AirportStAXParser airports = new AirportStAXParser(project);

        network.XMLReader("inOutFiles/TestSet02_Network.xml");
        instance.XMLReader("inOutFiles/TestSet02_Aircraft.xml");
        airports.XMLReader("inOutFiles/TestSet02_Airports.xml");
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
     * Test of addCDrag method, of class CDragRegister.
     */
    @Test
    public void testAddCDrag() {
        System.out.println("addCDrag");

        CDrag newCDrag = cDrag1;
        CDragRegister instance = new CDragRegister();

        boolean expResult = true;
        boolean result = instance.addCDrag(newCDrag);

        assertEquals(expResult, result);
    }

    /**
     * Test of addCDrag method, of class CDragRegister.
     */
    @Test
    public void testAddCDrag2() {
        System.out.println("addCDrag2");

        CDrag newCDrag = cDrag1;
        CDragRegister instance = new CDragRegister();
        instance.addCDrag(cDrag1);

        boolean expResult = false;
        boolean result = instance.addCDrag(newCDrag);

        assertEquals(expResult, result);
    }

    /**
     * Test of getCDrag method, of class CDragRegister.
     */
    @Test
    public void testGetCDrag() {
        System.out.println("getCDrag");

        int index = 0;
        CDragRegister instance = new CDragRegister();
        instance.addCDrag(cDrag1);
        instance.addCDrag(cDrag2);

        CDrag expResult = cDrag1;
        CDrag result = instance.getCDrag(index);

        assertEquals(expResult, result);
    }

    /**
     * Test of getListSize method, of class CDragRegister.
     */
    @Test
    public void testGetListSize() {
        System.out.println("getListSize");

        CDragRegister instance = new CDragRegister();
        instance.addCDrag(cDrag1);

        int expResult = 1;
        int result = instance.getListSize();

        assertEquals(expResult, result);
    }

}
