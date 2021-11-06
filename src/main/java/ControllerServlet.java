import Contacts.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "ControllerServlet", value = "/ControllerServlet")
public class ControllerServlet extends HttpServlet {
    private ContactsDAO contactsDAO;


    public void init() {
        String jdbcURL = getServletContext().getInitParameter("jdbcURL");
        String jdbcUsername = getServletContext().getInitParameter("jdbcUsername");
        String jdbcPassword = getServletContext().getInitParameter("jdbcPassword");

        contactsDAO = new ContactsDAO(jdbcURL, jdbcUsername, jdbcPassword);

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String action = request.getServletPath();
        try {
            switch (action) {
                case "/new":
                    showNewForm(request, response);
                    break;
                case "/insert":
                    insertContact(request, response);
                    break;
                case "/delete":
                    deleteContact(request, response);
                    break;
                case "/search":
                    searchContact(request, response);
                    break;
                case "/edit":
                    showEditForm(request, response);
                    break;
                case "/update":
                    updateContact(request, response);
                    break;
                case "/allContacts":
                    searchAllContacts(request, response);
                    break;
                default:
                    listContact(request, response);
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    //Delete a contact
    private void deleteContact(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {

        int id = Integer.parseInt(request.getParameter("id"));
        Contact contact = new Contact();
        contact.setContact_id(id);
        contactsDAO.deleteContact(contact);
        response.sendRedirect("/DB_CRUD/ControllerServlet");  //defines where to go back after operation

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    //Show Contacts
    private void listContact(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("DisplayContacts.jsp");
        dispatcher.forward(request, response);
    }

    private void searchContact(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {

        Contact contact = new Contact();
        try {
            int id = Integer.parseInt(request.getParameter("contact_id"));
            contact.setContact_id(id);
            Contact listContact = contactsDAO.searchContact(contact);
            List<Address> addressList = contactsDAO.searchAddress(listContact);
            List<Phone> phoneList = contactsDAO.searchPhone(listContact);
            Datee dateList = contactsDAO.searchDate(listContact);

            request.setAttribute("listContact", listContact);
            request.setAttribute("addressList", addressList);
            request.setAttribute("phoneList", phoneList);
            request.setAttribute("dateList", dateList);

            RequestDispatcher dispatcher = request.getRequestDispatcher("DisplayContacts.jsp");

            dispatcher.forward(request, response);

        }catch (Exception e){
            String fname = request.getParameter("contact_id");  //Only Acting as a contact_ID
            contact.setFirstName(fname);
            Contact listContact = contactsDAO.searchContact(contact);
            List<Address> addressList = contactsDAO.searchAddress(listContact);
            List<Phone> phoneList = contactsDAO.searchPhone(listContact);
            Datee dateList = contactsDAO.searchDate(listContact);

            request.setAttribute("listContact", listContact);
            request.setAttribute("addressList", addressList);
            request.setAttribute("phoneList", phoneList);
            request.setAttribute("dateList", dateList);
            RequestDispatcher dispatcher = request.getRequestDispatcher("DisplayContacts.jsp");
            dispatcher.forward(request, response);
        }

    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("AddContacts.jsp");
        dispatcher.forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("id"));
        Contact c = new Contact();
        c.setContact_id(id);
        System.out.println("showEditForm");
        Contact contact = contactsDAO.searchContact(c);
        System.out.println("showEditForm2");
        List<Address> addressList = contactsDAO.searchAddress(contact);
        System.out.println("showEditForm3");
        List<Phone> phoneList = contactsDAO.searchPhone(contact);
        System.out.println("showEditForm4");
        Datee date = contactsDAO.searchDate(contact);

        request.setAttribute("listContact", contact);
        request.setAttribute("addressList", addressList);
        request.setAttribute("phoneList", phoneList);
        request.setAttribute("dateList", date);
        System.out.println("showEditForm5");
        RequestDispatcher dispatcher = request.getRequestDispatcher("EditContacts.jsp");
        dispatcher.forward(request, response);

    }

    private void insertContact(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {

        Session s = contactsDAO.primaryKeys();
        int contact_id = s.getContact_id() + 1;
        System.out.println("INSERT contact_id: "+contact_id);
        String firstName = request.getParameter("firstName");
        String middleName = request.getParameter("middleName");
        String lastName = request.getParameter("lastName");

        int address_id = s.getAddress_id() + 1;
        System.out.println("INSERT address_id: "+address_id);
        String street_address = request.getParameter("street_address");
        String address_type = request.getParameter("address_type");
        String city = request.getParameter("city");
        String state = request.getParameter("state");
        String zip = request.getParameter("zip");

        int phone_id = s.getPhone_id() + 1;
        System.out.println("INSERT PHONE: "+phone_id);
        String phone_type = request.getParameter("phone_type");
        int area_code = Integer.parseInt(request.getParameter("area_code"));
        int phone_number = Integer.parseInt(request.getParameter("phone_number"));

        int date_id = s.getDate_id() + 1;
        System.out.println("INSERT DATE: "+date_id);
        String date_type = request.getParameter("date_type");
        Date date_entry = Date.valueOf(request.getParameter("date_entry"));

        Contact newContact = new Contact(contact_id, firstName, middleName, lastName);
        Address address = new Address(address_type, street_address, city, state, zip, address_id);
        Phone phone = new Phone(phone_type, area_code, phone_number, phone_id);
        Datee date = new Datee(date_type, date_entry, date_id);
        System.out.println("Until here???");

        try {
            contactsDAO.insertContact(newContact);
            contactsDAO.insertAddress(address, newContact);
            contactsDAO.insertPhone(phone, newContact);
            contactsDAO.insertDate(date, newContact);
        }catch(Exception e){
            System.out.println("Error while inserting records");
        }

        response.sendRedirect("/DB_CRUD/ControllerServlet");
    }

    private void updateContact(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {

        int contact_id = Integer.parseInt(request.getParameter("contact_id"));
        String firstName = request.getParameter("firstName");
        String middleName = request.getParameter("middleName");
        String lastName = request.getParameter("lastName");

        int address_id = Integer.parseInt(request.getParameter("address_id"));
        String street_address = request.getParameter("street_address");
        String address_type = request.getParameter("address_type");
        String city = request.getParameter("city");
        String state = request.getParameter("state");
        String zip = request.getParameter("zip");

        int phone_id = Integer.parseInt(request.getParameter("phone_id"));
        String phone_type = request.getParameter("phone_type");
        int area_code = Integer.parseInt(request.getParameter("area_code"));
        int phone_number = Integer.parseInt(request.getParameter("phone_number"));

        int address_id2 =0, phone_id2 = 0;
        String street_address2 = "", address_type2 = "", city2 = "", state2 = "", zip2 = "" , phone_type2 = "",date_type ="";
        int area_code2=0, phone_number2 = 0 ,date_id = 0;
        java.sql.Date date_entry = null;

        try {
            address_id2 = Integer.parseInt(request.getParameter("address_id2"));
            street_address2 = request.getParameter("street_address2");
            address_type2 = request.getParameter("address_type2");
            city2 = request.getParameter("city2");
            state2 = request.getParameter("state2");
            zip2 = request.getParameter("zip2");
        }catch(Exception e) { }
        try{
             phone_id2 = Integer.parseInt(request.getParameter("phone_id2"));
             phone_type2 = request.getParameter("phone_type2");
             area_code2 = Integer.parseInt(request.getParameter("area_code2"));
             phone_number2 = Integer.parseInt(request.getParameter("phone_number2"));
        }catch (Exception e){
        }
        try {
            date_id = Integer.parseInt(request.getParameter("date_id"));
            date_type = request.getParameter("date_type");
            date_entry = Date.valueOf(request.getParameter("date_entry"));
        }catch (Exception e){}

        Contact newContact = new Contact(contact_id, firstName, middleName, lastName);
        Address address = new Address(address_type, street_address, city, state, zip, address_id);
        Phone phone = new Phone(phone_type, area_code, phone_number, phone_id);
        Datee date = new Datee(date_type, date_entry, date_id);

        System.out.println("Check 12");

        contactsDAO.updateContact(newContact);
        contactsDAO.updateAddress(address, newContact);
        contactsDAO.updatePhone(phone, newContact);
        contactsDAO.updateDate(date, newContact);

        if(!(address_id2 == 0)) {
            Address address2 = new Address(address_type2, street_address2, city2, state2, zip2, address_id2);
            contactsDAO.updateAddress(address2, newContact);
        }
        if(!(phone_id2 == 0)) {
            Phone phone2 = new Phone(phone_type2, area_code2, phone_number2, phone_id2);
            contactsDAO.updatePhone(phone2, newContact);
        }

        response.sendRedirect("/DB_CRUD/ControllerServlet");
    }

    private void searchAllContacts(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {

        List<Contacts> listContacts = contactsDAO.searchAllContact();

        request.setAttribute("listContact", listContacts);
        RequestDispatcher dispatcher = request.getRequestDispatcher("DisplayAllContacts.jsp");
        dispatcher.forward(request, response);
    }


}
