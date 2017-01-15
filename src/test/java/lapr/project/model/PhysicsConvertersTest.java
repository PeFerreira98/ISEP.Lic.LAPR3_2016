/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;

/**
 *
 * @author João
 */
public class PhysicsConvertersTest {
    
    public PhysicsConvertersTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    /**
     * Test of converterDistanceKmToMiles method, of class PhysicsConverters.
     */
    @Test
    public void testConverterDistanceKmToMiles() {
        System.out.println("converterDistanceKmToMiles");
        double distance = 10;
        double expResult = 0.62*10;
        double result = PhysicsConverters.converterDistanceKmToMiles(distance);
        assertEquals(expResult, result, 0.01*expResult);
    }

    /**
     * Test of converterMachToKmsHour method, of class PhysicsConverters.
     */
    @Test
    public void testConverterMachToKmsHour() {
        System.out.println("converterMachToKmsHour");
        double a = 1000;
        double expResult =1225.04*a; 
        double result = PhysicsConverters.converterMachToKmsHour(a);
        assertEquals(expResult, result, 0.01*expResult);
    }

    /**
     * Test of altitudeConverterFeetToMeters method, of class PhysicsConverters.
     */
    @Test
    public void testAltitudeConverterFeetToMeters() {
        System.out.println("altitudeConverterFeetToMeters");
        double feet = 1000;
        double expResult = feet*0.3048;
        double result = PhysicsConverters.altitudeConverterFeetToMeters(feet);
        assertEquals(expResult, result, 0.01*expResult);
    }

    /**
     * Test of converterPoundsToKg method, of class PhysicsConverters.
     */
    @Test
    public void testConverterPoundsToKg() {
        System.out.println("converterPoundsToKg");
        double anyAircraftWeightValue = 1000;
        double expResult = 0.453592370*1000;
        double result = PhysicsConverters.converterPoundsToKg(anyAircraftWeightValue);
        assertEquals(expResult, result, 0.01*expResult);
    }

    /**
     * Test of converterKnotToKms method, of class PhysicsConverters.
     */
    @Test
    public void testConverterKnotToKms() {
        System.out.println("converterKnotToKms");
        double speed = 1000;
        double expResult = 1.852*speed;
        double result = PhysicsConverters.converterKnotToKms(speed);
        assertEquals(expResult, result, 0.01*expResult);
    }

    /**
     * Test of converterGallonsToLiter method, of class PhysicsConverters.
     */
    @Test
    public void testConverterGallonsToLiter() {
        System.out.println("converterGallonsToLiter");
        double gallons=1000;
        double expResult = gallons*0.82/0.2642;
        double result = PhysicsConverters.converterGallonsToLiter(gallons);
        assertEquals(expResult, result, expResult*0.01);
    }

    /**
     * Test of converterKelvinToCelsius method, of class PhysicsConverters.
     */
    @Test
    public void testConverterKelvinToCelsius() {
        System.out.println("converterKelvinToCelsius");
        double temperature = 1000;
        double expResult = temperature-273.15;
        double result = PhysicsConverters.converterKelvinToCelsius(temperature);
        assertEquals(expResult, result, temperature*0.01);
    }

    /**
     * Test of litersToKgConverter method, of class PhysicsConverters.
     */
    @Test
    public void testLitersToKgConverter() {
        System.out.println("litersToKgConverter");
        double liters = 1000;
        double expResult = liters*0.804;
        double result = PhysicsConverters.litersToKgConverter(liters);
        assertEquals(expResult, result, 0.01*expResult);
    }

    /**
     * Test of metersToKm method, of class PhysicsConverters.
     */
    @Test
    public void testMetersToKm() {
        System.out.println("metersToKm");
        double meters = 1000;
        double expResult = 1000*1000;
        double result = PhysicsConverters.metersToKm(meters);
        assertEquals(expResult, result, 1000000);
        
    }

    /**
     * Test of secondsToHours method, of class PhysicsConverters.
     */
    @Test
    public void testSecondsToHours() {
        System.out.println("secondsToHours");
        double seconds = 30000;
        double expResult = 30000 *  0.000277777778;
        double result = PhysicsConverters.secondsToHours(seconds);
        assertEquals(expResult, result, 8.33333334);
        
    }
    
}
