package Contacts;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Datee {

    private int date_id;
    private String date_type = "NULL";
    private java.sql.Date date_entry;
    private int contact_id;

    public Datee(){
    }

    public Datee(String date_type, java.sql.Date date_entry, int date_id){
        this.date_type = date_type;
        this.date_entry = date_entry;
        this.date_id = date_id;
    }

    public int getDate_id() {
        return date_id;
    }

    public void setDate_id(int date_id) {
        this.date_id = date_id;
    }

    public String getDate_type() {
        return date_type;
    }

    public void setDate_type(String date_type) {
        this.date_type = date_type;
    }

    public java.sql.Date getDate_entry() {
        return date_entry;
    }

    public void setDate_entry(java.sql.Date date_entry) {
        this.date_entry = date_entry;
    }

    public int getContact_id() {
        return contact_id;
    }

    public void setContact_id(int contact_id) {
        this.contact_id = contact_id;
    }


    public static java.sql.Date convertToDate() throws ParseException {
        String strDate = "1970-01-01";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
        java.util.Date date = sdf.parse(strDate);
        java.sql.Date sqlDate = new Date(date.getTime());
        return sqlDate;
    }

}




