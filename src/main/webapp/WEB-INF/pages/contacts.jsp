<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Hello world</title>
    <style>
        .error {
            color: red; font-weight: bold;
        }
    </style>
</head>
<body>
<header>
</header>

<form action="/toActivityPage" method="post" id="activities" >
<input type="submit" value="Activities" >
</form>


    <table>
        <sf:form method="post" action="/saveContact" modelAttribute="contact" id="saveContact">

        <tr>
            <td><sf:hidden path="id"></sf:hidden></td>
        </tr>

            <tr>
                <td><sf:label path="firstName">First Name *</sf:label></td>
                <td><sf:input path="firstName" ></sf:input></td>
                <td><sf:errors path="firstName" cssClass="error"></sf:errors></td>
            </tr>

            <tr>
                <td><sf:label path="lastName">Last Name *</sf:label></td>
                <td><sf:input path="lastName"></sf:input></td>
                <td><sf:errors path="lastName" cssClass="error"></sf:errors></td>
            </tr>

            <tr>
                <td><sf:label path="email">Email *</sf:label></td>
                <td><sf:input path="email"></sf:input></td>
                <td><sf:errors path="email" cssClass="error" ></sf:errors></td>
            </tr>

        <tr>
            <td><sf:label path="phone">Phone</sf:label></td>
            <td><sf:input path="phone"></sf:input></td>
            <td><sf:errors path="phone" cssClass="error" ></sf:errors></td>
        </tr>

        <tr>
            <td><sf:label path="address1">Address 1</sf:label></td>
            <td><sf:input path="address1"></sf:input></td>
            <td><sf:errors path="address1" cssClass="error" ></sf:errors></td>
        </tr>

        <tr>
            <td><sf:label path="address2">Address 2</sf:label></td>
            <td><sf:input path="address2"></sf:input></td>
            <td><sf:errors path="address2" cssClass="error" ></sf:errors></td>
        </tr>

        <tr>
            <td><sf:label path="city">City</sf:label></td>
            <td><sf:input path="city"></sf:input></td>
            <td><sf:errors path="city" cssClass="error" ></sf:errors></td>
        </tr>

        <tr>
            <td><sf:label path="postCode">Post Code</sf:label></td>
            <td><sf:input path="postCode"></sf:input></td>
            <td><sf:errors path="postCode" cssClass="error" ></sf:errors></td>
        </tr>

            <tr>
                <td><input type="submit" value="Add contact/Edit Contact"></td>
            </tr>
        </sf:form>

        <tr>
            <form method="POST" action="/deleteContact?id=${(contact.id == null || contact.id <= 0) ? 0 : contact.id}"
                  id="deleteContact" onSubmit="if(!confirm('Are you sure you want delete contact?')){return false;}">
                <td><input type="submit" value="Delete contact"></td>
            </form>
        </tr>
    </table>



        <table width="600px">
            <tr>
                <td><p>Contacts</p></td>
            </tr>
            <tr><td><label>-----------------------------------------------------------</label></td></tr>
            <c:forEach var="c" items="${contacts}">
                <tr>
                    <td><a href="/editContact?id=${c.id}">${c.firstName} ${c.lastName}</a></td>
                </tr>
            </c:forEach>
            <tr><td><label>-----------------------------------------------------------</label></td></tr>
        </table>

            <table width="600px">
                <tr>
                    <td><p>   Activities   </p></td>
                </tr>
                <tr>
                    <td><label>Title</label></td>
                    <td><label>Type</label></td>
                    <td><label>Date</label></td>
                </tr>
                        <c:forEach items="${contact.activities}" var="act">
                            <tr>
                                <td> ${act.title} </td>
                                <td> ${act.activityType} </td>
                                <td> ${act.date} </td>
                            </tr>
                        </c:forEach>

            </table>

</body>
</html>
