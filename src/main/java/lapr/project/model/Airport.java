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
    private double latitude;
    private double longitude;
    private double altitude;

    public Airport(String name, String town, String country, String IATAcode, double latitude, double longitude, double altitude) {
        this.name = name;
        this.town = town;
        this.country = country;
        this.IATAcode = IATAcode;
        this.latitude = latitude;
        this.longitude = longitude;
        this.altitude = altitude;
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

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getAltitude() {
        return altitude;
    }

    public void setAltitude(double altitude) {
        this.altitude = altitude;
    }

    @Override
    public String toString() {
        return "Airport{" + "name=" + name + ", town=" + town + ", country=" + country + ", IATAcode=" + IATAcode + ", latitude=" + latitude + ", longitude=" + longitude + ", altitude=" + altitude + '}';
    }

}
