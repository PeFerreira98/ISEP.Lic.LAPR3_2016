/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model.register;

import java.util.HashMap;
import java.util.LinkedHashMap;
import lapr.project.model.Airport;

/**
 *
 * @author Tiago
 */
public class AirportRegister {

    private HashMap<String, Airport> airportRegister;

    public AirportRegister() {
        this.airportRegister = new LinkedHashMap<>();
    }

    public boolean validateAirport(Airport newAirport) {
        for (Airport airport : this.airportRegister.values()) {
            if (newAirport.equals(airport)) {
                return false;
            }

        }
        return true;
    }

    public Airport getAirportByIATACode(String IATAcode) {
        for (Airport airport : this.airportRegister.values()) {
            if (airport.getIATAcode().equalsIgnoreCase(IATAcode)) {
                return this.airportRegister.get(airport.getIATAcode());

            }

        }
        return null;
    }

    public Airport addAirport(Airport newAirport) {
        if (validateAirport(newAirport)) {
            this.airportRegister.put(newAirport.getIATAcode(), newAirport);
            return this.airportRegister.get(newAirport.getIATAcode());
        }
        return null;
    }

}
