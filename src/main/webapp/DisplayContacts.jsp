<%@ page import="java.util.List" %><%--<%@ page import="java.util.ArrayList" %>--%>
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
        <form action="/DB_CRUD/search" method="get">
            <input type="text" var="contact_id" id="contact_id" name="contact_id" style="height:50px;width:500px;font-size:18pt;" placeholder="Search contacts..">
            <button type="submit" style="font-size:14pt;">Search</button>
        </form>

    </h2>
    <h3>
        <a href="/DB_CRUD/new">Add New Contact</a>
        &nbsp;&nbsp;&nbsp;
        <a href="/DB_CRUD/allContacts">List All Contacts</a>

    </h3>
</center>
<div align="center">
    <table border="1" cellpadding="5">
        <caption><h2>Contacts:</h2></caption>
        <tr>
            <th>CONTACT_ID</th>
            <th>FIRSTNAME</th>
            <th>MIDDLENAME</th>
            <th>LASTNAME</th>
        </tr>

            <tr>
                <td><c:out value="${listContact.contact_id}" /></td>
                <td><c:out value="${listContact.firstName}" /></td>
                <td><c:out value="${listContact.middleName}" /></td>
                <td><c:out value="${listContact.lastName}" /></td>
                <td>
                    <a href="/DB_CRUD/edit?id=<c:out value='${listContact.contact_id}' />">Edit</a>
                    &nbsp;&nbsp;&nbsp;&nbsp;
                    <a href="/DB_CRUD/delete?id=<c:out value='${listContact.contact_id}' />">Delete</a>
                </td>
            </tr>

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
            <c:forEach var="x" items="${addressList}">
                <tr>
                    <td><c:out value="${x.address_type}" /></td>
                    <td><c:out value="${x.street_address}" /></td>
                    <td><c:out value="${x.city}" /></td>
                    <td><c:out value="${x.state}" /></td>
                    <td><c:out value="${x.zip}" /></td>
                </tr>
            </c:forEach>

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
            <c:forEach var="x" items="${phoneList}">
                <tr>
                    <td><c:out value="${x.phone_type}" /></td>
                    <td><c:out value="${x.area_code}" /></td>
                    <td><c:out value="${x.phone_number}" /></td>
                </tr>
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
                <td><c:out value="${dateList.date_type}" /></td>
                <td><c:out value="${dateList.date_entry}" /></td>
            </tr>

    </table>
</div>

</body>
</html>
