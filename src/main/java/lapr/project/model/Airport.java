/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

/**
 *
 * @author João
 */
public class Airport {

    private String name;
    private String town;
    private String country;
    private String IATAcode;
    private Location location;

    public Airport(String name, String town, String country, String IATAcode, Location location) {
        this.name = name;
        this.town = town;
        this.country = country;
        this.IATAcode = IATAcode;
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getIATAcode() {
        return IATAcode;
    }

    public void setIATAcode(String IATAcode) {
        this.IATAcode = IATAcode;
    }

    public Location getLocation(){
        return this.location;
    }
    
    public void setLocation(Location l){
        this.location = l;
    }
    
    public boolean equals(Object otherObj) {
            if (this == otherObj) {
                return true;
            }
            if (otherObj == null || this.getClass() != otherObj.getClass()) {
                return false;
            }
            Airport otherAirport = (Airport) otherObj;

            return this.IATAcode.equalsIgnoreCase(otherAirport.IATAcode);
        }

    

    @Override
    public String toString() {
        return "Airport{" + "name=" + name + ", town=" + town + ", country=" + country + ", IATAcode=" + IATAcode + ", latitude=" + location.getLatitude() + ", longitude=" + location.getLongitude() + ", altitude=" + location.getAltitude() + '}';
    }

}
