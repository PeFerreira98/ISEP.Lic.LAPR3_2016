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
public class AircraftStAXParser2 {

    private AircraftModelRegister c_aircraftModelRegister;
    private AircraftModel c_aircraftModel;
//    private CDragRegister c_cDragRegister;
//    private CDrag c_cDrag;

    //aircraft boolean
    private boolean b_maker;
    private boolean b_type;

    //motorization boolean
    private boolean b_numberMotors;
    private boolean b_motor;
    private boolean b_motorType;
    private boolean b_cruiseAltitude;
    private boolean b_cruiseSpeed;
    private boolean b_TSFC;
    private boolean b_lapseRateFactor;

    //thrust_function boolean
    private boolean b_thrust0;
    private boolean b_thrustMaxSpeed;
    private boolean b_maxSpeed;

    //aircraft cont. boolean
    private boolean b_EWeight;
    private boolean b_MTOW;
    private boolean b_maxPayload;
    private boolean b_fuelCapacity;
    private boolean b_VMO;
    private boolean b_MMO;
    private boolean b_wingArea;
    private boolean b_wingSpan;
    private boolean b_aspectRatio;
    private boolean b_e;

    //cdrag boolean
    private boolean b_speed;
    private boolean b_Cdrag0;

    //aircraft
    private String s_airId;
    private String s_airDescription;
    private String s_maker;
    private String s_type;

    //motorization
    private double d_numberMotors;
    private String s_motor;
    private String s_motorType;
    private double d_cruiseAltitude;
    private double d_cruiseSpeed;
    private double d_TSFC;
    private double d_lapseRateFactor;

    //thrust
    private double d_thrust0;
    private double d_thrustMaxSpeed;
    private double d_maxSpeed;

    //aircraft cont.
    private double d_EWeight;
    private double d_MTOW;
    private double d_MZFW;
    private double d_maxPayload;
    private double d_fuelCapacity;
    private double d_VMO;
    private double d_MMO;
    private double d_wingArea;
    private double d_wingSpan;
    private double d_aspectRatio;
    private double d_e;

    //cdrag
    private double d_speed;
    private double d_Cdrag0;

    public AircraftStAXParser2(Project project) {
        this.c_aircraftModelRegister = project.getAircraftModelRegister();
    }

