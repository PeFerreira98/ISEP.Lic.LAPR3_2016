/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.utils;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Iterator;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.*;
import lapr.project.model.*;
import lapr.project.model.register.AircraftModelRegister;
import lapr.project.model.register.RegimeRegister;

/**
 *
 * @author Pedro Ferreira
 */
public class AircraftStAXParser {
    
    private AircraftModelRegister c_aircraftModelRegister;
    private AircraftModel c_aircraftModel;
    private RegimeRegister c_regimeRegister;
    private Regime c_regime;
    
    //Aircraft boolean
    private boolean b_maker;
    private boolean b_type;
    private boolean b_EWeight;
    private boolean b_MTOW;
    private boolean b_MZFW;
    private boolean b_maxPayload;
    private boolean b_fuelCapacity;
    private boolean b_VMO;
    private boolean b_MMO;
    private boolean b_wingArea;
    private boolean b_wingSpan;
    private boolean b_cDrag;
    private boolean b_e;
    
    //motorization boolean
    private boolean b_numberMotors;
    private boolean b_motor;
    private boolean b_motorType;
    
    //regime boolean
    private boolean b_TSFC;
    private boolean b_speed;
    private boolean b_thrust;
    private boolean b_altitude;
    
    //aircraft
    private String s_airId;
    private String s_airDescription;
    private String s_maker;
    private String s_type;
    private double d_EWeight;
    private double d_MTOW;
    private double d_MZFW;
    private double d_maxPayload;
    private double d_fuelCapacity;
    private double d_VMO;
    private double d_MMO;
    private double d_wingArea;
    private double d_wingSpan;
    private double d_cDrag;
    private double d_e;
    
    //motorization
    private double d_numberMotors;
    private String s_motor;
    private String s_motorType;
    
    //regime
    private String s_regId;
    private double d_TSFC;
    private double d_speed;
    private double d_thrust;
    private double d_altitude;

    
    public AircraftStAXParser(Project project) {
        this.c_aircraftModelRegister = project.getAircraftModelRegister();
    }

    public AircraftStAXParser() {
        this.c_aircraftModelRegister = new AircraftModelRegister();
    }

