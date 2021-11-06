
import Contacts.*;

import java.sql.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class ContactsDAO {

    private String jdbcURL;
    private String jdbcUsername;
    private String jdbcPassword;
    private Connection jdbcConnection;

    public ContactsDAO(){ }

    public ContactsDAO(String jdbcURL, String jdbcUsername, String jdbcPassword) {
        this.jdbcURL = jdbcURL;
        this.jdbcUsername = jdbcUsername;
        this.jdbcPassword = jdbcPassword;
    }

    protected void connect() throws SQLException {
        if (jdbcConnection == null || jdbcConnection.isClosed()) {
            try {
                Class.forName("com.mysql.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                throw new SQLException(e);
            }
            
            jdbcConnection = DriverManager.getConnection(
                    jdbcURL, jdbcUsername, jdbcPassword);
        }
    }

    protected void disconnect() throws SQLException {
        if (jdbcConnection != null && !jdbcConnection.isClosed()) {
            jdbcConnection.close();
        }
    }

    public boolean insertContact(Contact contact) throws SQLException {

        String sql = "INSERT INTO contact (contact_id, fname, mname, lname) VALUES (?,?,?,?)";

        connect();
        Statement stmt = jdbcConnection.createStatement();
        stmt.execute("USE CONTACTS;");

        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setInt(1, contact.getContact_id());
        statement.setString(2, contact.getFirstName());
        statement.setString(3, contact.getMiddleName());
        statement.setString(4, contact.getLastName());

        boolean rowInserted = statement.executeUpdate() > 0;
        statement.close();
        disconnect();
        return rowInserted;
    }

    public boolean updateContact(Contact contact) throws SQLException {

        String sql = "INSERT INTO contact (contact_id, fname, mname, lname) VALUES (?,?,?,?)" +
                " ON DUPLICATE KEY UPDATE fname = ?, mname = ?, lname = ?";

        connect();
        Statement stmt = jdbcConnection.createStatement();
        stmt.execute("USE CONTACTS;");

        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setInt(1, contact.getContact_id());
        statement.setString(2, contact.getFirstName());
        statement.setString(3, contact.getMiddleName());
        statement.setString(4, contact.getLastName());
        statement.setString(5, contact.getFirstName());
        statement.setString(6, contact.getMiddleName());
        statement.setString(7, contact.getLastName());

        boolean rowInserted = statement.executeUpdate() > 0;
        statement.close();
        disconnect();
        return rowInserted;
    }

    public boolean insertAddress(Address address, Contact contact) throws SQLException {
        String sql = "INSERT INTO ADDRESS (address_type, street_address, city, state, zip, contact_id) " +
                " VALUES (?,?,?,?,?,?)";

        System.out.println("DAO INSERT ADDR_ID: "+address.getAddress_id());

        connect();
        Statement stmt = jdbcConnection.createStatement();
        stmt.execute("USE CONTACTS;");

        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setString(1, address.getAddress_type());
        statement.setString(2, address.getStreet_address() );
        statement.setString(3, address.getCity());
        statement.setString(4, address.getState());
        statement.setString(5, address.getZip());
        statement.setInt(6, contact.getContact_id());

//        statement.setInt(7, address.getAddress_id());
//        statement.setString(8, address.getAddress_type());
//        statement.setString(9, address.getStreet_address() );
//        statement.setString(10, address.getCity());
//        statement.setString(11, address.getState());
//        statement.setString(12, address.getZip());

        boolean rowInserted = statement.executeUpdate() > 0;
        statement.close();
        disconnect();
        return rowInserted;
    }

    public boolean updateAddress(Address address, Contact contact) throws SQLException {

        String sql = "INSERT INTO ADDRESS (address_type, street_address, city, state, zip, contact_id, address_id) " +
                " VALUES (?,?,?,?,?,?,?) ON DUPLICATE KEY UPDATE address_type = ?, street_address = ?, city = ?, state = ?, zip = ?";

        connect();
        Statement stmt = jdbcConnection.createStatement();
        stmt.execute("USE CONTACTS;");

        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setString(1, address.getAddress_type());
        statement.setString(2, address.getStreet_address() );
        statement.setString(3, address.getCity());
        statement.setString(4, address.getState());
        statement.setString(5, address.getZip());
        statement.setInt(6, contact.getContact_id());

        statement.setInt(7, address.getAddress_id());
        statement.setString(8, address.getAddress_type());
        statement.setString(9, address.getStreet_address() );
        statement.setString(10, address.getCity());
        statement.setString(11, address.getState());
        statement.setString(12, address.getZip());

        boolean rowInserted = statement.executeUpdate() > 0;
        statement.close();
        disconnect();
        return rowInserted;
    }

    public boolean deleteContact(Contact contact) throws SQLException {
        String sql = "DELETE FROM contact where contact_ID = ?";

        connect();

        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setInt(1, contact.getContact_id());

        boolean rowDeleted = statement.executeUpdate() > 0;
        statement.close();
        disconnect();
        return rowDeleted;
    }

    public Contact searchContact(Contact contact) throws SQLException {
        String sql = "SELECT contact_id, fname, mname, lname FROM contact WHERE contact_id = ? OR LOWER(fname) = ?  OR LOWER(lname) = ?;";

        connect();

        Statement stmt = jdbcConnection.createStatement();
        stmt.execute("USE CONTACTS;");
        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setInt(1, contact.getContact_id());
        statement.setString(2, contact.getFirstName());
        statement.setString(3, contact.getFirstName());

        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()) {
            int cID = resultSet.getInt("contact_id");
            String firstName = resultSet.getString("fname");
            String middleName = resultSet.getString("mname");
            String lastName = resultSet.getString("lname");

            contact = new Contact(cID, firstName, middleName,lastName);
//            ContactList.add(contact);
        }
        resultSet.close();
        statement.close();

        return contact;
    }

    public List<Address> searchAddress(Contact contact) throws SQLException {
        Address address = new Address();
        System.out.println("Search Address DAO");
        List<Address> AddressList =  new ArrayList<>();
        String sql = "SELECT DISTINCT street_address, address_type, city, address_id state, zip, address_id FROM address WHERE contact_id = ? ;";

        connect();

        Statement stmt = jdbcConnection.createStatement();
        stmt.execute("USE CONTACTS;");
        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setInt(1, contact.getContact_id());
        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()) {
            String address_type = resultSet.getString("address_type");
            String street_address = resultSet.getString("street_address");
            String city = resultSet.getString("city");
            String state = resultSet.getString("state");
            String zip = resultSet.getString("zip");
            int address_id = resultSet.getInt("address_id");
            System.out.println("Address_ID: "+address_id);
            address = new Address(address_type, street_address, city, state, zip, address_id);
            AddressList.add(address);
        }
        System.out.println("Search Address DAO END");
        resultSet.close();
        statement.close();

        return AddressList;
    }

    public List<Phone> searchPhone(Contact contact) throws SQLException {

        Phone phone = new Phone();
        List<Phone> PhoneList = new ArrayList<>();

        String sql = "SELECT DISTINCT phone_type, area_code, phone_number, phone_id FROM Phone WHERE contact_id = ? ;";

        connect();

        Statement stmt = jdbcConnection.createStatement();
        stmt.execute("USE CONTACTS;");
        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setInt(1, contact.getContact_id());
        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()) {
            String phone_type = resultSet.getString("phone_type");
            int area_code = resultSet.getInt("area_code");
            int phone_number = resultSet.getInt("phone_number");
            int phone_id = resultSet.getInt("phone_id");

            phone = new Phone(phone_type, area_code, phone_number, phone_id);
            PhoneList.add(phone);
        }

        resultSet.close();
        statement.close();

        return PhoneList;
    }

    public Datee searchDate(Contact contact) throws SQLException {
        Datee date = new Datee();

        String sql = "SELECT date_type, date_entry, date_id FROM Date WHERE contact_id = ? ;";

        connect();

//        List<Datee> dateList = new ArrayList<>();
        Statement stmt = jdbcConnection.createStatement();
        stmt.execute("USE CONTACTS;");
        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setInt(1, contact.getContact_id());
//        statement.setString(2, contact.getFirstName());

        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()) {
            String date_type = resultSet.getString("date_type");
            Date date_entry = resultSet.getDate("date_entry");
            int date_id = resultSet.getInt("date_id");

            date = new Datee(date_type, date_entry, date_id);
//            dateList.add(date);
        }

        resultSet.close();
        statement.close();

        return date;
    }

    public boolean insertPhone(Phone phone, Contact contact) throws SQLException {

        String sql = "INSERT INTO Phone (phone_type, area_code, phone_number, contact_id) VALUES (?,?,?,?) ; ";
        connect();
        Statement stmt = jdbcConnection.createStatement();
        stmt.execute("USE CONTACTS;");

        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setString(1, phone.getPhone_type());
        statement.setInt(2, phone.getArea_code());
        statement.setInt(3, phone.getPhone_number());
        statement.setInt(4, contact.getContact_id());
//        statement.setInt(5, phone.getPhone_id());
//        statement.setString(6, phone.getPhone_type());
//        statement.setInt(7, phone.getArea_code());
//        statement.setInt(8, phone.getPhone_number());

        boolean rowInserted = statement.executeUpdate() > 0;
        statement.close();
        disconnect();
        return rowInserted;

    }

    public boolean updatePhone(Phone phone, Contact contact) throws SQLException {

        String sql = "INSERT INTO Phone (phone_type, area_code, phone_number, contact_id, phone_id) VALUES (?,?,?,?,?) " +
                " ON DUPLICATE KEY UPDATE phone_type = ?, area_code = ?, phone_number = ?";
        connect();
        Statement stmt = jdbcConnection.createStatement();
        stmt.execute("USE CONTACTS;");

        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setString(1, phone.getPhone_type());
        statement.setInt(2, phone.getArea_code());
        statement.setInt(3, phone.getPhone_number());
        statement.setInt(4, contact.getContact_id());
        statement.setInt(5, phone.getPhone_id());
        statement.setString(6, phone.getPhone_type());
        statement.setInt(7, phone.getArea_code());
        statement.setInt(8, phone.getPhone_number());

        boolean rowInserted = statement.executeUpdate() > 0;
        statement.close();
        disconnect();
        return rowInserted;

    }

    public boolean insertDate(Datee date, Contact contact) throws SQLException {

        String sql = "INSERT INTO DATE (date_type, date_entry, contact_id) VALUES (?,?,?)";
        connect();
        Statement stmt = jdbcConnection.createStatement();
        stmt.execute("USE CONTACTS;");

        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setString(1, date.getDate_type());
        statement.setDate(2, date.getDate_entry());
        statement.setInt(3, contact.getContact_id());

        boolean rowInserted = statement.executeUpdate() > 0;
        statement.close();
        disconnect();
        return rowInserted;

    }

    public boolean updateDate(Datee date, Contact contact) throws SQLException {

        String sql = "INSERT INTO DATE (date_type, date_entry, contact_id, date_id) VALUES (?,?,?,?)" +
                " ON DUPLICATE KEY UPDATE date_type = ?, date_entry = ?";
        connect();
        Statement stmt = jdbcConnection.createStatement();
        stmt.execute("USE CONTACTS;");

        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setString(1, date.getDate_type());
        statement.setDate(2, date.getDate_entry());
        statement.setInt(3, contact.getContact_id());
        statement.setInt(4, date.getDate_id());
        statement.setString(5, date.getDate_type());
        statement.setDate(6, date.getDate_entry());

        boolean rowInserted = statement.executeUpdate() > 0;
        statement.close();
        disconnect();
        return rowInserted;

    }

    public List<Contacts> searchAllContact() throws SQLException {
        String sql = "SELECT DISTINCT A.contact_id, fname, mname, lname, address_type, street_address, city, state, zip, \n" +
                " phone_type, area_code, phone_number, date_type, date_entry\n" +
                " FROM CONTACT A\n" +
                " LEFT JOIN ADDRESS B ON A.contact_id = B.contact_id\n" +
                " LEFT JOIN PHONE C ON A.contact_id = C.contact_id\n" +
                " LEFT JOIN DATE D ON A.contact_id = D.contact_id\n" +
                " ORDER BY contact_id";

        connect();

        Contacts contacts = new Contacts();
        List<Contacts> ContactList = new ArrayList<>();
        Statement stmt = jdbcConnection.createStatement();
        stmt.execute("USE CONTACTS;");
        PreparedStatement statement = jdbcConnection.prepareStatement(sql);

        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()) {
            int cID = resultSet.getInt("contact_id");
            String firstName = resultSet.getString("fname");
            String middleName = resultSet.getString("mname");
            String lastName = resultSet.getString("lname");
//            int address_id = resultSet.getInt("address_id");
            String address_type = resultSet.getString("address_type");
            String street_address = resultSet.getString("street_address");
            String city = resultSet.getString("city");
            String state = resultSet.getString("state");
            String zip = resultSet.getString("zip");
//            int phone_id = resultSet.getInt("phone_id");
            String phone_type = resultSet.getString("phone_type");
            int area_code = resultSet.getInt("area_code");
            int phone_number = resultSet.getInt("phone_number");
            String date_type = resultSet.getString("date_type");
            Date date_entry = resultSet.getDate("date_entry");

            contacts = new Contacts(cID, firstName, middleName, lastName, address_type, street_address, city,
                    state, zip, phone_type, area_code, phone_number, date_type, date_entry);
            ContactList.add(contacts);
        }

        resultSet.close();
        statement.close();

        return ContactList;
    }


    public Session primaryKeys() throws SQLException {

        String sql1 = "SELECT address_id from Address order by address_id DESC limit 1; ";
        String sql2 = "SELECT phone_id from Phone order by phone_id DESC limit 1; ";
        String sql3 = "SELECT date_id from Date order by date_id DESC limit 1; ";
        String sql4 = "SELECT contact_id from Contact order by contact_id DESC limit 1; ";

        Session s = new Session();
        connect();
        Statement stmt = jdbcConnection.createStatement();
        stmt.execute("USE CONTACTS;");

        PreparedStatement statement1 = jdbcConnection.prepareStatement(sql1);
        ResultSet resultSet = statement1.executeQuery();

        while (resultSet.next()) {
        int address_id = resultSet.getInt("address_id");
            s.setAddress_id(address_id);
            System.out.println("ADDR: "+address_id);
        }

        PreparedStatement statement2 = jdbcConnection.prepareStatement(sql2);
        ResultSet resultSet2 = statement2.executeQuery();

        while (resultSet2.next()) {
            int phone_id = resultSet2.getInt("phone_id");
            s.setPhone_id(phone_id);
            System.out.println("PHONE: "+phone_id);
        }
        PreparedStatement statement3 = jdbcConnection.prepareStatement(sql3);
        ResultSet resultSet3 = statement3.executeQuery();

        while (resultSet.next()) {
            int date_id = resultSet3.getInt("phone_id");
            s.setDate_id(date_id);
            System.out.println("DATE_ID: "+date_id);
        }
        PreparedStatement statement4 = jdbcConnection.prepareStatement(sql4);
        ResultSet resultSet4 = statement4.executeQuery();
        while (resultSet4.next()) {
            int contact_id = resultSet4.getInt("contact_id");
            s.setContact_id(contact_id);
            System.out.println("contact_id: "+contact_id);
        }

        statement1.close();
        statement2.close();
        statement3.close();
        statement4.close();

        disconnect();
        return s;


    }


}
