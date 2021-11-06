package Contacts;

public class Session {

    int contact_id;
    int address_id;
    int phone_id;
    int date_id;

    public Session(){
    }

    public Session(int address_id, int phone_id, int date_id, int contact_id){
        this.address_id=address_id;
        this.phone_id=phone_id;
        this.date_id=date_id;
        this.contact_id = contact_id;
    }

    public int getAddress_id() {
        return address_id;
    }

    public void setAddress_id(int address_id) {
        this.address_id = address_id;
    }

    public int getPhone_id() {
        return phone_id;
    }

    public void setPhone_id(int phone_id) {
        this.phone_id = phone_id;
    }

    public int getDate_id() {
        return date_id;
    }

    public void setDate_id(int date_id) {
        this.date_id = date_id;
    }

    public int getContact_id() {
        return contact_id;
    }

    public void setContact_id(int contact_id) {
        this.contact_id = contact_id;
    }
}
