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
        <a href="/DB_CRUD/new">Add New Contact</a>
        &nbsp;&nbsp;&nbsp;
        <a href="/DB_CRUD/allContacts">List All Contacts</a>

    </h2>
</center>

<div align="center">
    <c:if test="${contact != null}">
    <form action="update" method="post">
        </c:if>
        <c:if test="${contact == null}">
        <form action="insert" method="post">
            </c:if>
            <table border="1" cellpadding="5">
                <caption>
                    <h2>
                        <c:if test="${contact != null}">
                            Edit Contacts
                        </c:if>
                        <c:if test="${contact == null}">
                            Add New Contacts
                        </c:if>
                    </h2>
                </caption>
                <c:if test="${contact != null}">
                    <input type="hidden" name="id" value="<c:out value='${contact.id}' />" />
                </c:if>

                <%--<tr>
                    <th>ContactID: </th>
                    <td>
                        <input type="text" name="contact_id" size="45" value="<c:out value='${contact.contact_id}' />" />
                    </td>
                </tr>--%>
                <tr>
                    <th>FirstName: </th>
                    <td>
                        <input type="text" name="firstName" size="45" value="<c:out value='${contact.firstName}' />" />
                    </td>
                </tr>
                <tr>
                    <th>middleName: </th>
                    <td>
                        <input type="text" name="middleName" size="30"
                               value="<c:out value='${contact.middleName}' />"
                        />
                    </td>
                </tr>
                <tr>
                    <th>lastName: </th>
                    <td>
                        <input type="text" name="lastName" size="30"
                               value="<c:out value='${contact.lastName}' />"
                        />
                    </td>
                </tr>

                <tr>
                        <input type="hidden" name="address_id" size="30"
                               value="<c:out value='${address.address_id}' />"
                        />
                </tr>
                <tr>
                    <th>AddressType: </th>
                    <td>
                        <input type="text" name="address_type" size="30"
                               value="<c:out value='${address.address_type}' />"
                        />
                    </td>
                </tr>
                <tr>
                    <th>street_address: </th>
                    <td>
                        <input type="text" name="street_address" size="30"
                               value="<c:out value='${address.street_address}' />"
                        />
                    </td>
                </tr>
                <tr>
                    <th>City: </th>
                    <td>
                        <input type="text" name="city" size="30"
                               value="<c:out value='${address.city}' />"
                        />
                    </td>
                </tr>
                <tr>
                    <th>State: </th>
                    <td>
                        <input type="text" name="state" size="30"
                               value="<c:out value='${address.state}' />"
                        />
                    </td>
                </tr>
                <tr>
                    <th>Zip: </th>
                    <td>
                        <input type="text" name="zip" size="30"
                               value="<c:out value='${address.zip}' />"
                        />
                    </td>
                </tr>

                <tr>
                    <input type="hidden" name="phone_id" size="30"
                           value="<c:out value='${phone.phone_id}' />"
                    />
                </tr>
                <tr>
                    <th>PhoneType: </th>
                    <td>
                        <input type="text" name="phone_type" size="30"
                               value="<c:out value='${phone.phone_type}' />"
                        />
                    </td>
                </tr>
                <tr>
                    <th>AreaCode: </th>
                    <td>
                        <input type="text" name="area_code" size="30"
                               value="<c:out value='${phone.area_code}' />"
                        />
                    </td>
                </tr>
                <tr>
                    <th>PhoneNo: </th>
                    <td>
                        <input type="text" name="phone_number" size="30"
                               value="<c:out value='${phone.phone_number}' />"
                        />
                    </td>
                </tr>

                <tr>
                    <th>DateType: </th>
                    <td>
                        <input type="text" name="date_type" size="30"
                               value="<c:out value='${date.date_type}' />"
                        />
                    </td>
                </tr>
                <tr>
                    <th>Date: </th>
                    <td>
                        <input type="text" name="date_entry" size="30"
                               value="<c:out value='${date.date_entry}' />"
                        />
                    </td>
                </tr>


                <tr>
                    <td colspan="2" align="center">
                        <input type="submit" style="font-size:34pt;" value="Save" />
                    </td>
                </tr>
            </table>
        </form>


</div>
</body>
</html>
