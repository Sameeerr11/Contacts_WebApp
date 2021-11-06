package Contacts;

public class Phone {

    private int phone_id;
    private String phone_type = " ";
    private int area_code;
    private int phone_number;
    private int contact_id;

    public Phone(){
    }

    public Phone(String phone_type, int area_code, int phone_number, int phone_id){
        this.phone_type = phone_type;
        this.area_code = area_code;
        this.phone_number = phone_number;
        this.phone_id = phone_id;
    }

    public int getPhone_id() {
        return phone_id;
    }

    public void setPhone_id(int phone_id) {
        this.phone_id = phone_id;
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

    public int getContact_id() {
        return contact_id;
    }

    public void setContact_id(int contact_id) {
        this.contact_id = contact_id;
    }
}
