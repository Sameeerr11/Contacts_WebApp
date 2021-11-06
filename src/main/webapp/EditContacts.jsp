<%@ page import="java.util.List" %>
<%@ page import="Contacts.Contact" %>
<%@ page import="Contacts.Address" %>
<%@ page import="javax.swing.text.Document" %><%--<%@ page import="java.util.ArrayList" %>--%>
<%--
  Created by IntelliJ IDEA.
  User: sameer
  Contacts.Datee: 7/13/21
  Time: 11:40 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>



<html>
<head>
    <title>Contacts List</title>
</head>
<body>
<center>
    <h1>Contacts</h1>
    <h2>
        <form action="/DB_CRUD/search" method="post">
            <input type="text" var="contact_id" name="contact_id" style="height:50px;width:500px;font-size:18pt;" placeholder="Search contacts..">
            <button type="submit" style="font-size:14pt;">Search</button>
        </form>

    </h2>
    <h3>
        <a href="/DB_CRUD/new">Add New Contact</a>
        &nbsp;&nbsp;&nbsp;
        <a href="/DB_CRUD/allContacts">List All Contacts</a>

    </h3>
</center>


<form method="post" action="update">
    <div align="center">
        <table border="1" cellpadding="5">
            <caption><h2>Contacts:</h2></caption>
            <tr>
                <th>CONTACT_ID</th>
                <th>FIRSTNAME</th>
                <th>MIDDLENAME</th>
                <th>LASTNAME</th>
            </tr>

            <td><input type="text" name="contact_id" size="45" value='${listContact.contact_id}' /></td>
            <td><input type="text" name="firstName" size="45" value='${listContact.firstName}' /></td>
            <td><input type="text" name="middleName" size="45" value='${listContact.middleName}' /></td>
            <td><input type="text" name="lastName" size="45" value='${listContact.lastName}' /></td>

        </table>
    </div>

    <div align="center">
        <table border="1" cellpadding="5">
            <caption><h2>Address:</h2></caption>
            <tr>
                <th>ADDRESS_TYPE</th>
                <th>STREET_ADDRESS</th>
                <th>CITY</th>
                <th>STATE</th>
                <th>ZIP</th>
            </tr>
                <tr>
                    <input type="hidden" name="address_id" size="45" value='${addressList[0].address_id}' />
                    <td><input type="text" name="address_type" size="45" value='${addressList[0].address_type}' /></td>
                    <td><input type="text" name="street_address" size="45" value='${addressList[0].street_address}' /></td>
                    <td><input type="text" name="city" size="45" value='${addressList[0].city}' /></td>
                    <td><input type="text" name="state" size="45" value='${addressList[0].state}' /></td>
                    <td><input type="text" name="zip" size="45" value='${addressList[0].zip}' /></td>

                </tr>
                <tr>
                <c:forEach var="x" items="${addressList}" begin="1">
                    <c:if test="${not empty addressList[1].address_id}">
                    <input type="hidden" name="address_id2" size="45" value='${addressList[1].address_id}' />
                    <td><input type="text" name="address_type2" size="45" value='${addressList[1].address_type}' /></td>
                    <td><input type="text" name="street_address2" size="45" value='${addressList[1].street_address}' /></td>
                    <td><input type="text" name="city2" size="45" value='${addressList[1].city}' /></td>
                    <td><input type="text" name="state2" size="45" value='${addressList[1].state}' /></td>
                    <td><input type="text" name="zip2" size="45" value='${addressList[1].zip}' /></td>
                    </c:if>
                </c:forEach>
                </tr>

        </table>
    </div>

    <div align="center">
        <table border="1" cellpadding="5">
            <caption><h2>Phone:</h2></caption>
            <tr>
                <th>PHONE_TYPE</th>
                <th>AREA_CODE</th>
                <th>PHONE_NUMBER</th>
            </tr>
<%--            <c:forEach var="x" items="${phoneList}">--%>
                <tr>
                    <input type="hidden" name="phone_id" size="45" value='${phoneList[0].phone_id}' />
                    <td><input type="text" name="phone_type" size="45" value='${phoneList[0].phone_type}' /></td>
                    <td><input type="text" name="area_code" size="45" value='${phoneList[0].area_code}' /></td>
                    <td><input type="text" name="phone_number" size="45" value='${phoneList[0].phone_number}' /></td>
                </tr>
<%--            </c:forEach>--%>
                <c:forEach var="x" items="${addressList}" begin="1">
                    <c:if test="${not empty addressList[1].address_id}">
                        <tr>
                        <input type="hidden" name="phone_id" size="45" value='${phoneList[1].phone_id}' />
                        <td><input type="text" name="phone_type" size="45" value='${phoneList[1].phone_type}' /></td>
                        <td><input type="text" name="area_code" size="45" value='${phoneList[1].area_code}' /></td>
                        <td><input type="text" name="phone_number" size="45" value='${phoneList[1].phone_number}' /></td>
                        </tr>
                    </c:if>
                </c:forEach>

        </table>
    </div>

    <div align="center">
        <table border="1" cellpadding="5">
            <caption><h2>Date:</h2></caption>
            <tr>
                <th>DATE_TYPE</th>
                <th>DATE</th>
            </tr>

            <tr>
                <input type="hidden" name="date_id" size="45" value='${dateList.date_id}' />
                <td><input type="text" name="date_type" size="45" value='${dateList.date_type}' /></td>
                <td><input type="text" name="date_entry" size="45" value='${dateList.date_entry}' /></td>

            </tr>

        </table>
    </div>

    <td></td>

    <div align="center">
        <tr>
            <td colspan="2" align="center">
                <input type="submit" style="font-size:34pt;" value="Save" />
            </td>
        </tr>
    </div>

</form>

</body>
</html>
