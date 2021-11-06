package Contacts;

import java.sql.Date;

public class Contact {

    private int contact_id;
    private String firstName;
    private String middleName;
    private String lastName;

    public Contact(){

    }

    public Contact(int contact_id, String firstName, String middleName, String lastName) {
        this.contact_id = contact_id;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;

    }

    public int getContact_id() { return contact_id; }

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

}
