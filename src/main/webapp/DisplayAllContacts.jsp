<%@ page import="java.util.List" %><%--<%@ page import="java.util.ArrayList" %>--%>
<%--
  Created by IntelliJ IDEA.
  User: sameer
  Date: 7/13/21
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
        <caption><h2>List of Contacts</h2></caption>
        <tr>
            <th>CONTACT_ID</th>
            <th>FIRSTNAME</th>
            <th>MIDDLENAME</th>
            <th>LASTNAME</th>
            <th>ADDRESS TYPE</th>
            <th>ADDRESS</th>
            <th>CITY</th>
            <th>STATE</th>
            <th>ZIP</th>
            <th>PHONE TYPE</th>
            <th>AREA CODE</th>
            <th>PHONE NO</th>
            <th>DATE TYPE</th>
            <th>DATE</th>
        </tr>

        <c:forEach var="x" items="${listContact}">
            <tr>
                <td><c:out value="${x.contact_id}" /></td>
                <td><c:out value="${x.firstName}" /></td>
                <td><c:out value="${x.middleName}" /></td>
                <td><c:out value="${x.lastName}" /></td>
                <td><c:out value="${x.address_type}" /></td>
                <td><c:out value="${x.street_address}" /></td>
                <td><c:out value="${x.city}" /></td>
                <td><c:out value="${x.state}" /></td>
                <td><c:out value="${x.zip}" /></td>
                <td><c:out value="${x.phone_type}" /></td>
                <td><c:out value="${x.area_code}" /></td>
                <td><c:out value="${x.phone_number}" /></td>
                <td><c:out value="${x.date_type}" /></td>
                <td><c:out value="${x.date_entry}" /></td>
                <td>
                    <a href="/DB_CRUD/edit?id=<c:out value='${x.contact_id}' />">Edit</a>
                    &nbsp;&nbsp;&nbsp;&nbsp;
                    <a href="/DB_CRUD/delete?id=<c:out value='${x.contact_id}' />">Delete</a>
                </td>
            </tr>

        </c:forEach>

    </table>
</div>
</body>
</html>