    public AircraftStAXParser2() {
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

                        if (qName.equalsIgnoreCase("cruise_altitude")) {
                            b_cruiseAltitude = true;
                        }

                        if (qName.equalsIgnoreCase("cruise_speed")) {
                            b_cruiseSpeed = true;
                        }

                        if (qName.equalsIgnoreCase("TSFC")) {
                            b_TSFC = true;
                        }

                        if (qName.equalsIgnoreCase("lapse_rate_factor")) {
                            b_lapseRateFactor = true;
                        }

                        if (qName.equalsIgnoreCase("thrust_function")) {
                            System.out.println("Start Element : thrust_function");
                        }

                        if (qName.equalsIgnoreCase("thrust_0")) {
                            b_thrust0 = true;
                        }

                        if (qName.equalsIgnoreCase("thrust_max_speed")) {
                            b_thrustMaxSpeed = true;
                        }

                        if (qName.equalsIgnoreCase("max_speed")) {
                            b_maxSpeed = true;
                        }

                        if (qName.equalsIgnoreCase("EWeight")) {
                            b_EWeight = true;
                        }

                        if (qName.equalsIgnoreCase("MTOW")) {
                            b_MTOW = true;
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

                        if (qName.equalsIgnoreCase("aspect_ratio")) {
                            b_aspectRatio = true;
                        }

                        if (qName.equalsIgnoreCase("e")) {
                            b_e = true;
                        }

                        if (qName.equalsIgnoreCase("Cdrag_function")) {
//                            this.c_cDragRegister = new CDragRegister();
                            System.out.println("Start Element : Cdrag_function");
                        }

                        if (qName.equalsIgnoreCase("iten")) {
                            System.out.println("Start Element : iten / cdrag");
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

                        if (b_cruiseAltitude) {
                            System.out.println("\tcruise_altitude: " + characters.getData());
                            d_cruiseAltitude = Double.parseDouble(characters.getData().replace(" US", ""));
                            b_cruiseAltitude = false;
                        }

                        if (b_cruiseSpeed) {
                            System.out.println("\tcruise_speed: " + characters.getData());
                            d_cruiseSpeed = Double.parseDouble(characters.getData().replace(" M", ""));
                            b_cruiseSpeed = false;
                        }

                        if (b_TSFC) {
                            System.out.println("\tTSFC: " + characters.getData());
//                            d_TSFC = Double.parseDouble(characters.getData().replace(" SI", ""));
                            b_TSFC = false;
                        }

                        if (b_lapseRateFactor) {
                            System.out.println("\tlapse_rate_factor: " + characters.getData());
                            d_lapseRateFactor = Double.parseDouble(characters.getData());
                            b_lapseRateFactor = false;
                        }

                        if (b_thrust0) {
                            System.out.println("\tthrust_0: " + characters.getData());
                            d_thrust0 = Double.parseDouble(characters.getData().replace(" SI", ""));
                            b_thrust0 = false;
                        }

                        if (b_thrustMaxSpeed) {
                            System.out.println("\tthrust_max_speed: " + characters.getData());
                            d_thrustMaxSpeed = Double.parseDouble(characters.getData().replace(" SI", ""));
                            b_thrustMaxSpeed = false;
                        }

                        if (b_maxSpeed) {
                            System.out.println("\tmax_speed: " + characters.getData());
                            d_maxSpeed = Double.parseDouble(characters.getData().replace(" M", ""));
                            b_maxSpeed = false;
                        }

                        if (b_EWeight) {
                            System.out.println("\tEWeight: " + characters.getData());
                            
                            if (characters.getData().endsWith("US")) {
                                d_EWeight = Double.parseDouble(characters.getData().replace(" US", ""));
                            } 
                            else{
                                d_EWeight = Double.parseDouble(characters.getData().replace(" SI", ""));
                            }
                            
                            b_EWeight = false;
                        }

                        if (b_MTOW) {
                            System.out.println("\tMTOW: " + characters.getData());
                            
                            if (characters.getData().endsWith("US")) {
                                d_MTOW = Double.parseDouble(characters.getData().replace(" US", ""));
                            } 
                            else{
                                d_MTOW = Double.parseDouble(characters.getData().replace(" SI", ""));
                            }
                            
                            b_MTOW = false;
                        }

                        if (b_maxPayload) {
                            System.out.println("\tmax_payload: " + characters.getData());
                            
                            if (characters.getData().endsWith("US")) {
                                d_maxPayload = Double.parseDouble(characters.getData().replace(" US", ""));
                            } 
                            else{
                                d_maxPayload = Double.parseDouble(characters.getData().replace(" SI", ""));
                            }
                            
                            b_maxPayload = false;
                        }

                        if (b_fuelCapacity) {
                            System.out.println("\tfuel_capacity: " + characters.getData());
                            
                            if (characters.getData().endsWith("US")) {
                                d_fuelCapacity = Double.parseDouble(characters.getData().replace(" US", ""));
                            } 
                            else{
                                d_fuelCapacity = Double.parseDouble(characters.getData().replace(" SI", ""));
                            }
                            
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

                        if (b_aspectRatio) {
                            System.out.println("\taspect_ratio: " + characters.getData());
                            d_aspectRatio = Double.parseDouble(characters.getData());
                            b_aspectRatio = false;
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

                        if (endName.equalsIgnoreCase("thrust_function")) {
                            System.out.println("End Element : thrust_function" + "\n");
                        }

                        if (endName.equalsIgnoreCase("motorization")) {
                            System.out.println("End Element : motorization" + "\n");
                        }

                        if (endName.equalsIgnoreCase("iten")) {
//                            this.c_cDrag = new CDrag(d_speed, d_Cdrag0);
//                            
//                            this.c_cDragRegister.addCDrag(c_cDrag);
                            System.out.println("End Element : iten / cDrag" + "\n");
                        }

                        if (endName.equalsIgnoreCase("Cdrag_function")) {
                            System.out.println("End Element : Cdrag_function" + "\n");
                        }

                        if (endName.equalsIgnoreCase("aircraft")) {
//                            this.c_aircraftModel = new AircraftModel(s_airId, s_airDescription, s_maker, AircraftModel.Type.valueOf(s_type.toUpperCase()), d_numberMotors, s_motor, AircraftModel.MotorType.valueOf(s_motorType.toUpperCase()), c_regimeRegister, d_EWeight, d_MTOW, d_MZFW, d_maxPayload, d_fuelCapacity, d_VMO, d_MMO, d_wingArea, d_wingSpan, d_EWeight, d_e);

//                            this.c_aircraftModelRegister.addAircraftModel(c_aircraftModel);
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