    public AircraftModelRegister XMLReader(String filePath) {

        try {
            XMLInputFactory factory = XMLInputFactory.newInstance();
            XMLEventReader eventReader = factory.createXMLEventReader(new FileReader(filePath));

            while (eventReader.hasNext()) {
                XMLEvent event = eventReader.nextEvent();
                switch (event.getEventType()) {

                    case XMLStreamConstants.START_ELEMENT:
                        StartElement startElement = event.asStartElement();
                        String qName = startElement.getName().getLocalPart();

                        if (qName.equalsIgnoreCase("aircraft_list")) {
                            System.out.println("Start Element : aircraft_list");
                        }

                        if (qName.equalsIgnoreCase("aircraft")) {
                            System.out.println("Start Element : aircraft");
                            Iterator<Attribute> attributes = startElement.getAttributes();
                            s_airDescription = attributes.next().getValue();
                            s_airId = attributes.next().getValue();
                        }

                        if (qName.equalsIgnoreCase("maker")) {
                            b_maker = true;
                        }
                        
                        if (qName.equalsIgnoreCase("type")) {
                            b_type = true;
                        }
                        
                        if (qName.equalsIgnoreCase("motorization")) {
                            System.out.println("Start Element : motorization");
                        }
                        
                        if (qName.equalsIgnoreCase("number_motors")) {
                            b_numberMotors = true;
                        }
                        
                        if (qName.equalsIgnoreCase("motor")) {
                            b_motor = true;
                        }
                        
                        if (qName.equalsIgnoreCase("motor_type")) {
                            b_motorType = true;
                        }
                        
                        if (qName.equalsIgnoreCase("regime_list")) {
                            this.c_regimeRegister = new RegimeRegister();
                            System.out.println("Start Element : regime_list");
                        }

                        if (qName.equalsIgnoreCase("regime")) {
                            System.out.println("Start Element : regime");
                            Iterator<Attribute> attributes = startElement.getAttributes();
                            s_regId = attributes.next().getValue();
                        }
                        
                        if (qName.equalsIgnoreCase("TSFC")) {
                            b_TSFC = true;
                        }
                        
                        if (qName.equalsIgnoreCase("speed")) {
                            b_speed = true;
                        }
                        
                        if (qName.equalsIgnoreCase("thrust")) {
                            b_thrust = true;
                        }
                        
                        if (qName.equalsIgnoreCase("altitude")) {
                            b_altitude = true;
                        }
                        
                        if (qName.equalsIgnoreCase("EWeight")) {
                            b_EWeight = true;
                        }
                        
                        if (qName.equalsIgnoreCase("MTOW")) {
                            b_MTOW = true;
                        }
                        
                        if (qName.equalsIgnoreCase("MZFW")) {
                            b_MZFW = true;
                        }
                        
                        if (qName.equalsIgnoreCase("max_payload")) {
                            b_maxPayload = true;
                        }
                        
                        if (qName.equalsIgnoreCase("fuel_capacity")) {
                            b_fuelCapacity = true;
                        }
                        
                        if (qName.equalsIgnoreCase("VMO")) {
                            b_VMO = true;
                        }
                        
                        if (qName.equalsIgnoreCase("MMO")) {
                            b_MMO = true;
                        }
                        
                        if (qName.equalsIgnoreCase("wing_area")) {
                            b_wingArea = true;
                        }
                        
                        if (qName.equalsIgnoreCase("wing_span")) {
                            b_wingSpan = true;
                        }
                        
                        if (qName.equalsIgnoreCase("Cdrag_0")) {
                            b_cDrag = true;
                        }
                        
                        if (qName.equalsIgnoreCase("e")) {
                            b_e = true;
                        }
                        
                        break;

                    case XMLStreamConstants.CHARACTERS:
                        Characters characters = event.asCharacters();

                        if (b_maker) {
                            System.out.println("\tmaker: " + characters.getData());
                            s_maker = characters.getData();
                            b_maker = false;
                        }
                        
                        if (b_type) {
                            System.out.println("\ttype: " + characters.getData());
                            s_type = characters.getData();
                            b_type = false;
                        }
                        
                        if (b_numberMotors) {
                            System.out.println("\tnumber_motors: " + characters.getData());
                            d_numberMotors = Double.parseDouble(characters.getData());
                            b_numberMotors = false;
                        }

                        if (b_motor) {
                            System.out.println("\tmotor: " + characters.getData());
                            s_motor = characters.getData();
                            b_motor = false;
                        }
                        
                        if (b_motorType) {
                            System.out.println("\tmotor_type: " + characters.getData());
                            s_motorType = characters.getData();
                            b_motorType = false;
                        }
                        
                        if (b_TSFC) {
                            System.out.println("\tTSFC: " + characters.getData());
                            d_TSFC = Double.parseDouble(characters.getData().replace(" US", ""));
                            b_TSFC = false;
                        }
                        
                        if (b_speed) {
                            System.out.println("\tspeed: " + characters.getData());
                            d_speed = Double.parseDouble(characters.getData().replace(" M", ""));
                            b_speed = false;
                        }
                        
                        if (b_thrust) {
                            System.out.println("\tthrust: " + characters.getData());
                            d_thrust = Double.parseDouble(characters.getData().replace(" US", ""));
                            b_thrust = false;
                        }
                        
                        if (b_altitude) {
                            System.out.println("\taltitude: " + characters.getData());
                            d_altitude = Double.parseDouble(characters.getData().replace(" US", ""));
                            b_altitude = false;
                        }
                        
                        if (b_EWeight) {
                            System.out.println("\tEWeight: " + characters.getData());
                            d_EWeight = Double.parseDouble(characters.getData().replace(" US", ""));
                            b_EWeight = false;
                        }
                        
                        if (b_MTOW) {
                            System.out.println("\tMTOW: " + characters.getData());
                            d_MTOW = Double.parseDouble(characters.getData().replace(" US", ""));
                            b_MTOW = false;
                        }
                        
                        if (b_MZFW) {
                            System.out.println("\tMZFW: " + characters.getData());
                            d_MZFW = Double.parseDouble(characters.getData().replace(" US", ""));
                            b_MZFW = false;
                        }
                        
                        if (b_maxPayload) {
                            System.out.println("\tmax_payload: " + characters.getData());
                            d_maxPayload = Double.parseDouble(characters.getData().replace(" US", ""));
                            b_maxPayload = false;
                        }
                        
                        if (b_fuelCapacity) {
                            System.out.println("\tfuel_capacity: " + characters.getData());
                            d_fuelCapacity = Double.parseDouble(characters.getData().replace(" US", ""));
                            b_fuelCapacity = false;
                        }
                        
                        if (b_VMO) {
                            System.out.println("\tVMO: " + characters.getData());
                            d_VMO = Double.parseDouble(characters.getData().replace(" Knot", ""));
                            b_VMO = false;
                        }
                        
                        if (b_MMO) {
                            System.out.println("\tMMO: " + characters.getData());
                            d_MMO = Double.parseDouble(characters.getData().replace(" M", ""));
                            b_MMO = false;
                        }
                        
                        if (b_wingArea) {
                            System.out.println("\twing_area: " + characters.getData());
                            d_wingArea = Double.parseDouble(characters.getData().replace(" SI", ""));
                            b_wingArea = false;
                        }
                        
                        if (b_wingSpan) {
                            System.out.println("\twing_span: " + characters.getData());
                            d_wingSpan = Double.parseDouble(characters.getData().replace(" SI", ""));
                            b_wingSpan = false;
                        }
                        
                        if (b_cDrag) {
                            System.out.println("\tCdrag_0: " + characters.getData());
                            d_cDrag = Double.parseDouble(characters.getData());
                            b_cDrag = false;
                        }
                        
                        if (b_e) {
                            System.out.println("\te: " + characters.getData());
                            d_e = Double.parseDouble(characters.getData());
                            b_e = false;
                        }

                        break;

                    case XMLStreamConstants.END_ELEMENT:
                        EndElement endElement = event.asEndElement();
                        String endName = endElement.getName().getLocalPart();
                                                
                        if (endName.equalsIgnoreCase("regime")) {
                            this.c_regime = new Regime(s_regId, d_TSFC, d_speed, d_thrust, d_altitude);
                            
                            this.c_regimeRegister.addRegime(c_regime);
                            System.out.println("End Element : regime" + "\n");
                        }
                        
                        if (endName.equalsIgnoreCase("regime_list")) {
                            System.out.println("End Element : regime_list" + "\n");
                        }
                        
                        if (endName.equalsIgnoreCase("motorization")) {
                            System.out.println("End Element : motorization" + "\n");
                        }
                        
                        if (endName.equalsIgnoreCase("aircraft")) {
                            this.c_aircraftModel = new AircraftModel(s_airId, s_airDescription, s_maker, AircraftModel.Type.valueOf(s_type.toUpperCase()), d_numberMotors, s_motor, AircraftModel.MotorType.valueOf(s_motorType.toUpperCase()), c_regimeRegister, d_EWeight, d_MTOW, d_MZFW, d_maxPayload, d_fuelCapacity, d_VMO, d_MMO, d_wingArea, d_wingSpan, d_EWeight, d_e);
                            
                            this.c_aircraftModelRegister.addAircraftModel(c_aircraftModel);
                            System.out.println("End Element : aircraft" + "\n");
                        }

                        if (endName.equalsIgnoreCase("aircraft_list")) {
                            System.out.println("End Element : aircraft_list" + "\n");
                        }

                        break;
                }
            }
        } catch (FileNotFoundException | XMLStreamException e) {
        }
        return this.c_aircraftModelRegister;
    }

}
