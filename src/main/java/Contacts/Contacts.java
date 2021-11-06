package Contacts;

import java.sql.Date;

public class Contacts {

    private int contact_id;
    private String firstName;
    private String middleName;
    private String lastName;
    private String address_type;
    private String street_address;
    private String city;
    private String state;
    private String zip;
    private String phone_type;
    private int area_code;
    private int phone_number;
    private String date_type;
    private Date date_entry;

    public Contacts(){}

    public Contacts(int contact_id, String firstName, String middleName, String lastName, String address_type, String street_address, String city, String state, String zip, String phone_type, int area_code, int phone_number, String date_type, Date date_entry) {
    this.contact_id=contact_id;
    this.firstName=firstName;
    this.middleName=middleName;
    this.lastName=lastName;
    this.address_type=address_type;
    this.street_address=street_address;
    this.city=city;
    this.state=state;
    this.zip=zip;
    this.phone_type=phone_type;
    this.area_code=area_code;
    this.phone_number=phone_number;
    this.date_type=date_type;
    this.date_entry=date_entry;
    }

    public int getContact_id() {
        return contact_id;
    }

    public void setContact_id(int contact_id) {
        this.contact_id = contact_id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress_type() {
        return address_type;
    }

    public void setAddress_type(String address_type) {
        this.address_type = address_type;
    }

    public String getStreet_address() {
        return street_address;
    }

    public void setStreet_address(String street_address) {
        this.street_address = street_address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getPhone_type() {
        return phone_type;
    }

    public void setPhone_type(String phone_type) {
        this.phone_type = phone_type;
    }

    public int getArea_code() {
        return area_code;
    }

    public void setArea_code(int area_code) {
        this.area_code = area_code;
    }

    public int getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(int phone_number) {
        this.phone_number = phone_number;
    }

    public String getDate_type() {
        return date_type;
    }

    public void setDate_type(String date_type) {
        this.date_type = date_type;
    }

    public Date getDate_entry() {
        return date_entry;
    }

    public void setDate_entry(Date date_entry) {
        this.date_entry = date_entry;
    }
}
